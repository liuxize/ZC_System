package com.system.po;

import java.util.Date;

public class Userlogin {
    private Integer userid;

    private String username;

    private String password;

    private Integer role;

    private Integer permission;

    private Date userbirth;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getUserbirth() {
        return userbirth;
    }

    public void setUserbirth(Date userbirth) {
        this.userbirth = userbirth;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
}