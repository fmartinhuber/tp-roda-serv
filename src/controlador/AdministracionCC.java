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
	
	public static AdministracionCC getInstancia() {
		if (administracion == null) {
			//Creo la CasaCentral
			casaCentralNegocio = new CCNegocio();
			//Inicializo la CC
			administracion = new AdministracionCC();
		}
		return administracion;
	}
	
	public AdministracionCC()  {
		//Inicializo todos los array
		casaCentralNegocio.setOrdenesP(new ArrayList<OrdenCompraNegocio>());
		casaCentralNegocio.setRodamientos(new ArrayList<RodamientoNegocio>());
		casaCentralNegocio.setListaPrincipal(new ArrayList<RodamientoNegocio>());
		casaCentralNegocio.setListaOpcional(new ArrayList<RodamientoNegocio>());
		//Levanto el XML para cargar la ListaPrincipal (ListaComparativa)
		levantarXml();
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
	
	// Carlos: Requerido para el cliente we
	@Override
	public int crearOrdenCompraXid(List<String> idsSolCompra, String formaDePago) throws RemoteException {
		// TODO Auto-generated method stub
		List<SolicitudCompraDto> solCompraDTO = new ArrayList<SolicitudCompraDto>();
		for (int i = 0; i < idsSolCompra.size(); i++) {
			int idSolCot = Integer.getInteger(idsSolCompra.get(i));
			SolicitudCompraNegocio solNegocio = SolicitudCompraDAO.getInstancia().buscarSolicitudCompra(idSolCot);
			SolicitudCompraDto solDto = solNegocio.aSolicitudCompraDTO();
			solCompraDTO.add(solDto);
		}
		int salida = this.crearOrdenCompra(solCompraDTO, formaDePago);
		return salida;
	}

	
	public int crearOrdenCompra(List<SolicitudCompraDto> listaCotizaciones, String formaPago) throws RemoteException {	
		
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
		//Creo Array de OC para persistir todo junto al final, por que rompe al persistir indivudual
		List<OrdenCompraNegocio> ordenes = new ArrayList<OrdenCompraNegocio>(); 
		//Obtner los proveedores de todos los rodamientos de las solicitudCompraNegocio
		List<ProveedorNegocio> proveedores = new ArrayList<ProveedorNegocio>();
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
			descuentos = total*0.05;
			OCN.setDescuento(descuentos.floatValue());
			// Asiganmos los itemsOC QUE CREAMOS
			OCN.setItems(itemsOC);
	
			// Asignamos las solicitudes de compra que dieron origen a la OC
			OCN.setSolicitudesCompra(solCompraNeg);
			ordenes.add(OCN);
			
		}
		// Actualizamos estado de las Solicitudes de compra
		for (int k = 0; k < solCompraNeg.size(); k++) {
			solCompraNeg.get(k).setEstado("Adquisición");
		}
		
		for (int i = 0; i < ordenes.size(); i++) {
			ordenes.get(i).mergeOrdenCompra();
			OrdenCompraXML.getInstancia().ordencompraTOxml(ordenes.get(i));
		}
		
		//Creo el XML de Orden de Compra
		
		
		return OrdenCompraDAO.getinstancia().obtenerMaximoIDOrdenCompra();
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
	
	
	public OVNegocio ObtenerOV(int numeroOV){		
		OVNegocio salida = OVDAO.getInstancia().obtenerOV(numeroOV);
		return salida;
	}
	// Levanta las cotizaciones en un estado pasado por parametro "XXXXXXXX" //
	// "APROBADA"
	// PASAR A PRIVADO LUEGO DE LAS PRUEBAS
	@Deprecated
	public List<CotizacionDto> obtenerCotizacionesAprobadas() throws RemoteException {
		
		List<CotizacionNegocio> cotizaciones = CotizacionDAO.getinstancia().obtenerCotizacionesAprobada("ACEPTADA");
		List<CotizacionDto> cotizacionesDto = new ArrayList<CotizacionDto>();
		for (int i = 0; i < cotizaciones.size(); i++) {
			cotizacionesDto.add(cotizaciones.get(i).aCotizacionDto());
		}
		return cotizacionesDto;
	}

	
	/* Atributos de un remito
	 *  Fecha: fecha de la emisión del remito
	 *  Referencia: se identifica el contenido del remito
	 * 	Cliente: indica quien es el receptor del remito. Si no está dado de alta, se puede crear 
	 * 	Sucursal: por defecto trae la casa central y se puede cambiar a una orden de venta
	 * 	Domicilio: domicilio de la Sucursal seleccionada
	 * 	Depósito: se especifica de donde se extraen los productos
	 * 	Orden de Trabajo: es al número de la orden de trabajo que dio origen al remito
	 *  El remito modifica el stock de los artículos despachados
	 */
	public int crearRemito(List<OrdenCompraDto> listaOrdenes, ProveedorDto proveedor) throws RemoteException {
			
		ProveedorNegocio proov = ProveedorDAO.getInstancia().buscarProveedorPorCUIT(proveedor.getCUIT());
		RemitoNegocio remito = new RemitoNegocio();
		
		remito.setProveedor(proov);
		remito.setComentarios("Satisfecho");
		remito.setEstado("Recibido");
		Calendar c = new GregorianCalendar();
		remito.setFecha(c.getTime());
		
		//Obtenemos todas las Ordenes de Compra de la Base
		List<OrdenCompraNegocio> ordCompNeg = new ArrayList<OrdenCompraNegocio>();
		ordCompNeg = OrdenCompraDAO.getinstancia().obtenerOrdenCompra();
		
		//Declaramos el vector de ordenes a ser guardado en el Remito
		List<OrdenCompraNegocio> ordenes = new ArrayList<OrdenCompraNegocio>();
		//Comparamos si coincide la Orden de Compra con la pasada por parametro
		//Por cada una de la Base
		for (int i=0; i<ordCompNeg.size(); i++){
			//Por cada una del parametro
			for (int j=0; j<listaOrdenes.size(); j++){
				//Comparamos si sus ID coinciden
				if (ordCompNeg.get(i).getIdOrdenCompra() == listaOrdenes.get(j).getNumeroOrdenCompra()){
					//Si coinciden, nos quedamos con el obtenido en la BD
					OrdenCompraNegocio ordenCompNeg = new OrdenCompraNegocio();
					ordenCompNeg = ordCompNeg.get(i);
					ordenes.add(ordenCompNeg);
				}
			}
		}
		
		remito.setOrdenesDeCompra(ordenes);
		remito.mergeRemito();				
		
		// Aumentar el stock que ingresaron
		List<ItemDto> items = new ArrayList<ItemDto>();
		//Por cada orden de Compra
		for(int i=0; i<listaOrdenes.size(); i++){
			//Por cada item de la orden de compra
			for (int j=0; j<listaOrdenes.get(i).getItems().size(); j++){
				//Creo un ItemDto y asigno sus valores
				ItemDto miItemDto = new ItemDto();
				miItemDto.setCantidad(listaOrdenes.get(i).getItems().get(j).getCantidad());
				miItemDto.setRodamiento(listaOrdenes.get(i).getItems().get(j).getRodamiento());
				//Agrego el Dto a la lista de items
				items.add(miItemDto);
			}
		}		
							
		//items.get(i).setCantidad(listaOrdenes.get(i).getItems().get(i).getCantidad());
		//items.get(i).setRodamiento(listaOrdenes.get(i).getItems().get(i).getRodamiento());
		//items.get(i).setCantidad(ordenes.get(i).getItems().get(i).getCantidad());
		//items.get(i).setRodamiento(ordenes.get(i).getItems().get(i).getRodamiento().aRodamientoDto());	
		
		AdministracionCC.getInstancia().actualizarStock(items, "sumar");
		
		//RemitoXML.getInstancia().remitoTOxml(remito);
		
		return RemitoDAO.getinstancia().obtenerMaximoIDRemito();
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

	public void actualizarListaComparativa(List<RodamientoDto> listado) throws RemoteException {
		// TODO REVISAR.
		Iterator<RodamientoNegocio> iterador = AdministracionCC.casaCentralNegocio.getListaPrincipal().iterator();
		while (iterador.hasNext()) {
			RodamientoNegocio roda = iterador.next();
			this.agregarNuevoRodamiento(roda);
		}
	}

	private void agregarNuevoRodamiento(RodamientoNegocio rodamiento) {
		
		Iterator<RodamientoNegocio> iterador = AdministracionCC.casaCentralNegocio.getListaPrincipal().iterator();
		boolean encontradoP = false, actualizadoP = false;
		while (iterador.hasNext() && !encontradoP) {
			RodamientoNegocio rodamientoComp = iterador.next();
			if (rodamientoComp.getCodigo().equals(rodamiento.getCodigo())) {
				encontradoP = true;
				// si es mas barato
				if (rodamientoComp.getMonto() < rodamiento.getMonto()) {
					actualizadoP = true;
					casaCentralNegocio.getListaPrincipal().remove(rodamientoComp);
					casaCentralNegocio.getListaPrincipal().add(rodamiento);
					break;
				}
			}
		}
		if (encontradoP && !actualizadoP) {
			AdministracionCC.casaCentralNegocio.getListaPrincipal().add(rodamiento);
		}
		if (!encontradoP && !actualizadoP) {
			iterador = AdministracionCC.casaCentralNegocio.getListaOpcional().iterator();
			while (iterador.hasNext() && !encontradoP) {
				RodamientoNegocio rodamientoComp = iterador.next();
				if (rodamientoComp.getCodigo().equals(rodamiento.getCodigo())) {
					AdministracionCC.casaCentralNegocio.getListaOpcional().add(rodamiento);
					actualizadoP = true;
					break;
				}
			}
		}
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

	

}
