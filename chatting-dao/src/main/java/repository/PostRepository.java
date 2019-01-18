package repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import relationship.Post;


@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {

    //    @Query(value = "match(l:Login{logName:{0}})return l")
//    @Query(value = "match(u:User)-[p:POST]->(m:Moment)where u.name in {0} with m,p order by p.postDate return p")
//    public List<Post> getOrderedPostByUserId(List<String> userIds);

    //    @Query(value = "match(u:User)-[p:POST]-(m:Moment)where id(u)={0} return p")
//    Optional<Post> findById(Long postId);


    @Override
    Iterable<Post> findAllById(Iterable<Long> iterable);
    
}
