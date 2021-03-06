package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@NodeEntity
public class Picture {

    @Id
    @GeneratedValue
    private Long id;

    private String url;

    @CreatedBy
    @JsonIgnore
    private User creator;

    @CreatedDate
    private Date createTime;

    @Relationship(type = "MOMENT_PIC",direction = Relationship.INCOMING)
    @JsonIgnore
    private Moment moment;

    public Picture() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public User getCreator() {
        return creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setMoment(Moment moment) {
        this.moment = moment;
    }

    public Moment getMoment() {
        return moment;
    }
}
