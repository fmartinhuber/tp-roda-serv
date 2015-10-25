package negocio;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="OV")
public class OVNegocio{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idOV")
	private int idAdministracionOV;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ov_clientes")
	private List <ClienteNegocio> clientes;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ov_facturas")
	private List <FacturaNegocio> facturas;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ov_remitos")
	private List <RemitoNegocio> remitos;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ov_proveedores")
	private List <ProveedorNegocio> proveedores;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ov_cotizaciones")
	private List <CotizacionNegocio> cotizaciones;

	private String centroIndustrial;

	public OVNegocio() {
		
	}

	public int getIdAdministracionOV() {
		return idAdministracionOV;
	}

	public void setIdAdministracionOV(int idAdministracionOV) {
		this.idAdministracionOV = idAdministracionOV;
	}

	public List<ClienteNegocio> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteNegocio> clientes) {
		this.clientes = clientes;
	}

	public List<FacturaNegocio> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<FacturaNegocio> facturas) {
		this.facturas = facturas;
	}

	public List<RemitoNegocio> getRemitos() {
		return remitos;
	}

	public void setRemitos(List<RemitoNegocio> remitos) {
		this.remitos = remitos;
	}

	public List<ProveedorNegocio> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedorNegocio> proveedores) {
		this.proveedores = proveedores;
	}

	public List<CotizacionNegocio> getCotizaciones() {
		return cotizaciones;
	}

	public void setCotizaciones(List<CotizacionNegocio> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}

	public String getCentroIndustrial() {
		return centroIndustrial;
	}

	public void setCentroIndustrial(String centroIndustrial) {
		this.centroIndustrial = centroIndustrial;
	}
	
}