package com.system.mapper;


import com.system.po.Announce;
import com.system.po.AnnounceCustom;

import java.util.List;

public interface AnnounceMapper {
    int insert(Announce announce);  //插入公告
    int deleteByID(Integer id);     //删除
    List<AnnounceCustom> selectAll ();
    List<Announce> selectAllRead ();
    List<Announce> selectByAuth (String teachername); // 根据校区权限查看公告
    void updateIsread(int id, int isread); //更新状态
    void updateAnnounce(Announce announce); //
    String findConByID(Integer id);

}
