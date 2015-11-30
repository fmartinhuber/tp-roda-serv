package test2;

import java.rmi.RemoteException;
import java.util.*;

import org.hibernate.mapping.Array;

import negocio.*;
import controlador.*;
import dao.ClienteDAO;
import dto.*;
import utils.*;
import xml2.*;

/**
 * @author Daro: Desde aca voy a hacer pruebas unitarias de lo que tengo que hacer yo
 * Esto se puede borrar para la ultima entrega
 * NO TOCAR!
 */

public class TestDaro {

	public static void main(String[] args)  throws RemoteException {
		/*----------------------------------------------------------------------------------------------------*/
//		/*----------------------------------------------------------------------------------------------------*/
//						/*Pruebas de Cotizaciones, diferentes estados y su actualizacion en la BD*/
//		/*----------------------------------------------------------------------------------------------------*/
//		/*----------------------------------------------------------------------------------------------------*/
//		
//		//Creo lista de utils (esto va a ser lo que se reciba de la web cuando el cliente solicita rodamientos)
//		List <ItemDto> listaUtils = new ArrayList<ItemDto>();
//		//Creo Rodamientos
//		RodamientoDto rodaUno = new RodamientoDto();
//		RodamientoDto rodaDos = new RodamientoDto();
//		//Solamente le asigno los valores: Codigo, Origen y Marca, que va a ser por cuales lo busque
//		rodaUno.setCodigo("20210");
//		rodaUno.setOrigen("Suecia");
//		rodaUno.setMarca("SKF");
//		rodaDos.setCodigo("22207");
//		rodaDos.setOrigen("Argentina");
//		rodaDos.setMarca("IMP");
//		//Agrego el rodamiento y su cantidad a la lista de items
//		ItemDto itemNegUno = new ItemDto(rodaUno, 4);
//		ItemDto itemNegDos = new ItemDto(rodaDos, 7);
//		//Agrego los items a la lista
//		listaUtils.add(itemNegUno);
//		listaUtils.add(itemNegDos);
//		
//		//Creo un Cliente
//		ClienteDto miClienteDto = new ClienteDto();
//		ClienteNegocio miCliNeg = new ClienteNegocio();
//		//Obtengo el Cliente de la BD
//		miCliNeg = ClienteDAO.getInstancia().buscarClientePorCUIT("30-22222222-3");
//		miClienteDto = miCliNeg.aClienteDto();
//		
//		//Llamo al metodo para que me genere la Cotizacion
//		int idMaximoCot = AdministracionOV.getInstancia().crearCotizacion(listaUtils, miClienteDto);
//		
//		//miCotDto es la Cotizacion pendiente, la misma no se guarda en la Base de Datos porque no tiene items
//		
//		//Analizar los resultados de la cotizacion
//		
//		//Aprobamos la Cotizacion junto a la lista de items
//		float totalCotizacion;
//		//totalCotizacion = AdministracionOV.getInstancia().aprobarYCotizarCotizacion(idMaximoCot);
//		//System.out.println("El total de la cotizacion es de: $" + totalCotizacion);
//		
//		
//		
		/*----------------------------------------------------------------------------------------------------*/
		/*----------------------------------------------------------------------------------------------------*/
					/*Pruebas de funcionamiento de Cotizaciones a XML y de XML a Cotizaciones*/
		/*----------------------------------------------------------------------------------------------------*/
		/*----------------------------------------------------------------------------------------------------*/
	
//		ClienteNegocio miCliNeg = new ClienteNegocio("Juancho SRL", "NOOOOOOOO@PRUEBA_NO.COM", "20341085110");
//		ItemCotizacionNegocio misItemsNegUno = new ItemCotizacionNegocio();
//		ItemCotizacionNegocio misItemsNegDos = new ItemCotizacionNegocio();
//		RodamientoNegocio roda01 = new RodamientoNegocio(); roda01.setTipo("Rodamientos de rodillos a rotula"); roda01.setCodigo("20210"); roda01.setStock(0); roda01.setOrigen("Suecia"); roda01.setMonto((float) 5317.96); roda01.setCaracteristica("20210 TN9  . . . . . . . . . .SKF       Rodamiento");
//		misItemsNegUno.setRodamiento(roda01);
//		misItemsNegUno.setPrecio(119);
//		
//		RodamientoNegocio roda02 = new RodamientoNegocio(); roda02.setTipo("Rodamientos de rodillos a rotula"); roda02.setCodigo("21311 K"); roda02.setStock(0); roda02.setOrigen("Suecia"); roda02.setMonto((float) 3689.45); roda02.setCaracteristica("21311 EK . . . . . . . . . . .SKF       Rodamiento");
//		misItemsNegDos.setRodamiento(roda02);
//		misItemsNegUno.setPrecio(878);
//		
//		List <ItemCotizacionNegocio> listaItemsCotNeg = new ArrayList<ItemCotizacionNegocio>();
//		listaItemsCotNeg.add(misItemsNegUno);
//		listaItemsCotNeg.add(misItemsNegDos);
//		
//		CotizacionNegocio miCotNegocio = new CotizacionNegocio();
//		miCotNegocio.setCliente(miCliNeg);
//		miCotNegocio.setEstado("En espera");
//		Date fechaActual = new Date();
//		miCotNegocio.setFechaCreacion(fechaActual);
//		miCotNegocio.setFechaVigencia(fechaActual);
//		miCotNegocio.setItems(listaItemsCotNeg);
//		
//		//Creo el XML de cotizacion
//		String nombreDelXMdevuelto;
//		nombreDelXMdevuelto = CotizacionXML.getInstancia().cotizacionTOxml(miCotNegocio);
//		System.out.println(nombreDelXMdevuelto);
//		
//		//Obtengo un XML de Cotizacion
//		CotizacionNegocio cotizacionObtenida = new CotizacionNegocio();
//		cotizacionObtenida = CotizacionXML.getInstancia().xmlTOcotizacion("cotizacion_2015-11-15_145056.xml");
//		System.out.println(cotizacionObtenida.getEstado());
		
		
		
		/*----------------------------------------------------------------------------------------------------*/
		/*----------------------------------------------------------------------------------------------------*/
			/*Generacion del XML de RodamientosProveedores para que sea obtenido por la Lista Comparativa*/
		/*----------------------------------------------------------------------------------------------------*/
		/*----------------------------------------------------------------------------------------------------*/
		
		//Realizo la carga de la lista comparativa, generando su XML
		CargarDatosListaComparativa cargaDeDatosAutomatica = new CargarDatosListaComparativa();
		cargaDeDatosAutomatica.cargarListaComparativa();
		/*Obtengo la instancia de Administracion CC, lo que hace esto es crear el objeto, y ahi dentro generar la lista
		comparativa con todos los Rodamientos obtenidos del XML*/
		AdministracionCC.getInstancia();
		
		
		
		/*----------------------------------------------------------------------------------------------------*/
		/*----------------------------------------------------------------------------------------------------*/
													/*Pruebas Bulto*/
		/*----------------------------------------------------------------------------------------------------*/
		/*----------------------------------------------------------------------------------------------------*/
		
		
		
		
	}
		
}
