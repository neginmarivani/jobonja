
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		User u = udb.getCurrentUser();
		udb.removeUser(u.getId());
		ArrayList<User> users = udb.getUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("Users.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
