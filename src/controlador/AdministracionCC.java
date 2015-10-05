package controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import negocio.CotizacionNegocio;
import dto.CotizacionDto;
import dto.OrdenCompraDto;
import dto.ProveedorDto;
import dto.RemitoDto;
import dto.RodamientoDto;
import interfaces.IAdministracionCC;

public class AdministracionCC implements IAdministracionCC {

	public static AdministracionCC administracion;
	
	private List <OrdenCompraDto> ordenesP;
	/**
	 *  Actualizar stock propio. (RAMA)
	 *  Se utiliza para manejar el stock interno.
	 */
	private List <RodamientoDto> rodamientos;
	/**
	 * Rodamientos con stock del proveedor. (DARO-MARTIN)
	 */
	private List <RodamientoDto> listaPrincipal;
	/**
	 * Rodamientos con stock del proveedor. (DARO-MARTIN)
	 */
	private List <RodamientoDto> listaOpcional;
	
	
	public AdministracionCC(){
		ordenesP = new ArrayList <OrdenCompraDto>();
		rodamientos = new ArrayList <RodamientoDto>();
		listaPrincipal = new ArrayList<RodamientoDto>();
		listaOpcional = new ArrayList<RodamientoDto>();
		
		/*Daro: Meto valores hardcodeados a la ListaPrincipal para poder crear la Cotizacion
		Esto deberia hacerse de forma automatica desde algun lado que elija Martin para su lista*/
		ProveedorDto provUno = new ProveedorDto();
			provUno.setNombre("Solear SA");		
		RodamientoDto rodaUno = new RodamientoDto();
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
	
	@Override
	public boolean abmProveedor(ProveedorDto proveedor, String accion)
			throws RemoteException {
		// TODO NO ENTIENDO ESTO? QUIEN LO HIZO? DEBERIAN SER 3 METODOS DISTINTOS UN ABM
		return false;
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

	public List <OrdenCompraDto> getOrdenesP() {
		return ordenesP;
	}

	public void setOrdenesP(List <OrdenCompraDto> ordenesP) {
		this.ordenesP = ordenesP;
	}

	public List <RodamientoDto> getRodamientos() {
		return rodamientos;
	}

	public void setRodamientos(List <RodamientoDto> rodamientos) {
		this.rodamientos = rodamientos;
	}

	@Override
	public List<RodamientoDto> obtenerListaComparativa() throws RemoteException {
		return this.listaPrincipal;
	}
	
	public List <RodamientoDto> obtenerListaComparativaOpcional () throws RemoteException{
		return this.listaOpcional;
	}

	@Override
	public void actualizarListaComparativa(List<RodamientoDto> listado)	throws RemoteException {
		Iterator <RodamientoDto> iterador = this.listaPrincipal.iterator();
		while(iterador.hasNext()){
			RodamientoDto roda = iterador.next();
			this.agregarNuevoRodamiento(roda);
		}
	}


	private void agregarNuevoRodamiento (RodamientoDto rodamiento){
		Iterator <RodamientoDto> iterador = this.listaPrincipal.iterator();
		boolean encontradoP = false, actualizadoP = false; 
		while(iterador.hasNext() && !encontradoP){
			RodamientoDto rodamientoComp = iterador.next();
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
				RodamientoDto rodamientoComp = iterador.next();
				if(rodamientoComp.getCodigo().equals(rodamiento.getCodigo())){
					this.listaOpcional.add(rodamiento);
					actualizadoP = true;
					break;
				}
			}
		}
	}
	

	public List <RodamientoDto> getListaOpcional() {
		return listaOpcional;
	}

	public void setListaOpcional(List <RodamientoDto> listaOpcional) {
		this.listaOpcional = listaOpcional;
	}


}
