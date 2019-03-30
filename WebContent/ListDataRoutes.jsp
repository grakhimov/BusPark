<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.FileReader,java.io.IOException,java.io.PrintWriter,com.google.gson.*"%>
<%
	FileReader reader = new FileReader("C:\\Users\\George_Rakhimov\\eclipse-workspace\\BusPark\\data.json");
	JsonObject data = new Gson().fromJson(reader, JsonObject.class);
	JsonArray routes = data.getAsJsonObject("items").getAsJsonObject("routes").getAsJsonArray("route");
%>

<table border='1'>
	<tr>
		<td><b>Bus route number</b></td>
		<td><b>Route start time</b></td>
		<td><b>Route end time</b></td>
	</tr>
	<%
		for (int i = 0; i < routes.size(); i++) {
	%>
	<tr>
		<td><%=routes.get(i).getAsJsonObject().get("busRouteNumber")%></td>
		<td><%=routes.get(i).getAsJsonObject().get("startTime")%></td>
		<td><%=routes.get(i).getAsJsonObject().get("endTime")%></td>
		<%
			}
		%>
	</tr>
</table>