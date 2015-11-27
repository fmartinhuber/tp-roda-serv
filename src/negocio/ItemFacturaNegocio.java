package negocio;

import javax.persistence.*;

import dto.ItemFacturaDto;
import dto.RodamientoDto;

@Entity
@Table(name="ItemFactura")
public class ItemFacturaNegocio{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idItemFactura;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="item_rodamiento")
	private RodamientoNegocio rodamiento;
	private float precio;
	private int cantidad;
	
	
	public ItemFacturaNegocio(RodamientoNegocio rodamiento, int cant, float precio) {
		super();
		this.rodamiento = rodamiento;
		this.cantidad = cant;
		this.precio = precio;
	}
	
	public ItemFacturaNegocio(){
		
	}
	
	public void aItemFacturaNegocio(ItemFacturaDto miItFacDto){
		//Asigno los atributos simples
		this.setPrecio(miItFacDto.getPrecio());
		this.setCantidad(miItFacDto.getCantidad());
		//Asigno los atributos de Listas de Clase, con el metodo de esa clase
		RodamientoNegocio miRodaNeg = new RodamientoNegocio();
		miRodaNeg.aRodamientoNegocio(miItFacDto.getRodamiento());
		//Asigno los objetos a la salida
		this.setRodamiento(miRodaNeg);
	}
	
	public ItemFacturaDto aItemFacturaDto(){
		//Creo la salida del metodo
		ItemFacturaDto miItFacDto = new ItemFacturaDto();
		//Asigno los atributos simples
		miItFacDto.setCantidad(this.getCantidad());
		miItFacDto.setPrecio(this.getPrecio());
		//Asigno los atributos de Clase unica, con el metodo de esa clase
		RodamientoDto miRodaDto = new RodamientoDto();
		miRodaDto = this.getRodamiento().aRodamientoDto();
		//Asigno los objetos a la salida
		miItFacDto.setRodamiento(miRodaDto);
	return miItFacDto;
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

	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}
