package negocio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dao.RodamientoDAO;
import dto.*;


@Entity
@Table(name="Rodamiento")
public class RodamientoNegocio{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int IdRodamiento; 
	private String tipo;
	private String codigo;
	private int stock;
	private String origen;
	private String caracteristica;
	private float monto;
	private String marca;
	@ManyToOne
	@JoinColumn(name="IdProveedor") //Aqui va el nombre del campo clave de la tabla relacionada
	private ProveedorNegocio proveedor;
	//private float costo;
	//private float ganancia;
	
	public RodamientoNegocio(){
		
	}
	
	
	public RodamientoNegocio(String tipo, String codigo, int stock, String origen, 
			String marca, String caracteristica, float monto,
			ProveedorNegocio proveedor) {
		super();
		this.tipo = tipo;
		this.codigo = codigo;
		this.stock = stock;
		this.origen = origen;
		this.marca = marca;
		this.caracteristica = caracteristica;
		this.monto = monto;
		this.proveedor = proveedor;
	}


	public RodamientoNegocio aRodamientoNegocio(RodamientoDto miRodaDto) {
		RodamientoNegocio roda = new RodamientoNegocio ();
		roda.setCaracteristica(miRodaDto.getCaracteristica());
		roda.setCodigo(miRodaDto.getCodigo());
		roda.setMarca(miRodaDto.getMarca());
		roda.setMonto(miRodaDto.getMonto());
		roda.setOrigen(miRodaDto.getOrigen());
		roda.setStock(miRodaDto.getStock());
		roda.setTipo(miRodaDto.getTipo());
		ProveedorNegocio prove = new ProveedorNegocio();
		prove.aProveedorNegocio(miRodaDto.getProveedor());
		roda.setProveedor(prove);
		return roda;
	}
	
//	public RodamientoDto aRodamientoDto() {
//		RodamientoDto roda = new RodamientoDto();
//		roda.setCaracteristica(caracteristica);
//		roda.setCodigo(codigo);
//		roda.setMarca(marca);
//		roda.setMonto(monto);
//		roda.setOrigen(origen);
//		roda.setStock(stock);
//		roda.setProveedor(proveedor.aProveedorDto());
//		roda.setTipo(tipo);
//		return roda;
//	}
//	
//	public RodamientoDto aRodamientoDto() {
//		RodamientoDto roda = new RodamientoDto();
//		roda.setCaracteristica(this.getCaracteristica());
//		roda.setCodigo(this.getCodigo());
//		roda.setMarca(this.getMarca());
//		roda.setMonto(this.getMonto());
//		roda.setOrigen(this.getOrigen());
//		roda.setStock(this.getStock());
//		roda.setTipo(this.getTipo());
//		roda.setProveedor(this.getProveedor().aProveedorDto());
//		return roda;
//	}

	public float getMonto() {
		return monto;
	}
	
	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public String getOrigen() {
		return origen;
	}
	
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public ProveedorNegocio getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(ProveedorNegocio proveedor) {
		this.proveedor = proveedor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}
	
	public void persistirRodamiento(){
		RodamientoDAO.getInstancia().persist(this);
	}
	
}
