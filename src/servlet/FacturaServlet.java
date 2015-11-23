package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import controlador.AdministracionOV;
import dto.ClienteDto;
import dto.CotizacionDto;
import dto.RodamientoDto;

/**
 * Servlet implementation class FacturaServlet
 */
@WebServlet("/FacturaServlet")
public class FacturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacturaServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		System.out.println("metodo:" + request.getMethod());
		if(request.getParameter("metodo").equals("crearFactura")){
			crearFactura(request,response);
	   }

	}
	
	private void crearFactura(HttpServletRequest request, HttpServletResponse response){
		
		
		ClienteDto cliente = new ClienteDto();
		cliente.setCUIT(request.getParameter("cuit"));
		cliente.setRazonSocial(request.getParameter("razonSocial"));
		
		List <CotizacionDto> listaCotizaciones = new ArrayList<CotizacionDto>();
		
		JSONArray jObj;
		try {
			jObj = new JSONArray(request.getParameter("listaCotizacion"));
			
			for (int i = 0; i < jObj.length(); i++) {
				JSONObject objeto = jObj.getJSONObject(i);
				String codigo = objeto.getString("codigo");
				CotizacionDto item = new CotizacionDto();
				//item.setIdCotizacion(Integer.valueOf(codigo));
				item.setNumeroCotizacion(Integer.valueOf(codigo));
				listaCotizaciones.add(item);
				
			}
			
			AdministracionOV.getInstancia().generarFactura(listaCotizaciones, cliente);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
	}

}
