package dao;

public class OrdenCompraDAO extends HibernateDAO{
	private static OrdenCompraDAO instancia;

	private OrdenCompraDAO() {
		
	}
	
	public static OrdenCompraDAO getinstancia(){
		if (instancia == null) 
			instancia = new OrdenCompraDAO();
		return instancia;
	}
	
	
}
