package negocio;

import java.io.Serializable;

import javax.persistence.*;

import dto.*;


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
	private float precio;
	
	public ItemCotizacionNegocio(RodamientoNegocio rodamiento, float precio) {
		this.rodamiento = rodamiento;
		this.precio = precio;
	}
	
	public ItemCotizacionNegocio(RodamientoNegocio rodamiento, int cantidad) {
		this.rodamiento = rodamiento;
		this.cant = cantidad;
		this.precio = cantidad*rodamiento.getMonto();
	}

	public ItemCotizacionNegocio(){
		
	}
	
	public void aItemCotizacionNegocio(ItemCotizacionDto miItCotDto) {
		this.setIdItemCotizacion(miItCotDto.getNroRegistro());
		this.setPrecio(miItCotDto.getPrecio());
		this.setCantidad(miItCotDto.getCant());
		RodamientoNegocio roda = new RodamientoNegocio();
		roda.aRodamientoNegocio(miItCotDto.getRodamiento());
		this.setRodamiento(roda);
	}
	
	public ItemCotizacionDto aItemCotizacionDto() {
		ItemCotizacionDto itemCotDTO = new ItemCotizacionDto();
		itemCotDTO.setCant(this.getCantidad());
		itemCotDTO.setPrecio(this.getPrecio());
		itemCotDTO.setRodamiento(this.getRodamiento().aRodamientoDto());
		itemCotDTO.setNroRegistro(this.getIdItemCotizacion());
		return itemCotDTO;
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

	public int getCantidad() {
		return cant;
	}

	public void setCantidad(int cant) {
		this.cant = cant;
	}

}
