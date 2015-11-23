package xml2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import negocio.OrdenCompraNegocio;

/**
 * @author Daro
 * Esta clase unicamente posee SALIDA a XML, ya que asi lo indica el enunciado
 */
public class OrdenCompraXML {
	
	public static OrdenCompraXML instancia; 
	private static JAXBContext mijaxbcontext;
	
	public OrdenCompraXML() {

	}
	
	public static OrdenCompraXML getInstancia(){
		if(instancia == null){
			instancia = new OrdenCompraXML();
			try {
				//Obtengo la instancia de JAXB
				mijaxbcontext = JAXBContext.newInstance(OrdenCompraNegocio.class);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}
	
	
	/*Este metodo recibe una CotizacionNegocio y la guarda en un XML, cuyo nombre sera devuelto*/
	public String ordencompraTOxml(OrdenCompraNegocio miOrdNeg){
		
		//Creo el formato del nombre con la fecha actual
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		Date fecha = new Date();
		String formateado = formato.format(fecha);
		String nombreXML = "ordencompra_" + formateado + ".xml";
		
		try {
			//Creo el Marshaller
			Marshaller m = mijaxbcontext.createMarshaller();
			//Guardo la orden de compra
			m.marshal(miOrdNeg, new File(nombreXML));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	//Devuelvo el nombre de la orden de compra generada
	return nombreXML;
	}
	
}
