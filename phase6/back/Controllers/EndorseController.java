
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/EndorseController")
public class EndorseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Tools tools = Tools.getInstance();
	private UsersDB udb = UsersDB.getInstance();

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		User u = udb.getUser(Integer.parseInt(id));
		String skillName = req.getParameter("skillName");
		boolean status = u.updateUserSkill(skillName, udb.getCurrentUser().getId());

		String msg = "";
		JSONObject jsonObject;
		String myJson;
		if (status) {
			jsonObject = new JSONObject();
			jsonObject.put("msg", "Endorsed successfully");
			myJson = jsonObject.toString();
			resp.setStatus(200);

		} else {

			jsonObject = new JSONObject();
			jsonObject.put("msg", "Endorsed unsuccessfully");
			myJson = jsonObject.toString();
			resp.setStatus(404);
		}
		resp.setContentType("application/json; charset=UTF-8;");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(myJson);

		// request.setAttribute("user", u);
		// request.setAttribute("msg", "Endorsed Successfully");
		// request.getRequestDispatcher("User.jsp").forward(request, response);

	}

}
