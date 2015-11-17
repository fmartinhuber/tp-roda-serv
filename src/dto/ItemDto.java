package dto;

import dto.*;

public class ItemDto {
	private RodamientoDto rodamiento;
	private int cantidad;
	
	
	public ItemDto(RodamientoDto rodamiento, int cantidad) {
		this.rodamiento = rodamiento;
		this.cantidad = cantidad;
	}
	
	public ItemDto(){};


	public RodamientoDto getRodamiento() {
		return rodamiento;
	}
	public void setRodamiento(RodamientoDto rodamiento) {
		this.rodamiento = rodamiento;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}	
}
