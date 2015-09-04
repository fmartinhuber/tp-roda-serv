package negocio;

import java.util.*;


public class OrdenCompraNegocio{

	private String formaPago;
	private float total;	
	private float descuento;
	private List <ItemOrdenCompraNegocio> items;
	private ProveedorNegocio proveedor;
	private List<CotizacionNegocio> cotizaciones;
		
	
	
	public OrdenCompraNegocio(String formaPago, float total,
			float descuento, List<ItemOrdenCompraNegocio> items,
			ProveedorNegocio prov, List<CotizacionNegocio> cotizaciones) {
		super();
		this.formaPago = formaPago;
		this.total = total;
		this.descuento = descuento;
		this.items = items;
		this.proveedor = prov;
		this.cotizaciones = cotizaciones;
	}
	
	public OrdenCompraNegocio(){
		
	}

	public String getFormaPago() {
		return formaPago;
	}
	
	public ProveedorNegocio getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorNegocio proveedor) {
		this.proveedor = proveedor;
	}

	public List<CotizacionNegocio> getCotizaciones() {
		return cotizaciones;
	}

	public void setCotizaciones(List<CotizacionNegocio> cotizaciones) {
		this.cotizaciones = cotizaciones;
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
