
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/AddSkillController")
public class AddSkillController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Tools tools = Tools.getInstance();
	UsersMapper udb = UsersMapper.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// String id = request.getParameter("idofUserToaddSkill");
		int currentUserId = 1;
		String skillName = request.getParameter("nameOfwantedSkill");
		System.out.println(skillName);
		boolean status = udb.addSkill(skillName, currentUserId);
		JSONObject jsonObject;
		String myJson;
		if (status) {

			jsonObject = new JSONObject();
			jsonObject.put("msg", "skill added successfully");
			myJson = jsonObject.toString();
			response.setStatus(200);

		} else {

			jsonObject = new JSONObject();
			jsonObject.put("msg", "skill add was Unsuccessfully");
			myJson = jsonObject.toString();
			response.setStatus(404);
		}
		response.setContentType("application/json; charset=UTF-8;");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(myJson);
		// request.setAttribute("user", u);
		// request.setAttribute("msg", "Skill added");
		// request.setAttribute("jobonjaSkills", udb.getJobonjaSkills());
		// request.getRequestDispatcher("LoggedInUser.jsp").forward(request, response);
	}

}
