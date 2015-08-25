package bean;

import javax.persistence.*;



@Entity
@Table(name="ItemFactura")
public class ItemFacturaBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int idItemFactura;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="item_cotizacion")
		private CotizacionBean cotizacion;
	private float subtotal;
	
	
	
	public int getIdItemFactura() {
		return idItemFactura;
	}
	
	public void setIdItemFactura(int idItemFactura) {
		this.idItemFactura = idItemFactura;
	}
	
	public CotizacionBean getCotizacion() {
		return cotizacion;
	}
	
	public void setCotizacion(CotizacionBean cotizacion) {
		this.cotizacion = cotizacion;
	}
	
	public float getSubtotal() {
		return subtotal;
	}
	
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	
}
