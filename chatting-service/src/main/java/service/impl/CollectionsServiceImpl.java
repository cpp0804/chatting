package service.impl;

import entity.Moment;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.RequestResultVO;
import relationship.Collections;
import repository.CollectionRepository;
import repository.MomentRepository;
import service.CollectionsService;
import service.UserService;
import utils.HttpResponseConstants;
import utils.ResultBuilder;

import java.util.Date;

@Service
public class CollectionsServiceImpl implements CollectionsService {

    @Autowired
    UserService userService;

    @Autowired
    CollectionRepository collectionRepository;

    @Autowired
    MomentRepository momentRepository;

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

}
