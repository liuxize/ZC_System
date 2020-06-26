package com.system.mapper;
import com.system.po.PagingVO;
import com.system.po.Stu;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StuMapper {

    int insert(Stu record);

    void addStuNameByPrimaryKey(Integer stuid, String str);

    void addStuBirthByPrimaryKey(Integer stuid, Date date, String str);

    void addMotherBirthByPrimaryKey(Integer stuid, Date date, String str);

    void addFatherBirthByPrimaryKey(Integer stuid, Date date, String str);

    void addMajorByPrimaryKey(Integer stuid, String str);

    void addSchoolByPrimaryKey(Integer stuid, String str);  //学校

    void addStuTelByPrimaryKey(Integer stuid, String str);

    void addMotherNameByPrimaryKey(Integer stuid, String str);

    void addMoCompanyByPrimaryKey(Integer stuid, String str);  //母亲公司

    void addMoJobByPrimaryKey(Integer stuid, String str);  //母亲职位

    void addMoTelByPrimaryKey(Integer stuid, String str);

    void addFaNameByPrimaryKey(Integer stuid, String str);

    void addFaCompanyByPrimaryKey(Integer stuid, String str);  //父亲公司

    void addFaJobByPrimaryKey(Integer stuid, String strb);  //父亲职位

    void addFaTelByPrimaryKey(Integer stuid, String strb);

    void addAddressByPrimaryKey(Integer stuid, String str);  //家庭地址

    void addMasterByPrimaryKey(Integer stuid, String str);   //教师

    void addMasterTelByPrimaryKey(Integer stuid, String str);  //教师电话

    void addStuSexByPrimaryKey(Integer stuid, String str); //学生性别

    void addMasterAddressByPrimaryKey(Integer stuid, String str);

    void addMasterDegreeByPrimaryKey(Integer stuid, String str);

    void addMasterSexByPrimaryKey(Integer stuid, String str);

    void addMasterBirthByPrimaryKey(Integer stuid, Date date, String str);

    void addCheckDateByPrimaryKey(Integer stuid, Date date, String str);

    void addSchoolTextByPrimaryKey(Integer stuid, String str);

    void addFamilyTextByPrimaryKey(Integer stuid, String str);

    void addStudyTextByPrimaryKey(Integer stuid, String str);

    void addEducationTextByPrimaryKey(Integer stuid, String str);

    void addSupportTextByPrimaryKey(Integer stuid, String str);

    void addImproveTextByPrimaryKey(Integer stuid, String str);

    void addMoDegreeByPrimaryKey(Integer stuid, String str);

    void addFaDegreeByPrimaryKey(Integer stuid, String str);

    Date selectBirthByPrimaryKey(Integer stuid);

    Date selectFaBirthByPrimaryKey(Integer stuid);

    Date selectMoBirthByPrimaryKey(Integer stuid);

    Date selectMasterBirthByPrimaryKey(Integer stuid);

    Date selectCheckDateByPrimaryKey(Integer stuid);

    int countStu() throws Exception;

    int countStuByGrade(Integer gradeid) throws Exception;

    int countStuByDate(Date startdate, Date enddate) throws Exception;

    int countStuByBirth() throws Exception;

    int countStuByPay() throws Exception;

    int countStuByPayAndGrade(Integer gradeid) throws Exception;

    int countStuBySchool (String schoolName);

    int countStuByMajor (String majorName);

    ///////////////////////////根据年级和电话情况统计未缴费学生数量//////////////////////////////////////




    //未缴费学员 （分页 //全部 全部）
    int countStuByUnPayStu() throws Exception;

    //分页 全部 有电话
    int countStuByUnPayStuTel() throws Exception;

    //分页 全部 无电话
    int countStuByUnPayStuNoTel() throws Exception;

    //未缴费学生（分年级  全部）
    int countUnPayStuByGrade(Integer gradeid) throws Exception;

    //分页 分年级 有电话
    int countUnPayStuByGradeTel(Integer gradeid) throws Exception;

    //分页 分年级 没有电话
    int countUnPayStuByGradeNoTel(Integer gradeid);

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //删除学生信息
    int deleteByStuID (Integer stuID);

    //修改学生信息
    void updateByStuID(Stu stu);

    //签字后将新的信息与历史信息合并
    int updataStuHis(Integer stuID);

    //更新年级信息+1
    int updataGradeID();

    //更新年级信息-1 ???
    int updataGradeDec();

    //已缴费学生的数量  （全部年级，全部学科 全部类型）
    int countStuByPayStu(Integer campusid);
    //已缴费学生的数量  （学科 类型）
    int countPayBySubAndType(Integer subjectid, Integer typeid, Integer campusid);
    //已缴费学生的数量 （年级 类型）
    int countPayByGradeAndType( Integer gradeid, Integer typeid, Integer campusid);
    //已缴费学生的数量（年级 学科）
    int countPayByGradeAndSub(Integer gradeid,Integer subjectid, Integer campusid);
    //已缴费学生的数量（年级）
    int countPayByGrade(Integer gradeid,  Integer campusid);
    //已缴费学生的数量（学科）
    int countPayBySub(Integer subjectid,  Integer campusid);
    //已缴费学生的数量（类型）
    int countPayByType(Integer typeid,  Integer campusid);
    //已缴费学生的数量（年级 学科 类型）
    int countPayByGradeSubType(Integer gradeid, Integer subjectid, Integer typeid, Integer campusid);


    //已缴费学生的数量  （全部年级，全部学科 全部类型）
    int countPreStuByPayStu();
    //预缴费学生的数量  （学科 类型）
    int countPrePayBySubAndType(Integer subjectid, Integer typeid);
    //预缴费学生的数量 （年级 类型）
    int countPrePayByGradeAndType( Integer gradeid, Integer typeid);
    //预缴费学生的数量（年级 学科）
    int countPrePayByGradeAndSub(Integer gradeid,Integer subjectid);
    //预缴费学生的数量（年级）
    int countPrePayByGrade(Integer gradeid);
    //预缴费学生的数量（学科）
    int countPrePayBySub(Integer subjectid);
    //预缴费学生的数量（类型）
    int countPrePayByType(Integer typeid);
    //预缴费学生的数量（年级 学科 类型）
    int countPrePayByGradeSubType(Integer gradeid, Integer subjectid, Integer typeid);


    //统计优质学员的数量
    int countOutstandStu();

    List<Stu> selectStuTel(String tel);

    List<Stu> selectMotherTel(String tel);

    List<Stu> selectFatherTel(String tel);

    int countLister(String name, Date startdate, Date enddate);

    int countUpdater(String name, Date startdate, Date enddate);

    int countCheck(String majorName, Date startdate, Date enddate);

    //负责人新录入信息提醒  <!--新录入信息，根据年纪，已经缴费-->
    int countLeaderReceiveByGradePay ( List<Integer> gradeList);

    //负责人新录入信息提醒  <!--新录入信息，根据年纪，没有缴费-->
    int countLeaderReceiveByGradeUnPay( List<Integer> gradeList);

    //负责人新录入信息提醒  <!--新录入信息，根据年纪，缴费和未交费-->
    int countLeaderReceiveByGrade( List<Integer> gradeList);

    //校长新录入信息提醒  <!--新录入信息，根据年纪，已经缴费-->
    int countMasterReceiveByGradePay ( List<Integer> gradeList);

    //校长新录入信息提醒  <!--新录入信息，根据年纪，没有缴费-->
    int countMasterReceiveByGradeUnPay( List<Integer> gradeList);

    //校长新录入信息提醒  <!--新录入信息，根据年纪，缴费和未交费-->
    int countMasterReceiveByGrade( List<Integer> gradeList);

    //负责人更新信息提醒  <!--更新信息，根据年纪，已经缴费-->
    int countLeaderUpdateByGradePay( List<Integer> gradeList);

    //负责人更新信息提醒  <!--更新信息，根据年纪，没有缴费-->
    int countLeaderUpdateByGradeUnPay( List<Integer> gradeList);

    //负责人更新信息提醒  <!--更新信息，根据年纪，缴费和未交费-->
    int countLeaderUpdateByGrade( List<Integer> gradeList);

    //校长更新信息提醒  <!--更新信息，根据年纪，已经缴费-->
    int countMasterUpdateByGradePay( List<Integer> gradeList);

    //校长更新信息提醒  <!--更新信息，根据年纪，没有缴费-->
    int countMasterUpdateByGradeUnPay( List<Integer> gradeList);

    //校长更新信息提醒  <!--更新信息，根据年纪，缴费和未交费-->
    int countMasterUpdateByGrade( List<Integer> gradeList);

    //统计姓名相同学生的人数
    int countStuBySameName();

    //获取学生的录入人
    String selectRecordPersonByIndex(Integer stuid);

    //更新录入人信息
    void updateRecordPersonByIndex(Integer stuid, String name);


}
