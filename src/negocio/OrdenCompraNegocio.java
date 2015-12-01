package negocio;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import dao.OrdenCompraDAO;
import dto.*;

@Entity
@Table(name="OrdenCompra")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class OrdenCompraNegocio implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrdenCompra;
	private String formaPago;
	private float total;
	private float descuento;
	private String estado;

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="orden_item")
	private List <ItemOrdenCompraNegocio> items;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="orden_proveedor")
	private ProveedorNegocio proveedor;
	
	@OneToMany (cascade=CascadeType.ALL)
	@JoinColumn(name="orden_solicitud")
	private List<SolicitudCompraNegocio> solicitudesCompra;
	
		
	public OrdenCompraNegocio(int idOrdenCompra, String formaPago, float total, float descuento, String estado,
			List<ItemOrdenCompraNegocio> items, ProveedorNegocio proveedor,
			List<SolicitudCompraNegocio> solicitudesCompra) {
		super();
		this.idOrdenCompra = idOrdenCompra;
		this.formaPago = formaPago;
		this.total = total;
		this.descuento = descuento;
		this.estado = estado;
		this.items = items;
		this.proveedor = proveedor;
		this.solicitudesCompra = solicitudesCompra;
	}

	public OrdenCompraNegocio(){
		
	}

	public int getIdOrdenCompra() {
		return idOrdenCompra;
	}

	public void setIdOrdenCompra(int idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<ItemOrdenCompraNegocio> getItems() {
		return items;
	}

	public void setItems(List<ItemOrdenCompraNegocio> items) {
		this.items = items;
	}

	public ProveedorNegocio getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorNegocio proveedor) {
		this.proveedor = proveedor;
	}

	public List<SolicitudCompraNegocio> getSolicitudesCompra() {
		return solicitudesCompra;
	}

	public void setSolicitudesCompra(List<SolicitudCompraNegocio> solicitudesCompra) {
		this.solicitudesCompra = solicitudesCompra;
	}

	public void aOrdenCompraNegocio(OrdenCompraDto miOrdenDto) {	
		this.setDescuento(miOrdenDto.getDescuento());
		this.setEstado(miOrdenDto.getEstado());
		this.setFormaPago(miOrdenDto.getFormaPago());
		this.setTotal(miOrdenDto.getTotal());
		
		ProveedorNegocio proveedor = new ProveedorNegocio();
		proveedor.aProveedorNegocio(miOrdenDto.getProveedor());
		
		List<ItemOrdenCompraNegocio> lista = new ArrayList<ItemOrdenCompraNegocio>();
		for(int i=0; i<miOrdenDto.getItems().size(); i++){
			ItemOrdenCompraNegocio itemNegocio = new ItemOrdenCompraNegocio();
			ItemOrdenCompraDto itemDto = miOrdenDto.getItems().get(i);
			itemNegocio.aItemOrdenCompraNegocio(itemDto);
			lista.add(itemNegocio);
		}
		
		List<SolicitudCompraNegocio> listaSolicitud = new ArrayList<SolicitudCompraNegocio>();
		for (int i=0; i<miOrdenDto.getSolicitudesCompra().size(); i++) {
			SolicitudCompraNegocio solicNegocio = new SolicitudCompraNegocio();
			SolicitudCompraDto solicDto = new SolicitudCompraDto();
			solicNegocio.aSolicitudCompraNegocio(solicDto);
			listaSolicitud.add(solicNegocio);
		}
				
		this.setProveedor(proveedor);
		this.setItems(lista);		
	}
	
	public OrdenCompraDto aOrdenCompraDto() {
		
		OrdenCompraDto ordenDto = new OrdenCompraDto();
		
		ordenDto.setDescuento(this.getDescuento());
		ordenDto.setEstado(this.getEstado());
		ordenDto.setFormaPago(this.getFormaPago());
		ordenDto.setTotal(this.getTotal());
		
		ProveedorDto proveedorDto = new ProveedorDto();
		proveedorDto = this.getProveedor().aProveedorDto();
		
		List<ItemOrdenCompraDto> listaItemDto = new ArrayList<ItemOrdenCompraDto>();
		for(int i=0; i<this.getItems().size(); i++){
			ItemOrdenCompraDto itemDto = new ItemOrdenCompraDto();
			itemDto = this.getItems().get(i).aItemOrdenCompraDto();
			listaItemDto.add(itemDto);
		}
		
		List<SolicitudCompraDto> listaSolCompra = new ArrayList<SolicitudCompraDto>();
		for (int i=0; i<this.getSolicitudesCompra().size(); i++) {
			SolicitudCompraDto solicDto = new SolicitudCompraDto();
			solicDto = this.getSolicitudesCompra().get(i).aSolicitudCompraDTO();
			listaSolCompra.add(solicDto);
		}
		
		ordenDto.setProveedor(proveedorDto);
		ordenDto.setItems(listaItemDto);
		
		return ordenDto;		
	}

	public void persistirOrdenCompra() {
		OrdenCompraDAO.getInstancia().persist(this);
	}
	
	public void deleteOrdenCompra() {
		OrdenCompraDAO.getInstancia().delete(this);
	}
	
	public void updateOrdenCompra() {
		OrdenCompraDAO.getInstancia().update(this);
	}
	
	public void mergeOrdenCompra() {
		OrdenCompraDAO.getInstancia().merge(this);
	}

	
	
}
