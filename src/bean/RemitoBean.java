package bean;

import java.util.*;
import javax.persistence.*;



@Entity
@Table(name="Remito")
public class RemitoBean{
	
	@Id 
	@GeneratedValue (strategy=GenerationType.AUTO)
		private int idRemito;
	private String estado;
	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="remito_cliente")
		private ClienteBean cliente;
	@OneToMany
	@JoinColumn(name="remito_orden")
		private List <CotizacionBean> cotizaciones;
	private String comentarios;
	private Date fecha;
	private boolean conformidad;
	
	
}
