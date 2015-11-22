package negocio;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="SolicitudCompra")
public class SolicitudCompraNegocio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSolicitudCompra;
	private String estado;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="solicitud_cotizacion")
	private List <CotizacionNegocio> listaCotizaciones;
	
	
	public SolicitudCompraNegocio(int id, String estado, List<CotizacionNegocio> listaCotizaciones) {
		super();
		this.idSolicitudCompra = id;
		this.estado = estado;
		this.listaCotizaciones = listaCotizaciones;
	}

	public SolicitudCompraNegocio() {
		
	}

	public int getId() {
		return idSolicitudCompra;
	}

	public void setId(int id) {
		this.idSolicitudCompra = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<CotizacionNegocio> getListaCotizaciones() {
		return listaCotizaciones;
	}

	public void setListaCotizaciones(List<CotizacionNegocio> listaCotizaciones) {
		this.listaCotizaciones = listaCotizaciones;
	}
	
}
