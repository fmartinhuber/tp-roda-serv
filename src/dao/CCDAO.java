package dao;

import hbt.HibernateUtil;

import java.util.List;

import org.hibernate.Session;

import bean.RodamientoBean;

public class CCDAO extends HibernateDAO{

	private static ClienteDAO instancia;
	
	public static ClienteDAO getInstancia(){
		if(instancia==null)
			instancia = new ClienteDAO();
		return instancia;
	}
	
	public void guardarLista(List rodamiento){
		instancia.persistList(rodamiento);
	}
	
	public List <RodamientoBean> obtenerListaComparativaPrincipal(){
		Session s = HibernateUtil.getSessionFactory().openSession();
		return s.createQuery("from CCBean").list();
	}
}
