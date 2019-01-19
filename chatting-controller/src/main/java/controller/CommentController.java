package controller;

import entity.Moment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.RequestResultVO;
import relationship.Comment;

import service.CommentService;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //添加评论
    @RequestMapping(value = "/insert.do")
    public @ResponseBody
    RequestResultVO insert(HttpServletRequest request) {
        String commentString = request.getParameter("comment");
        Long momentId= Long.valueOf(request.getParameter("momentId"));
        Comment comment=commentService.createComment(commentString,momentId);
        return commentService.insert(comment);
    }
}
