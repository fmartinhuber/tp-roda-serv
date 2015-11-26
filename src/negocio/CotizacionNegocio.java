package negocio;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import javax.xml.bind.annotation.*;

import dao.*;

import dto.*;

/**
 * 	@author Daro
 *	El estado de la cotizacion puede ser pendiente o aprobada
 *	Si es pendiente: El cliente pidio la cotizacion y el sistema se la genero
 *	Si es aprobada: El cliente aprobo la solicitud (Esto se lo llama en el enunciado "Pedido de Venta")
 */

@Entity
@Table(name="Cotizacion")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CotizacionNegocio implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCotizacion;
	private String estado;
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn(name="cotizacion_items")
	private List<ItemCotizacionNegocio> items;
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="cotizacion_cliente")
	private ClienteNegocio cliente; 
	private Date fechaCreacion;
	private Date fechaVigencia;


	public CotizacionNegocio(List<ItemCotizacionNegocio> items, String estado,
			ClienteNegocio cliente, Date fechaCreacion, Date fechaVigencia) {
		super();
		this.items = items;
		this.estado = estado;
		this.cliente = cliente;
		this.fechaCreacion = fechaCreacion;
		this.fechaVigencia = fechaVigencia;
	}

	public CotizacionNegocio(){

	}

	/**
	 * @author Daro
	 * - Transformacion 1/2
	 * De esta forma se pasa Dto a Negocio. Cuando el cliente manda un Dto el servidor
	 * necesita transformarlo a negocio para usar los metodos necesarios
	 */
	public void aCotizacionNegocio (CotizacionDto miCotDto){
		//Asigno los atributos simples
		this.setIdCotizacion(miCotDto.getNumeroCotizacion());
		this.setEstado(miCotDto.getEstado());
		this.setFechaCreacion(miCotDto.getFechaCreacion());
		this.setFechaVigencia(miCotDto.getFechaVigencia());
		//Asigno los atributos de Clase unica, con el metodo de esa clase
		ClienteNegocio miCliNegocio = new ClienteNegocio();
		miCliNegocio.aClienteNegocio(miCotDto.getCliente());
		//Asigno los atributos de Listas de Clase, con el metodo de esa clase
		List<ItemCotizacionNegocio> listaItCoNegocio= new ArrayList<ItemCotizacionNegocio>();
		for (int i=0; i<miCotDto.getItems().size(); i++){
			//Creo el Item Cotizacion Negocio
			ItemCotizacionNegocio miItCotNegocio = new ItemCotizacionNegocio();
			//Lo transformo
			miItCotNegocio.aItemCotizacionNegocio(miCotDto.getItems().get(i));
			//Agrego el Item Cotizacion Negocio a la lista de negocio
			listaItCoNegocio.add(miItCotNegocio);
		}
		//Asigno los objetos a la salida
		this.setCliente(miCliNegocio);
		this.setItems(listaItCoNegocio);
	}
	
	
	
	/**
	 * @author Daro
	 * - Transformacion 2/2
	 * De esta forma se pasa Negocio a Dto. Cuando se necesita devolver informacion al cliente
	 * hay que transformar la clase Negocio a Dto para enviarsela
	 */
	public CotizacionDto aCotizacionDto(){
		//Creo la salida del metodo
		CotizacionDto miCotDto = new CotizacionDto();
		//Asigno los atributos simples
		miCotDto.setNumeroCotizacion(this.getIdCotizacion());
		miCotDto.setEstado(this.getEstado());
		miCotDto.setFechaCreacion(this.getFechaCreacion());
		miCotDto.setFechaVigencia(this.getFechaVigencia());
		//Asigno los atributos de Clase unica, con el metodo de esa clase
		ClienteDto miCliDto = new ClienteDto();
		miCliDto = this.getCliente().aClienteDto();		
		//Asigno los atributos de Listas de Clase, con el metodo de esa clase
		List<ItemCotizacionDto> listaItCoDto= new ArrayList<ItemCotizacionDto>();
		for (int i=0; i<this.getItems().size(); i++){
			//Creo el Item Cotizacion Dto
			ItemCotizacionDto miItCotDto = new ItemCotizacionDto();
			//Lo trasnformo
			miItCotDto = this.getItems().get(i).aItemCotizacionDto();
			//Agrego el Item Cotizacion Dto a la lista de Dto
			listaItCoDto.add(miItCotDto);
		}
		//Asigno los objetos a la salida
		miCotDto.setCliente(miCliDto);
		miCotDto.setItems(listaItCoDto);
	return miCotDto;
	}



	public List<ItemCotizacionNegocio> getItems() {
		return items;
	}

	public void setItems(List<ItemCotizacionNegocio> items) {
		this.items = items;
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

	public void persistirCotizacion(){
		CotizacionDAO.getinstancia().persist(this);
	}
	
	public void mergearCotizacion(){
		CotizacionDAO.getinstancia().merge(this);
	}
	
	public void actualizarCotizacion(){
		CotizacionDAO.getinstancia().update(this);
	}

	public int getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(int idCotizacion) {
		this.idCotizacion = idCotizacion;
	}
	
}
