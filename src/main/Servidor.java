package main;

import interfaces.*;

import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

import test2.CargarDatos_EjecutarPrimero;
import controlador.*;

public class Servidor {

	public static void main(String[] args){
		System.out.println(IAdministracionOV.class.toString());
		System.out.println(IAdministracionOV.class.getProtectionDomain().getCodeSource().getLocation().toString());
		
		//propieda para enlazar un archivo de texto "java.policy" con la propiedad de java. 
		System.setProperty("java.security.policy", "java.policy");
		//
		System.setProperty("java.rmi.server.codebase", IAdministracionOV.class.getProtectionDomain().getCodeSource().getLocation().toString());
        
		if(System.getSecurityManager() == null) {
			//Establece un sistema de seguridad. 
            //System.setSecurityManager(new SecurityManager());
        }
		new Servidor();
	}
	
	public Servidor(){
		iniciar();
	}
	
	public void iniciar() {
    	try {
    		LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
    		IAdministracionOV ServerOV = AdministracionOV.getInstancia();
    		IAdministracionCC ServerCC = AdministracionCC.getInstancia();
    		IAdministracionOV stubOV = (IAdministracionOV) UnicastRemoteObject.exportObject(ServerOV, 0);
    		IAdministracionCC stubCC = (IAdministracionCC) UnicastRemoteObject.exportObject(ServerCC, 0);
            Naming.rebind ("//localhost/SistemaRodamientoOV", stubOV);
            Naming.rebind ("//localhost/SistemaRodamientoCC", stubCC);
            verVinculos();
		} catch (Exception e) {
			System.out.println("ERROR: Error al ejecutar el servidor, compruebe que el mismo no este ya ejecutandose");
			e.printStackTrace();
		}
    	
    }
	
	public void verVinculos() {
        try {
      	  String[] vinculos = Naming.list( "" );
      	  System.out.println(InetAddress.getLocalHost().getHostAddress());
      	  for ( int i = 0; i < vinculos.length; i++ ){
      		System.out.print( "\n"+vinculos[i] );
      	  }
        }
        catch (Exception e) {
      	  e.printStackTrace();
        }
     }

}
