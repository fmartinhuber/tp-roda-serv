package utils;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import controlador.*;
import dao.OVDAO;
import dto.CotizacionDto;
import dto.FacturaDto;
import dto.OrdenCompraDto;
import dto.RemitoDto;
import dto.SolicitudCompraDto;

public class BatchUtils {
	
	public void batch(){
		Timer timerTask = new Timer();
		
		TimerTask task = new TimerTask(){

			public void run() {
				List<CotizacionDto> cotizaciones;
				List <OrdenCompraDto> ordensCOmpra;
				try {
						
						int ov = Integer.valueOf(AdministracionOV.getInstancia().obtenerUsuarioLogueado().getOv());
						cotizaciones = AdministracionOV.getInstancia().obtenerCotizacionesAprobadas();
						if(cotizaciones.size()!=0){
							SolicitudCompraDto solicitud = AdministracionOV.getInstancia().crearSolicitudCompra(cotizaciones);
							System.out.println("Se generaron la solicitud: " + solicitud.toString());
						}
//						ordensCOmpra = AdministracionCC.getInstancia().obtenerOrdenesCompra();
//						if(ordensCOmpra.size()!=0){
//							
//							RemitoDto remito = AdministracionCC.getInstancia().crearRemito(ordensCOmpra);
//							if(remito != null){
//								System.out.println("Se generaron el remito: " + remito.getNumeroRemito());
//								AdministracionOV.getInstancia().entregaPedidos(remito);
//								for (OrdenCompraDto ordenCompraDto : remito.getOrdenesDeCompra()) {
//									FacturaDto factura = AdministracionOV.getInstancia().generarFactura(ordenCompraDto.getListaCotizaciones(), AdministracionOV.getInstancia().obtenerUsuarioLogueado());
//									System.out.println("Se generaron la factura: " + factura.getNumeroFactura());
//								}
//								remito.getOrdenesDeCompra().get(1).getListaCotizaciones();
//							}
//							
//							
//						}
						
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		//Cada 10 segundos
		 timerTask.schedule(task, 10, 10000);
		 
		 
	}
	 
	
	
}
