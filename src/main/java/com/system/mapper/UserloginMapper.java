package com.system.mapper;

import com.system.po.Loginlog;
import com.system.po.PagingVO;
import com.system.po.Userlogin;
import com.system.po.UserloginExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserloginMapper {
    int countByExample(UserloginExample example);

    int deleteByExample(UserloginExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Userlogin record);

    int insertLoginLog(Loginlog loginlog);
    int deleteLoginLog();
    List<Loginlog> selectAllLoginLog ();
    List<Loginlog> selectAllLoginLogByPage (PagingVO pagingVO);
    int getCountLoginLog();

    int insertSelective(Userlogin record);

    List<Userlogin> selectByExample(UserloginExample example);

    Userlogin selectByPrimaryKey(Integer userid);

    String findNoteByName(String username);

    int updataNoteByName(String username, String notetext);

    int updataPasswordByName(String username, String password);

    int updateByExampleSelective(@Param("record") Userlogin record, @Param("example") UserloginExample example);

    int updateByExample(@Param("record") Userlogin record, @Param("example") UserloginExample example);

    int updateByPrimaryKeySelective(Userlogin record);

    int updateByPrimaryKey(Userlogin record);

    //删除用户
    int deleteByUserName(String username);

    //提前7天提醒
    List<Userlogin> selectByUserBirth ();

    //select*
    Userlogin findUser(String username);
    //用户更新教师的缴费未缴费权限
    int updatePermission(String username, Integer num);

}