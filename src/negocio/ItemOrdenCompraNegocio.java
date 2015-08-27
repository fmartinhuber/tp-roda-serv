package negocio;



public class ItemOrdenCompraNegocio{

	private CotizacionNegocio ordenPedido;
	private float monto;
	
	public ItemOrdenCompraNegocio(CotizacionNegocio ordenPedido, float monto) {
		super();
		this.ordenPedido = ordenPedido;
		this.monto = monto;
	}
	
	public ItemOrdenCompraNegocio(){
		
	}

	public CotizacionNegocio getOrdenPedido() {
		return ordenPedido;
	}

	public void setOrdenPedido(CotizacionNegocio ordenPedido) {
		this.ordenPedido = ordenPedido;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	
}
