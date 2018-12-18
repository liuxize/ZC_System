package com.system.service.impl;


import com.system.mapper.ExamMapper;
import com.system.po.Exam;
import com.system.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    public void save(Exam exam) throws Exception {

        Date date = new Date();
        exam.setRecorddate(date);
        examMapper.insert(exam);
    }


    public List<Exam> findExamByStuID(Integer id) throws Exception {

        return examMapper.selectExamByStuID(id);
    }

    public void removeExamByID(Integer stuID) throws Exception{
        examMapper.deleteByStuID(stuID);
    }

    public void removeByExamID(Integer examID) throws Exception{
        examMapper.deleteByExamID(examID);
    }

    public void changeExamSign(Integer stuid) throws Exception{
        examMapper.updateExamSign(stuid);
    }
    public void editExam(Exam exam) throws Exception{
        examMapper.updateExam(exam);
    }
    public Exam findExamByExamID(Integer examid) throws Exception{
        return examMapper.selectExamByExamID(examid);
    }
}
