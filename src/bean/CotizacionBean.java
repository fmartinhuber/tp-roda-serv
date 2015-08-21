package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import dto.CotizacionDto;
import dto.ItemCotizacionDto;



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
	private Date fecha;
	private float total;
	
	
	
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
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

	public CotizacionBean(CotizacionDto c) {
		super();
		this.estado = c.getEstado();
		List <ItemCotizacionBean> items=new ArrayList <ItemCotizacionBean>();
		for(ItemCotizacionDto co:c.getItems())
		{
			ItemCotizacionBean i=new ItemCotizacionBean(co);
			items.add(i);
		}
		this.items = items;
		ClienteBean cl = new ClienteBean (c.getCliente());
		this.cliente = cl;
		this.fecha = c.getFecha();
//		this.total = c.getTotal();
	}
	
	
}
