
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EndorseController")
public class EndorseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Tools tools = Tools.getInstance();
	private UsersDB udb = UsersDB.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		User u = udb.getUser(Integer.parseInt(id));
		String skillName = request.getParameter("skillName");
		u.updateUserSkill(skillName);

		request.setAttribute("user", u);
		request.setAttribute("msg", "Endorsed Successfully");
		request.getRequestDispatcher("User.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
