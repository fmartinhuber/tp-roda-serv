package negocio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dto.ItemCotizacionDto;


@Entity
@Table(name="ItemCotizacion")
public class ItemCotizacionNegocio{

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idItemCotizacion; 
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="item_rodamiento")
	private RodamientoNegocio rodamiento;
	private int cant;
	private float subtotal;
	
	
	public ItemCotizacionNegocio(RodamientoNegocio rodamiento, int cant) {
		super();
		this.rodamiento = rodamiento;
		this.cant = cant;
	}
	
	public ItemCotizacionNegocio(){
		
	}
	
	public ItemCotizacionNegocio aItemCotizacionNegocio(ItemCotizacionDto miItCotDto) {
		return null;
	}

	public RodamientoNegocio getRodamiento() {
		return rodamiento;
	}
	
	public void setRodamiento(RodamientoNegocio rodamiento) {
		this.rodamiento = rodamiento;
	}
	
	public int getCant() {
		return cant;
	}
	
	public void setCant(int cant) {
		this.cant = cant;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}



	

}
