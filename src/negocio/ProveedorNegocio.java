package negocio;

import java.util.*;

import javax.persistence.*;

import dao.*;
import dto.*;

@Entity
@Table(name="Proveedor")
public class ProveedorNegocio{

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int IdProveedor;
	private String nombre;
	private String CUIT;
	@OneToMany(mappedBy = "proveedor")
	private List<RodamientoNegocio> rodamientos;
	
	
		
	public ProveedorNegocio(int idProveedor, String nombre, String CUIT,
			List<RodamientoNegocio> rodamientos) {
		super();
		IdProveedor = idProveedor;
		this.nombre = nombre;
		this.CUIT = CUIT;
		this.rodamientos = rodamientos;
	}

	public ProveedorNegocio(){
		
	}
	
	public void aProveedorNegocio(ProveedorDto proveedor){
		this.setNombre(proveedor.getNombre());
		this.setCUIT(proveedor.getCUIT());
	}
	
	public ProveedorDto aProveedorDto() {
		ProveedorDto prov = new ProveedorDto();
		prov.setNombre(this.getNombre());
		prov.setCUIT(this.getCUIT());
	return prov;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<RodamientoNegocio> getRodamientos() {
		return rodamientos;
	}
	
	public void setRodamientos(List<RodamientoNegocio> regulares) {
		rodamientos = regulares;
	}
	
	public void persistirProveedor(){
		ProveedorDAO.getInstancia().persist(this);
	}
	
	public boolean updateProveedor(){
		ProveedorDAO.getInstancia().update(this);
		return false;
	}

	public int getIdProveedor() {
		return IdProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		IdProveedor = idProveedor;
	}

	public String getCUIT() {
		return CUIT;
	}

	public void setCUIT(String CUIT) {
		this.CUIT = CUIT;
	}
	
}
