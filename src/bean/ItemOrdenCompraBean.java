package bean;

import javax.persistence.*;



@Entity
@Table(name="ItemOrdenCompra")
public class ItemOrdenCompraBean{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idItemOrdenCompra;
	private float monto;
	private int cantidad;
	@JoinColumn(name="item_rodamiento")
	private RodamientoBean rodamiento;
	
	
	
	public int getIdItemOrdenCompra() {
		return idItemOrdenCompra;
	}
	
	public void setIdItemOrdenCompra(int idItemOrdenCompra) {
		this.idItemOrdenCompra = idItemOrdenCompra;
	}
	
	public float getMonto() {
		return monto;
	}
	
	public void setMonto(float monto) {
		this.monto = monto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public RodamientoBean getRodamiento() {
		return rodamiento;
	}

	public void setRodamiento(RodamientoBean rodamiento) {
		this.rodamiento = rodamiento;
	}
	
	
}
