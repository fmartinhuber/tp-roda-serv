package controlador;

import interfaces.IAdministracionODV;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import utils.Item;
import bean.RodamientoBean;
import dao.RodamientoDAO;
import dto.ClienteDto;
import dto.CotizacionDto;
import dto.EnvioAOVDto;
import dto.FacturaDto;
import dto.RemitoDto;
import dto.RodamientoDto;

public class AdministracionODV extends UnicastRemoteObject implements IAdministracionODV {

	private static final long serialVersionUID = 1L;

	public AdministracionODV() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public CotizacionDto generarCotizacion(List<Item> listaItems,
			ClienteDto cliente) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacturaDto generarFactura(ClienteDto cliente,
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

	@Override
	public List<RodamientoDto> obtenerRodamientos() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}
