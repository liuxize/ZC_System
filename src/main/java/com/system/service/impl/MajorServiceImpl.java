package com.system.service.impl;

import com.system.mapper.MajorMapper;
import com.system.po.ClassType;
import com.system.po.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.system.service.MajorService;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper majorMapper;

    public List<Major> findAllMajor() throws Exception{
        return majorMapper.findAll();
    }

    public void saveMajor (String majorname) throws Exception{
        majorMapper.insert(majorname);
    }

    public void deleteMajor (Integer majorid) throws Exception{
        majorMapper.deleteByID(majorid);
    }

}




