package test2;

import java.rmi.RemoteException;
import java.util.*;

import negocio.*;
import controlador.*;
import dao.*;
import dto.*;

public class TestRama {

	public static void main(String[] args) throws RemoteException{
	
//		System.out.println("Cargamos Datos");
//		CargarDatos.getInstance().cargaDeDatos();
//		System.out.println("Carga Finalizada");
				
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
		

				
		// CREAR REMITO		
		
		List<OrdenCompraDto> listaOrdenesDto = new ArrayList<OrdenCompraDto>();
		OrdenCompraDto aux = new OrdenCompraDto();
		List<OrdenCompraNegocio> listaOrdenes = OrdenCompraDAO.getinstancia().obtenerOrdenCompra();
		for(negocio.OrdenCompraNegocio o : listaOrdenes){
			aux = new OrdenCompraDto();
			aux.setDescuento(o.getDescuento());
			aux.setEstado(o.getEstado());
			aux.setFormaPago(o.getFormaPago());
			aux.setTotal(o.getTotal());
			aux.setNumeroOrdenCompra(o.getIdOrdenCompra());
			listaOrdenesDto.add(aux);
		}
		
		ProveedorDto proveedor = new ProveedorDto();
		proveedor.setCUIT(null);
		proveedor.setNombre(null);		
		
		AdministracionCC.getInstancia().crearRemito(listaOrdenesDto, proveedor);
		System.out.println("Remito creado");
		
		
		
	}

}
