package com.bobo.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private String userid;

    private String username;

    private Integer userage;

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", userage=" + userage +
                '}';
    }

    public User() {
    }

    public User(String userid, String username, Integer userage) {
        this.userid = userid;
        this.username = username;
        this.userage = userage;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserage() {
        return userage;
    }

    public void setUserage(Integer userage) {
        this.userage = userage;
    }
}
