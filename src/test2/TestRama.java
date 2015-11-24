package test2;

import java.rmi.RemoteException;
import java.util.*;

import negocio.ClienteNegocio;
import controlador.*;
import dao.*;
import dto.*;

public class TestRama {

	public static void main(String[] args) throws RemoteException{
		
		//AdministracionOV c = new AdministracionOV();
		
		System.out.println("===================================================================================");
		System.out.println("===================================================================================");
			
//		System.out.println("Cargamos Datos");
//		CargarDatos.getInstance().cargaDeDatos();
//		System.out.println("Carga Finalizada");
		
		// ACTUALIZAR STOCK
		
//		List<utils.ItemDto> listaItems = new ArrayList<utils.ItemDto>();	
//		RodamientoDto roda1 = new RodamientoDto();
//		RodamientoDto roda2 = new RodamientoDto();
//		int cantidad = 5;		
//		roda1.setCodigo("20210");
//		roda1.setOrigen("Suecia");
//		roda1.setMarca("SKF");		
//		roda2.setCodigo("21311 K");
//		roda2.setOrigen("Suecia");
//		roda2.setMarca("SKF");
//		utils.ItemDto itemNeg1 = new utils.ItemDto(roda1, cantidad);
//		utils.ItemDto itemNeg2 = new utils.ItemDto(roda2, cantidad);
//		listaItems.add(itemNeg1);
//		listaItems.add(itemNeg2);
		
//		cc.actualizarStock(listaItems, "suma");
		
		// ACTUALIZAR STOCK
		
		// CREAR REMITO
		
		//TODO RAMA: Aca consultar el cliente de la base por CUIT, de esa forma viene bien y no se carga uno nuevo
		
//		List<OrdenCompraDto> listaOrdenes = new ArrayList<OrdenCompraDto>();
//		listaOrdenes = null;
//		
//		ClienteDto cliente = new ClienteDto();
//		cliente.setCUIT("30-11111111-2");
//		cliente.setMail("compras@mecind.com.ar");
//		cliente.setRazonSocial("Mecanica Industrial SRL");
//		
//		AdministracionCC.getInstancia().crearRemito(listaOrdenes, cliente);
//		System.out.println("Remito creado");
		
		// CREAR REMITO
		
		// CREAR ORDEN COMPRA
		
		String formaDePago = "efectivo";
		List<SolicitudCompraDto> listaCotizaciones = new ArrayList<SolicitudCompraDto>();	
				
		AdministracionCC.getInstancia().crearOrdenCompra(listaCotizaciones, formaDePago);
		System.out.println("Orden de compra creada");
		
		// CREAR ORDEN COMPRA
		

	}

}
