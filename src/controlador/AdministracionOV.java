package controlador;

import interfaces.IAdministracionOV;

import java.rmi.RemoteException;
import java.util.*;

import negocio.*;
import utils.ItemDto;
import xml2.BultoXML;
import xml2.CotizacionXML;
import xml2.FacturaXML;
import dao.*;
import dto.*;

public class AdministracionOV implements IAdministracionOV{
	
	public static AdministracionOV administracion; 
	private static OVNegocio OficinaVentaNegocio;

	public static AdministracionOV getInstancia() throws RemoteException{
		if(administracion == null){	
//			//Creo la OficinaVentaNegocio
//			OficinaVentaNegocio = new OVNegocio();
//			//Inicializo la OV
			administracion = new AdministracionOV();
			
		}
		return administracion;
	}

	public AdministracionOV() throws RemoteException{
		//Obtengo la OV buscada o inicalizo una nueva
		this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(1));
		if(this.getOficinaVentaNegocio()== null){
			OficinaVentaNegocio = new OVNegocio();
			//Inicializo todos los array
			OficinaVentaNegocio.setClientes(new ArrayList <ClienteNegocio>());
			OficinaVentaNegocio.setFacturas(new ArrayList <FacturaNegocio>());
			OficinaVentaNegocio.setRemitos(new ArrayList <RemitoNegocio>());
			OficinaVentaNegocio.setCotizaciones(new ArrayList <CotizacionNegocio>());
			OficinaVentaNegocio.setSolicitudes(new ArrayList <SolicitudCompraNegocio>());
		
		}
			
	}
	
	public OVNegocio getOficinaVentaNegocio() {
		return OficinaVentaNegocio;
	}

	public void setOficinaVentaNegocio(OVNegocio oficinaVentaNegocio) {
		OficinaVentaNegocio = oficinaVentaNegocio;
	}
	
	//Daro: Este metodo crea la cotizacion con la lista de items pasada por parametro y la deja en estado: Pendiente
	public int crearCotizacion(List<ItemDto> listaItems, ClienteDto clienteDto) throws RemoteException {
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
		//Persisto la Cotizacion desde la OV
		CotizacionNegocio miCotNeg = new CotizacionNegocio();
		miCotNeg.aCotizacionNegocio(miCotDto);
		this.getOficinaVentaNegocio().getCotizaciones().add(miCotNeg);
		this.getOficinaVentaNegocio().mergeOV();
		//Obtengo la OV para ser persistida posteriormente
		this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(1));
		
		//Genero el XML de Cotizacion
		CotizacionXML.getInstancia().cotizacionTOxml(miCotNeg);
		//Devuelvo el maximo ID de la tabla Cotizaciones (el id de la ultima cotizacion creada)
		return CotizacionDAO.getinstancia().obtenerMaximoIDCotizacion();
	}
	
	
	
	//Daro: Este metodo aprueba la Cotizacion, dejandola en estado Aprobada
	public float aprobarYCotizarCotizacion(int idCotizacion)  throws RemoteException{		
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
		//Cambio el estado a Aprobada
		miCotNeg.setEstado("Aprobada");
		//Actualizo la CotizacionNegocio
		miCotNeg.mergearCotizacion();
		//Genero el XML de Cotizacion Aprobada
		CotizacionXML.getInstancia().cotizacionTOxml(miCotNeg);
		//Devuelvo el costo final de la Cotizacion
		return costoFinal;
	}
		
	//Daro: Este metodo rechaza la Cotizacion, dejandola en estado Rechazada
	public void rechazarCotizacion (int idCotizacion){
		//Busco la cotizacion y la guardo en la variable
		CotizacionNegocio miCotNeg = new CotizacionNegocio();
		miCotNeg = CotizacionDAO.getinstancia().buscarCotizacion(idCotizacion);
		//Cambio el estado a Rechazada
		miCotNeg.setEstado("Rechazada");
		//Actualizo la CotizacionNegocio
		miCotNeg.mergearCotizacion();
	return;
	}
	
	
	//Daro: Se realiza un bulto por cliente, junto a sus Remitos y Facturas
	public void entregaPedidos(int idRemito, int idFactura) throws RemoteException {
		//Creo el objeto que voy a persistir
		BultoNegocio miBultoNeg = new BultoNegocio();		
		//Busco el Remito en la Base de Datos
		RemitoNegocio miRemNeg = new RemitoNegocio();
		miRemNeg = RemitoDAO.getinstancia().buscarRemito(idRemito);
		//Busco la Factura en la Base de Datos
		FacturaNegocio miFacNeg = new FacturaNegocio();
		miFacNeg = FacturaDAO.getInstancia().buscarFactura(idFactura);
		
		//Creo los itemDto desde la FacturaDto (ItemFacturaDto)
		List<ItemBultoNegocio> miListaItBulNeg = new ArrayList<ItemBultoNegocio>();
		for(int i=0; i < miFacNeg.getItems().size(); i++){
			//Transformo cada ItemFacturaDto en ItemBultoNegocio
			ItemBultoNegocio miItBulNeg = new ItemBultoNegocio();
			//Asigno los rodamientos y cantidad
			miItBulNeg.setRodamiento(miFacNeg.getItems().get(i).getRodamiento());
			miItBulNeg.setCantidad(miFacNeg.getItems().get(i).getCantidad());
			//Agrego el item a la lista
			miListaItBulNeg.add(miItBulNeg);
		}
		
		//Asigno todos los objetos generados al BultoNegocio
		miBultoNeg.setFactura(miFacNeg);
		miBultoNeg.setRemito(miRemNeg);
		miBultoNeg.setItemBulto(miListaItBulNeg);
		
		//Persisto el objeto generado
		miBultoNeg.persistirBulto();
		
		//Una vez hecho el Bulto, se debe descontar del stock los Rodamientos que salieron
		ArrayList<ItemDto> misItemsDto = new ArrayList<ItemDto>();
		for (int i=0; i < miListaItBulNeg.size(); i++){
			//Formo la Lista de Items para luego actualizar stock
			misItemsDto.get(i).setRodamiento(miListaItBulNeg.get(i).getRodamiento().aRodamientoDto());
			misItemsDto.get(i).setCantidad(miListaItBulNeg.get(i).getCantidad());
		}
		
		//Descuento el Stock con la Lista de Items generada
		AdministracionCC.getInstancia().actualizarStock(misItemsDto, "restar");
		
		//Creo el BultoXML
		BultoXML.getInstancia().bultoTOxml(miBultoNeg);
	return;
	}
	
	
	//Carlos: Genera factura a partir de un listado de cotizaciones, para un cliente puntual	
	public int generarFactura(List<CotizacionDto> cotis, ClienteDto cliente){
		
		ClienteNegocio cli = new ClienteNegocio();
		cli.aClienteNegocio(cliente);	//Convertimos al cliente dto en negocio
		List<CotizacionNegocio> cotiNegocio = new ArrayList<CotizacionNegocio>(); 	//Creamos lista de cotizacionNegocio para contener las cot transformadas recibidas
		for(int i = 0; i < cotis.size(); i++){
			CotizacionNegocio co = new CotizacionNegocio();
			co.aCotizacionNegocio(cotis.get(i));	//convertimos a negocio cada una de las cotizacionesDTO
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

	return FacturaDAO.getInstancia().obtenerMaximoIDFactura();
	}
	
	@SuppressWarnings("unchecked")
	public List <CotizacionDto> obtenerCotizaciones(){
		List <CotizacionNegocio> cotizaciones = CotizacionDAO.getinstancia().obtenerCotizaciones();
		List <CotizacionDto> listCotizacionDto = new ArrayList <CotizacionDto> ();
		for(int i=0;i<cotizaciones.size();i++){
			CotizacionDto cotizacionDto = cotizaciones.get(i).aCotizacionDto();
			listCotizacionDto.add(cotizacionDto);
		}
		return listCotizacionDto;
	}
	

	@SuppressWarnings("unused")
	private int generarFactura2(List<CotizacionNegocio> cotis, ClienteNegocio cliente){
		
		FacturaNegocio factura = new FacturaNegocio();	//Creamos la nueva factura y seteamos datos basicos y listado de cotizaciones
		factura.setCliente(cliente);
		factura.setEstado("Generada");
		Calendar c = new GregorianCalendar();
		factura.setFecha(c.getTime());
		factura.setCotizacion(cotis);
		
		List<ItemFacturaNegocio> itemsFactura = new ArrayList<ItemFacturaNegocio>();	// Crear ItemsFactura
		// Obtenemos una lista de objetos conformado por un rodamiento, la cantidad de este rodamiento y el subtotal de esos rodamientos
		List<Object[]> misObjects = CotizacionDAO.getinstancia().rodaPorItemsCotizacion_OV_Estado_x_Cliente(cotis, this.getOficinaVentaNegocio(), "Aprobada", cliente);
		
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

	return FacturaDAO.getInstancia().obtenerMaximoIDFactura();
	}
	
	// Actualiza ESTADO de cotización
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
	
	
	
	public List<CotizacionNegocio> obtenerCotizacionesDeCiente(ClienteNegocio clie){
		CotizacionDAO cotizacionDao = CotizacionDAO.getinstancia();
		return cotizacionDao.obtenerCotizacionesDeCiente(clie);
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

	@Override
	public List<CotizacionDto> obtenerCotizacionesAprobadas() throws RemoteException {
		
		List<CotizacionNegocio> cotizaciones = CotizacionDAO.getinstancia().cotizacionesXovYestado(this.getOficinaVentaNegocio(), "Aceptada");
		List<CotizacionDto> cotizacionesDto = new ArrayList<CotizacionDto>();
		for(int i=0; i<cotizaciones.size(); i++){
			cotizacionesDto.add(cotizaciones.get(i).aCotizacionDto());
		}
		return cotizacionesDto;
	}

	@Override
	public void crearSolicitudCompra(List<CotizacionDto> cotizacionesAprobadas) throws RemoteException {
		
		//Convertimos mas cotizaciones DTO a Negocio
		List<CotizacionNegocio> cotisNego = new ArrayList<CotizacionNegocio>();
		for (int i = 0; i < cotizacionesAprobadas.size(); i++) {
			CotizacionNegocio cot = new CotizacionNegocio();
			cot.aCotizacionNegocio(cotizacionesAprobadas.get(i));
			cotisNego.add(cot);
		}
		
		//Creamos la solicitud de Compra
		SolicitudCompraNegocio solCompraNeg = new SolicitudCompraNegocio();
		solCompraNeg.setEstado("Nueva");
		solCompraNeg.setListaCotizaciones(cotisNego);
		
//		// Generar Factura - Debe separarse las cotizaciones por Cliente
//		// Obtenemos los clientes de la lista de cotizaciones pasada por parametro
//		List<ClienteNegocio> clientes = new ArrayList<ClienteNegocio>();
//		clientes = CotizacionDAO.getinstancia().clientesDeListadoCotizacionXovYestado(cotisNego, "Aprobada", this.getOficinaVentaNegocio());
//		
//		for (int i = 0; i < clientes.size(); i++) {
//			List<CotizacionNegocio> cotiFac = new ArrayList<CotizacionNegocio>();
//			cotiFac = CotizacionDAO.getinstancia().cotizacionXovYestadoYcliente(cotisNego, "Aprobada", this.getOficinaVentaNegocio(), 
//					clientes.get(i));
//			this.generarFactura2(cotiFac, clientes.get(i));
//		}
//		
		for (int i = 0; i < cotisNego.size(); i++) {
			cotisNego.get(i).setEstado("Solicitada");
		}
		//solCompraNeg.mergeSolicitudCompra();
		this.getOficinaVentaNegocio().getSolicitudes().add(solCompraNeg);
		this.getOficinaVentaNegocio().mergeOV();
		
		//Magia para no duplicar SolicitudCompra
		this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(1));
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
	
	@Override
	public List<SolicitudCompraDto> obtenerSolicitudesPendientes() throws RemoteException {
		return null;
	}

	@Override
	public void crearOrdenCompra(List<SolicitudCompraDto> solicitudesPendientes) throws RemoteException {		
	}
	
}
