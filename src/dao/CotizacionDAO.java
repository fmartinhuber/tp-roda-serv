package dao;

import hbt.HibernateUtil;

import java.util.List;

import org.hibernate.Session;

import bean.CotizacionBean;
import bean.RodamientoBean;

public class CotizacionDAO extends HibernateDAO{
	
	private static CotizacionDAO instancia;

	private CotizacionDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static CotizacionDAO getinstancia(){
		
		if (instancia == null) 
			instancia = new CotizacionDAO();
		return instancia;
	}

	//Levanta las cotizaciones en un estado pasado por parametro
	public List<CotizacionBean> obtenerCotizacionesAprobada(String estado){
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		List<CotizacionBean> cotizacionesAprobadas = s.createQuery("from CotizacionBean c where c.estado = ?").setParameter(1, estado).list();
		
		return cotizacionesAprobadas;
	}
}
