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
			document.getElementById("method").value = "aprobarCotizacion";
			document.getElementById("AprobarCotizacionServlet").submit();
		}
		
		
		
	</script>
	<form action="CotizacionServlet" method="POST">
		<input type="hidden" name="listaRodamiento" id="listaRodamiento" value="" />
		<input type="hidden" name="metodo" id="metodo" value="">
		<table>
			<tr>
				<td colspan="2" align="center">Cotizaciones</td>
			</tr>
			<tr>
				<td>Nro cotizacion:</td>
				<td>
					<!--  <select name="cotizacionSeleccionada">
						<option value="${cotizacionSeleccionada}" selected>${cotizacionSeleccionada}</option>
						<c:forEach items="${arrayCotizaciones}" var="cotizacion">
							<c:if test="${cotizacion != selected}">
								<option value="${cotizacion}">${cotizacion}</option>
							</c:if>
						</c:forEach>
					</select>-->
					<input type="TEXT" id="cotizacionSeleccionada" value="1">
				</td>
			</tr>
			<!--<jsp:getProperty property="items" name="CotizacionDtoId" />-->
			
			<tr>
				<td align="center"><input type="submit" value="Aceptar" onClick="enviar();"></td>
				<td align="center"><input type="reset" value="Cancelar"></td>
			</tr>
		</table>
	</form>
</body>
</html>