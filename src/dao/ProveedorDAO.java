package dao;

import hbt.HibernateUtil;
import negocio.ProveedorNegocio;

import org.hibernate.Session;

public class ProveedorDAO extends HibernateDAO{

	private static ProveedorDAO instancia;

	private ProveedorDAO(){};
	
	public static ProveedorDAO getInstancia(){
		if (instancia == null) 
			instancia = new ProveedorDAO();
		return instancia;
	}
	
	public ProveedorNegocio buscarProveedorPorCUIT(String cuit) {
		
		Session se = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Query query = se.createQuery("from ProveedorNegocio p where p.CUIT = :nombrePar");
		query.setParameter("nombrePar", cuit);
		ProveedorNegocio proveedor = (ProveedorNegocio) query.uniqueResult();
		se.close();
		return proveedor ;		
	}
	
}
