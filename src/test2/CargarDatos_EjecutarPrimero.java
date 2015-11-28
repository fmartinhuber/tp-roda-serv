package test2;

import java.io.IOException;
import java.rmi.RemoteException;

public class CargarDatos_EjecutarPrimero {
	
	public static void main(String[] args) throws IOException {
		
		//AdministracionOV admo = new AdministracionOV();
		//AdministracionCC admc = new AdministracionCC();
		/*Daro: Carlos recorda de no instanciar nuevas CC y OV sino de recuperar la instancia ya creada, por buena practica para el momento de hacer los
		metodos definitivos, si creamos nuevas CC y OV en vez de obtener las que ya tenemos se va todo al carajo, te lo hago aca*/
//		AdministracionOV admo = AdministracionOV.getInstancia();
//		AdministracionCC admc = AdministracionCC.getInstancia();

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
