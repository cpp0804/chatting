package service.impl;

import entity.Moment;
import entity.User;
import javafx.geometry.Pos;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import relationship.Collections;
import relationship.Post;
import repository.CollectionRepository;
import repository.MomentRepository;
import repository.PostRepository;
import repository.UserRepository;
import service.MomentService;
import service.PostService;
import service.UserService;
import utils.DateJsonValueProcessor;

import java.time.Year;
import java.util.*;

@Service
public class MomentServiceImpl implements MomentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MomentRepository momentRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void collectMoment(User user, Long MomentId){


//        Collections collections = new Collections();
//        collections.setUser(user);
//        collections.setMoment(momentRepository.findById(MomentId).get());
//        collections.setPostDate(new Date());

        Post post1 = new Post();
        post1.setUser(user);
        post1.setMoment(momentRepository.findById(40L).get());
        post1.setPostDate(new Date());

        user.getMomentsPost().add(post1);

        post1.setMoment(momentRepository.findById(19L).get());
        post1.setPostDate(new Date());


        user.getMomentsPost().add(post1);

        userRepository.save(user);

//        for (Collections col : user.getMomentsCollection()){
//            if (col.getId() == MomentId) {
//                return;
//            }
//        }
//        collectionRepository.save(collections);
    }
}
