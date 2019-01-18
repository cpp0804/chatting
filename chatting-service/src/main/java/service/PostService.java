package service;

import relationship.Post;

import java.util.List;
import java.util.Map;

public interface PostService {

    List<Post> findPostByUser(Long userId);


    public Map<String,Object> getFriendsMoment();

    public Map<String,Object> getMyMoment();
}
