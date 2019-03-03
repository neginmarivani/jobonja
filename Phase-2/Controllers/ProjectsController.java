
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProjectsController")
public class ProjectsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuctionDB adb = AuctionDB.getInstance();
	private Tools tools = Tools.getInstance();
	private ProjectsDB pdb = ProjectsDB.getInstance();
	private UsersDB udb = UsersDB.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		pdb.getProjectFromServer();
		ArrayList<Project> list = pdb.projectsForThisUser(udb.getCurrentUser());

		request.setAttribute("projectsList", list);
		request.getRequestDispatcher("ProjectsJstl.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
