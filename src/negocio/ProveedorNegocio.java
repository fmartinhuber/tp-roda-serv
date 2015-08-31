package negocio;

import java.util.*;



public class ProveedorNegocio{

	private String nombre;
	private List<RodamientoNegocio> Rodamientos;
	
	
	
	public ProveedorNegocio(String nombre, List<RodamientoNegocio> rodamientos) {
		super();
		this.nombre = nombre;
		Rodamientos = rodamientos;
	}
	
	public ProveedorNegocio(){
		
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<RodamientoNegocio> getRegulares() {
		return Rodamientos;
	}
	
	public void setRegulares(List<RodamientoNegocio> regulares) {
		Rodamientos = regulares;
	}
	
	
}
