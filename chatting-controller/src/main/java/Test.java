import entity.Login;
import entity.Moment;
import entity.User;
import javafx.geometry.Pos;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import relationship.Comment;
import relationship.Like;
import relationship.Post;
import repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-config.xml"})
public class Test {

    @Autowired
    private UserRepository repo;

    @Autowired
    private MomentRepository momentRepository;
    //
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;


    @org.junit.Test
    public void test() {

//        User user=new User();
//        user.setId(1L);
//        Optional<User> o = repo.findById(51L);
//        System.out.println(o.get().getSex());
//        Iterable user = repo.findAll();
//        Iterator iter = user.iterator();
//        while (iter.hasNext()) {
//            System.out.println(((User) iter.next()).getId());
//        }

//        User user=new User();
//        user.setName("pp");
//        repo.save(user);
//        Moment moment1=new Moment();
//        moment1.setDescription("ppppp");
//        Moment moment2=new Moment();
//        moment2.setDescription("pppppccccc");
//        momentRepository.save(moment1);
//        momentRepository.save(moment2);
//        Optional<User> user = repo.findById(183L);
//
//
//        Optional<Moment> moment = momentRepository.findById(132L);
//        Comment comment = new Comment();
//        comment.setMoment(moment.get());
//        comment.setUser(user.get());
//        //user.get().getMomentsComment().add(comment);
//        commentRepository.save(comment);
////        user.get().setComment(comment);
//        repo.save(user.get());

//        User user = new User();
//        user.setName("pp00");
//        Login login = new Login();
//        login.setLogName("oo");
//        login.setPassword("0000");
//        user.getLogins().add(login);
//        repo.save(user);
//        Optional<User> user=repo.findById(255L);
//        System.out.println(user.get().getMomentsComment());
//        Login login=loginRepository.getLoginByLogName("ooo");
//        System.out.println(login);
//
//        Like like=new Like();
//        like.setMoment(momentRepository.findById(254L).get());
//        like.setUser(repo.findById(256L).get());
//        likeRepository.save(like);

        Optional<User> user1 = repo.findById(102L);
        Optional<User> user2 = repo.findById(91L);
        Optional<User> user3 = repo.findById(22L);
        user1.get().getFriends().add(user2.get());
        user1.get().getFriends().add(user3.get());
        repo.save(user1.get());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Moment moment2 = new Moment();
        moment2.setDescription("user2 moment22222");
        Post post2 = new Post();
        try {
            post2.setPostDate(simpleDateFormat.parse("2018-9-1 12:12:12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        post2.setMoment(moment2);
        post2.setUser(user2.get());
        postRepository.save(post2);

        Moment moment3 = new Moment();
        moment3.setDescription("user3 moment33333");
        Post post3 = new Post();
        try {
            post3.setPostDate(simpleDateFormat.parse("2018-9-4 12:12:12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        post3.setMoment(moment3);
        post3.setUser(user3.get());
        postRepository.save(post3);

        Moment moment32 = new Moment();
        moment32.setDescription("user3 moment32323232");
        Post post32 = new Post();
        try {
            post32.setPostDate(simpleDateFormat.parse("2018-8-1 12:12:12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        post32.setMoment(moment32);
        post32.setUser(user3.get());
        postRepository.save(post32);
    }

    @org.junit.Test
    public void testPost() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        List<String> users = new ArrayList<>();
//        users.add("cpp");
//        users.add("cpp3");
//        List<Post> posts = postRepository.getOrderedPostByUserId(users);
//        for (Iterator iterator = posts.iterator(); iterator.hasNext(); ) {
//            Post post = (Post) iterator.next();
//            System.out.println(String.format(post.getMoment().getDescription() + "---"));
//            if(post.getPostDate()!=null){
//                System.out.println(simpleDateFormat.format(post.getPostDate()));
//            }
//        }
    }

    @org.junit.Test
    public void remove(){
        Optional<User>user=repo.findById(104L);
        Optional<Login>login=loginRepository.findById(103L);
        loginRepository.delete(login.get());

    }

    @org.junit.Test
    public void setFriends(){
        User user=new User();
        user.setName("培培");
        user.setEmail("培培@email.com");
        user.setSex("女");
        Login login=new Login();
        login.setLogName("l");
        login.setPassword("l");
        user.getLogins().add(login);

        User user1=new User();
        user1.setSex("女");
        user1.setEmail("user1@email");
        user1.setName("user1");

        User user2=new User();
        user2.setSex("女");
        user2.setEmail("user2@email");
        user2.setName("user2");

        user.getFriends().add(user1);
        user.getFriends().add(user2);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Moment moment2 = new Moment();
        moment2.setDescription("user2 moment22222");
        Post post2 = new Post();
        try {
            post2.setPostDate(simpleDateFormat.parse("2018-9-1 12:12:12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        post2.setMoment(moment2);
        post2.setUser(user1);
        postRepository.save(post2);

        Moment moment3 = new Moment();
        moment3.setDescription("user3 moment33333");
        Post post3 = new Post();
        try {
            post3.setPostDate(simpleDateFormat.parse("2018-9-4 12:12:12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        post3.setMoment(moment3);
        post3.setUser(user2);
        postRepository.save(post3);

        Moment moment32 = new Moment();
        moment32.setDescription("user3 moment32323232");
        Post post32 = new Post();
        try {
            post32.setPostDate(simpleDateFormat.parse("2018-8-1 12:12:12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        post32.setMoment(moment32);
        post32.setUser(user2);

        repo.save(user);


    }

    @org.junit.Test
    public void getU(){
        Optional<Login>login=loginRepository.findById(6L);
//        Optional<User>user=repo.findById(5L);
//        login.get().setUser(user.get());
//        loginRepository.save(login.get());
//        System.out.println(login.get().getUser());
        System.out.println(login.get());
    }

    @org.junit.Test
    public void testA() {
        User user=repo.findById(336L).get();
        Post post=new Post();
        Moment moment=new Moment();
        moment.setDescription("336 moment1");
        post.setMoment(moment);
        post.setUser(user);
        post.setPostDate(new Date());

        Post post2=new Post();
        Moment moment2=new Moment();
        moment.setDescription("336 moment2");
        post2.setMoment(moment2);
        post2.setUser(user);
        post2.setPostDate(new Date());
        user.getMomentsPost().add(post);
        user.getMomentsPost().add(post2);
        repo.save(user);
//        System.out.println(user.getName());

//        List<Post> post=postRepository.findPostByUser(user.getId());
    }
}


