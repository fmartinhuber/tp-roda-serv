package dao;

import hbt.HibernateUtil;

import java.util.List;

import negocio.SolicitudCompraNegocio;

import org.hibernate.Session;

public class SolicitudCompraDAO extends HibernateDAO{
	
	private static SolicitudCompraDAO instancia;
	
	public SolicitudCompraDAO(){};
	
	public static SolicitudCompraDAO getInstancia(){
		if(instancia==null)
			instancia = new SolicitudCompraDAO ();
		return instancia;
	}
		
	public List<SolicitudCompraNegocio> listarSolicitudesCompra(){	
		
		Session s = HibernateUtil.getSessionFactory().openSession();				
		@SuppressWarnings("unchecked")
		List<SolicitudCompraNegocio> solicitudes = s.createQuery("from SolicitudCompraNegocio s").list();
		
		return solicitudes ;
	}

}
