package com.system.po;

public class NoteDic {
    public Integer dicid;  //数据库的主键
    public String username;  //创建目录的用户名

    public String dicname;   //某个用户的目录名称

    public Integer dictype; //用于区分招生信息管理(1)和班级管理(0)

    public Integer getDictype() {
        return dictype;
    }

    public void setDictype(Integer dictype) {
        this.dictype = dictype;
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

    public String getDicname() {
        return dicname;
    }

    public void setDicname(String dicname) {
        this.dicname = dicname;
    }


}
