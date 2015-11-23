package test2;

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
	 */

	public static void main(String[] args) {
		
		AdministracionOV admo = new AdministracionOV();
		AdministracionCC admc = new AdministracionCC();
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		
//		System.out.println("Prueba Charly - Cargamos Datos");
//		CargarDatos.getInstance().cargaDeDatos();
//		System.out.println("Prueba Charly - Carga Finalizada");	
		
		// Seteamos la OV con la que trabajaremos
		
		admo.setOficinaVentaNegocio(admc.ObtenerOV(1));
		admo.pch_LevantaCotizaciones();
		
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");

		
	}

}
