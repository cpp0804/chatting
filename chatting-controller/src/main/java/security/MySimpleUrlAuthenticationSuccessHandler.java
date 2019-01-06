//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package security;

import entity.Login;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import service.LoginService;

public class MySimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private LoginService loginService;

    public MySimpleUrlAuthenticationSuccessHandler() {
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        AuthencationResult authencationResult = new AuthencationResult();
        this.setAuthencationResult(request, authentication, authencationResult);
        out.print(JSONObject.fromObject(authencationResult));
        out.flush();
        out.close();
    }

    private void setAuthencationResult(HttpServletRequest request, Authentication authentication, AuthencationResult authencationResult) {
        Login login = this.loginService.getLoginByLogName(authentication.getName());
        User user = login.getUser();
        authencationResult.setUser(user);
        authencationResult.setLogName(authentication.getName());
        authencationResult.setStatus(true);
        authencationResult.setMsg("登录验证成功");
        request.getSession().setAttribute("user", user);
    }
}
