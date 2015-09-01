package negocio;

import dto.ItemCotizacionDto;



public class ItemCotizacionNegocio{

	private RodamientoNegocio rodamiento;
	private int cant;
	private float precio;
	
	
	public ItemCotizacionNegocio(RodamientoNegocio rodamiento, int cant) {
		super();
		this.rodamiento = rodamiento;
		this.cant = cant;
	}
	
	public ItemCotizacionNegocio(){
		
	}
	
	public ItemCotizacionNegocio transformarItemCotizacionDtoAItemCotizacionNegocio(ItemCotizacionDto miItCotDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public RodamientoNegocio getRodamiento() {
		return rodamiento;
	}
	
	public void setRodamiento(RodamientoNegocio rodamiento) {
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
