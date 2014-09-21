<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="by.ittc.payments.model.CreditCard"%>
<%@page import="by.ittc.payments.model.persons.Client"%>
<%@page import="by.ittc.payments.model.Count"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<% 
		Client client = (Client) request.getSession().getAttribute("person");
		List<CreditCard> cardList = client.getCreditCards();
		List<Count> countList = new ArrayList<Count>();
		String hello = "";
			for(CreditCard card : cardList){
				countList.add(card.getCount());
			}
			if (client!=null){
				hello = "Hello " + client.getFirstName() + " " + client.getLastName();
			}	
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Transaction</title>
<link rel="stylesheet" href="pages/style.css">
</head>

<body>
	<div class="header">

	

			<div id="whois">
				<p class="title"> <%= hello %>
			</div>

	</div>
		<div class="menu">
		<p class="title">Menu
		<p class="list">

			<a href=Main?page=autorization>Autorization</a>
		<p class="list">
			<a href=Main?page=card_status>Card status</a>
		<p class="list">
			<a href=Main?page=client_panel>Client panel</a>
		<p class="list">
			<a href=Main?page=index>Index</a>
		<p class="list">
			<a href=Main?page=registration>Registration</a>

			<a href=Main?page=cardStatus>Card status</a>
		<p class="list">

		<p class="list">
			<a href=Main?page=transaction>Transaction</a>
		<p class="list">
		<div class="list">
			<form action="Main" method="post">
				<input type="submit" name="submit" value="LogOut" /> <input
					type="hidden" name="action" value="logout">
			</form>
		</div>
	</div>
	
	<div class="content">
		<!-- Transaction -->
		<p class="title">Content
		<fieldset>
			<legend>Transaction</legend>
			<form action="Main" method="post">
				<label>Select card: </label> <select name="count_number">
					<% 
						for (int i = 0; i < cardList.size(); i++){		
					%>
					<option value="<%= countList.get(i).getCountID()%>">
						Count №
						<%= countList.get(i).getCountID() %></option>
					<% 
						}
					%>
				</select>
				<div>
					<label>Count №</label> <input type="text" name="count_number2" />
				</div>

				<div>
					<label>Summa</label> <input type="text" name="summa" />
				</div>
				<div>
					<input type="submit" id="submit" name="submit" value="submit" />
				</div>
				<input type="hidden" name="action" value="transaction" />
			</form>
		</fieldset>
	</div>
	
</body>
</html>
