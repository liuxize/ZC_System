package com.system.controller;

import com.system.po.*;
import com.system.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/master")
public class MasterController {





    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    @Resource(name = "stuServiceImpl")
    private StuService stuService;

    @Resource(name = "lessonServiceImpl")
    private LessonService lessonService;

    @Resource(name = "examServiceImpl")
    private ExamService examService;

    @Resource(name = "gradeServiceImpl")
    private GradeService gradeService;

    @Resource(name = "signServiceImpl")
    private SignService signService;


    @Resource(name = "remindServiceImpl")
    private RemindService remindService;

    @Resource(name = "classTypeServiceImpl")
    private ClassTypeService classTypeService;

    @Resource(name = "subjectServiceImpl")
    private SubjectService subjectService;

    @Resource(name = "majorServiceImpl")
    private MajorService majorService;

    @Resource(name = "schoolServiceImpl")
    private SchoolService schoolService;

    @Resource(name = "imageServiceImpl")
    private ImageService imageService;

    @Resource(name = "campusServiceImpl")
    private CampusService campusService;

    @Resource(name = "announceServiceImpl")
    private AnnounceService announceService;

    // 添加用户信息表一（Get？？？）
    @RequestMapping(value = "/addTableOne", method = {RequestMethod.GET})
    public String showAddTable(Model model) throws Exception {
        List<Major> majorList = majorService.findAllMajor();
        List<School> schoolList =schoolService.findAllSchool();
        List<Grade> list = gradeService.findAllGrade();
        List<Campus> campusList = campusService.findAllCampus();
        model.addAttribute("schoolList",schoolList);
        model.addAttribute("gradeList", list);
        model.addAttribute("majorList", majorList);
        model.addAttribute("campusList", campusList);
        //stuService.save(stu);
        //重定向
        return "master/addTableOne";
    }

    // 添加用户信息表一（POST）
    @RequestMapping(value = "/addTableOne", method = {RequestMethod.POST})
    public String addTableOne(Stu stu, Model model) throws Exception {


        Date data = new Date();
        stu.setRecorddate(data);

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(data);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (stu.getSchooltext()!="")
            stu.setSchooltext(datestring+"  " + username+":  " + stu.getSchooltext());
        if (stu.getFamilytext()!="")
            stu.setFamilytext(datestring+"  " + username+":  " + stu.getFamilytext());
        if (stu.getImprovetext()!="")
            stu.setImprovetext(datestring+"  " + username+":  " + stu.getImprovetext());
        if (stu.getSupporttext()!="")
            stu.setSupporttext(datestring+"  " + username+":  " + stu.getSupporttext());
        if (stu.getEducationtext()!="")
            stu.setEducationtext(datestring+"  " + username+":  " + stu.getEducationtext());
        if (stu.getStudytext()!="")
            stu.setStudytext(datestring+"  " + username+":  " + stu.getStudytext());

        stu.setRecordperson(username);
        stuService.save(stu);
        Integer stuID = stu.getStuid();
        signService.addStuSign(stuID);

        String encodeID = Base64.getEncoder(). encodeToString(stuID.toString().getBytes(StandardCharsets.UTF_8));

        String url = "redirect:/master/editTableOne?encodeID=" + encodeID;
        return url;
    }

    //  添加信息页面显示
    @RequestMapping(value = "/editTableOne", method = {RequestMethod.GET})
    public String showTableOneUI(String encodeID, Model model) throws Exception {

        //对stuid进行Base64解密，获取真实的id
        String unDecodeStr = new String(Base64.getDecoder().decode(encodeID),StandardCharsets.UTF_8);
        Integer stuid = null;
        if(unDecodeStr!=null){
            stuid = Integer.valueOf(unDecodeStr);
        }

        Boolean permission =false;//年级和缴费权限
        Boolean payPermission = false; //缴费权限
        StuCustom stu = stuService.findById(stuid);
        Sign sign = signService.findSignByStuID(stuid);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<Integer> permissionList = userloginService.findTeacherPerssion(username);  //获取该用户的教师权限
        Userlogin userlogin = userloginService.findUser(username);//获取用户的缴费权限
        //判断是否缴费
        Integer lessonnum = lessonService.getcountLessonByPay(stuid);
        if(userlogin.getPermission()==0 && lessonnum>0){
            payPermission=true;
        }else if (userlogin.getPermission()==1 &&lessonnum==0){
            payPermission=true;
        }else if(userlogin.getPermission()==2){
            payPermission=true;
        }

        //判断年级
        for(int i=0;i<permissionList.size();i++)
        {
            if((stu.getGradeid()==permissionList.get(i))&&(payPermission==true)){
                permission =true;
                break;
            }
        }

        //将有关电话信息的资料设置为空
        if(permission==false)
        {
            stu.setFathername("");
            stu.setFathercompany("");
            stu.setFatherdegree("");
            stu.setFatherjob("");
            stu.setMothername("");
            stu.setMothercompany("");
            stu.setMotherdegree("");
            stu.setMotherjob("");
            stu.setMastername("");
            stu.setMasteraddress("");
            stu.setMasterdegree("");
            stu.setMastersex("");
            stu.setAddress("");
            stu.setStubirth(null);
            stu.setMotherbirth(null);
            stu.setFatherbirth(null);
            stu.setMasterbirth(null);
            stu.setStutel("");
            stu.setFathertel("");
            stu.setMothertel("");
            stu.setMastertel("");
            stu.setMajor("");
            stu.setCheckdate(null);

            stu.setSchooltext("");
            stu.setSchooltexthis("");
            stu.setFamilytext("");
            stu.setFamilytexthis("");
            stu.setStudytext("");
            stu.setStudytexthis("");
            stu.setEducationtext("");
            stu.setEducationtexthis("");
            stu.setSupporttext("");
            stu.setSupporttexthis("");
            stu.setImprovetext("");
            stu.setImprovetexthis("");
        }

        List<School> schoolList =schoolService.findAllSchool();
        List<Major> majorList = majorService.findAllMajor();
        String br ="\n";
        List<Images> imagesList = imageService.findImageByStuID(stuid);
        Integer imagesNum = imagesList.size(); //所有图片的数量
        Integer unsignImageNum = imageService.getCountUnsignImage(stuid);//未签字图片的数量
        model.addAttribute("unsignImageNum",unsignImageNum);
        model.addAttribute("imagesNum",imagesNum);
        model.addAttribute("imagesList",imagesList);
        model.addAttribute("schoolList",schoolList);
        model.addAttribute("majorList",majorList);
        model.addAttribute("permission", permission);
        model.addAttribute("stumessage", stu);
        model.addAttribute("signmessage", sign);
        model.addAttribute("br", br);

        return "master/editTableOne";
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<表一添加部分操作开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    // 添加用户信息表一（POST）
    @RequestMapping(value = "/addName", method = {RequestMethod.POST})
    public String addName(Integer stuid, String stuName) throws Exception {

        stuService.addStuNameByID(stuid, stuName);
        signService.changeStuNameSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addSchool", method = {RequestMethod.POST})
    public String addSchool(Integer stuid, String school) throws Exception {


        stuService.addSchoolByID(stuid, school);
        signService.changeSchoolSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addMajor", method = {RequestMethod.POST})
    public String addMajor(Integer stuid, String major) throws Exception {


        stuService.addMajorByID(stuid, major);
        signService.changeMajorSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addMother", method = {RequestMethod.POST})
    public String addMother(Integer stuid, String motherName) throws Exception {


        stuService.addMotherNameByID(stuid, motherName);
        signService.changeMoNameSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }


    @RequestMapping(value = "/addMoCompany", method = {RequestMethod.POST})
    public String addMoCompany(Integer stuid, String motherCompany) throws Exception {


        stuService.addMoCompanyByID(stuid, motherCompany);
        signService.changeMoCompanySign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addMoJob", method = {RequestMethod.POST})
    public String addMoJob(Integer stuid, String motherJob) throws Exception {


        stuService.addMoJobByID(stuid, motherJob);
        signService.changeMoJobSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addFather", method = {RequestMethod.POST})
    public String addFather(Integer stuid, String fatherName) throws Exception {


        stuService.addFaNameByID(stuid, fatherName);
        signService.changeFaNameSign(stuid);

        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }


    @RequestMapping(value = "/addFaCompany", method = {RequestMethod.POST})
    public String addFaCompany(Integer stuid, String fathercompany) throws Exception {


        stuService.addFaCompanyByID(stuid, fathercompany);
        signService.changeFaCompanySign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addFaJob", method = {RequestMethod.POST})
    public String addFaJob(Integer stuid, String fatherJob) throws Exception {


        stuService.addFaJobByID(stuid, fatherJob);
        signService.changeFaJobSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addBirth", method = {RequestMethod.POST})
    public String addBirth(Integer stuid, Date birth,Model model ) throws Exception {

        if (birth == null) {
            model.addAttribute("message", "请输入完整日期");
            return "error";
        }

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = stuService.getBirthByID(stuid);
        String birthstring="";
        if (birthday!=null){
            birthstring= datetype.format(birthday);
            System.out.println(birthstring);
        }
        stuService.addStuBirthByID(stuid,birth,birthstring);
        signService.changeBirthSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addMoBirth", method = {RequestMethod.POST})
    public String addMoBirth(Integer stuid, Date motherbirth,Model model ) throws Exception {

        if (motherbirth == null) {
            model.addAttribute("message", "请输入完整日期");
            return "error";
        }

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = stuService.getMoBirthByID(stuid);
        String birthstring="";
        if (birthday!=null){
            birthstring= datetype.format(birthday);

        }
        stuService.addMotherBirthByID(stuid,motherbirth,birthstring);
        signService.changeMoBirthSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addFaBirth", method = {RequestMethod.POST})
    public String addFaBirth(Integer stuid, Date fatherbirth,Model model ) throws Exception {

        if (fatherbirth == null) {
            model.addAttribute("message", "请输入完整日期");
            return "error";
        }

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = stuService.getFaBirthByID(stuid);
        String birthstring="";
        if (birthday!=null){
            birthstring= datetype.format(birthday);
        }
        stuService.addFatherBirthByID(stuid,fatherbirth,birthstring);
        signService.changeFaBirthSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addStuTel", method = {RequestMethod.POST})
    public String addStuTel(Integer stuid, String stuTel) throws Exception {


        stuService.addStuTelByID(stuid, stuTel);
        signService.changeStuTelSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addMotherTel", method = {RequestMethod.POST})
    public String addMotherTel(Integer stuid, String motherTel) throws Exception {


        stuService.addMoTelByID(stuid, motherTel);
        signService.changeMotherTelSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addFatherTel", method = {RequestMethod.POST})
    public String addFatherTel(Integer stuid, String fatherTel) throws Exception {


        stuService.addFaTelByID(stuid, fatherTel);
        signService.changeFatherTelSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addFatherGegree", method = {RequestMethod.POST})
    public String addFatherGegree(Integer stuid, String fatherDegree) throws Exception {


        stuService.addFatherDegreeByID(stuid,fatherDegree);
        signService.changeFaDegreeSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addMotherGegree", method = {RequestMethod.POST})
    public String addMotherGegree(Integer stuid, String motherDegree) throws Exception {


        stuService.addMotherDegreeByID(stuid,motherDegree);
        signService.changeMoDegreeSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addAddress", method = {RequestMethod.POST})
    public String addAddress(Integer stuid, String addAddress) throws Exception {


        stuService.addAddressByID(stuid, addAddress);
        signService.changeAddressSign(stuid);

        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addMaster", method = {RequestMethod.POST})
    public String addMaster(Integer stuid, String addMaster) throws Exception {


        stuService.addMasterByID(stuid, addMaster);
        signService.changeMasterSign(stuid);

        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addMasterTel", method = {RequestMethod.POST})
    public String addMasterTel(Integer stuid, String addmastertel) throws Exception {


        stuService.addMasterTelByID(stuid, addmastertel);
        signService.changeMasterTelSign(stuid);

        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;

    }

    //添加学生性别
    @RequestMapping(value = "/addStuSex", method = {RequestMethod.POST})
    public String addStuSex(Integer stuid, String addstusex) throws Exception {

        stuService.addStuSexByID(stuid,addstusex);
        signService.changeStuSexSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }
    //添加班主任地址
    @RequestMapping(value = "/addMasterAddress", method = {RequestMethod.POST})
    public String addMasterAddress(Integer stuid, String addmasteraddress) throws Exception {

        stuService.addMasterAddressByID(stuid,addmasteraddress);
        signService.changeMasterAddressSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }
    //添加班主任学历
    @RequestMapping(value = "/addMasterDegree", method = {RequestMethod.POST})
    public String addMasterDegree(Integer stuid, String addmasterdegree) throws Exception {
        stuService.addMasterDegreeByID(stuid,addmasterdegree);
        signService.changeMasterDegreeSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }
    //添加班主任性别
    @RequestMapping(value = "/addMasterSex", method = {RequestMethod.POST})
    public String addMasterSex(Integer stuid, String addmastersex) throws Exception {
        stuService.addMasterSexByID(stuid,addmastersex);
        signService.changeMasterSexSign(stuid);

        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    //添加班主任生日
    @RequestMapping(value = "/addMasterBirth", method = {RequestMethod.POST})
    public String addMasterBirth(Integer stuid, Date addmasterbirth) throws Exception {

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = stuService.getMasterBirthByID(stuid);
        String birthstring="";
        if (birthday!=null){
            birthstring= datetype.format(birthday);
        }
        stuService.addMasterBirthByID(stuid,addmasterbirth,birthstring);
        signService.changeMasterBirthSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }
    //添加检验日期信息
    @RequestMapping(value = "/addCheckDate", method = {RequestMethod.POST})
    public String addCheckDate(Integer stuid, Date addcheckdate) throws Exception {

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        Date checkdate = stuService.getCheckDateByID(stuid);
        String datestring="";
        if (checkdate!=null){
            datestring= datetype.format(checkdate);
        }
        stuService.addCheckDateByID(stuid,addcheckdate,datestring);
        signService.changeCheckDate(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addSchoolText", method = {RequestMethod.POST})
    public String addSchoolText(Integer stuid, String addschooltext) throws Exception {


        Date date = new Date();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(date);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        addschooltext = datestring+"  " + username+":  " + addschooltext;

        stuService.addSchoolTextByID(stuid, addschooltext);
        signService.changeSchoolTextSign(stuid);
        //  stuService.updataStuHisByID(stuid);

        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addFamilyText", method = {RequestMethod.POST})
    public String addFamilyText(Integer stuid, String addfamilytext) throws Exception {


        Date date = new Date();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(date);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        addfamilytext = datestring+"  " + username+":  " + addfamilytext;


        stuService.addFamilyTextByID(stuid, addfamilytext);
        signService.changeFamilyTextSign(stuid);

        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addStudyText", method = {RequestMethod.POST})
    public String addStudyText(Integer stuid, String addstudytext) throws Exception {

        Date date = new Date();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(date);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        addstudytext = datestring+"  " + username+":  " + addstudytext;

        stuService.addStudyTextByID(stuid, addstudytext);
        signService.changeStudyTextSign(stuid);

        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addEducationText", method = {RequestMethod.POST})
    public String addEducationText(Integer stuid, String addedutext) throws Exception {

        Date date = new Date();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(date);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        addedutext = datestring+"  " + username+":  " + addedutext;

        stuService.addEducationTextByID(stuid, addedutext);
        signService.changeEducationTextSign(stuid);

        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addSupportText", method = {RequestMethod.POST})
    public String addSupportText(Integer stuid, String addsupporttext) throws Exception {

        Date date = new Date();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(date);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        addsupporttext = datestring+"  " + username+":  " + addsupporttext;

        stuService.addSupportTextByID(stuid, addsupporttext);
        signService.changeSupportTextSign(stuid);

        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addImproveText", method = {RequestMethod.POST})
    public String addImproveText(Integer stuid, String addimprovetext) throws Exception {

        Date date = new Date();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(date);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        addimprovetext = datestring+"  " + username+":  " + addimprovetext;

        stuService.addImproveTextByID(stuid, addimprovetext);
        signService.changeImproveTextSign(stuid);

        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;

    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<添加部分信息操作结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<表二操作开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    //  添加信息页面显示
    @RequestMapping(value = "/editTableTwo", method = {RequestMethod.GET})
    public String showTableTwoUI(String encodeID, Model model) throws Exception {

        //对stuid进行Base64解密，获取真实的id
        String unDecodeStr = new String(Base64.getDecoder().decode(encodeID),StandardCharsets.UTF_8);

        Integer stuid = null;
        if(unDecodeStr!=null){
            stuid = Integer.valueOf(unDecodeStr);
        }
        StuCustom stu = stuService.findById(stuid);
        List<Exam> examlist = examService.findExamByStuID(stuid);
        model.addAttribute("stumessage", stu);
        model.addAttribute("examlist", examlist);

        return "master/editTableTwo";
    }

    @RequestMapping(value = "/addExam", method = {RequestMethod.GET})
    public String addExamUI(Integer stuid, Model model) throws Exception {

        model.addAttribute("StuID", stuid);
        return "master/addExam";
    }

    // 添加学生信息操作
    @RequestMapping(value = "/addExam", method = {RequestMethod.POST})
    public String addExam(Integer stuid, Exam exam, Model model) throws Exception {


        exam.setStuid(stuid);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        exam.setUpdateperson(username);
        examService.save(exam);
        signService.SetChangeSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableTwo?encodeID=" + encodeID;
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<表二操作结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/


    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<表三操作开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    //  添加信息页面显示
    @RequestMapping(value = "/editTableThree", method = {RequestMethod.GET})
    public String showTableThreeUI(String encodeID, Model model) throws Exception {

        //对stuid进行Base64解密，获取真实的id
        String unDecodeStr = new String(Base64.getDecoder().decode(encodeID),StandardCharsets.UTF_8);
        Integer stuid = null;
        if(unDecodeStr!=null){
            stuid = Integer.valueOf(unDecodeStr);
        }

        StuCustom stu = stuService.findById(stuid);
        List<LessonCustom> lessonlist = lessonService.findByStuID(stuid);
        model.addAttribute("stumessage", stu);
        model.addAttribute("lesList", lessonlist);

        return "master/editTableThree";
    }


    @RequestMapping(value = "/addLesson", method = {RequestMethod.GET})
    public String addLessonUI(Integer stuid, Model model) throws Exception {

        List<ClassType> typelist= classTypeService.findAllClassType();
        List<com.system.po.Subject> subjectlist = subjectService.findAllSubject();
        model.addAttribute("typelist", typelist);
        model.addAttribute("subjectlist", subjectlist);
        model.addAttribute("StuID", stuid);

        return "master/addLesson";
    }

    // 添加学生信息操作
    @RequestMapping(value = "/addLesson", method = {RequestMethod.POST})
    public String addLesson(Integer stuid, Lesson lesson, Model model) throws Exception {


        lesson.setStuid(stuid);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        lesson.setOperator(username);
        lessonService.save(lesson);
        signService.SetChangeSign(stuid);
        String encodeID = Base64.getEncoder(). encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableThree?encodeID=" + encodeID;
    }

    //添加签到信息
    @RequestMapping(value = "/addDutyDate", method = {RequestMethod.POST})
    public String addMajor(Integer stuid, Integer lessonid, String dutydate) throws Exception {
        dutydate = dutydate + "  ";
        lessonService.updateDutyDateByLessonID(dutydate, lessonid);
        //更新录入老师
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        lessonService.updateRecordTeacher(username,lessonid);
        signService.SetChangeSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableThree?encodeID=" + encodeID;
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<表三操作结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    // 搜索姓名操作
    @RequestMapping("/searchName")
    public String searchNameUI(Model model, Integer page) throws Exception {
        return "master/searchName";
    }

    // 搜索姓名操作
    @RequestMapping(value = "/selectName", method = {RequestMethod.POST})
    public String searchName(String StudentName, Model model) throws Exception {
        List<StuCustom> list = stuService.findByName(StudentName);
        model.addAttribute("stuList", list);
        return "master/searchName";
    }

/////////////////////////////////////////////////////////////////////////////////////////////
    //接收提醒
    @RequestMapping(value = "/remindReceive", method = {RequestMethod.GET})
    public String remindReceive(Model model, Integer page) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<Integer> permissionList = userloginService.findTeacherPerssion(username);  //获取该用户的年纪权限
        Userlogin userlogin = userloginService.findUser(username);//获取用户的缴费权限 0缴费 1未交费 2 全部
        Integer permission = userlogin.getPermission(); //获取该用户的查看缴费情况权限

        List<StuCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();

        //设置总页数
        pagingVO.setTotalCount(stuService.countMasterUpdateRemind(permission,permissionList));

        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list=stuService.masterUpdateRemind(1,permission,permissionList);
        } else {
            pagingVO.setToPageNo(page);
            list=stuService.masterUpdateRemind(page,permission,permissionList);
        }


        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);
        return "master/remindReceive";
    }

    //新录入信息提醒
    @RequestMapping(value = "/remindNewStu", method = {RequestMethod.GET})
    public String remindNewStu(Model model, Integer page) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<Integer> permissionList = userloginService.findTeacherPerssion(username);  //获取该用户的年纪权限
        Userlogin userlogin = userloginService.findUser(username);//获取用户的缴费权限 0缴费 1未交费 2 全部
        Integer permission = userlogin.getPermission(); //获取该用户的查看缴费情况权限


        List<StuCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(stuService.countMasterReceiveRemind(permission,permissionList));

        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list=stuService.masterReceiveRemind(1,permission,permissionList);
        } else {
            pagingVO.setToPageNo(page);
            list=stuService.masterReceiveRemind(page,permission,permissionList);
        }

        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);
        return "master/remindNewStu";
    }


    //新录入信息处理
    @RequestMapping(value = "/signConfirm", method = {RequestMethod.GET})
    private String signConfirm(Integer stuid, String currentPage, Model model) throws Exception {

        Sign sign = signService.findSignByStuID(stuid);
        Stu stu = stuService.findById(stuid);
        Integer leadernum = userloginService.getCountLeaderByGradeID(stu.getGradeid());
        if ((sign.getLeadersignid().equals(0) && (leadernum != 0))) {     //如果负责人未签字, 且当前有对应年级的负责人
             //   (sign.getLeadersignid().equals(1) && (leadernum == 0))) {  //负责人被删除, 负责人标志是1
            model.addAttribute("message", "负责人未签字");
            return "error";

        }
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        signService.masterSign(stuid,username);
        stuService.updataStuHisByID(stuid);
        examService.changeExamSign(stuid);
        lessonService.changeLessonSign(stuid);
        imageService.changeImageSign(stuid);
        return "redirect:/master/remindNewStu?page=" + currentPage;
    }

    //更新的信息处理
    @RequestMapping(value = "/updateConfirm", method = {RequestMethod.GET})
    private String updateConfirm(Integer stuid, String currentPage, Model model) throws Exception {
        Sign sign = signService.findSignByStuID(stuid);
        if(sign.getLeadersignid().equals(0)){
            Stu stu = stuService.findById(stuid);
            Integer leadernum = userloginService.getCountLeaderByGradeID(stu.getGradeid());
            if(leadernum!=0){
                model.addAttribute("message", "负责人未签字");
                return "error";
            }
        }
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        signService.masterSign(stuid,username);
        stuService.updataStuHisByID(stuid);
        examService.changeExamSign(stuid);
        lessonService.changeLessonSign(stuid);
        imageService.changeImageSign(stuid);
        return "redirect:/master/remindReceive?page=" + currentPage;
    }

    /////////////////////////////////////提醒功能////////////////////////////////////////////////////////

    @RequestMapping(value = "/remindBirth", method = {RequestMethod.GET})
    public String remindBirthUI(Model model, Integer page) throws Exception {

        List<Birthday> birthdayList = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(remindService.getCountBirth());
        // System.out.println("remind"+stuService.getCountByPay());
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            birthdayList = remindService.findAllBirthday(1);
        } else {
            pagingVO.setToPageNo(page);
            birthdayList = remindService.findAllBirthday(page);
        }
        model.addAttribute("pagingVO", pagingVO);
        model.addAttribute("birthdayList", birthdayList);
        return "master/remindBirth";
    }

    //缴费提醒（周工作提醒）
    @RequestMapping(value = "/remindPay", method = {RequestMethod.GET})
    public String remindPayUI(Model model,Integer page) throws Exception {
        List<LessonCustom> allStuList = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(remindService.getCountRemindPay());
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            allStuList = remindService.findAllRemindPay(1);
        } else {
            pagingVO.setToPageNo(page);
            allStuList = remindService.findAllRemindPay(page);
        }
        model.addAttribute("allStuList", allStuList);
        model.addAttribute("pagingVO", pagingVO);
        return "master/remindPay";
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    //判断用户名是否存在
    @ResponseBody
    @RequestMapping(value = "/isExist",method = { RequestMethod.POST,RequestMethod.GET })
    public String isExist(String realname) throws Exception {

        String result = null;
        List<StuCustom> stuCustomList = stuService.findByName(realname);
        if (stuCustomList.isEmpty()==true){
            result ="0";
        }
        else {
            result="1";
        }
        return result;
    }

    /////////////////////////////////////////文本记事本/////////////////////////////////////////////////////
    //显示记事本（文本）目录
    @RequestMapping(value = "/showTextDic", method = {RequestMethod.GET})
    public String showTextDicUI(Model model, Integer page) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<TextDic> list = null;
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(userloginService.getCountText(username, 0));

        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findTextByName(1,username,0);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findTextByName(page,username,0);
        }
        model.addAttribute("textList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "master/showTextDic";
    }

    // 添加记事本（文本）目录
    @RequestMapping(value = "/showTextDic", method = {RequestMethod.POST})
    public String showTextDic(String textName, String currentPage, Model model) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        TextDic textDic= new TextDic();
        textDic.setTitle(textName);
        textDic.setUsername(username);
        textDic.setTexttype(0);
        userloginService.saveText(textDic);
        return "redirect:/master/showTextDic?page=" + currentPage;
    }

    // 记事本操作
// 记事本操作
    @RequestMapping(value = "/editNoteText", method = {RequestMethod.GET})
    public String editNoteTextUI(Integer textid, String currentPage, Model model) throws Exception {

        TextDic textDic = userloginService.findTextDicByID(textid);
        if(textDic==null){
            model.addAttribute("message", "该记事本不存在");
            return "error";
        }
        //没有权限访问
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (!textDic.getUsername().equals(username) ) {
            model.addAttribute("message", "没有权限访问");
            return "error";
        }
        model.addAttribute("textDic", textDic.getContent());
        model.addAttribute("textid", textid);
        model.addAttribute("texttitle", textDic.getTitle());
        model.addAttribute("currentPage", currentPage);
        return "master/editNoteText";
    }


    // 记事本操作
    @RequestMapping(value = "/editNoteText", method = {RequestMethod.POST})
    public String editNoteText(TextDic textDic, String currentPage ) throws Exception {
        userloginService.updeTextDicByID(textDic);
        return "redirect:/master/showTextDic?page=" + currentPage;
    }

    @RequestMapping(value = "/editSeNoteText", method = {RequestMethod.GET})
    public String editSeNoteTextUI(Integer textid, String currentPage, Model model) throws Exception {

        TextDic textDic = userloginService.findTextDicByID(textid);
        if(textDic==null){
            model.addAttribute("message", "该记事本不存在");
            return "error";
        }
        //没有权限访问
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (!textDic.getUsername().equals(username) ) {
            model.addAttribute("message", "没有权限访问");
            return "error";
        }
        model.addAttribute("textDic", textDic.getContent());
        model.addAttribute("textid", textid);
        model.addAttribute("texttitle", textDic.getTitle());
        model.addAttribute("currentPage", currentPage);
        return "master/editSeNoteText";
    }


    // 记事本操作
    @RequestMapping(value = "/editSeNoteText", method = {RequestMethod.POST})
    public String editSeNoteText(TextDic textDic, String currentPage ) throws Exception {
        userloginService.updeTextDicByID(textDic);
        System.out.print(currentPage);
        return "redirect:/master/showSeTextDic?page=" + currentPage;
    }

    //删除文本
    @RequestMapping(value = "/removeText", method = {RequestMethod.GET})
    private String removeText(Integer textid,String currentPage) throws Exception {

        userloginService.removeTextByID(textid);
        return "redirect:/master/showTextDic?page=" + currentPage;

    }

    /////////////////////////////////////////表格记事本/////////////////////////////////////////////////////
    //显示记事本目录（表格）
    @RequestMapping(value = "/showNoteDic", method = {RequestMethod.GET})
    public String showNoteDicUI(Model model, Integer page) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<NoteDic> list = null;
        PagingVO pagingVO = new PagingVO();
        //pagingVO.setStringtemp(username);
        pagingVO.setTotalCount(userloginService.getCountNoteDic(username,0));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findNoteDic(1, username,0);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findNoteDic(page,username,0);
        }
        model.addAttribute("noteDicList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "master/showNoteDic";
    }

    // 添加记事本目录（表格）
    @RequestMapping(value = "/showNoteDic", method = {RequestMethod.POST})
    public String showNoteDic(String dicName, String currentPage, Model model) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        //  NoteDic dicname = userloginService.findNoteDic(username,dicName);
//        if (dicname!=null) {
//            model.addAttribute("message", "表名重复,请重新输入");
//            return "error";
//        }

        NoteDic noteDic=new NoteDic();
        noteDic.setDicname(dicName);
        noteDic.setUsername(username);
        noteDic.setDictype(0);
        userloginService.saveNoteDic(noteDic);

        return "redirect:/master/showNoteDic?page=" + currentPage;
    }

    //删除记事本目录
    // 删除学生
    @RequestMapping(value = "/removeNoteDic", method = {RequestMethod.GET})
    private String removeNoteDic(Integer dicid, String currentPage) throws Exception {
      /*  if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            return "master/showStudent";
        }*/
        userloginService.removeNoteDic(dicid);  //删除记事本目录
        userloginService.removeNoteByDicID(dicid);  //删除某个目录对应的内容
        return "redirect:/master/showNoteDic?page=" + currentPage;
    }

    // 添加记事表操作
    @RequestMapping(value = "/addNoteTable", method = {RequestMethod.GET})
    public String addNoteTableUI(Model model,Integer dicid, Integer currentpage) throws Exception {


        List<Grade> gradelist = gradeService.findAllGrade();
        model.addAttribute("gradeList", gradelist);
        model.addAttribute("dicid", dicid);
        model.addAttribute("currentPage", currentpage);

        return "master/addNoteTable";
    }

    // 添加记事表操作
    @RequestMapping(value = "/addNoteTable", method = {RequestMethod.POST})
    public String addNoteTable(NoteTable noteTable, Integer dicid, Integer currentpage) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        noteTable.setUsername(username);
        noteTable.setDicid(dicid);

        userloginService.saveNoteTable(noteTable);

        return "redirect:/master/editNoteTable?dicid="+dicid + "&page=" + currentpage;
    }

    //删除记事本中的一条记录
    @RequestMapping(value = "/removeNoteTable", method = {RequestMethod.GET})
    private String removeNoteTable(Integer noteID , Integer dicID ,String currentPage) throws Exception {
      /*  if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            return "master/showStudent";
        }*/
        userloginService.removeNoteTable(noteID);
        return "redirect:/master/editNoteTable?dicid="+dicID + "&page=" + currentPage;

    }
    //修改记事本中的一条记录
    @RequestMapping(value = "/modifyNoteTable", method = {RequestMethod.GET})
    private String modifyNoteTableUI(Integer noteID, Integer dicid, Integer page, Model model) throws Exception {

        List<Grade> gradelist = gradeService.findAllGrade();
        model.addAttribute("gradeList", gradelist);
        NoteTable noteTable = userloginService.findNoteTableByID(noteID);
        model.addAttribute("noteTable", noteTable);
        model.addAttribute("dicid", dicid);
        model.addAttribute("page", page);
        return "master/modifyNoteTable";
    }

    @RequestMapping(value = "/modifyNoteTable", method = {RequestMethod.POST})
    private String modifyNoteTable(NoteTable noteTable, Integer dicid, Integer page) throws Exception {

        userloginService.updateNoteTableByID(noteTable);
        return "redirect:/master/editNoteTable?dicid="+dicid + "&page=" + page;
    }

    // 记事表操作
    @RequestMapping(value = "/editNoteTable", method = {RequestMethod.GET})
    public String editNoteTableUI(Model model, Integer page, Integer dicid) throws Exception {
        NoteDic noteDic = userloginService.findNoteDicByID(dicid);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (!noteDic.getUsername().equals(username) ) {
            model.addAttribute("message", "没有权限访问");
            return "error";
        }

        List<NoteTable> list = null;
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(userloginService.getCountNoteTable(dicid));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findNoteTableByDicID(1,dicid);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findNoteTableByDicID(page,dicid);
        }

      //  List<NoteTable> noteTableList = userloginService.findAllNoteTableByDicID(dicid);
        model.addAttribute("dicid", dicid);
        model.addAttribute("noteTableList", list);
      //  model.addAttribute("allNoteTableList", noteTableList);
        model.addAttribute("pagingVO", pagingVO);

        return "master/editNoteTable";
    }

    // 记事表操作
    @RequestMapping(value = "/editNoteTable", method = {RequestMethod.POST})
    public String editNoteTable(String notetext) throws Exception {

        // Subject subject = SecurityUtils.getSubject();
        //String username = (String) subject.getPrincipal();
        // userloginService.updateNote(username, notetext);

        return "redirect:/master/editNoteTable";
    }

    ///////////////////////招生信息管理操作////////////////////////////////////
    //显示第二个记事本（文本）目录
    @RequestMapping(value = "/showSeTextDic", method = {RequestMethod.GET})
    public String showSeTextDicUI(Model model, Integer page) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<TextDic> list = null;
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(userloginService.getCountText(username,1));

        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findTextByName(1,username,1);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findTextByName(page,username,1);
        }
        model.addAttribute("textList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "master/showSeTextDic";
    }

    // 添加记事本（文本）目录
    @RequestMapping(value = "/showSeTextDic", method = {RequestMethod.POST})
    public String showSeTextDic(String textName, String currentPage, Model model) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        TextDic textDic= new TextDic();
        textDic.setTitle(textName);
        textDic.setUsername(username);
        textDic.setTexttype(1);
        userloginService.saveText(textDic);
        return "redirect:/master/showSeTextDic?page=" + currentPage;
    }

    @RequestMapping(value = "/removeSeText", method = {RequestMethod.GET})
    private String removeSeText(Integer textid,String currentPage) throws Exception {

        userloginService.removeTextByID(textid);
        return "redirect:/master/showSeTextDic?page=" + currentPage;

    }

    //显示记事本目录（表格）
    @RequestMapping(value = "/showSeNoteDic", method = {RequestMethod.GET})
    public String showSeNoteDicUI(Model model, Integer page) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<NoteDic> list = null;
        PagingVO pagingVO = new PagingVO();
        //pagingVO.setStringtemp(username);
        pagingVO.setTotalCount(userloginService.getCountNoteDic(username,1));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findNoteDic(1, username,1);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findNoteDic(page,username,1);
        }
        model.addAttribute("noteDicList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "master/showSeNoteDic";
    }


    // 添加记事本目录（表格）
    @RequestMapping(value = "/showSeNoteDic", method = {RequestMethod.POST})
    public String showSeNoteDic(String dicName, String currentPage, Model model) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        //  NoteDic dicname = userloginService.findNoteDic(username,dicName);
//        if (dicname!=null) {
//            model.addAttribute("message", "表名重复,请重新输入");
//            return "error";
//        }

        NoteDic noteDic=new NoteDic();
        noteDic.setDicname(dicName);
        noteDic.setUsername(username);
        noteDic.setDictype(1);
        userloginService.saveNoteDic(noteDic);

        return "redirect:/master/showSeNoteDic?page=" + currentPage;
    }

    //删除记事本目录
    // 删除学生
    @RequestMapping(value = "/removeSeNoteDic", method = {RequestMethod.GET})
    private String removeSeNoteDic(Integer dicid, String currentPage) throws Exception {
      /*  if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            return "master/showStudent";
        }*/
        userloginService.removeNoteDic(dicid);  //删除记事本目录
         userloginService.removeNoteByDicID(dicid);  //删除某个目录对应的内容
        return "redirect:/master/showSeNoteDic?page=" + currentPage;
    }

    // 记事表操作
    @RequestMapping(value = "/editSeNoteTable", method = {RequestMethod.GET})
    public String editSeNoteTableUI(Model model, Integer page, Integer dicid) throws Exception {
        NoteDic noteDic = userloginService.findNoteDicByID(dicid);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (!noteDic.getUsername().equals(username) ) {
            model.addAttribute("message", "没有权限访问");
            return "error";
        }

        List<NoteTable> list = null;
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(userloginService.getCountNoteTable(dicid));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findNoteTableByDicID(1,dicid);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findNoteTableByDicID(page,dicid);
        }

        //  List<NoteTable> noteTableList = userloginService.findAllNoteTableByDicID(dicid);
        model.addAttribute("dicid", dicid);
        model.addAttribute("noteTableList", list);
        //  model.addAttribute("allNoteTableList", noteTableList);
        model.addAttribute("pagingVO", pagingVO);

        return "master/editSeNoteTable";
    }

    // 记事表操作
    @RequestMapping(value = "/editSeNoteTable", method = {RequestMethod.POST})
    public String editSeNoteTable(String notetext) throws Exception {

        // Subject subject = SecurityUtils.getSubject();
        //String username = (String) subject.getPrincipal();
        // userloginService.updateNote(username, notetext);

        return "redirect:/master/editSeNoteTable";
    }

    // 添加记事表操作
    @RequestMapping(value = "/addSeNoteTable", method = {RequestMethod.GET})
    public String addSeNoteTableUI(Model model,Integer dicid, Integer currentpage) throws Exception {


        List<Grade> gradelist = gradeService.findAllGrade();
        model.addAttribute("gradeList", gradelist);
        model.addAttribute("dicid", dicid);
        model.addAttribute("currentPage", currentpage);

        return "master/addSeNoteTable";
    }

    // 添加记事表操作
    @RequestMapping(value = "/addSeNoteTable", method = {RequestMethod.POST})
    public String addSeNoteTable(NoteTable noteTable, Integer dicid, Integer currentpage) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        noteTable.setUsername(username);
        noteTable.setDicid(dicid);

        userloginService.saveNoteTable(noteTable);

        return "redirect:/master/editSeNoteTable?dicid="+dicid + "&page=" + currentpage;
    }

    //删除记事本中的一条记录
    @RequestMapping(value = "/removeSeNoteTable", method = {RequestMethod.GET})
    private String removeSeNoteTable(Integer noteID , Integer dicID ,String currentPage) throws Exception {
      /*  if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            return "master/showStudent";
        }*/
        userloginService.removeNoteTable(noteID);
        return "redirect:/master/editSeNoteTable?dicid="+dicID + "&page=" + currentPage;

    }
    //修改记事本中的一条记录
    @RequestMapping(value = "/modifySeNoteTable", method = {RequestMethod.GET})
    private String modifySeNoteTableUI(Integer noteID, Integer dicid, Integer page, Model model) throws Exception {

        List<Grade> gradelist = gradeService.findAllGrade();
        model.addAttribute("gradeList", gradelist);
        NoteTable noteTable = userloginService.findNoteTableByID(noteID);
        model.addAttribute("noteTable", noteTable);
        model.addAttribute("dicid", dicid);
        model.addAttribute("page", page);
        return "master/modifySeNoteTable";
    }

    @RequestMapping(value = "/modifySeNoteTable", method = {RequestMethod.POST})
    private String modifySeNoteTable(NoteTable noteTable, Integer dicid, Integer page) throws Exception {

        userloginService.updateNoteTableByID(noteTable);
        return "redirect:/master/editSeNoteTable?dicid="+dicid + "&page=" + page;
    }

    //上传图片
    @RequestMapping(value = "/uploadImage", method = {RequestMethod.POST})
    public String uploadImage(@RequestParam("image") CommonsMultipartFile file, Integer stuid, String imageTitle, HttpServletRequest request) throws IOException,Exception {
        Images images = new Images();
        images.setStuid(stuid);
        images.setTitle(imageTitle);
        String realUploadPath = request.getServletContext().getRealPath("/");
        //上传原图,保存到数据库
        imageService.uploadImage(file, images, realUploadPath);
        signService.SetChangeSign(stuid);//状态改为未签字
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/master/editTableOne?encodeID=" + encodeID;
    }
    //查看缴费的学生 表三的上课开始时间<=查询当天日期<=表三上课结束时间
    @RequestMapping(value = "/paidStudent", method = {RequestMethod.GET})
    public String paidStudentUI(Model model, Integer page, Integer gradeid, Integer subjectid, Integer typeid, Integer campusid) throws Exception {
        Subject subjectuse = SecurityUtils.getSubject();
        String username = (String) subjectuse.getPrincipal();
        List<Campus> campusList = campusService.findAllCampusByAuth(username);
        List<Grade> gradelist = gradeService.findAllGradeByAuth(username);

        int campusAuthorize = 0;
        for(int i=0; i<campusList.size(); i++)    {
            Integer campusAuthorizeID =  campusList.get(i).getCampusid();
            if(campusAuthorizeID.equals(campusid)){
                campusAuthorize = 1;
                break;
            }
        }
        if (campusAuthorize == 0){
            model.addAttribute("message", "您没有权限查看, 请返回");
            return "error";
        }

        int gradeAuthorize =  0;
        for(int i=0; i<gradelist.size(); i++)    {
            Integer gradeAuthorizeID =  gradelist.get(i).getGradeid();
            if(gradeAuthorizeID.equals(gradeid) || gradeid.equals(-1)){
                gradeAuthorize = 1;
                break;
            }
        }
        if (gradeAuthorize == 0 ){
            model.addAttribute("message", "您没有权限查看, 请返回");
            return "error";
        }

        List<com.system.po.Subject> subjectList = subjectService.findAllSubject();
        List<ClassType> classTypeList = classTypeService.findAllClassType();

        Grade grade = new Grade();
        grade.setGradeid(-1);
        grade.setGradename("全部");
        gradelist.add(0, grade);

        com.system.po.Subject subject = new com.system.po.Subject();
        subject.setSubjectid(-1);
        subject.setSubjectname("全部");
        subjectList.add(0, subject);

        ClassType classType = new ClassType();
        classType.setTypeid(-1);
        classType.setTypename("全部");
        classTypeList.add(0, classType);

        model.addAttribute("campusList", campusList);
        model.addAttribute("gradeList", gradelist);
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("classTypeList", classTypeList);
        List<LessonCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(stuService.countPayStuBySelectAuth(gradeid, subjectid, typeid, campusid, username));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = stuService.findStuByPayStuAndSelectAuth(1, gradeid, subjectid, typeid, campusid, username);
        } else {
            pagingVO.setToPageNo(page);
            list = stuService.findStuByPayStuAndSelectAuth(page, gradeid, subjectid, typeid, campusid,username);
        }
        //List<LessonCustom> allStuList = stuService.findAllStuByPayStuAndSelect(gradeid, subjectid, typeid, campusid);  //用于保存为excle 表格
        //model.addAttribute("allStuList", allStuList);
        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);
        model.addAttribute("gradeIndex", gradeid);
        model.addAttribute("subjectIndex", subjectid);
        model.addAttribute("typeIndex", typeid);
        model.addAttribute("campusIndex", campusid);

        return "master/paidStudent";
    }

    @RequestMapping(value = "/paidStudent", method = {RequestMethod.POST})
    public String paidStudent(Integer gradeid, Integer subjectid, Integer typeid,  Integer campusid) throws Exception {

        return "redirect:/master/paidStudent?gradeid=" + gradeid + "&subjectid=" + subjectid + "&typeid=" + typeid + "&campusid=" + campusid;
    }

    //查看预缴费学员 查询当天日期<表三的上课时间且小于表三的上课结束时间
    @RequestMapping(value = "/prePayStu", method = {RequestMethod.GET})
    public String prePayStuUI(Model model, Integer page, Integer gradeid, Integer subjectid, Integer typeid, Integer campusid) throws Exception {
        Subject subjectuse = SecurityUtils.getSubject();
        String username = (String) subjectuse.getPrincipal();
        List<Campus> campusList = campusService.findAllCampusByAuth(username);
        List<Grade> gradelist = gradeService.findAllGradeByAuth(username);

        int campusAuthorize = 0;
        for(int i=0; i<campusList.size(); i++)    {
            Integer campusAuthorizeID =  campusList.get(i).getCampusid();
            if(campusAuthorizeID.equals(campusid)){
                campusAuthorize = 1;
                break;
            }
        }
        if (campusAuthorize == 0){
            model.addAttribute("message", "您没有权限查看, 请返回");
            return "error";
        }

        int gradeAuthorize =  0;
        for(int i=0; i<gradelist.size(); i++)    {
            Integer gradeAuthorizeID =  gradelist.get(i).getGradeid();
            if(gradeAuthorizeID.equals(gradeid) || gradeid.equals(-1)){
                gradeAuthorize = 1;
                break;
            }
        }
        if (gradeAuthorize == 0 ){
            model.addAttribute("message", "您没有权限查看, 请返回");
            return "error";
        }

        List<com.system.po.Subject> subjectList = subjectService.findAllSubject();
        List<ClassType> classTypeList = classTypeService.findAllClassType();

        Grade grade = new Grade();
        grade.setGradeid(-1);
        grade.setGradename("全部");
        gradelist.add(0, grade);

        com.system.po.Subject subject = new com.system.po.Subject();
        subject.setSubjectid(-1);
        subject.setSubjectname("全部");
        subjectList.add(0, subject);

        ClassType classType = new ClassType();
        classType.setTypeid(-1);
        classType.setTypename("全部");
        classTypeList.add(0, classType);

        model.addAttribute("gradeList", gradelist);
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("classTypeList", classTypeList);
        model.addAttribute("campusList", campusList);

//        Integer gradeIndex;  //????????
//        gradeIndex = gradeid;  //????????
        List<LessonCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(stuService.countPrePayStuBySelectAuth(gradeid, subjectid, typeid, campusid, username));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = stuService.findStuByPrePayStuAndSelectAuth(1, gradeid, subjectid, typeid, campusid, username);
        } else {
            pagingVO.setToPageNo(page);
            list = stuService.findStuByPrePayStuAndSelectAuth(page, gradeid, subjectid, typeid, campusid, username);
        }
        //List<LessonCustom> allStuList = stuService.findAllStuByPrePayStuAndSelect(gradeid, subjectid, typeid, campusid);  //用于保存为excle 表格
        //model.addAttribute("allStuList", allStuList);
        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);
        model.addAttribute("gradeIndex", gradeid);
        model.addAttribute("subjectIndex", subjectid);
        model.addAttribute("typeIndex", typeid);
        model.addAttribute("campusIndex", campusid);

        return "master/prePayStu";
    }

    @RequestMapping(value = "/prePayStu", method = {RequestMethod.POST})
    public String prePayStu(Integer gradeid, Integer subjectid, Integer typeid, Integer campusid) throws Exception {

        return "redirect:/master/prePayStu?gradeid=" + gradeid + "&subjectid=" + subjectid + "&typeid=" + typeid+ "&campusid=" +campusid;
    }

    // 公告栏显示
    @RequestMapping(value = "/announcement", method = {RequestMethod.GET})
    public String announcement(Model model) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<Announce>  announceList = announceService.findAllReadByAuth(username);
        model.addAttribute("announceList", announceList);
        String firstCon = null;
        String firstTitle = null;

        if (announceList.size() > 0){
            Announce firstAnnounce  = announceList.get(0);
            firstCon = announceService.getConByID(firstAnnounce.getAnnid());
            firstTitle = firstAnnounce.getAnntitle();
        }
        model.addAttribute("firstCon", firstCon);
        model.addAttribute("firstTitle", firstTitle);
        return "/master/announcement";
    }

    // 公告栏显示
    @ResponseBody
    @RequestMapping(value = "/findAnnounce", produces={"text/html;charset=UTF-8;","application/json;"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String findAnnounce(String announceid) throws Exception {

        Integer annId = Integer.parseInt(announceid);
        String annCon = announceService.getConByID(annId);
        return annCon;
    }

    //查看未交费的学生
    @RequestMapping(value = "/paidNotStudent", method = {RequestMethod.GET})
    public String paidNotStudentUI(Model model, Integer gradeid, Integer teleType, Integer page, Integer campusid) throws Exception {

        Subject subjectuse = SecurityUtils.getSubject();
        String username = (String) subjectuse.getPrincipal();
        List<Campus> campusList = campusService.findAllCampusByAuth(username);
        List<Grade> gradelist = gradeService.findAllGradeByAuth(username);

        int campusAuthorize = 0;
        for(int i=0; i<campusList.size(); i++)    {
            Integer campusAuthorizeID =  campusList.get(i).getCampusid();
            if(campusAuthorizeID.equals(campusid)){
                campusAuthorize = 1;
                break;
            }
        }
        if (campusAuthorize == 0){
            model.addAttribute("message", "您没有权限查看, 请返回");
            return "error";
        }

        int gradeAuthorize =  0;
        for(int i=0; i<gradelist.size(); i++)    {
            Integer gradeAuthorizeID =  gradelist.get(i).getGradeid();
            if(gradeAuthorizeID.equals(gradeid) || gradeid.equals(-1)){
                gradeAuthorize = 1;
                break;
            }
        }
        if (gradeAuthorize == 0 ){
            model.addAttribute("message", "您没有权限查看, 请返回");
            return "error";
        }


        if (teleType == null) teleType = 0;
        Grade grade = new Grade();
        grade.setGradeid(-1);
        grade.setGradename("全部");
        gradelist.add(0, grade);
        List<StuCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(stuService.getCountByUnPayStuAuth(gradeid, teleType,campusid,username));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = stuService.findStuByUnPayStuAuth(1, gradeid, teleType, campusid, username);
        } else {
            pagingVO.setToPageNo(page);
            list = stuService.findStuByUnPayStuAuth(page, gradeid, teleType, campusid,username);
        }
        //List<StuCustom> allStuList = stuService.findAllStuByUnPayStu(gradeid, teleType, campusid);
        model.addAttribute("gradelist", gradelist);
        model.addAttribute("campusList", campusList);
        model.addAttribute("gradeIndex", gradeid);
        model.addAttribute("teleType", teleType);
        //model.addAttribute("allStuList", allStuList);
        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);
        model.addAttribute("campusIndex", campusid);

        return "master/paidNotStudent";
    }

    // 添加记事本目录（表格）
    @RequestMapping(value = "/paidNotStudent", method = {RequestMethod.POST})
    public String paidNotStudent(Integer gradeid, Integer teleType, Integer campusid) throws Exception {

        return "redirect:/master/paidNotStudent?gradeid=" + gradeid + "&teleType=" + teleType +"&campusid=" + campusid;
    }

}

