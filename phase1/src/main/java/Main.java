
import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class Main {
    private static AuctionDB auctionDB = new AuctionDB();
    private static  Tools tools = new Tools();
    private static ProjectsDB projectsDB = new ProjectsDB(auctionDB , tools);
    private  static  UsersDB usersDB = new UsersDB(tools);

    public static void  main(String args[])throws Exception {
        projectsDB.getProjectFromServer();
        usersDB.getSkillsFromServer();


        ReflectionServer server = new ReflectionServer(projectsDB,usersDB);
        server.startServer();

    }

}
