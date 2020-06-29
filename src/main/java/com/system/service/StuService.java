package com.system.service;
import com.system.po.*;

import java.util.Date;
import java.util.List;

public interface StuService {

    //保存表一(新建表)
    void save(Stu stu) throws Exception;
    //更新表
    void update(Stu stu) throws  Exception;

    StuCustom findById(Integer id) throws Exception;

    void addStuNameByID(Integer id, String str) throws Exception;

    void addStuBirthByID(Integer id, Date date, String str) throws Exception;

    void addMotherBirthByID(Integer id, Date date, String str) throws Exception;

    void addFatherBirthByID(Integer id, Date date, String str) throws Exception;

    void addMajorByID(Integer id, String str) throws Exception;

    void addSchoolByID(Integer id, String str) throws Exception;

    void addStuTelByID(Integer id, String str) throws Exception;

    void addMotherNameByID (Integer id, String str) throws Exception;

    void addMoCompanyByID (Integer id, String str) throws Exception;

    void addMoJobByID (Integer id, String str) throws Exception;

    void addMoTelByID (Integer id, String str) throws Exception;

    void addFaNameByID (Integer id, String str) throws Exception;

    void addFaCompanyByID (Integer id, String str) throws Exception;

    void addFaJobByID (Integer id, String str) throws Exception;

    void addFaTelByID (Integer id, String str) throws Exception;

    void addAddressByID (Integer id, String str) throws Exception;

    void addMasterByID (Integer id, String str) throws Exception;

    void addMasterTelByID (Integer id, String str) throws Exception;

    void addSchoolTextByID (Integer id, String str) throws Exception;

    void addFamilyTextByID (Integer id, String str) throws Exception;

    void addStudyTextByID (Integer id, String str) throws Exception;

    void addEducationTextByID (Integer id, String str) throws Exception;

    void addSupportTextByID (Integer id, String str) throws Exception;

    void addImproveTextByID (Integer id, String str) throws Exception;

    void addMotherDegreeByID (Integer id, String str) throws Exception;

    void addFatherDegreeByID (Integer id, String str) throws Exception;

    void addStuSexByID(Integer id, String str) throws Exception;

    void addMasterAddressByID(Integer id, String str) throws Exception;

    void addMasterDegreeByID(Integer id, String str) throws Exception;

    void addMasterSexByID(Integer id, String str) throws Exception;

    void addMasterBirthByID(Integer stuid, Date date, String str) throws Exception;

    void addCheckDateByID(Integer stuid, Date date, String str) throws Exception;



    //获取用户的生日
    Date getBirthByID(Integer id) throws Exception;

    //获取母亲的生日
    Date getMoBirthByID(Integer id) throws Exception;

    //获取父亲的生日
    Date getFaBirthByID(Integer id) throws Exception;
    //获取班主任生日
    Date getMasterBirthByID(Integer id) throws Exception;

    //获取检验日期
    Date getCheckDateByID(Integer id) throws Exception;

    List<StuCustom> findByPaging(Integer toPageNo) throws Exception;

    List<StuCustom> findOrderByGrade(Integer toPageNo) throws Exception;

    int getCountStudent() throws Exception;

    int getCountByGrade(Integer gradeid) throws Exception;

    int getCountByDate(Date startdate, Date enddate) throws Exception;

    int getCountByBirth() throws Exception;

    int getCountByPay() throws Exception;

    int getCountByPayAndGrade(Integer gradeid) throws Exception;

    int getCountByPayStu(Integer campusid) throws Exception;



    int getCountBySchool(String schoolName) throws Exception;

    int getCountByMajor(String majorName) throws Exception;

    int getCountBySameStu() throws Exception;
    //根据姓名查找用户
    List<StuCustom> findByName(String name) throws Exception;
    //根据姓名查找用户 学校，年级权限
    List<StuCustom> findByNameAuth(String stuName, String teacherName) throws Exception;


    //查询某个年级的所有学生
    List<StuCustom> findStuByGrade(Integer toPageNo, Integer gradeid) throws Exception;

    List<StuCustom> findAllStuByGrade(Integer gradeid) throws Exception;

    //查找日期（分页）
    List<StuCustom> findStuByDate(Integer toPageNo, Date startdate, Date enddate) throws Exception;
    //查找日期（全部）
    List<StuCustom> findAllStuByDate(Date startdate, Date enddate) throws Exception;
    //查找学生生日
    List<StuCustom> findStuByBirth() throws Exception;
    //查找父亲生日
    List<StuCustom> findStuByFaBirth() throws Exception;
    //查找母亲生日
    List<StuCustom> findStuByMoBirth() throws Exception;
    //查询专业
    List<StuCustom> findAllStuByMajor(String majorName) throws Exception;

    //查询专业(分页显示)
    List<StuCustom> findStuByMajor(Integer toPageNo, String majorName) throws Exception;

    //查询学校
    List<StuCustom> findAllStuBySchool(String schoolName) throws Exception;
    //查询学校（分页显示）
    List<StuCustom> findStuBySchool (Integer toPageNo, String schoolName) throws Exception;

    //查询姓名相同的学生(分页)
    List<StuCustom> findStuBySameName (Integer toPageNo) throws Exception;

    //缴费提醒
    List<StuCustom> findStuByPay(Integer toPageNo) throws Exception;
    //缴费提醒
    List<StuCustom> findAllStuByPay() throws Exception;

    //根据选项查找已经缴费的学生
    List<LessonCustom> findStuByPayStuAndSelect(Integer toPageNo, Integer gradeid, Integer subjectid, Integer typeid, Integer campusid) throws Exception;
    List<LessonCustom> findAllStuByPayStuAndSelect(Integer gradeid, Integer subjectid, Integer typeid, Integer campusid) throws Exception;
    int countPayStuBySelect(Integer gradeid, Integer subjectid, Integer typeid, Integer campusid) throws  Exception;
    //根据老师的权限,选项查找已经缴费的学生
    List<LessonCustom> findStuByPayStuAndSelectAuth(Integer toPageNo, Integer gradeid, Integer subjectid, Integer typeid, Integer campusid, String name) throws Exception;
    int countPayStuBySelectAuth(Integer gradeid, Integer subjectid, Integer typeid, Integer campusid, String name) throws  Exception;




    //根据选项查找预缴费学生
    List<LessonCustom> findStuByPrePayStuAndSelect(Integer toPageNo, Integer gradeid, Integer subjectid, Integer typeid, Integer campusid) throws Exception;
    List<LessonCustom> findAllStuByPrePayStuAndSelect(Integer gradeid, Integer subjectid, Integer typeid, Integer campusid) throws Exception;
    int countPrePayStuBySelect(Integer gradeid, Integer subjectid, Integer typeid, Integer campusid) throws Exception;

    //根据老师的权限,选项查找预缴费学生
    List<LessonCustom> findStuByPrePayStuAndSelectAuth(Integer toPageNo, Integer gradeid, Integer subjectid, Integer typeid, Integer campusid, String name) throws Exception;
    int countPrePayStuBySelectAuth(Integer gradeid, Integer subjectid, Integer typeid, Integer campusid,  String name) throws Exception;


    //未交费学员（未分页）完成
    List<StuCustom> findAllStuByUnPayStu(Integer gradeid, Integer teleType, Integer campusid) throws Exception;

    //未交费学员（分页） 完成
    List<StuCustom> findStuByUnPayStu(Integer toPageNo, Integer gradeid, Integer teleType, Integer campusid) throws Exception;
    // 完成
    int getCountByUnPayStu(Integer gradeid, Integer teleType, Integer campusid) throws Exception;

    //根据年级权限查看未缴费
    int getCountByUnPayStuAuth(Integer gradeid, Integer teleType, Integer campusid, String name) throws Exception;
    List<StuCustom> findStuByUnPayStuAuth(Integer toPageNo, Integer gradeid, Integer teleType, Integer campusid, String name) throws Exception;


    void removeStuByID(Integer stuID) throws Exception;

    void updataStuHisByID(Integer stuid) throws Exception;

    //更新学生的年级+1
    void updateStuGrade() throws Exception;

    //更新学生的年级-1
    void updateStuGradeDec() throws Exception;


    //    获取优质学生信息(分页)
     List<StuCustom> findOutStandStu(Integer toPageNo) throws Exception;

     List<StuCustom> findAllOutStandStu() throws Exception;
     //统计优质学员的人数
    int getCountByOutstand() throws Exception;

    //根据学生电话搜索用户
    List<Stu> findStuByStuTel(String stutel) throws Exception;

    List<Stu> findStuByMoTel(String stutel) throws Exception;

    List<Stu> findStuByFaTel(String stutel) throws Exception;

    //查找制作表的人
    List<StuCustom> findLister(Integer toPageNo, String name, Date startdate, Date enddate) throws Exception;

    int getCountLister(String name, Date startdate, Date enddate) throws Exception;

    //查找更新表的人
    List<StuCustom> findUpdater(Integer toPageNo,String name, Date startdate, Date enddate) throws Exception;

    int getCountUpdater(String name, Date startdate, Date enddate) throws Exception;

    //检验查询（起始时间，终止时间，专业）
    List<StuCustom> findCheck(Integer toPageNo, String majorName, Date startdate, Date enddate) throws Exception;

    int getCountCheck(String majorName, Date startdate, Date enddate) throws Exception;

    //负责人新录入信息提醒
    List<StuCustom> leaderReceiveRemind(Integer toPageNo, Integer permission, List<Integer> gradeList) throws Exception;

    int countLeaderReceiveRemind(Integer permission, List<Integer> gradeList) throws Exception;

    //校长新录入信息提醒
    List<StuCustom> masterReceiveRemind(Integer toPageNo, Integer permission, List<Integer> gradeList) throws Exception;

    int countMasterReceiveRemind(Integer permission, List<Integer> gradeList) throws Exception;

    //负责人更新信息提醒
    List<StuCustom> leaderUpdateRemind(Integer toPageNo, Integer permission, List<Integer> gradeList) throws Exception;

    int countLeaderUpdateRemind(Integer permission, List<Integer> gradeList) throws Exception;

    //校长更新信息提醒
    List<StuCustom> masterUpdateRemind(Integer toPageNo, Integer permission, List<Integer> gradeList) throws Exception;

    int countMasterUpdateRemind(Integer permission, List<Integer> gradeList) throws Exception;
    /////////////////////////////////////////////////////管理员新录入信息提醒//////////////////////////////////////////
    List<StuCustom> adminReceiveRemind(Integer toPageNo) throws Exception;
    List<StuCustom> adminReceiveRemindUnsign(Integer toPageNo) throws Exception;
    /////////////////////////////////////////////////////管理员更新信息提醒//////////////////////////////////////////
    List<StuCustom> adminUpdateRemind(Integer toPageNo) throws Exception;
    List<StuCustom> adminUpdateRemindUnsign(Integer toPageNo) throws Exception;

    //查找录入人
    String findRecordPerson(Integer stuid) throws Exception;

    //更新录入人
    void updateRecordPerson(Integer stuid, String name) throws Exception;
}
