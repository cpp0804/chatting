package controller;

import entity.Moment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.RequestResultVO;

import relationship.Post;
import repository.UserRepository;
import service.PostService;
import service.UserService;
import utils.HttpResponseConstants;
import utils.JsonFastUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    //发布动态
    @RequestMapping(value = "/insert.do")
    public @ResponseBody
    RequestResultVO insert(HttpServletRequest request) {
        String keys = request.getParameter("keys");
        Moment moment = null;
        Post post=new Post();
        post.setPostDate(new Date());
        post.setUser(userRepository.findById(userService.getSessionId()).get());
        try {
            moment = JsonFastUtil.parseObject(keys, Moment.class);
        } catch (Exception e) {
            throw new util.BizException(HttpResponseConstants.Public.ERROR_700);
        }
        post.setMoment(moment);
        return postService.insert(post);
    }

    //获取好友动态列表
    @RequestMapping(value = "/getFriendsMoment.do")
    public @ResponseBody
    Object getFriendsMoment(HttpServletRequest request) {
        return postService.getFriendsMoment();
    }

    //获取自己的动态列表
    @RequestMapping(value = "/getMyMoment.do")
    public @ResponseBody
    Object getMyMoment(HttpServletRequest request) {
        return postService.getMyMoment();
    }
}
