package repository;


import entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query(value = "match(u:User)-[r:LOGIN]->(l:Login)where id(l)={0} return u")
    User findUserByLogin(Long loginId);

    @Query(value = "match(u:User)-[r:FRIENDS]-(u2:User)where id(u)={0} return u2")
    List<User> findFriends(Long userId);

    Optional<User> findById(Long userId);

    @Query(value = "match(u:User) where u.name=~{0} return u")
    List<User> findUserByName(String name);

    @Query(value = "match(u1:User)-[f:FRIENDS]->(u2:User)where id(u2)={0} return count(f)")
    int getPanNum(Long userId);

}
