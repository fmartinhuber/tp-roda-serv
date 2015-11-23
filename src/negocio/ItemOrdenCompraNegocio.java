package negocio;

import javax.persistence.*;

import dto.ItemOrdenCompraDto;

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

	public void aItemOrdenCompraNegocio(ItemOrdenCompraDto itemDto) {

		this.setCantidad(itemDto.getCantidad());
		this.setMonto(itemDto.getMonto());
		
		RodamientoNegocio roda = new RodamientoNegocio();
		roda.aRodamientoNegocio(itemDto.getRodamiento());
		
		this.setRodamiento(roda);
		
	}
	
	
}
