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
}