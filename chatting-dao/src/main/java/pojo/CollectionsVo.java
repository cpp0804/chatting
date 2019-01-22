package pojo;

import relationship.Collections;

import java.util.List;

public class CollectionsVo extends Collections {

    private boolean liked;

    private String userName;
    private String userPortrait;

    private String description;

    private List<String> pictures;


    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public boolean isLiked() {
        return liked;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPortrait() {
        return userPortrait;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getPictures() {
        return pictures;
    }
}
