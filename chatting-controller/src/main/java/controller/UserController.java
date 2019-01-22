package controller;

import entity.Login;
import entity.Moment;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.RequestResultVO;
import repository.UserRepository;
import service.LoginService;
import service.UserService;
import utils.HttpResponseConstants;
import utils.JsonFastUtil;
import utils.ResultBuilder;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public void login() {

    }

    //获取好友列表
    @RequestMapping(value = "/findFriends.do")
    public @ResponseBody
    Object findFriends(HttpServletRequest request) {
        return userService.findFriends();
    }

    //好友关注
    @RequestMapping(value = "/friends.do")
    public @ResponseBody
    RequestResultVO friends(HttpServletRequest request) {
        Long friendId = Long.valueOf(request.getParameter("friendId"));
        return userService.friends(friendId);
    }

    //根据用户名查找用户
    @RequestMapping(value = "/findUserByName.do")
    public @ResponseBody
    Object findUserByName(HttpServletRequest request) {
        String name = request.getParameter("name");
        return userService.findUserByName(".*" + name + ".*");
//        return  userService.findUserByName(name);
    }

    @RequestMapping(value = "/getUser.do")
    public @ResponseBody
    Object getUser() {
        return userService.getUser();
    }

    //注册
    @RequestMapping(value = "/register.do")
    public @ResponseBody
    RequestResultVO register(HttpServletRequest request) {
        String userKey = request.getParameter("user");
        String loginKey = request.getParameter("login");
        User user;
        Login login;
        try {
            user = JsonFastUtil.parseObject(userKey, User.class);
            login = JsonFastUtil.parseObject(loginKey, Login.class);
            if (loginService.getLoginByLogName(login.getLogName()) != null) {
                return ResultBuilder.buildSuccessResult(HttpResponseConstants.Public.LOGNAME_EXIST, "");
            }
        } catch (Exception e) {
            throw new util.BizException(HttpResponseConstants.Public.ERROR_700);
        }
        user.getLogins().add(login);
        return userService.insert(user);
    }
}
