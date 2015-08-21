package dao;

import hbt.HibernateUtil;

import java.util.List;

import org.hibernate.Session;

import bean.RodamientoBean;

public class RodamientoDAO extends HibernateDAO{

	private static RodamientoDAO instancia;
	
	public static RodamientoDAO getInstancia(){
		if(instancia==null)
			instancia = new RodamientoDAO();
		return instancia;
	}
	
	public List<RodamientoBean> obtenerRodamientos(){
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		List<RodamientoBean> rodamientos = s.createQuery("from RodamientoBean").list();
		
		return rodamientos;
	}
	
}
