package utils;

import negocio.*;

public class ItemNegocio {
	private RodamientoNegocio rodamiento;
	private int cantidad;
	
	
	public ItemNegocio(RodamientoNegocio rodamiento, int cantidad) {
		this.rodamiento = rodamiento;
		this.cantidad = cantidad;
	}
	
	public ItemNegocio(){
		
	};
	
	public RodamientoNegocio getRodamiento() {
		return rodamiento;
	}
	public void setRodamiento(RodamientoNegocio rodamiento) {
		this.rodamiento = rodamiento;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}	
}
