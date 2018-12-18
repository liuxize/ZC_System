package com.system.service.impl;


import com.system.mapper.SchoolMapper;
import com.system.po.School;
import com.system.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;

    public List<School> findAllSchool() throws Exception{
        return schoolMapper.findAll();
    }

   public void saveSchool (String schoolname) throws Exception{
        schoolMapper.insert(schoolname);
    }

    public void deleteSchool (Integer schoolid) throws Exception{
        schoolMapper.deleteByID(schoolid);
    }
}
