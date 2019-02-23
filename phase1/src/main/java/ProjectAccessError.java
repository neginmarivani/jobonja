import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ProjectAccessError implements IPage {

    String message ;
    int status ;

    public ProjectAccessError(String message , int status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {

        String response =
                "<html>"
                        + "<body> "+  message+ " </body>"
                        + "</html>";
        httpExchange.sendResponseHeaders(status, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}

