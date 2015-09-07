package bean;

import javax.persistence.*;



@Entity
@Table(name="Cliente")
public class ClienteBean{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int idCliente;
	private String razonSocial;
	private String mail;
	private String CUIT;
	
	
	
	public String getRazonSocial() {
		return razonSocial;
	}
	
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getCUIT() {
		return CUIT;
	}
	
	public void setCUIT(String CUIT) {
		this.CUIT = CUIT;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	
}
