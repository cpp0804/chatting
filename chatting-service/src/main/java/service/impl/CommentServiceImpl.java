package service.impl;

import entity.Moment;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.CommentVo;
import pojo.RequestResultVO;
import relationship.Comment;
import relationship.Post;
import repository.CommentRepository;
import repository.MomentRepository;
import repository.UserRepository;
import service.CommentService;
import service.UserService;
import utils.DateJsonValueProcessor;
import utils.HttpResponseConstants;
import utils.ResultBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        Comment comment = new Comment();
        Moment moment = momentRepository.findById(momentId).get();
        comment.setMoment(moment);
        comment.setPostDate(new Date());
        comment.setUser(userService.getSessionUser());
        comment.setComment(commentString);
        return comment;
    }

    @Override
    public Map<String, Object> findCommentByMoment(Long momentId) {
        List<Comment> comments = commentRepository.findAllByMoment(momentId);
        Map<String, Object> map = new HashMap<>();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"user", "moment"});
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        map.put("aaData", JSONArray.fromObject(createVos(comments), config));
        return map;
    }

    private List<CommentVo> createVos(List<Comment> comments) {
        List<CommentVo> commentVos = new ArrayList<>();
        CommentVo commentVo;
        for (Comment comment : comments) {
            commentVo = new CommentVo();
            BeanUtils.copyProperties(comment, commentVo);
            commentVo.setUserName(comment.getUser().getName());
            commentVo.setUserPortrait(comment.getUser().getPortrait());
            commentVos.add(commentVo);
        }
        return commentVos;
    }
}
