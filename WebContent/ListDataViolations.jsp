<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.FileReader,java.io.IOException,java.io.PrintWriter,com.google.gson.*"%>
<%
	FileReader reader = new FileReader("C:\\Users\\George_Rakhimov\\eclipse-workspace\\BusPark\\data.json");
	JsonObject data = new Gson().fromJson(reader, JsonObject.class);
	JsonArray violations = data.getAsJsonObject("items").getAsJsonObject("violations").getAsJsonArray("violation");
%>

<table border='1'>
	<tr>
		<td><b>Violated driver</b></td>
		<td><b>Violated bus route number</b></td>
		<td><b>Violation date</b></td>
		<td><b>Description</b></td>
	</tr>
	<%
		for (int i = 0; i < violations.size(); i++) {
	%>
	<tr>
		<td><%=violations.get(i).getAsJsonObject().get("violatedDriver")%></td>
		<td><%=violations.get(i).getAsJsonObject().get("violatedRouteNumber")%></td>
		<td><%=violations.get(i).getAsJsonObject().get("violationDate")%></td>
		<td><%=violations.get(i).getAsJsonObject().get("violationDescription")%></td>
		<%
			}
		%>
	</tr>
</table>