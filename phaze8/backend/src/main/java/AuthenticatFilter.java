import io.jsonwebtoken.Jwts;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/main/*")
public class AuthenticatFilter implements Filter {


        private static final String AUTH_HEADER_KEY = "authorization";
        private static final String AUTH_HEADER_VALUE_PREFIX = "Bearer "; // with trailing space to separate token
        private UsersMapper udb = UsersMapper.getInstance();
        private static final int STATUS_CODE_UNAUTHORIZED = 401;

        @Override
        public void init( FilterConfig filterConfig ) throws ServletException {
            System.out.println( "JwtAuthenticationFilter initialized" );
        }

        @Override
        public void doFilter( final ServletRequest servletRequest,
                              final ServletResponse servletResponse,
                              final FilterChain filterChain ) throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

            Enumeration headerNames = httpRequest.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = httpRequest.getHeader(key);
                System.out.println(key +"   "+ value);
            }

            boolean loggedIn = false;
            try {

                //String jwt = getBearerToken( httpRequest );
                String jwt =httpRequest.getParameter("token").substring( AUTH_HEADER_VALUE_PREFIX.length() );

                if ( jwt != null ) {

                    loggedIn = true;
                    System.out.println( "Logged in using JWT" );

                    int loggedInUser = udb.getAuthentication(httpRequest ,jwt);
                    System.out.println("filterrrrrrrrrrr"+loggedInUser);
                    servletRequest.setAttribute("loggedInUser",loggedInUser);


                } else {
                    System.out.println( "No JWT provided, go on unauthenticated" );
                    ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
                }

                filterChain.doFilter( servletRequest, servletResponse );


                if ( loggedIn ) {
                    httpRequest.logout();
                    System.out.println( "Logged out" );
                }
            } catch ( final Exception e ) {

                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
                httpResponse.setContentLength( 0 );
                httpResponse.setStatus( STATUS_CODE_UNAUTHORIZED );
            }
        }


        @Override
        public void destroy() {
            System.out.println( "JwtAuthenticationFilter destroyed" );
        }

        private String getBearerToken( HttpServletRequest request ) {
            String authHeader = request.getHeader( AUTH_HEADER_KEY );
            if ( authHeader != null && authHeader.startsWith( AUTH_HEADER_VALUE_PREFIX ) ) {
                return authHeader.substring( AUTH_HEADER_VALUE_PREFIX.length() );
            }
            return null;
        }
    }

