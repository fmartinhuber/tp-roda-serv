package negocio;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import dao.*;
import dto.OrdenCompraDto;
import dto.RemitoDto;

@Entity
@Table(name="Remito")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class RemitoNegocio implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int idRemito;
	private String estado;
	@OneToMany (cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="remito_orden")
	private List <OrdenCompraNegocio> ordenesDeCompra;
	private Date fecha;
	private String comentarios;
//	@OneToOne (cascade=CascadeType.ALL)
//	@JoinColumn(name="remito_proveedor")
//	private ProveedorNegocio proveedor;
	
	public RemitoNegocio(){}
	
	public RemitoNegocio(int idRemito, String estado,
			List<OrdenCompraNegocio> ordenesDeCompra, Date fecha,
			String comentarios, ProveedorNegocio proveedor) {
		super();
		this.idRemito = idRemito;
		this.estado = estado;
		this.ordenesDeCompra = ordenesDeCompra;
		this.fecha = fecha;
		this.comentarios = comentarios;
		//this.proveedor = proveedor;
	}



	public void aRemitoNegocio(RemitoDto miRemDto){
		//Asigno los atributos simples
		this.setEstado(miRemDto.getEstado());
		this.setComentarios(miRemDto.getComentarios());
		this.setFecha(miRemDto.getFecha());
		//Asigno los atributos de Listas de Clase, con el metodo de esa clase
		List<OrdenCompraNegocio> listaOrdNeg = new ArrayList<OrdenCompraNegocio>();
		for (int i=0; i < miRemDto.getOrdenesDeCompra().size(); i++){
			//Creo la Orden de Compra Negocio
			OrdenCompraNegocio miOrdNeg = new OrdenCompraNegocio();
			//Lo transformo
			miOrdNeg.aOrdenCompraNegocio(miRemDto.getOrdenesDeCompra().get(i));
			//Agrego la Cotizacion a la lista de negocio
			listaOrdNeg.add(miOrdNeg);
		}
		//Asigno los objetos a la salida
		this.setOrdenesDeCompra(listaOrdNeg);
	}
	
	public RemitoDto aRemitoDto() {
		//Creo la salida del metodo
		RemitoDto miRemDto = new RemitoDto();
		//Asigno los atributos simples
		miRemDto.setEstado(this.getEstado());
		miRemDto.setComentarios(this.getComentarios());
		miRemDto.setFecha(this.getFecha());
		//Asigno los atributos de Listas de Clase, con el metodo de esa clase
		List<OrdenCompraDto> listaOrdDto = new ArrayList<OrdenCompraDto>();
		for (int i=0; i < this.getOrdenesDeCompra().size(); i++){
			//Creo la Orden de Compra Dto
			OrdenCompraDto miOrdDto = new OrdenCompraDto();
			//Lo transformo
			miOrdDto = this.getOrdenesDeCompra().get(i).aOrdenCompraDto();
			//Agrego la Cotizacion a la lista de negocio
			listaOrdDto.add(miOrdDto);
		}
		//Asigno los objetos a la salida
		miRemDto.setOrdenesDeCompra(listaOrdDto);
	return miRemDto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}

	public List<OrdenCompraNegocio> getOrdenesDeCompra() {
		return ordenesDeCompra;
	}

	public void setOrdenesDeCompra(List<OrdenCompraNegocio> ordenesDeCompra) {
		this.ordenesDeCompra = ordenesDeCompra;
	}

//	public ProveedorNegocio getProveedor() {
//		return proveedor;
//	}
//
//	public void setProveedor(ProveedorNegocio proveedor) {
//		this.proveedor = proveedor;
//	}

	
	
}
