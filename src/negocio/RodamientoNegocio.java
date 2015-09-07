package negocio;

import java.util.List;

import bean.RodamientoBean;
import dao.RodamientoDAO;
import dto.*;



public class RodamientoNegocio{
	
	private String tipo;
	private String codigo;
	private int stock;
	private int medida;
	private String nombre;
	private String caracteristicas;
	private int serie;
	private String origen;
	private String marca;
	private float monto;
	private float costo;
	private ProveedorNegocio proveedor;
	
	
	
	public RodamientoNegocio(String tipo, String codigo, int stock, int medida,
			String nombre, String pais, String caracteristicas, int serie,
			String origen, float monto, ProveedorNegocio proveedor) {
		super();
		this.tipo = tipo;
		this.codigo = codigo;
		this.stock = stock;
		this.medida = medida;
		this.nombre = nombre;
		this.caracteristicas = caracteristicas;
		this.serie = serie;
		this.origen = origen;
		this.monto = monto;
		this.proveedor = proveedor;
	}
	
	public RodamientoNegocio(){
		
	}
	
	public void guardarRodamiento(){
		
		RodamientoBean rodamientoBean = new RodamientoBean();
		rodamientoBean.setCodigo(this.codigo);
		
		RodamientoDAO.getInstancia().persist(rodamientoBean);
	}
	
	
	public List <RodamientoBean> obtenerRodamientos(){
		return RodamientoDAO.getInstancia().obtenerRodamientos();
	}

	public RodamientoNegocio transformarRodamientoDtoARodamientoNegocio(RodamientoDto miRodaDto) {
		// TODO Auto-generated method stub
		return null;
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
	
	public int getMedida() {
		return medida;
	}
	
	public void setMedida(int medida) {
		this.medida = medida;
	}
	
	public String getCaracteristicas() {
		return caracteristicas;
	}
	
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	public int getSerie() {
		return serie;
	}
	
	public void setSerie(int serie) {
		this.serie = serie;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public float getGanancia() {
		return (this.monto-this.costo);
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	
}