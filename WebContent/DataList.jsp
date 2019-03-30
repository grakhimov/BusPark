<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/ErrorManager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bus park</title>
</head>
<body style="background: <%=request.getSession().getAttribute("userColor").toString()%>">
Welcome, <%=request.getSession().getAttribute("userName").toString()%>
<br>Total requests from you: <%=request.getSession().getAttribute("countLoad").toString()%>
<br>Last time request: <%=request.getSession().getAttribute("lastUpdateDate").toString()%>
<br>
	<%
		request.setCharacterEncoding("UTF-8");
		String dataToDisplay = request.getParameter("dataToDisplay");
		if (dataToDisplay == null)
			throw new IllegalArgumentException("Name expected");
	%>
	<h1>
		List of
		<%=dataToDisplay%></h1>
	<%
		if (dataToDisplay.equals("drivers")) {
	%>
	<jsp:include page="ListDataDrivers.jsp" flush="true" />
	<%
		} else if (dataToDisplay.equals("routes")) {
	%>
	<jsp:include page="ListDataRoutes.jsp" flush="true" />
	<%
		} else if (dataToDisplay.equals("buses")) {
	%>
	<jsp:include page="ListDataBuses.jsp" flush="true" />
	<%
		} else if (dataToDisplay.equals("violations")) {
	%>
	<jsp:include page="ListDataViolations.jsp" flush="true" />
	<%
		}
	%>
</body>
</html>