package repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import relationship.Collections;
import relationship.Comment;

@Repository
public interface CollectionRepository extends Neo4jRepository<Collections, Long> {
}