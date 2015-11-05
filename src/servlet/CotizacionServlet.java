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
import controlador.AdministracionCC;
import controlador.AdministracionOV;
import dto.ClienteDto;
import dto.RodamientoDto;

/**
 * Servlet implementation class RodamientoServlet
 */
@WebServlet("/CotizacionServlet")
public class CotizacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CotizacionServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		ClienteDto cliente = new ClienteDto();
		cliente.setCUIT(request.getParameter("cuit"));
		cliente.setRazonSocial(request.getParameter("razonSocial"));
		
		//JSON.parse(request.getParameter("listado"));
		
		String [] arrayRodamiento = request.getParameterValues("listado");
		String [] arrayCantidad = request.getParameterValues("listado");
		List <ItemDto> listaItems = new ArrayList<ItemDto>();
		
		for (int i = 0; i < arrayRodamiento.length; i++) {
			String string = arrayRodamiento[i];
			RodamientoDto rodamiento = AdministracionCC.getInstancia().buscarRodamientoDto(string);
			ItemDto item = new ItemDto();
			item.setRodamiento(rodamiento); 
			item.setCantidad(Integer.valueOf(arrayCantidad[i]));
			listaItems.add(item);
		}
		
		AdministracionOV.getInstancia().crearCotizacion(listaItems, cliente);
	}

}
