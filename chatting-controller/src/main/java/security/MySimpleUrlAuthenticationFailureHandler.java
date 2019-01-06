//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class MySimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    public MySimpleUrlAuthenticationFailureHandler() {
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String username = request.getParameter("username");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", false);
        jsonObject.put("msg", exception.getMessage());
        printWriter.print(jsonObject);
        printWriter.flush();
        printWriter.close();
    }
}
