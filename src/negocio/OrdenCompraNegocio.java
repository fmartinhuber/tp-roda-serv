package negocio;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import dto.*;

@Entity
@Table(name="OrdenCompra")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class OrdenCompraNegocio implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrdenCompra;
	private String formaPago;
	private float total;
	private float descuento;
	private String estado;

	@OneToMany (cascade=CascadeType.ALL)
	@JoinColumn(name="orden_item")
	private List <ItemOrdenCompraNegocio> items;

	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private ProveedorNegocio proveedor;
	
	@OneToMany (cascade=CascadeType.ALL)
	@JoinColumn(name="orden_solicitud")
	private List<SolicitudCompraNegocio> solicitudesCompra;
	
		
	public OrdenCompraNegocio(int idOrdenCompra, String formaPago, float total, float descuento, String estado,
			List<ItemOrdenCompraNegocio> items, ProveedorNegocio proveedor,
			List<SolicitudCompraNegocio> solicitudesCompra) {
		super();
		this.idOrdenCompra = idOrdenCompra;
		this.formaPago = formaPago;
		this.total = total;
		this.descuento = descuento;
		this.estado = estado;
		this.items = items;
		this.proveedor = proveedor;
		this.solicitudesCompra = solicitudesCompra;
	}

	public OrdenCompraNegocio(){
		
	}

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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<ItemOrdenCompraNegocio> getItems() {
		return items;
	}

	public void setItems(List<ItemOrdenCompraNegocio> items) {
		this.items = items;
	}

	public ProveedorNegocio getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorNegocio proveedor) {
		this.proveedor = proveedor;
	}

	public List<SolicitudCompraNegocio> getSolicitudesCompra() {
		return solicitudesCompra;
	}

	public void setSolicitudesCompra(List<SolicitudCompraNegocio> solicitudesCompra) {
		this.solicitudesCompra = solicitudesCompra;
	}

	public void aOrdenCompraNegocio(OrdenCompraDto miOrdenDto) {
		
		this.setDescuento(miOrdenDto.getDescuento());
		this.setEstado(miOrdenDto.getEstado());
		this.setFormaPago(miOrdenDto.getFormaPago());
		this.setTotal(miOrdenDto.getTotal());
		List<ItemOrdenCompraNegocio> lista = new ArrayList<ItemOrdenCompraNegocio>();
		// algun for
		this.setItems(lista);

		
	}
	
	
}
