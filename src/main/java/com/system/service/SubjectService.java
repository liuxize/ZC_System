package com.system.service;

import com.system.po.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> findAllSubject() throws Exception;

    void saveSubject(String subjectName) throws Exception;

    void deleteSubject(Integer subjectid) throws Exception;
}
