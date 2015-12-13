package dao;

import hbt.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import negocio.CCNegocio;

public class CCDAO extends HibernateDAO{

	private static CCDAO instancia;
	
	public static CCDAO getInstancia(){
		if(instancia==null)
			instancia = new CCDAO();
		return instancia;
	}

	public CCNegocio obtenerCC(int numeroCC) {
		Session se = HibernateUtil.getSessionFactory().getCurrentSession();
		CCNegocio salida;
		Transaction trx = se.getTransaction();
		trx.begin();
		Query q = se.createQuery("from CCNegocio cc "
				+ "where cc.idAdministracionCC = :numeroCC ").setParameter("numeroCC", numeroCC);
		salida = (CCNegocio) q.uniqueResult();
		trx.commit();
		se = null;
		return salida;
	}
				
	
}
