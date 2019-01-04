package com.system.controller;

import com.system.po.*;
import com.system.service.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class SpringTaskController {

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Resource(name = "stuServiceImpl")
    private StuService stuService;

    @Resource(name = "remindServiceImpl")
    private RemindService remindService;

    @Resource(name = "lessonServiceImpl")
    private LessonService lessonService;


    /**
     * 每次任务执行完之后的2s后继续执行 2000
     */
//    @Scheduled(fixedDelay=3600000)
//    public void say(){
//        Date current = new Date();
//        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        System.out.println("--------" + format.format(current) + "---------");
//    }


    @Scheduled(cron="0 0 12 ? * *" )    // 每天早上12:00触发
    public void addBirth() throws Exception {

        //System.out.println("kjkjkjk");
        //更新生日表
        Birthday birthday =new Birthday();
        StuCustom stuCustom = new StuCustom();
        Userlogin userlogin =new Userlogin();
        List<StuCustom> stuCustomList = stuService.findStuByBirth();
        List<StuCustom> motherList = stuService.findStuByMoBirth();
        List<StuCustom> fatherList = stuService.findStuByFaBirth();
        List<Userlogin> userList = userloginService.findUserByBirth();
        if (stuCustomList.isEmpty()==false){
            for (int j = 0; j < stuCustomList.size(); j++) {
                stuCustom=stuCustomList.get(j);
                birthday.setStuid(stuCustom.getStuid());
                birthday.setStatus("学生");
                birthday.setBirthday(stuCustom.getStubirth());
                birthday.setTelephone(stuCustom.getStutel());
                birthday.setBirthname(stuCustom.getStuname());

                remindService.saveBirthday(birthday);

            }
        }
        if (motherList.isEmpty()==false){
            for (int j = 0; j < motherList.size(); j++) {
                stuCustom=motherList.get(j);
                birthday.setStuid(stuCustom.getStuid());
                birthday.setStatus(stuCustom.getStuname()+"的母亲");
                birthday.setBirthday(stuCustom.getMotherbirth());
                birthday.setTelephone(stuCustom.getMothertel());
                birthday.setBirthname(stuCustom.getMothername());
                remindService.saveBirthday(birthday);
            }
        }
        if (fatherList.isEmpty()==false){
            for (int j = 0; j < fatherList.size(); j++) {
                stuCustom=fatherList.get(j);
                birthday.setStuid(stuCustom.getStuid());
                birthday.setStatus(stuCustom.getStuname()+"的父亲");
                birthday.setBirthday(stuCustom.getFatherbirth());
                birthday.setTelephone(stuCustom.getFathertel());
                birthday.setBirthname(stuCustom.getFathername());
                remindService.saveBirthday(birthday);
            }
        }
        if(userList.isEmpty()==false){
            for (int j = 0; j < userList.size(); j++) {
                userlogin= userList.get(j);
                if (userlogin.getRole()==0){
                    birthday.setStatus("管理员");
                }else  if (userlogin.getRole()==1){
                    birthday.setStatus("校长");
                }else  if (userlogin.getRole()==2){
                    birthday.setStatus("教师");
                }else  if (userlogin.getRole()==3){
                    birthday.setStatus("负责人");
                }

                birthday.setBirthday(userlogin.getUserbirth());
                birthday.setBirthname(stuCustom.getFathername());
                remindService.saveBirthday(birthday);
            }
        }


        //更新缴费提醒的表
        RemindPay remindPay = new RemindPay();
        Integer lessonid =null;
        List<Lesson> lessons = lessonService.findLessonByPay();
        if(lessons.isEmpty()==false){
            for (int j = 0; j < lessons.size(); j++) {
                lessonid =lessons.get(j).getLessonid();
                remindPay.setLessonid(lessonid);
                remindService.saveRemindPay(remindPay);
            }
        }
    }
}
