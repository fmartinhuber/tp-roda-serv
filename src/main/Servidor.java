package main;

import interfaces.*;

import java.rmi.*;
import java.rmi.registry.*;

import controlador.*;

public class Servidor {

	public static void main(String[] args){
		new Servidor();
	}
	
	public Servidor(){
		iniciar();
	}
	
	public void iniciar() {
    	try {
    		LocateRegistry.createRegistry(1099);
    		IAdministracionODV Server = new AdministracionODV();
            Naming.rebind ("//localhost/SistemaRodamiento", Server);
            System.out.println("Servidor corriendo. Fijado en //localhost/SistemaRodamiento");
		} catch (Exception e) {
			System.out.println("ERROR: Error al ejecutar el servidor, compruebe que el mismo no este ya ejecutandose");
			e.printStackTrace();
		}
    	
    }

}
