package bean;

import java.util.*;
import javax.persistence.*;



@Entity
@Table(name="Remito")
public class RemitoBean{
	
	@Id 
	@GeneratedValue (strategy=GenerationType.AUTO)
		private int idRemito;
	private String estado;
	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="remito_cliente")
		private ClienteBean cliente;
	@OneToMany
	@JoinColumn(name="remito_orden")
		private List <OrdenPedidoBean> ordenes;
	private String comentarios;
	private Date fecha;
	
	
	
	
	public int getIdRemito() {
		return idRemito;
	}
	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public ClienteBean getCliente() {
		return cliente;
	}
	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}
	public List<OrdenPedidoBean> getOrdenes() {
		return ordenes;
	}
	public void setOrdenes(List<OrdenPedidoBean> ordenes) {
		this.ordenes = ordenes;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isConformidad() {
		return conformidad;
	}
	public void setConformidad(boolean conformidad) {
		this.conformidad = conformidad;
	}
	private boolean conformidad;
}
