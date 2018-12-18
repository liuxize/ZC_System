package com.system.mapper;

import com.system.po.PagingVO;
import com.system.po.TextDic;

import java.util.List;

public interface TextMapper {

    int insertText(TextDic textDic);

    int countTextByName(String username, Integer texttype);

    List<TextDic> selectTextByName(PagingVO pagingVO);

    TextDic selectTextDicByID(Integer textid);

    void updateText(TextDic textDic);

    int deleteTectByID(Integer textid);

    //清空文本记事本  根据姓名
    int clearNoteTextByName (String name);}



