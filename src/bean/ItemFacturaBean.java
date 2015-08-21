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
	
	
}
