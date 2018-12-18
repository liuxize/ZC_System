package com.system.service;

import com.system.po.Sign;

import java.beans.ExceptionListener;

public interface SignService {

     Sign findSignByStuID (Integer stuid) throws Exception;

     void addStuSign(Integer stuid) throws Exception;

//     //统计信息更新的人数
//     int countReceiveSign () throws Exception;
//     //统计新录入信息的人数
//     int countConfirmSign () throws Exception;


     //某个信息更新时改变sign状态值为0
     void changeStuNameSign(Integer stuid)throws Exception;

     void changeBirthSign(Integer stuid)throws Exception;

     void changeMoBirthSign(Integer stuid)throws Exception;

     void changeFaBirthSign(Integer stuid)throws Exception;

     void changeMajorSign(Integer stuid)throws Exception;

     void changeSchoolSign(Integer stuid)throws Exception;  //学校

     void changeStuTelSign(Integer stuid)throws Exception;

     void changeMoNameSign(Integer stuid)throws Exception;

     void changeMoCompanySign(Integer stuid)throws Exception;  //母亲公司

     void changeMoJobSign(Integer stuid)throws Exception;  //母亲职位

     void changeMotherTelSign(Integer stuid)throws Exception;

     void changeFaNameSign(Integer stuid)throws Exception;

     void changeFaDegreeSign(Integer stuid)throws Exception;

     void changeMoDegreeSign(Integer stuid)throws Exception;

     void changeFaCompanySign(Integer stuid)throws Exception;  //父亲公司

     void changeFaJobSign(Integer stuidb)throws Exception;  //父亲职位

     void changeFatherTelSign(Integer stuid)throws Exception;

     void changeAddressSign(Integer stuid)throws Exception;  //家庭地址

     void changeMasterSign(Integer stuid)throws Exception;   //教师

     void changeMasterTelSign(Integer stuid)throws Exception;  //教师电话

     void changeStuSexSign(Integer stuid) throws Exception;

     void changeMasterAddressSign(Integer stuid) throws Exception;

     void changeMasterDegreeSign(Integer stuid) throws Exception;

     void changeMasterSexSign(Integer stuid) throws Exception;

     void changeMasterBirthSign(Integer stuid) throws Exception;

     void changeCheckDate(Integer stuid) throws Exception;

     void changeSchoolTextSign(Integer stuid)throws Exception;

     void changeFamilyTextSign(Integer stuid)throws Exception;

     void changeStudyTextSign(Integer stuid)throws Exception;

     void changeEducationTextSign(Integer stuid)throws Exception;

     void changeSupportTextSign(Integer stuid)throws Exception;

     void changeImproveTextSign(Integer stuid)throws Exception;

     void leaderSign(Integer stuid, String username) throws Exception;   //负责人签字

     void masterSign(Integer stuid, String username) throws Exception;   //校长签字

     void adminSignReceive(Integer stuid) throws Exception;

     void adminSignUpdate(Integer stuid) throws Exception;

     int getCountAdminReceive()throws Exception;

     int getCountAdminUpdate()throws Exception;

     void SetChangeSign(Integer stuid)throws Exception; //添加表二，三的时候调用

     void removeSignByID(Integer stuid) throws Exception;
}
