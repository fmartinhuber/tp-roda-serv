package dao;

public class FacturaDAO extends HibernateDAO{

	private static FacturaDAO instancia;
	
	public static FacturaDAO getInstancia(){
		if(instancia==null)
			instancia = new FacturaDAO();
		return instancia;
	}
}
