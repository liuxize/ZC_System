package com.system.po;

import java.util.Date;

public class Lesson {
    private  Integer lessonid;

    private Integer stuid;    //学生ID

    private Date lessonstart;   //上课开始日期

    private Date lessonend;    //上课结束日期

    private  String operator; // 录入人

    private String lecturer;  //上课教师

    private String remark; //   备注

    private String lessontime;  //课时

    private Integer typeid; //课程类型id

    private Integer subjectid; //课程id

    private String schooltime; //每日上课时间

    private String schooldate;  //上课日期

    private Integer lessonsign; //lesson更新的标志

    private Date recorddate;

    private String dutydate;  //出勤日期

    public String getDutydate() {
        return dutydate;
    }

    public void setDutydate(String dutydate) {
        this.dutydate = dutydate;
    }

    public String getSchooldate() {
        return schooldate;
    }

    public void setSchooldate(String schooldate) {
        this.schooldate = schooldate;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getSchooltime() {
        return schooltime;
    }

    public void setSchooltime(String schooltime) {
        this.schooltime = schooltime;
    }

    public Integer getLessonsign() {
        return lessonsign;
    }

    public void setLessonsign(Integer lessonsign) {
        this.lessonsign = lessonsign;
    }

    //getter

    public String getLessontime() {
        return lessontime;
    }

    public String getOperator() {
        return operator;
    }

    public String getRemark() {
        return remark;
    }

    public Integer getStuid() {
        return stuid;
    }

    public Date getLessonend() {
        return lessonend;
    }

    public Date getLessonstart() {
        return lessonstart;
    }

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
//setter


    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public void setLessonend(Date lessonend) {
        this.lessonend = lessonend;
    }

    public void setLessonstart(Date lessonstart) {
        this.lessonstart = lessonstart;
    }

    public void setLessontime(String lessontime) {
        this.lessontime = lessontime;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getLessonid() {
        return lessonid;
    }

    public void setLessonid(Integer lessonid) {
        this.lessonid = lessonid;
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }
}
