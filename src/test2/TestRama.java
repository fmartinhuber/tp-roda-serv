package test2;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import controlador.*;
import dto.*;

public class TestRama {

	public static void main(String[] args) throws RemoteException{
		
		//AdministracionOV c = new AdministracionOV();
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
			
		System.out.println("Cargamos Datos");
		CargarDatos.getInstance().cargaDeDatos();
		System.out.println("Carga Finalizada");

	}

}
