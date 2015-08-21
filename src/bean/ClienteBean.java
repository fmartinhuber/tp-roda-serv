package bean;

import javax.persistence.*;

import dto.ClienteDto;



@Entity
@Table(name="Cliente")
public class ClienteBean{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int idCliente;
	private String razonSocial;
	private String mail;
	private int CUIT;
	
	
	
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
	public int getCUIT() {
		return CUIT;
	}
	public void setCUIT(int cUIT) {
		this.CUIT = cUIT;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public ClienteBean(ClienteDto c) {
		super();
		this.idCliente = c.getIdCliente();
		this.razonSocial = c.getRazonSocial();
		this.mail = c.getMail();
		this.CUIT = c.getCUIT();
	}
	
	
}
