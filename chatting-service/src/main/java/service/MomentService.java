package service;

import entity.Moment;
import entity.User;

import java.util.Map;

public interface MomentService {
    void collectMoment(User user, Long MomentId);

    Moment createMoment(String description,String pictureUrl);
}
