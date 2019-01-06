package repository;

import entity.Login;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import relationship.Like;

@Repository
public interface LoginRepository extends Neo4jRepository<Login, Long> {

    //    @Query(value = "match(u:User)-[:LOGIN]->(l:Login{logName:{0})return l")
    @Query(value = "match(l:Login{logName:{0}})return l")
    public Login getLoginByLogName(String logName);

}
