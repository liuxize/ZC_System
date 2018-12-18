package com.system.mapper;

import com.system.po.NoteTable;
import com.system.po.UserloginCustom;

import java.util.List;
import com.system.po.PagingVO;

/**
 *  UserloginMapper扩展类
 */
public interface UserloginMapperCustom {

    UserloginCustom findOneByName(String name) throws Exception;

    List<UserloginCustom> findByPaging(PagingVO pagingVO) throws Exception;

}
