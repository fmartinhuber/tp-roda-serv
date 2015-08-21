package controlador;

import interfaces.IAdministracionODV;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import bean.RodamientoBean;
import dao.RodamientoDAO;
import dto.ClienteDto;
import dto.CotizacionDto;
import dto.EnvioDto;
import dto.FacturaDto;
import dto.RemitoDto;
import dto.RodamientoDto;

public class AdministracionODV extends UnicastRemoteObject implements IAdministracionODV {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdministracionODV() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<RodamientoDto> obtenerRodamientos() throws RemoteException {
		
		List<RodamientoDto> rodamientosDto = new ArrayList<RodamientoDto>();
		List<RodamientoBean> rodamientosEntity = new ArrayList<RodamientoBean>();
		
		rodamientosEntity = RodamientoDAO.getInstancia().obtenerRodamientos();
		
//		for(RodamientoEntity r : rodamientosEntity){
//			RodamientoBean aux = new RodamientoBean(null);
//			aux.setCodigo(r.getCodigo());
//			aux.setMarca(r.getMarca());
//			aux.setOrigen(r.getOrigen());
//			aux.setPrecio(r.getPrecio());
//			rodamientosDto.add(aux);
//		}
		
		return rodamientosDto;
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
