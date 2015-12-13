package negocio;

import javax.persistence.*;

import dao.*;
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
	private String password;
	
	@Column(name = "ov_clientes")
	private String ov;
	
	public ClienteNegocio(String razonSocial, String mail, String CUIT, String password) {
		super();
		this.razonSocial = razonSocial;
		this.mail = mail;
		this.CUIT = CUIT;
		this.password = password;
	}
	
	/**
	 * OJO QUE SETEA UNA OV ESTE CONSTRUCTOR.
	 * @param razonSocial
	 * @param mail
	 * @param CUIT
	 * @param password
	 * @param ov
	 */
	public ClienteNegocio(String razonSocial, String mail, String CUIT, String password, String ov) {
		super();
		this.razonSocial = razonSocial;
		this.mail = mail;
		this.CUIT = CUIT;
		this.password = password;
		this.ov = ov;
	}
	
	public ClienteNegocio(){
		
	}
	
	public void aClienteNegocio(dto.ClienteDto clienteDto) {
		//Asigno los atributos simples
		this.setCUIT(clienteDto.getCUIT());
		this.setMail(clienteDto.getMail());
		this.setRazonSocial(clienteDto.getRazonSocial());
		this.setIdCliente(clienteDto.getNumeroCliente());
		this.setOv(clienteDto.getOv());
		this.setPassword(clienteDto.getPassword());
	}
	
	public ClienteDto aClienteDto() {
		//Creo la salida del metodo
		ClienteDto miCliDto = new ClienteDto();
		//Asigno los atributos simples
		miCliDto.setCUIT(this.getCUIT());
		miCliDto.setMail(this.getMail());
		miCliDto.setRazonSocial(this.getRazonSocial());
		miCliDto.setNumeroCliente(this.getIdCliente());
		miCliDto.setPassword(this.getPassword());
		miCliDto.setOv(this.getOv());
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void updateCliente() {
		ClienteDAO.getInstancia().update(this);		
	}
	
	public void persistirCliente(){
		ClienteDAO.getInstancia().persist(this);
	}
	
	public void deleteCliente(){
		ClienteDAO.getInstancia().delete(this);
	}
	
	public void mergeCliente(){
		ClienteDAO.getInstancia().merge(this);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOv() {
		return ov;
	}

	public void setOv(String ov) {
		this.ov = ov;
	}

}
