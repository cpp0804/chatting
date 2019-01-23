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
import pojo.LikesVo;
import pojo.RequestResultVO;
import relationship.Collections;
import relationship.Like;
import relationship.Post;
import repository.LikeRepository;
import repository.MomentRepository;
import service.LikeService;
import service.UserService;
import utils.DateJsonValueProcessor;
import utils.HttpResponseConstants;
import utils.ResultBuilder;

import java.util.*;

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
    public RequestResultVO likeOrCancel(Long momentId) {

        //获取User
        User user = userService.getSessionUser();

        if (user == null || momentId == null) {
            throw new util.BizException(HttpResponseConstants.Public.ERROR_700);
        } else {

            // 若已经喜欢，则取消喜欢
            for (Like lk : user.getMomentsLike()) {
                if (lk.getMoment().getId().equals(momentId)) {
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

        List<Like> lks = likeRepository.myLikes(userId);
        java.util.Collections.sort(lks, new Comparator<Like>() {
            public int compare(Like o1, Like o2) {
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
        map.put("aaData", JSONArray.fromObject(this.createVos(lks), config));
        return map;
    }

    private List<LikesVo> createVos(List<Like> likes) {
        List<LikesVo> likesVos = new ArrayList<LikesVo>();
        for (Like like : likes) {
            LikesVo likesVo = new LikesVo();
            BeanUtils.copyProperties(like, likesVo);
            likesVo.setDescription(like.getMoment().getDescription());
            List<String> pictures = new ArrayList<>();
            Moment moment = momentRepository.findById(like.getMoment().getId()).get();
            for (Iterator iterator = moment.getPictures().iterator(); iterator.hasNext(); ) {
                Picture picture = (Picture) iterator.next();
                pictures.add(picture.getUrl());
            }
            likesVo.setPictures(pictures);
            likesVo.setUserName(like.getUser().getName());
            likesVo.setUserPortrait(like.getUser().getPortrait());
            likesVo.setLiked(isLiked(like));
            likesVo.setCollected(isCollected(like));
            likesVos.add(likesVo);
        }
        return likesVos;
    }

    private boolean isCollected(Like like) {
        User user = userService.getSessionUser();
        List<relationship.Collections> collections = user.getMomentsCollection();
        for (Iterator iterator = collections.iterator(); iterator.hasNext(); ) {
            relationship.Collections c = (relationship.Collections) iterator.next();
            if (c.getMoment().getId().equals(like.getMoment().getId())) {
                return true;
            }
        }
        return false;
    }

    private boolean isLiked(Like like) {
        User user = userService.getSessionUser();
        List<Like> likes = user.getMomentsLike();
        for (Iterator iterator = likes.iterator(); iterator.hasNext(); ) {
            Like l = (Like) iterator.next();
            if (l.getMoment().getId().equals(like.getMoment().getId())) {
                return true;
            }
        }
        return false;
    }
}
