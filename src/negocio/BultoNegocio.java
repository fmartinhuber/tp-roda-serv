package negocio;

import java.util.*;

import javax.persistence.*;

import utils.ItemNegocio;


@Entity
@Table(name="Bulto")
public class BultoNegocio {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idBulto;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="bulto_itemRodamiento")
	private List <ItemNegocio> itemRodamiento;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bulto_cliente")
	private ClienteNegocio cliente;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bulto_remito")
	private RemitoNegocio remito;
	
	public BultoNegocio(List<ItemNegocio> itemRodamiento, ClienteNegocio cliente, RemitoNegocio remito) {
		super();
		this.itemRodamiento = itemRodamiento;
		this.cliente = cliente;
		this.remito = remito;
	}
	
	public BultoNegocio() {
		
	}
	
	public List<ItemNegocio> getItemRodamiento() {
		return itemRodamiento;
	}

	public void setItemRodamiento(List<ItemNegocio> itemRodamiento) {
		this.itemRodamiento = itemRodamiento;
	}

	public ClienteNegocio getCliente() {
		return cliente;
	}

	public void setCliente(ClienteNegocio cliente) {
		this.cliente = cliente;
	}

	public RemitoNegocio getRemito() {
		return remito;
	}

	public void setRemito(RemitoNegocio remito) {
		this.remito = remito;
	}
		

}
