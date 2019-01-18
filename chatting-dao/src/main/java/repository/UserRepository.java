package repository;


import entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import relationship.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query(value = "match(u:User)-[r:LOGIN]->(l:Login)where id(l)={0} return u")
    User findUserByLogin(Long loginId);

    @Query(value = "match(u:User)-[r:FRIENDS]-(u2:User)where id(u)={0} return u2")
    List<User> findFriends(Long userId);

    Optional<User> findById(Long userId);

}
