<%@page import="by.ittc.payments.model.persons.AbstractPerson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% 
		Object object = request.getSession().getAttribute("person");
		AbstractPerson person = (AbstractPerson) object;
		String hello = "";
		if (person!=null){
			hello = "Hello " + person.getFirstName() + " " + person.getLastName();
		}
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Payments project</title>
		<link rel="stylesheet" href="pages/style.css">
	</head>

	<body>
		<div class="header">
			<div id="whois">
				<p class="title"> <%= hello %>
			</div>
		</div>

		<div class="menu">
			<p class="title"> Menu
			
			<p class="list"> <a href=Main?page=autorization>Autorization</a>
			<p class="list"> <a href=Main?page=index>Index</a>
			<p class="list"> <a href=Main?page=registration>Registration</a>
		
		
		</div>
		<div class="content">
			<p class="title"> Content
			<div class="info">Information 1</div>
			<div class="info">Information 2</div>
			<div class="info">Information 3</div>
			<div class="info">Information 4</div>
			<div class="info">Information 5</div>
		</div>
	</body>
</html>