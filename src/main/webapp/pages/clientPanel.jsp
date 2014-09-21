<%@page import="by.ittc.payments.model.CreditCard"%>
<%@page import="by.ittc.payments.model.persons.Client"%>
<%@page import="by.ittc.payments.model.Count"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% 
		Object object = request.getSession().getAttribute("person");
		Client client = (Client) object;
		List<CreditCard> cardList = new ArrayList<CreditCard>();
		List<Count> countList = new ArrayList<Count>();
		String hello = "";
		if (client!=null){
			cardList = client.getCreditCards();
		}
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
		<title>Client panel</title>
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
			
			<p class="list"> <a href=Main?page=cardStatus>Card status</a>
			<p class="list"> <a href=Main?page=clientPanel>Client panel</a>
			<p class="list"> <a href=Main?page=transaction>Transaction</a>	
			<div class="list">
				<form action="Main" method="post">
					<input type="submit" name="submit" value="LogOut">
					<input type="hidden" name="action" value="logout">
				</form>
			</div>			
		</div>
		<div class="content">
			<p class="title"> Content
			
			<!-- Information about cards -->
			<div class="info">
				<table>
					<!-- Header of table -->
					<tr id="tableHead">
						<td>
							<p>Card number
						</td>
						<td>
							<p>Value
						</td>
					
					
					</tr>
					
					<!-- Card info -->
					<% 
						for (int i = 0; i < cardList.size(); i++){
					%>
						<tr>
							<td>
								<%= cardList.get(i).getCardID() %>
							</td>
							<td>
								<%= countList.get(i).getValue() %>
							</td>
						</tr>	

						<% 
						}
					%>
				
				
	
				</table>

		</div>
		</div>
	</body>
</html>
