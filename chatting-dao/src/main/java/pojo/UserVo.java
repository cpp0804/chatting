package pojo;

import entity.User;

public class UserVo extends User {

    private int likeNum;
    private int panNum;
    private int postNum;

    public void setPanNum(int panNum) {
        this.panNum = panNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public int getPanNum() {
        return panNum;
    }

    public int getPostNum() {
        return postNum;
    }
}
