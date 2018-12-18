package com.system.po;

public class NoteTable {

    private Integer noteid;
    private String stuname;
    private String stuschool;
    private String stugrade;
    private String stucourse;
    private String stutel;
    private String fathertel;
    private String mothertel;
    private String remarktext;
    private String username; //添加本条信息的用户名
    private Integer dicid;

    public String getStucourse() {
        return stucourse;
    }

    public void setStucourse(String stucourse) {
        this.stucourse = stucourse;
    }

    public Integer getDicid() {
        return dicid;
    }

    public void setDicid(Integer dicid) {
        this.dicid = dicid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStuschool() {
        return stuschool;
    }

    public void setStuschool(String stuschool) {
        this.stuschool = stuschool;
    }

    public String getStugrade() {
        return stugrade;
    }

    public void setStugrade(String stugrade) {
        this.stugrade = stugrade;
    }

    public String getStutel() {
        return stutel;
    }

    public void setStutel(String stutel) {
        this.stutel = stutel;
    }

    public String getFathertel() {
        return fathertel;
    }

    public void setFathertel(String fathertel) {
        this.fathertel = fathertel;
    }

    public String getMothertel() {
        return mothertel;
    }

    public void setMothertel(String mothertel) {
        this.mothertel = mothertel;
    }

    public String getRemarktext() {
        return remarktext;
    }

    public void setRemarktext(String remarktext) {
        this.remarktext = remarktext;
    }
}
