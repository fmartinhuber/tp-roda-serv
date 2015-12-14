package utils;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import controlador.*;
import dao.OVDAO;
import dto.CotizacionDto;
import dto.SolicitudCompraDto;

public class BatchUtils {
	
	public void batch(){
		Timer timerTask = new Timer();
		
		TimerTask task = new TimerTask(){

			public void run() {
				List<CotizacionDto> cotizaciones;
				try {
						
						int ov = Integer.valueOf(AdministracionOV.getInstancia().obtenerUsuarioLogueado().getOv());
						cotizaciones = AdministracionOV.getInstancia().obtenerCotizacionesAprobadas();
						if(cotizaciones.size()!=0){
							SolicitudCompraDto solicitud = AdministracionOV.getInstancia().crearSolicitudCompra(cotizaciones);
							System.out.println("Se generaron la solicitud: " + solicitud.toString());
						}
						//TODO cual es el que hay que usar? 
						//AdministracionCC.getInstancia().crearOrdenCompra(listaCotizaciones, formaPago);
						//AdministracionCC.getInstancia().crearOrdenCompraXid(idsSolCompra, formaDePago);
						
						
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		//Cada 10 segundos
		 timerTask.schedule(task, 10, 10000);
		 
		 
	}
	 
	
	
}
