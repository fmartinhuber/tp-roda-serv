package estrategia;

import javax.persistence.*;

import interfaces.IPagoEstrategia;

@Entity
public class EstrategiaFormaPago implements IPagoEstrategia{

	private String formaPago;
	
	public EstrategiaFormaPago(){};	
	
	public EstrategiaFormaPago(String formaPago) {
		super();
		this.formaPago = formaPago;
	}	

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	@Override
	public void pago(float monto) {		
		
	}
	
	public float calcularTotal(String formaPago, float monto){
		float total=0;
		
		if(formaPago.equalsIgnoreCase("tarjeta")){
			total = total + (float) (monto*0.89);
		}
		else if(formaPago.equalsIgnoreCase("efectivo")){
			total = total + (float) (monto*0.95);
		}		
		
		return total;
	}

}
