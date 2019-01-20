package service;

import entity.Moment;
import javafx.geometry.Pos;
import pojo.RequestResultVO;
import relationship.Post;

import java.util.List;
import java.util.Map;

public interface PostService {


    Map<String, Object> getFriendsMoment();

    Map<String, Object> getMyMoment();

    RequestResultVO insert(Post post);

    Post createPost(Moment moment);

    Map<String, Object> getHomeMoment();
}
