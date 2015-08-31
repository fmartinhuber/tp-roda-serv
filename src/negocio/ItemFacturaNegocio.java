package negocio;



public class ItemFacturaNegocio{

	private CotizacionNegocio orden;  
	private float subtotal;
	
	
	
	public ItemFacturaNegocio(CotizacionNegocio orden, float subtotal) {
		super();
		this.orden = orden;
		this.subtotal = subtotal;
	}
	
	public ItemFacturaNegocio(){
		
	}
	
	public CotizacionNegocio getOrden() {
		return orden;
	}
	public void setOrden(CotizacionNegocio orden) {
		this.orden = orden;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	
}
