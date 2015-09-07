package bean;

import javax.persistence.*;



@Entity
@Table(name="Rodamiento")
public class RodamientoBean{
	
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
	private float costo;
	private float ganancia;
	
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

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public float getGanancia() {
		return ganancia;
	}

	public void setGanancia(float ganancia) {
		this.ganancia = ganancia;
	}	
	
}
