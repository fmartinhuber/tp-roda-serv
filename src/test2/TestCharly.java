package test2;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import negocio.OVNegocio;
import negocio.ProveedorNegocio;
import negocio.RodamientoNegocio;
import controlador.AdministracionCC;
import controlador.AdministracionOV;
import dto.CotizacionDto;
import dto.RodamientoDto;

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
		AdministracionCC admc = AdministracionCC.getInstancia();

		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		
//		System.out.println("Prueba Charly - Cargamos Datos");
//		CargarDatos.getInstance().cargaDeDatos();
//		System.out.println("Prueba Charly - Carga Finalizada");	
		
		// Seteamos la OV con la que trabajaremos	
//		admo.setOficinaVentaNegocio(admc.ObtenerOV(1));
		admo.pch_LevantaCotizaciones();
		
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");

		
	}

}
