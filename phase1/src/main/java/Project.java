import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class project implements IPage {

   private ArrayList<Project> projects;

    public project(ArrayList<Project> p) {
        this.projects = p;
    }

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {



        String response = "<!DOCTYPE html> <html dir="+"\""+"rtl"+"\" "+ "lang="+"\""+"fa"+"\""+"> <head>";
        response += "<meta http-equiv="+"\""+"Content-Language"+"\""+" content="+"\""+"fa"+"\""+">";

        response += "<meta http-equiv="+"\""+"Content-Type"+"\""+" content="+"\""+"text/html"+"\""+"; charset="+"\""+"utf-8"+"\""+">" ;
        response += "</head> <body><h1>Projects </h1> <ul>" ;
        for (int i = 0; i < projects.size(); i++) {
            response += projects.get(i).toHtml();

        }
        response += "</ul>";
        response += "</body>"+ "</html>";

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}