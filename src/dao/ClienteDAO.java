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
	
	public ClienteNegocio buscarCliente(int idCli){
		
		Session se = HibernateUtil.getSessionFactory().openSession();
		ClienteNegocio salida = (ClienteNegocio) se.get(ClienteNegocio.class, idCli);
		se.close();
		return salida;
	}
	
	public ClienteNegocio buscarClientePorCUIT(String cuit){
		Session se = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Query query = se.createQuery("from ClienteNegocio c where c.CUIT = :nombrePar");
		query.setParameter("nombrePar", cuit);
		ClienteNegocio cliNeg = (ClienteNegocio) query.uniqueResult();
		se.close();
	return cliNeg;
	}
	
}
