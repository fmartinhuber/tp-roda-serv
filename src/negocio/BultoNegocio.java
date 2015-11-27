package negocio;

import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import dao.BultoDAO;


@Entity
@Table(name="Bulto")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class BultoNegocio {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int idBulto;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="bulto_itemBulto")
		private List <ItemBultoNegocio> itemBulto;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bulto_remito")
		private RemitoNegocio remito;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bulto_factura")
		private FacturaNegocio factura;
		
	public BultoNegocio(int idBulto, List<ItemBultoNegocio> itemBulto,
			RemitoNegocio remito, FacturaNegocio factura) {
		this.idBulto = idBulto;
		this.itemBulto = itemBulto;
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

	public List<ItemBultoNegocio> getItemBulto() {
		return itemBulto;
	}

	public void setItemBulto(List<ItemBultoNegocio> itemBulto) {
		this.itemBulto = itemBulto;
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
	
	public void persistirBulto(){
		BultoDAO.getinstancia().persist(this);
	}
	
}
