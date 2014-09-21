<%@page import="by.ittc.payments.model.CreditCard"%>
<%@page import="by.ittc.payments.model.persons.Client"%>
<%@page import="by.ittc.payments.model.persons.AbstractPerson"%>
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
		List<String> statusList = new ArrayList<String>();
		String hello = "";
		if (client!=null){
			cardList = client.getCreditCards();
		}
		for(CreditCard card : cardList){
			countList.add(card.getCount());
			if(card.getStatus()){
				statusList.add("<p class=\"status active\">Active");
			} else {
				statusList.add("<p class=\"status deactivated\">Deactivated");
			}
		}
		if (client!=null){
			hello = "Hello " + client.getFirstName() + " " + client.getLastName();
		}
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Card status</title>
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
	
			<!-- Card 1 -->
			<%
				for (int i = 0; i < cardList.size(); i++) {
			%>
				<fieldset>
					<legend>Card №: <%=cardList.get(i).getCardID()%> </legend>
						<div>
							<label>Card status: </label>
							<%=statusList.get(i)%>
						</div>
						<div>
							<label>Cange status: </label>
							<form action="Main" method="post">
								<input id="cardStatus" type="submit" name="submit" value="Activate"> 
								<input type="hidden" name="action" value="activate">
								<input type="hidden" name="cardId" value="<%=cardList.get(i).getCardID()%>">
							</form>
							<form action="Main" method="post">
								<input id="cardStatus" type="submit" name="submit" value="Diactivate"> 
								<input type="hidden" name="action" value="diactivate">
								<input type="hidden" name="cardId" value="<%=cardList.get(i).getCardID()%>">
							</form>
						</div>
				</fieldset>
			<%
				}
			%>
			
		</div>
	</body>
</html>
