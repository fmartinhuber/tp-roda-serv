package bean;

import javax.persistence.*;



@Entity
@Table(name="ItemOrdenPedido")
public class ItemOrdenPedidoBean{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int idItemOrdenPedido;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="itemOP_itemC")
		private ItemCotizacionBean item;
		
	
	
	public int getIdItemOrdenPedido() {
		return idItemOrdenPedido;
	}
	public void setIdItemOrdenPedido(int idItemOrdenPedido) {
		this.idItemOrdenPedido = idItemOrdenPedido;
	}
	public ItemCotizacionBean getItem() {
		return item;
	}
	public void setItem(ItemCotizacionBean item) {
		this.item = item;
	}
}
