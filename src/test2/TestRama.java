package test2;

import java.rmi.RemoteException;
import java.util.*;

import negocio.ClienteNegocio;
import negocio.OrdenCompraNegocio;
import negocio.SolicitudCompraNegocio;
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
				
		// CREAR ORDEN COMPRA
		// TODO:
		
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
		
		//TODO RAMA: Aca consultar el cliente de la base por CUIT, de esa forma viene bien y no se carga uno nuevo
		
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
		
		ClienteDto cliente = new ClienteDto();
		cliente.setCUIT("30-11111111-2");
		cliente.setMail("compras@mecind.com.ar");
		cliente.setRazonSocial("Mecanica Industrial SRL");
		
		AdministracionCC.getInstancia().crearRemito(listaOrdenesDto, cliente);
		System.out.println("Remito creado");
		
		
		
	}

}
