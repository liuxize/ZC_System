package com.system.mapper;

import com.system.po.Sign;

public interface SignMapper {
    void addStuNameSign(Integer stuid);

    void addBirthSign(Integer stuid);

    void addMajorSign(Integer stuid);

    void addSchoolSign(Integer stuid);  //学校

    void addStuTelSign(Integer stuid);

    void addMoNameSign(Integer stuid);

    void addMoCompanySign(Integer stuid);  //母亲公司

    void addMoJobSign(Integer stuid);  //母亲职位

    void addMotherTelSign(Integer stuid);

    void addMoBirthSign(Integer stuid);

    void addMoDegreeSign(Integer stuid);

    void addFaNameSign(Integer stuid);

    void addFaCompanySign(Integer stuid);  //父亲公司

    void addFaJobSign(Integer stuidb);  //父亲职位

    void addFatherTelSign(Integer stuid);

    void addFaBirthSign(Integer stuid);

    void addFaDegreeSign(Integer stuid);

    void addAddressSign(Integer stuid);  //家庭地址

    void addMasterSign(Integer stuid);   //教师

    void addMasterTelSign(Integer stuid);  //教师电话

    void addSchoolTextSign(Integer stuid);

    void addFamilyTextSign(Integer stuid);

    void addStudyTextSign(Integer stuid);

    void addEducationTextSign(Integer stuid);

    void addSupportTextSign(Integer stuid);

    void addImproveTextSign(Integer stuid);

    void addStuSexSign(Integer stuid);

    void addMasterAddressSign(Integer stuid);

    void addMasterDegreeSign(Integer stuid);

    void addMasterSexSign(Integer stuid);

    void addMasterBirthSign(Integer stuid);

    void addCheckDateSign(Integer stuid);

    void insertSign(Integer stuid);

    Sign selectSignByStuID(Integer stuid);

    int updateMasterSign(Integer stuid,String username);  //校长签字 保留名字

    int updateLeaderSign(Integer stuid,String username);  //负责人签字 保留名字

    //校长签字 刚插入数据库新录入标记是0，（新录入检索是0的） 校长查看后标记为1，更新的标记初始为0， 进行更新后标记为1， 校长查看后恢复为0 （更新检索是1的）
    int updateAdminReceiveSign(Integer stuid);

    int updateAdminUpdateSign(Integer stuid);

    //统计 管理员的新录入信息的数量(校长签字 和 校长未签字)
    int countAdminReceive();
    int countAdminReceiveUnsign();
    //统计管理员的更新的信息数量(校长签字 和 校长未签字)
    int countAdminUpdate();
    int countAdminUpdateUnsign();

    void changeSign(Integer stuid); //添加表二，三的时候调用

    int deleteSignByStuID (Integer stuid);
}
