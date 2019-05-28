
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/main/saveBidController")
public class saveBidController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AuctionMapper adb = AuctionMapper.getInstance();
	private Tools tools = Tools.getInstance();
	private ProjectsMapper pdb = ProjectsMapper.getInstance();
	private UsersMapper udb = UsersMapper.getInstance();

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String projectId = req.getParameter("project");
		int amount = Integer.parseInt(req.getParameter("bidAmount"));
		int userID= Integer.parseInt(req.getParameter("loggedInUser"));;
		boolean status = adb.UserHasBidForProject(projectId, userID);

		String msg = "";
		JSONObject jsonObject;
		String myJson;
		if (!status) {
			adb.addBid(projectId, userID, amount);
			jsonObject = new JSONObject();
			jsonObject.put("msg", "پیشنهاد شما با موفقیت ثبت شد  ");
			jsonObject.put("flag", "1");

			myJson = jsonObject.toString();
			resp.setStatus(200);

		} else {

			jsonObject = new JSONObject();
			jsonObject.put("msg", "شما قبلا پیشنهاد داده بودید ");
			jsonObject.put("flag", "0");
			myJson = jsonObject.toString();
			resp.setStatus(200);
		}

		resp.setContentType("application/json; charset=UTF-8;");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(myJson);


	}

}
