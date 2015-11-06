package dto;

import java.io.Serializable;



public class ItemCotizacionDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private RodamientoDto rodamiento;
	private int cant;
	private float precio;
	
	
		
	public ItemCotizacionDto(RodamientoDto rodamiento, int cant, float precio) {
		super();
		this.rodamiento = rodamiento;
		this.cant = cant;
		this.setPrecio(precio);
	}

	public ItemCotizacionDto(){
		
	}

	public RodamientoDto getRodamiento() {
		return rodamiento;
	}
	
	public void setRodamiento(RodamientoDto rodamiento) {
		this.rodamiento = rodamiento;
	}
	
	public int getCant() {
		return cant;
	}
	
	public void setCant(int cant) {
		this.cant = cant;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}
