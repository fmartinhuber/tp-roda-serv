<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
function agregar(){
	listItems.push({
		"codigo": document.getElementById('codigo').value,
		"cantidad": document.getElementById('cantidad').value,
		"caracteristica": document.getElementById('caracteristica').value,
		"pais": document.getElementById('pais').value,
		"marca": document.getElementById('marca').value
	});
	alert("Se agrego un elemento");
}

function enviar(){
	var a = JSON.stringify(listItems);
	document.getElementById("listaRodamiento").value = a;
	document.getElementById("metodo").value = "generarCotizacion";
	alert("Se creo la cotizacion");
}

<form action="CotizacionServlet" method="POST">
<input type="hidden" name="listaRodamiento" id="listaRodamiento" value="">
<input type="hidden" name="metodo" id="metodo" value="">
	<table>
		<tr>
			<td>Cliente: </td><td><input type="TEXT" name="cliente" value="1">
			<td>Cliente: </td><td><input type="TEXT" name="cliente" value="1">
			<td colspan="2">Factura</td>
			
			
		</tr>
	
	</table>
</form>
</body>
</html>