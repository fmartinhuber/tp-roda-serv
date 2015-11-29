package dao;

import java.util.List;

import hbt.HibernateUtil;
import negocio.ProveedorNegocio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateDAO{
	protected static HibernateDAO instancia = null;
	protected static SessionFactory sf = null;
	protected static Session session = null;

	
	public static HibernateDAO getInstancia(){
		if(instancia == null){
			instancia = new HibernateDAO();
		} 
		return instancia;
	}
	
	protected HibernateDAO()  {
		sf = HibernateUtil.getSessionFactory();
	}

	protected Session getSession(){
		if(session == null || !session.isOpen()){
			session = sf.openSession();
		}
		return session;
	}
	
	public void closeSession(){
		if (session.isOpen()) {
			session.close();
		}
	}
	
	public void persist(Object obj) {
		Session session = getSession();
		session.beginTransaction();
		session.persist(obj);
		session.getTransaction().commit();
	}
	
	public void merge(Object obj) {
		Session session = getSession();
		session.beginTransaction();
		session.merge(obj);		
		session.getTransaction().commit();
	}
	
	public void delete(Object obj) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(obj);		
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("rawtypes")
	public void persistList(List lista){
		Session session = getSession();
		session.beginTransaction();
		for(Object e : lista){
			session.saveOrUpdate(e);
		}
		session.flush();
		session.getTransaction().commit();
	}
	
	public void update(Object obj) {
		Session session = getSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
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