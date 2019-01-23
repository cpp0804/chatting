package repository;

import entity.Moment;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import relationship.Collections;
import relationship.Comment;
import relationship.Like;

import java.util.List;

@Repository
public interface LikeRepository extends Neo4jRepository<Like, Long> {

    @Query(value = "MATCH (u:User)-[l:LIKE]-(m:Moment) where id(u)={0} RETURN l order by l.postDate desc")
    List<Like> myLikes(Long userId);

    @Override
    void delete(Like like);
}
