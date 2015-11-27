package dao;

import org.hibernate.Session;

import hbt.HibernateUtil;
import negocio.FacturaNegocio;

public class FacturaDAO extends HibernateDAO{

	private static FacturaDAO instancia;
	
	public static FacturaDAO getInstancia(){
		if(instancia==null)
			instancia = new FacturaDAO();
		return instancia;
	}
	
	public FacturaNegocio buscarFactura(int idFactura){
		Session se = HibernateUtil.getSessionFactory().openSession();
		FacturaNegocio salida = (FacturaNegocio) se.get(FacturaNegocio.class, idFactura);
		se.close();
	return salida;
	}
	
	
}
