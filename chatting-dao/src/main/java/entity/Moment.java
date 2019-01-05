package entity;

import entity.Picture;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import relationship.Collections;
import relationship.Comment;
import relationship.Like;
import relationship.Post;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Moment {

    public Moment() {
    }

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @Relationship(type = "MOMENT_PIC", direction = Relationship.OUTGOING)
    private List<Picture> pictures = new ArrayList<Picture>();

    @Relationship(type = "POST", direction = Relationship.INCOMING)
    private List<Post> momentsPost = new ArrayList<Post>();

    @Relationship(type = "LIKE", direction = Relationship.INCOMING)
    private List<Like> momentsLike = new ArrayList<Like>();

    @Relationship(type = "COMMENT", direction = Relationship.INCOMING)
    private List<Comment> momentsComment = new ArrayList<Comment>();

    @Relationship(type = "COLLECTION", direction = Relationship.INCOMING)
    private List<Collections> momentsCollection = new ArrayList<Collections>();

    public void setMomentsCollection(List<Collections> momentsCollection) {
        this.momentsCollection = momentsCollection;
    }

    public List<Collections> getMomentsCollection() {
        return momentsCollection;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Picture> getPictures() {
        return pictures;
    }
}
