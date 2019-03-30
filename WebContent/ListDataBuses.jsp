<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.FileReader,java.io.IOException,java.io.PrintWriter,com.google.gson.*"%>
<%
	FileReader reader = new FileReader("C:\\Users\\George_Rakhimov\\eclipse-workspace\\BusPark\\data.json");
	JsonObject data = new Gson().fromJson(reader, JsonObject.class);
	JsonArray buses = data.getAsJsonObject("items").getAsJsonObject("buses").getAsJsonArray("bus");
%>

<table border='1'>
	<tr>
		<td><b>Bus route number</b></td>
		<td><b>Government number</b></td>
	</tr>
	<%
		for (int i = 0; i < buses.size(); i++) {
	%>
	<tr>
		<td><%=buses.get(i).getAsJsonObject().get("busRouteNumber")%></td>
		<td><%=buses.get(i).getAsJsonObject().get("governmentNumber")%></td>
		<%
			}
		%>
	</tr>
</table>