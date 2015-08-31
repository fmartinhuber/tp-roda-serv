package bean;

import javax.persistence.*;



@Entity
@Table(name="ItemCotizacion")
public class ItemCotizacionBean{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idItemCotizacion; 
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="item_rodamiento")
	private RodamientoBean rodamiento;
	private int cant;
	private float subtotal;
	
		
	
	public int getIdItemCotizacion() {
		return idItemCotizacion;
	}
	
	public void setIdItemCotizacion(int idItemCotizacion) {
		this.idItemCotizacion = idItemCotizacion;
	}
	
	public float getSubtotal() {
		return subtotal;
	}
	
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	public RodamientoBean getRodamiento() {
		return rodamiento;
	}
	
	public void setRodamiento(RodamientoBean rodamiento) {
		this.rodamiento = rodamiento;
	}
	
	public int getCant() {
		return cant;
	}
	
	public void setCant(int cant) {
		this.cant = cant;
	}
	
	
}
