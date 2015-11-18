package test2;

//
//import java.util.*;
//
//import negocio.*;
//
//public class CargarDatos {
//
//	private static CargarDatos instance;
//	
//	public static CargarDatos getInstance(){
//		
//		if(instance == null)
//			instance = new CargarDatos();
//		return instance;
//	}
//	
//	public void cargaDeDatos(){
//		
//		// Alta de proveedores
//				ProveedorNegocio prov01 = new ProveedorNegocio(); 	prov01.setNombre("BPB Solucines en Movimiento"); 	prov01.persistirProveedor();
//				ProveedorNegocio prov02 = new ProveedorNegocio(); 	prov02.setNombre("Blanco Rodamientos"); 			prov02.persistirProveedor();
//				ProveedorNegocio prov03 = new ProveedorNegocio(); 	prov03.setNombre("Giovagnoli SRL"); 				prov03.persistirProveedor();
//				ProveedorNegocio prov04 = new ProveedorNegocio(); 	prov04.setNombre("Ruleman SACIFIA"); 				prov04.persistirProveedor();
//				ProveedorNegocio prov05 = new ProveedorNegocio(); 	prov05.setNombre("BA Rodamientos"); 				prov05.persistirProveedor();
//				ProveedorNegocio prov06 = new ProveedorNegocio(); 	prov06.setNombre("Arodar SRL"); 					prov06.persistirProveedor();
//				ProveedorNegocio prov07 = new ProveedorNegocio(); 	prov07.setNombre("Rossi Rodamientos"); 				prov07.persistirProveedor();
//				ProveedorNegocio prov08 = new ProveedorNegocio(); 	prov08.setNombre("Alfa Rodamientos"); 				prov08.persistirProveedor();
//
//		// Alta de Rodamientos
//				RodamientoNegocio roda01 = new RodamientoNegocio(); roda01.setTipo("Rodamientos de rodillos a rotula"); roda01.setCodigo("20210"); roda01.setStock(0); roda01.setOrigen("Suecia"); roda01.setMonto((float) 5317.96); roda01.setCaracteristica("20210 TN9  . . . . . . . . . .SKF       Rodamiento"); roda01.setMarca("SKF"); roda01.setProveedor(prov01);roda01.persistirRodamiento(); 
//				RodamientoNegocio roda02 = new RodamientoNegocio(); roda02.setTipo("Rodamientos de rodillos a rotula"); roda02.setCodigo("21311 K"); roda02.setStock(0); roda02.setOrigen("Suecia"); roda02.setMonto((float) 3689.45); roda02.setCaracteristica("21311 EK . . . . . . . . . . .SKF       Rodamiento"); roda02.setMarca("SKF"); roda02.setProveedor(prov02);roda02.persistirRodamiento(); 
//				RodamientoNegocio roda03 = new RodamientoNegocio(); roda03.setTipo("Rodamientos de rodillos a rotula"); roda03.setCodigo("21311 K"); roda03.setStock(0); roda03.setOrigen("China"); roda03.setMonto((float) 1986.2); roda03.setCaracteristica("21311 K . . . . . . . . . . . SNR       Rodamiento"); roda03.setMarca("SNR"); roda03.setProveedor(prov03);roda03.persistirRodamiento(); 
//				RodamientoNegocio roda04 = new RodamientoNegocio(); roda04.setTipo("Rodamientos de rodillos a rotula"); roda04.setCodigo("21313 NN"); roda04.setStock(0); roda04.setOrigen("Israel"); roda04.setMonto((float) 2115.45); roda04.setCaracteristica("21313 NN . . . . . . . . . . .URB       Rodamiento"); roda04.setMarca("URB"); roda04.setProveedor(prov04);roda04.persistirRodamiento(); 
//				RodamientoNegocio roda05 = new RodamientoNegocio(); roda05.setTipo("Rodamientos de rodillos a rotula"); roda05.setCodigo("21313 NN"); roda05.setStock(0); roda05.setOrigen("Argentina"); roda05.setMonto((float) 1696.51); roda05.setCaracteristica("21313 NN . . . . . . . . . . .IMP       Rodamiento"); roda05.setMarca("IMP"); roda05.setProveedor(prov05);roda05.persistirRodamiento(); 
//				RodamientoNegocio roda06 = new RodamientoNegocio(); roda06.setTipo("Rodamientos de rodillos a rotula"); roda06.setCodigo("21319 K"); roda06.setStock(0); roda06.setOrigen("Suecia"); roda06.setMonto((float) 10062.53); roda06.setCaracteristica("21319 EK . . . . . . . . . . .SKF       Rodamiento"); roda06.setMarca("SKF"); roda06.setProveedor(prov06);roda06.persistirRodamiento(); 
//				RodamientoNegocio roda07 = new RodamientoNegocio(); roda07.setTipo("Rodamientos de rodillos a rotula"); roda07.setCodigo("BS2-2207-2CS/VT143"); roda07.setStock(0); roda07.setOrigen("Suecia"); roda07.setMonto((float) 39.56); roda07.setCaracteristica("BS2-2207-2CS/VT143 . . . . . .SKF       Rodamiento"); roda07.setMarca("SKF"); roda07.setProveedor(prov07);roda07.persistirRodamiento(); 
//				RodamientoNegocio roda08 = new RodamientoNegocio(); roda08.setTipo("Rodamientos de rodillos a rotula"); roda08.setCodigo("22207"); roda08.setStock(0); roda08.setOrigen("Suecia"); roda08.setMonto((float) 1685.33); roda08.setCaracteristica("22207 E . . . . . . . . . . . SKF       Rodamiento"); roda08.setMarca("SKF"); roda08.setProveedor(prov08);roda08.persistirRodamiento(); 
//				RodamientoNegocio roda09 = new RodamientoNegocio(); roda09.setTipo("Rodamientos de rodillos a rotula"); roda09.setCodigo("22207"); roda09.setStock(0); roda09.setOrigen("Republica Checa"); roda09.setMonto((float) 894.45); roda09.setCaracteristica("22207 . . . . . . . . . . . . ZKL       Rodamiento"); roda09.setMarca("ZKL"); roda09.setProveedor(prov01);roda09.persistirRodamiento(); 
//				RodamientoNegocio roda10 = new RodamientoNegocio(); roda10.setTipo("Rodamientos de rodillos a rotula"); roda10.setCodigo("22207"); roda10.setStock(0); roda10.setOrigen("Argentina"); roda10.setMonto((float) 48.03); roda10.setCaracteristica("22207 . . . . . . . . . . . . IMP       Rodamiento"); roda10.setMarca("IMP"); roda10.setProveedor(prov02);roda10.persistirRodamiento(); 
//				RodamientoNegocio roda11 = new RodamientoNegocio(); roda11.setTipo("Rodamientos de rodillos a rotula"); roda11.setCodigo("22207"); roda11.setStock(0); roda11.setOrigen("Argentina"); roda11.setMonto((float) 579.56); roda11.setCaracteristica("22207 . . . . . . . . . . . . ROLLWAY   Rodamiento"); roda11.setMarca("IMP"); roda11.setProveedor(prov03);roda11.persistirRodamiento(); 
//				RodamientoNegocio roda12 = new RodamientoNegocio(); roda12.setTipo("Rodamientos de rodillos a rotula"); roda12.setCodigo("22208 C3"); roda12.setStock(0); roda12.setOrigen("Suecia"); roda12.setMonto((float) 1758.43); roda12.setCaracteristica("22208 E/C3 . . . . . . . . . .SKF       Rodamiento"); roda12.setMarca("SKF"); roda12.setProveedor(prov04);roda12.persistirRodamiento(); 
//				RodamientoNegocio roda13 = new RodamientoNegocio(); roda13.setTipo("Rodamientos de rodillos a rotula"); roda13.setCodigo("22208 C3"); roda13.setStock(0); roda13.setOrigen("Republica Checa"); roda13.setMonto((float) 954.16); roda13.setCaracteristica("22208 EW33J/C3 . . . . . . . .ZKL       Rodamiento"); roda13.setMarca("ZKL"); roda13.setProveedor(prov05);roda13.persistirRodamiento(); 
//				RodamientoNegocio roda14 = new RodamientoNegocio(); roda14.setTipo("Rodamientos de rodillos a rotula"); roda14.setCodigo("22208 C3"); roda14.setStock(0); roda14.setOrigen("China"); roda14.setMonto((float) 718.34); roda14.setCaracteristica("22208 /C3 . . . . . . . . . . SNR       Rodamiento"); roda14.setMarca("SNR"); roda14.setProveedor(prov06);roda14.persistirRodamiento(); 
//				RodamientoNegocio roda15 = new RodamientoNegocio(); roda15.setTipo("Rodamientos de rodillos a rotula"); roda15.setCodigo("22208 C3"); roda15.setStock(0); roda15.setOrigen("Argentina"); roda15.setMonto((float) 117.02); roda15.setCaracteristica("22208/C3 . . . . . . . . . . .IMP       Rodamiento"); roda15.setMarca("IMP"); roda15.setProveedor(prov07);roda15.persistirRodamiento(); 
//				RodamientoNegocio roda16 = new RodamientoNegocio(); roda16.setTipo("Rodamientos de rodillos a rotula"); roda16.setCodigo("22226"); roda16.setStock(0); roda16.setOrigen("Suecia"); roda16.setMonto((float) 11655.74); roda16.setCaracteristica("22226 E . . . . . . . . . . . SKF       Rodamiento"); roda16.setMarca("SKF"); roda16.setProveedor(prov08);roda16.persistirRodamiento(); 
//				RodamientoNegocio roda17 = new RodamientoNegocio(); roda17.setTipo("Rodamientos de rodillos a rotula"); roda17.setCodigo("22226"); roda17.setStock(0); roda17.setOrigen("China"); roda17.setMonto((float) 5406.8); roda17.setCaracteristica("22226 EAW33 . . . . . . . . . SNR       Rodamiento"); roda17.setMarca("SNR"); roda17.setProveedor(prov01);roda17.persistirRodamiento(); 
//				RodamientoNegocio roda18 = new RodamientoNegocio(); roda18.setTipo("Rodamientos de rodillos a rotula"); roda18.setCodigo("22244 KC3"); roda18.setStock(0); roda18.setOrigen("Suecia"); roda18.setMonto((float) 72055.51); roda18.setCaracteristica("22244 CCK/C3W33 . . . . . . . SKF       Rodamiento"); roda18.setMarca("SKF"); roda18.setProveedor(prov02);roda18.persistirRodamiento(); 
//				RodamientoNegocio roda19 = new RodamientoNegocio(); roda19.setTipo("Rodamientos de rodillos a rotula"); roda19.setCodigo("22320"); roda19.setStock(0); roda19.setOrigen("Suecia"); roda19.setMonto((float) 13709.84); roda19.setCaracteristica("22320 E . . . . . . . . . . . SKF       Rodamiento"); roda19.setMarca("SKF"); roda19.setProveedor(prov03);roda19.persistirRodamiento(); 
//				RodamientoNegocio roda20 = new RodamientoNegocio(); roda20.setTipo("Rodamientos de rodillos a rotula"); roda20.setCodigo("22320 C3"); roda20.setStock(0); roda20.setOrigen("Suecia"); roda20.setMonto((float) 13709.84); roda20.setCaracteristica("22320 E/C3 . . . . . . . . . .SKF       Rodamiento"); roda20.setMarca("SKF"); roda20.setProveedor(prov04);roda20.persistirRodamiento(); 
//				RodamientoNegocio roda21 = new RodamientoNegocio(); roda21.setTipo("Rodamientos de rodillos a rotula"); roda21.setCodigo("22320"); roda21.setStock(0); roda21.setOrigen("Republica Checa"); roda21.setMonto((float) 5728.33); roda21.setCaracteristica("22320 JW33  . . . . . . . . . ZKL       Rodamiento"); roda21.setMarca("ZKL"); roda21.setProveedor(prov05);roda21.persistirRodamiento(); 
//				RodamientoNegocio roda22 = new RodamientoNegocio(); roda22.setTipo("Rodamientos de rodillos a rotula"); roda22.setCodigo("22320 C3"); roda22.setStock(0); roda22.setOrigen("Republica Checa"); roda22.setMonto((float) 7138.99); roda22.setCaracteristica("22320 EW33J/C3 . . . . . . . .ZKL       Rodamiento"); roda22.setMarca("ZKL"); roda22.setProveedor(prov06);roda22.persistirRodamiento(); 
//				RodamientoNegocio roda23 = new RodamientoNegocio(); roda23.setTipo("Rodamientos de rodillos a rotula"); roda23.setCodigo("22320 C3"); roda23.setStock(0); roda23.setOrigen("China"); roda23.setMonto((float) 5778.77); roda23.setCaracteristica("22320 /C3 . . . . . . . . . . SNR       Rodamiento"); roda23.setMarca("SNR"); roda23.setProveedor(prov07);roda23.persistirRodamiento(); 
//				RodamientoNegocio roda24 = new RodamientoNegocio(); roda24.setTipo("Rodamientos de rodillos a rotula"); roda24.setCodigo("22320"); roda24.setStock(0); roda24.setOrigen("Israel"); roda24.setMonto((float) 4062.12); roda24.setCaracteristica("22320 . . . . . . . . . . . . URB       Rodamiento"); roda24.setMarca("URB"); roda24.setProveedor(prov08);roda24.persistirRodamiento(); 
//				RodamientoNegocio roda25 = new RodamientoNegocio(); roda25.setTipo("Rodamientos de rodillos a rotula"); roda25.setCodigo("23026 KC3"); roda25.setStock(0); roda25.setOrigen("Suecia"); roda25.setMonto((float) 11278.93); roda25.setCaracteristica("23026 CCK/C3W33 . . . . . . . SKF       Rodamiento"); roda25.setMarca("SKF"); roda25.setProveedor(prov01);roda25.persistirRodamiento(); 
//				RodamientoNegocio roda26 = new RodamientoNegocio(); roda26.setTipo("Rodamientos Axiales"); roda26.setCodigo("51122"); roda26.setStock(0); roda26.setOrigen("Argentina"); roda26.setMonto((float) 3307.22); roda26.setCaracteristica("51122 . . . . . . . . . . . . SKF       Crapodina"); roda26.setMarca("SKF"); roda26.setProveedor(prov02);roda26.persistirRodamiento(); 
//				RodamientoNegocio roda27 = new RodamientoNegocio(); roda27.setTipo("Rodamientos Axiales"); roda27.setCodigo("51122"); roda27.setStock(0); roda27.setOrigen("Japon"); roda27.setMonto((float) 1108.64); roda27.setCaracteristica("51122 . . . . . . . . . . . . JAP       Crapodina"); roda27.setMarca("Japon"); roda27.setProveedor(prov03);roda27.persistirRodamiento(); 
//				RodamientoNegocio roda28 = new RodamientoNegocio(); roda28.setTipo("Rodamientos Axiales"); roda28.setCodigo("51122"); roda28.setStock(0); roda28.setOrigen("Republica Checa"); roda28.setMonto((float) 506); roda28.setCaracteristica("51122 . . . . . . . . . . . . ZKL       Crapodina"); roda28.setMarca("ZKL"); roda28.setProveedor(prov04);roda28.persistirRodamiento(); 
//				RodamientoNegocio roda29 = new RodamientoNegocio(); roda29.setTipo("Rodamientos Axiales"); roda29.setCodigo("51122"); roda29.setStock(0); roda29.setOrigen("Israel"); roda29.setMonto((float) 886.09); roda29.setCaracteristica("51122 . . . . . . . . . . . . URB       Crapodina"); roda29.setMarca("URB"); roda29.setProveedor(prov05);roda29.persistirRodamiento(); 
//				RodamientoNegocio roda30 = new RodamientoNegocio(); roda30.setTipo("Rodamientos Axiales"); roda30.setCodigo("51222"); roda30.setStock(0); roda30.setOrigen("Suecia"); roda30.setMonto((float) 5519.65); roda30.setCaracteristica("51222 . . . . . . . . . . . . SKF       Crapodina"); roda30.setMarca("SKF"); roda30.setProveedor(prov06);roda30.persistirRodamiento(); 
//				RodamientoNegocio roda31 = new RodamientoNegocio(); roda31.setTipo("Rodamientos Axiales"); roda31.setCodigo("51409 M"); roda31.setStock(0); roda31.setOrigen("Israel"); roda31.setMonto((float) 416.38); roda31.setCaracteristica("51409 M . . . . . . . . . . . URB       Crapodina"); roda31.setMarca("URB"); roda31.setProveedor(prov07);roda31.persistirRodamiento(); 
//				RodamientoNegocio roda32 = new RodamientoNegocio(); roda32.setTipo("Rodamientos Axiales"); roda32.setCodigo("52306"); roda32.setStock(0); roda32.setOrigen("Japon"); roda32.setMonto((float) 1153.55); roda32.setCaracteristica("52306 . . . . . . . . . . . . JAP       Crapodina"); roda32.setMarca("Japon"); roda32.setProveedor(prov08);roda32.persistirRodamiento(); 
//				RodamientoNegocio roda33 = new RodamientoNegocio(); roda33.setTipo("Rodamientos Axiales"); roda33.setCodigo("52306"); roda33.setStock(0); roda33.setOrigen("Republica Checa"); roda33.setMonto((float) 697.85); roda33.setCaracteristica("52306 . . . . . . . . . . . . ZKL       Crapodina"); roda33.setMarca("ZKL"); roda33.setProveedor(prov01);roda33.persistirRodamiento(); 
//				RodamientoNegocio roda34 = new RodamientoNegocio(); roda34.setTipo("Rodamientos Axiales"); roda34.setCodigo("BA 9"); roda34.setStock(0); roda34.setOrigen("Suecia"); roda34.setMonto((float) 682.58); roda34.setCaracteristica("BA 9 . . . . . . . . . . . . .SKF       Crapodina"); roda34.setMarca("SKF"); roda34.setProveedor(prov02);roda34.persistirRodamiento(); 
//				RodamientoNegocio roda35 = new RodamientoNegocio(); roda35.setTipo("Rodamientos de rodillos cilindricos"); roda35.setCodigo("MR 1307 EL"); roda35.setStock(0); roda35.setOrigen("EUA"); roda35.setMonto((float) 871.09); roda35.setCaracteristica("MR 1307 EL  . . . . . . . . . Bower     Rodamiento"); roda35.setMarca("Bower"); roda35.setProveedor(prov03);roda35.persistirRodamiento(); 
//				RodamientoNegocio roda36 = new RodamientoNegocio(); roda36.setTipo("Rodamientos de rodillos cilindricos"); roda36.setCodigo("F 207033"); roda36.setStock(0); roda36.setOrigen("Brasil"); roda36.setMonto((float) 279.91); roda36.setCaracteristica("F 207033 . . . . . . . . . . .INA       Rodamiento"); roda36.setMarca("INA"); roda36.setProveedor(prov04);roda36.persistirRodamiento(); 
//				RodamientoNegocio roda37 = new RodamientoNegocio(); roda37.setTipo("Rodamientos de rodillos cilindricos"); roda37.setCodigo("N 208"); roda37.setStock(0); roda37.setOrigen("Suecia"); roda37.setMonto((float) 1904.83); roda37.setCaracteristica("N 208 ECP . . . . . . . . . . SKF       Rodamiento"); roda37.setMarca("SKF"); roda37.setProveedor(prov05);roda37.persistirRodamiento(); 
//				RodamientoNegocio roda38 = new RodamientoNegocio(); roda38.setTipo("Rodamientos de rodillos cilindricos"); roda38.setCodigo("N 208"); roda38.setStock(0); roda38.setOrigen("Republica Checa"); roda38.setMonto((float) 385.87); roda38.setCaracteristica("N 208  . . . . . . . . . . . .ZKL       Rodamiento"); roda38.setMarca("ZKL"); roda38.setProveedor(prov06);roda38.persistirRodamiento(); 
//				RodamientoNegocio roda39 = new RodamientoNegocio(); roda39.setTipo("Rodamientos de rodillos cilindricos"); roda39.setCodigo("N 208"); roda39.setStock(0); roda39.setOrigen("Alemania"); roda39.setMonto((float) 1361.63); roda39.setCaracteristica("N 208 ETVP2  . . . . . . . . .FAG       Rodamiento"); roda39.setMarca("FAG"); roda39.setProveedor(prov07);roda39.persistirRodamiento(); 
//				RodamientoNegocio roda40 = new RodamientoNegocio(); roda40.setTipo("Rodamientos de rodillos cilindricos"); roda40.setCodigo("NU 211"); roda40.setStock(0); roda40.setOrigen("Argentina"); roda40.setMonto((float) 1892.69); roda40.setCaracteristica("NU 211 ECP . . . . . . . . . .SKF       Rodamiento"); roda40.setMarca("SKF"); roda40.setProveedor(prov08);roda40.persistirRodamiento(); 
//				RodamientoNegocio roda41 = new RodamientoNegocio(); roda41.setTipo("Rodamientos de rodillos cilindricos"); roda41.setCodigo("NU 211"); roda41.setStock(0); roda41.setOrigen("Suecia"); roda41.setMonto((float) 2082.32); roda41.setCaracteristica("NU 211 ECJ . . . . . . . . . .SKF       Rodamiento"); roda41.setMarca("SKF"); roda41.setProveedor(prov01);roda41.persistirRodamiento(); 
//				RodamientoNegocio roda42 = new RodamientoNegocio(); roda42.setTipo("Rodamientos de rodillos cilindricos"); roda42.setCodigo("NU 211"); roda42.setStock(0); roda42.setOrigen("Republica Checa"); roda42.setMonto((float) 559.3); roda42.setCaracteristica("NU 211 . . . . . . . . . . . .ZKL       Rodamiento"); roda42.setMarca("ZKL"); roda42.setProveedor(prov02);roda42.persistirRodamiento(); 
//				RodamientoNegocio roda43 = new RodamientoNegocio(); roda43.setTipo("Rodamientos de rodillos cilindricos"); roda43.setCodigo("NU 211"); roda43.setStock(0); roda43.setOrigen("Israel"); roda43.setMonto((float) 296.54); roda43.setCaracteristica("NU 211 . . . . . . . . . . . .URB       Rodamiento"); roda43.setMarca("URB"); roda43.setProveedor(prov03);roda43.persistirRodamiento(); 
//				RodamientoNegocio roda44 = new RodamientoNegocio(); roda44.setTipo("Rodamientos de rodillos cilindricos"); roda44.setCodigo("NU 211"); roda44.setStock(0); roda44.setOrigen("Argentina"); roda44.setMonto((float) 171.21); roda44.setCaracteristica("NU 211 . . . . . . . . . . . .IMP       Rodamiento"); roda44.setMarca("IMP"); roda44.setProveedor(prov04);roda44.persistirRodamiento(); 
//				RodamientoNegocio roda45 = new RodamientoNegocio(); roda45.setTipo("Rodamientos de rodillos cilindricos"); roda45.setCodigo("NJ 2220"); roda45.setStock(0); roda45.setOrigen("Israel"); roda45.setMonto((float) 1986.92); roda45.setCaracteristica("NJ 2220 . . . . . . . . . . . URB       Rodamiento"); roda45.setMarca("URB"); roda45.setProveedor(prov05);roda45.persistirRodamiento(); 
//				RodamientoNegocio roda46 = new RodamientoNegocio(); roda46.setTipo("Rodamientos de rodillos cilindricos"); roda46.setCodigo("NJ 314 MC3"); roda46.setStock(0); roda46.setOrigen("Suecia"); roda46.setMonto((float) 9894.96); roda46.setCaracteristica("NJ 314 ECM/C3 . . . . . . . . SKF       Rodamiento"); roda46.setMarca("SKF"); roda46.setProveedor(prov06);roda46.persistirRodamiento(); 
//				RodamientoNegocio roda47 = new RodamientoNegocio(); roda47.setTipo("Rodamientos de rodillos cilindricos"); roda47.setCodigo("315405"); roda47.setStock(0); roda47.setOrigen("Argentina"); roda47.setMonto((float) 944.47); roda47.setCaracteristica("315405 A . . . . . . . . . . .IMP       Rodamiento"); roda47.setMarca("IMP"); roda47.setProveedor(prov07);roda47.persistirRodamiento(); 
//				RodamientoNegocio roda48 = new RodamientoNegocio(); roda48.setTipo("Rodamientos de rodillos cilindricos"); roda48.setCodigo("37 RUKS60NR"); roda48.setStock(0); roda48.setOrigen("Japon"); roda48.setMonto((float) 660.48); roda48.setCaracteristica("37 RUKS60NR (37x60x23) . . . .JAP       Rodamiento"); roda48.setMarca("Japon"); roda48.setProveedor(prov08);roda48.persistirRodamiento(); 
//				RodamientoNegocio roda49 = new RodamientoNegocio(); roda49.setTipo("Rodamientos de Rodillos Conicos"); roda49.setCodigo("30230"); roda49.setStock(0); roda49.setOrigen("Suecia"); roda49.setMonto((float) 26495.61); roda49.setCaracteristica("30230 . . . . . . . . . . . . SKF       Rodamiento"); roda49.setMarca("SKF"); roda49.setProveedor(prov01);roda49.persistirRodamiento(); 
//				RodamientoNegocio roda50 = new RodamientoNegocio(); roda50.setTipo("Rodamientos de Rodillos Conicos"); roda50.setCodigo("30230"); roda50.setStock(0); roda50.setOrigen("Israel"); roda50.setMonto((float) 6657.28); roda50.setCaracteristica("30230 . . . . . . . . . . . . URB       Rodamiento"); roda50.setMarca("URB"); roda50.setProveedor(prov02);roda50.persistirRodamiento(); 
//				RodamientoNegocio roda51 = new RodamientoNegocio(); roda51.setTipo("Rodamientos de Rodillos Conicos"); roda51.setCodigo("33210"); roda51.setStock(0); roda51.setOrigen("Suecia"); roda51.setMonto((float) 840.54); roda51.setCaracteristica("33210/Q . . . . . . . . . . . SKF       Rodamiento"); roda51.setMarca("SKF"); roda51.setProveedor(prov03);roda51.persistirRodamiento(); 
//				RodamientoNegocio roda52 = new RodamientoNegocio(); roda52.setTipo("Rodamientos de Rodillos Conicos"); roda52.setCodigo("33210"); roda52.setStock(0); roda52.setOrigen("China"); roda52.setMonto((float) 1458.35); roda52.setCaracteristica("33210 92KA1 . . . . . . . . . Timken    Rodamiento"); roda52.setMarca("Timken"); roda52.setProveedor(prov04);roda52.persistirRodamiento(); 
//				RodamientoNegocio roda53 = new RodamientoNegocio(); roda53.setTipo("Rodamientos de Rodillos Conicos"); roda53.setCodigo("33210"); roda53.setStock(0); roda53.setOrigen("Japon"); roda53.setMonto((float) 947.81); roda53.setCaracteristica("33210 . . . . . . . . . . . . JAP       Rodamiento"); roda53.setMarca("Japon"); roda53.setProveedor(prov05);roda53.persistirRodamiento(); 
//				RodamientoNegocio roda54 = new RodamientoNegocio(); roda54.setTipo("Rodamientos de Rodillos Conicos"); roda54.setCodigo("33210"); roda54.setStock(0); roda54.setOrigen("Alemania"); roda54.setMonto((float) 1131.56); roda54.setCaracteristica("33210 . . . . . . . . . . . . FAG       Rodamiento"); roda54.setMarca("FAG"); roda54.setProveedor(prov06);roda54.persistirRodamiento(); 
//				RodamientoNegocio roda55 = new RodamientoNegocio(); roda55.setTipo("Rodamientos de Rodillos Conicos"); roda55.setCodigo("33210"); roda55.setStock(0); roda55.setOrigen("China"); roda55.setMonto((float) 452.07); roda55.setCaracteristica("33210 . . . . . . . . . . . . Steyr     Rodamiento"); roda55.setMarca("Steyr"); roda55.setProveedor(prov07);roda55.persistirRodamiento(); 
//				RodamientoNegocio roda56 = new RodamientoNegocio(); roda56.setTipo("Rodamientos de Rodillos Conicos"); roda56.setCodigo("33210"); roda56.setStock(0); roda56.setOrigen("Argentina"); roda56.setMonto((float) 216.63); roda56.setCaracteristica("33210 . . . . . . . . . . . . IMP       Rodamiento"); roda56.setMarca("IMP"); roda56.setProveedor(prov08);roda56.persistirRodamiento(); 
//				RodamientoNegocio roda57 = new RodamientoNegocio(); roda57.setTipo("Rodamientos de Rodillos Conicos"); roda57.setCodigo("33210"); roda57.setStock(0); roda57.setOrigen("Argentina"); roda57.setMonto((float) 229.07); roda57.setCaracteristica("33210 . . . . . . . . . . . . MAR       Rodamiento"); roda57.setMarca("MAR"); roda57.setProveedor(prov01);roda57.persistirRodamiento(); 
//				RodamientoNegocio roda58 = new RodamientoNegocio(); roda58.setTipo("Rodamientos de Rodillos Conicos"); roda58.setCodigo("33210"); roda58.setStock(0); roda58.setOrigen("India"); roda58.setMonto((float) 434.17); roda58.setCaracteristica("33210 . . . . . . . . . . . . ARB       Rodamiento"); roda58.setMarca("ARB"); roda58.setProveedor(prov02);roda58.persistirRodamiento(); 
//				RodamientoNegocio roda59 = new RodamientoNegocio(); roda59.setTipo("Rodamientos de Rodillos Conicos"); roda59.setCodigo("XGA 33211"); roda59.setStock(0); roda59.setOrigen("China"); roda59.setMonto((float) 3132.34); roda59.setCaracteristica("XGA 33211 92KA1 . . . . . . . Timken    Rodamiento"); roda59.setMarca("Timken"); roda59.setProveedor(prov03);roda59.persistirRodamiento(); 
//				RodamientoNegocio roda60 = new RodamientoNegocio(); roda60.setTipo("Rodamientos de Rodillos Conicos"); roda60.setCodigo("K 33885/822"); roda60.setStock(0); roda60.setOrigen("Japon"); roda60.setMonto((float) 827.95); roda60.setCaracteristica("33885/33822  . . . . . . . . .JAP       Rodamiento"); roda60.setMarca("Japon"); roda60.setProveedor(prov04);roda60.persistirRodamiento(); 
//				RodamientoNegocio roda61 = new RodamientoNegocio(); roda61.setTipo("Rodamientos de Rodillos Conicos"); roda61.setCodigo("K 33885/822"); roda61.setStock(0); roda61.setOrigen("Argentina"); roda61.setMonto((float) 488.05); roda61.setCaracteristica("33885/33822  . . . . . . . . .IMP       Rodamiento"); roda61.setMarca("IMP"); roda61.setProveedor(prov05);roda61.persistirRodamiento(); 
//				RodamientoNegocio roda62 = new RodamientoNegocio(); roda62.setTipo("Rodamientos de Rodillos Conicos"); roda62.setCodigo("K 89443/410"); roda62.setStock(0); roda62.setOrigen("Japon"); roda62.setMonto((float) 551.75); roda62.setCaracteristica("K 89443/410 . . . . . . . . . JAP       Rodamiento"); roda62.setMarca("Japon"); roda62.setProveedor(prov06);roda62.persistirRodamiento(); 
//				RodamientoNegocio roda63 = new RodamientoNegocio(); roda63.setTipo("Rodamientos de Rodillos Conicos"); roda63.setCodigo("K 89443/410"); roda63.setStock(0); roda63.setOrigen("Argentina"); roda63.setMonto((float) 257.31); roda63.setCaracteristica("K 89443/410 . . . . . . . . . IMP       Rodamiento"); roda63.setMarca("IMP"); roda63.setProveedor(prov07);roda63.persistirRodamiento(); 
//				RodamientoNegocio roda64 = new RodamientoNegocio(); roda64.setTipo("Rodamientos Rigidos de bolas"); roda64.setCodigo("2309"); roda64.setStock(0); roda64.setOrigen("Suecia"); roda64.setMonto((float) 2526.66); roda64.setCaracteristica("2309 ETN9 . . . . . . . . . . SKF       Rodamiento"); roda64.setMarca("SKF"); roda64.setProveedor(prov08);roda64.persistirRodamiento(); 
//				RodamientoNegocio roda65 = new RodamientoNegocio(); roda65.setTipo("Rodamientos Rigidos de bolas"); roda65.setCodigo("2309"); roda65.setStock(0); roda65.setOrigen("Republica Checa"); roda65.setMonto((float) 415.87); roda65.setCaracteristica("2309 . . . . . . . . . . . . .ZKL       Rodamiento"); roda65.setMarca("ZKL"); roda65.setProveedor(prov01);roda65.persistirRodamiento(); 
//				RodamientoNegocio roda66 = new RodamientoNegocio(); roda66.setTipo("Rodamientos Rigidos de bolas"); roda66.setCodigo("2309"); roda66.setStock(0); roda66.setOrigen("China"); roda66.setMonto((float) 1634.63); roda66.setCaracteristica("2309 . . . . . . . . . . . . .Steyr     Rodamiento"); roda66.setMarca("Steyr"); roda66.setProveedor(prov02);roda66.persistirRodamiento(); 
//				RodamientoNegocio roda67 = new RodamientoNegocio(); roda67.setTipo("Rodamientos Rigidos de bolas"); roda67.setCodigo("2309"); roda67.setStock(0); roda67.setOrigen("Israel"); roda67.setMonto((float) 332.67); roda67.setCaracteristica("2309 . . . . . . . . . . . . .URB       Rodamiento"); roda67.setMarca("URB"); roda67.setProveedor(prov03);roda67.persistirRodamiento(); 
//				RodamientoNegocio roda68 = new RodamientoNegocio(); roda68.setTipo("Rodamientos Rigidos de bolas"); roda68.setCodigo("2309"); roda68.setStock(0); roda68.setOrigen("Argentina"); roda68.setMonto((float) 376.91); roda68.setCaracteristica("2309 . . . . . . . . . . . . .IMP       Rodamiento"); roda68.setMarca("IMP"); roda68.setProveedor(prov04);roda68.persistirRodamiento(); 
//				RodamientoNegocio roda69 = new RodamientoNegocio(); roda69.setTipo("Rodamientos Rigidos de bolas"); roda69.setCodigo("ZKLF 2575 2RS"); roda69.setStock(0); roda69.setOrigen("Brasil"); roda69.setMonto((float) 9952.21); roda69.setCaracteristica("ZKLF 2575 2RS   . . . . . . . INA       Rodamiento"); roda69.setMarca("INA"); roda69.setProveedor(prov05);roda69.persistirRodamiento(); 
//				RodamientoNegocio roda70 = new RodamientoNegocio(); roda70.setTipo("Rodamientos Rigidos de bolas"); roda70.setCodigo("QJ 317 N2M"); roda70.setStock(0); roda70.setOrigen("Suecia"); roda70.setMonto((float) 11906.68); roda70.setCaracteristica("QJ 317 N2MA . . . . . . . . . SKF       Rodamiento"); roda70.setMarca("SKF"); roda70.setProveedor(prov06);roda70.persistirRodamiento(); 
//				RodamientoNegocio roda71 = new RodamientoNegocio(); roda71.setTipo("Rodamientos Rigidos de bolas"); roda71.setCodigo("ZL 5202 DRS"); roda71.setStock(0); roda71.setOrigen("Brasil"); roda71.setMonto((float) 1162.76); roda71.setCaracteristica("ZL 5202-DRS. . . . . . . . . .INA       Rodamiento"); roda71.setMarca("INA"); roda71.setProveedor(prov07);roda71.persistirRodamiento(); 
//				RodamientoNegocio roda72 = new RodamientoNegocio(); roda72.setTipo("Rodamientos Rigidos de bolas"); roda72.setCodigo("6006"); roda72.setStock(0); roda72.setOrigen("Argentina"); roda72.setMonto((float) 110.21); roda72.setCaracteristica("6006 . . . . . . . . . . . . .SKF       Rodamiento"); roda72.setMarca("SKF"); roda72.setProveedor(prov08);roda72.persistirRodamiento(); 
//				RodamientoNegocio roda73 = new RodamientoNegocio(); roda73.setTipo("Rodamientos Rigidos de bolas"); roda73.setCodigo("6006"); roda73.setStock(0); roda73.setOrigen("Suecia"); roda73.setMonto((float) 1233.35); roda73.setCaracteristica("W 6006 . . . . . . . . . . . .SKF       Rodamiento"); roda73.setMarca("SKF"); roda73.setProveedor(prov01);roda73.persistirRodamiento(); 
//				RodamientoNegocio roda74 = new RodamientoNegocio(); roda74.setTipo("Rodamientos Rigidos de bolas"); roda74.setCodigo("6006"); roda74.setStock(0); roda74.setOrigen("Japon"); roda74.setMonto((float) 108.93); roda74.setCaracteristica("6006 . . . . . . . . . . . . .JAP       Rodamiento"); roda74.setMarca("Japon"); roda74.setProveedor(prov02);roda74.persistirRodamiento(); 
//				RodamientoNegocio roda75 = new RodamientoNegocio(); roda75.setTipo("Rodamientos Rigidos de bolas"); roda75.setCodigo("6006"); roda75.setStock(0); roda75.setOrigen("China"); roda75.setMonto((float) 62.06); roda75.setCaracteristica("6006 . . . . . . . . . . . . .Steyr     Rodamiento"); roda75.setMarca("Steyr"); roda75.setProveedor(prov03);roda75.persistirRodamiento(); 
//				RodamientoNegocio roda76 = new RodamientoNegocio(); roda76.setTipo("Rodamientos Rigidos de bolas"); roda76.setCodigo("6006"); roda76.setStock(0); roda76.setOrigen("Argentina"); roda76.setMonto((float) 26.2); roda76.setCaracteristica("6006 . . . . . . . . . . . . .IMP       Rodamiento"); roda76.setMarca("IMP"); roda76.setProveedor(prov04);roda76.persistirRodamiento(); 
//				RodamientoNegocio roda77 = new RodamientoNegocio(); roda77.setTipo("Rodamientos Rigidos de bolas"); roda77.setCodigo("683"); roda77.setStock(0); roda77.setOrigen("Argentina"); roda77.setMonto((float) 59.62); roda77.setCaracteristica("618/3 . . . . . . . . . . . . GMN       Rodamiento"); roda77.setMarca("GMN"); roda77.setProveedor(prov05);roda77.persistirRodamiento(); 
//				RodamientoNegocio roda78 = new RodamientoNegocio(); roda78.setTipo("Rodamientos Rigidos de bolas"); roda78.setCodigo("61824 2RS"); roda78.setStock(0); roda78.setOrigen("Suecia"); roda78.setMonto((float) 6756.27); roda78.setCaracteristica("61824-2RS1  . . . . . . . . . SKF       Rodamiento"); roda78.setMarca("SKF"); roda78.setProveedor(prov06);roda78.persistirRodamiento(); 
//				RodamientoNegocio roda79 = new RodamientoNegocio(); roda79.setTipo("Rodamientos Rigidos de bolas"); roda79.setCodigo("61824 2RS"); roda79.setStock(0); roda79.setOrigen("Japon"); roda79.setMonto((float) 2258.43); roda79.setCaracteristica("61824 2RS (6824 VV) . . . . . JAP       Rodamiento"); roda79.setMarca("Japon"); roda79.setProveedor(prov07);roda79.persistirRodamiento(); 
//				RodamientoNegocio roda80 = new RodamientoNegocio(); roda80.setTipo("Rodamientos Rigidos de bolas"); roda80.setCodigo("6313 NR"); roda80.setStock(0); roda80.setOrigen("Israel"); roda80.setMonto((float) 471.54); roda80.setCaracteristica("6313 NR . . . . . . . . . . . URB       Rodamiento"); roda80.setMarca("URB"); roda80.setProveedor(prov08);roda80.persistirRodamiento(); 
//
//		// Asociamos rodamientos a proveedores
//				ArrayList<RodamientoNegocio> listProv01 = new ArrayList<RodamientoNegocio>(); 
//				ArrayList<RodamientoNegocio> listProv02 = new ArrayList<RodamientoNegocio>(); 
//				ArrayList<RodamientoNegocio> listProv03 = new ArrayList<RodamientoNegocio>(); 
//				ArrayList<RodamientoNegocio> listProv04 = new ArrayList<RodamientoNegocio>(); 
//				ArrayList<RodamientoNegocio> listProv05 = new ArrayList<RodamientoNegocio>(); 
//				ArrayList<RodamientoNegocio> listProv06 = new ArrayList<RodamientoNegocio>(); 
//				ArrayList<RodamientoNegocio> listProv07 = new ArrayList<RodamientoNegocio>(); 
//				ArrayList<RodamientoNegocio> listProv08 = new ArrayList<RodamientoNegocio>(); 
//
//				listProv01.add(roda01);		listProv02.add(roda02);		listProv03.add(roda03);		listProv04.add(roda04);
//				listProv05.add(roda05);		listProv06.add(roda06);		listProv07.add(roda07);		listProv08.add(roda08);
//				listProv01.add(roda09);		listProv02.add(roda10);		listProv03.add(roda11);		listProv04.add(roda12);
//				listProv05.add(roda13);		listProv06.add(roda14);		listProv07.add(roda15);		listProv08.add(roda16);
//				listProv01.add(roda17);		listProv02.add(roda18);		listProv03.add(roda19);		listProv04.add(roda20);
//				listProv05.add(roda21);		listProv06.add(roda22);		listProv07.add(roda23);		listProv08.add(roda24);
//				listProv01.add(roda25);		listProv02.add(roda26);		listProv03.add(roda27);		listProv04.add(roda28);
//				listProv05.add(roda29);		listProv06.add(roda30);		listProv07.add(roda31);		listProv08.add(roda32);
//				listProv01.add(roda33);		listProv02.add(roda34);		listProv03.add(roda35);		listProv04.add(roda36);
//				listProv05.add(roda37);		listProv06.add(roda38);		listProv07.add(roda39);		listProv08.add(roda40);
//				listProv01.add(roda41);		listProv02.add(roda42);		listProv03.add(roda43);		listProv04.add(roda44);
//				listProv05.add(roda45);		listProv06.add(roda46);		listProv07.add(roda47);		listProv08.add(roda48);
//				listProv01.add(roda49);		listProv02.add(roda50);		listProv03.add(roda51);		listProv04.add(roda52);
//				listProv05.add(roda53);		listProv06.add(roda54);		listProv07.add(roda55);		listProv08.add(roda56);
//				listProv01.add(roda57);		listProv02.add(roda58);		listProv03.add(roda59);		listProv04.add(roda60);
//				listProv05.add(roda61);		listProv06.add(roda62);		listProv07.add(roda63);		listProv08.add(roda64);
//				listProv01.add(roda65);		listProv02.add(roda66);		listProv03.add(roda67);		listProv04.add(roda68);
//				listProv05.add(roda69);		listProv06.add(roda70);		listProv07.add(roda71);		listProv08.add(roda72);
//				listProv01.add(roda73);		listProv02.add(roda74);		listProv03.add(roda75);		listProv04.add(roda76);
//				listProv05.add(roda77);		listProv06.add(roda78);		listProv07.add(roda79);		listProv08.add(roda80);
//
//				prov01.setRodamientos(listProv01);		prov01.updateProveedor();
//				prov02.setRodamientos(listProv02);		prov02.updateProveedor();
//				prov03.setRodamientos(listProv03);		prov03.updateProveedor();
//				prov04.setRodamientos(listProv04);		prov04.updateProveedor();
//				prov05.setRodamientos(listProv05);		prov05.updateProveedor();
//				prov06.setRodamientos(listProv06);		prov06.updateProveedor();
//				prov07.setRodamientos(listProv07);		prov07.updateProveedor();
//				prov08.setRodamientos(listProv08);		prov08.updateProveedor();
//
//		// Alta de Clientes
//				
//				ClienteNegocio cli01 = new ClienteNegocio("Mecanica Industrial SRL", "compras@mecind.com.ar", "30-11111111-2");
//				ClienteNegocio cli02 = new ClienteNegocio("Maquinaria Industrial SA", "compras@mi.com.ar", "30-22222222-3");
//				ClienteNegocio cli03 = new ClienteNegocio("Isolda e Hijos SRL", "jose.rodriguez@isolda.com", "30-33333333-4");
//				ClienteNegocio cli04 = new ClienteNegocio("Cooperativa Industrial Campo Santo", "compras@camposanto.coop.ar", "30-44444444-5");
//				ClienteNegocio cli05 = new ClienteNegocio("Industrial Rosales SA", "roberto.fuentes@rosales.com.ar", "30-55555555-6");
//				ClienteNegocio cli06 = new ClienteNegocio("Tubos Norte SRL", "compras@tubosnorte.com", "30-66666666-7");
//				
//				cli01.persistirCliente();
//				cli02.persistirCliente();
//				cli03.persistirCliente();
//				cli04.persistirCliente();
//				cli05.persistirCliente();
//				cli06.persistirCliente();
//
//		// Alta de cotizaciones
//				// Seteo de fechas				
//				Calendar cal01 = GregorianCalendar.getInstance(); 	cal01.add(Calendar.DAY_OF_YEAR, -1);	Date day01 = cal01.getTime();	cal01.add(Calendar.MONTH, 1); 	Date day06 = cal01.getTime();
//				Calendar cal02 = GregorianCalendar.getInstance();	cal02.add(Calendar.DAY_OF_YEAR, -5);	Date day02 = cal02.getTime();	cal02.add(Calendar.MONTH, 1); 	Date day07 = cal02.getTime();
//				Calendar cal03 = GregorianCalendar.getInstance();	cal03.add(Calendar.DAY_OF_YEAR, -13);	Date day03 = cal03.getTime();	cal03.add(Calendar.MONTH, 1); 	Date day08 = cal03.getTime();
//				Calendar cal04 = GregorianCalendar.getInstance();	cal04.add(Calendar.DAY_OF_YEAR, -27);	Date day04 = cal04.getTime();	cal04.add(Calendar.MONTH, 1); 	Date day09 = cal04.getTime();
//				Calendar cal05 = GregorianCalendar.getInstance();	cal05.add(Calendar.DAY_OF_YEAR, -31);	Date day05 = cal05.getTime();	cal05.add(Calendar.MONTH, 1); 	Date day10 = cal05.getTime();
//		
//				// Seteo de Clientes y fechas en cotizaciones
//				CotizacionNegocio cot01 = new CotizacionNegocio();	cot01.setCliente(cli01); 	cot01.setEstado("pendiente"); 	cot01.setFechaCreacion(day01); 	cot01.setFechaVigencia(day06);
//				CotizacionNegocio cot02 = new CotizacionNegocio();	cot02.setCliente(cli02); 	cot02.setEstado("aprobada"); 	cot02.setFechaCreacion(day02); 	cot02.setFechaVigencia(day07);
//				CotizacionNegocio cot03 = new CotizacionNegocio();	cot03.setCliente(cli01); 	cot03.setEstado("aprobada"); 	cot03.setFechaCreacion(day03); 	cot03.setFechaVigencia(day08);
//				CotizacionNegocio cot04 = new CotizacionNegocio();	cot04.setCliente(cli04); 	cot04.setEstado("aprobada"); 	cot04.setFechaCreacion(day04); 	cot04.setFechaVigencia(day09);
//				CotizacionNegocio cot05 = new CotizacionNegocio();	cot05.setCliente(cli01); 	cot05.setEstado("aprobada"); 	cot05.setFechaCreacion(day01); 	cot05.setFechaVigencia(day06);
//				CotizacionNegocio cot06 = new CotizacionNegocio();	cot06.setCliente(cli03); 	cot06.setEstado("aprobada"); 	cot06.setFechaCreacion(day02); 	cot06.setFechaVigencia(day07);
//				CotizacionNegocio cot07 = new CotizacionNegocio();	cot07.setCliente(cli05); 	cot07.setEstado("pendiente"); 	cot07.setFechaCreacion(day02); 	cot07.setFechaVigencia(day07);
//				CotizacionNegocio cot08 = new CotizacionNegocio();	cot08.setCliente(cli03); 	cot08.setEstado("aprobada"); 	cot08.setFechaCreacion(day05); 	cot08.setFechaVigencia(day10);
//				
//				// Alta de itemsCotizacion
//				List<ItemCotizacionNegocio> listItem01 = new ArrayList<ItemCotizacionNegocio>();
//				ItemCotizacionNegocio itCot0101 = new ItemCotizacionNegocio(roda28, 25);	listItem01.add(itCot0101);
//				ItemCotizacionNegocio itCot0102 = new ItemCotizacionNegocio(roda33, 3);		listItem01.add(itCot0102);
//				ItemCotizacionNegocio itCot0103 = new ItemCotizacionNegocio(roda72, 7);		listItem01.add(itCot0103);	
//				ItemCotizacionNegocio itCot0104 = new ItemCotizacionNegocio(roda12, 91);	listItem01.add(itCot0104);
//				ItemCotizacionNegocio itCot0105 = new ItemCotizacionNegocio(roda33, 14);	listItem01.add(itCot0105);
//				
//				List<ItemCotizacionNegocio> listItem02 = new ArrayList<ItemCotizacionNegocio>();
//				ItemCotizacionNegocio itCot0201 = new ItemCotizacionNegocio(roda29, 5);		listItem02.add(itCot0201);
//				ItemCotizacionNegocio itCot0202 = new ItemCotizacionNegocio(roda37, 13);	listItem02.add(itCot0202);
//				ItemCotizacionNegocio itCot0203 = new ItemCotizacionNegocio(roda79, 14);	listItem02.add(itCot0203);
//				
//				List<ItemCotizacionNegocio> listItem03 = new ArrayList<ItemCotizacionNegocio>();
//				ItemCotizacionNegocio itCot0301 = new ItemCotizacionNegocio(roda42, 15);	listItem03.add(itCot0301);
//				ItemCotizacionNegocio itCot0302 = new ItemCotizacionNegocio(roda66, 31);	listItem03.add(itCot0302);
//				
//				List<ItemCotizacionNegocio> listItem04 = new ArrayList<ItemCotizacionNegocio>();
//				ItemCotizacionNegocio itCot0401 = new ItemCotizacionNegocio(roda01, 15);	listItem04.add(itCot0401);
//				ItemCotizacionNegocio itCot0402 = new ItemCotizacionNegocio(roda09, 22);	listItem04.add(itCot0402);
//				
//				List<ItemCotizacionNegocio> listItem05 = new ArrayList<ItemCotizacionNegocio>();
//				ItemCotizacionNegocio itCot0501 = new ItemCotizacionNegocio(roda21, 15);	listItem05.add(itCot0501);
//				ItemCotizacionNegocio itCot0502 = new ItemCotizacionNegocio(roda39, 12);	listItem05.add(itCot0502);
//				ItemCotizacionNegocio itCot0503 = new ItemCotizacionNegocio(roda55, 22);	listItem05.add(itCot0503);
//				ItemCotizacionNegocio itCot0504 = new ItemCotizacionNegocio(roda67, 19);	listItem05.add(itCot0504);
//				
//				List<ItemCotizacionNegocio> listItem06 = new ArrayList<ItemCotizacionNegocio>();
//				ItemCotizacionNegocio itCot0601 = new ItemCotizacionNegocio(roda22, 90);	listItem06.add(itCot0601);
//				ItemCotizacionNegocio itCot0602 = new ItemCotizacionNegocio(roda39, 14);	listItem01.add(itCot0602);
//				ItemCotizacionNegocio itCot0603 = new ItemCotizacionNegocio(roda10, 10);	listItem06.add(itCot0603);
//				
//				List<ItemCotizacionNegocio> listItem07 = new ArrayList<ItemCotizacionNegocio>();
//				ItemCotizacionNegocio itCot0701 = new ItemCotizacionNegocio(roda78, 22);	listItem07.add(itCot0701);
//				ItemCotizacionNegocio itCot0702 = new ItemCotizacionNegocio(roda65, 14);	listItem07.add(itCot0702);
//				ItemCotizacionNegocio itCot0703 = new ItemCotizacionNegocio(roda51, 17);	listItem07.add(itCot0703);
//				
//				List<ItemCotizacionNegocio> listItem08 = new ArrayList<ItemCotizacionNegocio>();
//				ItemCotizacionNegocio itCot0801 = new ItemCotizacionNegocio(roda19, 13);	listItem08.add(itCot0801);
//				ItemCotizacionNegocio itCot0802 = new ItemCotizacionNegocio(roda77, 13);	listItem08.add(itCot0802);
//				
//				// Asociamos Lista de Items Cotizaciones a Cotizaciones y persistimos
//				cot01.setItems(listItem01);		cot01.persistirCotizacion();
//				cot02.setItems(listItem02);		cot02.persistirCotizacion();
//				cot03.setItems(listItem03);		cot03.persistirCotizacion();
//				cot04.setItems(listItem04);		cot04.persistirCotizacion();
//				cot05.setItems(listItem05);		cot05.persistirCotizacion();
//				cot06.setItems(listItem06);		cot06.persistirCotizacion();
//				cot07.setItems(listItem07);		cot07.persistirCotizacion();
//				cot08.setItems(listItem08);		cot08.persistirCotizacion();
//				
//				
//				
//				
//				
//				
//	}
//}
