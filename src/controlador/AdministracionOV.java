package controlador;

import interfaces.IAdministracionOV;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import bean.RodamientoBean;
import dao.RodamientoDAO;
import dto.RodamientoDto;

public class AdministracionOV extends UnicastRemoteObject implements IAdministracionOV {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdministracionOV() throws RemoteException {
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

}
