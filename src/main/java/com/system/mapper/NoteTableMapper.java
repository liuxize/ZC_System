package com.system.mapper;

import com.system.po.NoteDic;
import com.system.po.NoteTable;
import com.system.po.PagingVO;

import java.util.List;

public interface NoteTableMapper {
    int insertNote(NoteTable noteTable);

    //根据目录ID获取对应的表格
     int getCountNoteByDicID (Integer dicid);

    //统计某个人用户记事本的标题数目
    int countDicByName(String username,Integer dictype);

    int insertNoteDic(NoteDic noteDic);

    NoteDic selectNoteDicByUser(String username, String dicname);   //没有用的

    List<NoteTable> selectNoteTableByDicID(PagingVO pagingVO);

    List<NoteTable> selectAllNoteTableByDicID(Integer dicid);

    //获取笔记目录 包括两种
    List<NoteDic> selectNoteDicByName(PagingVO pagingVO);

    //删除笔记本目录中的某个笔记名称
    int deleteByDicID(Integer dicid);

    //删除笔记本中所有的笔记
    int deleteNoteByDicID(Integer dicid);

    //删除一个笔记
    int deleteByNoteID(Integer noteID);

    int updateByNoteID(NoteTable noteTable);

    NoteTable selectByNoteID(Integer noteID);

    NoteDic selectNoteDicByID(Integer dicid);

    //清空表格记事本的目录  根据姓名

    int clearNoteDicByName (String name);
    //清空表格记事本的具体表格  根据姓名
    int clearNoteTableByName (String name);

}


