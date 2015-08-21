package controlador;

import java.rmi.RemoteException;
import java.util.List;

import dto.ClienteDto;
import dto.CotizacionDto;
import dto.EnvioDto;
import dto.FacturaDto;
import dto.RemitoDto;
import dto.RodamientoDto;
import interfaces.IAdministracionODV;

public class AdministracionCC implements IAdministracionODV {

	@Override
	public List<RodamientoDto> obtenerRodamientos() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CotizacionDto generarCotizacion(List<RodamientoDto> listaRodamientos)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacturaDto generarFactura(ClienteDto cliente,
			CotizacionDto cotizacion) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnvioDto entregaPedidos(ClienteDto cliente, RemitoDto remito)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
