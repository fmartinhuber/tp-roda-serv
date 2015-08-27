package negocio;



public class OfertaNegocio{

	private RodamientoNegocio Rodamiento;
	private float descuento;
	private int volumen;
	private int stock;
	
		
	
	public OfertaNegocio(RodamientoNegocio rodamiento, float descuento,
			int volumen, int stock) {
		super();
		Rodamiento = rodamiento;
		this.descuento = descuento;
		this.volumen = volumen;
		this.stock = stock;
	}
	
	public OfertaNegocio(){
		
	}

	public RodamientoNegocio getRodamiento() {
		return Rodamiento;
	}
	
	public void setRodamiento(RodamientoNegocio rodamiento) {
		Rodamiento = rodamiento;
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
