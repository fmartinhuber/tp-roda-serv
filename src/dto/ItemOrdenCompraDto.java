package dto;

import java.io.Serializable;



public class ItemOrdenCompraDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private RodamientoDto rodamiento;
	private float monto;
	private int cantidad;
	
	public ItemOrdenCompraDto(CotizacionDto ordenPedido, float monto) {
		super();
		//this.ordenPedido = ordenPedido;
		this.monto = monto;
	}
	
	public ItemOrdenCompraDto(){
		
	}

	public RodamientoDto getOrdenPedido() {
		return rodamiento;
	}

	public void setOrdenPedido(RodamientoDto ordenPedido) {
		this.rodamiento = ordenPedido;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
