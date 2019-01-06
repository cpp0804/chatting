//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public MyAuthenticationFilter() {
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            response.setContentType("text/html;charset=utf-8");
            String username = this.obtainUsername(request);
            String password = this.obtainPassword(request);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            this.setDetails(request, authenticationToken);
            return this.getAuthenticationManager().authenticate(authenticationToken);
        }
    }

    protected String obtainPassword(HttpServletRequest request) {
        Object password = request.getParameter("password");
        return password == null ? "" : password.toString();
    }

    protected String obtainUsername(HttpServletRequest request) {
        Object username = request.getParameter("username");
        return username == null ? "" : username.toString();
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        super.setDetails(request, authRequest);
    }
}
