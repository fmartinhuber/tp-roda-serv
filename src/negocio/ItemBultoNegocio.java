package negocio;

import javax.persistence.*;

@Entity
@Table(name="ItemBulto")
public class ItemBultoNegocio {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int idItemBulto;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="item_rodamiento")
		private RodamientoNegocio rodamiento;
	private int cantidad;
	
	public ItemBultoNegocio(int idItemBulto, RodamientoNegocio rodamiento,
			int cantidad) {
		this.idItemBulto = idItemBulto;
		this.rodamiento = rodamiento;
		this.cantidad = cantidad;
	}
	
	public ItemBultoNegocio() {
		
	}

	public int getIdItemBulto() {
		return idItemBulto;
	}

	public void setIdItemBulto(int idItemBulto) {
		this.idItemBulto = idItemBulto;
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
	
}
