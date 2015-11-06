package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
			
		try {
			ClienteDto cliente = new ClienteDto();
			cliente.setCUIT(request.getParameter("cuit"));
			cliente.setRazonSocial(request.getParameter("razonSocial"));
			
			List <ItemDto> listaItems = new ArrayList<ItemDto>();
	
			JSONArray jObj = new JSONArray(request.getParameter("listaRodamiento")); // this parses the json
			System.out.println(jObj);
			
			
			for (int i = 0; i < jObj.length(); i++) {
				JSONObject objeto = jObj.getJSONObject(i);
				String codigo = objeto.getString("codigo");
				String marca = objeto.getString("marca");
				String pais = objeto.getString("pais");
				String caracteristica = objeto.getString("caracteristica");
				String cantidad = objeto.getString("cantidad");
				ItemDto item = new ItemDto();
				
				RodamientoDto rodamiento = new RodamientoDto();
				rodamiento.setCodigo(codigo);
				rodamiento.setMarca(marca);
				rodamiento.setOrigen(pais);
				rodamiento.setCaracteristica(caracteristica);
				
				item.setRodamiento(rodamiento);
				item.setCantidad(Integer.valueOf(cantidad));
				listaItems.add(item);
				
				AdministracionOV.getInstancia().crearCotizacion(listaItems, cliente);
				
				
			}
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cotizacion.jsp");
			dispatcher.forward(request,response);
		
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		
	}

}
