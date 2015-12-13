package dao;

import hbt.HibernateUtil;

import java.util.List;

import negocio.OrdenCompraNegocio;

import org.hibernate.Session;

public class OrdenCompraDAO extends HibernateDAO{
	private static OrdenCompraDAO instancia;

	private OrdenCompraDAO() {
		
	}
	
	public static OrdenCompraDAO getinstancia(){
		if (instancia == null) 
			instancia = new OrdenCompraDAO();
		return instancia;
	}

	public List<OrdenCompraNegocio> obtenerOrdenCompra() {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<OrdenCompraNegocio> salida = s.createQuery("from OrdenCompraNegocio o").list();
		
		s.close();
		return salida;
	}

	public int obtenerMaximoIDOrdenCompra() {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		int resultado = (int) s.createQuery("select max(o.idOrdenCompra) from OrdenCompraNegocio o").uniqueResult();
		
		s.clear();
		return resultado;
	}

	public OrdenCompraNegocio obtenerOrdenCompraPorId(int nroOrden) {
		Session se = HibernateUtil.getSessionFactory().openSession();
		OrdenCompraNegocio salida = (OrdenCompraNegocio) se.get(OrdenCompraNegocio.class, nroOrden);
		se = null;
		return salida;
	}
	
	
}
