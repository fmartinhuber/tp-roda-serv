package entity;

import javax.persistence.*;

@Entity
@Table(name="rodamientos")
public class RodamientoEntity {

	@Id
	private String codigo;
	@Column(columnDefinition="decimal")
	private float precio;
	private String marca;
	private String origen;
	
	public RodamientoEntity(String codigo, float precio, String marca, String origen) {
		super();
		this.codigo = codigo;
		this.precio = precio;
		this.marca = marca;
		this.origen = origen;
	}
	
	public RodamientoEntity(){}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	};
	
	
	
}
