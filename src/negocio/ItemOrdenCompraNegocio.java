package negocio;



public class ItemOrdenCompraNegocio{

	//private CotizacionNegocio ordenPedido;
	private RodamientoNegocio rodamiento;
	private float monto;
	private int cantidad;
	
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
