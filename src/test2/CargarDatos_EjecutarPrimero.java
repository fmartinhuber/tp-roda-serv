package test2;

import java.io.IOException;

import controlador.AdministracionCC;

public class CargarDatos_EjecutarPrimero {
	
	public static void main(String[] args) throws IOException {
		

		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		
		System.out.println("Primera Ejecución - Cargamos Datos");
		System.out.println("Recuerde setear 'create-drop' en hinernate.properties");
		System.out.println("Enter para continuar");
		//System.in.read();
		CargarDatos.getInstance().cargaDeDatos();
		System.out.println("Prueba Charly - Carga Finalizada");	
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		/*----------------------------------------------------------------------------------------------------*/
		/*----------------------------------------------------------------------------------------------------*/
			/*Generacion del XML de RodamientosProveedores para que sea obtenido por la Lista Comparativa*/
		/*----------------------------------------------------------------------------------------------------*/
		/*----------------------------------------------------------------------------------------------------*/
		
		System.out.println("Generacion de los XML necesarios en adminCC by Daro");
		//Realizo la carga de la lista comparativa, generando su XML
		CargarDatosListaComparativa cargaDeDatosAutomatica = new CargarDatosListaComparativa();
		cargaDeDatosAutomatica.cargarListaComparativa();
		/*Obtengo la instancia de Administracion CC, lo que hace esto es crear el objeto, y ahi dentro generar la lista
		comparativa con todos los Rodamientos obtenidos del XML*/
		AdministracionCC.getInstancia();
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
		
	}
}
