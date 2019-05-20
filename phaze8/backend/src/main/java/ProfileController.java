import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main/profile")
public class ProfileController extends HttpServlet {

    private UsersMapper udb = UsersMapper.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jwt =request.getParameter("token").substring( "Bearer ".length() );

        int loggedInUser = udb.getAuthentication(request ,jwt);

        User u = udb.getUser(loggedInUser);
        JSONObject jsonObject = new JSONObject(u);
        String myJson = jsonObject.toString();
        response.setContentType("application/json; charset=UTF-8;");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(myJson);

    }
}
