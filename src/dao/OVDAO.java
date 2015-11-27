package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hbt.HibernateUtil;
import negocio.CotizacionNegocio;
import negocio.OVNegocio;

public class OVDAO extends HibernateDAO {
	
	private static OVDAO instancia;
	
	public static OVDAO getInstancia(){
		if(instancia==null)
			instancia = new OVDAO();
		return instancia;
	}

	//Levanta las cotizaciones de una OV en un estado pasado por parametro
	public List<CotizacionNegocio> obtenerCotizacionesPorOVAprobada(String estado, OVNegocio ov){
		Session s = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<CotizacionNegocio> cotizacionesAprobadas = s.createQuery(" select cot "
				+ "from OVNegocio ovn join ovn.cotizaciones cot "
				+ "where cot.estado = :estado "
				+ "and onv = :ov ").setParameter("estado", estado).setParameter("ov", ov).list();
		
//		List<CotizacionNegocio> cotizacionSalida = new ArrayList<CotizacionNegocio>();
//		for(int i=0; i<cotizacionesAprobadas.size(); i++){
//			CotizacionNegocio miCotizacion = new CotizacionNegocio();
//			cotizacionSalida.add(miCotizacion);
//		}
		
		return cotizacionesAprobadas;
	}
	
	public OVNegocio obtenerOV (int numeroOV){
		Session se = HibernateUtil.getSessionFactory().getCurrentSession();
		OVNegocio salida;
		Transaction trx = se.getTransaction();
		trx.begin();
		Query q = se.createQuery("from OVNegocio ov "
				+ "where ov.idAdministracionOV = :numeroOV ").setParameter("numeroOV", numeroOV);
		salida = (OVNegocio) q.uniqueResult();
		trx.commit();
		se = null;
		return salida;
	}
}
