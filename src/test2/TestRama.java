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
		
//		List<OrdenCompraDto> listaOrdenesDto = new ArrayList<OrdenCompraDto>();
		
		//List<OrdenCompraNegocio> listaOrdenes = OrdenCompraDAO.getinstancia().obtenerOrdenCompra();
		//Generamos 2 ordenes de compra con unicamente sus id
		List<OrdenCompraDto> listaOrdenes = new ArrayList<OrdenCompraDto>();
		OrdenCompraDto ordenUno = new OrdenCompraDto();
		OrdenCompraDto ordenDos = new OrdenCompraDto();
		ordenUno.setNumeroOrdenCompra(3);
		ordenDos.setNumeroOrdenCompra(5);
		listaOrdenes.add(ordenUno);
		listaOrdenes.add(ordenDos);

		
		
//		OrdenCompraDto aux = new OrdenCompraDto();
								
//		List<ItemOrdenCompraDto> listaItemsDto = new ArrayList<ItemOrdenCompraDto>();
//		List<ItemOrdenCompraNegocio> listaItemsNegocio = ItemOrdenCompraDAO.getInstancia().listarItemsOrdenCompra();
//		ItemOrdenCompraDto aux2 = new ItemOrdenCompraDto();
				
//		for(negocio.ItemOrdenCompraNegocio i : listaItemsNegocio ){
//			aux2 = new ItemOrdenCompraDto();
//			aux2.setCantidad(i.getCantidad());
//			aux2.setMonto(i.getMonto());	
//			aux2.setRodamiento(i.getRodamiento().aRodamientoDto());
//			listaItemsDto.add(aux2);
//		}
		
//		for(negocio.OrdenCompraNegocio o : listaOrdenes){
//			aux = new OrdenCompraDto();
//			aux.setDescuento(o.getDescuento());
//			aux.setEstado(o.getEstado());
//			aux.setFormaPago(o.getFormaPago());
//			aux.setTotal(o.getTotal());
//			aux.setNumeroOrdenCompra(o.getIdOrdenCompra());
//			aux.setProveedor(proveedor);
//			
//			aux.setItems(listaItemsDto);
//			
//			listaOrdenesDto.add(aux);
//		}				
		
		AdministracionCC.getInstancia().crearRemito(listaOrdenes, proveedor);
		System.out.println("Remito creado");
		
		/*********************************************/
		
		// CREAR ORDEN COMPRA
		
//		String formaDePago = "efectivo";
//		
//		List<SolicitudCompraDto> listaCotizacionesDto = new ArrayList<SolicitudCompraDto>();
//		SolicitudCompraDto aux = new SolicitudCompraDto();		
//		List<SolicitudCompraNegocio> listaCotizacionesNegocioAprobadas = SolicitudCompraDAO.getInstancia().listarSolicitudesCompraAprobadas();
//		for(negocio.SolicitudCompraNegocio s : listaCotizacionesNegocioAprobadas){
//			aux = new SolicitudCompraDto();
//			aux.setEstado(s.getEstado());
//			aux.setNumeroSolicitudCompra(s.getId());								
//			listaCotizacionesDto.add(aux);
//		}
//						
//		AdministracionCC.getInstancia().crearOrdenCompra(listaCotizacionesDto, formaDePago);
//		System.out.println("Orden de compra creada");
		
	}

}
