package dao;

public class BultoDAO extends HibernateDAO{
	private static BultoDAO instancia;

	private BultoDAO() {
		
	}
	
	public static BultoDAO getinstancia(){
		if (instancia == null) 
			instancia = new BultoDAO();
		return instancia;
	}
	
}
