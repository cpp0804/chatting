package service;

import pojo.RequestResultVO;
import relationship.Comment;
import relationship.Post;

import java.util.Map;


public interface CommentService {

    public RequestResultVO insert(Comment comment);

    Comment createComment(String commentString ,Long momentId);

    Map<String,Object>findCommentByMoment(Long momentId);
}
