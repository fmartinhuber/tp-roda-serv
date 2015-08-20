package bean;

import javax.persistence.*;



@Entity
@Table(name="ItemOrdenPedido")
public class ItemOrdenPedidoBean
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int numero;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="itemOP_itemC")
	private ItemCotizacionBean item;
}
