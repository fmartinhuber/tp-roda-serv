package dto;

import java.io.Serializable;
import java.util.*;



public class RemitoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String estado;
	private ClienteDto cliente;
	private List <CotizacionDto> cotizaciones;
	private String comentarios;
	private Date fecha;
	private boolean conformidad;
	
	
	
	public RemitoDto(String estado, ClienteDto cliente,
			List<CotizacionDto> cotizaciones, String comentarios, Date fecha,
			boolean conformidad) {
		super();
		this.estado = estado;
		this.cliente = cliente;
		this.cotizaciones = cotizaciones;
		this.comentarios = comentarios;
		this.fecha = fecha;
		this.conformidad = conformidad;
	}
	
	public RemitoDto(){
		
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

	public List<CotizacionDto> getCotizaciones() {
		return cotizaciones;
	}

	public void setCotizaciones(List<CotizacionDto> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isConformidad() {
		return conformidad;
	}

	public void setConformidad(boolean conformidad) {
		this.conformidad = conformidad;
	}
	

}
