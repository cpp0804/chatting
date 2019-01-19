package service.impl;

import entity.Moment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.RequestResultVO;
import relationship.Comment;
import relationship.Post;
import repository.CommentRepository;
import repository.MomentRepository;
import repository.UserRepository;
import service.CommentService;
import service.UserService;
import utils.HttpResponseConstants;
import utils.ResultBuilder;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private MomentRepository momentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public RequestResultVO insert(Comment comment) {
        if (comment == null) {
            throw new util.BizException(HttpResponseConstants.Public.ERROR_700);
        }
        commentRepository.save(comment);
        return ResultBuilder.buildSuccessResult(HttpResponseConstants.Public.COMMENT_SUCCESS, "");
    }

    @Override
    public Comment createComment(String commentString, Long momentId) {
        Comment comment=new Comment();
        Moment moment=momentRepository.findById(momentId).get();
        comment.setMoment(moment);
        comment.setPostDate(new Date());
        comment.setUser(userService.getSessionUser());
        comment.setComment(commentString);
        return comment;
    }
}
