package service.impl;

import entity.Moment;
import entity.Picture;
import entity.User;
import javafx.geometry.Pos;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.PostVo;
import pojo.RequestResultVO;
import relationship.Like;
import relationship.Post;
import repository.PostRepository;
import repository.UserRepository;
import service.PostService;
import service.UserService;
import utils.DateJsonValueProcessor;
import utils.HttpResponseConstants;
import utils.ResultBuilder;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    public Map<String, Object> getFriendsMoment() {
//        Long userId = userService.getSessionId();
        User user = userService.getSessionUser();
        List<User> friends = new ArrayList<>();
        for (User u : user.getFriends()) {
            friends.add(userRepository.findById(u.getId()).get());
        }
        Iterable<Long> friendsMomentsIds = new ArrayList<>();
        List<Post> friendsMonents = new ArrayList<Post>();
        User friend;
        Post post;
        for (Iterator iterator = friends.iterator(); iterator.hasNext(); ) {
            friend = (User) iterator.next();
            for (Iterator iterator1 = friend.getMomentsPost().iterator(); iterator1.hasNext(); ) {
                post = (Post) iterator1.next();
                ((ArrayList<Long>) friendsMomentsIds).add(post.getId());
            }
        }
        friendsMonents.addAll((Collection<? extends Post>) postRepository.findAllById(friendsMomentsIds));
        Collections.sort(friendsMonents, new Comparator<Post>() {
            public int compare(Post o1, Post o2) {
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
        map.put("aaData", JSONArray.fromObject(this.createVos(friendsMonents), config));
        return map;
    }

    @Override
    public Map<String, Object> getMyMoment() {
//        Long userId = userService.getSessionId();
        User user = userService.getSessionUser();
        Iterable<Long> myMomentsIds = new ArrayList<>();
        List<Post> myMonents = new ArrayList<Post>();
        User friend;
        Post post;
        for (Iterator iterator = user.getMomentsPost().iterator(); iterator.hasNext(); ) {
            post = (Post) iterator.next();
            ((ArrayList<Long>) myMomentsIds).add(post.getId());
        }

        myMonents.addAll((Collection<? extends Post>) postRepository.findAllById(myMomentsIds));
        Collections.sort(myMonents, new Comparator<Post>() {
            public int compare(Post o1, Post o2) {
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
        map.put("aaData", JSONArray.fromObject(this.createVos(myMonents), config));
        return map;

    }

    @Override
    public RequestResultVO insert(Post post) {
        if (post == null) {
            throw new util.BizException(HttpResponseConstants.Public.ERROR_700);
        }
        postRepository.save(post);
        return ResultBuilder.buildSuccessResult(HttpResponseConstants.Public.POST_SUCCESS, "");
    }

    @Override
    public Post createPost(Moment moment) {
        Post post = new Post();
        post.setPostDate(new Date());
        post.setUser(userRepository.findById(userService.getSessionId()).get());
        post.setMoment(moment);
        return post;
    }

    @Override
    public Map<String, Object> getHomeMoment() {
        Date endDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, -7);
        Date beginDate = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        List<Post> posts = postRepository.getHomeMoment(simpleDateFormat.format(beginDate), simpleDateFormat.format(endDate));
        Map<String, Object> map = new HashMap<>();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"user", "moment"});
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        map.put("aaData", JSONArray.fromObject(this.createVos(posts), config));
        return map;

    }

    private List<PostVo> createVos(List<Post> posts) {
        List<PostVo> postVos = new ArrayList<PostVo>();
        for (Post post : posts) {
            PostVo postVo = new PostVo();
            BeanUtils.copyProperties(post, postVo);
            postVo.setDescription(post.getMoment().getDescription());
            List<String> pictures = new ArrayList<>();
            for (Iterator iterator = post.getMoment().getPictures().iterator(); iterator.hasNext(); ) {
                Picture picture = (Picture) iterator.next();
                pictures.add(picture.getUrl());
            }
            postVo.setPictures(pictures);
            postVo.setUserName(post.getUser().getName());
            postVo.setUserPortrait(post.getUser().getPortrait());
            postVo.setCollected(isCollected(post));
            postVo.setLiked(isLiked(post));
            postVos.add(postVo);
        }
        return postVos;
    }

    private boolean isCollected(Post post) {
        User user = userService.getSessionUser();
        List<relationship.Collections> collections = user.getMomentsCollection();
        for (Iterator iterator = collections.iterator(); iterator.hasNext(); ) {
            relationship.Collections c = (relationship.Collections) iterator.next();
            if (c.getMoment().getId() == post.getMoment().getId()) {
                return true;
            }
        }
        return false;
    }

    private boolean isLiked(Post post) {
        User user = userService.getSessionUser();
        List<Like> likes = user.getMomentsLike();
        for (Iterator iterator = likes.iterator(); iterator.hasNext(); ) {
            Like l = (Like) iterator.next();
            if (l.getMoment().getId() == post.getMoment().getId()) {
                return true;
            }
        }
        return false;
    }
}
