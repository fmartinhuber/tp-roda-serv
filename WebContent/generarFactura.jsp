<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	function agregar(){
		listItems.push({
			"codigo": document.getElementById('codigo').value,
			"cantidad": document.getElementById('cantidad').value
		});
		alert("Se agrego un elemento");
	}
	
	function enviar(){
		var a = JSON.stringify(listItems);
		document.getElementById("listaCotizacion").value = a;
		document.getElementById("metodo").value = "crearFactura";
		alert("Se creo la cotizacion");
	}
</script>

<form action="FacturaServlet" method="POST">
<input type="hidden" name="listaCotizacion" id="listaCotizacion" value="">
<input type="hidden" name="metodo" id="metodo" value="">
	<table>
		<tr><td>Cliente: </td><td><input type="TEXT" name="cliente" value="1"></tr>
		<tr><td>Fecha: </td><td><input type="TEXT" name="fecha" value="01/01/2015"></tr>
		<tr><td colspan="2">Factura</td></tr>
		<tr><td>Codigo:</td><td><input type="TEXT" id="codigo" value="1"></td></tr>
		<tr><td colspan="2" align="right"><input type="button" value="Agregar" onClick="agregar();"></td></tr>
		<tr><td>Sub total:</td><td><input type="TEXT" id="subTotal" value="1"></td></tr>
		<tr><td>Total:</td><td><input type="TEXT" id="total" value="1"></td></tr>
		<tr><td align="center"><input type="submit" value="Aceptar" onClick="enviar();"></td><td align="center"><input type="reset" value="Cancelar" ></td></tr>
	</table>
</form>
</body>
</html>