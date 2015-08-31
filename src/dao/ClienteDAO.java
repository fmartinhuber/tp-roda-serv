package dao;

import hbt.HibernateUtil;

import java.util.List;

import org.hibernate.Session;

import bean.*;

public class ClienteDAO extends HibernateDAO {
	
	private static ClienteDAO instancia;
	
	public static ClienteDAO getInstancia(){
		if(instancia==null)
			instancia = new ClienteDAO();
		return instancia;
	}

	public List<ClienteBean> listarClientes(){
		
		Session s = HibernateUtil.getSessionFactory().openSession();
				
		@SuppressWarnings("unchecked")
		List<ClienteBean> clientes = s.createQuery("from ClienteBean c").list();
		
		return clientes;
	}
	
}
