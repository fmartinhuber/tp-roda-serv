package negocio;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="OrdenCompra")
public class OrdenCompraNegocio{

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrdenCompra;
	private String formaPago;
	private float total;
	private float descuento;
	private String estado;

	//@OneToMany(fetch=FetchType.EAGER, mappedBy="club", cascade=CascadeType.ALL)
	//@Fetch(FetchMode.SUBSELECT)
	@OneToMany (cascade=CascadeType.ALL)
	@JoinColumn(name="orden_item")
	private List <ItemOrdenCompraNegocio> items;

	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private ProveedorNegocio proveedor;
	
	@ManyToMany(cascade=CascadeType.ALL)
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
