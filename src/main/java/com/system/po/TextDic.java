package com.system.po;

public class TextDic {
    private Integer textid;
    private String username;
    private String title;
    private String content;

    private Integer texttype; //用于区分 0标记班级管理 1表示招生信息管理

    public Integer getTexttype() {
        return texttype;
    }

    public void setTexttype(Integer texttype) {
        this.texttype = texttype;
    }

    public Integer getTextid() {
        return textid;
    }

    public void setTextid(Integer textid) {
        this.textid = textid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
