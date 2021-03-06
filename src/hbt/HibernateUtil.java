package hbt;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	 private static final SessionFactory sessionFactory;
	    static
	    {
	        try
	        {
	        	 AnnotationConfiguration config = new AnnotationConfiguration();
	        	 //config.addAnnotatedClass(negocio.BultoNegocio.class);
	        	 config.addAnnotatedClass(negocio.CCNegocio.class);
	             config.addAnnotatedClass(negocio.ClienteNegocio.class);
	        	 config.addAnnotatedClass(negocio.CotizacionNegocio.class);
	        	 config.addAnnotatedClass(negocio.FacturaNegocio.class);
	        	 config.addAnnotatedClass(negocio.ItemBultoNegocio.class);
	        	 config.addAnnotatedClass(negocio.ItemCotizacionNegocio.class);
	        	 config.addAnnotatedClass(negocio.ItemFacturaNegocio.class);
	        	 config.addAnnotatedClass(negocio.ItemOrdenCompraNegocio.class);
	        	 config.addAnnotatedClass(negocio.OrdenCompraNegocio.class);
	        	 config.addAnnotatedClass(negocio.OVNegocio.class);
	        	 config.addAnnotatedClass(negocio.ProveedorNegocio.class);
	        	 config.addAnnotatedClass(negocio.RemitoNegocio.class);
	        	 config.addAnnotatedClass(negocio.RodamientoNegocio.class);
	        	 config.addAnnotatedClass(negocio.SolicitudCompraNegocio.class);
	        	 
	             sessionFactory = config.buildSessionFactory();
	        }
	        catch (Throwable ex)
	        {
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
	    
	    public static SessionFactory getSessionFactory()
	    {
	        return sessionFactory;
	    }
}
