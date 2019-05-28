
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/register")
public class registerController extends HttpServlet {
//	private static final long serialVersionUID = 1L;

	private UsersMapper udb = UsersMapper.getInstance();

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String family = request.getParameter("family");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String jobTitle = request.getParameter("jobTitle");
		String imageUrl = request.getParameter("imageUrl");
		String bio = request.getParameter("bio");
		String myJson;
		JSONObject jsonObject;
		if(udb.isRepeatedUserName(userName)){


			jsonObject = new JSONObject();
			jsonObject.put("msg", "please try another userName");
			jsonObject.put("flag", "0");
			myJson = jsonObject.toString();
			response.setStatus(200);

		}
		else {
			int lastId = udb.getLastId();
			String hash=udb.calHash(passWord);
			udb.addLogedInInfoToDB(userName,hash);
			User u = new User(lastId + 1, bio, jobTitle, name, family, null, imageUrl);
			udb.addUserToDB(u,userName);
			jsonObject = new JSONObject();
			jsonObject.put("msg", "login success");
			jsonObject.put("flag", "1");
			String JWT=udb.createJWT(userName);
			jsonObject.put("access_token",JWT);
			myJson = jsonObject.toString();
			response.setStatus(200);


		}

		response.setContentType("application/json; charset=UTF-8;");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(myJson);
	}
}
