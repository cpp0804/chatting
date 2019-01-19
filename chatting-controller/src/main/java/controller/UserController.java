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

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/login")
    public void login() {

    }

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

}
