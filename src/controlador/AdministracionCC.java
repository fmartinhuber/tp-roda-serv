package controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

import negocio.*;

import dto.CotizacionDto;
import dto.OrdenCompraDto;
import dto.ProveedorDto;
import dto.RemitoDto;
import dto.RodamientoDto;
import interfaces.IAdministracionCC;

@Entity
@Table(name="CC")
public class AdministracionCC implements IAdministracionCC {

	@Transient
	public static AdministracionCC administracion;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private static int idAdministracionCC;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cc_ordenes")
	private List <OrdenCompraNegocio> ordenesP;
	/**
	 *  Actualizar stock propio. (RAMA)
	 *  Se utiliza para manejar el stock interno.
	 */
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cc_rodamientos_interno")
	private List <RodamientoNegocio> rodamientos;
	/**
	 * Rodamientos con stock del proveedor. (DARO-MARTIN)
	 */
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cc_rodamientos_pri")
	private List <RodamientoNegocio> listaPrincipal;
	/**
	 * Rodamientos con stock del proveedor. (DARO-MARTIN)
	 */
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cc_rodamientos_opc")
	private List <RodamientoNegocio> listaOpcional;
	
	
	public AdministracionCC(){
		ordenesP = new ArrayList <OrdenCompraNegocio>();
		rodamientos = new ArrayList <RodamientoNegocio>();
		listaPrincipal = new ArrayList<RodamientoNegocio>();
		listaOpcional = new ArrayList<RodamientoNegocio>();
		
		/*Daro: Meto valores hardcodeados a la ListaPrincipal para poder crear la Cotizacion
		Esto deberia hacerse de forma automatica desde algun lado que elija Martin para su lista*/
		ProveedorNegocio provUno = new ProveedorNegocio();
			provUno.setNombre("Solear SA");		
		RodamientoNegocio rodaUno = new RodamientoNegocio();
			rodaUno.setCodigo("22310");
			rodaUno.setCaracteristica("CCW33");
			rodaUno.setMarca("ZKL");
			rodaUno.setMonto((float) 310.71);
			rodaUno.setOrigen("Japon");
			rodaUno.setProveedor(provUno);
			rodaUno.setStock(85);
			rodaUno.setTipo("Bolilla");
		listaPrincipal.add(rodaUno);
	}
	
	public static AdministracionCC getInstancia(){
		if(administracion == null){
			administracion = new AdministracionCC();
		}
		return administracion;
	}
	
	
	/*
	 * No es necesario una lista de cotizaciónes
	 * Debera levantar todas las cotizaciones que aun no fueron cargadas a una orden de compra
	 * Se requiere un nuevo estado en la orden de compra y en la solicitud de cotización
	 * Marcar solicitud de cotización como "En Adquisición"
	 * Marcar Orden de compra como "Nueva" luego de su creación y previo a la entrega al proveedor
	*/
	public List<OrdenCompraDto> crearOrden(List<CotizacionDto> listaCotizaciones)
			throws RemoteException {
		// TODO NO SE A QUIEN LE TOCA ESTO
		return null;
		// Levantar Cotizaciones en estado "APROBADAS"
		
		
	}
	
	// Levanta las cotizaciones en un estado pasado por parametro "XXXXXXXX"  // "APROBADA"
	// PASAR A PRIVADO LUEGO DE LAS PRUEBAS
	public List<CotizacionNegocio> buscarCotizacionesAprobadas(String estado){
		
		List<CotizacionNegocio> misCotizaciones = new ArrayList<CotizacionNegocio>();
		
		
		
		return null;
	}

	@Override
	public RemitoDto crearRemito(List<OrdenCompraDto> listaOrdenes)
			throws RemoteException {
		// TODO CARLOS
		return null;
	}

	@Override
	public void actualizarStock(List<RodamientoDto> listaRodamientos) {
		// TODO RAMA
		
	}

	public List <OrdenCompraNegocio> getOrdenesP() {
		return ordenesP;
	}

	public void setOrdenesP(List <OrdenCompraNegocio> ordenesP) {
		this.ordenesP = ordenesP;
	}

	public List <RodamientoNegocio> getRodamientos() {
		return rodamientos;
	}

	public void setRodamientos(List <RodamientoNegocio> rodamientos) {
		this.rodamientos = rodamientos;
	}

	@Override
	public List<RodamientoDto> obtenerListaComparativa() throws RemoteException {
		//TODO REVISAR.
		return null;
	}
	
	public List <RodamientoDto> obtenerListaComparativaOpcional () throws RemoteException{
		//TODO REVISAR.
		return null;
	}

	@Override
	public void actualizarListaComparativa(List<RodamientoDto> listado)	throws RemoteException {
		//TODO REVISAR.
		Iterator <RodamientoNegocio> iterador = this.listaPrincipal.iterator();
		while(iterador.hasNext()){
			RodamientoNegocio roda = iterador.next();
			this.agregarNuevoRodamiento(roda);
		}
	}


	private void agregarNuevoRodamiento (RodamientoNegocio rodamiento){
		Iterator <RodamientoNegocio> iterador = this.listaPrincipal.iterator();
		boolean encontradoP = false, actualizadoP = false; 
		while(iterador.hasNext() && !encontradoP){
			RodamientoNegocio rodamientoComp = iterador.next();
			if(rodamientoComp.getCodigo().equals(rodamiento.getCodigo())){
				encontradoP = true;
				//si es mas barato
				if(rodamientoComp.getMonto() < rodamiento.getMonto()){
					actualizadoP = true; 
					this.listaPrincipal.remove(rodamientoComp);
					this.listaPrincipal.add(rodamiento);
					break;
				}
			}
		}
		if(encontradoP && !actualizadoP){
			this.listaOpcional.add(rodamiento);
		}
		if(!encontradoP && !actualizadoP){
			iterador = this.listaOpcional.iterator();
			while(iterador.hasNext() && !encontradoP){
				RodamientoNegocio rodamientoComp = iterador.next();
				if(rodamientoComp.getCodigo().equals(rodamiento.getCodigo())){
					this.listaOpcional.add(rodamiento);
					actualizadoP = true;
					break;
				}
			}
		}
	}
	

	public List <RodamientoNegocio> getListaOpcional() {
		return listaOpcional;
	}

	public void setListaOpcional(List <RodamientoNegocio> listaOpcional) {
		this.listaOpcional = listaOpcional;
	}

	public static int getIdAdministracionCC() {
		return idAdministracionCC;
	}

	public static void setIdAdministracionCC(int idAdministracionCC) {
		AdministracionCC.idAdministracionCC = idAdministracionCC;
	}

	public RodamientoDto buscarRodamientoDto(String codigo){
		for(Iterator <RodamientoNegocio> iterador = rodamientos.iterator();iterador.hasNext();){
			RodamientoNegocio rodamiento = iterador.next();
			if(rodamiento.getCodigo().equals(codigo))
				return rodamiento.aRodamientoDto();
		}
		return null;
	}
	
	public RodamientoNegocio buscarRodamientoNegocio(String codigo){
		for(Iterator <RodamientoNegocio> iterador = rodamientos.iterator();iterador.hasNext();){
			RodamientoNegocio rodamiento = iterador.next();
			if(rodamiento.getCodigo().equals(codigo))
				return rodamiento;
		}
		return null;
	}

	@Override
	public boolean abmProveedor(ProveedorDto proveedor, String accion)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
