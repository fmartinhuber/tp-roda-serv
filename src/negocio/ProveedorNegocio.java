package negocio;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import dao.*;
import dto.*;

@Entity
@Table(name="Proveedor")
public class ProveedorNegocio implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	
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
	
	public void aProveedorNegocio(dto.ProveedorDto proveedorDto){
		
		this.setCUIT(proveedorDto.getCUIT());
		this.setNombre(proveedorDto.getNombre());
		this.setIdProveedor(proveedorDto.getNroProveedor());		
		
//		List<RodamientoNegocio> rodas = new ArrayList<RodamientoNegocio>();
//		for(int i = 0; i < proveedor.getRodamientos().size(); i++){
//			RodamientoNegocio ro = new RodamientoNegocio();
//			ro.aRodamientoNegocio(proveedor.getRodamientos().get(i));
//			rodas.add(ro);
//		}
//		this.setRodamientos(rodas);
	}
	
	public ProveedorDto aProveedorDto() {
		ProveedorDto prov = new ProveedorDto();
		prov.setNroProveedor(this.getIdProveedor());
		prov.setNombre(this.getNombre());
		prov.setCUIT(this.getCUIT());
//		List<RodamientoDto> rodas = new ArrayList<RodamientoDto>();
//		for(int i = 0; i < this.getRodamientos().size(); i++){
//			RodamientoDto ro = this.getRodamientos().get(i).aRodamientoDto();
//			rodas.add(ro);
//		}
//		prov.setRodamientos(rodas);
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
