package controlador;

import interfaces.IAdministracionOV;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import utils.ItemDto;
import bean.RodamientoBean;
import dao.RodamientoDAO;
import dto.ClienteDto;
import dto.CotizacionDto;
import dto.EnvioAOVDto;
import dto.FacturaDto;
import dto.ProveedorDto;
import dto.RemitoDto;
import dto.RodamientoDto;

public class AdministracionOV implements IAdministracionOV {

	public AdministracionOV administracion; 
	
	private List <ClienteDto> clientes;
	private List <FacturaDto> facturas;
	private List <RemitoDto> remitos;
	private List <ProveedorDto> proveedores;
	private List <CotizacionDto> cotizaciones;
	private String centroIndustrial;
	
	
	public AdministracionOV getInstancia(){
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
	public List<RodamientoDto> obtenerRodamientos() throws RemoteException {
		// TODO Auto-generated method stub
		List <RodamientoBean> listaRodamients = RodamientoDAO.getInstancia().obtenerRodamientos();
		
		return null;
	}



	@Override
	public FacturaDto crearFactura(ClienteDto cliente,
			List<CotizacionDto> cotizaciones) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public EnvioAOVDto entregaPedidos(RemitoDto remito) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean abmCliente(ClienteDto cliente, String accion)
			throws RemoteException {
		// TODO Auto-generated method stub
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
	public CotizacionDto crearCotizacion(List<ItemDto> listaItems,
			ClienteDto cliente) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}
