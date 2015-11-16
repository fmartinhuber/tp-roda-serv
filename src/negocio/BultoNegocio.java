package negocio;

import java.util.*;



public class BultoNegocio {

	private List <RodamientoNegocio> rodamientos;
	private ClienteNegocio cliente;
	private RemitoNegocio remito;
	
	
	
	public BultoNegocio(List<RodamientoNegocio> rodamientos, ClienteNegocio cliente,
			RemitoNegocio remito) {
		super();
		this.rodamientos = rodamientos;
		this.cliente = cliente;
		this.remito = remito;
	}
	
	public BultoNegocio(){
		
	}

	public List<RodamientoNegocio> getRodamientos() {
		return rodamientos;
	}

	public void setRodamientos(List<RodamientoNegocio> rodamientos) {
		this.rodamientos = rodamientos;
	}

	public ClienteNegocio getCliente() {
		return cliente;
	}

	public void setCliente(ClienteNegocio cliente) {
		this.cliente = cliente;
	}

	public RemitoNegocio getRemito() {
		return remito;
	}

	public void setRemito(RemitoNegocio remito) {
		this.remito = remito;
	}
	

}
