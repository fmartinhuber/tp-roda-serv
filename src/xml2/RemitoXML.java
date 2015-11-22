package xml2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import negocio.RemitoNegocio;

/**
 * @author Daro
 * Esta clase unicamente posee SALIDA a XML, ya que asi lo indica el enunciado
 */
public class RemitoXML {

	public static RemitoXML instancia; 
	private static JAXBContext mijaxbcontext;
	
	public RemitoXML() {

	}
	
	public static RemitoXML getInstancia(){
		if(instancia == null){
			instancia = new RemitoXML();
			try {
				//Obtengo la instancia de JAXB
				mijaxbcontext = JAXBContext.newInstance(RemitoNegocio.class);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}
	
	
	/*Este metodo recibe un RemitoNegocio y lo guarda en un XML, cuyo nombre sera devuelto*/
	public String ordencompraTOxml(RemitoNegocio miRemNeg){
		
		//Creo el formato del nombre con la fecha actual
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		Date fecha = new Date();
		String formateado = formato.format(fecha);
		String nombreXML = "remito_" + formateado + ".xml";
		
		try {
			//Creo el Marshaller
			Marshaller m = mijaxbcontext.createMarshaller();
			//Guardo el remito
			m.marshal(miRemNeg, new File(nombreXML));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	//Devuelvo el nombre del remito generado
	return nombreXML;
	}
}
