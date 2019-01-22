package pojo;

import relationship.Post;

import java.util.List;


public class PostVo extends Post {

    private String userName;
    private String userPortrait;

    private String description;

    private List<String> pictures;

    private boolean liked;

    private boolean collected;

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public boolean isLiked() {
        return liked;
    }

    public boolean isCollected() {
        return collected;
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
