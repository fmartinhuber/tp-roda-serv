package negocio;

import java.util.*;

import javax.persistence.*;

import dao.SolicitudCompraDAO;
import dto.CotizacionDto;
import dto.SolicitudCompraDto;

@Entity
@Table(name="SolicitudCompra")
public class SolicitudCompraNegocio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSolicitudCompra;
	private String estado;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
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

	public void aSolicitudCompraNegocio(SolicitudCompraDto solicitudCompraDto) {
		this.setId(solicitudCompraDto.getNumeroSolicitudCompra());
		this.setEstado(solicitudCompraDto.getEstado());						
		List<CotizacionNegocio> cotisNego = new ArrayList<CotizacionNegocio>();
		for (int i = 0; i < solicitudCompraDto.getListaCotizaciones().size(); i++) {
			CotizacionNegocio coti = new CotizacionNegocio();
			coti.aCotizacionNegocio(solicitudCompraDto.getListaCotizaciones().get(i));
			cotisNego.add(coti);
		}
		this.setListaCotizaciones(cotisNego);
	}
	
	public SolicitudCompraDto aSolicitudCompraDTO() {
		SolicitudCompraDto scDTO = new SolicitudCompraDto();
		scDTO.setEstado(this.getEstado());
		scDTO.setNumeroSolicitudCompra(this.getId());
		List<CotizacionDto> cotSol = new ArrayList<CotizacionDto>();
		for(int i = 0; i < this.getListaCotizaciones().size(); i++){
			CotizacionDto cotDto = this.getListaCotizaciones().get(i).aCotizacionDto();
			cotSol.add(cotDto);
		}
		scDTO.setListaCotizaciones(cotSol);
		return scDTO;
	}

	public void persistirSolicitudCompra() {
		SolicitudCompraDAO.getInstancia().persist(this);
	}
	
	public void updateSolicitudCompra() {
		SolicitudCompraDAO.getInstancia().update(this);
	}
	
	public void deleteSolicitudCompra() {
		SolicitudCompraDAO.getInstancia().delete(this);
	}
	
	public void mergeSolicitudCompra() {
		SolicitudCompraDAO.getInstancia().merge(this);
	}
		
}
