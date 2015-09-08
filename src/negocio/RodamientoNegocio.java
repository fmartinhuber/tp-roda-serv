package negocio;

import java.util.List;

import bean.RodamientoBean;
import dao.RodamientoDAO;
import dto.*;



public class RodamientoNegocio{
	
	private String tipo;
	private String codigo;
	private int stock;
	private String origen;
	private String marca;
	private String caracteristica;
	private float monto;
	private ProveedorNegocio proveedor;
	
	
	
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

	public RodamientoBean rodamientoNegocioToBean() {
		// TODO Auto-generated method stub
		RodamientoBean miRodamientoBean = new RodamientoBean();
		miRodamientoBean.setCaracteristica(this.getCaracteristica());
		miRodamientoBean.setCodigo(this.getCodigo());
		miRodamientoBean.setMarca(this.getMarca());
		miRodamientoBean.setMonto(this.getMonto());
		miRodamientoBean.setOrigen(this.getOrigen());
		miRodamientoBean.setStock(this.getStock());
		miRodamientoBean.setTipo(this.getTipo());
		return miRodamientoBean;
	}

	public RodamientoNegocio rodamientoBeanToNegocio(RodamientoBean rodamiento) {
		// TODO Auto-generated method stub
		this.setCaracteristica(rodamiento.getCaracteristica());
		this.setCodigo(rodamiento.getCodigo());
		this.setMarca(rodamiento.getMarca());
		this.setMonto(rodamiento.getMonto());
		this.setOrigen(rodamiento.getOrigen());
		//Falta el proveedor en el bean
		//this.setProveedor(rodamiento.ge);
		this.setStock(rodamiento.getStock());
		this.setTipo(rodamiento.getTipo());
		return this;
	}
	
}
