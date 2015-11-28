package test2;

import java.io.IOException;
import java.rmi.RemoteException;

public class CargarDatos_EjecutarPrimero {
	
	public static void main(String[] args) throws IOException {
		

		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		
		System.out.println("Primera Ejecución - Cargamos Datos");
		System.out.println("Recuerde setear 'create-drop' en hinernate.properties");
		System.out.println("Enter para continuar");
		System.in.read();
		CargarDatos.getInstance().cargaDeDatos();
		System.out.println("Prueba Charly - Carga Finalizada");	
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		
	}
}
