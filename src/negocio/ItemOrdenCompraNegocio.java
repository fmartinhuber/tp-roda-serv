package negocio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="ItemOrdenCompra")
public class ItemOrdenCompraNegocio{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idItemOrdenCompra;
	private float monto;
	private int cantidad;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="item_rodamiento")
	private RodamientoNegocio rodamiento;
	
	public ItemOrdenCompraNegocio(RodamientoNegocio roda, float monto, int cant) {
		super();
		//this.ordenPedido = ordenPedido;
		this.rodamiento = roda;
		this.monto = monto;
		this.cantidad = cant;
	}
	
	public ItemOrdenCompraNegocio(){
		
	}

	/*public CotizacionNegocio getOrdenPedido() {
		return ordenPedido;
	}

	public void setOrdenPedido(CotizacionNegocio ordenPedido) {
		this.ordenPedido = ordenPedido;
	}*/
	
	
	public float getMonto() {
		return monto;
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

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	
}
