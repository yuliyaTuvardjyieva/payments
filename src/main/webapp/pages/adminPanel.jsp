<%@page import="by.ittc.payments.model.persons.AbstractPerson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    Object object = request.getSession().getAttribute("person");
    AbstractPerson person = (AbstractPerson) object;
    String hello = "";
    if (person != null) {
        hello = "Hello " + person.getFirstName() + " " + person.getLastName();
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin panel</title>
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
		<div class="list">
			<form action="Main" method="post">
				<input type="submit" name="submit" value="LogOut"> <input
					type="hidden" name="action" value="logout">
			</form>
		</div>
	</div>
	<div class="content">
		<p class="title">
			Content



			<!----------------------------------Find field------------------------------------>
		<div class="info">
			<form action="Main" method="post">
				<table>
					<!-- Header of table -->
					<tr id="tableHead">
						<td>
							<p>User ID
						</td>
						<td>
							<p>Card ID
						</td>
						<td>
							<p>Count ID
						</td>
						<td>
							<p>First Name
						</td>
						<td>
							<p>Last Name
						</td>
					</tr>
					<tr id="tableHead">
						<td><input class="find" type="text" name="userId" /></td>
						<td><input class="find" type="text" name="cardId" /></td>
						<td><input class="find" type="text" name="countId" /></td>
						<td><input class="find" type="text" name="firstName" /></td>
						<td><input class="find" type="text" name="lastName" /></td>
					</tr>
				</table>
				<div>
					<input type="submit" name="submit" value="submit" /> <input
						type="hidden" name="action" value="find" />
				</div>
			</form>
		</div>


		<!---------------------------Information about cards------------------------------>
		<div class="info">
			<table>
				<!-- Header of table -->
				<tr id="tableHead">
					<td>
						<p>User ID
					</td>
					<td>
						<p>Card ID
					</td>
					<td>
						<p>Count ID
					</td>
					<td>
						<p>First Name
					</td>
					<td>
						<p>Last Name
					</td>
				</tr>

				<!-- Card info -->
				<%
				    for (int i = 0; i < 5; i++) { //TODO: Change here
				%>
				<tr>
					<td><%=1%></td>
					<!-- TODO: Change here -->
					<td><%=2%></td>
					<!-- TODO: Change here -->
					<td><%=3%></td>
					<!-- TODO: Change here -->
					<td><%=4%></td>
					<!-- TODO: Change here -->
					<td><%=5%></td>
					<!-- TODO: Change here -->
				</tr>
				<%
				    }
				%>

			</table>
		</div>

	</div>
</body>
</html>
