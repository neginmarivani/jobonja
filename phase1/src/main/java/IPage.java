import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IPage {
	public void HandleRequest(HttpExchange httpExchange) throws IOException;
}
