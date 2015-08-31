package negocio;

import dto.*;



public class ClienteNegocio{

	private String razonSocial;
	private String mail;
	private String CUIT;
	private int unidades;
	private int meses;
	private int operaciones;
	
	
	
	public ClienteNegocio(String razonSocial, String mail, String CUIT, int unidades,
			int meses, int operaciones) {
		super();
		this.razonSocial = razonSocial;
		this.mail = mail;
		this.CUIT = CUIT;
		this.unidades = unidades;
		this.meses = meses;
		this.operaciones = operaciones;
	}
	
	public ClienteNegocio(){
		
	}
	
	public ClienteNegocio transformarClienteDtoAClienteNegocio(ClienteDto miCliDto) {
		//Creo la salida del metodo
			ClienteNegocio miCliNegocio = new ClienteNegocio();
		//Asigno los atributos simples
			miCliNegocio.setCUIT(miCliDto.getCUIT());
			miCliNegocio.setMail(miCliDto.getMail());
			miCliNegocio.setMeses(miCliDto.getMeses());
			miCliNegocio.setOperaciones(miCliDto.getOperaciones());
			miCliNegocio.setRazonSocial(miCliDto.getRazonSocial());
			miCliNegocio.setUnidades(miCliDto.getUnidades());
		return miCliNegocio;
	}
	
	
	public int getUnidades() {
		return unidades;
	}
	
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	public int getMeses() {
		return meses;
	}
	
	public void setMeses(int meses) {
		this.meses = meses;
	}
	
	public int getOperaciones() {
		return operaciones;
	}
	
	public void setOperaciones(int operaciones) {
		this.operaciones = operaciones;
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
