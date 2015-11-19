<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!--  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aprobar cotizaciones</title>
</head>
<body>

	<script type="text/javascript">
		function enviar() {
			alert("Cotizacion aprobada");
			document.getElementById("AprobarCotizacionServlet").submit();
		}
		
		function buscar() {
			
			
			document.getElementById("AprobarCotizacionServlet").submit();
		}
		
		
	</script>
	<form action="/buscarCotizaciones" id='buscarCotizaciones' method="POST">
		<input type="hidden" name="listaRodamiento" id="listaRodamiento" value="" />
		<!--<jsp:useBean id="CotizacionDtoId" class="dto.CotizacionDto" scope="session">
			<jsp:setProperty property="*" name="CotizacionDtoId" />
		</jsp:useBean>-->
		<table>
			<tr>
				<td>Cliente:</td>
				<td><input type="TEXT" name="cliente" value="1"></td>
			</tr>
			<tr>
				<td>Cuit:</td>
				<td><input type="TEXT" name="cuit" value="1"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="button" value="Buscar" onClick="buscar()"></td>
			</tr>
		</table>
	</form>
</body>
</html>