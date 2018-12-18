package com.system.service;

import com.system.po.Exam;

import java.util.List;

public interface ExamService {
    void save (Exam exam) throws Exception;

    List<Exam> findExamByStuID(Integer id) throws Exception;

    void removeExamByID(Integer stuID) throws Exception;

    void removeByExamID(Integer examID) throws Exception;

    void changeExamSign(Integer stuid) throws Exception;

    void editExam(Exam exam) throws Exception;

    Exam findExamByExamID(Integer examid) throws Exception;
}
