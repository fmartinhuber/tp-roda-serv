package negocio;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="SolicitudCompra")
public class SolicitudCompraNegocio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String estado;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="solicitud_cotizacion")
	private List <CotizacionNegocio> listaCotizaciones;
	
	
	public SolicitudCompraNegocio(int id, String estado, List<CotizacionNegocio> listaCotizaciones) {
		super();
		this.id = id;
		this.estado = estado;
		this.listaCotizaciones = listaCotizaciones;
	}

	public SolicitudCompraNegocio() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
