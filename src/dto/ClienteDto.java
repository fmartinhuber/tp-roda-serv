package dto;

import java.io.Serializable;



public class ClienteDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String razonSocial;
	private String mail;
	private String CUIT;
	
	
	
	public ClienteDto(String razonSocial, String mail, String CUIT) {
		super();
		this.razonSocial = razonSocial;
		this.mail = mail;
		this.CUIT = CUIT;
	}
	
	public ClienteDto(){
		
	}
	
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
	
	
}
