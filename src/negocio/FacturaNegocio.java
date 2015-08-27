package negocio;

import java.util.*;



public class FacturaNegocio{
	
	private String estado;
	private Date fecha;
	private ClienteNegocio cliente;
	private float descuento;
	private List <ItemFacturaNegocio> items;
	private float total;
	
	
	
	public FacturaNegocio(String estado, Date fecha,
			ClienteNegocio cliente, float descuento, List<ItemFacturaNegocio> items,
			float total) {
		super();
		this.estado = estado;
		this.fecha = fecha;
		this.cliente = cliente;
		this.descuento = descuento;
		this.items = items;
		this.total = total;
	}
	
	public FacturaNegocio(){
		
	}

	public float getTotal() {
		return total;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	public List<ItemFacturaNegocio> getItems() {
		return items;
	}
	
	public void setItems(List<ItemFacturaNegocio> items) {
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
	
	public ClienteNegocio getCliente() {
		return cliente;
	}
	
	public void setCliente(ClienteNegocio cliente) {
		this.cliente = cliente;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	
}
