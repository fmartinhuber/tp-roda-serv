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

	function generar(){
		alert('Solicitudes de compras generadas.');
		document.getElementById('SolicitudCompraServlet').submit();
		
	}

</script>

<form action="SolicitudCompraServlet" id="SolicitudCompraServlet" method="POST">
		<input type="hidden" name="listaRodamiento" id="listaRodamiento" value="" />
		<input type="hidden" name="metodo" id="metodo" value="">
		<table>
			<tr>
				<td align="center"><input type="submit" value="Generar" onClick="generar()"></td>
				<td align="center"><input type="reset" value="Cancelar"></td>
			</tr>
		</table>
	</form>

</body>
</html>