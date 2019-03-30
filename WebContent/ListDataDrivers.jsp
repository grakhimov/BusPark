<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.FileReader,java.io.IOException,java.io.PrintWriter,com.google.gson.*"%>
<%
	FileReader reader = new FileReader("C:\\Users\\George_Rakhimov\\eclipse-workspace\\BusPark\\data.json");
	JsonObject data = new Gson().fromJson(reader, JsonObject.class);
	JsonArray drivers = data.getAsJsonObject("items").getAsJsonObject("drivers").getAsJsonArray("driver");
%>

<table border='1'>
	<tr>
		<td><b>Driver name</b></td>
		<td><b>Driver surname</b></td>
		<td><b>Driver class</b></td>
		<td><b>Driver experience</b></td>
	</tr>
	<%
		for (int i = 0; i < drivers.size(); i++) {
	%>
	<tr>
		<td><%=drivers.get(i).getAsJsonObject().get("name")%></td>
		<td><%=drivers.get(i).getAsJsonObject().get("surname")%></td>
		<td><%=drivers.get(i).getAsJsonObject().get("class")%></td>
		<td><%=drivers.get(i).getAsJsonObject().get("experience")%></td>
		<%
			}
		%>
	</tr>
</table>