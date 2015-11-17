package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.ClienteNegocio;
import negocio.CotizacionNegocio;
import controlador.AdministracionCC;
import controlador.AdministracionOV;

/**
 * Servlet implementation class AprobarCotizacionServlet
 */
@WebServlet("/AprobarCotizacionServlet")
public class CotizacionServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CotizacionServlet2() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
		//System.out.println("aprobarCotizaciones");
		//AdministracionOV.getInstancia().aprobarCotizacion(miCotDto)(Integer.valueOf(request.getParameter("cotizacionSeleccionada")));
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cotizacion.jsp");
//		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClienteNegocio clienteNegocio = new ClienteNegocio();
		clienteNegocio.setCUIT(request.getParameter("cuit"));
		
		List <CotizacionNegocio> cotizaciones = AdministracionOV.getInstancia().obtenerCotizacionesDeCiente(clienteNegocio);
		
		String [] arrayCotizaciones = new String [cotizaciones.size()];
		
		for (int i = 0; i < arrayCotizaciones.length; i++) {
			arrayCotizaciones[i] = String.valueOf(cotizaciones.get(i).getIdCotizacion());
		}
		request.setAttribute("arrayCotizaciones", arrayCotizaciones);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/aprobarCotizacion.jsp");
		dispatcher.forward(request,response);
	
	}

}
