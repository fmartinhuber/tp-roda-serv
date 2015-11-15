package test2;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.sun.security.ntlm.Client;

import negocio.ClienteNegocio;
import negocio.CotizacionNegocio;
import negocio.ItemCotizacionNegocio;
import negocio.RodamientoNegocio;
import controlador.*;
import dto.*;
import utils.*;
import xml.CotizacionXML;

/**
 * @author Daro: Desde aca voy a hacer pruebas unitarias de lo que tengo que hacer yo
 * Esto se puede borrar para la ultima entrega
 * NO TOCAR!
 */

public class TestDaro {

	public static void main(String[] args)  throws RemoteException {
/*		//Creo lista de utils (esto va a ser lo que se reciba de la web cuando el cliente solicita rodamientos)
		List <ItemDto> listaUtils = new ArrayList<ItemDto>();
		//Creo Rodamientos
		RodamientoDto rodaUno = new RodamientoDto();
		RodamientoDto rodaDos = new RodamientoDto();
		//Solamente le asigno los valores: Codigo, Origen y Marca, que va a ser por cuales lo busque
		rodaUno.setCodigo("22310");
		rodaUno.setOrigen("Japon");
		rodaUno.setMarca("ZKL");
		rodaDos.setCodigo("6200F");
		rodaDos.setOrigen("Francia");
		rodaDos.setMarca("SNR");
		//Agrego el rodamiento y su cantidad a la lista de items
		ItemDto itemNegUno = new ItemDto(rodaUno, 4);
		ItemDto itemNegDos = new ItemDto(rodaDos, 7);
		//Agrego los items a la lista
		listaUtils.add(itemNegUno);
		listaUtils.add(itemNegDos);
		
		//Creo un Cliente
		ClienteDto miClienteDto = new ClienteDto();
		//Solamente le asigno los valores: CUIT y Razon Social, va a ser por cual lo busque
		miClienteDto.setCUIT("20345850090");
		miClienteDto.setRazonSocial("Nieto SRL");
		
		//Creo una cotizacionDTO, que va a ser lo que me devuelva el metodo
		CotizacionDto miCotDto = new CotizacionDto();
		
		//Llamo al metodo para que me genere la Cotizacion
		AdministracionOV miAdminOV = new AdministracionOV();
		miCotDto = miAdminOV.crearCotizacion(listaUtils, miClienteDto);
		
		//Analizar los resultados de la cotizacion

*/
		
		
	
		
		//Prueba Cotizacion to XML
		ClienteNegocio miCliNeg = new ClienteNegocio("Juancho SRL", "NOOOOOOOO@PRUEBA_NO.COM", "20341085110");
		ItemCotizacionNegocio misItemsNegUno = new ItemCotizacionNegocio();
		ItemCotizacionNegocio misItemsNegDos = new ItemCotizacionNegocio();
		misItemsNegUno.setCant(4);
		RodamientoNegocio roda01 = new RodamientoNegocio(); roda01.setTipo("Rodamientos de rodillos a rotula"); roda01.setCodigo("20210"); roda01.setStock(0); roda01.setOrigen("Suecia"); roda01.setMonto((float) 5317.96); roda01.setCaracteristica("20210 TN9  . . . . . . . . . .SKF       Rodamiento");
		misItemsNegUno.setRodamiento(roda01);
		misItemsNegUno.setSubtotal(119);
		
		misItemsNegDos.setCant(9);
		RodamientoNegocio roda02 = new RodamientoNegocio(); roda02.setTipo("Rodamientos de rodillos a rotula"); roda02.setCodigo("21311 K"); roda02.setStock(0); roda02.setOrigen("Suecia"); roda02.setMonto((float) 3689.45); roda02.setCaracteristica("21311 EK . . . . . . . . . . .SKF       Rodamiento");
		misItemsNegDos.setRodamiento(roda02);
		misItemsNegUno.setSubtotal(878);
		
		List <ItemCotizacionNegocio> listaItemsCotNeg = new ArrayList<ItemCotizacionNegocio>();
		listaItemsCotNeg.add(misItemsNegUno);
		listaItemsCotNeg.add(misItemsNegDos);
		
		CotizacionNegocio miCotNegocio = new CotizacionNegocio();
		miCotNegocio.setCliente(miCliNeg);
		miCotNegocio.setEstado("En espera");
		Date fechaActual = new Date();
		miCotNegocio.setFechaCreacion(fechaActual);
		miCotNegocio.setFechaVigencia(fechaActual);
		miCotNegocio.setItems(listaItemsCotNeg);
		
		//Creo el XML de cotizacion
		String nombreDelXMdevuelto;
		nombreDelXMdevuelto = CotizacionXML.getInstancia().cotizacionTOxml(miCotNegocio);
		System.out.println(nombreDelXMdevuelto);
		
		//Obtengo un XML de Cotizacion
		CotizacionNegocio cotizacionObtenida = new CotizacionNegocio();
		cotizacionObtenida = CotizacionXML.getInstancia().xmlTOcotizacion("cotizacion_2015-11-15_145056.xml");
		System.out.println(cotizacionObtenida.getEstado());
	}

}
