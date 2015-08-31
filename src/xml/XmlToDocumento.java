<<<<<<< HEAD
//package xml;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import dto.ClienteDto;
//import dto.CotizacionDto;
//import dto.ItemCotizacionDto;
//import dto.RodamientoDto;
//
//public class XmlToDocumento {
//	private Document doc; 
//	private CotizacionDto co=null; 
//	//private OVto odv; 
//	public CotizacionDto XmlACotizacion()
//	{
//		try {
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
//			DocumentBuilder db = dbf.newDocumentBuilder();
//			doc = db.parse("Cotizacion.xml");
//			Element raiz = doc.getDocumentElement();
//			
//			//recuperar todas las solicitudes de cotizacion
//			NodeList solicitudes =  raiz.getElementsByTagName("SolicitudCotizacion");
//			if(raiz.hasChildNodes())
//			{
//				for(int u=0;u<solicitudes.getLength();u++)
//				{
//					co=new CotizacionDto();
//					//NodeList hijos= raiz.getChildNodes();
//					ClienteDto c=new ClienteDto();
//					Element solicitud = (Element) solicitudes.item(u);
//					
//					//c.setCUIT(Integer.parseInt(solicitud.getElementsByTagName("Cuil").item(0).getTextContent()));
//					c.setRazonSocial(solicitud.getElementsByTagName("RazonSocial").item(0).getTextContent());
//					co.setCliente(c);			
//					
//					//recuperar los items
//					NodeList rodamientos = solicitud.getElementsByTagName("Item");
//					for(int i=0;i<rodamientos.getLength();i++)
//					{
//						ItemCotizacionDto ic=new ItemCotizacionDto();
//						Element itemsE = (Element) rodamientos.item(i);
//						RodamientoDto r=new RodamientoDto();
//						r.setOrigen(itemsE.getElementsByTagName("Origen").item(0).getTextContent());
//						r.setCodigo(itemsE.getElementsByTagName("Sufijo").item(0).getTextContent());
//						ic.setCant(Integer.parseInt(itemsE.getElementsByTagName("Cantidad").item(0).getTextContent()));
//						ic.setRodamiento(r);
//						co.getItems().add(ic);
//					}
//					co.setEstado("Solicitado");
//					
//					//formatear la fecha de string a date
//					SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
//					try {
//						Date date;
//						date = sdf.parse(solicitud.getAttribute("fecha"));
//						co.setFecha(date);;
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					//co.setNumero(Integer.parseInt(solicitud.getAttribute("numero")));
//					String st=solicitud.getAttribute("centroIndustrial");
//					
//					//agregar las cotizaciones al sistema >> odv >> cotizacion
//					//odv.getCotizaciones().add(co);
//				}
//			
//			}
//			
//			
//		} catch (ParserConfigurationException | SAXException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		return co;
//		
//	}
//
//}
=======
package xml;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dto.ClienteDto;
import dto.CotizacionDto;
import dto.ItemCotizacionDto;
import dto.RodamientoDto;

public class XmlToDocumento {
	private Document doc; 
	private CotizacionDto co=null; 
	//private OVto odv; 
	public CotizacionDto XmlACotizacion()
	{
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse("Cotizacion.xml");
			Element raiz = doc.getDocumentElement();
			
			//recuperar todas las solicitudes de cotizacion
			NodeList solicitudes =  raiz.getElementsByTagName("SolicitudCotizacion");
			if(raiz.hasChildNodes())
			{
				for(int u=0;u<solicitudes.getLength();u++)
				{
					co=new CotizacionDto();
					//NodeList hijos= raiz.getChildNodes();
					ClienteDto c=new ClienteDto();
					Element solicitud = (Element) solicitudes.item(u);
					
					//Daro: Cambie CUIT de int a String, rompe esta linea, nose como arreglar esto
					//c.setCUIT(Integer.parseInt(solicitud.getElementsByTagName("Cuil").item(0).getTextContent()));
					c.setRazonSocial(solicitud.getElementsByTagName("RazonSocial").item(0).getTextContent());
					co.setCliente(c);			
					
					//recuperar los items
					NodeList rodamientos = solicitud.getElementsByTagName("Item");
					for(int i=0;i<rodamientos.getLength();i++)
					{
						ItemCotizacionDto ic=new ItemCotizacionDto();
						Element itemsE = (Element) rodamientos.item(i);
						RodamientoDto r=new RodamientoDto();
						r.setOrigen(itemsE.getElementsByTagName("Origen").item(0).getTextContent());
						r.setCodigo(itemsE.getElementsByTagName("Sufijo").item(0).getTextContent());
						ic.setCant(Integer.parseInt(itemsE.getElementsByTagName("Cantidad").item(0).getTextContent()));
						ic.setRodamiento(r);
						co.getItems().add(ic);
					}
					co.setEstado("Solicitado");
					
					//formatear la fecha de string a date
					SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
					try {
						Date date;
						date = sdf.parse(solicitud.getAttribute("fecha"));
						//Daro: Aca puse la fecha de vigencia sola, falta la de creacion, nose como usar esto
						co.setFechaVigencia(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					//co.setNumero(Integer.parseInt(solicitud.getAttribute("numero")));
					String st=solicitud.getAttribute("centroIndustrial");
					
					//agregar las cotizaciones al sistema >> odv >> cotizacion
					//odv.getCotizaciones().add(co);
				}
			
			}
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		
		return co;
		
	}

}
>>>>>>> branch 'master' of https://github.com/fmartinhuber/tp-roda-serv
