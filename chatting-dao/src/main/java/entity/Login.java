package entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Login {

    @Id
    @GeneratedValue
    private Long id;

    private String logName;
    private String password;

    @Relationship(type = "BE_LOGIN", direction = Relationship.INCOMING)
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getLogName() {
        return logName;
    }

    public String getPassword() {
        return password;
    }

    public Login() {
    }
}
