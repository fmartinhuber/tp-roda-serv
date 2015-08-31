package negocio;

import java.util.*;


public class OrdenCompraNegocio{

	private String formaPago;
	private float total;	
	private float descuento;
	private List <ItemOrdenCompraNegocio> items;
		
	
	
	public OrdenCompraNegocio(String formaPago, float total,
			float descuento, List<ItemOrdenCompraNegocio> items) {
		super();
		this.formaPago = formaPago;
		this.total = total;
		this.descuento = descuento;
		this.items = items;
	}
	
	public OrdenCompraNegocio(){
		
	}

	public String getFormaPago() {
		return formaPago;
	}
	
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	public float getTotal() {
		return total;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	public List<ItemOrdenCompraNegocio> getItems() {
		return items;
	}
	
	public void setItems(List<ItemOrdenCompraNegocio> items) {
		this.items = items;
	}
	
	
}
