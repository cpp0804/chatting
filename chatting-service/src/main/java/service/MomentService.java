package service;

import entity.Moment;
import entity.User;
import pojo.RequestResultVO;

import java.util.Map;

public interface MomentService {

    Moment createMoment(String description,String pictureUrl);
}
