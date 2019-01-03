package repository;

import entity.Moment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MomentRepository extends Neo4jRepository<Moment,Long> {
}
