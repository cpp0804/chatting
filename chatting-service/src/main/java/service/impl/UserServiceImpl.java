package service.impl;

import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.RequestResultVO;
import repository.UserRepository;
import service.UserService;
import utils.DateJsonValueProcessor;
import utils.HttpResponseConstants;
import utils.ResultBuilder;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Autowired(required = false)
    private HttpSession session;

    public Long getSessionId() {
        Long userId = (Long) session.getAttribute("userId");
        return userId;
//        return null;
    }

    @Override
    public User getSessionUser() {
        return userRepository.findById((Long) session.getAttribute("userId")).get();
//        return null;
    }


    @Override
    public RequestResultVO friends(Long friendsId) {
        if (friendsId == null) {
            throw new util.BizException(HttpResponseConstants.Public.ERROR_700);
        }
        User friend = userRepository.findById(friendsId).get();
        User user = getSessionUser();
        user.getFriends().add(friend);
        userRepository.save(user);
        return ResultBuilder.buildSuccessResult(HttpResponseConstants.Public.FRIENDS_SUCCESS, "");
    }

    public User findUserByLogin(Long loginId) {
        return userRepository.findUserByLogin(loginId);
    }

    public Map<String, Object> findFriends() {
        List<User> friends = userRepository.findFriends((Long) session.getAttribute("userId"));
//        List<User> friends=null;
        Map<String, Object> map = new HashMap<String, Object>();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"login", "momentsPost", "momentsLike", "momentsComment", "momentsCollection", "friends", "specialFriends"});
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        map.put("aaData", JSONArray.fromObject(friends, config));
        return map;
    }

    @Override
    public Map<String, Object> findUserByName(String name) {
        List<User> users = userRepository.findUserByName(name);
        Map<String, Object> map = new HashMap<String, Object>();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"login", "momentsPost", "momentsLike", "momentsComment", "momentsCollection", "friends", "specialFriends"});
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        map.put("aaData", JSONArray.fromObject(users, config));
        return map;
    }
}
