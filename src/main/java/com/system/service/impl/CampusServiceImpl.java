package com.system.service.impl;

import com.system.mapper.CampusMapper;
import com.system.po.Campus;
import com.system.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CampusServiceImpl implements CampusService {
    @Autowired
    private CampusMapper  campusMapper;

    public List<Campus> findAllCampus() throws Exception{
        return campusMapper.findAll();
    }

    public List<Campus> findAllCampusByAuth(String teachername) throws Exception{
        return campusMapper.findAllByAuth(teachername);
    }

    public void saveCampus (String campusname) throws Exception{
        campusMapper.insert(campusname);
    }

    public void deleteCampus (Integer campusid) throws Exception{
        campusMapper.deleteByID(campusid);
    }
}
