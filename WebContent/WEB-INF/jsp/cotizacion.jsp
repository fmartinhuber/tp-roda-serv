<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cotizacion</title>
</head>
<body>
<form action="CotizacionServlet" method="POST">
<table>
	<tr><td>Razon Social:</td><td><input type="TEXT" name="razon"></td></tr>
	<tr><td>Mail:</td><td><input type="TEXT" name="mail"></td></tr>
	<tr><td>Nombre:</td><td><input type="TEXT" name="Nombre"></td></tr>
	<tr><td>CUIT:</td><td><input type="TEXT" name="cuit"></td></tr>
	<tr><td>Rodamiento:</td><td><input type="TEXT" name="rodamiento"></td><input type="SUBMIT" value="Agregar"></td></tr>
	
	<tr><td colspan=2><input type="SUBMIT" value="Aceptar	"></td></tr>
</table>
</form>
</body>
</html>