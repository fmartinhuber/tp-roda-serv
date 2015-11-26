package dao;

import org.hibernate.Session;

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
	
	
}
