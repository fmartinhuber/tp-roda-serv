package dao;

import hbt.HibernateUtil;

import java.util.List;

import negocio.*;

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
		List<RodamientoNegocio> rodamientos = s.createQuery("from RodamientoNegocio r").list();
		
		return rodamientos;
	}
	
	// Levantar rodamiento segun id recibido
	public RodamientoNegocio buscarRodamiento(int idRota){
		Session se = HibernateUtil.getSessionFactory().openSession();
		RodamientoNegocio salida = (RodamientoNegocio) se.get(RodamientoNegocio.class, idRota);
		se = null;
		return salida;
	}
	
	public RodamientoNegocio buscarRodamientoPorCodigoMarcaOrigen(RodamientoNegocio rodamiento){
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		RodamientoNegocio salida = (RodamientoNegocio) s.createQuery("from RodamientoNegocio r where "
				+ "r.codigo like '" +rodamiento.getCodigo() + "'" + " "
				+ "and r.marca like '" +rodamiento.getMarca() + "'" + " "
				+ "and r.origen like '" +rodamiento.getOrigen() +"'").uniqueResult();
		s = null;
		return salida;
	}

	public int buscarStock(RodamientoNegocio r) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		int stock = (int) s.createQuery("select r.stock from RodamientoNegocio r where r.codigo like '" + r.getCodigo() + "'" + " and r.marca like '" +r.getMarca() + "'" + " and r.origen like '" +r.getOrigen() + "'" ).uniqueResult();
		
		s = null;
		return stock;
	}
}
