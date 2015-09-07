package negocio;


public class ItemFacturaNegocio{

	private RodamientoNegocio rodamiento;
	private int cantidad;
	private float subtotal;
	
	
	
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
