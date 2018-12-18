package com.system.service;

import com.system.po.*;

import java.util.Date;
import java.util.List;

/**
 *
 *
 */
public interface UserloginService {

    //根据名字查找用户
    Userlogin findByName(String name) throws Exception;

    //根据名字查找用户(select *)
    Userlogin findUser(String name) throws Exception;
    //保存用户登录信息
    void save(Userlogin userlogin) throws Exception;

    //根据用户名更新
    void updateByName(String name, Userlogin userlogin);

    //获取用户的总数量
    int getCountUsers() throws Exception;

    //获取分页查询老师信息
    List<UserloginCustom> findByPaging(Integer toPageNo) throws Exception;

    //更新记事本信息
    void updateNote(String username, String text);

    //读取记事本信息
    String getNote(String username);

    //添加记事表格信息
    void saveNoteTable(NoteTable noteTable) throws Exception;



    int getCountNoteTable(Integer dicid) throws Exception;

    //统计某个用户的表格题目的数量
    int getCountNoteDic(String username, Integer dictype) throws Exception;

    void saveText(TextDic textDic) throws Exception;

    //统计某个用户的文本数量
    int getCountText(String username, Integer texttype) throws Exception;


    //分页获取记事表格信息
    List<NoteTable> findNoteTableByDicID(Integer toPageNo, Integer dicid) throws Exception;

    //全部获取
    List<NoteTable> findAllNoteTableByDicID(Integer dicid) throws Exception;

    //添加教师权限信息
    void setTeacherPerssion(String teachername, GradeList gradeList)  throws Exception;

    //删除教师权限
    void deleteTeacherPerssion(String teachername) throws Exception;
    //获取全部用户
   // List<UserloginCustom> findAll() throws Exception;

    //更新用户密码
    void updataPassword(String username, String password)  throws Exception;

    //查看教师权限
    List<Integer>  findTeacherPerssion(String username) throws Exception;

    //获取记事表格目录
    List<NoteDic>  findNoteDic(Integer toPageNo, String username,Integer dictype) throws Exception;

    //添加表格目录
    void saveNoteDic(NoteDic noteDic ) throws Exception;

    NoteDic findNoteDic(String username, String dicname)  throws Exception;

    //删除目录中的名称
    void removeNoteDic(Integer dicid) throws Exception;

    void removeNoteTable(Integer noteID) throws Exception;

    void removeNoteByDicID(Integer dicid) throws Exception;

    void removeUserByName(String username) throws Exception;

    //更新更改年级的日期+1
    void updateDateRecord(Date date) throws Exception;

    Date getDateRecord() throws Exception;

    void updateNoteTableByID(NoteTable noteTable);

    NoteTable findNoteTableByID(Integer noteID);

    List<TextDic> findTextByName(Integer toPageNo, String username, Integer texttype) throws Exception;

    TextDic  findTextDicByID(Integer textid) throws Exception;

    NoteDic findNoteDicByID(Integer dicid) throws Exception;

     void  updeTextDicByID(TextDic textDic) throws Exception;

     void removeTextByID(Integer textid) throws Exception;

     //用户提前7天生日提醒
     List<Userlogin> findUserByBirth() throws Exception;

     //更新教师查看缴费 未缴费权限
    void updateUserPermission (String username, Integer num) throws Exception;

    //根据年纪判断是否有负责人
    int getCountLeaderByGradeID(Integer gradeid) throws Exception;

}
