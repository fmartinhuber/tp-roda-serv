package xml2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import negocio.FacturaNegocio;

public class FacturaXML {

	public static FacturaXML instancia; 
	private static JAXBContext mijaxbcontext;
	
	public FacturaXML() {

	}
	
	public static FacturaXML getInstancia(){
		if(instancia == null){
			instancia = new FacturaXML();
			try {
				//Obtengo la instancia de JAXB
				mijaxbcontext = JAXBContext.newInstance(FacturaNegocio.class);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}
	
	
	/*Este metodo recibe una FacturaNegocio y la guarda en un XML, cuyo nombre sera devuelto*/
	public String cotizacionTOxml(FacturaNegocio miFacNeg){
		
		//Creo el formato del nombre con la fecha actual
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		Date fecha = new Date();
		String formateado = formato.format(fecha);
		String nombreXML = "factura_" + formateado + ".xml";
		
		try {
			//Creo el Marshaller
			Marshaller m = mijaxbcontext.createMarshaller();
			//Guardo la factura
			m.marshal(miFacNeg, new File(nombreXML));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	//Devuelvo el nombre de la factura generada
	return nombreXML;
	}
	
	
	/*Este metodo recibe el nombre de un XML y devuelve la FacturaNegocio*/
	public FacturaNegocio xmlTOcotizacion(String nombreXML){
		
		//Creo la factura que voy a devolver y la inicializo con null
		FacturaNegocio resultado = new FacturaNegocio();
		resultado = null;
		
		try {
			//Creo el Unmarshaller
			Unmarshaller um = mijaxbcontext.createUnmarshaller();
			//Obtengo la FacturaNegocio del nombreXML pasado como parametro
			resultado = (FacturaNegocio) um.unmarshal(new File(nombreXML));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	return resultado;
	}
}
