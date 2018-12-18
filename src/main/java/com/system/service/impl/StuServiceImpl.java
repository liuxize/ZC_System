package com.system.service.impl;

import com.system.mapper.StuMapper;
import com.system.mapper.StuMapperCustom;
import com.system.po.PagingVO;
import com.system.po.StuCustom;
import com.system.po.TextDic;
import com.system.service.StuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.system.po.Stu;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Autowired
    private StuMapperCustom stuMapperCustom;

    public void save(Stu stu) throws Exception {


       /* stu.setAddresshis("");
        stu.setMothercompanyhis("");
        stu.setMotherjobhis("");
        stu.setFathercompanyhis("");
        stu.setFatherjobhis("");
        stu.setMasternamehis("");
        stu.setMastertelhis("");
        stu.setSchoolhis("");*/


       // stu.s
        if (stu.getSchooltext()!="")
            stu.setSchooltext(stu.getSchooltext()+"\n");
        if (stu.getFamilytext()!="")
            stu.setFamilytext(stu.getFamilytext()+"\n");
        if (stu.getStudytext()!="")
            stu.setStudytext(stu.getStudytext()+"\n");
        if (stu.getEducationtext()!="")
            stu.setEducationtext(stu.getEducationtext()+"\n");
        if (stu.getSupporttext()!="")
            stu.setSupporttext(stu.getSupporttext()+"\n");
        if (stu.getImprovetext()!="")
            stu.setImprovetext(stu.getImprovetext()+"\n");


        stuMapper.insert(stu);

    }

    public void update(Stu stu) throws  Exception{
        stuMapper.updateByStuID(stu);
    }

    public StuCustom findById(Integer id) throws Exception {

        StuCustom stuCustom = stuMapperCustom.selectByPrimaryKey(id);

        return stuCustom;
    }

    //添加姓名
    public void addStuNameByID (Integer id, String str) throws Exception{
        stuMapper.addStuNameByPrimaryKey(id, str);
    }
    //添加生日
    public void addStuBirthByID(Integer id, Date date, String str) throws Exception{
        stuMapper.addStuBirthByPrimaryKey(id, date, str);
    }

    public void addFatherBirthByID(Integer id, Date date, String str) throws Exception{
        stuMapper.addFatherBirthByPrimaryKey(id, date, str);
    }

    public void addMotherBirthByID(Integer id, Date date, String str) throws Exception{
        stuMapper.addMotherBirthByPrimaryKey(id, date, str);
    }
    //添加专业
    public void addMajorByID(Integer id, String str) throws Exception{
        stuMapper.addMajorByPrimaryKey(id, str);
    }
    //添加学校
    public void addSchoolByID(Integer id, String school) throws Exception {

        stuMapper.addSchoolByPrimaryKey(id, school);
    }
    //学生电话
    public void addStuTelByID(Integer id, String str) throws Exception{
        stuMapper.addStuTelByPrimaryKey(id, str);
    }
    //母亲姓名
    public void addMotherNameByID(Integer id, String str) throws Exception{
        stuMapper.addMotherNameByPrimaryKey(id, str);
    }
    //母亲公司
    public void addMoCompanyByID(Integer id, String str) throws Exception {
        stuMapper.addMoCompanyByPrimaryKey(id, str);
    }
    //母亲职位
    public void addMoJobByID(Integer id, String str) throws Exception {
        stuMapper.addMoJobByPrimaryKey(id, str);
    }
    //母亲电话
    public void addMoTelByID(Integer id, String str) throws Exception{
        stuMapper.addMoTelByPrimaryKey(id, str);
    }
    //父亲姓名
    public void addFaNameByID(Integer id, String str) throws Exception{
        stuMapper.addFaNameByPrimaryKey(id, str);
    }
    //父亲公司
    public void addFaCompanyByID(Integer id, String str) throws Exception {
        stuMapper.addFaCompanyByPrimaryKey(id, str);
    }
    //父亲职位
    public void addFaJobByID(Integer id, String str) throws Exception {
        stuMapper.addFaJobByPrimaryKey(id, str);
    }
    //父亲电话
    public void addFaTelByID(Integer id, String str) throws Exception{
        stuMapper.addFaTelByPrimaryKey(id, str);
    }
    //家庭地址
    public void addAddressByID(Integer id, String str) throws Exception {
        stuMapper.addAddressByPrimaryKey(id, str);
    }
    //教师
    public void addMasterByID(Integer id, String str) throws Exception {
        stuMapper.addMasterByPrimaryKey(id, str);
    }
    //教师电话
    public void addMasterTelByID(Integer id, String str) throws Exception {
        stuMapper.addMasterTelByPrimaryKey(id, str);
    }

    public void addSchoolTextByID(Integer id, String str) throws Exception {
        stuMapper.addSchoolTextByPrimaryKey(id, str);
    }

    public void addFamilyTextByID(Integer id, String str) throws Exception {
        stuMapper.addFamilyTextByPrimaryKey(id, str);
    }

    public void addStudyTextByID(Integer id, String str) throws Exception {
        stuMapper.addStudyTextByPrimaryKey(id, str);
    }

    public void addEducationTextByID(Integer id, String str) throws Exception {
        stuMapper.addEducationTextByPrimaryKey(id, str);
    }

    public void addSupportTextByID(Integer id, String str) throws Exception {
        stuMapper.addSupportTextByPrimaryKey(id, str);

    }

    public Date getBirthByID(Integer id) throws Exception {
        return stuMapper.selectBirthByPrimaryKey(id);
    }

    public Date getMoBirthByID(Integer id) throws Exception {
        return stuMapper.selectMoBirthByPrimaryKey(id);
    }

    public Date getFaBirthByID(Integer id) throws Exception {
        return stuMapper.selectFaBirthByPrimaryKey(id);
    }


    public Date getMasterBirthByID(Integer id) throws Exception{
        return stuMapper.selectMasterBirthByPrimaryKey(id);
    }

    public Date getCheckDateByID(Integer id) throws Exception{
        return stuMapper.selectCheckDateByPrimaryKey(id);
    }

    public void addImproveTextByID(Integer id, String str) throws Exception {
        stuMapper.addImproveTextByPrimaryKey(id, str);
    }

    public void addMotherDegreeByID (Integer id, String str) throws Exception{
        stuMapper.addMoDegreeByPrimaryKey(id,str);
    }

    public void addFatherDegreeByID (Integer id, String str) throws Exception{
        stuMapper.addFaDegreeByPrimaryKey(id,str);
    }

    public void addStuSexByID(Integer id, String str) throws Exception{
        stuMapper.addStuSexByPrimaryKey(id, str);
    }

    public void addMasterAddressByID(Integer id, String str) throws Exception{
        stuMapper.addMasterAddressByPrimaryKey(id, str);
    }

    public void addMasterDegreeByID(Integer id, String str) throws Exception{
        stuMapper.addMasterDegreeByPrimaryKey(id, str);
    }

    public void addMasterSexByID(Integer id, String str) throws Exception{
        stuMapper.addMasterSexByPrimaryKey(id, str);
    }

    public void addMasterBirthByID(Integer stuid, Date date, String str) throws Exception{
        stuMapper.addMasterBirthByPrimaryKey(stuid,date,str);
    }

    public void addCheckDateByID(Integer stuid, Date date, String str) throws Exception{
        stuMapper.addCheckDateByPrimaryKey(stuid,date,str);
    }

    public List<StuCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<StuCustom> list =stuMapperCustom.findByPaging(pagingVO);

        return list;
    }


    public List<StuCustom> findOrderByGrade(Integer toPageNo) throws Exception {

        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        List<StuCustom> list = stuMapperCustom.findByGrade(pagingVO);

        return list;
    }


    public List<StuCustom> findByName(String name) throws Exception {

        List<StuCustom> list = stuMapperCustom.selectByName(name);

        return list;
    }


    public int getCountStudent() throws Exception {

        return stuMapper.countStu();
    }

    public int getCountByGrade(Integer gradeid) throws Exception {

        return stuMapper.countStuByGrade(gradeid);
    }

    public int getCountByBirth() throws Exception {

        return stuMapper.countStuByBirth();
    }

    public int getCountByPayStu() throws Exception {

        return stuMapper.countStuByPayStu();
    }

    //统计未缴费学生人数
    public int getCountByUnPayStu(Integer gradeid, Integer teleType) throws Exception {
//
//        if(gradeid == -1){
//            return stuMapper.countStuByUnPayStu();
//        }else {
//
//        }

        if(gradeid==-1 && teleType==0){  //全部 全部

            return stuMapper.countStuByUnPayStu();
        }
        else if(gradeid==-1 && teleType==1) { //全部 有电话
           return stuMapper.countStuByUnPayStuTel();
        }
        else if(gradeid==-1 && teleType==2){  //全部 没有电弧
            return stuMapper.countStuByUnPayStuNoTel();
        }
        else if (gradeid!=-1 && teleType==0){ //分年级 全部

            return stuMapper.countUnPayStuByGrade(gradeid);

        }
        else if(gradeid!=-1 && teleType==1){ //分年级 有电话
            return stuMapper.countUnPayStuByGradeTel(gradeid);
        }
        else if (gradeid!=-1 && teleType==2) //分年级 没有电话

        {
            return stuMapper.countUnPayStuByGradeNoTel(gradeid);
        }
        else {
            return 0;
        }
    }

    public int getCountByPay() throws Exception {

        return stuMapper.countStuByPay();
    }

    public int getCountByPayAndGrade(Integer gradeid) throws Exception {

        return stuMapper.countStuByPayAndGrade(gradeid);
    }

    public int getCountBySchool(String schoolName) throws Exception{
        return stuMapper.countStuBySchool(schoolName);

    }

    public int getCountByMajor(String majorName) throws Exception{
        return stuMapper.countStuByMajor(majorName);
    }

    public int getCountBySameStu() throws Exception{
        return stuMapper.countStuBySameName();
    }

    public List<StuCustom> findStuByGrade(Integer toPageNo, Integer gradeid) throws Exception{

        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setIntergertemp(gradeid);
        return stuMapperCustom.selectByGrade(pagingVO);
    }

    public List<StuCustom> findAllStuByGrade(Integer gradeid) throws Exception{


        return stuMapperCustom.selectAllByGrade(gradeid);
    }


    public int getCountByDate(Date startdate, Date enddate) throws Exception {

        return stuMapper.countStuByDate(startdate,enddate);
    }

    public List<StuCustom> findStuByDate(Integer toPageNo, Date startdate, Date enddate) throws Exception{

        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setStartdate(startdate);
        pagingVO.setEnddate(enddate);
        return stuMapperCustom.selectByDate(pagingVO);
    }

    public List<StuCustom> findAllStuByDate(Date startdate, Date enddate) throws Exception{

        return stuMapperCustom.selectAllByDate(startdate,enddate);
    }

    public List<StuCustom> findStuByBirth( ) throws Exception{

        return stuMapperCustom.selectByBirth();
    }

    public List<StuCustom> findStuByFaBirth() throws Exception{

        return stuMapperCustom.selectByFatherBirth();
    }

    public List<StuCustom> findStuByMoBirth() throws Exception{

        return stuMapperCustom.selectByMotherBirth();
    }

    public List<StuCustom> findAllStuByMajor( String majorName) throws Exception{
        return stuMapperCustom.selectAllByMajor(majorName);
    }

    public List<StuCustom> findStuByMajor(Integer toPageNo, String majorName) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setStringtemp(majorName);
        return stuMapperCustom.selectByMajor(pagingVO);
    }

    public List<StuCustom> findAllStuBySchool(String schoolName) throws Exception{
        return stuMapperCustom.selectAllBySchool(schoolName);
    }

    public List<StuCustom> findStuBySchool(Integer toPageNo, String schoolName) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setStringtemp(schoolName);
        return stuMapperCustom.selectBySchool(pagingVO);
    }

    public List<StuCustom> findStuBySameName (Integer toPageNo) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        return stuMapperCustom.selectSameName(pagingVO);
    }
    public List<StuCustom> findStuByPay(Integer toPageNo) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        return stuMapperCustom.selectByPay(pagingVO);
    }


    public List<StuCustom> findAllStuByPay( ) throws Exception{

        return stuMapperCustom.selectAllByPay();
    }


    //根据选项分页查找已经缴费的学生
    public List<StuCustom> findStuByPayStuAndSelect(Integer toPageNo, Integer gradeid, Integer subjectid, Integer typeid) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        //pagingVO.setIntergertemp(gradeid);
        if(gradeid==-1 && subjectid !=-1 && typeid!=-1) {    //学科 类型
            pagingVO.setIntergertwo(subjectid);
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectPayStuBySubAndType(pagingVO);

        }
        else if (gradeid!=-1 && subjectid ==-1 && typeid!=-1){    //年级 类型
            pagingVO.setIntergertemp(gradeid);
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectPayStuByGradeAndType(pagingVO);
        }
        else if(gradeid!=-1 && subjectid !=-1 && typeid==-1){ //年级 学科
            pagingVO.setIntergertemp(gradeid);
            pagingVO.setIntergertwo(subjectid);
            return stuMapperCustom.selectPayStuByGradeAndSub(pagingVO);
        }
        else if(gradeid!=-1 && subjectid ==-1 && typeid==-1){  //年级
            pagingVO.setIntergertemp(gradeid);
            return stuMapperCustom.selectPayStuByGrade(pagingVO);

        }
        else if(gradeid==-1 && subjectid !=-1 && typeid==-1){  //学科
            pagingVO.setIntergertwo(subjectid);
            return stuMapperCustom.selectPayStuBySub(pagingVO);
        }
        else if(gradeid==-1 && subjectid ==-1 && typeid!=-1){  //类型
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectPayStuByType(pagingVO);
        }
        else if(gradeid==-1 && subjectid ==-1 && typeid==-1){  //全部
            return stuMapperCustom.selectByPayStu(pagingVO);
        }
        else {  //学科 类型 类型
            pagingVO.setIntergertemp(gradeid);
            pagingVO.setIntergertwo(subjectid);
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectPayStuByGradeSubType(pagingVO);
        }
    }


    //根据选项全部查找已经缴费的学生
    public List<StuCustom> findAllStuByPayStuAndSelect(Integer gradeid, Integer subjectid, Integer typeid) throws Exception{
        PagingVO pagingVO = new PagingVO();
        if(gradeid==-1 && subjectid !=-1 && typeid!=-1) {    //学科 类型
            pagingVO.setIntergertwo(subjectid);
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectAllPayStuBySubAndType(pagingVO);

        }
        else if (gradeid!=-1 && subjectid ==-1 && typeid!=-1){    //年级 类型
            pagingVO.setIntergertemp(gradeid);
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectAllPayStuByGradeAndType(pagingVO);
        }
        else if(gradeid!=-1 && subjectid !=-1 && typeid==-1){ //年级 学科
            pagingVO.setIntergertemp(gradeid);
            pagingVO.setIntergertwo(subjectid);
            return stuMapperCustom.selectAllPayStuByGradeAndSub(pagingVO);
        }
        else if(gradeid!=-1 && subjectid ==-1 && typeid==-1){  //年级
            pagingVO.setIntergertemp(gradeid);
            return stuMapperCustom.selectAllPayStuByGrade(pagingVO);

        }
        else if(gradeid==-1 && subjectid !=-1 && typeid==-1){  //学科
            pagingVO.setIntergertwo(subjectid);
            return stuMapperCustom.selectAllPayStuBySub(pagingVO);
        }
        else if(gradeid==-1 && subjectid ==-1 && typeid!=-1){  //类型
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectAllPayStuByType(pagingVO);
        }
        else if(gradeid==-1 && subjectid ==-1 && typeid==-1){  //全部
            return stuMapperCustom.selectAllByPayStu();
        }
        else {  //学科 类型 类型
            pagingVO.setIntergertemp(gradeid);
            pagingVO.setIntergertwo(subjectid);
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectAllPayStuByGradeSubType(pagingVO);
        }
    }


    public int countPayStuBySelect(Integer gradeid, Integer subjectid, Integer typeid) throws  Exception{
        if(gradeid==-1 && subjectid !=-1 && typeid!=-1) {    //学科 类型
            return stuMapper.countPayBySubAndType(subjectid, typeid);
        }
        else if (gradeid!=-1 && subjectid ==-1 && typeid!=-1){    //年级 类型
            return stuMapper.countPayByGradeAndType(gradeid,typeid);
        }
        else if(gradeid!=-1 && subjectid !=-1 && typeid==-1){ //年级 学科
            return stuMapper.countPayByGradeAndSub(gradeid,subjectid);
        }
        else if(gradeid!=-1 && subjectid ==-1 && typeid==-1){  //年级
            return stuMapper.countPayByGrade(gradeid);
        }
        else if(gradeid==-1 && subjectid !=-1 && typeid==-1){  //学科
           return stuMapper.countPayBySub(subjectid);
        }
        else if(gradeid==-1 && subjectid ==-1 && typeid!=-1){  //类型
            return stuMapper.countPayByType(typeid);
        }
        else if(gradeid==-1 && subjectid ==-1 && typeid==-1){  //全部
           return stuMapper.countStuByPayStu();
        }
        else {  //学科 类型 类型
            return stuMapper.countPayByGradeSubType(gradeid,subjectid,typeid);
        }
    }


    //根据选项分页查找预缴费的学生
    public List<StuCustom> findStuByPrePayStuAndSelect(Integer toPageNo, Integer gradeid, Integer subjectid, Integer typeid) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        //pagingVO.setIntergertemp(gradeid);
        if(gradeid==-1 && subjectid !=-1 && typeid!=-1) {    //学科 类型
            pagingVO.setIntergertwo(subjectid);
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectPrePayStuBySubAndType(pagingVO);

        }
        else if (gradeid!=-1 && subjectid ==-1 && typeid!=-1){    //年级 类型
            pagingVO.setIntergertemp(gradeid);
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectPrePayStuByGradeAndType(pagingVO);
        }
        else if(gradeid!=-1 && subjectid !=-1 && typeid==-1){ //年级 学科
            pagingVO.setIntergertemp(gradeid);
            pagingVO.setIntergertwo(subjectid);
            return stuMapperCustom.selectPrePayStuByGradeAndSub(pagingVO);
        }
        else if(gradeid!=-1 && subjectid ==-1 && typeid==-1){  //年级
            pagingVO.setIntergertemp(gradeid);
            return stuMapperCustom.selectPrePayStuByGrade(pagingVO);

        }
        else if(gradeid==-1 && subjectid !=-1 && typeid==-1){  //学科
            pagingVO.setIntergertwo(subjectid);
            return stuMapperCustom.selectPrePayStuBySub(pagingVO);
        }
        else if(gradeid==-1 && subjectid ==-1 && typeid!=-1){  //类型
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectPrePayStuByType(pagingVO);
        }
        else if(gradeid==-1 && subjectid ==-1 && typeid==-1){  //全部
            return stuMapperCustom.selectByPrePayStu(pagingVO);
        }
        else {  //学科 类型 类型
            pagingVO.setIntergertemp(gradeid);
            pagingVO.setIntergertwo(subjectid);
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectPrePayStuByGradeSubType(pagingVO);
        }
    }


    //根据选项全部查找预缴费的学生
    public List<StuCustom> findAllStuByPrePayStuAndSelect(Integer gradeid, Integer subjectid, Integer typeid) throws Exception{
        PagingVO pagingVO = new PagingVO();
        if(gradeid==-1 && subjectid !=-1 && typeid!=-1) {    //学科 类型
            pagingVO.setIntergertwo(subjectid);
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectAllPrePayStuBySubAndType(pagingVO);

        }
        else if (gradeid!=-1 && subjectid ==-1 && typeid!=-1){    //年级 类型
            pagingVO.setIntergertemp(gradeid);
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectAllPrePayStuByGradeAndType(pagingVO);
        }
        else if(gradeid!=-1 && subjectid !=-1 && typeid==-1){ //年级 学科
            pagingVO.setIntergertemp(gradeid);
            pagingVO.setIntergertwo(subjectid);
            return stuMapperCustom.selectAllPrePayStuByGradeAndSub(pagingVO);
        }
        else if(gradeid!=-1 && subjectid ==-1 && typeid==-1){  //年级
            pagingVO.setIntergertemp(gradeid);
            return stuMapperCustom.selectAllPrePayStuByGrade(pagingVO);

        }
        else if(gradeid==-1 && subjectid !=-1 && typeid==-1){  //学科
            pagingVO.setIntergertwo(subjectid);
            return stuMapperCustom.selectAllPrePayStuBySub(pagingVO);
        }
        else if(gradeid==-1 && subjectid ==-1 && typeid!=-1){  //类型
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectAllPrePayStuByType(pagingVO);
        }
        else if(gradeid==-1 && subjectid ==-1 && typeid==-1){  //全部
            return stuMapperCustom.selectAllByPrePayStu();
        }
        else {  //学科 类型 类型
            pagingVO.setIntergertemp(gradeid);
            pagingVO.setIntergertwo(subjectid);
            pagingVO.setIntergerthree(typeid);
            return stuMapperCustom.selectAllPrePayStuByGradeSubType(pagingVO);
        }
    }


    public int countPrePayStuBySelect(Integer gradeid, Integer subjectid, Integer typeid) throws  Exception{
        if(gradeid==-1 && subjectid !=-1 && typeid!=-1) {    //学科 类型
            return stuMapper.countPrePayBySubAndType(subjectid, typeid);
        }
        else if (gradeid!=-1 && subjectid ==-1 && typeid!=-1){    //年级 类型
            return stuMapper.countPrePayByGradeAndType(gradeid,typeid);
        }
        else if(gradeid!=-1 && subjectid !=-1 && typeid==-1){ //年级 学科
            return stuMapper.countPrePayByGradeAndSub(gradeid,subjectid);
        }
        else if(gradeid!=-1 && subjectid ==-1 && typeid==-1){  //年级
            return stuMapper.countPrePayByGrade(gradeid);
        }
        else if(gradeid==-1 && subjectid !=-1 && typeid==-1){  //学科
            return stuMapper.countPrePayBySub(subjectid);
        }
        else if(gradeid==-1 && subjectid ==-1 && typeid!=-1){  //类型
            return stuMapper.countPrePayByType(typeid);
        }
        else if(gradeid==-1 && subjectid ==-1 && typeid==-1){  //全部
            return stuMapper.countPreStuByPayStu();
        }
        else {  //学科 类型 类型
            return stuMapper.countPrePayByGradeSubType(gradeid,subjectid,typeid);
        }
    }




    public List<StuCustom> findAllStuByUnPayStu(Integer gradeid , Integer teleType) throws Exception{

        if(gradeid==-1 && teleType==0){  //全部 全部
            return stuMapperCustom.selectAllByUnPayStu();
        }
        else if(gradeid==-1 && teleType==1) { //全部 有电话
            return stuMapperCustom.selectAllByUnPayStuTel();
        }
        else if(gradeid==-1 && teleType==2){  //全部 没有电弧
            return stuMapperCustom.selectAllByUnPayStuNoTel();
        }
        else if (gradeid!=-1 && teleType==0){ //分年级 全部
            return stuMapperCustom.selectAllUnPayStuByGrade(gradeid);

        }
        else if(gradeid!=-1 && teleType==1){ //分年级 有电话
            return stuMapperCustom.selectAllUnPayStuByGradeTel(gradeid);
        }
        else if (gradeid!=-1 && teleType==2) //分年级 没有电话

        {
            return stuMapperCustom.selectAllUnPayStuByGradeNoTel(gradeid);
        }
        else {
            return null;
        }
    }

    public List<StuCustom> findStuByUnPayStu(Integer toPageNo, Integer gradeid, Integer teleType) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        if(gradeid==-1 && teleType==0){  //全部 全部
            return stuMapperCustom.selectByUnPayStu(pagingVO);
        }
        else if(gradeid==-1 && teleType==1) { //全部 有电话
            return stuMapperCustom.selectByUnPayStuTel(pagingVO);
        }
        else if(gradeid==-1 && teleType==2){  //全部 没有电弧
            return stuMapperCustom.selectByUnPayStuNoTel(pagingVO);
        }
        else if (gradeid!=-1 && teleType==0){ //分年级 全部
            pagingVO.setIntergertemp(gradeid);
            return stuMapperCustom.selectUnPayStuByGrade(pagingVO);

        }
        else if(gradeid!=-1 && teleType==1){ //分年级 有电话
          pagingVO.setIntergertemp(gradeid);
            return stuMapperCustom.selectUnPayStuByGradeTel(pagingVO);
        }
        else if (gradeid!=-1 && teleType==2) //分年级 没有电话

        {
            pagingVO.setIntergertemp(gradeid);
           return stuMapperCustom.selectUnPayStuByGradeNoTel(pagingVO);
        }
        else {
            return null;
        }
    }

    public void removeStuByID(Integer stuID) throws Exception{
        stuMapper.deleteByStuID(stuID);
    }

    public void updataStuHisByID(Integer stuid) throws Exception{
        stuMapper.updataStuHis(stuid);
    }

    //更新学生的年级信息+1
    public void updateStuGrade( ) throws Exception{
        stuMapper.updataGradeID();
    }

    //更新学生的年级信息-1
    public void updateStuGradeDec( ) throws Exception{
        stuMapper.updataGradeDec();
    }

    //获取优秀学生信息 分页显示
    public  List<StuCustom> findOutStandStu(Integer toPageNo ) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        return stuMapperCustom.selectOutstandStu(pagingVO);
    }
    //获取优秀学生信息 全部
    public  List<StuCustom> findAllOutStandStu() throws Exception{

        return stuMapperCustom.selectAllOutstandStu();
    }

    public  int getCountByOutstand() throws Exception{
        return stuMapper.countOutstandStu();
    }

    public  List<Stu> findStuByStuTel(String stutel) throws Exception{
        return  stuMapper.selectStuTel(stutel);
    }

    public  List<Stu> findStuByMoTel(String mothertel) throws Exception{
        return  stuMapper.selectMotherTel(mothertel);
    }

    public  List<Stu> findStuByFaTel(String fathertel) throws Exception{
        return  stuMapper.selectFatherTel(fathertel);
    }

    public List<StuCustom> findLister(Integer toPageNo, String name, Date startdate, Date enddate) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setStringtemp(name);
        pagingVO.setStartdate(startdate);
        pagingVO.setEnddate(enddate);
        return stuMapperCustom.selectByLister(pagingVO);
    }

    public  int getCountLister(String name, Date startdate, Date enddate) throws Exception{
        return stuMapper.countLister(name,startdate,enddate);
    }

    public List<StuCustom> findUpdater(Integer toPageNo, String name, Date startdate, Date enddate) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setStringtemp(name);
        pagingVO.setStartdate(startdate);
        pagingVO.setEnddate(enddate);
        return stuMapperCustom.selectByUpdater(pagingVO);
    }

    public  int getCountUpdater(String name, Date startdate, Date enddate) throws Exception{
        return stuMapper.countUpdater(name,startdate,enddate);
    }

    //检验查询（起始时间，终止时间，专业）
    public List<StuCustom> findCheck(Integer toPageNo, String majorName, Date startdate, Date enddate) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setStringtemp(majorName);
        pagingVO.setStartdate(startdate);
        pagingVO.setEnddate(enddate);
        return stuMapperCustom.selectByCheck(pagingVO);
    }

    public int getCountCheck(String majorName, Date startdate, Date enddate) throws Exception{
        return stuMapper.countCheck(majorName,startdate,enddate);
    }

    //负责人新录入信息提醒
    public List<StuCustom> leaderReceiveRemind(Integer toPageNo,Integer permission, List<Integer> gradeList) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", gradeList);
        map.put("pagingVO",pagingVO);

        if(permission==0){  //pay
            return stuMapperCustom.selectLeaderReceiveByGradePay(map);
        }
        else if(permission ==1){  //unpay
            return stuMapperCustom.selectLeaderReceiveByGradeUnPay(map);
        }else {     //pay and unpay
            return stuMapperCustom.selectLeaderReceiveByGrade(map);
        }
    }

    // 计数 负责人新录入信息提醒
    public int countLeaderReceiveRemind(Integer permission, List<Integer> gradeList) throws Exception{
        if(permission==0){  //pay
            return stuMapper.countLeaderReceiveByGradePay(gradeList);
        }
        else if(permission ==1){  //unpay
            return stuMapper.countLeaderReceiveByGradeUnPay(gradeList);
        }else {     //pay and unpay
            return stuMapper.countLeaderReceiveByGrade(gradeList);
        }
    }

    //校长新录入信息提醒
    public List<StuCustom> masterReceiveRemind(Integer toPageNo,Integer permission, List<Integer> gradeList) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", gradeList);
        map.put("pagingVO",pagingVO);

        if(permission==0){  //pay
            return stuMapperCustom.selectMasterReceiveByGradePay(map);
        }
        else if(permission ==1){  //unpay
            return stuMapperCustom.selectMasterReceiveByGradeUnPay(map);
        }else {     //pay and unpay
            return stuMapperCustom.selectMasterReceiveByGrade(map);
        }
    }

    // 计数
    public int countMasterReceiveRemind(Integer permission, List<Integer> gradeList) throws Exception{
        if(permission==0){  //pay
            return stuMapper.countMasterReceiveByGradePay(gradeList);
        }
        else if(permission ==1){  //unpay
            return stuMapper.countMasterReceiveByGradeUnPay(gradeList);
        }else {     //pay and unpay
            return stuMapper.countMasterReceiveByGrade(gradeList);
        }
    }

    //负责人更新提醒
    public List<StuCustom> leaderUpdateRemind(Integer toPageNo,Integer permission, List<Integer> gradeList) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", gradeList);
        map.put("pagingVO",pagingVO);

        if(permission==0){  //pay
            return stuMapperCustom.selectLeaderUpdateByGradePay(map);
        }
        else if(permission ==1){  //unpay
            return stuMapperCustom.selectLeaderUpdateByGradeUnPay(map);
        }else {     //pay and unpay
            return stuMapperCustom.selectLeaderUpdateByGrade(map);
        }
    }

    // 计数
    public int countLeaderUpdateRemind(Integer permission, List<Integer> gradeList) throws Exception{
        if(permission==0){  //pay
            return stuMapper.countLeaderUpdateByGradePay(gradeList);
        }
        else if(permission ==1){  //unpay
            return stuMapper.countLeaderUpdateByGradeUnPay(gradeList);
        }else {     //pay and unpay
            return stuMapper.countLeaderUpdateByGrade(gradeList);
        }
    }

    //校长更新提醒
    public List<StuCustom> masterUpdateRemind(Integer toPageNo,Integer permission, List<Integer> gradeList) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", gradeList);
        map.put("pagingVO",pagingVO);

        if(permission==0){  //pay
            return stuMapperCustom.selectMasterUpdateByGradePay(map);
        }
        else if(permission ==1){  //unpay
            return stuMapperCustom.selectMasterUpdateByGradeUnPay(map);
        }else {     //pay and unpay
            return stuMapperCustom.selectMasterUpdateByGrade(map);
        }
    }

    // 计数
    public int countMasterUpdateRemind(Integer permission, List<Integer> gradeList) throws Exception{
        if(permission==0){  //pay
            return stuMapper.countMasterUpdateByGradePay(gradeList);
        }
        else if(permission ==1){  //unpay
            return stuMapper.countMasterUpdateByGradeUnPay(gradeList);
        }else {     //pay and unpay
            return stuMapper.countMasterUpdateByGrade(gradeList);
        }
    }

    public   List<StuCustom> adminReceiveRemind(Integer toPageNo) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        return stuMapperCustom.selectAdminReceive(pagingVO);
    }

    public   List<StuCustom> adminUpdateRemind(Integer toPageNo) throws Exception{
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        return stuMapperCustom.selectAdminUpdate(pagingVO);
    }

     //查找录入人
    public String findRecordPerson(Integer stuid) throws Exception{
        return stuMapper.selectRecordPersonByIndex(stuid);
    }

    //更新录入人
    public void updateRecordPerson(Integer stuid, String name) throws Exception{
        stuMapper.updateRecordPersonByIndex(stuid,name);
    }



}
