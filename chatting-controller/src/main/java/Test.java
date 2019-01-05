import entity.Login;
import entity.Moment;
import entity.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import relationship.Comment;
import relationship.Like;
import repository.*;

import java.util.Optional;

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
        Optional<User> user=repo.findById(255L);
        System.out.println(user.get().getMomentsComment());
        Login login=loginRepository.getLoginByLogName("ooo");
        System.out.println(login);

        Like like=new Like();
        like.setMoment(momentRepository.findById(254L).get());
        like.setUser(repo.findById(256L).get());
        likeRepository.save(like);
    }
}


