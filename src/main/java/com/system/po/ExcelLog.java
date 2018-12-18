package com.system.po;

public class ExcelLog {
    Integer logid;
    Integer excelid;
    String stuname;

    Integer typeid; //0表示导入功能：成功的  1 表示导入功能 重名的  2表示更新功能成功的  3表示更新功能信息对不上的

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }



    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public Integer getExcelid() {
        return excelid;
    }

    public void setExcelid(Integer excelid) {
        this.excelid = excelid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
}
