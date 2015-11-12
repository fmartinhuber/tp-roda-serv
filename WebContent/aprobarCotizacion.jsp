<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		}
		
	</script>
	<form action="AprobarCotizacionServlet" method="aceptarCotizaciones">
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
				<td align="center" colspan="2"><input type="submit" value="Buscar" ></td>
			</tr>
		</table>
	</form>
	<form action="AprobarCotizacionServlet" method="aprobarCotizaciones">
		<table>
			<tr>
				<td colspan="2" align="center">Cotizaciones</td>
			</tr>
			<tr>
				<td>Nro cotizacion:</td>
				<td>
					<select name="cotizacion">
						<option value="${cotizacionSeleccionada}" selected>${cotizacionSeleccionada}</option>
						<c:forEach items="${arrayCotizaciones}" var="cotizacion">
							<c:if test="${cotizacion != selected}">
								<option value="${cotizacion}">${cotizacion}</option>
							</c:if>
						</c:forEach>
					</select>
					<!-- <input type="TEXT" id="cotizacionSeleccionada" value="1"> -->
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