import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WelcomePageProcessor")
public class WelcomePageProcessor extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public WelcomePageProcessor() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("name");
		String userColor = request.getParameter("color");
		String dataToDisplay = request.getParameter("dataToDisplay");
		if (userName == null || userColor == null || dataToDisplay == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Some params are missed");
			return;
		}
		request.getSession().setAttribute("userName", userName);
		request.getSession().setAttribute("userColor", userColor);
		request.getSession().setAttribute("dataToDisplay", dataToDisplay);
		Cookie c = new Cookie("user.name", URLEncoder.encode(userName, "UTF-8"));
		Cookie d = new Cookie("user.color", URLEncoder.encode(userColor, "UTF-8"));
		Cookie e = new Cookie("user.data", URLEncoder.encode(dataToDisplay, "UTF-8"));
		c.setMaxAge(3600);
		d.setMaxAge(3600);
		e.setMaxAge(3600);
		response.addCookie(c);
		response.addCookie(d);
		response.addCookie(e);
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+ "/DataList.jsp?dataToDisplay=" + dataToDisplay));
		return;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
