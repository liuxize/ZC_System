package com.system.service;

import com.system.po.Campus;

import java.util.List;

public interface CampusService {
    List<Campus> findAllCampus() throws Exception;

    List<Campus> findAllCampusByAuth(String campusname) throws Exception;

    void saveCampus (String campusname) throws Exception;

    void deleteCampus (Integer campusid) throws Exception;
}


