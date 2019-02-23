import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ShowProjectsInfo implements IPage{

    private Project project;


    public ShowProjectsInfo(Project p) {
        this.project = p;
    }

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {
        String response = "<!DOCTYPE html> <html dir="+"\""+"rtl"+"\" "+ "lang="+"\""+"fa"+"\""+"> <head>";
        response += "<meta http-equiv="+"\""+"Content-Language"+"\""+" content="+"\""+"fa"+"\""+">";

        response += "<meta http-equiv="+"\""+"Content-Type"+"\""+" content="+"\""+"text/html"+"\""+"; charset="+"\""+"utf-8"+"\""+">" ;
        response += "</head> <body><h1>Projects </h1> <ul>" ;
        response += project.toHtml();

        response += "</ul>";
        response += "</body>"+ "</html>";

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
