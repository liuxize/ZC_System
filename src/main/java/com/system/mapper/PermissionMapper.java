package com.system.mapper;

import java.util.List;

public interface PermissionMapper {
    int insert(String name, Integer gradeid);
    int deleteByName(String username);
    int countByName(String username);
    List<Integer> selectGradeIdByName(String username);
    int countLeaderByGradeID(Integer gradeid);
    int countLeaderByGradeIDAndCampusid(Integer gradeid, Integer campusid);
}
