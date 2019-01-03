package repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import relationship.Comment;

@Repository
public interface CommentRepository extends Neo4jRepository<Comment, Long> {
}
