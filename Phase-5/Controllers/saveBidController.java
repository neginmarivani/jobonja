
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/saveBidController")
public class saveBidController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AuctionDB adb = AuctionDB.getInstance();
	private Tools tools = Tools.getInstance();
	private ProjectsDB pdb = ProjectsDB.getInstance();
	private UsersDB udb = UsersDB.getInstance();

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		pdb.getProjectFromServer();
		String projectId = req.getParameter("project");
		Project p = pdb.getProject(projectId, false);
		int amount = Integer.parseInt(req.getParameter("bidAmount"));

		boolean status = adb.UserHasBidForProject(p.getTitle(), udb.getCurrentUser());

		String msg = "";
		JSONObject jsonObject;
		String myJson;
		if (!status) {
			Bid bid = new Bid(p, udb.getCurrentUser(), amount);
			adb.addBid(p.getTitle(), bid);
			jsonObject = new JSONObject();
			jsonObject.put("msg", "پیشنهاد شما با موفقیت ثبت شد ");

			myJson = jsonObject.toString();
			resp.setStatus(200);

		} else {

			jsonObject = new JSONObject();
			jsonObject.put("msg", "شما قبلا پیشنهاد داده اید");
			myJson = jsonObject.toString();
			resp.setStatus(404);
		}

		resp.setContentType("application/json; charset=UTF-8;");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(myJson);

		// req.setAttribute("msg", "درخواست شما با موفقیت ذخیره شد !");
		// req.setAttribute("projectId", projectId);
		// req.getRequestDispatcher("successmsg.jsp").forward(req, resp);
	}

}
