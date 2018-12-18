package com.system.po;

import java.util.Date;

public class Birthday {
    Integer birthid;
    Integer stuid;
    String status;
    Date birthday;
    String telephone;
    String birthname;

    public Integer getBirthid() {
        return birthid;
    }

    public void setBirthid(Integer birthid) {
        this.birthid = birthid;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBirthname() {
        return birthname;
    }

    public void setBirthname(String birthname) {
        this.birthname = birthname;
    }
}
