package controller;

import entity.Moment;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.RequestResultVO;
import repository.UserRepository;
import service.UserService;
import utils.HttpResponseConstants;
import utils.JsonFastUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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

    @RequestMapping(value = "/getSessionUser.do")
    public @ResponseBody
    Object getSessionUser() {
        return userService.getSessionUser();
    }
}
