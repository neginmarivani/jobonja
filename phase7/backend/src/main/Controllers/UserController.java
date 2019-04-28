
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsersDB udb = UsersDB.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		User u = udb.getUser(Integer.parseInt(id));

		JSONObject jsonObject = new JSONObject(u);
		String myJson = jsonObject.toString();
		response.setContentType("application/json; charset=UTF-8;");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(myJson);

		// if (Integer.parseInt(id) == udb.getCurrentUser().getId()) {
		// request.setAttribute("user", u);
		// request.setAttribute("jobonjaSkills", udb.getJobonjaSkills());
		// request.getRequestDispatcher("LoggedInUser.jsp").forward(request, response);
		//
		// } else {
		// request.setAttribute("user", u);
		// request.getRequestDispatcher("User.jsp").forward(request, response);
		// }
	}

}
