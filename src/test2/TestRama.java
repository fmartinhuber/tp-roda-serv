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
		int cantidad = 5;		
		roda1.setCodigo("20210");
		roda1.setOrigen("Suecia");
		roda1.setMarca("SKF");		
		roda2.setCodigo("21311 K");
		roda2.setOrigen("Suecia");
		roda2.setMarca("SKF");
		utils.ItemDto itemNeg1 = new utils.ItemDto(roda1, cantidad);
		utils.ItemDto itemNeg2 = new utils.ItemDto(roda2, cantidad);
		listaItems.add(itemNeg1);
		listaItems.add(itemNeg2);
				
		// fijens� si el m�todo est� bien. Tratar� de estar entre 23:00 y 23:20 (juega Independiente a las 21:30)
		cc.actualizarStock(listaItems, "suma");
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		System.out.println("Se actualizo el stock");

	}

}
