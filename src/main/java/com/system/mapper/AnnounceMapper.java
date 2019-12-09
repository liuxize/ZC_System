package com.system.mapper;


import com.system.po.Announce;

import java.util.List;

public interface AnnounceMapper {
    int insert(Announce announce);  //插入公告
    int deleteByID(Integer id);     //删除
    List<Announce> selectAll ();
    List<Announce> selectAllRead ();
    void updateIsread(int id, int isread); //更新状态
    void updateAnnounce(Announce announce); //
    String findConByID(Integer id);

}
