package negocio;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import dao.*;
import dto.ClienteDto;
import dto.CotizacionDto;
import dto.RemitoDto;

@Entity
@Table(name="Remito")
public class RemitoNegocio implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int idRemito;
	private String estado;
	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="remito_cliente")
	private ClienteNegocio cliente;
	@OneToMany
	@JoinColumn(name="remito_orden")
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
	
	public void aRemitoNegocio(RemitoDto miRemDto){
		//Asigno los atributos simples
		this.setEstado(miRemDto.getEstado());
		this.setComentarios(miRemDto.getComentarios());
		this.setFecha(miRemDto.getFecha());
		this.setConformidad(miRemDto.getConformidad());
		//Asigno los atributos de Clase unica, con el metodo de esa clase
		ClienteNegocio miCliNegocio = new ClienteNegocio();
		miCliNegocio.aClienteNegocio(miRemDto.getCliente());
		//Asigno los atributos de Listas de Clase, con el metodo de esa clase
		List<CotizacionNegocio> listaCotNeg = new ArrayList<CotizacionNegocio>();
		for (int i=0; i < miRemDto.getCotizaciones().size(); i++){
			//Creo la Cotizacion Negocio
			CotizacionNegocio miCotNeg = new CotizacionNegocio();
			//Lo transformo
			miCotNeg.aCotizacionNegocio(miRemDto.getCotizaciones().get(i));
			//Agrego la Cotizacion a la lista de negocio
			listaCotNeg.add(miCotNeg);
		}
		//Asigno los objetos a la salida
		this.setCliente(miCliNegocio);
		this.setCotizaciones(listaCotNeg);
	}
	
	public RemitoDto aRemitoDto() {
		//Creo la salida del metodo
		RemitoDto miRemDto = new RemitoDto();
		//Asigno los atributos simples
		miRemDto.setEstado(this.getEstado());
		miRemDto.setComentarios(this.getComentarios());
		miRemDto.setFecha(this.getFecha());
		miRemDto.setConformidad(this.getConformidad());
		//Asigno los atributos de Clase unica, con el metodo de esa clase
		ClienteDto miCliDto = new ClienteDto();
		miCliDto = this.getCliente().aClienteDto();
		//Asigno los atributos de Listas de Clase, con el metodo de esa clase
		List<CotizacionDto> listaCotDto = new ArrayList<CotizacionDto>();
		for (int i=0; i < this.getCotizaciones().size(); i++){
			//Creo la Cotizacion Negocio
			CotizacionDto miCotDto = new CotizacionDto();
			//Lo transformo
			miCotDto = this.getCotizaciones().get(i).aCotizacionDto();
			//Agrego la Cotizacion a la lista de negocio
			listaCotDto.add(miCotDto);
		}
		//Asigno los objetos a la salida
		miRemDto.setCliente(miCliDto);
		miRemDto.setCotizaciones(listaCotDto);
	return miRemDto;
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

	public boolean getConformidad() {
		return conformidad;
	}

	public void setConformidad(boolean conformidad) {
		this.conformidad = conformidad;
	}

	public void persistRemito() {
		RemitoDAO.getInstancia().persist(this);		
	}
	
	public void mergeRemito(){
		RemitoDAO.getInstancia().merge(this);
	}
	
	public void updateRemito() {
		RemitoDAO.getInstancia().update(this);		
	}
	
	public void deleteRemito() {
		RemitoDAO.getInstancia().delete(this);		
	}

	

}
