package com.system.mapper;

import com.system.po.Lesson;
import com.system.po.LessonCustom;

import java.util.List;

public interface LessonMapper {

    int insert(Lesson lesson);

    int deleteByStuID (Integer stuID);

    int deleteByLessonID (Integer lessonID);

    void updateLessonSign(Integer stuid);  //lessonsign设置为1

    void updateDutyDate(String dutydate, Integer lessonid); //修改签到日期

    List<LessonCustom> selectByStuID(Integer stuid);

    LessonCustom selectByLessonID(Integer lessonID);

    void updateLesson(Lesson lesson);

    //计算学生缴费的科目数量，用于判断学生是否缴费
    int countLessonByPay(Integer stuid);

    // 缴费科目提醒 提前7天提醒
    List<Lesson> selectLessonByPay();

}
