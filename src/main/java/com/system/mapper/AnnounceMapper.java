package com.system.mapper;


import com.system.po.Announce;
import com.system.po.AnnounceCustom;

import java.util.List;

public interface AnnounceMapper {
    int insert(AnnounceCustom announceCustom);  //插入公告
    int deleteByID(Integer id);     //删除
    List<AnnounceCustom> selectAll ();
    List<Announce> selectAllRead ();
    void updateIsread(int id, int isread); //更新状态
    void updateAnnounce(AnnounceCustom announce); //
    String findConByID(Integer id);

}