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
<title>Autorization</title>
<link rel="stylesheet" href="pages/style.css">
</head>

<body>
	<div class="header">
		<div id="whois">
			<p class="title">
				<%=hello%>
		</div>
	</div>

	<div class="menu">
		<p class="title">Menu
		<p class="list">
			<a href=Main?page=autorization>Autorization</a>
		<p class="list">
			<a href=Main?page=index>Index</a>
		<p class="list">
			<a href=Main?page=registration>Registration</a>
		<div class="list">
			<form action="Main" method="post">
				<input type="submit" name="submit" value="LogOut" /> <input
					type="hidden" name="action" value="logout">
			</form>
		</div>
	</div>

	<div class="content">

		<!-- Autorization form -->

			<p class="title"> Content
			<fieldset>
				<legend>Autorization</legend>
				<form action="Main" method="post">
					<div>
						<label>Login: </label> <input type="text" name="login"/>
					</div>
					<div>
						<label>Pass:</label> <input type="password" name="password"/>
					</div>
					<div>
						<input type="submit" id="submit" name="submit" value="submit"/>
					</div>
					<p style="text-align: right;"><a href="Main?page=registration"> Registration </a>
					<input type="hidden" name="action" value="login">
				</form>
		
		<!-- Error message -->
		<%
			Object loginError = request.getAttribute("loginError");
			if (loginError != null) {
		%>
			<h1 id="error">Login Error</h1>
			<%
				}
			%>
		</fieldset>
	</div>
</body>
</html>
