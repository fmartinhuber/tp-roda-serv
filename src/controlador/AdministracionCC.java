package controlador;

import java.rmi.RemoteException;
import java.util.List;

import utils.Item;
import dto.ClienteDto;
import dto.CotizacionDto;
import dto.EnvioAOVDto;
import dto.FacturaDto;
import dto.RemitoDto;
import dto.RodamientoDto;
import interfaces.IAdministracionODV;

public class AdministracionCC implements IAdministracionODV {

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
