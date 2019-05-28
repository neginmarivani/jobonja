import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/main/AuctionController")
public class AuctionController  extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private ProjectsMapper pdb = ProjectsMapper.getInstance();
        private UsersMapper udb = UsersMapper.getInstance();
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            System.out.println("salammmmmmmmmmmmmmmmmmm");
            String id = request.getParameter("id");
            int uId=pdb.getWonBidForProject(id);
            String name ;
            if(uId !=0) {
                User u = udb.getUser(uId);
                name = u.getUserName();
                name = name + " " + u.getUserFamilyName();
            }
            else{
                name ="doesnt have winner";
            }
            JSONObject jsonObject = new JSONObject(name);
            String myJson = jsonObject.toString();
            response.setContentType("application/json; charset=UTF-8;");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(myJson);

        }

}
