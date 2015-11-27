package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hbt.HibernateUtil;
import negocio.RemitoNegocio;

public class RemitoDAO extends HibernateDAO{
	
	private static RemitoDAO instancia;

	private RemitoDAO() {
		
	}
	
	public static RemitoDAO getinstancia(){
		if (instancia == null) 
			instancia = new RemitoDAO();
		return instancia;
	}
	
	public RemitoNegocio buscarRemito(int idRemito) {
		Session se = HibernateUtil.getSessionFactory().openSession();
		RemitoNegocio salida = (RemitoNegocio) se.get(RemitoNegocio.class, idRemito);
		se.close();
	return salida;
	}

	public int obtenerMaximoIDRemito() {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		int resultado = (int) s.createQuery("select max(r.idRemito) from RemitoNegocio r").uniqueResult();
		
		s.clear();
		return resultado;		
	}
	
	
}
