package service;

import pojo.RequestResultVO;
import relationship.Comment;
import relationship.Post;


public interface CommentService {

    public RequestResultVO insert(Comment comment);

    Comment createComment(String commentString ,Long momentId);
}
