package negocio;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="CC")
public class CCNegocio {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idCC")
	private int idAdministracionCC;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cc_ordenes")
	private List <OrdenCompraNegocio> ordenesP;
	
	/**
	 *  Actualizar stock propio. (RAMA)
	 *  Se utiliza para manejar el stock interno.
	 */
	@OneToMany(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private List <RodamientoNegocio> rodamientos;
	
	/**
	 * Rodamientos con stock del proveedor. (DARO-MARTIN)
	 */
	@OneToMany(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private List <RodamientoNegocio> listaPrincipal;
	
	/**
	 * Rodamientos con stock del proveedor. (DARO-MARTIN)
	 */
	@OneToMany(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private List <RodamientoNegocio> listaOpcional;

	
	public CCNegocio() {
		
	}


	public int getIdAdministracionCC() {
		return idAdministracionCC;
	}


	public void setIdAdministracionCC(int idAdministracionCC) {
		this.idAdministracionCC = idAdministracionCC;
	}


	public List<OrdenCompraNegocio> getOrdenesP() {
		return ordenesP;
	}


	public void setOrdenesP(List<OrdenCompraNegocio> ordenesP) {
		this.ordenesP = ordenesP;
	}


	public List<RodamientoNegocio> getRodamientos() {
		return rodamientos;
	}


	public void setRodamientos(List<RodamientoNegocio> rodamientos) {
		this.rodamientos = rodamientos;
	}


	public List<RodamientoNegocio> getListaPrincipal() {
		return listaPrincipal;
	}


	public void setListaPrincipal(List<RodamientoNegocio> listaPrincipal) {
		this.listaPrincipal = listaPrincipal;
	}


	public List<RodamientoNegocio> getListaOpcional() {
		return listaOpcional;
	}


	public void setListaOpcional(List<RodamientoNegocio> listaOpcional) {
		this.listaOpcional = listaOpcional;
	}
	
}