package controlador;

import interfaces.ManejoDatos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import beans.RodamientoBean;
import dao.RodamientoDAO;
import entity.RodamientoEntity;

public class Controlador extends UnicastRemoteObject implements ManejoDatos {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Controlador() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<RodamientoBean> obtenerRodamientos() throws RemoteException {
		
		List<RodamientoBean> rodamientosDto = new ArrayList<RodamientoBean>();
		List<RodamientoEntity> rodamientosEntity = new ArrayList<RodamientoEntity>();
		
		rodamientosEntity = RodamientoDAO.getInstancia().obtenerRodamientos();
		
		for(RodamientoEntity r : rodamientosEntity){
			RodamientoBean aux = new RodamientoBean();
			aux.setCodigo(r.getCodigo());
			aux.setMarca(r.getMarca());
			aux.setOrigen(r.getOrigen());
			aux.setPrecio(r.getPrecio());
			rodamientosDto.add(aux);
		}
		
		return rodamientosDto;
	}

}
