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
	<jsp:useBean id="cotizacion" class="dto.CotizacionDto"/>
	<tr><td>Razon Social:</td><td><jsp:setProperty name="cotizacion" property="razonSocial" /></td></tr>
	<tr><td>CUIT:</td><td><input type="TEXT" name="cuit"></td></tr>
	<tr><td colspan="5">Rodamiento</td></<tr>
	<tr><td>Cantidad:</td><td><input type="TEXT" name="cantidad"></td></tr>
	<tr><td>caracteristica:</td><td><input type="TEXT" name="caracteristica"></td></tr>
	<tr><td>pais:</td><td><input type="TEXT" name="pais"></td></tr>
	<tr><td>marca:</td><td><input type="TEXT" name="marca"></td></tr>
	<tr><td colspan=2><input type="SUBMIT" value="Aceptar"></td></tr>
</table>
</form>
</body>
</html>