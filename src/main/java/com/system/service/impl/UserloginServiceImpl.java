package com.system.service.impl;

import com.system.mapper.*;
import com.system.po.*;
import com.system.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class UserloginServiceImpl implements UserloginService {

    @Autowired
    private UserloginMapper userloginMapper;

    @Autowired
    private UserloginMapperCustom userloginMapperCustom;

    @Autowired
    private NoteTableMapper noteTableMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private DateMapper dateMapper;

    @Autowired
    private TextMapper textMapper;



    public Userlogin findByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        List<Userlogin> list = userloginMapper.selectByExample(userloginExample);

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public Userlogin findUser(String name) throws Exception{
        return userloginMapper.findUser(name);
    }
    public void save(Userlogin userlogin) throws Exception {

        userloginMapper.insert(userlogin);
    }


    public int getCountTeacher() throws Exception {
        //自定义查询对象
        UserloginExample userloginExample = new UserloginExample();
        //通过criteria构造查询条件
        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUseridIsNotNull();

        return userloginMapper.countByExample(userloginExample);
    }


    public void updateByName(String name, Userlogin userlogin) {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        userloginMapper.updateByExample(userlogin, userloginExample);
    }

    public List<UserloginCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<UserloginCustom> list = userloginMapperCustom.findByPaging(pagingVO);

        return list;
    }

    public int getCountUsers() throws Exception {
        //自定义查询对象
        UserloginExample userloginExample = new UserloginExample();
        //通过criteria构造查询条件
        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUseridIsNotNull();

        return userloginMapper.countByExample(userloginExample);
    }

    public void updateNote(String username, String text) {


        userloginMapper.updataNoteByName(username, text);
    }

    public String getNote(String username) {

        String text = userloginMapper.findNoteByName(username);
        return text;

    }

    public void saveNoteTable(NoteTable noteTable) throws Exception {
        noteTableMapper.insertNote(noteTable);
    }

    public int getCountNoteTable(Integer dicid) throws Exception { // ?????????????????
       return noteTableMapper.getCountNoteByDicID(dicid);
    }

    //保存记事本
    public void saveText(TextDic textDic) throws Exception {
        textMapper.insertText(textDic);
    }

    public int getCountText(String username, Integer texttype) throws Exception {
        return textMapper.countTextByName(username, texttype);
    }

    public int getCountNoteDic(String username, Integer  dictype) throws Exception {
        return noteTableMapper.countDicByName(username,dictype);
    }

    public List<NoteTable> findNoteTableByDicID(Integer toPageNo, Integer dicname) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setIntergertemp(dicname);
        return noteTableMapper.selectNoteTableByDicID(pagingVO);
    }

    public List<NoteTable> findAllNoteTableByDicID(Integer dicid) throws Exception{
        return noteTableMapper.selectAllNoteTableByDicID(dicid);
    }

    public void setTeacherPerssion(String teachername, GradeList gradeList) throws Exception {
        if (gradeList.getGrade0() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade0());
        }
        if (gradeList.getGrade1() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade1());
        }
        if (gradeList.getGrade2() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade2());
        }
        if (gradeList.getGrade3() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade3());
        }
        if (gradeList.getGrade4() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade4());
        }
        if (gradeList.getGrade5() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade5());
        }
        if (gradeList.getGrade6() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade6());
        }
        if (gradeList.getGrade7() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade7());
        }
        if (gradeList.getGrade8() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade8());
        }
        if (gradeList.getGrade9() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade9());
        }
        if (gradeList.getGrade10() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade10());
        }
        if (gradeList.getGrade11() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade11());
        }
        if (gradeList.getGrade12() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade12());
        }
        if (gradeList.getGrade13() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade13());
        }
        if (gradeList.getGrade14() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade14());
        }
        if (gradeList.getGrade15() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade15());
        }
        if (gradeList.getGrade16() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade16());
        }
        if (gradeList.getGrade17() != null) {
            permissionMapper.insert(teachername, gradeList.getGrade17());
        }
    }

    public void deleteTeacherPerssion(String teachername) throws Exception{
       // System.out.println("count"+permissionMapper.countByName(teachername));
       // if (permissionMapper.countByName(teachername)>0) {
            permissionMapper.deleteByName(teachername);
       // }
    }

    public void updataPassword(String username, String password)  throws Exception{
        userloginMapper.updataPasswordByName(username,password);
    }


    public List<Integer> findTeacherPerssion(String username)  throws Exception{
       // if (username!=null){
           List<Integer>list= permissionMapper.selectGradeIdByName(username);

        if (list.size() == 0) {
            list.add(-1);
        }
     //       return null;
      //  } else {
            return list;
   //     }

    }

    public List<NoteDic>  findNoteDic(Integer toPageNo, String username, Integer dictype) throws Exception {

        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setStringtemp(username);
        pagingVO.setIntergertemp(dictype);
        return noteTableMapper.selectNoteDicByName(pagingVO);
    }

    public void saveNoteDic(NoteDic noteDic) throws Exception {
        noteTableMapper.insertNoteDic(noteDic);
    }

    public NoteDic findNoteDic(String username, String notedic) throws Exception {
        return noteTableMapper.selectNoteDicByUser(username,notedic);
    }

    public void removeNoteDic(Integer dicid) throws Exception{
        noteTableMapper.deleteByDicID(dicid);
    }

    public void removeNoteTable(Integer noteID)  throws Exception{
        noteTableMapper.deleteByNoteID(noteID);
    }
    public void removeNoteByDicID(Integer dicid)  throws Exception{
        noteTableMapper.deleteNoteByDicID(dicid);
    }

    public  void removeUserByName(String username) throws Exception{
        userloginMapper.deleteByUserName(username);
        permissionMapper.deleteByName(username);
        noteTableMapper.clearNoteDicByName(username);
        noteTableMapper.clearNoteTableByName(username);
        textMapper.clearNoteTextByName(username);
    }

    public void updateDateRecord(Date date) throws Exception{
        dateMapper.setDateHis(date);
    }

    public Date getDateRecord() throws Exception{
        return dateMapper.getDateHis();
    }

    public  void updateNoteTableByID(NoteTable noteTable){
        noteTableMapper.updateByNoteID(noteTable);
    }

    public  NoteTable findNoteTableByID(Integer noteID){
        return noteTableMapper.selectByNoteID(noteID);
    }

    public  List<TextDic> findTextByName(Integer toPageNo, String username, Integer texttype) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setStringtemp(username);
        pagingVO.setIntergertemp(texttype);
        return textMapper.selectTextByName(pagingVO);
    }
    //根据textid查抄文本信息
    public  TextDic  findTextDicByID(Integer textid) throws Exception{
        return textMapper.selectTextDicByID(textid);
    }

    public  NoteDic findNoteDicByID(Integer dicid) throws Exception{
        return noteTableMapper.selectNoteDicByID(dicid);
    }


    public  void  updeTextDicByID(TextDic textDic) throws Exception{
        textMapper.updateText(textDic);
    }
    public void removeTextByID(Integer textid) throws Exception{
        textMapper.deleteTectByID(textid);
    }

    public List<Userlogin> findUserByBirth() throws Exception{
        return  userloginMapper.selectByUserBirth();
    }



    public void updateUserPermission (String username, Integer num) throws Exception{
        userloginMapper.updatePermission(username,num);
    }

    public  int getCountLeaderByGradeID(Integer gradeid) throws Exception{
       return permissionMapper.countLeaderByGradeID(gradeid);
    }




}
