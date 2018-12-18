package com.system.service;

import com.system.po.ClassType;

import java.util.List;

public interface ClassTypeService {
    List<ClassType> findAllClassType() throws Exception;

    void saveClassType (String classtype) throws Exception;

    void deleteClassType (Integer typeid) throws Exception;
}

