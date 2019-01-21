package service;

import pojo.RequestResultVO;

public interface LikeService {
    RequestResultVO likeOrCancel(Long MomentId);
}
