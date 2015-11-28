package dao;

public class CCDAO extends HibernateDAO{

	private static ClienteDAO instancia;
	
	public static ClienteDAO getInstancia(){
		if(instancia==null)
			instancia = new ClienteDAO();
		return instancia;
	}
	
}
