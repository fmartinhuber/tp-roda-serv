package dto;

import java.io.Serializable;

/**
 * @author Martin
 *	Los rodamientos los sacas de los items de cotizacion. 
 */
public class ItemFacturaDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private RodamientoDto rodamiento;  
	private float subtotal;
	private int cantidad; 
	
	public ItemFacturaDto(RodamientoDto rodamiento, float subtotal) {
		super();
		this.rodamiento = rodamiento;
		this.subtotal = subtotal;
	}
	
	public ItemFacturaDto(){
		
	}
	
	public RodamientoDto getOrden() {
		return rodamiento;
	}
	public void setOrden(RodamientoDto rodamiento) {
		this.rodamiento = rodamiento;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
