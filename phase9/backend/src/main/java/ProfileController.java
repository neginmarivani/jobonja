import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/main/profile")
public class ProfileController extends HttpServlet {

    private UsersMapper udb = UsersMapper.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentUserId = Integer.parseInt(request.getParameter("loggedInUser"));
        User u = udb.getUser(currentUserId);

        JSONObject jsonObject = new JSONObject(u);
        String myJson = jsonObject.toString();
        response.setContentType("application/json; charset=UTF-8;");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(myJson);

    }
}
