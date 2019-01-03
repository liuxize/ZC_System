package com.system.mapper;

import com.system.po.LessonCustom;
import com.system.po.PagingVO;
import com.system.po.RemindPay;
import com.system.po.StuCustom;

import java.util.List;

public interface RemindPayMapper {
    int insert (RemindPay remindPay);
    int deleteByID(Integer id);

    List<LessonCustom> selectAllByPay(PagingVO pagingVO);  //分页

    List<StuCustom> selectAllRemindPay();  //全部
    int countRemindPay();
}
