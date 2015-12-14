package controlador;

import java.rmi.RemoteException;
import java.util.*;

import dao.*;
import utils.*;
import xml2.ListaComparativaXML;
import xml2.OrdenCompraXML;
import xml2.RemitoXML;
import negocio.*;
import dto.*;
import interfaces.*;

public class AdministracionCC implements IAdministracionCC {

	public static AdministracionCC administracion;
	private static CCNegocio casaCentralNegocio;
	
	public static AdministracionCC getInstancia()  throws RemoteException{
		if (administracion == null) {
			administracion = new AdministracionCC();
		}
		return administracion;
	}
	
	public AdministracionCC(){
		if (casaCentralNegocio==null){
			casaCentralNegocio = new CCNegocio();
			//Inicializo la CC
			levantarCc();
			//Levanto el XML para cargar la ListaPrincipal (ListaComparativa)
			levantarXml();
		}		
	}
	
	

	public static AdministracionCC getAdministracion() {
		return administracion;
	}

	public static void setAdministracion(AdministracionCC administracion) {
		AdministracionCC.administracion = administracion;
	}

	public static CCNegocio getCasaCentralNegocio() {
		return casaCentralNegocio;
	}

	public static void setCasaCentralNegocio(CCNegocio casaCentralNegocio) {
		AdministracionCC.casaCentralNegocio = casaCentralNegocio;
	}
	
	/*
	 * No es necesario una lista de cotizaciónes 
	 * Debera levantar todas las cotizaciones que aun no fueron cargadas a una orden de compra 
	 * Se requiere un nuevo estado en la orden de compra y en la solicitud de cotización
	 * Marcar solicitud de cotización como "En Adquisición" 
	 * Marcar Orden de compra como "Nueva" luego de su creación 
	 * y previo a la entrega al proveedor
	 */
	// Carlos: Requerido para el cliente web
	@Override
	public List <OrdenCompraDto> crearOrdenCompraXid(List<String> idsSolCompra, String formaDePago) throws RemoteException {
		List<SolicitudCompraDto> solCompraDTO = new ArrayList<SolicitudCompraDto>();
		for (int i = 0; i < idsSolCompra.size(); i++) {
			int idSolCot = Integer.getInteger(idsSolCompra.get(i));
			SolicitudCompraNegocio solNegocio = SolicitudCompraDAO.getInstancia().buscarSolicitudCompra(idSolCot);
			SolicitudCompraDto solDto = solNegocio.aSolicitudCompraDTO();
			solCompraDTO.add(solDto);
		}
		List <OrdenCompraDto> orden = this.crearOrdenCompra(solCompraDTO, formaDePago);
		return orden;
	}

	
	public List <OrdenCompraDto> crearOrdenCompra(List<SolicitudCompraDto> listaCotizaciones, String formaPago) throws RemoteException {	
		//Conventirmos SolicitudesCompraDTO a Negocio
		List<SolicitudCompraNegocio> solCompraNeg = new ArrayList<SolicitudCompraNegocio>();
		for(int i = 0; i < listaCotizaciones.size(); i++){
			SolicitudCompraNegocio solCompra = new SolicitudCompraNegocio();
			//solCompra.aSolicitudCompraNegocio(listaCotizaciones.get(i));
			//busco las solicitudes de compra que coincidan con el numero de la solicitud DTO
			solCompra = SolicitudCompraDAO.getInstancia().buscarSolicitudCompra(listaCotizaciones.get(i).getNumeroSolicitudCompra());
			solCompraNeg.add(solCompra);
		}
		Double total = 0.0;
		Double descuentos = 0.0;
		//Obtner los proveedores de todos los rodamientos de las solicitudCompraNegocio
		List<ProveedorNegocio> proveedores = new ArrayList<ProveedorNegocio>();
		List <OrdenCompraDto> ordenes = new ArrayList <OrdenCompraDto>();
		proveedores = SolicitudCompraDAO.getInstancia().proveedoresDeSolicitudCompra(solCompraNeg);
		
		for (int i = 0; i < proveedores.size(); i++) {
			// Creamos la OC para este proveedor
			OrdenCompraNegocio OCN = new OrdenCompraNegocio();
			OCN.setProveedor(proveedores.get(i));
			OCN.setFormaPago(formaPago);
			OCN.setEstado("Nueva");
			total = 0.0;
			descuentos = 0.0;		
			//Generamos los ItemsOC
			List<ItemOrdenCompraNegocio> itemsOC = new ArrayList<ItemOrdenCompraNegocio>();
			//Levanto todos los rodamientos de las cotizaciones para el proveedor i
			List<Object[]> misObjects = SolicitudCompraDAO.getInstancia().rodamientoYcantidadXsolicitudCompraYproveedor(solCompraNeg, "Nueva", proveedores.get(i));
			for (int j = 0; j < misObjects.size(); j++) {
				ItemOrdenCompraNegocio iOC = new ItemOrdenCompraNegocio();
				RodamientoNegocio ro = RodamientoDAO.getInstancia().buscarRodamiento((Integer)misObjects.get(j)[0]);
				iOC.setRodamiento(ro);
				iOC.setCantidad(Integer.valueOf(misObjects.get(j)[1].toString()));
				// Calculo el precio segun la estrategía que tenga el proveedor
				iOC.setMonto(ro.getValorStrategy(proveedores.get(j),iOC.getCantidad(), OCN.getFormaPago()));
				total = total + iOC.getMonto();
				itemsOC.add(iOC);
			}
			OCN.setTotal(total.floatValue());

			// Asiganmos los itemsOC QUE CREAMOS
			OCN.setItems(itemsOC);
	
			// Asignamos las solicitudes de compra que dieron origen a la OC
			OCN.setSolicitudesCompra(solCompraNeg);
			ordenes.add(OCN.aOrdenCompraDto());
			
			//Vamos agregando las OCN al vector de Cotizaciones de CC
			this.getCasaCentralNegocio().getOrdenesP().add(OCN);
		}

		//Guardamos la Orden de compra y la volvemos a obtener para su uso posterior
		this.getCasaCentralNegocio().mergeCC();
		this.setCasaCentralNegocio(CCDAO.getInstancia().obtenerCC());
		
	return ordenes;
	}
	
	
	/*Esto ahora va a levantar un XML que "nos da el proveedor" (Ver clase test.CargarDatosListaComparativa)
	IMPORTANTE: Si no tenes el "RodamientosProveedores.xml podes ejecutarlo desde TestDaro para generarlo*/
	public void levantarXml(){		
		//Levanto el XML RodamientosProveedores
		ItemNegocioList miItemNegocioList = new ItemNegocioList();
		miItemNegocioList = ListaComparativaXML.getInstancia().xmlTOitemlist("RodamientosProveedores.xml");
		
		//Recorro el ItemNegocioList y cargo la listaComparativa
		for (int i=0; i<miItemNegocioList.getMisItemsNegocio().size(); i++){
			casaCentralNegocio.getListaPrincipal().add(miItemNegocioList.getMisItemsNegocio().get(i).getRodamiento());
		}
	}
	
	private void levantarCc() {
		this.setCasaCentralNegocio(CCDAO.getInstancia().obtenerCC());
	}

	


	public void actualizarStock(List<ItemDto> listaItemsParametro, String accion) {

		//List<RodamientoNegocio> listaRodamiento = new ArrayList<RodamientoNegocio>();
		List<ItemDto> miListaItemsDto = new ArrayList<ItemDto>();
		List<ItemNegocio> miListaItemNegocio = new ArrayList<ItemNegocio>();
		int cantidad = 0;
		
		//Por cada elemento de la lista pasada como parametro
		for (int i = 0; i < listaItemsParametro.size(); i++) {
			//Creo item
			ItemDto miItemDto = new ItemDto();
			//Busco el rodamiento en la Base de Datos para asignarlo con todos sus campos
			RodamientoNegocio miRodaNeg = new RodamientoNegocio();
			
			//Obtengo la Marca Origen y Codigo
			miRodaNeg.setMarca(listaItemsParametro.get(i).getRodamiento().getMarca());
			miRodaNeg.setOrigen(listaItemsParametro.get(i).getRodamiento().getOrigen());
			miRodaNeg.setCodigo(listaItemsParametro.get(i).getRodamiento().getCodigo());
			
			miRodaNeg = RodamientoDAO.getInstancia().buscarRodamientoPorCodigoMarcaOrigen(miRodaNeg);
			miItemDto.setRodamiento(miRodaNeg.aRodamientoDto());
			miItemDto.setCantidad(listaItemsParametro.get(i).getCantidad());
			miListaItemsDto.add(miItemDto);
		}
		
		//Transformar miListaItemsDto a listaRodamiento (negocio)
		for (int i = 0; i < miListaItemsDto.size(); i++) {
			ItemNegocio itemNeg = new ItemNegocio();
			//Seteo la cantidad
			itemNeg.setCantidad(miListaItemsDto.get(i).getCantidad());
			//Transformo el Rodamiento a RodamientoNegocio
			RodamientoNegocio rodaNeg = new RodamientoNegocio();
			rodaNeg.aRodamientoNegocio(miListaItemsDto.get(i).getRodamiento());
			//Seteo el Rodamiento a la lista
			itemNeg.setRodamiento(rodaNeg);
			//Agrego el item generado a la lista
			miListaItemNegocio.add(itemNeg);
		}

		// Recorrer la lista de rodamientos
		for (int j = 0; j < miListaItemNegocio.size(); j++) {
			//Asignamos cantidad por cada iteracion
			cantidad = miListaItemNegocio.get(j).getCantidad();
			
			RodamientoNegocio rodamiento = new RodamientoNegocio();
			rodamiento = miListaItemNegocio.get(j).getRodamiento();

			if ((accion.equalsIgnoreCase("sumar")) || (accion.equalsIgnoreCase("suma"))) {
				rodamiento.setStock(rodamiento.buscarStock(miListaItemNegocio.get(j).getRodamiento()) + cantidad);
				rodamiento.mergeRodamiento();
			}
			if ((accion.equalsIgnoreCase("restar")) || (accion.equalsIgnoreCase("resta"))) {
				rodamiento.setStock(rodamiento.buscarStock(miListaItemNegocio.get(j).getRodamiento()) - cantidad);
				rodamiento.mergeRodamiento();

				// La validación que sigue es por si la cantidad pasa a ser un número negativo
				int cantStock = rodamiento.buscarStock(miListaItemNegocio.get(j).getRodamiento());
				if (cantStock < 0) {
					rodamiento.setStock(0);
					rodamiento.mergeRodamiento();
				}
			}

		}

	}

	// Daro: Obtiene la lista comparativa (RodamientoNegocio), la transforma y
	// devuFelve (RodamientoDto)
	public List<RodamientoDto> obtenerListaComparativa() throws RemoteException {
		List<RodamientoNegocio> rodasNegocio = AdministracionCC.casaCentralNegocio.getListaPrincipal();
		List<RodamientoDto> rodasDto = new ArrayList<RodamientoDto>();
		for (int i = 0; i < rodasNegocio.size(); i++) {
			rodasDto.add(rodasNegocio.get(i).aRodamientoDto());
		}
		return rodasDto;
	}

	public RodamientoNegocio buscarRodamientoNegocio(String codigo) {
		
		for (Iterator<RodamientoNegocio> iterador = casaCentralNegocio.getRodamientos().iterator(); iterador.hasNext();) {
			RodamientoNegocio rodamiento = iterador.next();
			if (rodamiento.getCodigo().equals(codigo))
				return rodamiento;
		}
		return null;
	}	
	
	public void crearProveedor(ProveedorDto proveedor) throws RemoteException {

		ProveedorNegocio pro = new ProveedorNegocio();
		pro.setCUIT(proveedor.getCUIT());
		pro.setNombre(proveedor.getNombre());
		pro.persistirProveedor();				
	}
	
	public void eliminarProveedor(ProveedorDto proveedor) throws RemoteException {			
		ProveedorNegocio provee = ProveedorDAO.getInstancia().buscarProveedorPorCUIT(proveedor.getCUIT());
		provee.deleteProveedor();
	}

	public void modificarProveedor(ProveedorDto proveedor) throws RemoteException {				
		ProveedorNegocio provee = ProveedorDAO.getInstancia().buscarProveedorPorCUIT(proveedor.getCUIT());
		provee.setNombre(proveedor.getNombre());
		provee.updateProveedor();
	}
	
	@Deprecated
	public List<RodamientoDto> obtenerListaComparativaOpcional() throws RemoteException {		
		return null;
	}

	public List <OrdenCompraDto> obtenerOrdenesCompra () throws RemoteException{
		List <OrdenCompraNegocio> ordenesCompra = OrdenCompraDAO.getinstancia().obtenerOrdenCompra();
		List <OrdenCompraDto> ordenesDto = new ArrayList <OrdenCompraDto>();
		for (OrdenCompraNegocio ordenCompra : ordenesCompra) {
			ordenesDto.add(ordenCompra.aOrdenCompraDto());
		}
		return ordenesDto;
	}

	@SuppressWarnings("static-access")
	public void aprobarOrdenCompra(int nroOrden) throws RemoteException {
	
		OrdenCompraNegocio ordenNegocio = new OrdenCompraNegocio();
		ordenNegocio = OrdenCompraDAO.getinstancia().obtenerOrdenCompraPorId(nroOrden);
		
		//Por cada uno de la base
		for(int i=0; i<this.getCasaCentralNegocio().getOrdenesP().size(); i++){
			//Si coincide el nroOrden
			if(nroOrden == this.getCasaCentralNegocio().getOrdenesP().get(i).getIdOrdenCompra()){
				//Seteo el estado aprobada
				this.getCasaCentralNegocio().getOrdenesP().get(i).setEstado("Aprobada");
			}
		}	
		
		//Guardamos la Orden de compra y la volvemos a obtener para su uso posterior
		this.getCasaCentralNegocio().mergeCC();
		this.setCasaCentralNegocio(CCDAO.getInstancia().obtenerCC());
		
		//Guardo XML
		OrdenCompraXML.getInstancia().ordencompraTOxml(ordenNegocio);
		
	}

}
