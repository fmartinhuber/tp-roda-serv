package xml;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.*;

import controlador.AdministracionCC;
import negocio.*;

/**
 * 	@author Daro
 *	Esta clase se encarga de transformar un objeto Cotizacion en XML y un XML en objeto Cotizacion
 *	Siempre son Objetos de negocio
 */
public class CotizacionXML {
	
	public static CotizacionXML instancia; 
	private static JAXBContext mijaxbcontext;
	
	public CotizacionXML() {

	}
	
	public static CotizacionXML getInstancia(){
		if(instancia == null){
			instancia = new CotizacionXML();
			try {
				//Obtengo la instancia de JAXB
				mijaxbcontext = JAXBContext.newInstance(CotizacionNegocio.class);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}
	
	
	/*Este metodo recibe una CotizacionNegocio y la guarda en un XML, cuyo nombre sera devuelto*/
	public String cotizacionTOxml(CotizacionNegocio miCotNeg){
		
		//Creo el formato del nombre con la fecha actual
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		Date fecha = new Date();
		String formateado = formato.format(fecha);
		String nombreXML = "cotizacion_" + formateado + ".xml";
		
		try {
			//Creo el Marshaller
			Marshaller m = mijaxbcontext.createMarshaller();
			//Guardo la cotizacion
			m.marshal(miCotNeg, new File(nombreXML));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	//Devuelvo el nombre de la cotizacion generada
	return nombreXML;
	}
		
	
	/*Este metodo recibe el nombre de un XML y devuelve la CotizacionNegocio*/
	public CotizacionNegocio xmlTOcotizacion(String nombreXML){
		
		//Creo la cotizacion que voy a devolver y la inicializo con null
		CotizacionNegocio resultado = new CotizacionNegocio();
		resultado = null;
		
		try {
			//Creo el Unmarshaller
			Unmarshaller um = mijaxbcontext.createUnmarshaller();
			//Obtengo la CotizacionNegocio del nombreXML pasado como parametro
			resultado = (CotizacionNegocio) um.unmarshal(new File(nombreXML));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	return resultado;
	}

}
