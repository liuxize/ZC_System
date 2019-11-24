package com.system.po;

import java.util.Date;

public class Stu {
    private Integer stuid;   //学号

    private String stuname;  //姓名

    private String stunamehis;

    private String stusex; //学生性别

    private String stusexhis;//学生性别历史

    private String stutype;   //学生类型  优质 普通

    private Date stubirth;   //生日

    private String stubirthhis;

    private String school;    //学校

    private String schoolhis;  //学校历史

    private String major;     //专业

    private String majorhis;

    private String stutel;    //电话

    private String stutelhis;

    private String address;    //家庭地址

    private String addresshis;    //家庭地址

    private String mothername;    //母亲姓名

    private String mothernamehis;

    private String mothercompany;    //母亲单位

    private String mothercompanyhis;    //母亲单位历史

    private String motherjob;    //母亲工作

    private String motherjobhis;    //母亲工作历史

    private String mothertel;    //母亲电话

    private String mothertelhis;

    private Date motherbirth;   //母亲生日

    private String motherbirthhis;  //生日历史

    private String motherdegree;

    private String motherdegreehis;

    private String fathername;    //父亲姓名

    private String fathernamehis;

    private String fathercompany;    //父亲单位

    private String fathercompanyhis;    //父亲单位历史

    private String fatherjob;    //父亲工作

    private String fatherjobhis;    //父亲工作历史

    private String fathertel;    //父亲电话

    private String fathertelhis;

    private Date fatherbirth;

    private String fatherbirthhis;

    private String fatherdegree;  //父亲学历

    private String fatherdegreehis;

    private String mastername;    //教师姓名

    private String masternamehis; //教师姓名历史

    private String mastertel;    //教师电话

    private String mastertelhis;    //教师电话历史

    private String masteraddress;  //教师地址

    private String masteraddresshis;  //教师地址

    private String mastersex;  //教师性别

    private String mastersexhis;  //教师性别历史

    private String masterdegree; //教师学历

    private String masterdegreehis;  //教师学历历史

    private Date masterbirth;  //教师生日

    private String masterbirthhis; //教师生日历史

    private String schooltext;    //在校情况

    private String schooltexthis;

    private String familytext;    //家庭情况

    private String familytexthis;

    private  String studytext;    //学习情况

    private  String studytexthis;

    private String educationtext;    //教育方案

    private String educationtexthis;

    private String supporttext;    //配合情况

    private String supporttexthis;

    private String improvetext;     //提高程度

    private String improvetexthis;

    private Integer gradeid;  //年级id

    private Date recorddate;   //创建表的时间

    private String recordperson; //制作表人

    private Date checkdate;//检验日期

    private String checkdatehis;//校验日期历史

    private Integer campusid;

    public Integer getCampusid() {
        return campusid;
    }

    public void setCampusid(Integer campusid) {
        this.campusid = campusid;
    }

    public Date getMotherbirth() {
        return motherbirth;
    }

    public void setMotherbirth(Date motherbirth) {
        this.motherbirth = motherbirth;
    }

    public String getMotherbirthhis() {
        return motherbirthhis;
    }

    public void setMotherbirthhis(String motherbirthhis) {
        this.motherbirthhis = motherbirthhis;
    }

    public Date getFatherbirth() {
        return fatherbirth;
    }

    public void setFatherbirth(Date fatherbirth) {
        this.fatherbirth = fatherbirth;
    }

    public String getFatherbirthhis() {
        return fatherbirthhis;
    }

    public void setFatherbirthhis(String fatherbirthhis) {
        this.fatherbirthhis = fatherbirthhis;
    }

    public String getRecordperson() {
        return recordperson;
    }

    public void setRecordperson(String recordperson) {
        this.recordperson = recordperson;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStutype() {
        return stutype;
    }

    public void setStutype(String stutype) {
        this.stutype = stutype;
    }

    public Date getStubirth() {
        return stubirth;
    }

    public void setStubirth(Date stubirth) {
        this.stubirth = stubirth;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchoolhis() {
        return schoolhis;
    }

    public void setSchoolhis(String schoolhis) {
        this.schoolhis = schoolhis;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStutel() {
        return stutel;
    }

    public void setStutel(String stutel) {
        this.stutel = stutel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddresshis() {
        return addresshis;
    }

    public void setAddresshis(String addresshis) {
        this.addresshis = addresshis;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getMothercompany() {
        return mothercompany;
    }

    public void setMothercompany(String mothercompany) {
        this.mothercompany = mothercompany;
    }

    public String getMothercompanyhis() {
        return mothercompanyhis;
    }

    public void setMothercompanyhis(String mothercompanyhis) {
        this.mothercompanyhis = mothercompanyhis;
    }

    public String getMotherjob() {
        return motherjob;
    }

    public void setMotherjob(String motherjob) {
        this.motherjob = motherjob;
    }

    public String getMotherjobhis() {
        return motherjobhis;
    }

    public void setMotherjobhis(String motherjobhis) {
        this.motherjobhis = motherjobhis;
    }

    public String getMothertel() {
        return mothertel;
    }

    public void setMothertel(String mothertel) {
        this.mothertel = mothertel;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getFathercompany() {
        return fathercompany;
    }

    public void setFathercompany(String fathercompany) {
        this.fathercompany = fathercompany;
    }

    public String getFathercompanyhis() {
        return fathercompanyhis;
    }

    public void setFathercompanyhis(String fathercompanyhis) {
        this.fathercompanyhis = fathercompanyhis;
    }

    public String getFatherjob() {
        return fatherjob;
    }

    public void setFatherjob(String fatherjob) {
        this.fatherjob = fatherjob;
    }

    public String getFatherjobhis() {
        return fatherjobhis;
    }

    public void setFatherjobhis(String fatherjobhis) {
        this.fatherjobhis = fatherjobhis;
    }

    public String getFathertel() {
        return fathertel;
    }

    public void setFathertel(String fathertel) {
        this.fathertel = fathertel;
    }

    public String getMastername() {
        return mastername;
    }

    public void setMastername(String mastername) {
        this.mastername = mastername;
    }

    public String getMasternamehis() {
        return masternamehis;
    }

    public void setMasternamehis(String masternamehis) {
        this.masternamehis = masternamehis;
    }

    public String getMastertel() {
        return mastertel;
    }

    public void setMastertel(String mastertel) {
        this.mastertel = mastertel;
    }

    public String getMastertelhis() {
        return mastertelhis;
    }

    public void setMastertelhis(String mastertelhis) {
        this.mastertelhis = mastertelhis;
    }

    public String getSchooltext() {
        return schooltext;
    }

    public void setSchooltext(String schooltext) {
        this.schooltext = schooltext;
    }

    public String getSchooltexthis() {
        return schooltexthis;
    }

    public void setSchooltexthis(String schooltexthis) {
        this.schooltexthis = schooltexthis;
    }

    public String getFamilytext() {
        return familytext;
    }

    public void setFamilytext(String familytext) {
        this.familytext = familytext;
    }

    public String getFamilytexthis() {
        return familytexthis;
    }

    public void setFamilytexthis(String familytexthis) {
        this.familytexthis = familytexthis;
    }

    public String getStudytext() {
        return studytext;
    }

    public void setStudytext(String studytext) {
        this.studytext = studytext;
    }

    public String getStudytexthis() {
        return studytexthis;
    }

    public void setStudytexthis(String studytexthis) {
        this.studytexthis = studytexthis;
    }

    public String getEducationtext() {
        return educationtext;
    }

    public void setEducationtext(String educationtext) {
        this.educationtext = educationtext;
    }

    public String getEducationtexthis() {
        return educationtexthis;
    }

    public void setEducationtexthis(String educationtexthis) {
        this.educationtexthis = educationtexthis;
    }

    public String getSupporttext() {
        return supporttext;
    }

    public void setSupporttext(String supporttext) {
        this.supporttext = supporttext;
    }

    public String getSupporttexthis() {
        return supporttexthis;
    }

    public void setSupporttexthis(String supporttexthis) {
        this.supporttexthis = supporttexthis;
    }

    public String getImprovetext() {
        return improvetext;
    }

    public void setImprovetext(String improvetext) {
        this.improvetext = improvetext;
    }

    public String getImprovetexthis() {
        return improvetexthis;
    }

    public void setImprovetexthis(String improvetexthis) {
        this.improvetexthis = improvetexthis;
    }

    public Integer getGradeid() {
        return gradeid;
    }

    public void setGradeid(Integer gradeid) {
        this.gradeid = gradeid;
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }

    public String getStunamehis() {
        return stunamehis;
    }

    public void setStunamehis(String stunamehis) {
        this.stunamehis = stunamehis;
    }

    public String getStubirthhis() {
        return stubirthhis;
    }

    public void setStubirthhis(String stubirthhis) {
        this.stubirthhis = stubirthhis;
    }

    public String getMajorhis() {
        return majorhis;
    }

    public void setMajorhis(String majorhis) {
        this.majorhis = majorhis;
    }

    public String getStutelhis() {
        return stutelhis;
    }

    public void setStutelhis(String stutelhis) {
        this.stutelhis = stutelhis;
    }

    public String getMothernamehis() {
        return mothernamehis;
    }

    public void setMothernamehis(String mothernamehis) {
        this.mothernamehis = mothernamehis;
    }

    public String getMothertelhis() {
        return mothertelhis;
    }

    public void setMothertelhis(String mothertelhis) {
        this.mothertelhis = mothertelhis;
    }

    public String getFathernamehis() {
        return fathernamehis;
    }

    public void setFathernamehis(String fathernamehis) {
        this.fathernamehis = fathernamehis;
    }

    public String getFathertelhis() {
        return fathertelhis;
    }

    public void setFathertelhis(String fathertelhis) {
        this.fathertelhis = fathertelhis;
    }


    public void setMotherdegree(String motherdegree) {
        this.motherdegree = motherdegree;
    }

    public void setMotherdegreehis(String motherdegreehis) {
        this.motherdegreehis = motherdegreehis;
    }

    public void setFatherdegree(String fatherdegree) {
        this.fatherdegree = fatherdegree;
    }

    public void setFatherdegreehis(String fatherdegreehis) {
        this.fatherdegreehis = fatherdegreehis;
    }

    public String getMotherdegree() {
        return motherdegree;
    }

    public String getMotherdegreehis() {
        return motherdegreehis;
    }

    public String getFatherdegree() {
        return fatherdegree;
    }

    public String getFatherdegreehis() {
        return fatherdegreehis;
    }

    public String getStusex() {
        return stusex;
    }

    public void setStusex(String stusex) {
        this.stusex = stusex;
    }

    public String getStusexhis() {
        return stusexhis;
    }

    public void setStusexhis(String stusexhis) {
        this.stusexhis = stusexhis;
    }

    public String getMasteraddress() {
        return masteraddress;
    }

    public void setMasteraddress(String masteraddress) {
        this.masteraddress = masteraddress;
    }

    public String getMasteraddresshis() {
        return masteraddresshis;
    }

    public void setMasteraddresshis(String masteraddresshis) {
        this.masteraddresshis = masteraddresshis;
    }

    public String getMastersex() {
        return mastersex;
    }

    public void setMastersex(String mastersex) {
        this.mastersex = mastersex;
    }

    public String getMastersexhis() {
        return mastersexhis;
    }

    public void setMastersexhis(String mastersexhis) {
        this.mastersexhis = mastersexhis;
    }

    public String getMasterdegree() {
        return masterdegree;
    }

    public void setMasterdegree(String masterdegree) {
        this.masterdegree = masterdegree;
    }

    public String getMasterdegreehis() {
        return masterdegreehis;
    }

    public void setMasterdegreehis(String masterdegreehis) {
        this.masterdegreehis = masterdegreehis;
    }

    public Date getMasterbirth() {
        return masterbirth;
    }

    public void setMasterbirth(Date masterbirth) {
        this.masterbirth = masterbirth;
    }

    public String getMasterbirthhis() {
        return masterbirthhis;
    }

    public void setMasterbirthhis(String masterbirthhis) {
        this.masterbirthhis = masterbirthhis;
    }

    public Date getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }

    public String getCheckdatehis() {
        return checkdatehis;
    }

    public void setCheckdatehis(String checkdatehis) {
        this.checkdatehis = checkdatehis;
    }
}
