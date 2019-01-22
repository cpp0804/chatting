package service.impl;

import entity.Moment;
import entity.Picture;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.CollectionsVo;
import pojo.PostVo;
import pojo.RequestResultVO;
import relationship.Collections;
import relationship.Like;
import relationship.Post;
import repository.CollectionRepository;
import repository.MomentRepository;
import service.CollectionsService;
import service.UserService;
import utils.DateJsonValueProcessor;
import utils.HttpResponseConstants;
import utils.ResultBuilder;

import java.util.*;

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
    public RequestResultVO collectOrCancel(Long momentId) {

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

        List<Collections> cols = collectionRepository.myCollections(userId);

        java.util.Collections.sort(cols, new Comparator<Collections>() {
            public int compare(Collections o1, Collections o2) {
                if (o1.getPostDate() != null & o2.getPostDate() != null) {
                    return o2.getPostDate().compareTo(o1.getPostDate());
                } else {
                    return 1;
                }
            }
        });
        Map<String, Object> map = new HashMap<String, Object>();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"user", "moment"});
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        map.put("aaData", JSONArray.fromObject(this.createVos(cols), config));
        return map;

    }


    private List<CollectionsVo> createVos(List<Collections> collections) {
        List<CollectionsVo> collectionsVos = new ArrayList<CollectionsVo>();
        for (Collections collect : collections) {
            CollectionsVo collectionsVo = new CollectionsVo();
            BeanUtils.copyProperties(collect, collectionsVo);
            collectionsVo.setDescription(collect.getMoment().getDescription());
            List<String> pictures = new ArrayList<>();
            Moment moment = momentRepository.findById(collect.getMoment().getId()).get();
            for (Iterator iterator = moment.getPictures().iterator(); iterator.hasNext(); ) {
                Picture picture = (Picture) iterator.next();
                pictures.add(picture.getUrl());
            }
            collectionsVo.setPictures(pictures);
            collectionsVo.setUserName(collect.getUser().getName());
            collectionsVo.setUserPortrait(collect.getUser().getPortrait());
            collectionsVo.setLiked(isLiked(collect));
            collectionsVos.add(collectionsVo);
        }
        return collectionsVos;
    }

    private boolean isLiked(Collections collections) {
        User user = userService.getSessionUser();
        List<Like> likes = user.getMomentsLike();
        for (Iterator iterator = likes.iterator(); iterator.hasNext(); ) {
            Like l = (Like) iterator.next();
            if (l.getMoment().getId().equals(collections.getMoment().getId())) {
                return true;
            }
        }
        return false;
    }
}
