
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addBidController")
public class addBidController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AuctionDB adb = AuctionDB.getInstance();
	private ProjectsDB pdb = ProjectsDB.getInstance();
	private UsersDB udb = UsersDB.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		pdb.getProjectFromServer();
		String id = request.getParameter("projectId");
		Project p = pdb.getProject(id, false);

		if (!adb.UserHasBidForProject(p.getTitle(), udb.getCurrentUser())) {

			request.setAttribute("project", id);
			request.setAttribute("msg", adb.getBid(p.getTitle()));
			request.getRequestDispatcher("BidForm.jsp").forward(request, response);

		} else {
			request.setAttribute("errormsg", "شما قبلا درخواست داده اید ");
			request.setAttribute("redirectUrl", "Project.jsp");
			request.getRequestDispatcher("ErrorJstl.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
