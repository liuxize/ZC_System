package com.system.service;

import com.system.po.Birthday;
import com.system.po.LessonCustom;
import com.system.po.RemindPay;
import com.system.po.StuCustom;

import java.util.List;

public interface RemindService {   //生日提醒和缴费提醒
    void saveBirthday(Birthday birthday) throws Exception;

    void saveRemindPay (RemindPay remindPay) throws Exception;

    void removeBirthday(Integer id)throws Exception;

    void removeRemindPay(Integer id) throws  Exception;

    List<Birthday> findAllBirthday( Integer toPageNo) throws Exception;
    List<Birthday> findAllBirthdayAuth( Integer toPageNo, String teacherName) throws Exception;

    int getCountBirth() throws Exception;
    int getCountBirthAuth(String teacherName) throws Exception;

    List<LessonCustom> findAllRemindPay(Integer toPageNo) throws  Exception;//分页
    List<LessonCustom> findAllRemindPayAuth(Integer toPageNo, String teacherName) throws  Exception;//分页

    List<StuCustom> findAllRemindList() throws  Exception; //全部

    int getCountRemindPay() throws Exception;
    int getCountRemindPayAuth(String teacherName) throws Exception;

}
