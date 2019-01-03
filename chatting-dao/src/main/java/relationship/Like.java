package relationship;

import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity(type = "LIKE")
public class Like extends BaseRelationship {

    public Like() {
    }
}
