package bean;

import java.util.*;
import javax.persistence.*;



@Entity
@Table(name="Cotizacion")
public class CotizacionBean{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int idCotizacion;
	private String estado;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cotizacion_items")
		private List<ItemCotizacionBean> items;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cotizacion_cliente")
		private ClienteBean cliente; 
	private Date fechaCreacion;
	private Date fechaVigencia;
	
	
	
	public ClienteBean getCliente() {
		return cliente;
	}
	
	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemCotizacionBean> getItems() {
		return items;
	}
	
	public void setItems(List<ItemCotizacionBean> items) {
		this.items = items;
	}
	
	public int getIdCotizacion() {
		return idCotizacion;
	}
	
	public void setIdCotizacion(int idCotizacion) {
		this.idCotizacion = idCotizacion;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	
}
