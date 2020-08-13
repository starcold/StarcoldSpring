/**
 * author: starcold
 * createTime: 2020/08/10
 * context: 用户信息类
 * updateTime:
 * updateContext:
 */
package myBatis;

import java.util.Date;

public class UserInfo implements java.io.Serializable {
    private int id;
    private String userName;
    private String nickName;
    private String password;
    private Date lastLoginTime;

    //region Getter & Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    //endregion
}
