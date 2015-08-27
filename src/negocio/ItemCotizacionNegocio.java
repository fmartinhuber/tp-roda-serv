package negocio;



public class ItemCotizacionNegocio{

	private RodamientoNegocio rodamiento;
	private int cant;
	
	
	
	public ItemCotizacionNegocio(RodamientoNegocio rodamiento, int cant) {
		super();
		this.rodamiento = rodamiento;
		this.cant = cant;
	}
	
	public ItemCotizacionNegocio(){
		
	}

	public RodamientoNegocio getRodamiento() {
		return rodamiento;
	}
	
	public void setRodamiento(RodamientoNegocio rodamiento) {
		this.rodamiento = rodamiento;
	}
	
	public int getCant() {
		return cant;
	}
	
	public void setCant(int cant) {
		this.cant = cant;
	}
	

}
