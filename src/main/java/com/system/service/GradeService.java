package com.system.service;

import com.system.po.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> findAllGrade() throws Exception;
    List<Grade> findAllGradeByDesc() throws Exception;

    void saveGrade (String gradeName) throws Exception;

    void deleteGrade (Integer gradeID) throws Exception;
}
