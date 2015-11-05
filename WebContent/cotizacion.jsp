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
	alert("Se agrego un elemento");
}

function enviar(){
	JSON.stringify(listItems);
	document.getElementById("listaRodamiento").value = listItems;
	alert("Se creo la cotizacion");
}


</script>
<form action="CotizacionServlet" method="POST">
<input type="hidden" name="listaRodamiento" value="">
<table>
	<tr><td>Razon Social:</td><td><input type="TEXT" name="razonSocial" value="1"></td></tr>
	<tr><td>CUIT:</td><td><input type="TEXT" name="cuit" value="1"></td></tr>
	<tr><td colspan="2" align="center">Rodamiento</td></tr>
	<tr><td>Codigo:</td><td><input type="TEXT" name="codigo" value="1"></td></tr>
	<tr><td>Cantidad:</td><td><input type="TEXT" name="cantidad" value="1"></td></tr>
	<tr><td>caracteristica:</td><td><input type="TEXT" name="caracteristica" value="1"></td></tr>
	<tr><td>pais:</td><td><input type="TEXT" name="pais" value="1"></td></tr>
	<tr><td>marca:</td><td><input type="TEXT" name="marca" value="1"	></td></tr>
	<tr><td colspan="2" align="right"><input type="button" value="Agregar" onClick="agregar();"></td></tr>
	<tr><td align="center"><input type="submit" value="Aceptar" onClick="enviar();"></td><td align="center"><input type="reset" value="Cancelar" ></td></tr>
</table>
</form>
</body>
</html>