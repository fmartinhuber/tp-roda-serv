package test2;

import java.rmi.RemoteException;
import java.util.*;

import negocio.*;
import controlador.*;
import dao.ClienteDAO;
import dao.CotizacionDAO;
import dto.*;
import utils.*;

/**
 * @author Daro: Desde aca voy a hacer pruebas unitarias de lo que tengo que hacer yo
 * Esto se puede borrar para la ultima entrega
 * NO TOCAR!
 */

public class TestDaro {

	@SuppressWarnings("static-access")
	public static void main(String[] args)  throws RemoteException {
//		/*----------------------------------------------------------------------------------------------------*/
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
//		//Creo la cotizacion a devolver
//		@SuppressWarnings("unused")
//		CotizacionDto miCotDto = new CotizacionDto();
//		
//		//IMPORTANTE: Para simular el login seteo el numero OV en 1
//		AdministracionOV.getInstancia().setNumeroOv(1);
//		
//		//Paso 1)
//		//Llamo al metodo para que me genere la Cotizacion
//		miCotDto = AdministracionOV.getInstancia().crearCotizacion(listaUtils, miClienteDto);
//		//Analizar los resultados de la cotizacion generada (debe estar en estado "Pendiente" y generar sus itemCotizacion)
//		
//		//Paso 2)
//		//Cotizamos la cotizacion recien generada 
//		float totalCotizacion;
//		totalCotizacion = AdministracionOV.getInstancia().cotizarCotizacion(CotizacionDAO.getinstancia().obtenerMaximoIDCotizacion());
//		System.out.println("El total de la cotizacion es de: $" + totalCotizacion);
//		//Analizar los resultados de la cotizacion cotizada (debe cambiarse el estado a "Cotizada" y devolver su cotizacion)
//		
//		//Paso 3)
//		//Aprobamos la cotizacion recien cotizada
//		AdministracionOV.getInstancia().aprobarCotizacion(CotizacionDAO.getinstancia().obtenerMaximoIDCotizacion());
//		//Analizar los resultados de la cotizacion aprobada (debe cambiarse el estado a "Aprobada")
//		
//		//Si no cambio la carga de datos y es la primera que generas se genera la cotizacion nro 17
//		
//		
//		/*----------------------------------------------------------------------------------------------------*/
//		/*----------------------------------------------------------------------------------------------------*/
//											/*Pruebas Solicitudes de Compra*/
//								/*Tener descomentada la parte de arriba (Pruebas Cotizaciones)*/
//		/*----------------------------------------------------------------------------------------------------*/
//		/*----------------------------------------------------------------------------------------------------*/
//		
//		//Obtenemos la cotizacionDto de la base
//		CotizacionNegocio miCotNegDeLaBase = new CotizacionNegocio();
//		miCotNegDeLaBase = CotizacionDAO.getinstancia().buscarCotizacion(CotizacionDAO.getinstancia().obtenerMaximoIDCotizacion());
//		
//		//Creo array de Cotizaciones
//		List <CotizacionDto> miListaCotDto = new ArrayList<CotizacionDto>();
//		//Agrego la cotizacion obtenida
//		miListaCotDto.add(miCotNegDeLaBase.aCotizacionDto());
//		
//		//Llamo a Solicitud de Compra
//		AdministracionOV.getInstancia().crearSolicitudCompra(miListaCotDto);
		
		
		
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
		
//		//Realizo la carga de la lista comparativa, generando su XML
//		CargarDatosListaComparativa cargaDeDatosAutomatica = new CargarDatosListaComparativa();
//		cargaDeDatosAutomatica.cargarListaComparativa();
//		/*Obtengo la instancia de Administracion CC, lo que hace esto es crear el objeto, y ahi dentro generar la lista
//		comparativa con todos los Rodamientos obtenidos del XML*/
//		AdministracionCC.getInstancia();
		
				
		//AdministracionCC.getInstancia().aprobarOrdenCompra(6);
		
		
		
		
	}
		
}
