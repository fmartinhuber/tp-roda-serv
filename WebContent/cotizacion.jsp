<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cotizacion</title>
</head>
<body>
<script type="text/javascript">

var listItems = {
    rodamientos: []
};

//var listaRodamiento = '{ "rodamiento" : [' + '{ "codigo": "", "cantidad":"", "caracteristica": "", "pais":"" , "marca:" "" }' +']}';

function agregar(){
	listItems.rodamientos.push({
		"codigo": document.getElementsByName('codigo').getValue,
		"cantidad": document.getElementsByName('cantidad').getValue,
		"caracteristica": document.getElementsByName('caracteristica').getValue,
		"pais": document.getElementsByName('pais').getValue ,
		"marca": document.getElementsByName('marca').getValue
	});
}

function enviar(){
	JSON.stringify(listItems);
	document.listaRodamiento = listItems;
}


</script>
<form action="CotizacionServlet" method="POST">
<input type="hidden" name="listaRodamiento" >
<input type="hidden" name="listCantidad" >
<table>
	<tr><td>Razon Social:</td><td><input type="TEXT" name="razonSocial"></td></tr>
	<tr><td>CUIT:</td><td><input type="TEXT" name="cuit"></td></tr>
	<tr><td colspan="2" align="center">Rodamiento</td></tr>
	<tr><td>Codigo:</td><td><input type="TEXT" name="codigo"></td></tr>
	<tr><td>Cantidad:</td><td><input type="TEXT" name="cantidad"></td></tr>
	<tr><td>caracteristica:</td><td><input type="TEXT" name="caracteristica"></td></tr>
	<tr><td>pais:</td><td><input type="TEXT" name="pais"></td></tr>
	<tr><td>marca:</td><td><input type="TEXT" name="marca"></td></tr>
	<tr><td><input type="Submit" value="Agregar" onClick="agregar();"></td><td><input type="SUBMIT" value="Aceptar" onClick="enviar();"></td></tr>
</table>
</form>
</body>
</html>