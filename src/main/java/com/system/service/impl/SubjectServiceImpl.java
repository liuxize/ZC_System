package com.system.service.impl;

import com.system.mapper.SubjectMapper;
import com.system.po.Subject;
import com.system.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper ;

    public List<Subject> findAllSubject() throws  Exception{
        return subjectMapper.findAll();
    }

    public void saveSubject(String subjectName) throws Exception{
         subjectMapper.insert(subjectName);
    }

    public void deleteSubject(Integer subjectid) throws Exception{
        subjectMapper.deleteByID(subjectid);
    }
}