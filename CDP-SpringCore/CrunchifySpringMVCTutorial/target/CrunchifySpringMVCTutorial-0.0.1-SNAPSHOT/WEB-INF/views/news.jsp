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


	<h3>Latest News</h3>
	<c:if test="${!empty newsList}">
		<table class="data">
			<tr>
				<th>Title</th>
				<th>Author</th>
				<th>Added</th>
				<th>View</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${newsList}" var="news">
				<tr>
					<td>${news.title}</td>
					<td>${news.author}</td>
					<td>${news.created}</td>
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


</body>
</html>