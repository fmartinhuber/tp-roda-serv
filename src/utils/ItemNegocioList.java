package utils;

import java.util.*;

import javax.xml.bind.annotation.*;


/**
 * @author Daro
 * 
 * Esta clase se crea para resolver la multiplicidad de rodamientos al generar el XML para la ListaComparativa.
 * ¡¡NO USAR PARA OTRA COSA!!
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ItemNegocioList {
	private ArrayList<ItemNegocio> misItemsNegocio = new ArrayList<ItemNegocio>();

	public ItemNegocioList(ArrayList<ItemNegocio> misItemsNegocio) {
		this.misItemsNegocio = misItemsNegocio;
	}

	public ItemNegocioList() {
		
	}

	public ArrayList<ItemNegocio> getMisItemsNegocio() {
		return misItemsNegocio;
	}

	public void setMisItemsNegocio (ArrayList<ItemNegocio> misItemsNegocio) {
		this.misItemsNegocio = misItemsNegocio;
	}

}
