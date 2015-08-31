package negocio;

import java.util.List;

public class ComparativaNegocio {

	/**
	 * La principal listado a consultar los rodamientos.
	 * La idea es que aca esten todos los rodamientos (uno de cada) con su mejor precio.
	 */
	List <RodamientoNegocio> principal;
	/**
	 * En caso que no se disponga de stock o por X motivo se quiera utilizar otro rodamiento. 
	 */
	List <RodamientoNegocio> opcional;
	
	
}
