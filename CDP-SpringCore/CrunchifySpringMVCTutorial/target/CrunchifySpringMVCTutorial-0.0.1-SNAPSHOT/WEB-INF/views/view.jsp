<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View News</title>
</head>
<body>

	<table class="data">
		<tr>
			<td colspan="2" align="right">
				<h3>"${newsView.title}"</h3>
			</td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2">"${newsView.content}"</td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td></td>
			<td align="right"><h3>By:</h3>"${newsView.author}"</td>
		</tr>
	</table>


</body>
</html>