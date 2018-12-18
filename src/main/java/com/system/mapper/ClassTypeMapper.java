package com.system.mapper;

import com.system.po.ClassType;

import java.util.List;

public interface ClassTypeMapper {
    List<ClassType> findAll() throws Exception;

    int insert(String name);

    int deleteByID(Integer id);

}
