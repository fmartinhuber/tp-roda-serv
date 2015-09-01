package controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



import dto.CotizacionDto;
import dto.OrdenCompraDto;
import dto.ProveedorDto;
import dto.RemitoDto;
import dto.RodamientoDto;
import interfaces.IAdministracionCC;

public class AdministracionCC implements IAdministracionCC {

	public AdministracionCC administracion;
	private List <OrdenCompraDto> ordenesP;
	/**
	 *  Actualizar stock propio. (RAMA)
	 */
	private List <RodamientoDto> rodamientos;
	/**
	 * Rodamientos con stock del proveedor. (DARO-MARTIN)
	 */
	private List <RodamientoDto> listaComparativa;
	
	
	
	public AdministracionCC(){
		ordenesP = new ArrayList <OrdenCompraDto>();
		rodamientos = new ArrayList <RodamientoDto>();
	}
	
	public AdministracionCC getInstancia(){
		if(administracion == null){
			administracion = new AdministracionCC();
		}
		return administracion;
	}
	
	@Override
	public boolean abmProveedor(ProveedorDto proveedor, String accion)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public List<RodamientoDto> obtenerListaComparativa(
			List<RodamientoDto> listaRodamientosNueva) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdenCompraDto> crearOrden(List<CotizacionDto> listaCotizaciones)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RemitoDto crearRemito(List<OrdenCompraDto> listaOrdenes)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarStock(List<RodamientoDto> listaRodamientos) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarListaComparativa(List<RodamientoDto> listado)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public List <RodamientoDto> getListaComparativa() {
		return listaComparativa;
	}

	public void setListaComparativa(List <RodamientoDto> listaComparativa) {
		this.listaComparativa = listaComparativa;
	}

	private void agregarNuevoRodamiento (RodamientoDto rodamiento){
		Iterator <RodamientoDto> iterador = this.listaComparativa.iterator();
		boolean encontradoP = false, actualizadoP = false; 
		while(iterador.hasNext() && !encontradoP){
			RodamientoDto rodamientoComp = iterador.next();
			if(rodamientoComp.getCodigo().equals(rodamiento.getCodigo())){
				encontradoP = true;
				//si es mas barato
				if(rodamientoComp.getMonto() < rodamiento.getMonto()){
					actualizadoP = true; 
					this.listaComparativa.remove(rodamientoComp);
					this.listaComparativa.add(rodamiento);
				}
			}
		}
	}
	
	
	
	
	public RodamientoDto consultaMejorRodamiento (int codigo, int cantidad){
		return null;
	}

	



}
