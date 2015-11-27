package test2;

import java.rmi.*;
import java.util.*;

import controlador.*;
import dto.*;

public class TestAplication {

	public static void main(String[] args) throws RemoteException {

		System.out.println("Cargamos Datos");
		CargarDatos.getInstance().cargaDeDatos();
		System.out.println("Carga Finalizada");
		
		/*Daro: Rama hace tus pruebas en testRama, dejame este puramente para levantar la base con datos, 
		hasta que hagamos que quede bien*/
//		List<utils.ItemDto> listaItems = new ArrayList<utils.ItemDto>();	
//		RodamientoDto roda1 = new RodamientoDto();
//		RodamientoDto roda2 = new RodamientoDto();
//		int cantidad = 5;
//		roda1.setCodigo("20210");
//		roda1.setOrigen("Suecia");
//		roda1.setMarca("SKF");		
//		roda2.setCodigo("21311 K");
//		roda2.setOrigen("Suecia");
//		roda2.setMarca("SKF");
//		utils.ItemDto itemNeg1 = new utils.ItemDto(roda1, cantidad);
//		utils.ItemDto itemNeg2 = new utils.ItemDto(roda2, cantidad);
//		listaItems.add(itemNeg1);
//		listaItems.add(itemNeg2);
//		
//		AdministracionCC.getInstancia().actualizarStock(listaItems, "suma");

		
	}

}
