package negocio;

import java.util.*;

import javax.persistence.*;

import dao.CCDAO;

@Entity
@Table(name="CC")
public class CCNegocio{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idCC")
	private int idAdministracionCC;
	private String Nombre;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cc_ov")
	private List <OVNegocio> ovs;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cc_ordenes")
	private List <OrdenCompraNegocio> ordenesP;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cc_proveedores")
	private List <ProveedorNegocio> proveedores;

	@OneToMany(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private List <RodamientoNegocio> rodamientos;
	
	@OneToMany(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private List <RodamientoNegocio> listaPrincipal;
	
	/*@OneToMany(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private List <RodamientoNegocio> listaOpcional;*/

	
	public CCNegocio() {
		//Inicializo todos los array
		this.setOrdenesP(new ArrayList<OrdenCompraNegocio>());
		this.setRodamientos(new ArrayList<RodamientoNegocio>());
		this.setListaPrincipal(new ArrayList<RodamientoNegocio>());
		//this.setListaOpcional(new ArrayList<RodamientoNegocio>());
	}

	public void persistirCC(){
		CCDAO.getInstancia().persist(this);
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


	/*public List<RodamientoNegocio> getListaOpcional() {
		return listaOpcional;
	}


	public void setListaOpcional(List<RodamientoNegocio> listaOpcional) {
		this.listaOpcional = listaOpcional;
	}*/


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public List<OVNegocio> getOvs() {
		return ovs;
	}


	public void setOvs(List<OVNegocio> ovs) {
		this.ovs = ovs;
	}


	public List<ProveedorNegocio> getProveedores() {
		return proveedores;
	}


	public void setProveedores(List<ProveedorNegocio> proveedores) {
		this.proveedores = proveedores;
	}

	public void mergeCC() {
		CCDAO.getInstancia().merge(this);		
	}
	
	public void updateCC() {
		CCDAO.getInstancia().update(this);		
	}
	
	public void persistCC() {
		CCDAO.getInstancia().persist(this);		
	}
	
	public void deleteCC() {
		CCDAO.getInstancia().delete(this);		
	}
	
}