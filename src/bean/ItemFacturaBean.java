package bean;

import javax.persistence.*;

import dto.RodamientoDto;



@Entity
@Table(name="ItemFactura")
public class ItemFacturaBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idItemFactura;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="item_rodamiento")
	private RodamientoDto rodamiento;
	private float subtotal;
	
	
	
	public int getIdItemFactura() {
		return idItemFactura;
	}
	
	public void setIdItemFactura(int idItemFactura) {
		this.idItemFactura = idItemFactura;
	}
	
	
	public float getSubtotal() {
		return subtotal;
	}
	
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public RodamientoDto getRodamiento() {
		return rodamiento;
	}

	public void setRodamiento(RodamientoDto rodamiento) {
		this.rodamiento = rodamiento;
	}
	
	
}
