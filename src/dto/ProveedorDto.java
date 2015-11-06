package dto;

import java.io.Serializable;
import java.util.*;



public class ProveedorDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;
	private List<RodamientoDto> Rodamientos;
	
	
	
	public ProveedorDto(String nombre, List<RodamientoDto> rodamientos) {
		super();
		this.nombre = nombre;
		Rodamientos = rodamientos;
	}
	
	public ProveedorDto(){
		
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<RodamientoDto> getRegulares() {
		return Rodamientos;
	}
	
	public void setRegulares(List<RodamientoDto> regulares) {
		Rodamientos = regulares;
	}
	
	
}
