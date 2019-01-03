package repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import relationship.Post;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {
}
