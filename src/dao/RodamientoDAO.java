package dao;

import hbt.HibernateUtil;

import java.util.List;

import negocio.RodamientoNegocio;

import org.hibernate.Session;


public class RodamientoDAO extends HibernateDAO{

	private static RodamientoDAO instancia;
	
	public static RodamientoDAO getInstancia(){
		if(instancia==null)
			instancia = new RodamientoDAO();
		return instancia;
	}
	
	public List<RodamientoNegocio> obtenerRodamientos(){
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		List<RodamientoNegocio> rodamientos = s.createQuery("from RodamientoBean r").list();
		
		return rodamientos;
	}
	
}
