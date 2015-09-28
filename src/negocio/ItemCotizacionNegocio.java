package negocio;

import bean.ItemCotizacionBean;
import dto.ItemCotizacionDto;



public class ItemCotizacionNegocio{

	private RodamientoNegocio rodamiento;
	private int cant;
	private float subtotal;
	
	
	public ItemCotizacionNegocio(RodamientoNegocio rodamiento, int cant) {
		super();
		this.rodamiento = rodamiento;
		this.cant = cant;
	}
	
	public ItemCotizacionNegocio(){
		
	}
	
	public ItemCotizacionNegocio transformarItemCotizacionDtoAItemCotizacionNegocio(ItemCotizacionDto miItCotDto) {
		// TODO Auto-generated method stub
		return null;
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

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public ItemCotizacionBean itemCotizacionNegocioToBean() {
		// TODO Auto-generated method stub
		ItemCotizacionBean miItemCotizacionBean = new ItemCotizacionBean();
		miItemCotizacionBean.setCant(this.getCant());
		miItemCotizacionBean.setSubtotal(subtotal);
		miItemCotizacionBean.setRodamiento(this.getRodamiento().rodamientoNegocioToBean());
		return miItemCotizacionBean;
	}

	public ItemCotizacionNegocio itemCotizacionBeanToNegocio(ItemCotizacionBean itemCotizacionBean) {
		// TODO Auto-generated method stub
		this.setCant(itemCotizacionBean.getCant());
		this.setSubtotal(itemCotizacionBean.getSubtotal());
		RodamientoNegocio rodamientoNegocio = new RodamientoNegocio();
		this.setRodamiento(rodamientoNegocio.rodamientoBeanToNegocio(itemCotizacionBean.getRodamiento()));
		return this;
	}


	

}
