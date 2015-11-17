package controlador;

import interfaces.IAdministracionOV;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import negocio.ClienteNegocio;
import negocio.CotizacionNegocio;
import negocio.FacturaNegocio;
import negocio.ItemFacturaNegocio;
import negocio.OVNegocio;
import negocio.ProveedorNegocio;
import negocio.RemitoNegocio;
import negocio.RodamientoNegocio;
import utils.ItemDto;
import dao.CotizacionDAO;
import dao.RodamientoDAO;
import dto.BultoDto;
import dto.ClienteDto;
import dto.CotizacionDto;
import dto.ItemCotizacionDto;
import dto.RemitoDto;
import dto.RodamientoDto;
import dto.SolicitudCompraDto;


public class AdministracionOV implements IAdministracionOV{

	public static AdministracionOV administracion; 
	//Daro 25/10: Se genera una OVnegocio unica, cuando realmente deberia ser una lista. Trabajarlo con listas es muy complejo
	//sumado con el singleton en el medio seria una locura saber cuando se levanta tal o cual OV (y los constructores se harian multiples, demente)
	//Creo una OV unica y se deberia sacar el singleton de esta clase, asi se puede dar de alta los controladores que se deseen para cada OV (negrisimo pero logico)
	//De la forma que esta hecho ahora funciona para una unica OV
	private static OVNegocio OficinaVentaNegocio;

	public static AdministracionOV getInstancia(){
		if(administracion == null){
			administracion = new AdministracionOV();
			OficinaVentaNegocio = new OVNegocio();
		}
		return administracion;
	}

	public AdministracionOV(){
		this.getOficinaVentaNegocio().setClientes(new ArrayList <ClienteNegocio>());
		this.getOficinaVentaNegocio().setFacturas(new ArrayList <FacturaNegocio>());
		this.getOficinaVentaNegocio().setRemitos(new ArrayList <RemitoNegocio>());
		this.getOficinaVentaNegocio().setProveedores(new ArrayList <ProveedorNegocio>());
		this.getOficinaVentaNegocio().setCotizaciones(new ArrayList <CotizacionNegocio>());
		
	}
	
	
	
	//TODO REVISAR
	public List<RodamientoDto> obtenerRodamientos(){
		//Daro: Este es un metodo que solo sirve para hacer pruebas, borrar despues
		@SuppressWarnings("unused")
		List <RodamientoNegocio> listaRodamientos = RodamientoDAO.getInstancia().obtenerRodamientos();
		List <RodamientoDto> listaRodaDto = new ArrayList <RodamientoDto>();
//		for(int i=0;i<listaRodamientos.size();i++){
//			RodamientoNegocio roda = listaRodamientos.get(i);
//			//RodamientoDto rodaDto = roda.aRodamientoDto();
//			listaRodaDto.add(rodaDto);
//		}
		return listaRodaDto;
	}
	
	public List<RodamientoDto> obtenerRodamientos(String marca){
		return null;
	}
	

	//Este metodo crea la cotizacion con la lista de items pasada por parametro y la deja en estado: Pendiente
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
		
		
		
	//Este metodo aprueba la Cotizacion, dejandola en estado Aprobada
	public float aprobarCotizacion (CotizacionDto miCotDto)  throws RemoteException{		
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
		//Persisto la CotizacionNegocio
		cotizNegocio.mergearCotizacion();
		
		//Devuelvo el costo final de la Cotizacion
		return costoFinal;
	}
	
	
	
	//Este metodo rechaza la Cotizacion, dejandola en estado Rechazada
	public void rechazarCotizacion (CotizacionDto miCotDto){
		//Cambio el estado a Rechazada
		miCotDto.setEstado("Rechazada");
		//Hago merge de la Cotizacion para que cambie su estado a "Rechazada" en la BD
		CotizacionNegocio cotizNegocio = new CotizacionNegocio();
		cotizNegocio.aCotizacionNegocio(miCotDto);
		cotizNegocio.mergearCotizacion();
	}
	
	
	
	public BultoDto entregaPedidos(RemitoDto remito) throws RemoteException {
		// TODO Daro
		return null;
	}


	public void generarFactura(List<CotizacionDto> cotis, ClienteDto cliente){
		ClienteNegocio cli = new ClienteNegocio();
		cli.aClienteNegocio(cliente);
		//Creo lista de ids de cotizaciones
		List<Integer> idsCoti = new ArrayList<Integer>();
		// Crear Factura y setear datos primarios
		FacturaNegocio factura = new FacturaNegocio();
		factura.setCliente(cli);
		factura.setEstado("generada");
		Calendar c = new GregorianCalendar();
		factura.setFecha(c.getTime());
		List<CotizacionNegocio> cotizacionesFactura = new ArrayList<CotizacionNegocio>();
		// Crear ItemsFactura
		List<ItemFacturaNegocio> itemsFactura = new ArrayList<ItemFacturaNegocio>();
		for(int i=0; i<cotis.size(); i++){
			idsCoti.add(cotis.get(i).getIdCotizacion());
			CotizacionNegocio coti = new CotizacionNegocio(); //buscarCotizacion(idsCoti.get(i).intValue());
			coti.aCotizacionNegocio(cotis.get(i));
			cotizacionesFactura.add(coti);
			ActualizarEstadoCotizacion(coti, "SOLICITADA");
		}
		List<Object[]> misObjects = CotizacionDAO.getinstancia().itemsCotizacionAgrupadosPorRodamiento(idsCoti);
		for(int i=0; i<misObjects.size(); i++){
			ItemFacturaNegocio itFactura = new ItemFacturaNegocio();
			RodamientoNegocio rodamiento = RodamientoDAO.getInstancia().buscarRodamiento((Integer)misObjects.get(i)[0]);
			itFactura.setRodamiento(rodamiento);
			itFactura.setCantidad((Integer)misObjects.get(i)[0]);
			Double sal = (Double)misObjects.get(i)[2];
			itFactura.setSubtotal(sal.floatValue());
			itemsFactura.add(itFactura);
		}
		factura.setItems(itemsFactura);
		factura.persistirFactura();
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
	@Deprecated
	public void Prueba(List<Integer> coti){
		List<Object[]> misObjects = CotizacionDAO.getinstancia().itemsCotizacionAgrupadosPorRodamiento(coti);
		for(int i=0; i<misObjects.size(); i++){
			System.out.println(misObjects.get(i)[0]);
			System.out.println(misObjects.get(i)[1]);
			System.out.println(misObjects.get(i)[2]);
		}
	}

	@Override
	public void altaCliente(ClienteDto cliente) throws RemoteException {
		ClienteNegocio clientenNegocio = new ClienteNegocio(cliente.getRazonSocial(), cliente.getMail(), cliente.getCUIT());
		AdministracionOV.getInstancia().getOficinaVentaNegocio().getClientes().add(clientenNegocio);
		return;
	}

	@Override
	public void bajaCliente(ClienteDto cliente) throws RemoteException {
		// TODO Auto-generated method stub
		//AdministracionOV.getInstancia().getOficinaVentaNegocio().getClientes().remove(index);
		return;
	}

	@Override
	public void modificacionCliente(ClienteDto cliente)
			throws RemoteException {
		// TODO Auto-generated method stub
		return;
	}

	public OVNegocio getOficinaVentaNegocio() {
		return OficinaVentaNegocio;
	}

	public void setOficinaVentaNegocio(OVNegocio oficinaVentaNegocio) {
		OficinaVentaNegocio = oficinaVentaNegocio;
	}


	@Override
	public List<CotizacionDto> obtenerCotizacionesAprobadas()
			throws RemoteException {
		// TODO Auto-generated method stub
		List<CotizacionNegocio> cotizaciones = CotizacionDAO.getinstancia().obtenerCotizacionesAprobada("ACEPTADA");
		List<CotizacionDto> cotizacionesDto = new ArrayList<CotizacionDto>();
		for(int i=0; i<cotizaciones.size(); i++){
			cotizacionesDto.add(cotizaciones.get(i).aCotizacionDto());
		}
		return cotizacionesDto;
	}

	@Override
	public void crearSolicitudCompra(List<CotizacionDto> cotizacionesAprobadas)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SolicitudCompraDto> obtenerSolicitudesPendientes()
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearOrdenCompra(List<SolicitudCompraDto> solicitudesPendientes)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
