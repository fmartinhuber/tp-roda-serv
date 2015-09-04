package negocio;

import dto.*;



public class ClienteNegocio{

	private String razonSocial;
	private String mail;
	private String CUIT;
	
	
	
	public ClienteNegocio(String razonSocial, String mail, String CUIT) {
		super();
		this.razonSocial = razonSocial;
		this.mail = mail;
		this.CUIT = CUIT;
	}
	
	public ClienteNegocio(){
		
	}
	
	
	
	public ClienteNegocio transformarClienteDtoAClienteNegocio(ClienteDto miCliDto) {
		//Creo la salida del metodo
			ClienteNegocio miCliNegocio = new ClienteNegocio();
		//Asigno los atributos simples
			miCliNegocio.setCUIT(miCliDto.getCUIT());
			miCliNegocio.setMail(miCliDto.getMail());
			miCliNegocio.setRazonSocial(miCliDto.getRazonSocial());
		return miCliNegocio;
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
