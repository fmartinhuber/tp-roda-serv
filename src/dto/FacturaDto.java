package dto;

import java.io.Serializable;
import java.util.*;



public class FacturaDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String estado;
	private Date fecha;
	private ClienteDto cliente;
	private float descuento;
	private List <ItemFacturaDto> items;
	private float total;
	
	
	
	public FacturaDto(String estado, Date fecha,
			ClienteDto cliente, float descuento, List<ItemFacturaDto> items,
			float total) {
		super();
		this.estado = estado;
		this.fecha = fecha;
		this.cliente = cliente;
		this.descuento = descuento;
		this.items = items;
		this.total = total;
	}
	
	public FacturaDto(){
		
	}

	public float getTotal() {
		return total;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	public List<ItemFacturaDto> getItems() {
		return items;
	}
	
	public void setItems(List<ItemFacturaDto> items) {
		this.items = items;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public ClienteDto getCliente() {
		return cliente;
	}
	
	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	
}
