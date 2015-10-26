package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.RodamientoNegocio;
import utils.ItemDto;
import controlador.AdministracionCC;
import controlador.AdministracionOV;
import dto.ClienteDto;
import dto.CotizacionDto;
import dto.ItemCotizacionDto;
import dto.RodamientoDto;

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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List <ItemDto> listaItems = new ArrayList <ItemDto>();
		ClienteDto cliente = new ClienteDto();
		cliente.setCUIT(request.getParameter("cuit"));
		cliente.setRazonSocial(request.getParameter("razonSocial"));
		
		RodamientoDto rodamiento = AdministracionCC.getInstancia().buscarRodamientoDto(request.getParameter("codigo"));
		
<<<<<<< HEAD
		ItemCotizacionDto item = new ItemCotizacionDto();
		item.setRodamiento(rodamiento);
		item.setCant(Integer.valueOf(request.getParameter("cantidad")));
=======
		
		ItemDto item = new ItemDto();
		item.setRodamiento(rodamiento);
>>>>>>> refs/remotes/origin/master
		
<<<<<<< HEAD
		CotizacionDto cotizacion = new CotizacionDto();
		cotizacion.setCliente(cliente);
		cotizacion.getItems().add(item);
=======
>>>>>>> refs/remotes/origin/master
		
		AdministracionOV.getInstancia().crearCotizacion(listaItems, cliente);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
