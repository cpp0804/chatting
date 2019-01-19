package service.impl;

import entity.Moment;
import entity.Picture;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import relationship.Post;
import service.MomentService;
import service.PostService;
import service.UserService;
import utils.DateJsonValueProcessor;
import utils.HttpResponseConstants;
import utils.JsonFastUtil;

import java.util.*;

@Service
public class MomentServiceImpl implements MomentService {

    @Autowired
    private UserService userService;


    @Override
    public Moment createMoment(String description, String pictureUrl) {
        List<String> pictureUrlList;
        Moment moment = new Moment();

        try {
            pictureUrlList= JsonFastUtil.parseObject(pictureUrl,List.class);
//            moment = JsonFastUtil.parseObject(keys, Moment.class);
        } catch (Exception e) {
            throw new util.BizException(HttpResponseConstants.Public.ERROR_700);
        }
        Picture picture;
        List<Picture>pictures=new ArrayList<>();
        for(String url:pictureUrlList){
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
