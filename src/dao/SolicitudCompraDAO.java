package dao;

import hbt.HibernateUtil;

import java.util.List;

import negocio.ProveedorNegocio;
import negocio.SolicitudCompraNegocio;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

	public List<SolicitudCompraNegocio> listarSolicitudesCompraAprobadas() {

		Session s = HibernateUtil.getSessionFactory().openSession();				
		@SuppressWarnings("unchecked")
		List<SolicitudCompraNegocio> solicitudes = s.createQuery("from SolicitudCompraNegocio s where s.estado = 'aprobada'").list();
		
		return solicitudes ;
		
	}
	
	// Carlos: Levantar proveedores de los rodamiento de las cotizaciones contenidas en un arrar de solicitudes de Compra
		@SuppressWarnings("unchecked")
		public List<ProveedorNegocio> proveedoresDeSolicitudCompra(List<SolicitudCompraNegocio> solCotis){
			Session se = HibernateUtil.getSessionFactory().getCurrentSession();
			List<ProveedorNegocio> salida;
			Transaction tr = se.getTransaction();
			tr.begin();
			Query q = se.createQuery("Select prov "
					+ "from SolicitudCompraNegocio scn left join listaCotizaciones cot join cot.items itCot join itCot.rodamiento ro join ro.proveedor prov "
					+ "where scn in (:ids) "
					+ "group by prov ").setParameterList("ids", solCotis);
			salida = q.list();
			tr.commit();
			se = null;
			return salida;
		}
		
	// Carlos: NECESARIO PARA PRUEBAS
	// 	       Levanta las solicitudes en un estado determinado
		@SuppressWarnings("unchecked")
		public List<SolicitudCompraNegocio> solicitudCompraXestado(String estado){
			Session se = HibernateUtil.getSessionFactory().getCurrentSession();
			List<SolicitudCompraNegocio> salida;
			Transaction tr = se.getTransaction();
			tr.begin();
			Query q = se.createQuery("from SolicitudCompraNegocio solComp "
					+ "where solComp.estado like :estado ").setParameter("estado", estado);
			salida = q.list();
			tr.commit();
			se = null;
			return salida;
		}

}
