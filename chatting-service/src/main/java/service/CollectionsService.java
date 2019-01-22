package service;

import pojo.RequestResultVO;

import java.util.Map;

public interface CollectionsService {

    RequestResultVO collectOrCancel(Long MomentId);

    Map<String, Object> myCollections();
}
