import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ShowUserInfo implements IPage {

    private User user ;

    public ShowUserInfo(User user) {
        this.user = user;
    }

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {



        String response = "<!DOCTYPE html> <html dir="+"\""+"rtl"+"\" "+ "lang="+"\""+"fa"+"\""+"> <head>";
        response += "<meta http-equiv="+"\""+"Content-Language"+"\""+" content="+"\""+"fa"+"\""+">";

        response += "<meta http-equiv="+"\""+"Content-Type"+"\""+" content="+"\""+"text/html"+"\""+"; charset="+"\""+"utf-8"+"\""+">" ;
        response += "</head> <body><h1>User Info </h1> <ul>" ;
        response += user.toHtml();
        response += "</ul>";
        response += "</body>"+ "</html>";

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
