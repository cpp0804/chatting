package service;

import javafx.geometry.Pos;
import pojo.RequestResultVO;
import relationship.Post;

import java.util.List;
import java.util.Map;

public interface PostService {


    public Map<String,Object> getFriendsMoment();

    public Map<String,Object> getMyMoment();

    public RequestResultVO insert(Post post);
}
