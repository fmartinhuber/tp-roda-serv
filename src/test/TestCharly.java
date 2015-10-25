package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import negocio.ProveedorNegocio;
import negocio.RodamientoNegocio;
import controlador.AdministracionOV;
import dto.RodamientoDto;

public class TestCharly {
	
	/**
	 * No tocar PORFA!!!
	 * Para pruebas de Charly
	 */

	public static void main(String[] args) {
		
		AdministracionOV c = new AdministracionOV();
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
			
		System.out.println("Prueba Charly - Cargamos Datos");
		CargarDatos.getInstance().cargaDeDatos();
		System.out.println("Prueba Charly - Carga Finalizada");	
		
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");

		
	}

}
