package service.impl;

import entity.Login;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


//    @Autowired
//    private HttpSession session;

    public Long getSessionId() {
//        Long userId = (Long) session.getAttribute("userId");
//        return userId;
        return null;
    }

    public User findUserByLogin(Long loginId) {
        return userRepository.findUserByLogin(loginId);
    }

    public List<User> findFriends() {
        return userRepository.findFriends(getSessionId());
    }
}
