package negocio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SolicitudCompraNegocio {
	
	@Id
	private int id; 
	
	private String estado;
	
	private int numeroOv;
	
	private List <CotizacionNegocio> listaCotizaciones;

	public List <CotizacionNegocio> getListaCotizaciones() {
		return listaCotizaciones;
	}

	public void setListaCotizaciones(List <CotizacionNegocio> listaCotizaciones) {
		this.listaCotizaciones = listaCotizaciones;
	}

	public int getNumeroOv() {
		return numeroOv;
	}

	public void setNumeroOv(int numeroOv) {
		this.numeroOv = numeroOv;
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
	
	
}
