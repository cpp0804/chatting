package pojo;

import relationship.Comment;

public class CommentVo extends Comment {

    private String userName;
    private String userPortrait;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPortrait() {
        return userPortrait;
    }
}
