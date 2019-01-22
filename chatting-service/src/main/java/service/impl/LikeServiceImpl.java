package service.impl;

import entity.Moment;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.RequestResultVO;
import relationship.Collections;
import relationship.Like;
import repository.LikeRepository;
import repository.MomentRepository;
import service.LikeService;
import service.UserService;
import utils.DateJsonValueProcessor;
import utils.HttpResponseConstants;
import utils.ResultBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    UserService userService;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    MomentRepository momentRepository;

    @Override
    @Transactional
    public RequestResultVO likeOrCancel(Long momentId){

        //获取User
        User user = userService.getSessionUser();

        if (user == null || momentId == null) {
            throw new util.BizException(HttpResponseConstants.Public.ERROR_700);
        } else {

            // 若已经喜欢，则取消喜欢
            for (Like lk : user.getMomentsLike()) {
                if (lk.getMoment().getId() == momentId) {
                    likeRepository.delete(lk);
                    return ResultBuilder.buildSuccessResult(HttpResponseConstants.Public.CANCEL_LIKE_SUCCESS, "");
                }
            }

            // 若还没喜欢，则喜欢
            Moment moment = momentRepository.findById(momentId).get();

            Like like = new Like();
            like.setMoment(moment);
            like.setUser(user);
            like.setPostDate(new Date());

            likeRepository.save(like);
            return ResultBuilder.buildSuccessResult(HttpResponseConstants.Public.LIKE_SUCCESS, "");
        }
    }

    // 我的喜欢
    @Override
    public Map<String, Object> myLikes() {

        Long userId = userService.getSessionId();

        List<Moment> lks = likeRepository.myLikes(userId);

        Map<String, Object> map = new HashMap<>();
        JsonConfig config = new JsonConfig();
//        config.setExcludes(new String[]{"user"});
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        map.put("aaData", JSONArray.fromObject(lks, config));
        return map;
    }
}
