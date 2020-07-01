package com.system.po;

import java.sql.Timestamp;

public class Loginlog {
    private Integer loginlogid;
    private String username;
    private String cip;
    private String cname;
    private Timestamp logintime;


    public Integer getLoginlogid() {
        return loginlogid;
    }

    public void setLoginlogid(Integer loginlogid) {
        this.loginlogid = loginlogid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public Timestamp getLogintime() {
        return logintime;
    }

    public void setLogintime(Timestamp logintime) {
        this.logintime = logintime;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
