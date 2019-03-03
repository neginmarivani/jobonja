
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		ArrayList<Project> list = pdb.projectsForThisUser(udb.getCurrentUser());
		String id = request.getParameter("id");
		this.p = pdb.getProject(id, false);

		if (pdb.findIn(list, p)) {
			request.setAttribute("project", p);
			request.getRequestDispatcher("Project.jsp").forward(request, response);
		} else {
			// show error page
			request.setAttribute("errormsg", "Dont have permission to access this project! ");
			request.setAttribute("redirectUrl", "Home.jsp");
			request.getRequestDispatcher("ErrorJstl.jsp").forward(request, response);
		}

	}

}
