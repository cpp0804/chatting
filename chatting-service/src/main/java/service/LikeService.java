package service;

import pojo.RequestResultVO;

import java.util.Map;

public interface LikeService {

    RequestResultVO likeOrCancel(Long MomentId);

    Map<String, Object> myLikes();
}
