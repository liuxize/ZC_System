package com.system.mapper;

import java.util.List;

public interface CampusAuthMapper {
    int insert(String name, Integer campusid);
    int deleteByName(String username);
    List<Integer> selectCampusIdByName(String username);
}
