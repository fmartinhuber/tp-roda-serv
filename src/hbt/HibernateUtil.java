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
	        
	             config.addAnnotatedClass(bean.ClienteBean.class);
	        	 config.addAnnotatedClass(bean.CotizacionBean.class);
	        	 config.addAnnotatedClass(bean.FacturaBean.class);
	        	 config.addAnnotatedClass(bean.ItemCotizacionBean.class);
	        	 config.addAnnotatedClass(bean.ItemFacturaBean.class);
	        	 config.addAnnotatedClass(bean.ItemOrdenCompraBean.class);
	        	 config.addAnnotatedClass(bean.OrdenCompraBean.class);
	        	 config.addAnnotatedClass(bean.ProveedorBean.class);
	        	 config.addAnnotatedClass(bean.RemitoBean.class);
	        	 config.addAnnotatedClass(bean.RodamientoBean.class);
	        	 
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
