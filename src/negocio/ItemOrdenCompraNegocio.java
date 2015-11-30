package negocio;

import javax.persistence.*;

import dao.ItemOrdenCompraDAO;
import dto.ItemOrdenCompraDto;

@Entity
@Table(name="ItemOrdenCompra")
public class ItemOrdenCompraNegocio{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idItemOrdenCompra;
	private float monto;
	private int cantidad;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="item_rodamiento")
	private RodamientoNegocio rodamiento;
	
	public ItemOrdenCompraNegocio(RodamientoNegocio roda, float monto, int cant) {
		this.rodamiento = roda;
		this.monto = monto;
		this.cantidad = cant;
	}
	
	public ItemOrdenCompraNegocio(RodamientoNegocio roda, int cant) {
		this.rodamiento = roda;
		this.cantidad = cant;
	}
	
	public ItemOrdenCompraNegocio(){
		
	}	
	
	public float getMonto() {
		return monto;
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

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public void aItemOrdenCompraNegocio(ItemOrdenCompraDto itemDto){
		this.setIdItemOrdenCompra(itemDto.getNroItemOrdenCompra());
		this.setCantidad(itemDto.getCantidad());
		this.setMonto(itemDto.getMonto());
		RodamientoNegocio roda = new RodamientoNegocio();
		roda.aRodamientoNegocio(itemDto.getRodamiento());
		this.setRodamiento(roda);
	}
	
	public ItemOrdenCompraDto aItemOrdenCompraDto(){
		ItemOrdenCompraDto itemOrdenDto = new ItemOrdenCompraDto();
		itemOrdenDto.setNroItemOrdenCompra(this.getIdItemOrdenCompra());
		itemOrdenDto.setCantidad(this.getCantidad());
		itemOrdenDto.setMonto(this.getMonto());
		itemOrdenDto.setRodamiento(this.getRodamiento().aRodamientoDto());
		return itemOrdenDto;
	}

	public void persistirItemOrdenCompra() {
		ItemOrdenCompraDAO.getInstancia().persist(this);
	}
	
	public void updateItemOrdenCompra() {
		ItemOrdenCompraDAO.getInstancia().update(this);
	}
	
	public void deleteItemOrdenCompra() {
		ItemOrdenCompraDAO.getInstancia().delete(this);
	}
	
	public void mergeItemOrdenCompra() {
		ItemOrdenCompraDAO.getInstancia().merge(this);
	}

	public int getIdItemOrdenCompra() {
		return idItemOrdenCompra;
	}

	public void setIdItemOrdenCompra(int idItemOrdenCompra) {
		this.idItemOrdenCompra = idItemOrdenCompra;
	}
	
}
