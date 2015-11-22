package negocio;

import javax.persistence.*;

@Entity
@Table(name="ItemFactura")
public class ItemFacturaNegocio{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idItemFactura;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="item_rodamiento")
	private RodamientoNegocio rodamiento;
	private float subtotal;
	private int cantidad;
	
	
	public ItemFacturaNegocio(RodamientoNegocio rodamiento, int cant, float subtotal) {
		super();
		this.rodamiento = rodamiento;
		this.cantidad = cant;
		this.subtotal = subtotal;
	}
	
	public ItemFacturaNegocio(){
		
	}
	
	
	public RodamientoNegocio getRodamiento() {
		return rodamiento;
	}

	public void setRodamiento(RodamientoNegocio rodamiento) {
		this.rodamiento = rodamiento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	
}
