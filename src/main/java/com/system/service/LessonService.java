package com.system.service;

import com.system.po.Lesson;
import com.system.po.LessonCustom;

import java.util.List;

public interface LessonService {

    void save(Lesson lesson) throws Exception;

    List<LessonCustom> findByStuID(Integer id) throws Exception;

    LessonCustom findByLessonID(Integer id) throws Exception;

    void removeLessonByID(Integer stuID) throws Exception;  //根据学生的id

    void removeByLessonID(Integer lessonID) throws Exception;  //根据学生的id

    void changeLessonSign(Integer stuID) throws Exception;

    void changeLesson(Lesson lesson) throws Exception;

    int getcountLessonByPay(Integer stuid) throws Exception;

    List<Lesson> findLessonByPay() throws Exception;

    void updateDutyDateByLessonID(String dutydate, Integer lessonid) throws Exception;

}
