
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

@WebServlet("/SearchInProjectsController")
public class SearchInProjectsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectsMapper pdb = ProjectsMapper.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String query = request.getParameter("searchQuery");

		ArrayList<Project> projects = pdb.searchProject(query);

		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(projects, new TypeToken<ArrayList<Project>>() {
		}.getType());

		JsonArray jsonArray = element.getAsJsonArray();

		String myJson = jsonArray.toString();
		response.setContentType("application/json; charset=UTF-8;");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(myJson);
	}

}
