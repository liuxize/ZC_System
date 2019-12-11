package com.system.service;

import com.system.po.Announce;
import com.system.po.AnnounceCustom;

import java.util.List;

public interface AnnounceService {
    List<AnnounceCustom> findAll() throws Exception;
    List<Announce> findALlRead() throws Exception;
    void saveAnnounce(Announce announce) throws Exception;
    void deleteAnnounce(Integer id) throws Exception;
    void editAnnounce(Announce announce) throws Exception;
    void editIsRead(int id, int isread) throws Exception;
    String getConByID(Integer id) throws Exception;
}
