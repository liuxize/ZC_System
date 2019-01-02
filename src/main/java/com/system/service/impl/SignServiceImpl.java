package com.system.service.impl;

import com.system.mapper.SignMapper;
import com.system.po.Sign;
import com.system.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService{

    @Autowired
    private SignMapper signMapper;

    public Sign findSignByStuID (Integer stuid) throws Exception{
        return signMapper.selectSignByStuID(stuid);
    }

    public void addStuSign(Integer stuid) throws  Exception{
        signMapper.insertSign(stuid);
    }

    //统计信息更新的人数
//    public int countReceiveSign () throws Exception{
//        return signMapper.countByReceiveSign();
//    }
//    //统计新录入信息的人数
//    public int countConfirmSign () throws Exception{
//        return signMapper.countByConfirmSign();
//    }

    //某个信息更新时改变sign状态值为0

    public void changeStuNameSign(Integer stuid)throws Exception{
        signMapper.addStuNameSign(stuid);
    }

    public void changeBirthSign(Integer stuid)throws Exception{
        signMapper.addBirthSign(stuid);
    }

    public void changeMoBirthSign(Integer stuid)throws Exception{
        signMapper.addMoBirthSign(stuid);
    }

    public void changeFaBirthSign(Integer stuid)throws Exception{
        signMapper.addFaBirthSign(stuid);
    }

    public void changeMajorSign(Integer stuid)throws Exception{
        signMapper.addMajorSign(stuid);
    }

    public void changeSchoolSign(Integer stuid)throws Exception{
        signMapper.addSchoolSign(stuid);
    }  //学校


    public void changeStuTelSign(Integer stuid)throws Exception{
        signMapper.addStuTelSign(stuid);
    }  //学校

    public void changeMoNameSign(Integer stuid)throws Exception{
        signMapper.addMoNameSign(stuid);
    }

    public void changeMoCompanySign(Integer stuid)throws Exception{
        signMapper.addMoCompanySign(stuid);
    }  //母亲公司

    public void changeMoJobSign(Integer stuid)throws Exception{
        signMapper.addMoJobSign(stuid);
    }  //母亲职位

    public void changeMotherTelSign(Integer stuid)throws Exception{
        signMapper.addMotherTelSign(stuid);
    }

    public void changeFaNameSign(Integer stuid)throws Exception{
        signMapper.addFaNameSign(stuid);
    }

    public void changeFaDegreeSign(Integer stuid)throws Exception{
        signMapper.addFaDegreeSign(stuid);
    }

    public void changeMoDegreeSign(Integer stuid)throws Exception{
        signMapper.addMoDegreeSign(stuid);
    }
    public void changeFaCompanySign(Integer stuid)throws Exception{
        signMapper.addFaCompanySign(stuid);
    }  //父亲公司

    public void changeFaJobSign(Integer stuid)throws Exception{
        signMapper.addFaJobSign(stuid);
    }  //父亲职位

    public void changeFatherTelSign(Integer stuid)throws Exception{
        signMapper.addFatherTelSign(stuid);
    }

    public void changeAddressSign(Integer stuid)throws Exception{
        signMapper.addAddressSign(stuid);
    }  //家庭地址

    public void changeMasterSign(Integer stuid)throws Exception{
        signMapper.addMasterSign(stuid);
    }   //教师

    public void changeMasterTelSign(Integer stuid)throws Exception{
        signMapper.addMasterTelSign(stuid);
    }  //教师电话

    public void changeStuSexSign(Integer stuid) throws Exception{
        signMapper.addStuSexSign(stuid);
    }

    public void changeMasterAddressSign(Integer stuid) throws Exception{
        signMapper.addMasterAddressSign(stuid);
    }

    public void changeMasterDegreeSign(Integer stuid) throws Exception{
        signMapper.addMasterDegreeSign(stuid);
    }

    public void changeMasterSexSign(Integer stuid) throws Exception{
        signMapper.addMasterSexSign(stuid);
    }

    public void changeMasterBirthSign(Integer stuid) throws Exception{
        signMapper.addMasterBirthSign(stuid);
    }

    public void changeCheckDate(Integer stuid) throws Exception{
        signMapper.addCheckDateSign(stuid);
    }

    public void changeSchoolTextSign(Integer stuid)throws Exception{
        signMapper.addSchoolTextSign(stuid);
    }

    public void changeFamilyTextSign(Integer stuid)throws Exception{
        signMapper.addFamilyTextSign(stuid);
    }

    public void changeStudyTextSign(Integer stuid)throws Exception{
        signMapper.addStudyTextSign(stuid);
    }

    public void changeEducationTextSign(Integer stuid)throws Exception{
        signMapper.addEducationTextSign(stuid);
    }

    public void changeSupportTextSign(Integer stuid)throws Exception{
        signMapper.addSupportTextSign(stuid);
    }

    public void changeImproveTextSign(Integer stuid)throws Exception{
        signMapper.addImproveTextSign(stuid);
    }

    //负责人签字
    public  void leaderSign(Integer stuid, String username) throws Exception{
        signMapper.updateLeaderSign(stuid, username);
    }
    //校长签字
    public  void masterSign(Integer stuid, String username) throws Exception{
        signMapper.updateMasterSign(stuid, username);
    }

    public void adminSignReceive(Integer stuid) throws Exception{
        signMapper.updateAdminReceiveSign(stuid);
    }

    public void adminSignUpdate(Integer stuid) throws Exception{
        signMapper.updateAdminUpdateSign(stuid);
    }

    public int getCountAdminReceive()throws Exception{
        return signMapper.countAdminReceive();
    }

    public int getCountAdminReceiveUnsign()throws Exception{
        return signMapper.countAdminReceiveUnsign();
    }

    public int getCountAdminUpdate()throws Exception{
        return signMapper.countAdminUpdate();
    }

    public int getCountAdminUpdateUnsign()throws Exception{
        return signMapper.countAdminUpdateUnsign();
    }


    public void SetChangeSign(Integer stuid)throws Exception{
        signMapper.changeSign(stuid);
    }

    public void removeSignByID(Integer stuid) throws Exception{
        signMapper.deleteSignByStuID(stuid);
    }

}


