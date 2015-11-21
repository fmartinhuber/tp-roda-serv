package negocio;


//TODO: no tiene ninguna Entity
public class OfertaNegocio extends RodamientoNegocio{

	private float descuento;
	private int volumen;
	private int stock;
	
	
	
	public OfertaNegocio(float descuento, int volumen, int stock) {
		super();
		this.descuento = descuento;
		this.volumen = volumen;
		this.stock = stock;
	}
	
	public OfertaNegocio(){
		
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	public int getVolumen() {
		return volumen;
	}
	
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
