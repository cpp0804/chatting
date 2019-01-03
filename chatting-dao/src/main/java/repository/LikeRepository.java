package repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import relationship.Comment;
import relationship.Like;

@Repository
public interface LikeRepository extends Neo4jRepository<Like, Long> {
}
