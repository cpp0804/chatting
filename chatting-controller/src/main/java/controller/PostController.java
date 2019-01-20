package controller;

import entity.Moment;
import entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.RequestResultVO;

import relationship.Post;
import repository.UserRepository;
import service.MomentService;
import service.PostService;
import service.UserService;
import utils.HttpResponseConstants;
import utils.JsonFastUtil;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private MomentService momentService;


    //发布动态
    @RequestMapping(value = "/insert.do")
    public @ResponseBody
    RequestResultVO insert(HttpServletRequest request) {
        String description = request.getParameter("description");
        String pictureUrl = request.getParameter("pictures");
        Moment moment = momentService.createMoment(description, pictureUrl);
        Post post = postService.createPost(moment);
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

    //获取首页的动态列表
    //最近7天内
    @RequestMapping(value = "/getHomeMoment.do")
    public @ResponseBody
    Object getHomeMoment(HttpServletRequest request){
        return postService.getHomeMoment();
    }
}
