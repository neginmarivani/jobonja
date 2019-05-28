
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import sun.security.util.SecurityConstants;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	private UsersMapper udb = UsersMapper.getInstance();

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String hashPass =udb.calHash(passWord);
		System.out.println(hashPass);
        if(udb.isUserAndPassCorrect(userName,hashPass)) {
			JSONObject jsonObject;
			String myJson;
			jsonObject = new JSONObject();
			jsonObject.put("msg", "login success");
			jsonObject.put("flag", "1");
			String JWT=udb.createJWT(userName);
			System.out.println("created JWT "+JWT);
			jsonObject.put("access_token",JWT);
			myJson = jsonObject.toString();
			response.setStatus(200);
			response.setContentType("application/json; charset=UTF-8;");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(myJson);

        }
        else{
            response= udb.setErrorMsg(response,"username or password is incorrect",403);
        }


	}
}
