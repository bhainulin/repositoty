<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit News</title>
</head>
<body>

<form:form method="PUT" action="/CrunchifySpringMVCTutorial/news" commandName="news">
	<form:hidden path="id" value="${news.id}"/>
	<table>
		<tr>
			<td>Title</td>
			<td><form:input path="title" size="100" value="${news.title}"/></td>
		</tr>
		<tr>
			<td>Content</td>
			<td><form:textarea path="content" value="${news.content}" rows="10" cols="100" /></td>
		</tr>
		<tr>
			<td>Author</td>
			<td><form:input path="author" value="${news.author}" size="45"/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				value="SAVE" /></td>
		</tr>
	</table>
</form:form>
</body>
</html>