//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package security;

import com.alibaba.fastjson.JSONObject;
import entity.User;

public class AuthencationResult {
    private JSONObject menuJSON;
    private boolean status;
    private User user;
    private String logName;
    private String msg;

    public AuthencationResult() {
    }

    public void setMenuJSON(JSONObject menuJSON) {
        this.menuJSON = menuJSON;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getMenuJSON() {
        return this.menuJSON;
    }

    public boolean isStatus() {
        return this.status;
    }

    public User getUser() {
        return this.user;
    }

    public String getLogName() {
        return this.logName;
    }

    public String getMsg() {
        return this.msg;
    }
}
