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
	private float precio;
	
	public ItemCotizacionNegocio(RodamientoNegocio rodamiento, float precio) {
		this.rodamiento = rodamiento;
		this.precio = precio;
	}

	public ItemCotizacionNegocio(){
		
	}
	
	public ItemCotizacionNegocio aItemCotizacionNegocio(ItemCotizacionDto miItCotDto) {
		return null;
	}
	
	public ItemCotizacionDto aItemCotizacionDto() {
		return null;
	}

	public RodamientoNegocio getRodamiento() {
		return rodamiento;
	}
	
	public void setRodamiento(RodamientoNegocio rodamiento) {
		this.rodamiento = rodamiento;
	}

	public int getIdItemCotizacion() {
		return idItemCotizacion;
	}

	public void setIdItemCotizacion(int idItemCotizacion) {
		this.idItemCotizacion = idItemCotizacion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
