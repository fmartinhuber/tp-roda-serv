//package xml;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Result;
//import javax.xml.transform.Source;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//
//
//import org.w3c.dom.Comment;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//import dto.CotizacionDto;
//import dto.ItemCotizacionDto;
//
//public class CotizacionAXml {
//	//TODO esta desactualizada este lista y es solo de ejemplo... no va a formar parte de la entrega final.
//	private Document doc;
//	private Integer i;
//	public void crearXML(CotizacionDto c)
//	{		
//		try {
//			DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
//			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//			doc = docBuilder.newDocument();
//			
//			//Comment comment = doc.createComment("SOLICITUD DE COTIZACION");
//            //comment.appendChild(comment);
//			
//			Element root = doc.createElement("SolicitudCotizacion");
//			i=1;
//			root.setAttribute("numero",i.toString());
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
//			String date = sdf.format(new Date()); 
//			root.setAttribute("fecha", date);
//			doc.appendChild(root);
//			
//			Element cliente = doc.createElement("Cliente");
//			Element cuil = doc.createElement("Cuil");
//			//i=c.getCliente().getCUIT();
//			cuil.setTextContent(i.toString());
//			Element razon = doc.createElement("RazonSocial");
//			razon.setTextContent(c.getCliente().getRazonSocial());
//			cliente.appendChild(cuil);
//			cliente.appendChild(razon);
//			root.appendChild(cliente);
//			
//			Element rodamiento = doc.createElement("Rodamientos");
//			for(ItemCotizacionDto ic: c.getItems())
//			{
//				Element item = doc.createElement("Item");
//				Element sufijo= doc.createElement("Sufijo");
//				sufijo.setTextContent(ic.getRodamiento().getCodigo());
//				Element origen= doc.createElement("Origen");
//				origen.setTextContent(ic.getRodamiento().getOrigen());
//				Element cant= doc.createElement("Cantidad");
//				i=ic.getCant();
//				cant.setTextContent(i.toString());
//				item.appendChild(sufijo);
//				item.appendChild(origen);
//				item.appendChild(cant);
//				rodamiento.appendChild(item);
//			}
//			root.appendChild(rodamiento);
//			
//			saveDomXML();
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	void saveDomXML() {
//	    TransformerFactory tranFactory = TransformerFactory.newInstance(); 
//	    Transformer tran;
//		try {
//			tran = tranFactory.newTransformer();
//		    Source src = new DOMSource(doc); 
//		    Result dest = new StreamResult(new FileWriter(new File("Cotizacion.xml")));
//		    tran.transform(src, dest); 
//		    System.out.print("archivo creado");
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//	}
//}
