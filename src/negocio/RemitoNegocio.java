package negocio;

import java.util.*;



public class RemitoNegocio{

	private String estado;
	private ClienteNegocio cliente;
	private List <CotizacionNegocio> cotizaciones;
	private String comentarios;
	private Date fecha;
	private boolean conformidad;
	
	
	
	public RemitoNegocio(String estado, ClienteNegocio cliente,
			List<CotizacionNegocio> cotizaciones, String comentarios, Date fecha,
			boolean conformidad) {
		super();
		this.estado = estado;
		this.cliente = cliente;
		this.cotizaciones = cotizaciones;
		this.comentarios = comentarios;
		this.fecha = fecha;
		this.conformidad = conformidad;
	}
	
	public RemitoNegocio(){
		
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ClienteNegocio getCliente() {
		return cliente;
	}

	public void setCliente(ClienteNegocio cliente) {
		this.cliente = cliente;
	}

	public List<CotizacionNegocio> getCotizaciones() {
		return cotizaciones;
	}

	public void setCotizaciones(List<CotizacionNegocio> cotizaciones) {
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
