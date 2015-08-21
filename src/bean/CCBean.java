package bean;

import java.util.*;
import javax.persistence.*;


@Entity
@Table(name="Casa Central")
public class CCBean{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int idCasaCentral;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CasaCentral_OrdenCompra")
		private List <OrdenCompraBean> ordenesP;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CasaCentral_Rodamientos")
		private List <RodamientoBean> rodamientos;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CasaCentral_ListaRodamientos")
		private List <RodamientoBean> listaComparativa ;
	
	
	
	public int getIdCasaCentral() {
		return idCasaCentral;
	}
	public void setIdCasaCentral(int idCasaCentral) {
		this.idCasaCentral = idCasaCentral;
	}
	public List<OrdenCompraBean> getOrdenesP() {
		return ordenesP;
	}
	public void setOrdenesP(List<OrdenCompraBean> ordenesP) {
		this.ordenesP = ordenesP;
	}
	public List<RodamientoBean> getRodamientos() {
		return rodamientos;
	}
	public void setRodamientos(List<RodamientoBean> rodamientos) {
		this.rodamientos = rodamientos;
	}
	public List <RodamientoBean> getListaComparativa() {
		return listaComparativa;
	}
	public void setListaComparativa(List <RodamientoBean> listaComparativa) {
		this.listaComparativa = listaComparativa;
	}
}
