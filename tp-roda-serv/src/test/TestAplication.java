package test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import beans.RodamientoBean;
import controlador.Controlador;

public class TestAplication {

	public static void main(String[] args) throws RemoteException {

		Controlador c = new Controlador();
		
		List<RodamientoBean> aux = new ArrayList<RodamientoBean>();
		aux = c.obtenerRodamientos();
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		
		System.out.println();		
		System.out.println();
				
		for (RodamientoBean r : aux){
			System.out.println("Codigo: " +r.getCodigo() + " \t Marca: " +r.getMarca() + " \t Origen: " +r.getOrigen() + " \t Precio: " + r.getPrecio());
		}
		System.out.println();
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");

	}

}
