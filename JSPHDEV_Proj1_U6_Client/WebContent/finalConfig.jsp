<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="model.Automobile" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% Automobile auto = (Automobile) request.getSession().getAttribute("auto"); %>
<h3>Please review your selection:</h3>
<table border="1px">
<tr>
	<td><%= auto.getModel() %></td>
	<td>Base Price</td>
	<td><%= auto.getBaseprice() %></td>
</tr>
<%
	Iterator<String> iter = auto.getOpsetList().keySet().iterator();
	while(iter.hasNext()) {
		String opsetName = iter.next();
		String optSelected = request.getParameter(opsetName);
		auto.setOptionChoice(opsetName, optSelected);
%>
		<tr>
			<td><%= opsetName %></td>
			<td><%= auto.getOptionChoice(opsetName) %></td>
			<td><%= auto.getOptionChoicePrice(opsetName) %></td>
		</tr>
<%
	}
%>
<tr>
	<td><b>Total Cost</b></td>
	<td></td>
	<td><%= auto.getTotalPrice() %></td>
</tr>
</table>


</body>
</html>