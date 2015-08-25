package bean;

import java.util.*;
import javax.persistence.*;



@Entity
@Table(name="Proveedor")
public class ProveedorBean{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int IdProveedor;
	private String nombre;
	@OneToMany (cascade=CascadeType.ALL)
	@JoinColumn(name="rodamiento_proveedor")
		private List<RodamientoBean> Rodamientos;
	
	
	
	public int getIdProveedor() {
		return IdProveedor;
	}
	
	public void setIdProveedor(int idProveedor) {
		IdProveedor = idProveedor;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<RodamientoBean> getRodamientos() {
		return Rodamientos;
	}
	
	public void setRodamientos(List<RodamientoBean> rodamientos) {
		Rodamientos = rodamientos;
	}
	
	
}
