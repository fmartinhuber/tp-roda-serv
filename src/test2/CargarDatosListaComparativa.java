package test2;

import java.util.*;
import negocio.*;
import utils.ItemNegocio;
import utils.ItemNegocioList;
import xml2.ListaComparativaXML;

/**
 * @author Daro
 * 
 * Esta clase tiene un unico metodo, el cual generara muchos rodamientos (Se reutilizan la mayoria de la carga de datos de carlos)
 * Estos rodamientos seran cargados en la lista de itemNegocio (util.ItemNegocio), el cual posee esta lista de rodamientos y cantidad
 * Cantidad quedara vacio porque no es obligatorio para este caso, pero tengo que usar una clase ya armada para pasarlo a XML
 * 
 * Al finalizar, el metodo genera un XML gigante con todos los Rodamientos cargados, que sera para emular el XML que nos entrega el proveedor
 * el cual sera cargado en la lista comparativa al inicio del programa
 */
public class CargarDatosListaComparativa {
	
	private static CargarDatosListaComparativa instancia;
	
	public static CargarDatosListaComparativa getInstance(){
		if(instancia == null)
			instancia = new CargarDatosListaComparativa();
		return instancia;
	}
	
	public void cargarListaComparativa(){
		//Creo el Array de ItemNegocio, el cual se utilizara como soporte para generar el XML de los Rodamientos
		ItemNegocioList misItemsNegocios = new ItemNegocioList();
		misItemsNegocios.setMisItemsNegocio(new ArrayList<ItemNegocio>());
		
		//Alta de proveedores
		ProveedorNegocio prov01 = new ProveedorNegocio(); 	prov01.setNombre("BPB Solucines en Movimiento"); 	
		ProveedorNegocio prov02 = new ProveedorNegocio(); 	prov02.setNombre("Blanco Rodamientos"); 			
		ProveedorNegocio prov03 = new ProveedorNegocio(); 	prov03.setNombre("Giovagnoli SRL"); 				
		ProveedorNegocio prov04 = new ProveedorNegocio(); 	prov04.setNombre("Ruleman SACIFIA"); 				
		ProveedorNegocio prov05 = new ProveedorNegocio(); 	prov05.setNombre("BA Rodamientos"); 				
		ProveedorNegocio prov06 = new ProveedorNegocio(); 	prov06.setNombre("Arodar SRL"); 					
		ProveedorNegocio prov07 = new ProveedorNegocio(); 	prov07.setNombre("Rossi Rodamientos"); 				
		ProveedorNegocio prov08 = new ProveedorNegocio(); 	prov08.setNombre("Alfa Rodamientos"); 				
		
		//Alta de Rodamientos
		RodamientoNegocio roda01 = new RodamientoNegocio(); roda01.setTipo("Rodamientos de rodillos a rotula"); roda01.setCodigo("20210"); roda01.setStock(0); roda01.setOrigen("Suecia"); roda01.setMonto((float) 5317.96); roda01.setCaracteristica("20210 TN9  . . . . . . . . . .SKF       Rodamiento"); roda01.setMarca("SKF"); roda01.setProveedor(prov01); 
		RodamientoNegocio roda02 = new RodamientoNegocio(); roda02.setTipo("Rodamientos de rodillos a rotula"); roda02.setCodigo("21311 K"); roda02.setStock(0); roda02.setOrigen("Suecia"); roda02.setMonto((float) 3689.45); roda02.setCaracteristica("21311 EK . . . . . . . . . . .SKF       Rodamiento"); roda02.setMarca("SKF"); roda02.setProveedor(prov02); 
		RodamientoNegocio roda03 = new RodamientoNegocio(); roda03.setTipo("Rodamientos de rodillos a rotula"); roda03.setCodigo("21311 K"); roda03.setStock(0); roda03.setOrigen("China"); roda03.setMonto((float) 1986.2); roda03.setCaracteristica("21311 K . . . . . . . . . . . SNR       Rodamiento"); roda03.setMarca("SNR"); roda03.setProveedor(prov03);
		RodamientoNegocio roda04 = new RodamientoNegocio(); roda04.setTipo("Rodamientos de rodillos a rotula"); roda04.setCodigo("21313 NN"); roda04.setStock(0); roda04.setOrigen("Israel"); roda04.setMonto((float) 2115.45); roda04.setCaracteristica("21313 NN . . . . . . . . . . .URB       Rodamiento"); roda04.setMarca("URB"); roda04.setProveedor(prov04); 
		RodamientoNegocio roda05 = new RodamientoNegocio(); roda05.setTipo("Rodamientos de rodillos a rotula"); roda05.setCodigo("21313 NN"); roda05.setStock(0); roda05.setOrigen("Argentina"); roda05.setMonto((float) 1696.51); roda05.setCaracteristica("21313 NN . . . . . . . . . . .IMP       Rodamiento"); roda05.setMarca("IMP"); roda05.setProveedor(prov05);
		RodamientoNegocio roda06 = new RodamientoNegocio(); roda06.setTipo("Rodamientos de rodillos a rotula"); roda06.setCodigo("21319 K"); roda06.setStock(0); roda06.setOrigen("Suecia"); roda06.setMonto((float) 10062.53); roda06.setCaracteristica("21319 EK . . . . . . . . . . .SKF       Rodamiento"); roda06.setMarca("SKF"); roda06.setProveedor(prov06);
		RodamientoNegocio roda07 = new RodamientoNegocio(); roda07.setTipo("Rodamientos de rodillos a rotula"); roda07.setCodigo("BS2-2207-2CS/VT143"); roda07.setStock(0); roda07.setOrigen("Suecia"); roda07.setMonto((float) 39.56); roda07.setCaracteristica("BS2-2207-2CS/VT143 . . . . . .SKF       Rodamiento"); roda07.setMarca("SKF"); roda07.setProveedor(prov07); 
		RodamientoNegocio roda08 = new RodamientoNegocio(); roda08.setTipo("Rodamientos de rodillos a rotula"); roda08.setCodigo("22207"); roda08.setStock(0); roda08.setOrigen("Suecia"); roda08.setMonto((float) 1685.33); roda08.setCaracteristica("22207 E . . . . . . . . . . . SKF       Rodamiento"); roda08.setMarca("SKF"); roda08.setProveedor(prov08);
		RodamientoNegocio roda09 = new RodamientoNegocio(); roda09.setTipo("Rodamientos de rodillos a rotula"); roda09.setCodigo("22207"); roda09.setStock(0); roda09.setOrigen("Republica Checa"); roda09.setMonto((float) 894.45); roda09.setCaracteristica("22207 . . . . . . . . . . . . ZKL       Rodamiento"); roda09.setMarca("ZKL"); roda09.setProveedor(prov01);
		RodamientoNegocio roda10 = new RodamientoNegocio(); roda10.setTipo("Rodamientos de rodillos a rotula"); roda10.setCodigo("22207"); roda10.setStock(0); roda10.setOrigen("Argentina"); roda10.setMonto((float) 48.03); roda10.setCaracteristica("22207 . . . . . . . . . . . . IMP       Rodamiento"); roda10.setMarca("IMP"); roda10.setProveedor(prov02); 
		RodamientoNegocio roda11 = new RodamientoNegocio(); roda11.setTipo("Rodamientos de rodillos a rotula"); roda11.setCodigo("22207"); roda11.setStock(0); roda11.setOrigen("Argentina"); roda11.setMonto((float) 579.56); roda11.setCaracteristica("22207 . . . . . . . . . . . . ROLLWAY   Rodamiento"); roda11.setMarca("IMP"); roda11.setProveedor(prov03);
		RodamientoNegocio roda12 = new RodamientoNegocio(); roda12.setTipo("Rodamientos de rodillos a rotula"); roda12.setCodigo("22208 C3"); roda12.setStock(0); roda12.setOrigen("Suecia"); roda12.setMonto((float) 1758.43); roda12.setCaracteristica("22208 E/C3 . . . . . . . . . .SKF       Rodamiento"); roda12.setMarca("SKF"); roda12.setProveedor(prov04);
		RodamientoNegocio roda13 = new RodamientoNegocio(); roda13.setTipo("Rodamientos de rodillos a rotula"); roda13.setCodigo("22208 C3"); roda13.setStock(0); roda13.setOrigen("Republica Checa"); roda13.setMonto((float) 954.16); roda13.setCaracteristica("22208 EW33J/C3 . . . . . . . .ZKL       Rodamiento"); roda13.setMarca("ZKL"); roda13.setProveedor(prov05); 
		RodamientoNegocio roda14 = new RodamientoNegocio(); roda14.setTipo("Rodamientos de rodillos a rotula"); roda14.setCodigo("22208 C3"); roda14.setStock(0); roda14.setOrigen("China"); roda14.setMonto((float) 718.34); roda14.setCaracteristica("22208 /C3 . . . . . . . . . . SNR       Rodamiento"); roda14.setMarca("SNR"); roda14.setProveedor(prov06);
		RodamientoNegocio roda15 = new RodamientoNegocio(); roda15.setTipo("Rodamientos de rodillos a rotula"); roda15.setCodigo("22208 C3"); roda15.setStock(0); roda15.setOrigen("Argentina"); roda15.setMonto((float) 117.02); roda15.setCaracteristica("22208/C3 . . . . . . . . . . .IMP       Rodamiento"); roda15.setMarca("IMP"); roda15.setProveedor(prov07);
		RodamientoNegocio roda16 = new RodamientoNegocio(); roda16.setTipo("Rodamientos de rodillos a rotula"); roda16.setCodigo("22226"); roda16.setStock(0); roda16.setOrigen("Suecia"); roda16.setMonto((float) 11655.74); roda16.setCaracteristica("22226 E . . . . . . . . . . . SKF       Rodamiento"); roda16.setMarca("SKF"); roda16.setProveedor(prov08);
		RodamientoNegocio roda17 = new RodamientoNegocio(); roda17.setTipo("Rodamientos de rodillos a rotula"); roda17.setCodigo("22226"); roda17.setStock(0); roda17.setOrigen("China"); roda17.setMonto((float) 5406.8); roda17.setCaracteristica("22226 EAW33 . . . . . . . . . SNR       Rodamiento"); roda17.setMarca("SNR"); roda17.setProveedor(prov01);
		RodamientoNegocio roda18 = new RodamientoNegocio(); roda18.setTipo("Rodamientos de rodillos a rotula"); roda18.setCodigo("22244 KC3"); roda18.setStock(0); roda18.setOrigen("Suecia"); roda18.setMonto((float) 72055.51); roda18.setCaracteristica("22244 CCK/C3W33 . . . . . . . SKF       Rodamiento"); roda18.setMarca("SKF"); roda18.setProveedor(prov02);
		RodamientoNegocio roda19 = new RodamientoNegocio(); roda19.setTipo("Rodamientos de rodillos a rotula"); roda19.setCodigo("22320"); roda19.setStock(0); roda19.setOrigen("Suecia"); roda19.setMonto((float) 13709.84); roda19.setCaracteristica("22320 E . . . . . . . . . . . SKF       Rodamiento"); roda19.setMarca("SKF"); roda19.setProveedor(prov03);
		RodamientoNegocio roda20 = new RodamientoNegocio(); roda20.setTipo("Rodamientos de rodillos a rotula"); roda20.setCodigo("22320 C3"); roda20.setStock(0); roda20.setOrigen("Suecia"); roda20.setMonto((float) 13709.84); roda20.setCaracteristica("22320 E/C3 . . . . . . . . . .SKF       Rodamiento"); roda20.setMarca("SKF"); roda20.setProveedor(prov04);
		RodamientoNegocio roda21 = new RodamientoNegocio(); roda21.setTipo("Rodamientos de rodillos a rotula"); roda21.setCodigo("22320"); roda21.setStock(0); roda21.setOrigen("Republica Checa"); roda21.setMonto((float) 5728.33); roda21.setCaracteristica("22320 JW33  . . . . . . . . . ZKL       Rodamiento"); roda21.setMarca("ZKL"); roda21.setProveedor(prov05);
		RodamientoNegocio roda22 = new RodamientoNegocio(); roda22.setTipo("Rodamientos de rodillos a rotula"); roda22.setCodigo("22320 C3"); roda22.setStock(0); roda22.setOrigen("Republica Checa"); roda22.setMonto((float) 7138.99); roda22.setCaracteristica("22320 EW33J/C3 . . . . . . . .ZKL       Rodamiento"); roda22.setMarca("ZKL"); roda22.setProveedor(prov06);
		RodamientoNegocio roda23 = new RodamientoNegocio(); roda23.setTipo("Rodamientos de rodillos a rotula"); roda23.setCodigo("22320 C3"); roda23.setStock(0); roda23.setOrigen("China"); roda23.setMonto((float) 5778.77); roda23.setCaracteristica("22320 /C3 . . . . . . . . . . SNR       Rodamiento"); roda23.setMarca("SNR"); roda23.setProveedor(prov07); 
		RodamientoNegocio roda24 = new RodamientoNegocio(); roda24.setTipo("Rodamientos de rodillos a rotula"); roda24.setCodigo("22320"); roda24.setStock(0); roda24.setOrigen("Israel"); roda24.setMonto((float) 4062.12); roda24.setCaracteristica("22320 . . . . . . . . . . . . URB       Rodamiento"); roda24.setMarca("URB"); roda24.setProveedor(prov08);
		RodamientoNegocio roda25 = new RodamientoNegocio(); roda25.setTipo("Rodamientos de rodillos a rotula"); roda25.setCodigo("23026 KC3"); roda25.setStock(0); roda25.setOrigen("Suecia"); roda25.setMonto((float) 11278.93); roda25.setCaracteristica("23026 CCK/C3W33 . . . . . . . SKF       Rodamiento"); roda25.setMarca("SKF"); roda25.setProveedor(prov01);
		RodamientoNegocio roda26 = new RodamientoNegocio(); roda26.setTipo("Rodamientos Axiales"); roda26.setCodigo("51122"); roda26.setStock(0); roda26.setOrigen("Argentina"); roda26.setMonto((float) 3307.22); roda26.setCaracteristica("51122 . . . . . . . . . . . . SKF       Crapodina"); roda26.setMarca("SKF"); roda26.setProveedor(prov02);
		RodamientoNegocio roda27 = new RodamientoNegocio(); roda27.setTipo("Rodamientos Axiales"); roda27.setCodigo("51122"); roda27.setStock(0); roda27.setOrigen("Japon"); roda27.setMonto((float) 1108.64); roda27.setCaracteristica("51122 . . . . . . . . . . . . JAP       Crapodina"); roda27.setMarca("Japon"); roda27.setProveedor(prov03);
		RodamientoNegocio roda28 = new RodamientoNegocio(); roda28.setTipo("Rodamientos Axiales"); roda28.setCodigo("51122"); roda28.setStock(0); roda28.setOrigen("Republica Checa"); roda28.setMonto((float) 506); roda28.setCaracteristica("51122 . . . . . . . . . . . . ZKL       Crapodina"); roda28.setMarca("ZKL"); roda28.setProveedor(prov04);
		RodamientoNegocio roda29 = new RodamientoNegocio(); roda29.setTipo("Rodamientos Axiales"); roda29.setCodigo("51122"); roda29.setStock(0); roda29.setOrigen("Israel"); roda29.setMonto((float) 886.09); roda29.setCaracteristica("51122 . . . . . . . . . . . . URB       Crapodina"); roda29.setMarca("URB"); roda29.setProveedor(prov05);
		RodamientoNegocio roda30 = new RodamientoNegocio(); roda30.setTipo("Rodamientos Axiales"); roda30.setCodigo("51222"); roda30.setStock(0); roda30.setOrigen("Suecia"); roda30.setMonto((float) 5519.65); roda30.setCaracteristica("51222 . . . . . . . . . . . . SKF       Crapodina"); roda30.setMarca("SKF"); roda30.setProveedor(prov06);
		RodamientoNegocio roda31 = new RodamientoNegocio(); roda31.setTipo("Rodamientos Axiales"); roda31.setCodigo("51409 M"); roda31.setStock(0); roda31.setOrigen("Israel"); roda31.setMonto((float) 416.38); roda31.setCaracteristica("51409 M . . . . . . . . . . . URB       Crapodina"); roda31.setMarca("URB"); roda31.setProveedor(prov07);
		RodamientoNegocio roda32 = new RodamientoNegocio(); roda32.setTipo("Rodamientos Axiales"); roda32.setCodigo("52306"); roda32.setStock(0); roda32.setOrigen("Japon"); roda32.setMonto((float) 1153.55); roda32.setCaracteristica("52306 . . . . . . . . . . . . JAP       Crapodina"); roda32.setMarca("Japon"); roda32.setProveedor(prov08);
		RodamientoNegocio roda33 = new RodamientoNegocio(); roda33.setTipo("Rodamientos Axiales"); roda33.setCodigo("52306"); roda33.setStock(0); roda33.setOrigen("Republica Checa"); roda33.setMonto((float) 697.85); roda33.setCaracteristica("52306 . . . . . . . . . . . . ZKL       Crapodina"); roda33.setMarca("ZKL"); roda33.setProveedor(prov01);
		RodamientoNegocio roda34 = new RodamientoNegocio(); roda34.setTipo("Rodamientos Axiales"); roda34.setCodigo("BA 9"); roda34.setStock(0); roda34.setOrigen("Suecia"); roda34.setMonto((float) 682.58); roda34.setCaracteristica("BA 9 . . . . . . . . . . . . .SKF       Crapodina"); roda34.setMarca("SKF"); roda34.setProveedor(prov02);
		RodamientoNegocio roda35 = new RodamientoNegocio(); roda35.setTipo("Rodamientos de rodillos cilindricos"); roda35.setCodigo("MR 1307 EL"); roda35.setStock(0); roda35.setOrigen("EUA"); roda35.setMonto((float) 871.09); roda35.setCaracteristica("MR 1307 EL  . . . . . . . . . Bower     Rodamiento"); roda35.setMarca("Bower"); roda35.setProveedor(prov03);
		RodamientoNegocio roda36 = new RodamientoNegocio(); roda36.setTipo("Rodamientos de rodillos cilindricos"); roda36.setCodigo("F 207033"); roda36.setStock(0); roda36.setOrigen("Brasil"); roda36.setMonto((float) 279.91); roda36.setCaracteristica("F 207033 . . . . . . . . . . .INA       Rodamiento"); roda36.setMarca("INA"); roda36.setProveedor(prov04);
		RodamientoNegocio roda37 = new RodamientoNegocio(); roda37.setTipo("Rodamientos de rodillos cilindricos"); roda37.setCodigo("N 208"); roda37.setStock(0); roda37.setOrigen("Suecia"); roda37.setMonto((float) 1904.83); roda37.setCaracteristica("N 208 ECP . . . . . . . . . . SKF       Rodamiento"); roda37.setMarca("SKF"); roda37.setProveedor(prov05);
		RodamientoNegocio roda38 = new RodamientoNegocio(); roda38.setTipo("Rodamientos de rodillos cilindricos"); roda38.setCodigo("N 208"); roda38.setStock(0); roda38.setOrigen("Republica Checa"); roda38.setMonto((float) 385.87); roda38.setCaracteristica("N 208  . . . . . . . . . . . .ZKL       Rodamiento"); roda38.setMarca("ZKL"); roda38.setProveedor(prov06);
		RodamientoNegocio roda39 = new RodamientoNegocio(); roda39.setTipo("Rodamientos de rodillos cilindricos"); roda39.setCodigo("N 208"); roda39.setStock(0); roda39.setOrigen("Alemania"); roda39.setMonto((float) 1361.63); roda39.setCaracteristica("N 208 ETVP2  . . . . . . . . .FAG       Rodamiento"); roda39.setMarca("FAG"); roda39.setProveedor(prov07);
		RodamientoNegocio roda40 = new RodamientoNegocio(); roda40.setTipo("Rodamientos de rodillos cilindricos"); roda40.setCodigo("NU 211"); roda40.setStock(0); roda40.setOrigen("Argentina"); roda40.setMonto((float) 1892.69); roda40.setCaracteristica("NU 211 ECP . . . . . . . . . .SKF       Rodamiento"); roda40.setMarca("SKF"); roda40.setProveedor(prov08);

		//Asociamos Rodamientos a ItemsNegocio y los metemos en itemNegocioList
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda01, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda02, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda03, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda04, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda05, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda06, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda07, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda08, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda09, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda10, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda11, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda12, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda13, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda14, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda15, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda16, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda17, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda18, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda19, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda20, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda21, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda22, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda23, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda24, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda25, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda26, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda27, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda28, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda29, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda30, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda31, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda32, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda33, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda34, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda35, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda36, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda37, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda38, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda39, 0));
		misItemsNegocios.getMisItemsNegocio().add(new ItemNegocio(roda40, 0));
				
		//Una vez todos los Rodamientos cargados en ItemNegocioList, procedo a generar el XML correspondiente
		ListaComparativaXML.getInstancia().itemlistTOxml(misItemsNegocios);
	}

}
