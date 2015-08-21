package bean;

import java.util.*;
import javax.persistence.*;



@Entity
@Table(name="ODV")
public class ODVBean{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int IdODV; 
	@Transient
		private List <ClienteBean> clientes;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ODV_factura")
		private List <FacturaBean> facturas;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ODV_remito")
		private List <RemitoBean> remitos;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ODV_proveedor")
		private List <ProveedorBean> proveedores;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ODV_cotizacion")
		private List <CotizacionBean> cotizaciones;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ODV_ordenesC")
		private List <OrdenPedidoBean> ordenesC;
	private String centroIndustrial;
	
	
		
	public int getIdODV() {
		return IdODV;
	}
	public void setIdODV(int idODV) {
		IdODV = idODV;
	}
	public List<ClienteBean> getClientes() {
		return clientes;
	}
	public void setClientes(List<ClienteBean> clientes) {
		this.clientes = clientes;
	}
	public List<FacturaBean> getFacturas() {
		return facturas;
	}
	public void setFacturas(List<FacturaBean> facturas) {
		this.facturas = facturas;
	}
	public List<RemitoBean> getRemitos() {
		return remitos;
	}
	public void setRemitos(List<RemitoBean> remitos) {
		this.remitos = remitos;
	}
	public List<ProveedorBean> getProveedores() {
		return proveedores;
	}
	public void setProveedores(List<ProveedorBean> proveedores) {
		this.proveedores = proveedores;
	}
	public List<CotizacionBean> getCotizaciones() {
		return cotizaciones;
	}
	public void setCotizaciones(List<CotizacionBean> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}
	public List<OrdenPedidoBean> getOrdenesC() {
		return ordenesC;
	}
	public void setOrdenesC(List<OrdenPedidoBean> ordenesC) {
		this.ordenesC = ordenesC;
	}
	public String getCentroIndustrial() {
		return centroIndustrial;
	}
	public void setCentroIndustrial(String centroIndustrial) {
		this.centroIndustrial = centroIndustrial;
	}
	
}
