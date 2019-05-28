
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

@WebServlet("/main/ProjectsController")
public class ProjectsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuctionMapper adb = AuctionMapper.getInstance();
	private Tools tools = Tools.getInstance();
	private ProjectsMapper pdb = ProjectsMapper.getInstance();
	private UsersMapper udb = UsersMapper.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentUserId = Integer.parseInt(request.getParameter("loggedInUser"));
		pdb.getProjectFromServer();
		String number = request.getParameter("number");

		ArrayList<Project> list = pdb.projectsForThisUser(udb.getUser(currentUserId));
	//	ArrayList<Project> list = pdb.getProjectsFromDB(Integer.parseInt(number));

		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<Project>>() {
		}.getType());

		JsonArray jsonArray = element.getAsJsonArray();

		String myJson = jsonArray.toString();
		response.setContentType("application/json; charset=UTF-8;");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(myJson);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
