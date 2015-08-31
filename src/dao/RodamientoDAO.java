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
		/*Daro: Hacer que busque con where los parametros que se pasan, esta mal levantar toda una tabla
		Si queres levantar toda la tabla pasarle % % % % */
		
		/*Rama: voy a sacarle los parámetros para que sea un método prueba
		 */
		List<RodamientoBean> rodamientos = s.createQuery("from RodamientoBean r").list();
		
		return rodamientos;
	}
	
}
