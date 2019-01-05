package entity;

import javafx.geometry.Pos;
import org.neo4j.ogm.annotation.*;
import relationship.Collections;
import relationship.Comment;
import relationship.Like;
import relationship.Post;


import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String sex;
    private String phone;
    private String email;

    @Relationship(type = "LOGIN")
    private List<Login> logins = new ArrayList<Login>();

    @Relationship(type = "POST")
    private List<Post> momentsPost = new ArrayList<Post>();

    @Relationship(type = "LIKE")
    private List<Like> momentsLike = new ArrayList<Like>();

    @Relationship(type = "COMMENT")
    private List<Comment> momentsComment = new ArrayList<Comment>();

    @Relationship(type = "COLLECTION")
    private List<Collections> momentsCollection = new ArrayList<Collections>();

    @Relationship(type = "FRIENDS")
    private List<User> friends = new ArrayList<User>();

    @Relationship(type = "SPECIAL_FRIENDS")
    private List<User> specialFriends = new ArrayList<User>();

    public void setMomentsCollection(List<Collections> momentsCollection) {
        this.momentsCollection = momentsCollection;
    }

    public List<Collections> getMomentsCollection() {
        return momentsCollection;
    }

    public void setSpecialFriends(List<User> specialFriends) {
        this.specialFriends = specialFriends;
    }

    public List<User> getSpecialFriends() {
        return specialFriends;
    }

    public void setMomentsPost(List<Post> momentsPost) {
        this.momentsPost = momentsPost;
    }

    public void setMomentsLike(List<Like> momentsLike) {
        this.momentsLike = momentsLike;
    }

    public void setMomentsComment(List<Comment> momentsComment) {
        this.momentsComment = momentsComment;
    }

    public List<Post> getMomentsPost() {
        return momentsPost;
    }

    public List<Like> getMomentsLike() {
        return momentsLike;
    }

    public List<Comment> getMomentsComment() {
        return momentsComment;
    }

    public void setLogins(List<Login> logins) {
        this.logins = logins;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<User> getFriends() {
        return friends;
    }

    public List<Login> getLogins() {
        return logins;
    }

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
