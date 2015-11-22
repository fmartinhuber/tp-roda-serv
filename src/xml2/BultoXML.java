package xml2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import negocio.BultoNegocio;

public class BultoXML {

	public static BultoXML instancia; 
	private static JAXBContext mijaxbcontext;
	
	public BultoXML() {

	}
	
	public static BultoXML getInstancia(){
		if(instancia == null){
			instancia = new BultoXML();
			try {
				//Obtengo la instancia de JAXB
				mijaxbcontext = JAXBContext.newInstance(BultoNegocio.class);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}
	
	
	/*Este metodo recibe un BultoNegocio y lo guarda en un XML, cuyo nombre sera devuelto*/
	public String bultoTOxml(BultoXML miBulNeg){
		
		//Creo el formato del nombre con la fecha actual
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		Date fecha = new Date();
		String formateado = formato.format(fecha);
		String nombreXML = "bulto_" + formateado + ".xml";
		
		try {
			//Creo el Marshaller
			Marshaller m = mijaxbcontext.createMarshaller();
			//Guardo el bulto
			m.marshal(miBulNeg, new File(nombreXML));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	//Devuelvo el nombre del bulto generado
	return nombreXML;
	}
	
	
	/*Este metodo recibe el nombre de un XML y devuelve el BultoNegocio*/
	public BultoNegocio xmlTObulto(String nombreXML){
		
		//Creo el bulto que voy a devolver y lo inicializo con null
		BultoNegocio resultado = new BultoNegocio();
		resultado = null;
		
		try {
			//Creo el Unmarshaller
			Unmarshaller um = mijaxbcontext.createUnmarshaller();
			//Obtengo el BultoNegocio del nombreXML pasado como parametro
			resultado = (BultoNegocio) um.unmarshal(new File(nombreXML));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	//Devuelvo el objeto BultoNegocio generado
	return resultado;
	}
}
