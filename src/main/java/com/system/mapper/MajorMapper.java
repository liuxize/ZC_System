package com.system.mapper;

import com.system.po.Major;

import java.util.List;

public interface MajorMapper {
    List<Major> findAll() throws Exception;

    int insert(String name);

    int deleteByID(Integer id);

}
