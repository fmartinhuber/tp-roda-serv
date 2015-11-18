package dao;

import hbt.HibernateUtil;

import java.util.List;

import negocio.CotizacionNegocio;
import negocio.RodamientoNegocio;

import org.hibernate.Session;

import dto.RodamientoDto;


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
		RodamientoNegocio salida = (RodamientoNegocio) s.createQuery("from RodamientoNegocio where r.codigo = " + rodamiento.getCodigo() + " and r.marca = " +rodamiento.getMarca() + " and r.origen = " + rodamiento.getOrigen()).uniqueResult();
		s = null;
		return salida;
	}
}
