package test2;

import java.rmi.RemoteException;
import java.util.*;

import negocio.*;
import controlador.*;
import dao.*;
import dto.*;

public class TestRama {

	public static void main(String[] args) throws RemoteException{
					
		// CREAR REMITO		
		
		ProveedorDto proveedor = new ProveedorDto();
		proveedor.setCUIT("20-11111111-1");
		proveedor.setNombre("BPB Solucines en Movimiento");		
			
		//Generamos 2 ordenes de compra con unicamente sus id
		List<OrdenCompraDto> listaOrdenes = new ArrayList<OrdenCompraDto>();
		List<ItemOrdenCompraDto> listaItemsOrdenesUno = new ArrayList<ItemOrdenCompraDto>();
		List<ItemOrdenCompraDto> listaItemsOrdenesDos = new ArrayList<ItemOrdenCompraDto>();
		RodamientoDto rodamiento1 = new RodamientoDto();
		RodamientoDto rodamiento2 = new RodamientoDto();
		rodamiento1.setCodigo("20210");
		rodamiento1.setMarca("SKF");
		rodamiento1.setOrigen("Suecia");
		rodamiento2.setCodigo("21311 K");
		rodamiento2.setMarca("SKF");
		rodamiento2.setOrigen("Suecia");
		
		OrdenCompraDto ordenUno = new OrdenCompraDto();
		OrdenCompraDto ordenDos = new OrdenCompraDto();
		ordenUno.setNumeroOrdenCompra(2);
		ordenDos.setNumeroOrdenCompra(4);	
		
		ItemOrdenCompraDto itemUno = new ItemOrdenCompraDto();
		ItemOrdenCompraDto itemDos = new ItemOrdenCompraDto();
		
		itemUno.setCantidad(3);
		itemUno.setRodamiento(rodamiento1);
		itemDos.setCantidad(4);
		itemDos.setRodamiento(rodamiento2);
	
		listaItemsOrdenesUno.add(itemUno);
		listaItemsOrdenesDos.add(itemDos);
		
		ordenUno.setItems(listaItemsOrdenesUno);
		ordenDos.setItems(listaItemsOrdenesDos);
		
		listaOrdenes.add(ordenUno);
		listaOrdenes.add(ordenDos);
		
		//AdministracionCC.getInstancia().crearRemito(listaOrdenes, proveedor);
		AdministracionCC.getInstancia().crearRemito(listaOrdenes);
		System.out.println("Remito creado");			
		

		
//		AdministracionCC.getInstancia().aprobarOrdenCompra(3);
		
	}

}
