package dao;

import hbt.HibernateUtil;

import java.util.*;

import negocio.*;

import org.hibernate.*;

public class CotizacionDAO extends HibernateDAO{
	
	private static CotizacionDAO instancia;

	private CotizacionDAO() {
		super();
	}
	
	public static CotizacionDAO getinstancia(){
		if (instancia == null) 
			instancia = new CotizacionDAO();
		return instancia;
	}
	
	// Levantar cotizacion segun id recibido
	public CotizacionNegocio buscarCotizacion(int idCot){
		Session se = HibernateUtil.getSessionFactory().openSession();
		CotizacionNegocio salida = (CotizacionNegocio) se.get(CotizacionNegocio.class, idCot);
		se = null;
		return salida;
	}
	
	// Carlos: Levantar todas las cotizaciones de una OV y en un estado pasado por parametro
	// Se usa para Factura (cotizaciones asociadas a una factura)
	@SuppressWarnings("unchecked")
	public List<CotizacionNegocio> cotizacionesXovYestado (OVNegocio ov, String estado){
		Session se = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CotizacionNegocio> salida = new ArrayList<CotizacionNegocio>();
		List<CotizacionNegocio> sali2 = new ArrayList<CotizacionNegocio>();
		Transaction trx = se.getTransaction();
		trx.begin();
		Query q = se.createQuery("select cotis "
				+ "from OVNegocio ovn join ovn.cotizaciones cotis "
				+ "where ovn = :ov ").setParameter("ov", ov);
		salida = q.list();
		trx.commit();
		se = null;
		for(int i = 0; i < salida.size(); i++){
			if (salida.get(i).getEstado().compareTo(estado) == 0)
				sali2.add(salida.get(i));
		}
		return sali2;
	}
	
	// Carlos: Levantar Rodamiento, cantidad y subtotal de los itemsCotización de un listado de cotizaciones 
	// para una ov un estado determinado y para un cliente
	// Se usa para generar los itemsFactura!!
	@SuppressWarnings("unchecked")
	public List<Object[]> rodaPorItemsCotizacion_OV_Estado_x_Cliente(List<CotizacionNegocio> cotizaciones, 
			OVNegocio ov, String estado, ClienteNegocio clie){
		Session se = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Object[]> salida;
		Transaction tr = se.getTransaction();
		tr.begin();
		Query q = se.createQuery("Select ro.IdRodamiento, sum(itCot.cant), sum(itCot.precio)  "
				+ "from OVNegocio ov join ov.cotizaciones cot join cot.cliente cli join cot.items itCot join itCot.rodamiento ro "
				+ "where ov = :ov "
				+ "and cot.estado = :estado "
				+ "and cot in (:ids) "
				+ "and cli = :cliente "
				+ "group by ro.IdRodamiento ").setParameter("ov", ov).setParameter("estado", estado)
				.setParameter("cliente", clie)
				.setParameterList("ids", cotizaciones);
		salida = q.list();
		tr.commit();
		se = null;
		return salida;
	}
	
	//Daro: Levanta el maximo ID de la tabla Cotizaciones, esto se realiza para devolver el id en las creaciones
	public int obtenerMaximoIDCotizacion (){
		Session se = HibernateUtil.getSessionFactory().getCurrentSession();
		int salida = 0;
		Transaction tr = se.getTransaction();
		tr.begin();
		Query q = se.createQuery("select max(c.idCotizacion) from CotizacionNegocio c");
		salida = (int) q.uniqueResult();
		tr.commit();
		se = null;
	return salida;
	}

	
	public List <CotizacionNegocio> obtenerCotizaciones() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		List <CotizacionNegocio> salida =  s.createQuery("from CotizacionNegocio c").list();
		s.close();
		return salida;
	}

	// Carlos; Levanto los clientes de una colección de cotizaciones pasadas por parametro de una ov
	// Lo uso para el masivo de generar factura
	@SuppressWarnings("unchecked")
	public List<ClienteNegocio> clientesDeListadoCotizacionXovYestado (List<CotizacionNegocio> cotizaciones, String estado, OVNegocio ov){
		Session se = HibernateUtil.getSessionFactory().getCurrentSession();
		List<ClienteNegocio> salida = new ArrayList<ClienteNegocio>();
		List<String> cuitClientes;
		Transaction tr = se.getTransaction();
		tr.begin();
		Query q = se.createQuery("Select cli.CUIT  "
				+ "from OVNegocio ov "
				+ "join ov.cotizaciones cot "
				+ "join cot.cliente cli "
				+ "where ov = :ov "
				+ "and cot.estado = :estado "
				+ "and cot in (:ids) "
				+ "group by cli.CUIT ").setParameter("ov", ov).setParameter("estado", estado)
				.setParameterList("ids", cotizaciones);
		cuitClientes = q.list();
		tr.commit();
		se = null;
		// Busco cada uno de los proveedores por el ID devueltos
		for (int i = 0; i < cuitClientes.size(); i++) {
			ClienteNegocio cliente = ClienteDAO.getInstancia().buscarClientePorCUIT(cuitClientes.get(i));
			salida.add(cliente);
		}
		return salida;
	}
	
	// Carlos; Levanto las cotizaciones de un cliente pasadas por parametro de una ov yb una colecciones de cotizaciones predeterminada
	// Lo uso para el masivo de generar factura
		@SuppressWarnings("unchecked")
		public List<CotizacionNegocio> cotizacionXovYestadoYcliente (List<CotizacionNegocio> cotizaciones, String estado, 
				OVNegocio ov, ClienteNegocio clie){
			Session se = HibernateUtil.getSessionFactory().getCurrentSession();
			List<CotizacionNegocio> salida = new ArrayList<CotizacionNegocio>();
			Transaction tr = se.getTransaction();
			tr.begin();
			Query q = se.createQuery("Select cot  "
					+ "from OVNegocio ov "
					+ "join ov.cotizaciones cot "
					+ "join cot.cliente cli "
					+ "where ov = :ov "
					+ "and cot.estado = :estado "
					+ "and cli = :cli "
					+ "and cot in (:ids) ").setParameter("ov", ov).setParameter("estado", estado).setParameter("cli", clie)
					.setParameterList("ids", cotizaciones);
			salida = q.list();
			tr.commit();
			se = null;
			return salida;
		}

}
