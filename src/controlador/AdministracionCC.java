package controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;
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
	private List <RodamientoDto> rodamientos;
	private Set <RodamientoDto> listaComparativa ;
	
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
	public void actualizarStock(List<RodamientoDto> listaRodamientos)
			throws RemoteException {
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


	



}
