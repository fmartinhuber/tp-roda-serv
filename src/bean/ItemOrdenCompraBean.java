package bean;

import javax.persistence.*;



@Entity
@Table(name="ItemOrdenCompra")
public class ItemOrdenCompraBean{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int idItemOrdenCompra;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="item_orden")
		private CotizacionBean ordenPedido;
	private float monto;
	
	
	
	public int getIdItemOrdenCompra() {
		return idItemOrdenCompra;
	}
	public void setIdItemOrdenCompra(int idItemOrdenCompra) {
		this.idItemOrdenCompra = idItemOrdenCompra;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
}
