
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
		ArrayList<Project> list = pdb.projectsForThisUser(udb.getUser(1));

		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<User>>() {
		}.getType());

		JsonArray jsonArray = element.getAsJsonArray();

		String myJson = jsonArray.toString();
		response.setContentType("application/json; charset=UTF-8;");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(myJson);

		// request.setAttribute("projectsList", list);
		// request.getRequestDispatcher("ProjectsJstl.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
