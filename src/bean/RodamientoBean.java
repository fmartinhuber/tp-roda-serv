package bean;

import javax.persistence.*;

import dto.RodamientoDto;



@Entity
@Table(name="Rodamiento")
public class RodamientoBean{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int IdRodamiento; 
	private String tipo;
	private String codigo;
	private int stock;
	private int medida;
	private String caracteristicas;
	private int serie;
	private String origen;
	private float monto;
	private String nombre;
	private String pais;
	private String descripcion; 
	
	
	public int getIdRodamiento() {
		return IdRodamiento;
	}
	public void setIdRodamiento(int idRodamiento) {
		IdRodamiento = idRodamiento;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public String getTipo() {
		return tipo;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
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
	public RodamientoBean(RodamientoDto r) {
		super();
		this.tipo = r.getTipo();
		this.codigo = r.getCodigo();
		this.stock = r.getStock();
		this.medida = r.getMedida();
		this.caracteristicas = r.getCaracteristicas();
		this.serie = r.getSerie();
		this.origen = r.getOrigen();
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
