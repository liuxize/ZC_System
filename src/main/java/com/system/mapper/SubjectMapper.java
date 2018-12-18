package com.system.mapper;

import com.system.po.Subject;

import java.util.List;

public interface SubjectMapper {
    List<Subject> findAll();

    int insert(String subjectName);

    int deleteByID(Integer id);
}
