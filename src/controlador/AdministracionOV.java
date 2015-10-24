package controlador;

import javax.persistence.*;

import interfaces.IAdministracionOV;

import java.rmi.*;
import java.util.*;

import negocio.*;
import utils.*;
import dao.*;
import dto.*;

@Entity
@Table(name="OV")
public class AdministracionOV implements IAdministracionOV {

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name="idOV")
	private int idAdministracionOV;
	
	@Transient
	public static AdministracionOV administracion; 
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ov_clientes")
	private List <ClienteNegocio> clientes;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ov_facturas")
	private List <FacturaNegocio> facturas;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ov_remitos")
	private List <RemitoNegocio> remitos;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ov_proveedores")
	private List <ProveedorNegocio> proveedores;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ov_cotizaciones")
	private List <CotizacionNegocio> cotizaciones;
	
	private String centroIndustrial;
	
	
	public static AdministracionOV getInstancia(){
		if(administracion == null){
			administracion = new AdministracionOV();
		}
		return administracion;
	}

	public AdministracionOV(){
		clientes = new ArrayList <ClienteNegocio>();
		facturas = new ArrayList <FacturaNegocio>();
		remitos = new ArrayList <RemitoNegocio>();
		proveedores = new ArrayList <ProveedorNegocio>();
		cotizaciones = new ArrayList <CotizacionNegocio>();
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



	public List <ClienteNegocio> getClientes() {
		return clientes;
	}


	public void setClientes(List <ClienteNegocio> clientes) {
		this.clientes = clientes;
	}


	public List <FacturaNegocio> getFacturas() {
		return facturas;
	}


	public void setFacturas(List <FacturaNegocio> facturas) {
		this.facturas = facturas;
	}


	public List <RemitoNegocio> getRemitos() {
		return remitos;
	}


	public void setRemitos(List <RemitoNegocio> remitos) {
		this.remitos = remitos;
	}


	public List <ProveedorNegocio> getProveedores() {
		return proveedores;
	}


	public void setProveedores(List <ProveedorNegocio> proveedores) {
		this.proveedores = proveedores;
	}


	public List <CotizacionNegocio> getCotizaciones() {
		return cotizaciones;
	}


	public void setCotizaciones(List <CotizacionNegocio> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}


	public String getCentroIndustrial() {
		return centroIndustrial;
	}


	public void setCentroIndustrial(String centroIndustrial) {
		this.centroIndustrial = centroIndustrial;
	}

	@Override
	public FacturaDto crearFactura(ClienteDto cliente, CotizacionDto cotizacion) throws RemoteException {
		// TODO RAMA
		
		ClienteDto cl = new ClienteDto();
		cl.setCUIT(cliente.getCUIT());
		cl.setMail(cliente.getMail());
		cl.setRazonSocial(cliente.getRazonSocial());
		CotizacionDto c = new CotizacionDto();
		c.setEstado(cotizacion.getEstado());
		FacturaDto aux = new FacturaDto(); 
		
		if(c.getEstado()=="Aprobada"){
			
			
		}
		
		return aux;
	}

	public int getIdAdministracionOV() {
		return idAdministracionOV;
	}

	//Daro 24-10: Esto obliga a que el id sea static, y eso no permite mapear hibernate, queda comentado igual no sirve setear ids
//	public static void setIdAdministracionOV(int idAdministracionOV) {
//		AdministracionOV.idAdministracionOV = idAdministracionOV;
//	}

	@Override
	public boolean abmCliente(ClienteDto cliente, String accion)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}



}
