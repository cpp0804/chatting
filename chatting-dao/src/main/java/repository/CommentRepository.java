package repository;

import entity.Moment;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import relationship.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends Neo4jRepository<Comment, Long> {

    @Query("match(u:User)-[c:COMMENT]->(m:Moment)where id(m)={0} return c")
    List<Comment> findAllByMoment(Long momentId);
}
