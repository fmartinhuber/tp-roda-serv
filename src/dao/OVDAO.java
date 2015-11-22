package dao;

public class OVDAO extends HibernateDAO {
	
	private static OVDAO instancia;
	
	public static OVDAO getInstancia(){
		if(instancia==null)
			instancia = new OVDAO();
		return instancia;
	}

}
