
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/project")
public class ProjectController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AuctionDB adb = AuctionDB.getInstance();
	private Tools tools = Tools.getInstance();
	private ProjectsDB pdb = ProjectsDB.getInstance();
	private UsersDB udb = UsersDB.getInstance();

	private Project p;

	public ProjectController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		pdb.getProjectFromServer();
		// ArrayList<Project> list = pdb.projectsForThisUser(udb.getUser(1));
		String id = request.getParameter("id");
		this.p = pdb.getProject(id, false);
		JSONObject jsonObject = new JSONObject(p);
		String myJson = jsonObject.toString();
		response.setContentType("application/json; charset=UTF-8;");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(myJson);

		// if (pdb.findIn(list, p)) {
		// request.setAttribute("project", p);
		// request.getRequestDispatcher("Project.jsp").forward(request, response);
		// } else {
		// // show error page
		// request.setAttribute("errormsg", "Dont have permission to access this
		// project! ");
		// request.setAttribute("redirectUrl", "Home.jsp");
		// request.getRequestDispatcher("ErrorJstl.jsp").forward(request, response);
		// }

	}

}
