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
			OficinaVentaNegocio = new OVNegocio();
		}
		return administracion;
	}

	public AdministracionOV() throws RemoteException{
		//Inicializo todos los array
		OficinaVentaNegocio.setClientes(new ArrayList <ClienteNegocio>());
		OficinaVentaNegocio.setFacturas(new ArrayList <FacturaNegocio>());
		OficinaVentaNegocio.setRemitos(new ArrayList <RemitoNegocio>());
		OficinaVentaNegocio.setCotizaciones(new ArrayList <CotizacionNegocio>());
		OficinaVentaNegocio.setSolicitudes(new ArrayList <SolicitudCompraNegocio>());
		//Obtengo la OV buscada
		this.setOficinaVentaNegocio(OVDAO.getInstancia().obtenerOV(1));
	}
	
	public OVNegocio getOficinaVentaNegocio() {
		return OficinaVentaNegocio;
	}

	public void setOficinaVentaNegocio(OVNegocio oficinaVentaNegocio) {
		OficinaVentaNegocio = oficinaVentaNegocio;
	}
	
	//Daro: Este metodo crea la cotizacion con la lista de items pasada por parametro y la deja en estado: Pendiente
	public int crearCotizacion(List<ItemDto> listaItems, ClienteDto cliente) throws RemoteException {
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
	
	//Devuelvo el maximo ID de la tabla Cotizaciones (el id de la ultima cotizacion creada)
	return CotizacionDAO.getinstancia().obtenerMaximoIDCotizacion();
	}
	
	
	
	//Daro: Este metodo aprueba la Cotizacion, dejandola en estado Aprobada
	public float aprobarYCotizarCotizacion (int idCotizacion)  throws RemoteException{		
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
		miCotNeg.actualizarCotizacion();
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
		miCotNeg.actualizarCotizacion();
	return;
	}
	
	
	//Daro: Se realiza un bulto por cliente, junto a sus Remitos y Facturas
	public BultoDto entregaPedidos(int idRemito, int idFactura) throws RemoteException {
		//Creo el Bulto que voy a devolver
		BultoDto miBulDto = new BultoDto();
		//Busco el Remito en la Base de Datos
		RemitoNegocio miRemNeg = new RemitoNegocio();
		miRemNeg = RemitoDAO.getinstancia().buscarRemito(idRemito);
		//Busco la Factura en la Base de Datos
		FacturaNegocio miFacNeg = new FacturaNegocio();
		miFacNeg = FacturaDAO.getInstancia().buscarFactura(idFactura);
		
		//Transformo RemitoNegocio a RemitoDto
		RemitoDto miRemDto = new RemitoDto();
		miRemDto = miRemNeg.aRemitoDto();
		//Trasnformo FacturaNegocio a FacturaDto
		FacturaDto miFacDto = new FacturaDto();
		miFacDto = miFacNeg.aFacturaDto();
		
		
		
	return miBulDto;
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
			itFactura.setPrecio(sal.floatValue());
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

	

}
