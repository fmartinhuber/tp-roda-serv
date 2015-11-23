package controlador;

import java.rmi.RemoteException;
import java.util.*;

import dao.*;
import utils.*;
import utils.ItemDto;
import xml2.ListaComparativaXML;
import negocio.*;
import dto.*;
import interfaces.*;

public class AdministracionCC implements IAdministracionCC {

	public static AdministracionCC administracion;

	private CCNegocio casaCentralNegocio = new CCNegocio();

	public AdministracionCC() {
		casaCentralNegocio.setOrdenesP(new ArrayList<OrdenCompraNegocio>());
		casaCentralNegocio.setRodamientos(new ArrayList<RodamientoNegocio>());
		casaCentralNegocio.setListaPrincipal(new ArrayList<RodamientoNegocio>());
		casaCentralNegocio.setListaOpcional(new ArrayList<RodamientoNegocio>());

		/*Esto ahora va a levantar un XML que "nos da el proveedor" (Ver clase test.CargarDatosListaComparativa)
		IMPORTANTE: Si no tenes el "RodamientosProveedores.xml podes ejecutarlo desde TestDaro para generarlo*/
		
		//Levanto el XML RodamientosProveedores
		ItemNegocioList miItemNegocioList = new ItemNegocioList();
		miItemNegocioList = ListaComparativaXML.getInstancia().xmlTOitemlist("RodamientosProveedores.xml");
		
		//Recorro el ItemNegocioList y cargo la listaComparativa
		for (int i=0; i<miItemNegocioList.getMisItemsNegocio().size(); i++){
			casaCentralNegocio.getListaPrincipal().add(miItemNegocioList.getMisItemsNegocio().get(i).getRodamiento());
		}
	}

	public static AdministracionCC getInstancia() {
		if (administracion == null) {
			administracion = new AdministracionCC();
		}
		return administracion;
	}

	/*
	 * No es necesario una lista de cotizaciónes Debera levantar todas las
	 * cotizaciones que aun no fueron cargadas a una orden de compra Se requiere
	 * un nuevo estado en la orden de compra y en la solicitud de cotización
	 * Marcar solicitud de cotización como "En Adquisición" Marcar Orden de
	 * compra como "Nueva" luego de su creación y previo a la entrega al
	 * proveedor
	 */
	public List<OrdenCompraDto> crearOrden(
			List<SolicitudCompraDto> listaCotizaciones) throws RemoteException {
		// TODO CARLOS (?)

		return null;
		// Levantar Cotizaciones en estado "APROBADAS"

	}

	// Levanta las cotizaciones en un estado pasado por parametro "XXXXXXXX" //
	// "APROBADA"
	// PASAR A PRIVADO LUEGO DE LAS PRUEBAS
	public List<CotizacionDto> obtenerCotizacionesAprobadas()
			throws RemoteException {
		// TODO Auto-generated method stub
		List<CotizacionNegocio> cotizaciones = CotizacionDAO.getinstancia()
				.obtenerCotizacionesAprobada("ACEPTADA");
		List<CotizacionDto> cotizacionesDto = new ArrayList<CotizacionDto>();
		for (int i = 0; i < cotizaciones.size(); i++) {
			cotizacionesDto.add(cotizaciones.get(i).aCotizacionDto());
		}
		return cotizacionesDto;
	}

	// TODO: Rama
	public void crearRemito(List<OrdenCompraDto> listaOrdenes, ClienteDto cliente) throws RemoteException {

		ClienteNegocio cli = ClienteDAO.getInstancia().buscarClientePorCUIT(cliente.getCUIT());

		RemitoNegocio remito = new RemitoNegocio();
		// TODO: chequear acá porque me genera un cliente y lo asocia como el tujes
		remito.setCliente(cli);
		remito.setComentarios("comentario 1");
		remito.setConformidad(true); // qué joraca es esto?
		remito.setEstado("generado");
		Calendar c = new GregorianCalendar();
		remito.setFecha(c.getTime());

		// Falta agregar los ov_remitos, primero encaro el cliente

		// List<CotizacionNegocio> cotizacionesRemito = new
		// ArrayList<CotizacionNegocio>();
		// List<ItemOrdenCompraNegocio> ordenCompraNegocio = new
		// ArrayList<ItemOrdenCompraNegocio>();
		// List<Integer> idsOrdenes = new ArrayList<Integer>();
		// for(int i=0; i<listaOrdenes.size(); i++){
		// idsOrdenes.add(listaOrdenes.get(i).getIdOrdenCompra());
		// OrdenCompraNegocio orden = new OrdenCompraNegocio();
		// orden.aOrdenCompraNegocio(listaOrdenes.get(i));
		// }

		remito.mergearRemito();

	}

	public void actualizarStock(List<ItemDto> listaItems, String accion) {

		List<RodamientoNegocio> listaRodamiento = new ArrayList<RodamientoNegocio>();
		int cantidad = 0;

		// Transformar DTO a negocio
		for (int i = 0; i < listaItems.size(); i++) {
			RodamientoNegocio roda = new RodamientoNegocio();
			roda.aRodamientoNegocio(listaItems.get(i).getRodamiento());
			cantidad = listaItems.get(i).getCantidad();
			listaRodamiento.add(roda);
		}

		// Recorrer la lista de rodamientos
		for (int j = 0; j < listaRodamiento.size(); j++) {
			RodamientoNegocio rodamiento = new RodamientoNegocio();
			// la lista tiene que ser de negocio
			rodamiento = rodamiento
					.buscarRodamientoPorCodigoMarcaOrigen(listaRodamiento
							.get(j));

			if ((accion.equalsIgnoreCase("sumar"))
					|| (accion.equalsIgnoreCase("suma"))) {
				rodamiento.setStock(rodamiento.buscarStock(listaRodamiento
						.get(j)) + cantidad);
				rodamiento.actualizarRodamiento();
			}
			if ((accion.equalsIgnoreCase("restar"))
					|| (accion.equalsIgnoreCase("resta"))) {
				rodamiento.setStock(rodamiento.buscarStock(listaRodamiento
						.get(j)) - cantidad);
				rodamiento.actualizarRodamiento();

				// La validación que sigue es por si la cantidad pasa a ser un
				// número negativo
				int cantStock = rodamiento.buscarStock(listaRodamiento.get(j));
				if (cantStock < 0) {
					rodamiento.setStock(0);
					rodamiento.actualizarRodamiento();
				}
			}

		}

	}

	// Daro: Obtiene la lista comparativa (RodamientoNegocio), la transforma y
	// devuelve (RodamientoDto)
	public List<RodamientoDto> obtenerListaComparativa() throws RemoteException {
		List<RodamientoNegocio> rodasNegocio = this.casaCentralNegocio
				.getListaPrincipal();
		List<RodamientoDto> rodasDto = new ArrayList<RodamientoDto>();
		for (int i = 0; i < rodasNegocio.size(); i++) {
			rodasDto.add(rodasNegocio.get(i).aRodamientoDto());
		}
		return rodasDto;
	}

	@Deprecated
	public List<RodamientoDto> obtenerListaComparativaOpcional()
			throws RemoteException {
		// TODO REVISAR.
		return null;
	}

	public void actualizarListaComparativa(List<RodamientoDto> listado)
			throws RemoteException {
		// TODO REVISAR.
		Iterator<RodamientoNegocio> iterador = this.casaCentralNegocio
				.getListaPrincipal().iterator();
		while (iterador.hasNext()) {
			RodamientoNegocio roda = iterador.next();
			this.agregarNuevoRodamiento(roda);
		}
	}

	private void agregarNuevoRodamiento(RodamientoNegocio rodamiento) {
		Iterator<RodamientoNegocio> iterador = this.casaCentralNegocio
				.getListaPrincipal().iterator();
		boolean encontradoP = false, actualizadoP = false;
		while (iterador.hasNext() && !encontradoP) {
			RodamientoNegocio rodamientoComp = iterador.next();
			if (rodamientoComp.getCodigo().equals(rodamiento.getCodigo())) {
				encontradoP = true;
				// si es mas barato
				if (rodamientoComp.getMonto() < rodamiento.getMonto()) {
					actualizadoP = true;
					casaCentralNegocio.getListaPrincipal().remove(
							rodamientoComp);
					casaCentralNegocio.getListaPrincipal().add(rodamiento);
					break;
				}
			}
		}
		if (encontradoP && !actualizadoP) {
			this.casaCentralNegocio.getListaPrincipal().add(rodamiento);
		}
		if (!encontradoP && !actualizadoP) {
			iterador = this.casaCentralNegocio.getListaOpcional().iterator();
			while (iterador.hasNext() && !encontradoP) {
				RodamientoNegocio rodamientoComp = iterador.next();
				if (rodamientoComp.getCodigo().equals(rodamiento.getCodigo())) {
					this.casaCentralNegocio.getListaOpcional().add(rodamiento);
					actualizadoP = true;
					break;
				}
			}
		}
	}

	// TODO REVISAR
	public RodamientoDto buscarRodamientoDto(String codigo) {
		for (Iterator<RodamientoNegocio> iterador = casaCentralNegocio
				.getRodamientos().iterator(); iterador.hasNext();) {
			RodamientoNegocio rodamiento = iterador.next();
			// if(rodamiento.getCodigo().equals(codigo)){
			// return rodamiento.aRodamientoDto();
			// }
		}
		return null;
	}

	public RodamientoNegocio buscarRodamientoNegocio(String codigo) {
		for (Iterator<RodamientoNegocio> iterador = casaCentralNegocio
				.getRodamientos().iterator(); iterador.hasNext();) {
			RodamientoNegocio rodamiento = iterador.next();
			if (rodamiento.getCodigo().equals(codigo))
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
		// TODO no se que onda esto, porque la lista de proveedores esta en la
		// OV, no tendria que estar en la CC?
		ProveedorNegocio proveedorNegocio = new ProveedorNegocio();
		// AdministracionCC.getInstancia().getCasaCentralNegocio();
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
