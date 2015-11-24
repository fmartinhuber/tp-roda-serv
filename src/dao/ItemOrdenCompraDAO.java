package dao;

import hbt.HibernateUtil;

import java.util.List;

import org.hibernate.Session;

import negocio.ItemOrdenCompraNegocio;

public class ItemOrdenCompraDAO extends HibernateDAO{
	
	private static ItemOrdenCompraDAO instancia;
	
	public ItemOrdenCompraDAO(){};
	
	public static ItemOrdenCompraDAO getInstancia(){
		if(instancia==null)
			instancia = new ItemOrdenCompraDAO();
		return instancia;
	}

	public List<ItemOrdenCompraNegocio> listarItemsOrdenCompra() {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<ItemOrdenCompraNegocio> salida = s.createQuery("from ItemOrdenCompraNegocio i").list();
		s.close();
		
		return salida;
	}

}
