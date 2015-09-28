package dao;

import hbt.HibernateUtil;

import java.util.List;

import org.hibernate.Session;

import negocio.*;

public class ClienteDAO extends HibernateDAO {
	
	private static ClienteDAO instancia;
	
	public static ClienteDAO getInstancia(){
		if(instancia==null)
			instancia = new ClienteDAO();
		return instancia;
	}

	public List<ClienteNegocio> listarClientes(){
		
		Session s = HibernateUtil.getSessionFactory().openSession();
				
		@SuppressWarnings("unchecked")
		List<ClienteNegocio> clientes = s.createQuery("from ClienteNegocio c").list();
		
		return clientes;
	}
	
}
