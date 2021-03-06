package negocio;

import javax.persistence.*;

import dao.*;
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
	@ManyToOne (cascade=CascadeType.ALL, fetch = FetchType.EAGER)
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


	public void aRodamientoNegocio (RodamientoDto miRodaDto) {
		this.setIdRodamiento(miRodaDto.getNumeroRodamiento());
		this.setCaracteristica(miRodaDto.getCaracteristica());
		this.setCodigo(miRodaDto.getCodigo());
		this.setMarca(miRodaDto.getMarca());
		this.setMonto(miRodaDto.getMonto());
		this.setOrigen(miRodaDto.getOrigen());
		this.setStock(miRodaDto.getStock());
		this.setTipo(miRodaDto.getTipo());
		ProveedorNegocio prove = new ProveedorNegocio();
		prove.aProveedorNegocio(miRodaDto.getProveedor());
		this.setProveedor(prove);
	}
	
	public RodamientoDto aRodamientoDto () {
		RodamientoDto roda = new RodamientoDto();
		roda.setNumeroRodamiento(this.getIdRodamiento());
		roda.setCaracteristica(this.getCaracteristica());
		roda.setCodigo(this.getCodigo());
		roda.setMarca(this.getMarca());
		roda.setMonto(this.getMonto());
		roda.setOrigen(this.getOrigen());
		roda.setStock(this.getStock());
		roda.setTipo(this.getTipo());
		roda.setProveedor(this.getProveedor().aProveedorDto());
	return roda;
	}

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


	public int getIdRodamiento() {
		return IdRodamiento;
	}


	public void setIdRodamiento(int idRodamiento) {
		IdRodamiento = idRodamiento;
	}
	
	public RodamientoNegocio buscarRodamientoPorCodigoMarcaOrigen(RodamientoNegocio r){
		return RodamientoDAO.getInstancia().buscarRodamientoPorCodigoMarcaOrigen(r);
	}
	
	public void actualizarRodamiento(){
		RodamientoDAO.getInstancia().update(this);
	}
	
	public void mergeRodamiento(){
		RodamientoDAO.getInstancia().merge(this);
	}


	public int buscarStock(RodamientoNegocio r) {
		return RodamientoDAO.getInstancia().buscarStock(r);
	}


	public float getValorStrategy(ProveedorNegocio proveedorNegocio, int cantidad, String formaDePago) {
		// TODO Aqui deberíamos aplicar la estrategia de precio del proveedor a nosotros como empresa
		
		// Estrategía Actual: PrecioUnitario por Cantidad!!!
		
		return (this.getMonto()*cantidad);
	}
	
}
