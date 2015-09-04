package negocio;

import java.util.List;

import bean.RodamientoBean;
import dao.RodamientoDAO;
import dto.*;



public class RodamientoNegocio{
	
	private String tipo;
	private String codigo;
	private int stock;
	private int serie;
	private String origen;
	private String marca;
	private String descripcion;
	private float monto;
	private ProveedorNegocio proveedor;
	
	

	public RodamientoNegocio(String tipo, String codigo, int stock, int serie,
			String origen, String marca, String descripcion, float monto,
			ProveedorNegocio proveedor) {
		super();
		this.tipo = tipo;
		this.codigo = codigo;
		this.stock = stock;
		this.serie = serie;
		this.origen = origen;
		this.marca = marca;
		this.descripcion = descripcion;
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
	
	public int getSerie() {
		return serie;
	}
	
	public void setSerie(int serie) {
		this.serie = serie;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
