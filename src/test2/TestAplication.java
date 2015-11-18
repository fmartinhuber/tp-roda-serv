package test2;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import controlador.AdministracionOV;
import dao.CCDAO;
import dto.RodamientoDto;

public class TestAplication {

	public static void main(String[] args) throws RemoteException {

		AdministracionOV ov = AdministracionOV.getInstancia();
		
		List<RodamientoDto> aux = new ArrayList<RodamientoDto>();
		aux = ov.obtenerRodamientos();
		
	}

}
