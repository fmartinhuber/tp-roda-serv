package test2;

import java.rmi.*;
import java.util.*;

import controlador.*;
import dto.*;

public class TestAplication {

	public static void main(String[] args) throws RemoteException {

		@SuppressWarnings("unused")
		AdministracionOV ov = AdministracionOV.getInstancia();
		
		@SuppressWarnings("unused")
		List<RodamientoDto> aux = new ArrayList<RodamientoDto>();
		//aux = ov.obtenerRodamientos();
		
	}

}
