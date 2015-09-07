package bean;

import java.util.List;

import dto.OrdenCompraDto;

public class CCBean {

	
	
	private List <RodamientoBean> listaPrincipal;
	
	private List <RodamientoBean> listaOpcional;

	private List <OrdenCompraDto> ordenesP;
	
	public List <RodamientoBean> getListaPrincipal() {
		return listaPrincipal;
	}

	public void setListaPrincipal(List <RodamientoBean> listaPrincipal) {
		this.listaPrincipal = listaPrincipal;
	}

	public List <RodamientoBean> getListaOpcional() {
		return listaOpcional;
	}

	public void setListaOpcional(List <RodamientoBean> listaOpcional) {
		this.listaOpcional = listaOpcional;
	}

	public List <OrdenCompraDto> getOrdenesP() {
		return ordenesP;
	}

	public void setOrdenesP(List <OrdenCompraDto> ordenesP) {
		this.ordenesP = ordenesP;
	}
	
	
}
