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
import repository.CollectionRepository;
import repository.MomentRepository;
import service.CollectionsService;
import service.UserService;
import utils.DateJsonValueProcessor;
import utils.HttpResponseConstants;
import utils.ResultBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectionsServiceImpl implements CollectionsService {

    @Autowired
    UserService userService;

    @Autowired
    CollectionRepository collectionRepository;

    @Autowired
    MomentRepository momentRepository;

    // 收藏、取消收藏
    @Override
    @Transactional
    public RequestResultVO collectOrCancel(Long momentId){

        //获取User
        User user = userService.getSessionUser();

        if (user == null || momentId == null) {
            throw new util.BizException(HttpResponseConstants.Public.ERROR_700);
        } else {

            // 若已经收藏，则取消收藏
            for (Collections col : user.getMomentsCollection()) {
                if (col.getMoment().getId() == momentId) {
                    collectionRepository.delete(col);
                    return ResultBuilder.buildSuccessResult(HttpResponseConstants.Public.CANCEL_COLLECT_SUCCESS, "");
                }
            }

            // 若还没收藏，则收藏
            Moment moment = momentRepository.findById(momentId).get();

            Collections collections = new Collections();
            collections.setUser(user);
            collections.setMoment(moment);
            collections.setPostDate(new Date());

            collectionRepository.save(collections);
            return ResultBuilder.buildSuccessResult(HttpResponseConstants.Public.COLLECT_SUCCESS, "");
        }
    }

    //我的收藏
    @Override
    public Map<String, Object> myCollections() {

        Long userId = userService.getSessionId();

        List<Moment> cols = collectionRepository.myCollections(userId);

        Map<String, Object> map = new HashMap<>();
        JsonConfig config = new JsonConfig();
//        config.setExcludes(new String[]{"user", "momentsCollection",});
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        map.put("aaData", JSONArray.fromObject(cols, config));
        return map;

    }


}
