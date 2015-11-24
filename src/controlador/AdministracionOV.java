package controlador;

import interfaces.IAdministracionOV;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import negocio.*;
import utils.ItemDto;
import dao.*;
import dto.*;


//Daro 25/10: Se genera una OVnegocio unica, cuando realmente deberia ser una lista. Trabajarlo con listas es muy complejo
public class AdministracionOV extends UnicastRemoteObject implements IAdministracionOV{
	
	private static final long serialVersionUID = 1L;
	
	public static AdministracionOV administracion; 
	private static OVNegocio OficinaVentaNegocio;

	public static AdministracionOV getInstancia() throws RemoteException{
		if(administracion == null){
			administracion = new AdministracionOV();
			//OficinaVentaNegocio = new OVNegocio();
		}
		return administracion;
	}

	public AdministracionOV() throws RemoteException{
		this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(1));
//		this.getOficinaVentaNegocio().setClientes(new ArrayList <ClienteNegocio>());
//		this.getOficinaVentaNegocio().setFacturas(new ArrayList <FacturaNegocio>());
//		this.getOficinaVentaNegocio().setRemitos(new ArrayList <RemitoNegocio>());
//		//this.getOficinaVentaNegocio().setProveedores(new ArrayList <ProveedorNegocio>());
//		this.getOficinaVentaNegocio().setCotizaciones(new ArrayList <CotizacionNegocio>());
//		this.getOficinaVentaNegocio().setSolicitudes(new ArrayList <SolicitudCompraNegocio>());
	}
	
	public OVNegocio getOficinaVentaNegocio() {
		return OficinaVentaNegocio;
	}

	public void setOficinaVentaNegocio(OVNegocio oficinaVentaNegocio) {
		OficinaVentaNegocio = oficinaVentaNegocio;
	}
	
	//Daro: Este metodo crea la cotizacion con la lista de items pasada por parametro y la deja en estado: Pendiente
	public void crearCotizacion(List<ItemDto> listaItems, ClienteDto cliente) throws RemoteException {
		//Obtengo la lista comparativa
		AdministracionCC admCC = new AdministracionCC();
		List<RodamientoDto> listaCompa = new ArrayList<RodamientoDto>();
		listaCompa = admCC.obtenerListaComparativa();
		
		//Declaro la cotizacion que voy a devolver
		CotizacionDto miCotDto = new CotizacionDto();

		//Seteo los valores correspondientes a la Cotizacion
		miCotDto.setCliente(cliente);		//Seteo Cliente
		miCotDto.setEstado("Pendiente"); 	//Todas se crean pendientes hasta ser aprobadas
		Date actual = new Date();
		miCotDto.setFechaCreacion(actual); 	//Se crea con la fecha actual
		Calendar c = Calendar.getInstance();
		c.setTime(actual);
		c.add(Calendar.DATE, 30); 			//Se agregan 30 dias a la fecha actual para la vigencia
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
					//Seteo sus valores
					itemCotDto.setCant(listaItems.get(j).getCantidad());
					itemCotDto.setRodamiento(listaCompa.get(j));
					itemCotDto.setPrecio(listaItems.get(j).getCantidad() * listaCompa.get(i).getMonto());
					//Agrego el item a la lista de items
					listaItemCotDto.add(itemCotDto);
				}
			}
		}

		//Agrego a la cotizacion toda la lista de items obtenida
		miCotDto.setItems(listaItemCotDto);
		
		//Persisto la Cotizacion
		CotizacionNegocio miCotNeg = new CotizacionNegocio();
		miCotNeg.aCotizacionNegocio(miCotDto);
		miCotNeg.persistirCotizacion();		

		//Devuelvo la cotizacion
		return;
	}
	
	
	
	//Daro: Este metodo aprueba la Cotizacion, dejandola en estado Aprobada
	public float aprobarYCotizarCotizacion (CotizacionDto miCotDto)  throws RemoteException{		
		//Creo la variable a devolver, calculando el costo de la Cotizacion Aprobada
		float costoFinal;
		costoFinal = 0;
		//Recorro la lista y voy sumando los costos
		for (int i=0; i<miCotDto.getItems().size(); i++){
			costoFinal = miCotDto.getItems().get(i).getPrecio() + costoFinal;
		}
		
		//Cambio el estado a Aprobada
		miCotDto.setEstado("Aprobada");
		
		//Transformo la CotizacionDto a CotizacionNegocio
		CotizacionNegocio cotizNegocio = new CotizacionNegocio();
		cotizNegocio.aCotizacionNegocio(miCotDto);
		//Actualizo la CotizacionNegocio
		cotizNegocio.actualizarCotizacion();
		
		//Devuelvo el costo final de la Cotizacion
		return costoFinal;
	}
	
	
	//Daro: Este metodo rechaza la Cotizacion, dejandola en estado Rechazada
	public void rechazarCotizacion (CotizacionDto miCotDto){
		//Cambio el estado a Rechazada
		miCotDto.setEstado("Rechazada");
		//Hago merge de la Cotizacion para que cambie su estado a "Rechazada" en la BD
		CotizacionNegocio cotizNegocio = new CotizacionNegocio();
		cotizNegocio.aCotizacionNegocio(miCotDto);
		//Actualizo la CotizacionNegocio
		cotizNegocio.mergearCotizacion();

	}
	
	
	//TODO Daro
	public BultoDto entregaPedidos(RemitoDto remito) throws RemoteException {
		
	return null;
	}
	
	
	// Genera factura a partir de un listado de cotizaciones, para un cliente puntual	
	public void generarFactura(List<CotizacionDto> cotis, ClienteDto cliente){
		
		ClienteNegocio cli = ClienteDAO.getInstancia().buscarCliente(1);// new ClienteNegocio();
		//cli.aClienteNegocio(cliente);
		//Creo lista de cotizaciones
		List<CotizacionNegocio> cotiNegocio = new ArrayList<CotizacionNegocio>();
		for(int i = 0; i < cotis.size(); i++){
			CotizacionNegocio co = new CotizacionNegocio();
			co.aCotizacionNegocio(cotis.get(i));
			cotiNegocio.add(co);
		}
		FacturaNegocio factura = new FacturaNegocio();
		factura.setCliente(cli);
		factura.setEstado("generada");
		Calendar c = new GregorianCalendar();
		factura.setFecha(c.getTime());
		factura.setCotizacion(cotiNegocio);
		
		// Crear ItemsFactura
		List<ItemFacturaNegocio> itemsFactura = new ArrayList<ItemFacturaNegocio>();
		for(int i=0; i<cotis.size(); i++){
			//TODO CARLOS: Revisa esto
			//idsCoti.add(cotis.get(i).getIdCotizacion());
			CotizacionNegocio coti = new CotizacionNegocio(); //buscarCotizacion(idsCoti.get(i).intValue());
			coti.aCotizacionNegocio(cotis.get(i));
			//TODO CARLOS: Revisa esto, rompe, mal merge parece
			//cotizacionesFactura.add(coti);
			ActualizarEstadoCotizacion(coti, "SOLICITADA");
		}	
		//TODO CARLOS: Revisa esto, rompe, mal merge parece
		//List<Object[]> misObjects = CotizacionDAO.getinstancia().itemsCotizacionAgrupadosPorRodamiento(idsCoti);
		List<Object[]> misObjects = CotizacionDAO.getinstancia().rodaPorItemsCotizacion_OV_Estado_x_Cliente(cotiNegocio, this.getOficinaVentaNegocio(), "aprobada", cli);
		
		for(int i=0; i<misObjects.size(); i++){
			ItemFacturaNegocio itFactura = new ItemFacturaNegocio();
			RodamientoNegocio rodamiento = RodamientoDAO.getInstancia().buscarRodamiento((Integer)misObjects.get(i)[0]);
			itFactura.setRodamiento(rodamiento);
			itFactura.setCantidad((Integer)misObjects.get(i)[1]);
			Double sal = (Double)misObjects.get(i)[2];
			itFactura.setSubtotal(sal.floatValue());
			itemsFactura.add(itFactura);
		}
//		for(int i=0; i<cotiNegocio.size(); i++){
//			cotiNegocio.get(i).setEstado("generada");
//			cotiNegocio.get(i).actualizarCotizacion();
//		}
		factura.setItems(itemsFactura);
		factura.persistirFactura();
	}
	
	
	// Metodo de prueba Charly, borrar antes de la entrega
	public void pch_LevantaCotizaciones(){
		List<CotizacionNegocio> cotizaciones = CotizacionDAO.getinstancia().cotizacionesTodas(this.getOficinaVentaNegocio());
		List<CotizacionDto> cotizacionesDTO = new ArrayList<CotizacionDto>();
		for(int i = 0; i < cotizaciones.size(); i++){
			System.out.println(cotizaciones.get(i).getIdCotizacion());
			CotizacionDto cotiDTO = cotizaciones.get(i).aCotizacionDto();
			cotizacionesDTO.add(cotiDTO);
		}
		//ClienteNegocio cli = ClienteDAO.getInstancia().buscarCliente(1);
		
		generarFactura(cotizacionesDTO, null);
//		for(int i = 0; i < cotizacionesDTO.size(); i++){
//			System.out.println(cotizacionesDTO.get(i).getIdCotizacion());
//		}
//		
//		//probamos nuevo metodo de busqueda en tabla
//		List<Object[]> misObjects = CotizacionDAO.getinstancia().rodaPorItemsCotizacion_OV_Estado(cotizaciones, this.getOficinaVentaNegocio(), "aprobada");
//		for(int i=0; i<misObjects.size(); i++){
//			int idRoda = (int)misObjects.get(i)[0];
//			RodamientoNegocio roda = (RodamientoDAO.getInstancia().buscarRodamiento(idRoda));
//			System.out.println(roda.getCodigo());
//			Double sal2 = (Double) misObjects.get(i)[2];
//			System.out.println(sal2);
//			//int sal = (int)misObjects.get(i)[1];
//			System.out.println(misObjects.get(i)[1]);
//		}
		
	}
	
	
	// Actualiza ESTADO de cotización
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
	
	
	
	// Prueba
//	@Deprecated
//	public void Prueba(List<Integer> coti){
//		List<Object[]> misObjects = CotizacionDAO.getinstancia().itemsCotizacionAgrupadosPorRodamiento(coti);
//		for(int i=0; i<misObjects.size(); i++){
//			System.out.println(misObjects.get(i)[0]);
//			System.out.println(misObjects.get(i)[1]);
//			System.out.println(misObjects.get(i)[2]);
//		}
//	}

	@Override
	public void crearCliente(ClienteDto cliente) throws RemoteException {
		
		/*
		ClienteNegocio clientenNegocio = new ClienteNegocio(cliente.getRazonSocial(), cliente.getMail(), cliente.getCUIT());
		AdministracionOV.getInstancia().getOficinaVentaNegocio().getClientes().add(clientenNegocio);
		return;
		*/		
		
		//ClienteDAO.getInstancia().persist(cliente);
		
		ClienteNegocio cli = new ClienteNegocio();
		cli.setCUIT(cliente.getCUIT());
		cli.setMail(cliente.getMail());
		cli.setRazonSocial(cliente.getRazonSocial());
		cli.persistirCliente();
		
	}

	
	public void eliminarCliente(ClienteDto cliente) throws RemoteException {
		//AdministracionOV.getInstancia().getOficinaVentaNegocio().getClientes().remove(index);
		
		ClienteDAO.getInstancia().delete(cliente);
		
	}

	@Override
	public void modificarCliente(ClienteDto cliente) throws RemoteException {

		ClienteNegocio cli = new ClienteNegocio();
		cli.setCUIT(cliente.getCUIT());
		cli.setMail(cliente.getMail());
		cli.setRazonSocial(cliente.getRazonSocial());
		cli.updateCliente();
	}

	@Override
	public List<CotizacionDto> obtenerCotizacionesAprobadas() throws RemoteException {
		
		List<CotizacionNegocio> cotizaciones = CotizacionDAO.getinstancia().obtenerCotizacionesAprobada("ACEPTADA");
		List<CotizacionDto> cotizacionesDto = new ArrayList<CotizacionDto>();
		for(int i=0; i<cotizaciones.size(); i++){
			cotizacionesDto.add(cotizaciones.get(i).aCotizacionDto());
		}
		return cotizacionesDto;
	}

	@Override
	public void crearSolicitudCompra(List<CotizacionDto> cotizacionesAprobadas) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SolicitudCompraDto> obtenerSolicitudesPendientes() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearOrdenCompra(List<SolicitudCompraDto> solicitudesPendientes) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	

}
