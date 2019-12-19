package com.system.service.impl;

import com.system.mapper.AnnounceMapper;
import com.system.po.Announce;
import com.system.po.AnnounceCustom;
import com.system.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnounceServiceImpl implements AnnounceService {
    @Autowired
    private AnnounceMapper announceMapper;

    public List<AnnounceCustom> findAll() throws Exception{
        return announceMapper.selectAll();
    }
    public List<Announce> findALlRead() throws Exception{
        return announceMapper.selectAllRead();
    }
    public void saveAnnounce(AnnounceCustom announceCustom) throws Exception{
        announceMapper.insert(announceCustom);
    }
    public void deleteAnnounce(Integer id) throws Exception{
        announceMapper.deleteByID(id);
    }
    public void editAnnounce(AnnounceCustom announceCustom) throws Exception{
        announceMapper.updateAnnounce(announceCustom);
    }
    public void editIsRead(int id, int isread) throws Exception{
        announceMapper.updateIsread(id, isread);
    }
    public String getConByID(Integer id) throws Exception{
        return announceMapper.findConByID(id);
    }
}





