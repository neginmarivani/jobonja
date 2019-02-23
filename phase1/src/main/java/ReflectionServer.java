import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReflectionServer {
	private ProjectsDB db;
	private UsersDB udb;

	public ReflectionServer(ProjectsDB db,UsersDB udb) {
		this.db = db;
		this.udb = udb;
	}

	public void startServer() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    class MyHandler implements HttpHandler {
        @Override

        public void handle(HttpExchange httpExchange) throws IOException {
        	StringTokenizer tokenizer = new StringTokenizer(httpExchange.getRequestURI().getPath(), "/");
        	String page = tokenizer.nextToken();
			String id ="";

        	if(tokenizer.hasMoreTokens())
        		id =tokenizer.nextToken();

			ArrayList<Project> projects = db.projectsForThisUser(udb.getUser(1));

            Class<IPage> pageClass;
			try {
				if(page.startsWith("project")&& id.equals("")) {

					Class[] cArg = new Class[1];
					cArg[0] = ArrayList.class;

					pageClass = (Class<IPage>) Class.forName(page);
					IPage newInstance = pageClass.getDeclaredConstructor(cArg).newInstance(projects);
					newInstance.HandleRequest(httpExchange);
				}
				else if (page.startsWith("project") && !id.equals("")){
					Project p = db.getProject(id , false);
					if(db.findIn(projects , p)){
						IPage showProjectsInfo  = new ShowProjectsInfo(p);
						showProjectsInfo.HandleRequest(httpExchange);
					}
					else{
						IPage error = new ProjectAccessError("error 403.Do not have permission to access this project!" , 403);
						error.HandleRequest(httpExchange);
					}
				}
				else if(page.startsWith("user")){
					User user = udb.getUser(Integer.parseInt(id));
					IPage userInfo = new ShowUserInfo(user);
					userInfo.HandleRequest(httpExchange);
				}

			} catch (ClassNotFoundException | 
					InstantiationException |

					IllegalAccessException | 
					IllegalArgumentException | 
					InvocationTargetException | 
					NoSuchMethodException | 
					SecurityException e) {
				e.printStackTrace();
				IPage error = new ProjectAccessError("Page "+ page + " not 0found." , 403);
				error.HandleRequest(httpExchange);

			}
        }
    }

}
