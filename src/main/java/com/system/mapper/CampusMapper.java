package com.system.mapper;

import com.system.po.Campus;

import java.util.List;

public interface CampusMapper {
    List<Campus> findAll() throws Exception;
    int insert(String name);
    int deleteByID(Integer id);
    List<Campus> findAllByAuth(String name) throws Exception;

}


