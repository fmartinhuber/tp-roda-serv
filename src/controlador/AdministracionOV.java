package controlador;

import interfaces.IAdministracionOV;

import java.rmi.*;
import java.util.*;

import negocio.RodamientoNegocio;
import utils.*;
import dao.*;
import dto.*;

public class AdministracionOV implements IAdministracionOV {

	public static AdministracionOV administracion; 
	
	private List <ClienteDto> clientes;
	private List <FacturaDto> facturas;
	private List <RemitoDto> remitos;
	private List <ProveedorDto> proveedores;
	private List <CotizacionDto> cotizaciones;
	private String centroIndustrial;
	
	
	public static AdministracionOV getInstancia(){
		if(administracion == null){
			administracion = new AdministracionOV();
		}
		return administracion;
	}

	public AdministracionOV(){
		clientes = new ArrayList <ClienteDto>();
		facturas = new ArrayList <FacturaDto>();
		remitos = new ArrayList <RemitoDto>();
		proveedores = new ArrayList <ProveedorDto>();
		cotizaciones = new ArrayList <CotizacionDto>();
	}
	
	@Override
	public List<RodamientoDto> obtenerRodamientos(){
		//Daro: Este es un metodo que solo sirve para hacer pruebas, borrar despues
		@SuppressWarnings("unused")
		List <RodamientoNegocio> listaRodamientos = RodamientoDAO.getInstancia().obtenerRodamientos();	
		return null;
	}

	public CotizacionDto crearCotizacion(List<ItemDto> listaItems, ClienteDto cliente) throws RemoteException {
		//Declaro la cotizacion que voy a devolver
		CotizacionDto miCotDto = new CotizacionDto();
		//Uso la magica lista de Martin a ver como funca
		AdministracionCC admCC = new AdministracionCC();
		List<RodamientoDto> listaCompa = new ArrayList<RodamientoDto>();
		listaCompa = admCC.obtenerListaComparativa();
				
		//Seteo los valores correspondientes a 
		miCotDto.setCliente(cliente);
		miCotDto.setEstado("Pendiente"); //Todas se crean pendientes hasta ser aprobadas
			Date actual = new Date();
		miCotDto.setFechaCreacion(actual); //Se crea con la fecha actual
			Calendar c = Calendar.getInstance(); 
			c.setTime(actual); 
			c.add(Calendar.DATE, 30); //Se agregan 30 dias a la fecha actual
			Date vigencia = new Date();
			vigencia = c.getTime();
		miCotDto.setFechaVigencia(vigencia);
		
		/*Aca viene la magia, recorro la listaComparativa en busqueda de los rodamientos que me pidio el cliente
		y los cotizo (multiplico monto * cantidad)*/
		
		//Creo la lista de items que voy a utilizar para ir cargandolos
		List<ItemCotizacionDto> listaItemCotDto = new ArrayList<ItemCotizacionDto>();

		//Para cada elemento de la lista comparativa
		for (int i=0; i<=listaCompa.size(); i++){
			//Comparo con cada elemento de la lista Item
			for (int j=0; j<=listaItems.size(); j++){
				//Obtengo los codigos
					String codComp = listaCompa.get(i).getCodigo();
					String codItem = listaItems.get(j).getRodamiento().getCodigo();
				//Origen
					String orgComp = listaCompa.get(i).getCodigo();
					String orgItem = listaItems.get(j).getRodamiento().getOrigen();
				//Marca
					String marComp = listaCompa.get(i).getCodigo();
					String marItem = listaItems.get(j).getRodamiento().getOrigen();
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
		
		//Aca tengo que guardar la cotizacion en la base antes de salir
		
		return miCotDto;
	}



	@Override
	public EnvioAOVDto entregaPedidos(RemitoDto remito) throws RemoteException {
		// TODO Carlos
		return null;
	}


	@Override
	public boolean abmCliente(ClienteDto cliente, String accion)
			throws RemoteException {
		// TODO OTRO METODO ABM???
		return false;
	}


	public List <ClienteDto> getClientes() {
		return clientes;
	}


	public void setClientes(List <ClienteDto> clientes) {
		this.clientes = clientes;
	}


	public List <FacturaDto> getFacturas() {
		return facturas;
	}


	public void setFacturas(List <FacturaDto> facturas) {
		this.facturas = facturas;
	}


	public List <RemitoDto> getRemitos() {
		return remitos;
	}


	public void setRemitos(List <RemitoDto> remitos) {
		this.remitos = remitos;
	}


	public List <ProveedorDto> getProveedores() {
		return proveedores;
	}


	public void setProveedores(List <ProveedorDto> proveedores) {
		this.proveedores = proveedores;
	}


	public List <CotizacionDto> getCotizaciones() {
		return cotizaciones;
	}


	public void setCotizaciones(List <CotizacionDto> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}


	public String getCentroIndustrial() {
		return centroIndustrial;
	}


	public void setCentroIndustrial(String centroIndustrial) {
		this.centroIndustrial = centroIndustrial;
	}

	@Override
	public FacturaDto crearFactura(ClienteDto cliente, CotizacionDto cotizacion)
			throws RemoteException {
		// TODO RAMA
		return null;
	}



}
