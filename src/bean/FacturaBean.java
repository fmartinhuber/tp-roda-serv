package bean;

import java.util.*;
import javax.persistence.*;



@Entity
@Table(name="Factura")
public class FacturaBean{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int idFactura;	
	private String estado;
	private Date fecha;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="factura_cliente")
		private ClienteBean cliente;
	@OneToOne
	@JoinColumn(name="factura_pedido")
		private OrdenPedidoBean pedido;
	private float descuento;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="factura_item")
		private List <ItemFacturaBean> items; 
	private float total;
	
	
	
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	public List<ItemFacturaBean> getItems() {
		return items;
	}
	public void setItems(List<ItemFacturaBean> items) {
		this.items = items;
	}
	
	public OrdenPedidoBean getPedido() {
		return pedido;
	}
	public void setPedido(OrdenPedidoBean pedido) {
		this.pedido = pedido;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
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
	public ClienteBean getCliente() {
		return cliente;
	}
	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	
}
