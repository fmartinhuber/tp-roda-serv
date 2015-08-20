package bean;

import java.util.List;

import javax.persistence.*;



@Entity
@Table(name="OrdenPedido")
public class OrdenPedidoBean
{
	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	private int numero;
	@OneToMany(cascade=CascadeType.ALL)
	private List <ItemOrdenPedidoBean> items;
	@OneToOne(cascade=CascadeType.ALL)
	private ClienteBean cliente;
	private String estado;
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public List<ItemOrdenPedidoBean> getItems() {
		return items;
	}
	public void setItems(List<ItemOrdenPedidoBean> items) {
		this.items = items;
	}
	public ClienteBean getCliente() {
		return cliente;
	}
	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
