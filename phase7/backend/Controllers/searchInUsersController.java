
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

@WebServlet("/searchInUsersController")
public class searchInUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDB udb = UsersDB.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String query = request.getParameter("searchQuery");

		ArrayList<User> users = udb.searchUser(query);

		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(users, new TypeToken<ArrayList<User>>() {
		}.getType());

		JsonArray jsonArray = element.getAsJsonArray();

		String myJson = jsonArray.toString();
		response.setContentType("application/json; charset=UTF-8;");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(myJson);

	}

}
