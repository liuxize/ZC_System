package com.system.service;

import com.system.po.School;

import java.util.List;

public interface SchoolService {

    List<School> findAllSchool() throws Exception;

    void saveSchool (String schoolname) throws Exception;

    void deleteSchool (Integer schoolid) throws Exception;
}


