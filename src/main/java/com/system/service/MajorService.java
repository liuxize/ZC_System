package com.system.service;

import com.system.po.Lesson;
import com.system.po.Major;

import java.util.List;

public interface MajorService {

    List<Major> findAllMajor() throws Exception;

    void saveMajor (String majorname) throws Exception;

    void deleteMajor (Integer majorid) throws Exception;
}
