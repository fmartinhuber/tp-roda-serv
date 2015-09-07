package test;

import java.util.ArrayList;
import java.util.List;

import controlador.AdministracionOV;
import dto.RodamientoDto;

public class TestCharly {
	
	/**
	 * No tocar PORFA!!!
	 * Para pruebas de Charly
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AdministracionOV c = new AdministracionOV();
		
		List<RodamientoDto> aux = new ArrayList<RodamientoDto>();
		aux = c.obtenerRodamientos();
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		
				
		System.out.println("prueba Charly");
		
	
		
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");

		
	}

}
