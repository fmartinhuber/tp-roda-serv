package controlador;

import java.rmi.RemoteException;
import java.util.*;

import negocio.*;
import dto.*;
import interfaces.*;

public class AdministracionCC implements IAdministracionCC {

	public static AdministracionCC administracion; 

	private CCNegocio casaCentralNegocio = new CCNegocio();

	public AdministracionCC(){
		casaCentralNegocio.setOrdenesP(new ArrayList <OrdenCompraNegocio>());
		casaCentralNegocio.setRodamientos(new ArrayList <RodamientoNegocio>());
		casaCentralNegocio.setListaPrincipal(new ArrayList<RodamientoNegocio>());
		casaCentralNegocio.setListaOpcional(new ArrayList<RodamientoNegocio>());
		
//		/*Daro: Meto valores hardcodeados a la ListaPrincipal para poder crear la Cotizacion
//		Esto deberia hacerse de forma automatica desde algun lado que elija Martin para su lista*/
//		ProveedorNegocio provUno = new ProveedorNegocio();
//		provUno.setNombre("Solear SA");
//		RodamientoNegocio rodaUno = new RodamientoNegocio();
//		rodaUno.setCodigo("22310");
//		rodaUno.setCaracteristica("CCW33");
//		rodaUno.setMarca("ZKL");
//		rodaUno.setMonto((float) 310.71);
//		rodaUno.setOrigen("Japon");
//		rodaUno.setProveedor(provUno);
//		rodaUno.setStock(85);
//		rodaUno.setTipo("Bolilla");
//		casaCentralNegocio.getListaPrincipal().add(rodaUno);
	}

	public static AdministracionCC getInstancia(){
		if(administracion == null){
			administracion = new AdministracionCC();
		}
		return administracion;
	}


	/*
	 * No es necesario una lista de cotizaciónes
	 * Debera levantar todas las cotizaciones que aun no fueron cargadas a una orden de compra
	 * Se requiere un nuevo estado en la orden de compra y en la solicitud de cotización
	 * Marcar solicitud de cotización como "En Adquisición"
	 * Marcar Orden de compra como "Nueva" luego de su creación y previo a la entrega al proveedor
	 */
	public List<OrdenCompraDto> crearOrden(List<CotizacionDto> listaCotizaciones)
			throws RemoteException {
		// TODO MARTIN (?)
		return null;
		// Levantar Cotizaciones en estado "APROBADAS"

	}

	// Levanta las cotizaciones en un estado pasado por parametro "XXXXXXXX"  // "APROBADA"
	// PASAR A PRIVADO LUEGO DE LAS PRUEBAS
	private List<CotizacionNegocio> buscarCotizacionesAprobadas(String estado){

		List<CotizacionNegocio> misCotizaciones = new ArrayList<CotizacionNegocio>();


		return null;
	}

	public RemitoDto crearRemito(List<OrdenCompraDto> listaOrdenes)
			throws RemoteException {
		// TODO CARLOS
		return null;
	}

	public void actualizarStock(List<RodamientoDto> listaRodamientos) {
		// TODO RAMA

	}

	//Daro: Obtiene la lista comparativa (RodamientoNegocio), la transforma y devuelve (RodamientoDto)
	public List<RodamientoDto> obtenerListaComparativa() throws RemoteException{
		List<RodamientoNegocio> rodasNegocio = this.casaCentralNegocio.getListaPrincipal();
		List<RodamientoDto> rodasDto = new ArrayList<RodamientoDto>();
		for (int i=0; i<rodasNegocio.size(); i++){
			rodasDto.add(rodasNegocio.get(i).aRodamientoDto());
		}
		return rodasDto;
	}

	public List <RodamientoDto> obtenerListaComparativaOpcional () throws RemoteException{
		//TODO REVISAR.
		return null;
	}

	public void actualizarListaComparativa(List<RodamientoDto> listado)	throws RemoteException {
		//TODO REVISAR.
		Iterator <RodamientoNegocio> iterador = this.casaCentralNegocio.getListaPrincipal().iterator();
		while(iterador.hasNext()){
			RodamientoNegocio roda = iterador.next();
			this.agregarNuevoRodamiento(roda);
		}
	}


	private void agregarNuevoRodamiento (RodamientoNegocio rodamiento){
		Iterator <RodamientoNegocio> iterador = this.casaCentralNegocio.getListaPrincipal().iterator();
		boolean encontradoP = false, actualizadoP = false; 
		while(iterador.hasNext() && !encontradoP){
			RodamientoNegocio rodamientoComp = iterador.next();
			if(rodamientoComp.getCodigo().equals(rodamiento.getCodigo())){
				encontradoP = true;
				//si es mas barato
				if(rodamientoComp.getMonto() < rodamiento.getMonto()){
					actualizadoP = true;
					casaCentralNegocio.getListaPrincipal().remove(rodamientoComp);
					casaCentralNegocio.getListaPrincipal().add(rodamiento);
					break;
				}
			}
		}
		if(encontradoP && !actualizadoP){
			this.casaCentralNegocio.getListaPrincipal().add(rodamiento);
		}
		if(!encontradoP && !actualizadoP){
			iterador = this.casaCentralNegocio.getListaOpcional().iterator();
			while(iterador.hasNext() && !encontradoP){
				RodamientoNegocio rodamientoComp = iterador.next();
				if(rodamientoComp.getCodigo().equals(rodamiento.getCodigo())){
					this.casaCentralNegocio.getListaOpcional().add(rodamiento);
					actualizadoP = true;
					break;
				}
			}
		}
	}

	//TODO REVISAR
	public RodamientoDto buscarRodamientoDto(String codigo){
		for(Iterator <RodamientoNegocio> iterador = casaCentralNegocio.getRodamientos().iterator();iterador.hasNext();){
			RodamientoNegocio rodamiento = iterador.next();
			//			if(rodamiento.getCodigo().equals(codigo)){
			//				return rodamiento.aRodamientoDto();
			//			}
		}
		return null;
	}

	public RodamientoNegocio buscarRodamientoNegocio(String codigo){
		for(Iterator <RodamientoNegocio> iterador = casaCentralNegocio.getRodamientos().iterator();iterador.hasNext();){
			RodamientoNegocio rodamiento = iterador.next();
			if(rodamiento.getCodigo().equals(codigo))
				return rodamiento;
		}
		return null;
	}




	public AdministracionCC(CCNegocio casaCentralNegocio) {
		this.casaCentralNegocio = casaCentralNegocio;
	}

	public CCNegocio getCasaCentralNegocio() {
		return casaCentralNegocio;
	}

	public void setCasaCentralNegocio(CCNegocio casaCentralNegocio) {
		this.casaCentralNegocio = casaCentralNegocio;
	}

	@Override
	public void altaProveedor(ProveedorDto proveedor) throws RemoteException {
		//TODO no se que onda esto, porque la lista de proveedores esta en la OV, no tendria que estar en la CC? 
		ProveedorNegocio proveedorNegocio = new ProveedorNegocio();
		//AdministracionCC.getInstancia().getCasaCentralNegocio();
		return;
	}

	@Override
	public void bajaProveedor(ProveedorDto proveedor) throws RemoteException {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void modificacionProveedor(ProveedorDto proveedor)
			throws RemoteException {
		// TODO Auto-generated method stub
		return;
	}


}
