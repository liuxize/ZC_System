package com.system.service;

import com.system.po.Birthday;
import com.system.po.RemindPay;
import com.system.po.StuCustom;

import java.util.List;

public interface RemindService {   //生日提醒和缴费提醒
    void saveBirthday(Birthday birthday) throws Exception;

    void saveRemindPay (RemindPay remindPay) throws Exception;

    void removeBirthday(Integer id)throws Exception;

    void removeRemindPay(Integer id) throws  Exception;

    List<Birthday> findAllBirthday( Integer toPageNo) throws Exception;

    int getCountBirth() throws Exception;

    List<StuCustom> findAllRemindPay(Integer toPageNo) throws  Exception;//分页

    List<StuCustom> findAllRemindList() throws  Exception; //全部

    RemindPay  findRemindPayByStuID(Integer stuid) throws Exception;

    int getCountRemindPay() throws Exception;

}
