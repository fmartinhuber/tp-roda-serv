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
		private OrdenPedidoBean ordenPedido;
	private float monto;
	
	
		
	public int getIdItemOrdenCompra() {
		return idItemOrdenCompra;
	}
	public void setIdItemOrdenCompra(int idItemOrdenCompra) {
		this.idItemOrdenCompra = idItemOrdenCompra;
	}
	public OrdenPedidoBean getOrdenPedido() {
		return ordenPedido;
	}
	public void setOrdenPedido(OrdenPedidoBean ordenPedido) {
		this.ordenPedido = ordenPedido;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
}
