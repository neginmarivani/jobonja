
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

@WebServlet("/users")
public class UsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Tools tools = Tools.getInstance();
	private UsersDB udb = UsersDB.getInstance();

	public UsersController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		ArrayList<User> users = udb.getUsers();

		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(users, new TypeToken<ArrayList<User>>() {
		}.getType());

		JsonArray jsonArray = element.getAsJsonArray();

		String myJson = jsonArray.toString();
		response.setContentType("application/json; charset=UTF-8;");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(myJson);

		// request.setAttribute("users", users);
		// request.getRequestDispatcher("Users.jsp").forward(request, response);
	}

}
