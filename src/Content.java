
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Content
 */
@WebServlet("/Content")
public class Content extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Content() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Задание типа кодировки для параметров запроса
		request.setCharacterEncoding("utf-8");
		// Чтение параметра name из запроса
		String dataToDisplay = request.getParameter("dataToDisplay");
		if (dataToDisplay == null) {
			return;
		}
		String lang = request.getParameter("lang");
		if (lang == null) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "'lang' is expected parameter");
			return;
		}
		if (!"en".equalsIgnoreCase(lang) && !"de".equalsIgnoreCase(lang)) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "'lang' parameter can be either 'en' or 'de'");
			return;
		}

		// Задание типа содержимого для ответа (в том числе кодировки)
		response.setContentType("text/html;charset=UTF-8");
		// Получение потока для вывода ответа
		PrintWriter out = response.getWriter();
		try {
			FileReader reader = new FileReader("C:\\Users\\George_Rakhimov\\eclipse-workspace\\BusPark\\data.json");
			JsonObject data = new Gson().fromJson(reader, JsonObject.class);
			ResourceBundle bundle = ResourceBundle.getBundle("Content", "de".equalsIgnoreCase(lang) ? Locale.GERMAN : Locale.getDefault());
			// Создание HTML-страницы
			out.println("<html>");
			out.println("<head><title>" + bundle.getString("header") + "</title></head>");
			out.println("<body>");
			out.println("<h1>" + bundle.getString("tableTitle") + dataToDisplay + "</h1>");
			out.println("<table border='1'>");
			if (dataToDisplay.equalsIgnoreCase("drivers")) {
				out.println("<tr><td><b>" + bundle.getString("driversTableColumn1") + "</b></td><td><b>"
						+ bundle.getString("driversTableColumn2") + "</b></td><td><b>"
						+ bundle.getString("driversTableColumn3") + "</b></td><td><b>"
						+ bundle.getString("driversTableColumn4") + "</b></td></tr>");
				JsonArray drivers = data.getAsJsonObject("items").getAsJsonObject("drivers").getAsJsonArray("driver");
				for (int i = 0; i < drivers.size(); i++) {
					out.println("<tr><td>" + drivers.get(i).getAsJsonObject().get("name") + "</td><td>"
							+ drivers.get(i).getAsJsonObject().get("surname") + "</td><td>"
							+ drivers.get(i).getAsJsonObject().get("class") + "</td><td>"
							+ drivers.get(i).getAsJsonObject().get("experience") + "</td></tr>");
				}
				out.println("</table>");
				reader.close();
			} else if (dataToDisplay.equalsIgnoreCase("routes")) {
				out.println("<tr><td><b>" + bundle.getString("routesTableColumn1") + "</b></td><td><b>"
						+ bundle.getString("routesTableColumn2") + "</b></td><td><b>"
						+ bundle.getString("routesTableColumn3") + "</b></td></tr>");
				JsonArray routes = data.getAsJsonObject("items").getAsJsonObject("routes").getAsJsonArray("route");
				for (int i = 0; i < routes.size(); i++) {
					out.println("<tr><td>" + routes.get(i).getAsJsonObject().get("busRouteNumber") + "</td><td>"
							+ routes.get(i).getAsJsonObject().get("startTime") + "</td><td>"
							+ routes.get(i).getAsJsonObject().get("endTime") + "</td></tr>");
				}
				out.println("</table>");
				reader.close();
			} else if (dataToDisplay.equalsIgnoreCase("buses")) {
				out.println("<tr><td><b>" + bundle.getString("busesTableColumn1") + "</b></td><td><b>"
						+ bundle.getString("busesTableColumn2") + "</b></td></tr>");
				JsonArray buses = data.getAsJsonObject("items").getAsJsonObject("buses").getAsJsonArray("bus");
				for (int i = 0; i < buses.size(); i++) {
					out.println("<tr><td>" + buses.get(i).getAsJsonObject().get("busRouteNumber") + "</td><td>"
							+ buses.get(i).getAsJsonObject().get("governmentNumber") + "</td></tr>");
				}
				out.println("</table>");
				reader.close();
			} else if (dataToDisplay.equalsIgnoreCase("violations")) {
				out.println("<tr><td><b>" + bundle.getString("violationsTableColumn1") + "</b></td><td><b>"
						+ bundle.getString("violationsTableColumn2") + "</b></td><td><b>"
						+ bundle.getString("violationsTableColumn3") + "</b></td><td><b>"
						+ bundle.getString("violationsTableColumn4") + "</b></td></tr>");
				JsonArray violations = data.getAsJsonObject("items").getAsJsonObject("violations")
						.getAsJsonArray("violation");
				for (int i = 0; i < violations.size(); i++) {
					out.println("<tr><td>" + violations.get(i).getAsJsonObject().get("violatedDriver") + "</td><td>"
							+ violations.get(i).getAsJsonObject().get("violatedRouteNumber") + "</td><td>"
							+ violations.get(i).getAsJsonObject().get("violationDate") + "</td><td>"
							+ violations.get(i).getAsJsonObject().get("violationDescription") + "</td></tr>");
				}
				out.println("</table>");
				reader.close();
			} else {
				out.println("<h2>You specified wrong type (" + dataToDisplay + ") to display</h2>");
			}
			out.println("</body>");
			out.println("</html>");
		} finally {
			// Закрытие потока вывода
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
