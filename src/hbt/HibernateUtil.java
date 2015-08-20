package hbt;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import bean.ClienteBean;
import bean.CotizacionBean;
import bean.FacturaBean;
import bean.ItemCotizacionBean;
import bean.ItemFacturaBean;
import bean.ItemOrdenPedidoBean;
import bean.ODVBean;
import bean.OrdenCompraBean;
import bean.OrdenPedidoBean;
import bean.ProveedorBean;
import bean.RemitoBean;
import bean.RodamientoBean;

public class HibernateUtil {
	 private static final SessionFactory sessionFactory;
	    static
	    {
	        try
	        {
	        	 AnnotationConfiguration config = new AnnotationConfiguration();
	        
	             config.addAnnotatedClass(RodamientoBean.class);	                    
	             config.addAnnotatedClass(ClienteBean.class);
	        	 config.addAnnotatedClass(CotizacionBean.class);
	        	 config.addAnnotatedClass(FacturaBean.class);
	        	 config.addAnnotatedClass(ItemCotizacionBean.class);
	        	 config.addAnnotatedClass(ItemOrdenPedidoBean.class);
	        	 config.addAnnotatedClass(ItemFacturaBean.class);
	        	 config.addAnnotatedClass(ODVBean.class);
	        	 config.addAnnotatedClass(OrdenPedidoBean.class);
	        	 config.addAnnotatedClass(OrdenCompraBean.class);
	        	 config.addAnnotatedClass(ProveedorBean.class);
	        	 config.addAnnotatedClass(RemitoBean.class);
	        	 config.addAnnotatedClass(RodamientoBean.class);
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
