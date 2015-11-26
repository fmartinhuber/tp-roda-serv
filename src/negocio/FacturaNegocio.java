package negocio;

import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import dao.*;
import dto.ClienteDto;
import dto.CotizacionDto;
import dto.FacturaDto;
import dto.ItemFacturaDto;

@Entity
@Table(name="Factura")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class FacturaNegocio{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idFactura;
	private String estado; // Generada // enviada // pagada
	private Date fecha;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="factura_cliente")
	private ClienteNegocio cliente;
	private float descuento;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="factura_item")
	private List <ItemFacturaNegocio> items; 
	private float total;

	//Lo agregue para que se sepa que cotizacion se esta facturando.
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="factura_cotizacion")
	private List<CotizacionNegocio> cotizacion;
	
	public FacturaNegocio(String estado, Date fecha,
			ClienteNegocio cliente, float descuento, List<ItemFacturaNegocio> items,
			float total) {
		this.estado = estado;
		this.fecha = fecha;
		this.cliente = cliente;
		this.descuento = descuento;
		this.items = items;
		this.total = total;
	}
	
	public FacturaNegocio(){
		
	}
	
	public void aFacturaNegocio (FacturaDto miFacDto){
		//Asigno los atributos simples
		this.setIdFactura(miFacDto.getNumeroFactura());
		this.setEstado(miFacDto.getEstado());
		this.setFecha(miFacDto.getFecha());
		this.setDescuento(miFacDto.getDescuento());
		this.setTotal(miFacDto.getTotal());
		//Asigno los atributos de Clase unica, con el metodo de esa clase
		ClienteNegocio miCliNeg = new ClienteNegocio();
		miCliNeg.aClienteNegocio(miFacDto.getCliente());
		
		//Asigno los atributos de Listas de Clase, con el metodo de esa clase
		List <ItemFacturaNegocio> miListaItemFacNeg = new ArrayList<ItemFacturaNegocio>();
		for (int i=0; i < miFacDto.getItems().size(); i++) {
			//Creo el Item Factura Negocio
			ItemFacturaNegocio miItFacNeg = new ItemFacturaNegocio();
			//Lo transformo
			miItFacNeg.aItemFacturaNegocio(miFacDto.getItems().get(i));
			//Agrego el Item Factuar Negocio a la lista de negocio
			miListaItemFacNeg.add(miItFacNeg);
		}
		
		List <CotizacionNegocio> miListaCotNeg = new ArrayList<CotizacionNegocio>();
		for (int i=0; i < miFacDto.getCotizacion().size(); i++){
			//Creo la Cotizacion Negocio
			CotizacionNegocio miCotNeg = new CotizacionNegocio();
			//Lo transformo
			miCotNeg.aCotizacionNegocio(miFacDto.getCotizacion().get(i));
			//Agrego la Cotizacion Negocio
			miListaCotNeg.add(miCotNeg);
		}
		
		//Asigno los objetos a la salida
		this.setItems(miListaItemFacNeg);
		this.setCotizacion(miListaCotNeg);
	}
	
	
	public FacturaDto aFacturaDto (){
		//Creo la salida del metodo
		FacturaDto miFactDto = new FacturaDto();
		//Asigno los atributos simples
		miFactDto.setNumeroFactura(this.getIdFactura());
		miFactDto.setEstado(this.getEstado());
		miFactDto.setFecha(this.getFecha());
		miFactDto.setDescuento(this.getDescuento());
		miFactDto.setTotal(this.getTotal());
		
		//Asigno los atributos de Listas de Clase, con el metodo de esa clase
		List <ItemFacturaDto> miListaItemFacDto = new ArrayList<ItemFacturaDto>();
		for (int i=0; i < this.getItems().size(); i++){
			//Creo el Item Factura Dto
			ItemFacturaDto miItFacDto = new ItemFacturaDto();
			//Lo transformo
			miItFacDto = this.getItems().get(i).aItemFacturaDto();
			//Agrego el Item Factura Dto a la lista de Dto
			miListaItemFacDto.add(miItFacDto);
		}
		
		List<CotizacionDto> miListaCotDto = new ArrayList<CotizacionDto>();
		for (int i=0; i < this.getCotizacion().size(); i++){
			//Creo la Cotizacion Dto
			CotizacionDto miCotDto = new CotizacionDto();
			//Lo transformo
			miCotDto = this.getCotizacion().get(i).aCotizacionDto();
			//Agrego la Cotizacion a la lista de Dto
			miListaCotDto.add(miCotDto);
		}
		
		//Asigno los objetos a la salida
		miFactDto.setItems(miListaItemFacDto);
		miFactDto.setCotizacion(miListaCotDto);
		
	return miFactDto;
	}

	
	public float getTotal() {
		return total;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	public List<ItemFacturaNegocio> getItems() {
		return items;
	}
	
	public void setItems(List<ItemFacturaNegocio> items) {
		this.items = items;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public ClienteNegocio getCliente() {
		return cliente;
	}
	
	public void setCliente(ClienteNegocio cliente) {
		this.cliente = cliente;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public List<CotizacionNegocio> getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(List<CotizacionNegocio> cotizacion) {
		this.cotizacion = cotizacion;
	}
	
	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public void persistirFactura(){
		FacturaDAO.getInstancia().merge(this);
	}
	
	public void updateFactura(){
		FacturaDAO.getInstancia().update(this);
	}
}
