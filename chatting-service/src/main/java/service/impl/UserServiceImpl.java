package service.impl;

import entity.Login;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.RequestResultVO;
import repository.UserRepository;
import service.UserService;
import utils.HttpResponseConstants;
import utils.ResultBuilder;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private HttpSession session;

    public Long getSessionId() {
        Long userId = (Long) session.getAttribute("userId");
        return userId;
//        return null;
    }

    @Override
    public User getSessionUser() {
        return userRepository.findById((Long) session.getAttribute("userId")).get();
    }

    @Override
    public RequestResultVO friends(Long friendsId) {
        if (friendsId == null) {
            throw new util.BizException(HttpResponseConstants.Public.ERROR_700);
        }
        User friend=userRepository.findById(friendsId).get();
        User user=getSessionUser();
        user.getFriends().add(friend);
        userRepository.save(user);
        return ResultBuilder.buildSuccessResult(HttpResponseConstants.Public.FRIENDS_SUCCESS, "");
    }

    public User findUserByLogin(Long loginId) {
        return userRepository.findUserByLogin(loginId);
    }

    public List<User> findFriends() {
        return userRepository.findFriends((Long) session.getAttribute("userId"));
//        return null;
    }

}
