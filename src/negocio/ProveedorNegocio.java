package negocio;

import java.util.*;

import bean.ProveedorBean;
import bean.RodamientoBean;



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
	
	public List<RodamientoNegocio> getRodamientos() {
		return Rodamientos;
	}
	
	public void setRodamientos(List<RodamientoNegocio> regulares) {
		Rodamientos = regulares;
	}
	
	public ProveedorBean proveedorNegocioToBean(){
		ProveedorBean miProveedorBean = new ProveedorBean();
		miProveedorBean.setNombre(this.getNombre());
		List<RodamientoBean> rodamientosBean = new ArrayList<RodamientoBean>();
		for(int i=0; i<this.getRodamientos().size();i++){
			RodamientoBean rodamientoBean = this.getRodamientos().get(i).rodamientoNegocioToBean();
			rodamientosBean.add(rodamientoBean);
		}
		return miProveedorBean;
	}
	
	public ProveedorNegocio proveedorBeanToNegocio(ProveedorBean miProveedorBean){
		this.setNombre(miProveedorBean.getNombre());
		List<RodamientoNegocio> rodamientosNegocio = new ArrayList<RodamientoNegocio>();
		for(int i=0; i<miProveedorBean.getRodamientos().size(); i++){
			RodamientoNegocio rodamientoNegocio = new RodamientoNegocio();
			rodamientoNegocio.rodamientoBeanToNegocio(miProveedorBean.getRodamientos().get(i));
			rodamientosNegocio.add(rodamientoNegocio);
		}
		this.setRodamientos(rodamientosNegocio);
		return this;
	}
}
