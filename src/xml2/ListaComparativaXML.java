package xml2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.*;

import utils.ItemNegocioList;
/**
 * 	@author Daro
 *	Esta clase se encarga de crear el XML para todos los Rodamientos de los Proveedores, utilizando utils.ItemNegocio como soporte
 *	Luego este XML sera levantado por la lista comparativa
 */
public class ListaComparativaXML {
	public static ListaComparativaXML instancia; 
	private static JAXBContext mijaxbcontext;
	
	public ListaComparativaXML() {

	}
	
	public static ListaComparativaXML getInstancia(){
		if(instancia == null){
			instancia = new ListaComparativaXML();
			try {
				//Obtengo la instancia de JAXB
				mijaxbcontext = JAXBContext.newInstance(ItemNegocioList.class);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}
	
	public String itemlistTOxml(ItemNegocioList miItemNegList){
		//Seteo el nombre
		String nombreXML = "RodamientosProveedores.xml";
		try {
			//Creo el Marshaller
			Marshaller m = mijaxbcontext.createMarshaller();
			//Guardo el ItemList
			m.marshal(miItemNegList, new File(nombreXML));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	//Devuelvo el nombre del ItemList generado
	return nombreXML;
	}
	
	public ItemNegocioList xmlTOitemlist(String nombreXML){
		
		//Creo el ItemList que voy a devolver y lo inicializo con null
		ItemNegocioList resultado = new ItemNegocioList();
		resultado = null;
		
		try {
			//Creo el Unmarshaller
			Unmarshaller um = mijaxbcontext.createUnmarshaller();
			//Obtengo el ItemNegocioList del nombreXML pasado como parametro
			resultado = (ItemNegocioList) um.unmarshal(new File(nombreXML));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	//Devuelvo el ItemNegocioList
	return resultado;
	}
}
