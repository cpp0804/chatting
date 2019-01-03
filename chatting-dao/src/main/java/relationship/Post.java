package relationship;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "POST")
public class Post extends BaseRelationship{

    public Post() {
    }

}
