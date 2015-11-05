package test2;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import controlador.*;
import dto.*;

public class TestRama {

	public static void main(String[] args) throws RemoteException{
		
		//Rama ni hagas los abm, ponete con lo otro, los abm ni los hacemos!
		
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
