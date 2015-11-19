package test2;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import controlador.*;
import dto.*;

public class TestRama {

	public static void main(String[] args) throws RemoteException{
		
		//AdministracionOV c = new AdministracionOV();
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
			
//		System.out.println("Cargamos Datos");
//		CargarDatos.getInstance().cargaDeDatos();
//		System.out.println("Carga Finalizada");
		
		AdministracionCC cc = new AdministracionCC();
		
		List<utils.ItemDto> listaItems = new ArrayList<utils.ItemDto>();	
		RodamientoDto roda1 = new RodamientoDto();
		RodamientoDto roda2 = new RodamientoDto();
		roda1.setCodigo("20210");
		roda1.setOrigen("Suecia");
		roda1.setMarca("SKF");		
		roda2.setCodigo("21311 K");
		roda2.setOrigen("Suecia");
		roda2.setMarca("SKF");
		utils.ItemDto itemNeg1 = new utils.ItemDto(roda1, 2);
		utils.ItemDto itemNeg2 = new utils.ItemDto(roda2, 3);
		listaItems.add(itemNeg1);
		listaItems.add(itemNeg2);
		
		
		cc.actualizarStock(listaItems, "sumar");
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		System.out.println("Se actualizo el stock");

	}

}
