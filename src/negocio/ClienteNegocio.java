package negocio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.ClienteDAO;
import dto.*;


@Entity
@Table(name="Cliente")
public class ClienteNegocio{

	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCliente;
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
	
	
	
	public ClienteNegocio aClienteNegocio(dto.ClienteDto clienteDto) {
		//Creo la salida del metodo
		ClienteNegocio miCliNegocio = new ClienteNegocio();
		//Asigno los atributos simples
		miCliNegocio.setCUIT(clienteDto.getCUIT());
		miCliNegocio.setMail(clienteDto.getMail());
		miCliNegocio.setRazonSocial(clienteDto.getRazonSocial());
	return miCliNegocio;
	}
	
	public ClienteDto aClienteDto() {
		//Creo la salida del metodo
		ClienteDto miCliDto = new ClienteDto();
		//Asigno los atributos simples
		miCliDto.setCUIT(this.getCUIT());
		miCliDto.setMail(this.getMail());
		miCliDto.setRazonSocial(this.getRazonSocial());
	return miCliDto;
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

	public void persistirCliente(){
		ClienteDAO.getInstancia().persist(this);
	}



}
