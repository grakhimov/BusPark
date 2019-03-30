<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.net.URLDecoder"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Please enter name and color</title>
</head>
<body>
	<form METHOD=GET action="WelcomePageProcessor">
		Please enter your name and your favorite color<br> Your name: <INPUT
			TYPE=TEXT NAME="name"
			<%
			Integer countLoad = 0;
	        try {
	        	countLoad = Integer.parseInt(request.getSession().getAttribute("countLoad").toString());
	        } catch (Exception e) {

	        } finally {
	            request.getSession().setAttribute("countLoad", countLoad + 1);
	            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            request.getSession().setAttribute("lastUpdateDate", df.format(new Date()));
	        }
	        Cookie[] c = request.getCookies();
			if (c != null)
				for (int i = 0; i < c.length; i++)
					if ("user.name".equals(c[i].getName())) {
						out.print(" value='" + URLDecoder.decode(c[i].getValue(), "UTF-8") + "' ");
						break;
					}%>>
		<br> Your favorite color: <INPUT TYPE=TEXT NAME="color"
			<%if (c != null)
				for (int i = 0; i < c.length; i++)
					if ("user.color".equals(c[i].getName())) {
						out.print(" value='" + URLDecoder.decode(c[i].getValue(), "UTF-8") + "' ");
						break;
					}%>>
		<br> Data to display: <INPUT TYPE=TEXT NAME="dataToDisplay"
			<%if (c != null)
				for (int i = 0; i < c.length; i++)
					if ("user.data".equals(c[i].getName())) {
						out.print(" value='" + URLDecoder.decode(c[i].getValue(), "UTF-8") + "' ");
						break;
					}%>>
		<br> <INPUT TYPE=SUBMIT value="Submit">
	</form>
</body>
</html>