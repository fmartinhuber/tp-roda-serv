package utils;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import controlador.AdministracionOV;
import dto.CotizacionDto;
import dto.SolicitudCompraDto;

public class BatchUtils {
	
	public void batch(){
		Timer timerTask = new Timer();
		
		TimerTask task = new TimerTask(){

			public void run() {
				List<CotizacionDto> cotizaciones;
				try {
						cotizaciones = AdministracionOV.getInstancia().obtenerCotizacionesAprobadas();
						SolicitudCompraDto solicitud = AdministracionOV.getInstancia().crearSolicitudCompra(cotizaciones);
						System.out.println("Se aprobaron: " + solicitud.toString());
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		//Cada 10 segundos
		 timerTask.schedule(task, 10, 10000);
		 
		 
	}
	 
	
	
}
