package com.system.mapper;

import com.system.po.Grade;

import java.util.List;

public interface GradeMapper {

    List<Grade> findAll() throws Exception;
    List<Grade> findAllByDesc() throws Exception;

    int insert(String name);
    int deleteByID(Integer id);

}
