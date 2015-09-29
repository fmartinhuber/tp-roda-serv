package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ItemDto;
import controlador.AdministracionOV;
import dto.ClienteDto;
import dto.CotizacionDto;
import dto.ItemCotizacionDto;

/**
 * Servlet implementation class RodamientoServlet
 */
@WebServlet("/RodamientoServlet")
public class CotizacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CotizacionServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <ItemDto> listaItems = new ArrayList <ItemDto>();
		ClienteDto cliente = new ClienteDto();
		cliente.setCUIT(request.getParameter("cuit"));
		cliente.getRazonSocial();
		AdministracionOV.getInstancia().crearCotizacion(listaItems, cliente);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
