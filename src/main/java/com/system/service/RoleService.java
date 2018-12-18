package com.system.service;

import com.system.po.Role;

import java.util.List;

/**
 *  Role 权限表Service层
 */
public interface RoleService {

    Role findByid(Integer id) throws Exception;
    List<Role> findAll() throws Exception;

}
