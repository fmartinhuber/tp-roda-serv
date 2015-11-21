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

	//Levanta las cotizaciones en un estado pasado por parametro
	public List<CotizacionNegocio> obtenerCotizacionesAprobada(String estado){
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		List<CotizacionNegocio> cotizacionesAprobadas = s.createQuery("from CotizacionNegocio c where c.estado = ?").setParameter(1, estado).list();
		
		List<CotizacionNegocio> cotizacionSalida = new ArrayList<CotizacionNegocio>();
		for(int i=0; i<cotizacionesAprobadas.size(); i++){
			CotizacionNegocio miCotizacion = new CotizacionNegocio();
			cotizacionSalida.add(miCotizacion);
		}
		
		return cotizacionSalida;
	}
	
	//Levantar las cotizaciones de un cliente en estado distinto de "solicitada"
	@SuppressWarnings("unchecked")
	public List<CotizacionNegocio> obtenerCotizacionesDeCiente(ClienteNegocio clie){
		Session se = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CotizacionNegocio> salida;
		Transaction tr = se.getTransaction();
		tr.begin();
		Query q = se.createQuery("from CotizacionNegocio cot "
				+ "where cot.cliente = :clie "
				+ "and cot.estado <> 'solicitada'").setParameter("clie", clie);
		salida = q.list();
		tr.commit();
		se = null;
		return salida;
	}
	
	// Levantar cotizacion segun id recibido
	public CotizacionNegocio buscarCotizacion(int idCot){
		Session se = HibernateUtil.getSessionFactory().openSession();
		CotizacionNegocio salida = (CotizacionNegocio) se.get(CotizacionNegocio.class, idCot);
		se = null;
		return salida;
	}
	
	// Levantar Items Cotización de determinas cotizaciones
	@SuppressWarnings("unchecked")
	public List<Object[]> itemsCotizacionAgrupadosPorRodamiento(List<Integer> cotizaciones){
		Session se = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Object[]> salida;
		Transaction tr = se.getTransaction();
		tr.begin();
		Query q = se.createQuery("Select ro.IdRodamiento, sum(itCot.cant), sum(itCot.subtotal)  "
				+ "from CotizacionNegocio cot join cot.items itCot join itCot.rodamiento ro "
				+ "where cot.idCotizacion in (:ids) "
				+ "group by ro.IdRodamiento").setParameterList("ids", cotizaciones);
		salida = q.list();
		tr.commit();
		se = null;
		return salida;
	}
	
	// Levantar Rodamientos de Items Cotización de determinas cotizaciones para un proveedor dado
		@SuppressWarnings("unchecked")
		public List<Object[]> rodamientoDeItemsCotizacionAgrupadosPorRodamiento(ProveedorNegocio prove){
			Session se = HibernateUtil.getSessionFactory().getCurrentSession();
			List<Object[]> salida;
			Transaction tr = se.getTransaction();
			tr.begin();
			Query q = se.createQuery("Select ro, sum(itCot.cant), sum(itCot.subtotal) "
					+ "from CotizacionNegocio cot join cot.items itCot join itCot.rodamiento ro join ro.proveedor prov "
					+ "where prov = (:ids) "
					+ "group by ro.IdRodamiento").setParameter("ids", prove);
			salida = q.list();
			tr.commit();
			se = null;
			return salida;
		}
	
		// Levantar proveedores de los rodamiento de los Items Cotización de determinas cotizaciones
		@SuppressWarnings("unchecked")
		public List<ProveedorNegocio> proveedorDeItemsCotizacion(List<CotizacionNegocio> cotizaciones){
			Session se = HibernateUtil.getSessionFactory().getCurrentSession();
			List<ProveedorNegocio> salida;
			Transaction tr = se.getTransaction();
			tr.begin();
			Query q = se.createQuery("Select prov "
					+ "from CotizacionNegocio cot join cot.items itCot join itCot.rodamiento ro join ro.proveedor prov "
					+ "where cot in (:ids) "
					+ "group by prov ").setParameterList("ids", cotizaciones);
			salida = q.list();
			tr.commit();
			se = null;
			return salida;
		}

}
