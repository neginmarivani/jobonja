
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/DeleteSkillController")
public class DeleteSkillController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Tools tools = Tools.getInstance();
	UsersDB udb = UsersDB.getInstance();

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		User u = udb.getUser(Integer.parseInt(id));
		String skillName = req.getParameter("skillName");
		boolean status = u.deleteSkill(skillName);

		JSONObject jsonObject;
		String myJson;
		if (status) {
			jsonObject = new JSONObject();
			jsonObject.put("msg", "skill deleted");
			myJson = jsonObject.toString();
			resp.setStatus(200);

		} else {

			jsonObject = new JSONObject();
			jsonObject.put("msg", "cant delete skill");
			myJson = jsonObject.toString();
			resp.setStatus(404);
		}
		resp.setContentType("application/json; charset=UTF-8;");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(myJson);

		// req.setAttribute("user", u);
		// req.setAttribute("msg", "Skill deleted");
		// req.setAttribute("jobonjaSkills", udb.getJobonjaSkills());
		// req.getRequestDispatcher("LoggedInUser.jsp").forward(req, resp);

	}

}
