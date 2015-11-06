package dto;

import java.io.Serializable;
import java.util.*;



/**
 * 	@author Daro
 *	El estado de la cotizacion puede ser pendiente o aprobada
 *	Si es pendiente: El cliente pidio la cotizacion y el sistema se la genero
 *	Si es aprobada: El cliente aprobo la solicitud (Esto se lo llama en el enunciado "Pedido de Venta")
 */
public class CotizacionDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<ItemCotizacionDto> items;
	private String estado;
	private ClienteDto cliente;
	private Date fechaCreacion;
	private Date fechaVigencia;
	
	
	
	public CotizacionDto(List<ItemCotizacionDto> items, String estado,
			ClienteDto cliente, Date fechaCreacion, Date fechaVigencia) {
		super();
		this.items = items;
		this.estado = estado;
		this.cliente = cliente;
		this.fechaCreacion = fechaCreacion;
		this.fechaVigencia = fechaVigencia;
	}

	public CotizacionDto(){
		
	}

	public List<ItemCotizacionDto> getItems() {
		return items;
	}

	public void setItems(List<ItemCotizacionDto> items) {
		this.items = items;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}
	
}
