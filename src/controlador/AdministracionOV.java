package controlador;

import interfaces.IAdministracionOV;

import java.rmi.*;
import java.util.*;

import negocio.*;
import utils.*;
import dao.*;
import dto.*;


public class AdministracionOV implements IAdministracionOV{

	public static AdministracionOV administracion; 

	//Daro 25/10: Se genera una OVnegocio unica, cuando realmente deberia ser una lista. Trabajarlo con listas es muy complejo
	//sumado con el singleton en el medio seria una locura saber cuando se levanta tal o cual OV (y los constructores se harian multiples, demente)
	//Creo una OV unica y se deberia sacar el singleton de esta clase, asi se puede dar de alta los controladores que se deseen para cada OV (negrisimo pero logico)
	//De la forma que esta hecho ahora funciona para una unica OV
	public OVNegocio OficinaVentaNegocio = new OVNegocio();

	public static AdministracionOV getInstancia(){
		if(administracion == null){
			administracion = new AdministracionOV();
		}
		return administracion;
	}

	public AdministracionOV(){
		OficinaVentaNegocio.setClientes(new ArrayList <ClienteNegocio>());
		OficinaVentaNegocio.setFacturas(new ArrayList <FacturaNegocio>());
		OficinaVentaNegocio.setRemitos(new ArrayList <RemitoNegocio>());
		OficinaVentaNegocio.setProveedores(new ArrayList <ProveedorNegocio>());
		OficinaVentaNegocio.setCotizaciones(new ArrayList <CotizacionNegocio>());
	}

	public List<RodamientoDto> obtenerRodamientos(){
		//Daro: Este es un metodo que solo sirve para hacer pruebas, borrar despues
		@SuppressWarnings("unused")
		List <RodamientoNegocio> listaRodamientos = RodamientoDAO.getInstancia().obtenerRodamientos();	
		return null;
	}

	public CotizacionDto crearCotizacion(List<ItemDto> listaItems, ClienteDto cliente) throws RemoteException {
		//Declaro la cotizacion que voy a devolver
		CotizacionDto miCotDto = new CotizacionDto();
		//Uso la magica lista de Martin a ver como funca
		AdministracionCC admCC = new AdministracionCC();
		List<RodamientoDto> listaCompa = new ArrayList<RodamientoDto>();
		listaCompa = admCC.obtenerListaComparativa();

		//Seteo los valores correspondientes a 
		miCotDto.setCliente(cliente);
		miCotDto.setEstado("Pendiente"); //Todas se crean pendientes hasta ser aprobadas
		Date actual = new Date();
		miCotDto.setFechaCreacion(actual); //Se crea con la fecha actual
		Calendar c = Calendar.getInstance(); 
		c.setTime(actual); 
		c.add(Calendar.DATE, 30); //Se agregan 30 dias a la fecha actual
		Date vigencia = new Date();
		vigencia = c.getTime();
		miCotDto.setFechaVigencia(vigencia);

		/*Aca viene la magia, recorro la listaComparativa en busqueda de los rodamientos que me pidio el cliente
		y los cotizo (multiplico monto * cantidad)*/

		//Creo la lista de items que voy a utilizar para ir cargandolos
		List<ItemCotizacionDto> listaItemCotDto = new ArrayList<ItemCotizacionDto>();

		//Para cada elemento de la lista comparativa
		for (int i=0; i<=listaCompa.size(); i++){
			//Comparo con cada elemento de la lista Item
			for (int j=0; j<=listaItems.size(); j++){
				//Obtengo los codigos
				String codComp = listaCompa.get(i).getCodigo();
				String codItem = listaItems.get(j).getRodamiento().getCodigo();
				//Origen
				String orgComp = listaCompa.get(i).getCodigo();
				String orgItem = listaItems.get(j).getRodamiento().getOrigen();
				//Marca
				String marComp = listaCompa.get(i).getCodigo();
				String marItem = listaItems.get(j).getRodamiento().getOrigen();
				//Si coinciden las 3 cosas, es el que busco
				if (codComp.equals(codItem) && orgComp.equals(orgItem) && marComp.equals(marItem)){
					//Creo item
					ItemCotizacionDto itemCotDto = new ItemCotizacionDto();
					//Seteo sus valores
					itemCotDto.setCant(listaItems.get(j).getCantidad());
					itemCotDto.setRodamiento(listaCompa.get(j));
					itemCotDto.setPrecio(listaItems.get(j).getCantidad() * listaCompa.get(i).getMonto());
					//Agrego el item a la lista de items
					listaItemCotDto.add(itemCotDto);
				}
			}
		}

		//Agrego a la cotizacion toda la lista de items obtenida
		miCotDto.setItems(listaItemCotDto);

		//Aca tengo que guardar la cotizacion en la base antes de salir

		return miCotDto;
	}



	public EnvioAOVDto entregaPedidos(RemitoDto remito) throws RemoteException {
		// TODO Carlos
		return null;
	}


	public FacturaDto crearFactura(ClienteDto cliente, CotizacionDto cotizacion) throws RemoteException {
		// TODO RAMA

		ClienteDto cl = new ClienteDto();
		cl.setCUIT(cliente.getCUIT());
		cl.setMail(cliente.getMail());
		cl.setRazonSocial(cliente.getRazonSocial());
		CotizacionDto c = new CotizacionDto();
		c.setEstado(cotizacion.getEstado());
		FacturaDto aux = new FacturaDto(); 

		if(c.getEstado()=="Aprobada"){


		}

		return aux;
	}


	public boolean abmCliente(ClienteDto cliente, String accion) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public void procesarCotizaciones(int idCli){

		//Levantamos un ciente, cuyo ID sea el pasado.
		ClienteNegocio cli = ClienteDAO.getInstancia().buscarCliente(idCli);

		//Listamos las cotizaciones de un cliente que no hayan sido "solicitada" a CC
		List<CotizacionNegocio> cotizaciones = CotizacionDAO.getinstancia().obtenerCotizacionesDeCiente(cli);

		for(int i=0; i<cotizaciones.size(); i++){
			System.out.println(cotizaciones.get(i).getEstado());
		}

	}

	public void GenerarFactura(List<Integer> idsCoti, int idCliente){

		ClienteNegocio cli = ClienteDAO.getInstancia().buscarCliente(idCliente);
		// Crear Factura y setear datos primarios
		FacturaNegocio factura = new FacturaNegocio();
		factura.setCliente(cli);
		factura.setEstado("generada");
		Calendar c = new GregorianCalendar();
		factura.setFecha(c.getTime());
		List<CotizacionNegocio> cotizacionesFactura = new ArrayList<CotizacionNegocio>();
		// Crear ItemsFactura
		List<ItemFacturaNegocio> itemsFactura = new ArrayList<ItemFacturaNegocio>();
		for(int i=0; i<idsCoti.size(); i++){
			CotizacionNegocio coti = CotizacionDAO.getinstancia().buscarCotizacion(idsCoti.get(i).intValue());
			cotizacionesFactura.add(coti);
			ActualizarEstadoCotizacion(coti, "solicitada");
		}
		List<Object[]> misObjects = CotizacionDAO.getinstancia().itemsCotizacionAgrupadosPorRodamiento(idsCoti);
		for(int i=0; i<misObjects.size(); i++){
			ItemFacturaNegocio itFactura = new ItemFacturaNegocio();
			RodamientoNegocio rodamiento = RodamientoDAO.getInstancia().buscarRodamiento((Integer)misObjects.get(i)[0]);
			itFactura.setRodamiento(rodamiento);
			itFactura.setCantidad((Integer)misObjects.get(i)[0]);
			Double sal = (Double)misObjects.get(i)[2];
			itFactura.setSubtotal(sal.floatValue());
			itemsFactura.add(itFactura);
		}
		factura.setItems(itemsFactura);
		factura.persistirFactura();

	}
	// Actualiza ESTADO de cotización
	private boolean ActualizarEstadoCotizacion (CotizacionNegocio cotizacion, String estadoNuevo){
		//CotizacionNegocio coti = CotizacionDAO.getinstancia().buscarCotizacion(idCotizacion);
		Boolean salida = true;
		try{
			cotizacion.setEstado(estadoNuevo);
			CotizacionDAO.getinstancia().update(cotizacion);
		}
		catch(Exception ex){
			System.out.println("Error al actualizar estado de cotización");
			salida = false;
		}
		return salida;
	}
	// Prueba
	public void Prueba(List<Integer> coti){
		List<Object[]> misObjects = CotizacionDAO.getinstancia().itemsCotizacionAgrupadosPorRodamiento(coti);
		for(int i=0; i<misObjects.size(); i++){
			System.out.println(misObjects.get(i)[0]);
			System.out.println(misObjects.get(i)[1]);
			System.out.println(misObjects.get(i)[2]);
		}
	}

}
