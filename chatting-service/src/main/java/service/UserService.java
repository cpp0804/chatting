package service;

import entity.Login;
import entity.User;
import pojo.RequestResultVO;

import java.util.List;

public interface UserService {

    public User findUserByLogin(Long loginId);

    public List<User>findFriends();

    public Long getSessionId();

    User getSessionUser();

    RequestResultVO friends(Long friendsId);
}
