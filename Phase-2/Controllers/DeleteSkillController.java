
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteSkillController")
public class DeleteSkillController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Tools tools = Tools.getInstance();
	UsersDB udb = UsersDB.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		User u = udb.getUser(Integer.parseInt(id));
		String skillName = request.getParameter("skillName");
		u.deleteSkill(skillName);

		request.setAttribute("user", u);
		request.setAttribute("msg", "Skill deleted");
		request.setAttribute("jobonjaSkills", udb.getJobonjaSkills());
		request.getRequestDispatcher("LoggedInUser.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
