package com.system.service.impl;

import com.system.mapper.BirthdayMapper;
import com.system.mapper.RemindPayMapper;
import com.system.po.*;
import com.system.service.LessonService;
import com.system.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RemindServiceImpl implements RemindService {

    @Autowired
    private BirthdayMapper birthdayMapper;

    @Autowired
    private RemindPayMapper remindPayMapper;

    public void saveBirthday(Birthday birthday) throws Exception{
        birthdayMapper.insert(birthday);
    }

    public void saveRemindPay(RemindPay remindPay) throws Exception{
        remindPayMapper.insert(remindPay);
    }

    public void removeBirthday(Integer id)throws Exception{
        birthdayMapper.deleteByID(id);
    }

    public  void removeRemindPay(Integer id) throws  Exception{
        remindPayMapper.deleteByID(id);
    }

    public List<Birthday> findAllBirthday(Integer toPageNo) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        return birthdayMapper.selectAll(pagingVO);
    }

    public List<Birthday> findAllBirthdayAuth(Integer toPageNo, String teacherName) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setStringtemp(teacherName);
        return birthdayMapper.selectAllAuth(pagingVO);
    }

    public  int getCountBirth() throws Exception{
        return birthdayMapper.countBirth();
    }

    public  int getCountBirthAuth(String teacherName) throws Exception{
        return birthdayMapper.countBirthAuth(teacherName);
    }

    public List<LessonCustom> findAllRemindPay(Integer toPageNo) throws  Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        return remindPayMapper.selectAllByPay(pagingVO);
    }

    public List<StuCustom> findAllRemindList() throws  Exception{
        return remindPayMapper.selectAllRemindPay();
    }


    public  int getCountRemindPay() throws Exception{
        return remindPayMapper.countRemindPay();
    }
}