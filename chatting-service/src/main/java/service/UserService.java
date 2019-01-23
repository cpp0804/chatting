package service;

import entity.Login;
import entity.User;
import pojo.RequestResultVO;

import java.util.List;
import java.util.Map;

public interface UserService {

    User findUserByLogin(Long loginId);

    Map<String, Object> findFriends();

    Long getSessionId();

    User getSessionUser();

    RequestResultVO friends(Long friendsId);


    Map<String, Object> findUserByName(String name);

    Map<String, Object> getUser();

    RequestResultVO insert(User user);

    List<String> getAlbum();

    RequestResultVO edit(String user);

//    User createUser(String keys);
}
