package bean;

import java.util.*;

import javax.persistence.*;



@Entity
@Table(name="OrdenCompra")
public class OrdenCompraBean{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int idOrdenCompra;
	private String formaPago;
	private float total;
	private float descuento;
	@OneToMany (cascade=CascadeType.ALL)
	@JoinColumn(name="orden_item")
		private List <ItemOrdenCompraBean> items;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cotizacion_orden")
		private CotizacionBean cotizacion;
	private String estado;
	
	
	
	public int getIdOrdenCompra() {
		return idOrdenCompra;
	}
	
	public void setIdOrdenCompra(int idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
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
	
	public List<ItemOrdenCompraBean> getItems() {
		return items;
	}
	
	public void setItems(List<ItemOrdenCompraBean> items) {
		this.items = items;
	}

	public CotizacionBean getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(CotizacionBean cotizacion) {
		this.cotizacion = cotizacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
