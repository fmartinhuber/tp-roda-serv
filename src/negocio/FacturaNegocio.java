package negocio;

import java.util.*;

import javax.persistence.*;

import dao.*;

@Entity
@Table(name="Factura")
public class FacturaNegocio{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idFactura;
	private String estado; // Generada // enviada // pagada
	private Date fecha;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="factura_cliente")
	private ClienteNegocio cliente;
	private float descuento;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="factura_item")
	private List <ItemFacturaNegocio> items; 
	private float total;

	//Lo agregue para que se sepa que cotizacion se esta facturando.
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="factura_cotizacion")
	private List<CotizacionNegocio> cotizacion;
	
	public FacturaNegocio(String estado, Date fecha,
			ClienteNegocio cliente, float descuento, List<ItemFacturaNegocio> items,
			float total) {
		super();
		this.estado = estado;
		this.fecha = fecha;
		this.cliente = cliente;
		this.descuento = descuento;
		this.items = items;
		this.total = total;
	}
	
	public FacturaNegocio(){
		
	}

	public float getTotal() {
		return total;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	public List<ItemFacturaNegocio> getItems() {
		return items;
	}
	
	public void setItems(List<ItemFacturaNegocio> items) {
		this.items = items;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public ClienteNegocio getCliente() {
		return cliente;
	}
	
	public void setCliente(ClienteNegocio cliente) {
		this.cliente = cliente;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public List<CotizacionNegocio> getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(List<CotizacionNegocio> cotizacion) {
		this.cotizacion = cotizacion;
	}
	
	public void persistirFactura(){
		FacturaDAO.getInstancia().merge(this);
	}
	
	public void updateFactura(){
		FacturaDAO.getInstancia().update(this);
	}
}
