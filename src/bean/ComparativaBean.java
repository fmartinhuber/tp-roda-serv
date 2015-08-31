package bean;

import java.util.List;

public class ComparativaBean {

	/**
	 * La principal listado a consultar los rodamientos.
	 * La idea es que aca esten todos los rodamientos (uno de cada) con su mejor precio.
	 * 
	 */
	List <RodamientoBean> principal;
	/**
	 * En caso que no se disponga de stock o por X motivo se quiera utilizar otro rodamiento. 
	 */
	List <RodamientoBean> opcional;
	
	
}
