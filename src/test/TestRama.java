package test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import bean.*;
import controlador.*;
import dto.*;

public class TestRama {

	public static void main(String[] args) throws RemoteException{
		
		AdministracionOV administracionCliente = new AdministracionOV();
		List<ClienteDto> clienteDto = new ArrayList<ClienteDto>();
		//clienteDto = administracionCliente.listarClientes();
		
		System.out.println("=====================================================================================");
		System.out.println("=====================================================================================");
		System.out.println();
		System.out.println();
		
		for(ClienteDto c : clienteDto){
			System.out.println("CUIT: " +c.getCUIT());
		}

	}

}
