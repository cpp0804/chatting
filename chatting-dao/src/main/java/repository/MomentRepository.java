package repository;

import entity.Moment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MomentRepository extends Neo4jRepository<Moment,Long> {

    @Override
    Optional<Moment> findById(Long aLong);
}
