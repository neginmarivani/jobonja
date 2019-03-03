
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saveBidController")
public class saveBidController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AuctionDB adb = AuctionDB.getInstance();
	private Tools tools = Tools.getInstance();
	private ProjectsDB pdb = ProjectsDB.getInstance();
	private UsersDB udb = UsersDB.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		pdb.getProjectFromServer();
		String projectId = request.getParameter("project");
		Project p = pdb.getProject(projectId, false);
		int amount = Integer.parseInt(request.getParameter("bidAmount"));
		Bid bid = new Bid(p, udb.getCurrentUser(), amount);
		adb.addBid(p.getTitle(), bid);

		request.setAttribute("msg", "درخواست شما با موفقیت ذخیره شد !");
		request.setAttribute("projectId", projectId);
		request.getRequestDispatcher("successmsg.jsp").forward(request, response);

	}

}
