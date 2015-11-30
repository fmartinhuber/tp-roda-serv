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

	public int obtenerMaximoIDFactura() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		int resultado = (int) s.createQuery("select max(f.idFactura) from FacturaNegocio f").uniqueResult();
		
		s.clear();
		return resultado;	
	}
	
	
}
