package com.system.mapper;

import com.system.po.Exam;

import java.util.List;

public interface ExamMapper {

    int insert(Exam exam);

    List<Exam> selectExamByStuID(Integer stuid);

    int deleteByStuID (Integer stuID);

    int deleteByExamID (Integer examID);

    //将examsign更新为1，表示已经确认
    void updateExamSign(Integer stuid);

    void updateExam(Exam exam);

    Exam selectExamByExamID(Integer examid);
}
