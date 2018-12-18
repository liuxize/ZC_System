package com.system.mapper;
import com.system.po.School;

import java.util.List;

public interface SchoolMapper {
    List<School> findAll() throws Exception;
    int insert(String name);
    int deleteByID(Integer id);
}

