package test2;

import java.rmi.RemoteException;

import controlador.*;

public class TestCharly {
	
	/**
	 * No tocar PORFA!!!
	 * Para pruebas de Charly
	 * @throws RemoteException 
	 */

	public static void main(String[] args) throws RemoteException {
		
		//AdministracionOV admo = new AdministracionOV();
		//AdministracionCC admc = new AdministracionCC();
		/*Daro: Carlos recorda de no instanciar nuevas CC y OV sino de recuperar la instancia ya creada, por buena practica para el momento de hacer los
		metodos definitivos, si creamos nuevas CC y OV en vez de obtener las que ya tenemos se va todo al carajo, te lo hago aca*/
		AdministracionOV admo = AdministracionOV.getInstancia();
//		AdministracionCC admc = AdministracionCC.getInstancia();

		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		
		// Seteamos la OV con la que trabajaremos	
//		admo.setOficinaVentaNegocio(admc.ObtenerOV(1));
		admo.pch_LevantaCotizaciones();

//		admc.pchBorrarAlTerminar();
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");

		
	}

}
