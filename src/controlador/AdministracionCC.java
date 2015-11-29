package controlador;

import java.rmi.RemoteException;
import java.util.*;

import dao.*;
import utils.*;
import xml2.ListaComparativaXML;
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
	
	public void crearOrdenCompra(List<SolicitudCompraDto> listaCotizaciones) throws RemoteException {	
		
		//Conventirmos SolicitudesCompraDTO a Negocio
		List<SolicitudCompraNegocio> solCompraNeg = new ArrayList<SolicitudCompraNegocio>();
		for(int i = 0; i < listaCotizaciones.size(); i++){
			SolicitudCompraNegocio solCompra = new SolicitudCompraNegocio();
			solCompra.aSolicitudCompraNegocio(listaCotizaciones.get(i));
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
			OCN.setFormaPago("Contado");
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
		
//		
//		OrdenCompraNegocio orden = new OrdenCompraNegocio();		
//		orden.setEstado("en adquisicion");
//		
//		EstrategiaFormaPago estrategia = new EstrategiaFormaPago();
//		float monto=200;
//		monto = estrategia.calcularTotal(formaDePago, monto);
//				
//		if(formaDePago.equalsIgnoreCase("efectivo")){
//			orden.setDescuento(monto);
//			orden.setFormaPago(formaDePago);
//			orden.setTotal(900);
//		}		
//		
//		if(formaDePago.equalsIgnoreCase("tarjeta")){
//			orden.setDescuento(20);
//			orden.setFormaPago(formaDePago);
//			orden.setTotal(100);
//		}
//		
//		List<ItemOrdenCompraNegocio> itemsOrdenCompra = new ArrayList<ItemOrdenCompraNegocio>();
//		//itemsOrdenCompra = ItemOrdenCompraDAO.getInstancia().listarItemsOrdenCompra();
//		for(int i=0; i<listaCotizaciones.size(); i++){
//			ItemOrdenCompraNegocio itemOrdenCompra = new ItemOrdenCompraNegocio();
//			//itemOrdenCompra.setCantidad(itemsOrdenCompra.get(i).getCantidad());
//			//itemOrdenCompra.setMonto(itemsOrdenCompra.get(i).getMonto());
//			itemOrdenCompra.setCantidad(20);
//			itemOrdenCompra.setMonto(2000);
//			// TODO: Rama, meter el select a la base de datos, hice esto rápido para solucionar esta garcha
//			itemsOrdenCompra.add(itemOrdenCompra);
//		}
//		
//		orden.setItems(itemsOrdenCompra);
//		orden.persistirOrdenCompra();
//		
//		return OrdenCompraDAO.getinstancia().obtenerMaximoIDOrdenCompra();
	}
	
	public void pchBorrarAlTerminar(){
		List<SolicitudCompraNegocio> solCompra = SolicitudCompraDAO.getInstancia().solicitudCompraXestado("Nueva");
		List<SolicitudCompraDto> solCompraDTO = new ArrayList<SolicitudCompraDto>();
		for(int i = 0; i < solCompra.size(); i++){
			System.out.println(solCompra.get(i).getEstado());
			SolicitudCompraDto scDTO = solCompra.get(i).aSolicitudCompraDTO();
			solCompraDTO.add(scDTO);
		}
		try {
			crearOrdenCompra(solCompraDTO);
		} catch (Exception e) {
			System.out.println("Error al generar Ordeners de Compra");
		}
		
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

		// Convertimos OrdenCompraDTO a Negocio
		List<OrdenCompraNegocio> ordenes = new ArrayList<OrdenCompraNegocio>();
		for(int i=0; i<listaOrdenes.size(); i++){
			OrdenCompraNegocio orden = new OrdenCompraNegocio();
			orden.aOrdenCompraNegocio(listaOrdenes.get(i));
			ordenes.add(orden);
		}
		
		// Buscamos el proveedor por el CUIT
		ProveedorNegocio proov = ProveedorDAO.getInstancia().buscarProveedorPorCUIT(proveedor.getCUIT());
		RemitoNegocio remito = new RemitoNegocio();
		
		remito.setProveedor(proov);
		remito.setComentarios(null);
		remito.setEstado("Recibido");
		Calendar c = new GregorianCalendar();
		remito.setFecha(c.getTime());		
			
		//remito.setOrdenesDeCompra(ordenes);
		remito.mergeRemito();
		
		return RemitoDAO.getinstancia().obtenerMaximoIDRemito();
	}

	public void actualizarStock(List<ItemDto> listaItems, String accion) {

		List<RodamientoNegocio> listaRodamiento = new ArrayList<RodamientoNegocio>();
		int cantidad = 0;

		// Transformar DTO a negocio
		for (int i = 0; i < listaItems.size(); i++) {
			RodamientoNegocio roda = new RodamientoNegocio();
			roda.aRodamientoNegocio(listaItems.get(i).getRodamiento());
			cantidad = listaItems.get(i).getCantidad();
			listaRodamiento.add(roda);
		}

		// Recorrer la lista de rodamientos
		for (int j = 0; j < listaRodamiento.size(); j++) {
			RodamientoNegocio rodamiento = new RodamientoNegocio();
			// la lista tiene que ser de negocio
			rodamiento = rodamiento.buscarRodamientoPorCodigoMarcaOrigen(listaRodamiento.get(j));

			if ((accion.equalsIgnoreCase("sumar")) || (accion.equalsIgnoreCase("suma"))) {
				rodamiento.setStock(rodamiento.buscarStock(listaRodamiento.get(j)) + cantidad);
				rodamiento.actualizarRodamiento();
			}
			if ((accion.equalsIgnoreCase("restar")) || (accion.equalsIgnoreCase("resta"))) {
				rodamiento.setStock(rodamiento.buscarStock(listaRodamiento.get(j)) - cantidad);
				rodamiento.actualizarRodamiento();

				// La validación que sigue es por si la cantidad pasa a ser un número negativo
				int cantStock = rodamiento.buscarStock(listaRodamiento.get(j));
				if (cantStock < 0) {
					rodamiento.setStock(0);
					rodamiento.actualizarRodamiento();
				}
			}

		}

	}

	// Daro: Obtiene la lista comparativa (RodamientoNegocio), la transforma y
	// devuFelve (RodamientoDto)
	public List<RodamientoDto> obtenerListaComparativa() throws RemoteException {
		List<RodamientoNegocio> rodasNegocio = this.casaCentralNegocio.getListaPrincipal();
		List<RodamientoDto> rodasDto = new ArrayList<RodamientoDto>();
		for (int i = 0; i < rodasNegocio.size(); i++) {
			rodasDto.add(rodasNegocio.get(i).aRodamientoDto());
		}
		return rodasDto;
	}

	public void actualizarListaComparativa(List<RodamientoDto> listado) throws RemoteException {
		// TODO REVISAR.
		Iterator<RodamientoNegocio> iterador = this.casaCentralNegocio.getListaPrincipal().iterator();
		while (iterador.hasNext()) {
			RodamientoNegocio roda = iterador.next();
			this.agregarNuevoRodamiento(roda);
		}
	}

	private void agregarNuevoRodamiento(RodamientoNegocio rodamiento) {
		
		Iterator<RodamientoNegocio> iterador = this.casaCentralNegocio.getListaPrincipal().iterator();
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
			this.casaCentralNegocio.getListaPrincipal().add(rodamiento);
		}
		if (!encontradoP && !actualizadoP) {
			iterador = this.casaCentralNegocio.getListaOpcional().iterator();
			while (iterador.hasNext() && !encontradoP) {
				RodamientoNegocio rodamientoComp = iterador.next();
				if (rodamientoComp.getCodigo().equals(rodamiento.getCodigo())) {
					this.casaCentralNegocio.getListaOpcional().add(rodamiento);
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
		
		ProveedorDAO.getInstancia().delete(proveedor.getCUIT());
	}

	public void modificarProveedor(ProveedorDto proveedor) throws RemoteException {
		
		ProveedorNegocio pro = new ProveedorNegocio();
		pro.setCUIT(proveedor.getCUIT());
		pro.setNombre(proveedor.getNombre());
		pro.updateProveedor();	
	}
	
	@Deprecated
	public List<RodamientoDto> obtenerListaComparativaOpcional() throws RemoteException {		
		return null;
	}

	@Deprecated
	public int crearOrdenCompra(List<SolicitudCompraDto> listaCotizaciones, String formaDePago) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
