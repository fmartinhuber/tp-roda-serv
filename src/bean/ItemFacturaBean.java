package bean;

import javax.persistence.*;

@Entity
@Table(name="ItemFactura")
public class ItemFacturaBean {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int numero;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="item_orden")
	private OrdenPedidoBean orden;  
	private float subtotal;
	public OrdenPedidoBean getOrden() {
		return orden;
	}
	public void setOrden(OrdenPedidoBean orden) {
		this.orden = orden;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
}
