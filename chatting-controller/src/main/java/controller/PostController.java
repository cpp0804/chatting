package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.RequestResultVO;

import relationship.Post;
import service.PostService;
import utils.JsonFastUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    //发布动态
//    @RequestMapping(value = "/insert.do")
//    public @ResponseBody
//    RequestResultVO insert(HttpServletRequest request) {
//        String keys = request.getParameter("keys");
//        Post post = null;
//        try {
//            post= JsonFastUtil.parseObject(keys, Post.class);
//        } catch (Exception e) {
//            throw new BizException(Public.ERROR_700);
//        }
//        return comsumePermitService.insert(comsumePermit);
//    }

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
