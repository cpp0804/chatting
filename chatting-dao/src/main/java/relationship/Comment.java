package relationship;

import entity.Moment;
import entity.User;
import org.neo4j.ogm.annotation.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@RelationshipEntity(type = "COMMENT")
public class Comment extends BaseRelationship {

    public Comment() {
    }

    private String comment;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
