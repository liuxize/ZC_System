package com.system.mapper;

import com.system.po.PagingVO;
import com.system.po.Stu;
import com.system.po.StuCustom;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StuMapperCustom {

    //根据姓名查找信息
    List<StuCustom> selectByName(String name) throws Exception;

    List<StuCustom> selectByLister(PagingVO pagingVO) throws Exception;

    List<StuCustom> selectByUpdater(PagingVO pagingVO) throws Exception;

    //分页显示检验查询（起始时间，终止时间，专业）
    List<StuCustom> selectByCheck(PagingVO pagingVO) throws Exception;

    //分页显示学生信息
    List<StuCustom> findByPaging(PagingVO pagingVO) throws Exception;

    //分页显示学生信息（根据年级排序）
    List<StuCustom> findByGrade(PagingVO pagingVO) throws Exception;

    List<StuCustom> selectByGrade(PagingVO pagingVO) throws Exception;

    //根据年级搜索全部的学生
    List<StuCustom> selectAllByGrade(Integer gradeid) throws Exception;

    StuCustom selectByPrimaryKey(Integer stuid);

    //搜索日期（分页）
    List<StuCustom> selectByDate(PagingVO pagingVO);

    //搜索日期（全部）
    List<StuCustom> selectAllByDate(Date startdate, Date enddate);

    //生日提醒（提前7天）
    List<StuCustom> selectByBirth();

    List<StuCustom> selectByFatherBirth();

    List<StuCustom> selectByMotherBirth();

    //搜索专业( 全部)
    List<StuCustom> selectAllByMajor(String majorName);

    //搜索专业（分页显示）
    List<StuCustom> selectByMajor(PagingVO pagingVO);

    //搜索学校(全部)
    List<StuCustom> selectAllBySchool(String schoolName);

    //搜索学校（分页显示）
    List<StuCustom> selectBySchool(PagingVO pagingVO);

    //缴费提醒(提前7天)
    List<StuCustom> selectByPay(PagingVO pagingVO);

    //缴费提醒(提前7天全部)
    List<StuCustom> selectAllByPay();

    ////////////////////////已缴费查看//////////////////////////////////////////////////////

    //缴费学员(全部学科 全部类型 全部年级) 分页显示
    List<StuCustom> selectByPayStu(PagingVO pagingVO);

    //缴费学员(全部学科 全部类型 全部年级) 全部显示
    List<StuCustom> selectAllByPayStu();

    // 缴费学员1 （学科 类型）分页显示
    List<StuCustom> selectPayStuBySubAndType(PagingVO pagingVO);

    // 缴费学员1 （学科 类型）全部显示
    List<StuCustom> selectAllPayStuBySubAndType(PagingVO pagingVO);

    //缴费学员2 （年级 类型）分页显示
    List<StuCustom> selectPayStuByGradeAndType(PagingVO pagingVO);

    //缴费学员2 （年级 类型）全部显示
    List<StuCustom> selectAllPayStuByGradeAndType(PagingVO pagingVO);

    //缴费学员3 （年级 学科）分页显示
    List<StuCustom> selectPayStuByGradeAndSub(PagingVO pagingVO);

    //缴费学员3 （年级 学科）全部显示
    List<StuCustom> selectAllPayStuByGradeAndSub(PagingVO pagingVO);

    //缴费学员4 （年级）分页显示
    List<StuCustom> selectPayStuByGrade(PagingVO pagingVO);

    //缴费学员4 （年级）全部显示
    List<StuCustom> selectAllPayStuByGrade(PagingVO pagingVO);

    //缴费学员5 （学科）分页显示
    List<StuCustom> selectPayStuBySub(PagingVO pagingVO);

    //缴费学员5 （学科）全部显示
    List<StuCustom> selectAllPayStuBySub(PagingVO pagingVO);

    //缴费学员6 （类型）分页显示
    List<StuCustom> selectPayStuByType(PagingVO pagingVO);

    //缴费学员6 （类型）全部显示
    List<StuCustom> selectAllPayStuByType(PagingVO pagingVO);

    //缴费学员7 （年级 学科 类型）分页显示
    List<StuCustom> selectPayStuByGradeSubType(PagingVO pagingVO);

    //缴费学员7 （年级 学科 类型）全部显示
    List<StuCustom> selectAllPayStuByGradeSubType(PagingVO pagingVO);


////////////////////////预缴费查看/////////////////////////////////////////////////////////

    //预缴费学员(全部学科 全部类型 全部年级) 分页显示
    List<StuCustom> selectByPrePayStu(PagingVO pagingVO);

    List<StuCustom> selectAllByPrePayStu();

    // 预缴费学员1 （学科 类型）分页显示
    List<StuCustom> selectPrePayStuBySubAndType(PagingVO pagingVO);

    // 预缴费学员1 （学科 类型）全部显示
    List<StuCustom> selectAllPrePayStuBySubAndType(PagingVO pagingVO);

    //预缴费学员2 （年级 类型）分页显示
    List<StuCustom> selectPrePayStuByGradeAndType(PagingVO pagingVO);

    //预缴费学员2 （年级 类型）全部显示
    List<StuCustom> selectAllPrePayStuByGradeAndType(PagingVO pagingVO);

    //预缴费学员3 （年级 学科）分页显示
    List<StuCustom> selectPrePayStuByGradeAndSub(PagingVO pagingVO);

    //预缴费学员3 （年级 学科）全部显示
    List<StuCustom> selectAllPrePayStuByGradeAndSub(PagingVO pagingVO);

    //预缴费学员4 （年级）分页显示
    List<StuCustom> selectPrePayStuByGrade(PagingVO pagingVO);

    //预缴费学员4 （年级）全部显示
    List<StuCustom> selectAllPrePayStuByGrade(PagingVO pagingVO);

    //预缴费学员5 （学科）分页显示
    List<StuCustom> selectPrePayStuBySub(PagingVO pagingVO);

    //预缴费学员5 （学科）全部显示
    List<StuCustom> selectAllPrePayStuBySub(PagingVO pagingVO);

    //缴费学员6 （类型）分页显示
    List<StuCustom> selectPrePayStuByType(PagingVO pagingVO);

    //预缴费学员6 （类型）全部显示
    List<StuCustom> selectAllPrePayStuByType(PagingVO pagingVO);

    //预缴费学员7 （年级 学科 类型）分页显示
    List<StuCustom> selectPrePayStuByGradeSubType(PagingVO pagingVO);

    //预缴费学员7 （年级 学科 类型）全部显示
    List<StuCustom> selectAllPrePayStuByGradeSubType(PagingVO pagingVO);

////////////////////////未缴费查看/////////////////////////////////////////////////////////

    //未缴费学员 （分页 //全部 全部）
    List<StuCustom> selectByUnPayStu(PagingVO pagingVO);

    //全部未缴费学员  （不分页 //全部 全部）
    List<StuCustom> selectAllByUnPayStu();

    //分页 全部 有电话
    List<StuCustom> selectByUnPayStuTel(PagingVO pagingVO);

    //不分页 全部 有电话
    List<StuCustom> selectAllByUnPayStuTel();

    //分页 全部 无电话
    List<StuCustom> selectByUnPayStuNoTel(PagingVO pagingVO);
    //不分页 全部 无有电话
    List<StuCustom> selectAllByUnPayStuNoTel();

    //未缴费学生（分页，分年级  全部）
    List<StuCustom> selectUnPayStuByGrade(PagingVO pagingVO);

    //未缴费学生（全部，分年级  全部）
    List<StuCustom> selectAllUnPayStuByGrade(Integer gradeid);

    //分页 分年级 有电话
    List<StuCustom> selectUnPayStuByGradeTel(PagingVO pagingVO);

    //不分页 分年级 有电话
    List<StuCustom> selectAllUnPayStuByGradeTel(Integer gradeid);

    //分页 分年级 没有电话
    List<StuCustom> selectUnPayStuByGradeNoTel(PagingVO pagingVO);

    //不分页 分年级 没有电话
    List<StuCustom> selectAllUnPayStuByGradeNoTel(Integer gradeid);


//////////////////////////////////////////////////////
    //搜索优秀的学生(分页显示)
    List<StuCustom> selectOutstandStu(PagingVO pagingVO);

    //搜索全部的优秀学生
    List<StuCustom> selectAllOutstandStu();

    /////////////////////////////////////////////////////负责人新录入信息提醒//////////////////////////////////////////
    //负责人新录入信息提醒  <!--新录入信息，根据年纪，已经缴费-->
    List<StuCustom> selectLeaderReceiveByGradePay(Map<String, Object> map);

    //负责人新录入信息提醒  <!--新录入信息，根据年纪，没有缴费-->
    List<StuCustom> selectLeaderReceiveByGradeUnPay(Map<String, Object> map);

    //负责人新录入信息提醒  <!--新录入信息，根据年纪，缴费和未交费-->
    List<StuCustom> selectLeaderReceiveByGrade(Map<String, Object> map);

    /////////////////////////////////////////////////////校长新录入信息提醒//////////////////////////////////////////
    //校长新录入信息提醒  <!--新录入信息，根据年纪，已经缴费-->
    List<StuCustom> selectMasterReceiveByGradePay(Map<String, Object> map);

    //校长新录入信息提醒  <!--新录入信息，根据年纪，没有缴费-->
    List<StuCustom> selectMasterReceiveByGradeUnPay(Map<String, Object> map);

    //校长新录入信息提醒  <!--新录入信息，根据年纪，缴费和未交费-->
    List<StuCustom> selectMasterReceiveByGrade(Map<String, Object> map);

    /////////////////////////////////////////////////////负责人更新信息提醒//////////////////////////////////////////
    //负责人更新信息提醒  <!--更新信息，根据年纪，已经缴费-->
    List<StuCustom> selectLeaderUpdateByGradePay(Map<String, Object> map);

    //负责人更新信息提醒  <!--更新信息，根据年纪，没有缴费-->
    List<StuCustom> selectLeaderUpdateByGradeUnPay(Map<String, Object> map);

    //负责人更新信息提醒  <!--更新信息，根据年纪，缴费和未交费-->
    List<StuCustom> selectLeaderUpdateByGrade(Map<String, Object> map);

    /////////////////////////////////////////////////////校长更新信息提醒//////////////////////////////////////////
    //校长更新信息提醒  <!--更新信息，根据年纪，已经缴费-->
    List<StuCustom> selectMasterUpdateByGradePay(Map<String, Object> map);

    //校长更新信息提醒  <!--更新信息，根据年纪，没有缴费-->
    List<StuCustom> selectMasterUpdateByGradeUnPay(Map<String, Object> map);

    //校长更新信息提醒  <!--更新信息，根据年纪，缴费和未交费-->
    List<StuCustom> selectMasterUpdateByGrade(Map<String, Object> map);


    /////////////////////////////////////////////////////管理员录入信息提醒//////////////////////////////////////////
    List<StuCustom> selectAdminReceive(PagingVO pagingVO);

    /////////////////////////////////////////////////////管理员更新信息提醒//////////////////////////////////////////
    List<StuCustom> selectAdminUpdate(PagingVO pagingVO);

    //选出姓名相同的信息
    List<StuCustom> selectSameName(PagingVO pagingVO);
}
