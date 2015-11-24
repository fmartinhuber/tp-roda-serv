package negocio;

import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import utils.ItemNegocio;

@Entity
@Table(name="Bulto")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class BultoNegocio {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int idBulto;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="bulto_itemRodamiento")
		private List <ItemNegocio> itemRodamiento;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bulto_remito")
		private RemitoNegocio remito;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bulto_factura")
		private FacturaNegocio factura;
	
	public BultoNegocio(List<ItemNegocio> itemRodamiento, RemitoNegocio remito, FacturaNegocio factura) {
		super();
		this.itemRodamiento = itemRodamiento;
		this.remito = remito;
		this.factura = factura;
	}
	
	public BultoNegocio() {
		
	}

	public int getIdBulto() {
		return idBulto;
	}

	public void setIdBulto(int idBulto) {
		this.idBulto = idBulto;
	}

	public List<ItemNegocio> getItemRodamiento() {
		return itemRodamiento;
	}

	public void setItemRodamiento(List<ItemNegocio> itemRodamiento) {
		this.itemRodamiento = itemRodamiento;
	}

	public RemitoNegocio getRemito() {
		return remito;
	}

	public void setRemito(RemitoNegocio remito) {
		this.remito = remito;
	}

	public FacturaNegocio getFactura() {
		return factura;
	}

	public void setFactura(FacturaNegocio factura) {
		this.factura = factura;
	}
	
}
