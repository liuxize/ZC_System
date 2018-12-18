package com.system.service;

import com.system.po.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> findAllGrade() throws Exception;
}
