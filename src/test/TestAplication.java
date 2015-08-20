package test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import bean.RodamientoBean;
import controlador.AdministracionODV;
import dto.RodamientoDto;

public class TestAplication {

	public static void main(String[] args) throws RemoteException {

		AdministracionODV c = new AdministracionODV();
		
		List<RodamientoDto> aux = new ArrayList<RodamientoDto>();
		aux = c.obtenerRodamientos();
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		
		System.out.println();		
		System.out.println();
				
//		for (RodamientoBean r : aux){
//			System.out.println("Codigo: " +r.getCodigo() + " \t Marca: " +r.getMarca() + " \t Origen: " +r.getOrigen() + " \t Precio: " + r.getPrecio());
//		}
		System.out.println();
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");

	}

}
