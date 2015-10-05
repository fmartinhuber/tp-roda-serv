package negocio;

import java.util.List;

import dto.OrdenCompraDto;

public class CCNegocio {

	
	
	private List <RodamientoNegocio> listaPrincipal;
	
	private List <RodamientoNegocio> listaOpcional;

	private List <OrdenCompraDto> ordenesP;
	
	public List <RodamientoNegocio> getListaPrincipal() {
		return listaPrincipal;
	}

	public void setListaPrincipal(List <RodamientoNegocio> listaPrincipal) {
		this.listaPrincipal = listaPrincipal;
	}

	public List <RodamientoNegocio> getListaOpcional() {
		return listaOpcional;
	}

	public void setListaOpcional(List <RodamientoNegocio> listaOpcional) {
		this.listaOpcional = listaOpcional;
	}

	public List <OrdenCompraDto> getOrdenesP() {
		return ordenesP;
	}

	public void setOrdenesP(List <OrdenCompraDto> ordenesP) {
		this.ordenesP = ordenesP;
	}
	
	
}
