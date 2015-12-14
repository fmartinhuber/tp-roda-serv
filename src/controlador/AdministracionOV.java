package controlador;

import interfaces.IAdministracionOV;

import java.rmi.RemoteException;
import java.util.*;

import negocio.*;
import utils.BatchUtils;
import utils.ItemDto;
import xml2.CotizacionXML;
import xml2.FacturaXML;
import dao.*;
import dto.*;

public class AdministracionOV implements IAdministracionOV{
	
	//Numero OV seteado en 0 al momento de inicializar
	private static int numeroOv = 0;
	public static AdministracionOV administracion; 
	private static OVNegocio OficinaVentaNegocio;
	private ClienteNegocio usuarioLogueado;
	public static AdministracionOV getInstancia() throws RemoteException{
		if(administracion == null){	
			administracion = new AdministracionOV();
		}
		return administracion;
	}

	public AdministracionOV() {
		if(OficinaVentaNegocio == null){
			OficinaVentaNegocio = new OVNegocio();
		}
	}
	
	public OVNegocio getOficinaVentaNegocio() {
		return OficinaVentaNegocio;
	}

	public void setOficinaVentaNegocio(OVNegocio oficinaVentaNegocio) {
		OficinaVentaNegocio = oficinaVentaNegocio;
	}
	
	
	
	//Daro: Este metodo crea la cotizacion con la lista de items pasada por parametro y la deja en estado: Pendiente
	public CotizacionDto crearCotizacion(List<ItemDto> listaItems, ClienteDto clienteDto) throws RemoteException {
		//Obtengo la lista comparativa
		List<RodamientoDto> listaCompa = new ArrayList<RodamientoDto>();
		listaCompa = AdministracionCC.getInstancia().obtenerListaComparativa();
		
		//Declaro la cotizacion que voy a devolver
		CotizacionDto miCotDto = new CotizacionDto();

		//Seteo los valores correspondientes a la Cotizacion
		ClienteNegocio miCliNeg = ClienteDAO.getInstancia().buscarClientePorCUIT(clienteDto.getCUIT());
		miCotDto.setCliente(miCliNeg.aClienteDto());	//Seteo Cliente buscado desde la BD
		miCotDto.setEstado("Pendiente"); 				//Todas se crean pendientes hasta ser aprobadas
		Date actual = new Date();
		miCotDto.setFechaCreacion(actual); 				//Se crea con la fecha actual
		Calendar c = Calendar.getInstance();
		c.setTime(actual);
		c.add(Calendar.DATE, 30); 						//Se agregan 30 dias a la fecha actual para la vigencia
		Date vigencia = new Date();
		vigencia = c.getTime();
		miCotDto.setFechaVigencia(vigencia);
		miCotDto.setItems(new ArrayList<ItemCotizacionDto>());	//Creo Items Cotizacion vacios
		
		//Creo la lista de items que voy a utilizar para ir cargandolos
		List<ItemCotizacionDto> listaItemCotDto = new ArrayList<ItemCotizacionDto>();

		//Para cada elemento de la lista comparativa
		for (int i=0; i<listaCompa.size(); i++){
			//Comparo con cada elemento de la lista Item
			for (int j=0; j<listaItems.size(); j++){
				//Obtengo los codigos
				String codComp = listaCompa.get(i).getCodigo();
				String codItem = listaItems.get(j).getRodamiento().getCodigo();
				//Origen
				String orgComp = listaCompa.get(i).getOrigen();
				String orgItem = listaItems.get(j).getRodamiento().getOrigen();
				//Marca
				String marComp = listaCompa.get(i).getMarca();
				String marItem = listaItems.get(j).getRodamiento().getMarca();
				//Si coinciden las 3 cosas, es el que busco
				if (codComp.equals(codItem) && orgComp.equals(orgItem) && marComp.equals(marItem)){
					//Creo item
					ItemCotizacionDto itemCotDto = new ItemCotizacionDto();
					
					//Busco el rodamiento en la Base de Datos para asignarlo con todos sus campos
					RodamientoNegocio miRodaNeg = new RodamientoNegocio();
					miRodaNeg.aRodamientoNegocio(listaCompa.get(j));
					miRodaNeg = RodamientoDAO.getInstancia().buscarRodamientoPorCodigoMarcaOrigen(miRodaNeg);
					itemCotDto.setRodamiento(miRodaNeg.aRodamientoDto());
					
					//Seteo sus valores
					itemCotDto.setCant(listaItems.get(j).getCantidad());
					itemCotDto.setPrecio(listaItems.get(j).getCantidad() * listaCompa.get(i).getMonto());
					//Agrego el item a la lista de items
					listaItemCotDto.add(itemCotDto);
				}
			}
		}
		
		//Agrego a la cotizacion toda la lista de items obtenida
		miCotDto.setItems(listaItemCotDto);
		
		//Obtengo la lista de OV
		//this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(numeroOv));
		//Persisto la Cotizacion desde la OV
		CotizacionNegocio miCotNeg = new CotizacionNegocio();
		miCotNeg.aCotizacionNegocio(miCotDto);
		this.getOficinaVentaNegocio().getCotizaciones().add(miCotNeg);
		this.getOficinaVentaNegocio().mergeOV();
		//Obtengo la OV para ser utilizada posteriormente
		this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(numeroOv));

		
		//Genero el XML de Cotizacion
		CotizacionXML.getInstancia().cotizacionTOxml(miCotNeg);
		//Devuelvo el maximo ID de la tabla Cotizaciones (el id de la ultima cotizacion creada)
		miCotNeg.setIdCotizacion(CotizacionDAO.getinstancia().obtenerMaximoIDCotizacion());
		
		return miCotNeg.aCotizacionDto();
	}
	
	
	
	//Daro: Este metodo cotiza la Cotizacion, devolviendo su valor y cambiando su estado a "Cotizada"
	public float cotizarCotizacion (int idCotizacion){
		//Creo la variable a devolver, calculando el costo de la Cotizacion Aprobada
		float costoFinal;
		costoFinal = 0;
		//Busco la cotizacion y la guardo en la variable
		CotizacionNegocio miCotNeg = new CotizacionNegocio();
		miCotNeg = CotizacionDAO.getinstancia().buscarCotizacion(idCotizacion);
		//Recorro la lista y voy sumando los costos
		for (int i=0; i<miCotNeg.getItems().size(); i++){
			costoFinal = miCotNeg.getItems().get(i).getPrecio() + costoFinal;
		}
		//Persisto la Cotizacion desde la OV. En este caso cambio su estado a "Cotizado", despues persisto
		//Busco la cotizacion en la OV
		for (int i=0; i < this.getOficinaVentaNegocio().getCotizaciones().size(); i++){
			//Si coincide el ID, actualizo
			if (idCotizacion == this.getOficinaVentaNegocio().getCotizaciones().get(i).getIdCotizacion()){
				this.getOficinaVentaNegocio().getCotizaciones().get(i).setEstado("Cotizada");
			}
		}
		this.getOficinaVentaNegocio().mergeOV();
		//Obtengo la OV para ser utilizada posteriormente
		this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(numeroOv));
		CotizacionXML.getInstancia().cotizacionTOxml(miCotNeg);
		
		return costoFinal;
	}
	
	
	
	//Daro: Este metodo aprueba la Cotizacion, dejandola en estado Aprobada
	public void aprobarCotizacion(int idCotizacion)  throws RemoteException{		
		//Busco la cotizacion y la guardo en la variable
		CotizacionNegocio miCotNeg = new CotizacionNegocio();
		miCotNeg = CotizacionDAO.getinstancia().buscarCotizacion(idCotizacion);
		
		//Persisto la Cotizacion desde la OV. En este caso cambio su estado a "Aprobada", despues persisto
		//Busco la cotizacion en la OV
		for (int i=0; i < this.getOficinaVentaNegocio().getCotizaciones().size(); i++){
			//Si coincide el ID, actualizo
			if (idCotizacion == this.getOficinaVentaNegocio().getCotizaciones().get(i).getIdCotizacion()){
				this.getOficinaVentaNegocio().getCotizaciones().get(i).setEstado("Aprobada");
			}
		}
		this.getOficinaVentaNegocio().mergeOV();
		//Obtengo la OV para ser utilizada posteriormente
		this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(numeroOv));
		
		//Genero el XML de Cotizacion Aprobada
		miCotNeg.setEstado("Aprobada");
		CotizacionXML.getInstancia().cotizacionTOxml(miCotNeg);
		
	}
		
	
	//Daro: Se envia el Remito junto a los Rodamientos (no se persisten) de la CC a OV
	public void entregaPedidos(int idRemito) throws RemoteException {
		//TODO RAMA: Rama, este metodo lo habia hecho yo (Daro), pero ahora por lo que hablamos cambia su funcionamiento
		/*Aca esta super complejo porque pasaba del remito a itemDto, pero esto vos no lo tendrias que hacer, te dejo todo comentado
		 * para que tengas como referencia, pero no es asi. Este metodo lo unico que hace es:
		 * - Recibir un Remito
		 * - Guardarlo en el array de Remitos de la OV
		 * - Persistir ese array
		 * - Volver a levantar OV (como Cotizacion y todos los demas)*/
		
//		//Buscar Remito
//		RemitoNegocio miRemNeg = new RemitoNegocio();
//		List<ItemDto> miListaItemDto = new ArrayList<ItemDto>();
//		miRemNeg = RemitoDAO.getinstancia().buscarRemito(idRemito); //Obtenemos el remito Buscado
//		
//		//Descontar Stock
//		CotizacionNegocio miCotNeg = new CotizacionNegocio();
//		//Por cada orden de compra
//		for (int i=0; i<miRemNeg.getOrdenesDeCompra().size(); i++){
//			//Por cada Solicitud de compra de la orden de compra
//			for (int j=0; j<miRemNeg.getOrdenesDeCompra().get(i).getSolicitudesCompra().size(); j++){
//				//Por cada Cotizacion de la lista de cotizaciones de la solicitud de compra de la orden de compra (complicadito no?)
//				for (int k=0; k<miRemNeg.getOrdenesDeCompra().get(i).getSolicitudesCompra().get(j).getListaCotizaciones().size(); k++){
//					//Obtengo la cotizacion
//					miCotNeg = miRemNeg.getOrdenesDeCompra().get(i).getSolicitudesCompra().get(j).getListaCotizaciones().get(k);
//					//Para cada item de la listaItems de la Cotizacion
//					for (int m=0; m<miCotNeg.getItems().size(); m++){
//						//Creo un nuevo itemDto para asignarlo a su lista
//						ItemDto miItemDto = new ItemDto();
//						//Asigno sus valores
//						miItemDto.setRodamiento(miCotNeg.getItems().get(m).getRodamiento().aRodamientoDto());
//						miItemDto.setCantidad(miCotNeg.getItems().get(m).getCantidad());
//						//Agrego el nuevo itemDto a la lista de ItemDto
//						miListaItemDto.add(miItemDto);
//					}
//				}
//			}
//		}
//		//Ahora si, finalmente descuento el Stock
//		AdministracionCC.getInstancia().actualizarStock(miListaItemDto, "resta");
//		
//		//Agregar Remito a la lista de Remitos de OVNegocio
//		this.getOficinaVentaNegocio().getRemitos().add(miRemNeg);
//		this.getOficinaVentaNegocio().mergeOV();
//		//Obtengo la OV para ser utilizada posteriormente
//		this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(numeroOv));
	
	return;
	}
	
	
	//Carlos: Genera factura a partir de un listado de cotizaciones, para un cliente puntual	
	public FacturaDto generarFactura(List<CotizacionDto> cotis, ClienteDto cliente){
		
		ClienteNegocio cli = new ClienteNegocio();
		//cli.aClienteNegocio(cliente);	//Convertimos al cliente dto en negocio
		//Buscamos al cliente por el cuit que vino en el dto
		cli = ClienteDAO.getInstancia().buscarClientePorCUIT(cliente.getCUIT());
		List<CotizacionNegocio> cotiNegocio = new ArrayList<CotizacionNegocio>(); 	//Creamos lista de cotizacionNegocio para contener las cot transformadas recibidas
		for(int i = 0; i < cotis.size(); i++){
			CotizacionNegocio co = new CotizacionNegocio();
			//co.aCotizacionNegocio(cotis.get(i));	//convertimos a negocio cada una de las cotizacionesDTO
			//Buscamos la cotizacion cuyo id coincida con el pasado en la cotización dto
			co = CotizacionDAO.getinstancia().buscarCotizacion(cotis.get(i).getNumeroCotizacion());
			cotiNegocio.add(co);
		}
		FacturaNegocio factura = new FacturaNegocio();	//Creamos la nueva factura y seteamos datos basicos y listado de cotizaciones
		factura.setCliente(cli);
		factura.setEstado("Generada");
		Calendar c = new GregorianCalendar();
		factura.setFecha(c.getTime());
		factura.setCotizacion(cotiNegocio);
		
		List<ItemFacturaNegocio> itemsFactura = new ArrayList<ItemFacturaNegocio>();	// Crear ItemsFactura
		// Obtenemos una lista de objetos conformado por un rodamiento, la cantidad de este rodamiento y el subtotal de esos rodamientos
		List<Object[]> misObjects = CotizacionDAO.getinstancia().rodaPorItemsCotizacion_OV_Estado_x_Cliente(cotiNegocio, this.getOficinaVentaNegocio(), "Aprobada", cli);
		
		Double totalFactura = 0.0;
		
		for(int i=0; i<misObjects.size(); i++){
			ItemFacturaNegocio itFactura = new ItemFacturaNegocio();
			RodamientoNegocio rodamiento = RodamientoDAO.getInstancia().buscarRodamiento((Integer)misObjects.get(i)[0]);
			itFactura.setRodamiento(rodamiento);
			itFactura.setCantidad(Integer.valueOf(misObjects.get(i)[1].toString()));
			Double sal = (Double)misObjects.get(i)[2];
			itFactura.setPrecio(sal.floatValue());
			itemsFactura.add(itFactura);
			//Acumulo el total de la factura
			totalFactura = totalFactura + sal;
		}
		
		factura.setItems(itemsFactura);
		factura.setTotal(totalFactura.floatValue());
		this.getOficinaVentaNegocio().getFacturas().add(factura);
		this.getOficinaVentaNegocio().mergeOV();
		//factura.setDescuento(strategy);
		
		//Magia para no duplicar facturas
		this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(1));
		
		//Genero el XML de Factura
		FacturaXML.getInstancia().cotizacionTOxml(factura);
		factura.setIdFactura(FacturaDAO.getInstancia().obtenerMaximoIDFactura());
		return factura.aFacturaDto();
	}
		
	public List <CotizacionDto> obtenerCotizaciones(){
		List <CotizacionNegocio> cotizaciones = CotizacionDAO.getinstancia().obtenerCotizaciones();
		List <CotizacionDto> listCotizacionDto = new ArrayList <CotizacionDto> ();
		for(int i=0;i<cotizaciones.size();i++){
			CotizacionDto cotizacionDto = cotizaciones.get(i).aCotizacionDto();
			listCotizacionDto.add(cotizacionDto);
		}
		return listCotizacionDto;
	}
	
	
	//Actualiza ESTADO de cotización
	@SuppressWarnings("unused")
	private boolean ActualizarEstadoCotizacion (CotizacionNegocio cotizacion, String estadoNuevo){
		//CotizacionNegocio coti = CotizacionDAO.getinstancia().buscarCotizacion(idCotizacion);
		Boolean salida = true;
		try{
			cotizacion.setEstado(estadoNuevo);
			CotizacionDAO.getinstancia().update(cotizacion);
		}
		catch(Exception ex){
			System.out.println("Error al actualizar estado de cotización");
			salida = false;
		}
		return salida;
	}
	
	
	public CotizacionDto obtenerCotizaciones(int id){
		CotizacionDAO cotizacionDao = CotizacionDAO.getinstancia();
		return cotizacionDao.buscarCotizacion(id).aCotizacionDto();
	}
	
	
	public void crearCliente(ClienteDto cliente) throws RemoteException {
		ClienteNegocio cli = new ClienteNegocio();
		cli.setCUIT(cliente.getCUIT());
		cli.setMail(cliente.getMail());
		cli.setRazonSocial(cliente.getRazonSocial());
		cli.persistirCliente();	
	}
	
	
	public void eliminarCliente(ClienteDto cliente) throws RemoteException {
		ClienteNegocio cli = ClienteDAO.getInstancia().buscarClientePorCUIT(cliente.getCUIT());		
		cli.deleteCliente();		
	}
	

	public void modificarCliente(ClienteDto cliente) throws RemoteException {
		ClienteNegocio cli = ClienteDAO.getInstancia().buscarClientePorCUIT(cliente.getCUIT());
		cli.setMail(cliente.getMail());
		cli.setRazonSocial(cliente.getMail());
		cli.updateCliente();
	}
	

	public List<CotizacionDto> obtenerCotizacionesAprobadas() throws RemoteException {
		List<CotizacionNegocio> cotizaciones = CotizacionDAO.getinstancia().cotizacionesXovYestado(this.getOficinaVentaNegocio(), "Aprobada");
		List<CotizacionDto> cotizacionesDto = new ArrayList<CotizacionDto>();
		for(int i=0; i<cotizaciones.size(); i++){
			cotizacionesDto.add(cotizaciones.get(i).aCotizacionDto());
		}
		return cotizacionesDto;
	}

	
	public SolicitudCompraDto crearSolicitudCompra(List<CotizacionDto> cotizacionesAprobadas) throws RemoteException {
		//Convertimos cotizaciones DTO a Negocio
		List<CotizacionNegocio> cotisNego = new ArrayList<CotizacionNegocio>();
		for (int i=0; i<cotizacionesAprobadas.size(); i++) {
			CotizacionNegocio cot = new CotizacionNegocio();
			cot.aCotizacionNegocio(cotizacionesAprobadas.get(i));
			cot.setEstado("Solicitada");
			cotisNego.add(cot);
		}
		
		//Creamos la solicitud de Compra
		SolicitudCompraNegocio solCompraNeg = new SolicitudCompraNegocio();
		solCompraNeg.setEstado("Nueva");
		solCompraNeg.setListaCotizaciones(cotisNego);

		//this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(numeroOv));
		//Persisto la Cotizacion desde la OV
		this.getOficinaVentaNegocio().getSolicitudes().add(solCompraNeg);
		this.getOficinaVentaNegocio().mergeOV();
		//Obtengo la OV para ser utilizada posteriormente
		this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(numeroOv));
		
		
		return solCompraNeg.aSolicitudCompraDTO();
	} 

	
	public void pch_LevantaCotizaciones() {
		List<CotizacionNegocio> cotisAprobadas;
		cotisAprobadas = CotizacionDAO.getinstancia().cotizacionesXovYestado(this.getOficinaVentaNegocio(), "Aprobada");
		List<CotizacionDto> cotisDTO = new ArrayList<CotizacionDto>();
		for (int i = 0; i < cotisAprobadas.size(); i++) {
			CotizacionDto cotiDTO = new CotizacionDto();
			cotiDTO = cotisAprobadas.get(i).aCotizacionDto();
			cotisDTO.add(cotiDTO);
		}
		try {
			crearSolicitudCompra(cotisDTO);
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Error al generar Solicitud de Compra");
			
		}
		
	}
	
	
	public List<SolicitudCompraDto> obtenerSolicitudesPendientes() throws RemoteException {
		return null;
	}

	
	public ClienteDto obtenerUsuario(String usuario, String contrasena) throws RemoteException {
		ClienteNegocio clieNeg = ClienteDAO.getInstancia().obtenerUsuario(usuario, contrasena);
		if(clieNeg != null){
			levantarOv(Integer.valueOf(clieNeg.getOv()));
			this.setUsuarioLogueado(clieNeg);
			numeroOv = Integer.valueOf(clieNeg.getOv());
			this.comenzarBatch();
			return clieNeg.aClienteDto();
		}
		
		return null;
	}
	
	
	@SuppressWarnings("unused")
	private void comenzarBatch(){
		new BatchUtils().batch();
	}

	
	private void levantarOv(int ov) {
		this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(ov));
	}
	

	
	public ClienteDto obtenerUsuarioLogueado() throws RemoteException {
		return this.getUsuarioLogueado().aClienteDto();
	}

	
	public static int getNumeroOv() {
		return numeroOv;
	}
	

	public static void setNumeroOv(int numeroOv) {
		AdministracionOV.numeroOv = numeroOv;
	}

	public ClienteNegocio getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(ClienteNegocio usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}
	
	
}
