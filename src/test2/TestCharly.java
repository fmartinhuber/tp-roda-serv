package test2;

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
		
		
		//c.procesarCotizaciones(1);
		
		//List<Integer> parametros = new ArrayList<Integer>();
		//parametros.add(1);
		//parametros.add(2);
		//c.Prueba(parametros);
		//c.GenerarFactura(parametros, 1);
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");

		
	}

}
