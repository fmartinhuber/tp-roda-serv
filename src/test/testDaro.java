package test;

import java.rmi.RemoteException;
import java.util.*;

import controlador.*;
import dto.*;
import utils.*;

/**
 * @author Daro: Desde aca voy a hacer pruebas unitarias de lo que tengo que hacer yo
 * Esto se puede borrar para la ultima entrega
 * NO TOCAR!
 */

public class testDaro {

	public static void main(String[] args)  throws RemoteException {
		//Creo lista de utils
		List <ItemDto> listaUtils = new ArrayList<ItemDto>();
		//Creo Rodamientos
		RodamientoDto rodaUno = new RodamientoDto();
		RodamientoDto rodaDos = new RodamientoDto();
		//Solamente le asigno los valores: Codigo, Pais y Marca, que va a ser por cuales lo busque
		rodaUno.setCodigo("NJ208");
		rodaUno.setOrigen("Francia");
		rodaUno.setMarca("SNR");
		rodaDos.setCodigo("NJ209");
		rodaDos.setOrigen("Suecia");
		rodaDos.setMarca("SKF");
		//Agrego el rodamiento y su cantidad a la lista de items
		ItemDto itemNegUno = new ItemDto(rodaUno, 4);
		ItemDto itemNegDos = new ItemDto(rodaDos, 7);
		//Agrego los items a la lista
		listaUtils.add(itemNegUno);
		listaUtils.add(itemNegDos);
		
		//Creo un Cliente
		ClienteDto miClienteDto = new ClienteDto();
		//Solamente le asigno los valores: CUIT y Razon Social, que  
		miClienteDto.setCUIT("20345850090");
		miClienteDto.setRazonSocial("Nieto SRL");
		
		//Creo una cotizacionDTO, que va a ser lo que me devuelva el metodo
		CotizacionDto miCotDto = new CotizacionDto();
		
		//Llamo al metodo para que me genere la Cotizacion
		AdministracionOV miAdminOV = new AdministracionOV();
		miCotDto = miAdminOV.crearCotizacion(listaUtils, miClienteDto);
		
		//Analizar los resultados de la cotizacion
	}

}
