package controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import bean.*;
import dao.*;
import dto.*;
import interfaces.IAdministracionCliente;

public class AdministracionCliente implements IAdministracionCliente {
	
	public AdministracionCliente administracion;
	public List<ClienteDto> clientes;
	
	public AdministracionCliente getInstancia(){
		if(administracion == null)
			administracion = new AdministracionCliente();
		return administracion;
	}
	
	public AdministracionCliente(){
		clientes = new ArrayList<ClienteDto>();
	}

	public void crearCliente(String razonSocial, String mail, int CUIT, int unidades, int meses, int operaciones) throws RemoteException {
		// TODO:
	}

	public void eliminarCliente(int CUIT) throws RemoteException {
		// TODO
	}

	public void modificarCliente(ClienteDto cliente) throws RemoteException {
		// TODO
	}

	public ClienteDto obtenerCliente(int CUIT) throws RemoteException {
		// TODO
		return null;
	}

	public List<ClienteBean> listarClientes() throws RemoteException {		
		List<ClienteBean> listaClientes = ClienteDAO.listarClientes();
		return listaClientes;
	}

	

}
