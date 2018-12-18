package com.system.service.impl;

import com.system.mapper.ClassTypeMapper;
import com.system.po.ClassType;
import com.system.service.ClassTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassTypeServiceImpl implements ClassTypeService {

    @Autowired
    private ClassTypeMapper classTypeMapper;

    public List<ClassType> findAllClassType() throws  Exception{
        return  classTypeMapper.findAll();
    }

    public void saveClassType (String classtype) throws Exception{
        classTypeMapper.insert(classtype);
    }

    public void deleteClassType (Integer typeid) throws Exception{
        classTypeMapper.deleteByID(typeid);
    }

}