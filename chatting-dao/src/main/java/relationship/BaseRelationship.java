package relationship;

import entity.Moment;
import entity.User;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

public class BaseRelationship {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private User user;

    @EndNode
    private Moment moment;

    @CreatedDate
    private Date postDate;

    public BaseRelationship() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Moment getMoment() {
        return moment;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMoment(Moment moment) {
        this.moment = moment;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }


}
