package com.system.service.impl;

import com.system.mapper.GradeMapper;
import com.system.po.Grade;
import com.system.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    public List<Grade> findAllGrade() throws Exception {
        return gradeMapper.findAll();
    }

    public List<Grade> findAllGradeByDesc() throws Exception {
        return gradeMapper.findAllByDesc();
    }

    public void saveGrade (String gradeName) throws Exception{
        gradeMapper.insert(gradeName);
    }

    public void deleteGrade (Integer gradeID) throws Exception{
        gradeMapper.deleteByID(gradeID);
    }
}