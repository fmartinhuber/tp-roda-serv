package dto;

import java.util.*;



public class EnvioAOVDto {

	private List <RodamientoDto> rodamientos;
	private ClienteDto cliente;
	private RemitoDto remito;
	
	
	
	public EnvioAOVDto(List<RodamientoDto> rodamientos, ClienteDto cliente,
			RemitoDto remito) {
		super();
		this.rodamientos = rodamientos;
		this.cliente = cliente;
		this.remito = remito;
	}
	
	public EnvioAOVDto(){
		
	}

	public List<RodamientoDto> getRodamientos() {
		return rodamientos;
	}

	public void setRodamientos(List<RodamientoDto> rodamientos) {
		this.rodamientos = rodamientos;
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public RemitoDto getRemito() {
		return remito;
	}

	public void setRemito(RemitoDto remito) {
		this.remito = remito;
	}
	

}
