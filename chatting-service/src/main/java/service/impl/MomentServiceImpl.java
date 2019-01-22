package service.impl;

import entity.Moment;
import entity.Picture;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pojo.RequestResultVO;
import relationship.Comment;
import relationship.Post;
import relationship.Collections;
import repository.CollectionRepository;
import repository.MomentRepository;
import repository.PostRepository;
import repository.UserRepository;
import service.MomentService;
import service.PostService;
import service.UserService;
import utils.DateJsonValueProcessor;
import utils.HttpResponseConstants;
import utils.JsonFastUtil;
import utils.ResultBuilder;

import java.util.*;

@Service
public class MomentServiceImpl implements MomentService {

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MomentRepository momentRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Moment createMoment(String description, String pictureUrl) {
        String[] pictureUrlList;
        List<String> temp ;
        Moment moment = new Moment();

        try {
            pictureUrlList = pictureUrl.split(",");
            temp = Arrays.asList(pictureUrlList);
//            pictureUrlList= JsonFastUtil.parseObject(pictureUrl,List.class);
//            moment = JsonFastUtil.parseObject(keys, Moment.class);
        } catch (Exception e) {
            throw new util.BizException(HttpResponseConstants.Public.ERROR_700);
        }
        Picture picture;
        List<Picture>pictures=new ArrayList<>();
        for(String url:temp){
            picture=new Picture();
            picture.setUrl(url);
            picture.setCreateTime(new Date());
            picture.setCreator(userService.getSessionUser());
            pictures.add(picture);
        }
        moment.setDescription(description);
        moment.setPictures(pictures);
        return moment;
    }


}


