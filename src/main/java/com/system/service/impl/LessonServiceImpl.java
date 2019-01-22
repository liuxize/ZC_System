package com.system.service.impl;

import com.system.mapper.LessonMapper;
import com.system.po.Exam;
import com.system.po.Lesson;
import com.system.po.LessonCustom;
import com.system.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;



@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonMapper lessonMapper;

    public void save(Lesson lesson) throws Exception {
        Date date = new Date();
        lesson.setRecorddate(date);
        lessonMapper.insert(lesson);
    }

    public List<LessonCustom> findByStuID(Integer id) throws Exception {

        return lessonMapper.selectByStuID(id);
    }

    public LessonCustom findByLessonID(Integer id) throws Exception {

        return lessonMapper.selectByLessonID(id);
    }


    public void removeLessonByID(Integer stuID) throws Exception{
        lessonMapper.deleteByStuID(stuID);
    }

    public void removeByLessonID(Integer lessonID) throws Exception{
        lessonMapper.deleteByLessonID(lessonID);
    }

    public void changeLessonSign(Integer stuID) throws Exception{
        lessonMapper.updateLessonSign(stuID);
    }

    public void changeLesson(Lesson lesson) throws Exception{
        lessonMapper.updateLesson(lesson);
    }

    public int getcountLessonByPay(Integer stuid) throws Exception{
       return lessonMapper.countLessonByPay(stuid);
    }
   public List<Lesson> findLessonByPay() throws Exception{
        return lessonMapper.selectLessonByPay();
   }

   public void updateDutyDateByLessonID(String dutydate, Integer lessonid) throws Exception{
        lessonMapper.updateDutyDate(dutydate, lessonid);
   }

   public void updateRecordTeacher(String operator, int lessonid) throws Exception{
        lessonMapper.updateLessonOperator(operator,lessonid);
   }
}
