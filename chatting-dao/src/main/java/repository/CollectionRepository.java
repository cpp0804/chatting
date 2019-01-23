package repository;

import entity.Moment;
import entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import relationship.Collections;
import relationship.Comment;

import java.util.List;

@Repository
public interface CollectionRepository extends Neo4jRepository<Collections, Long> {

    @Query(value = "MATCH (u:User)-[c:COLLECTION]-(m:Moment) where id(u)={0} RETURN c order by c.postDate desc")
    List<Collections> myCollections(Long userId);


    @Override
    void delete(Collections collections);
}
