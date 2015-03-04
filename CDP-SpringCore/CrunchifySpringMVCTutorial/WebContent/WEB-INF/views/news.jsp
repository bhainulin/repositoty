<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News Agency</title>
</head>
<body>
	<h2>Latest News</h2>
	<table width="100%">
		<tr>
			<td align="right"><a href="news/create" />Add News To The News
				Agency</a></td>
		</tr>
	</table>
	<c:if test="${!empty newsList}">
		<table class="data">
			<c:forEach items="${newsList}" var="news">
				<tr>
					<td colspan="2">${news.title},by${news.author}</td>
				</tr>
				<tr>
					<td><form:form method="GET" action="news/${news.id}">
							<input type="submit" value="VIEW" />
						</form:form></td>
					<td><form:form method="DELETE" action="news/${news.id}">
							<input type="submit" value="DELETE" />
						</form:form></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<table>
		<tr>
			<td><a href="news/create" />Add News</a></td>
		</tr>
	</table>
</body>
</html>