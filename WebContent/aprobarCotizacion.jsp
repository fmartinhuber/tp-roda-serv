<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aprobar cotizaciones</title>
</head>
<body>

<script type="text/javascript">
function enviar(){
	
}


</script>
<form action="CotizacionServlet" method="POST">
<input type="hidden" name="listaRodamiento" id="listaRodamiento" value="">
<table>
	<tr><td>Cliente:</td><td><input type="TEXT" name="cliente" value="1"></td></tr>
	<tr><td>Cuit:</td><td><input type="TEXT" name="cuit" value="1"></td></tr>
	<tr><td colspan="2" align="center">Cotizaciones</td></tr>
	<tr><td>Nro cotizacion:</td><td><input type="TEXT" id="codigo" value="1"></td></tr>
	<tr><td align="center"><input type="submit" value="Aceptar" onClick="enviar();"></td><td align="center"><input type="reset" value="Cancelar" ></td></tr>
</table>
</form>

</body>
</html>