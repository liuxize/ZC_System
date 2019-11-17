package com.system.controller;

import com.system.exception.CustomException;
import com.system.po.*;
import com.system.service.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Base64;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 * 30.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

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

    @Resource(name = "classTypeServiceImpl")
    private ClassTypeService classTypeService;

    @Resource(name = "subjectServiceImpl")
    private SubjectService subjectService;

    @Resource(name = "remindServiceImpl")
    private RemindService remindService;

    @Resource(name = "majorServiceImpl")
    private MajorService majorService;

    @Resource(name = "schoolServiceImpl")
    private SchoolService schoolService;

    @Resource(name = "campusServiceImpl")
    private CampusService campusService;

    @Resource(name = "excelLogServiceImpl")
    private ExcelLogService excelLogService;

    @Resource(name = "imageServiceImpl")
    private ImageService imageService;


    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<登陆账户操作操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/


    //  添加用户信息页面显示
    @RequestMapping(value = "/addUsers", method = {RequestMethod.GET})
    public String addUsersUI(Model model) throws Exception {
        List<Role> list = roleService.findAll();
        model.addAttribute("roleList", list);

        return "admin/addUsers";
    }

    // 添加用户信息操作
    @RequestMapping(value = "/addUsers", method = {RequestMethod.POST})
    public String addUser(UserloginCustom userloginCustom, GradeList gradelist, Integer unpay, Integer pay, Model model) throws Exception {


        Userlogin user = userloginService.findByName(userloginCustom.getUsername());
        if (user != null) {
            model.addAttribute("message", "用户名重复，请重新输入");
            return "error";
        }

        if (unpay == null && pay != null) {
            userloginCustom.setPermission(0);   //缴费
        } else if (unpay != null && pay == null) {     //未缴费
            userloginCustom.setPermission(1);
        } else {                                 //都可以
            userloginCustom.setPermission(2);
        }

        userloginService.save(userloginCustom);
        userloginService.setTeacherPerssion(userloginCustom.getUsername(), gradelist);

        //重定向
        return "redirect:/admin/showUsers";
    }

    // 年级更新操作
    @RequestMapping(value = "/updateStuGrade", method = {RequestMethod.GET})
    public String updateStuGrade(String currentPage, Model model) throws Exception {

        stuService.updateStuGrade();
        Date date = new Date();
        userloginService.updateDateRecord(date);
        return "redirect:/admin/showUsers?page=" + currentPage;
    }


    //删除用户信息
    @RequestMapping(value = "/removeUsers", method = {RequestMethod.GET})
    private String removeUsers(String username, String currentPage) throws Exception {

        userloginService.removeUserByName(username);
        //清空表格记事本的目录  根据姓名
        //清空表格记事本的具体表格  根据姓名
        //清空文本记事本  根据姓名
        //清空年纪权限 根据姓名

        return "redirect:/admin/showUsers?page=" + currentPage;
    }

    //设置教师权限
    @RequestMapping(value = "/setPermission", method = {RequestMethod.GET})
    public String setPermissionUI(Model model, String username) throws Exception {

        String name = URLDecoder.decode(username, "utf-8");
        List<Integer> list = userloginService.findTeacherPerssion(name);
        Userlogin user = userloginService.findUser(name);
        Integer num = user.getPermission();  //0缴费 1未缴费 2 全部

        model.addAttribute("permisionList", list);
        List<Grade> gradelist = gradeService.findAllGrade();
        model.addAttribute("gradeList", gradelist);

        model.addAttribute("num", num);
        model.addAttribute("username", name);

        return "admin/setPermission";
    }

    @RequestMapping(value = "/setPermission", method = {RequestMethod.POST})
    public String setPermission(String username, GradeList gradelist, Integer pay, Integer unpay) throws Exception {

        if (unpay == null && pay != null) {
            userloginService.updateUserPermission(username, 0);   //缴费
        } else if (unpay != null && pay == null) {     //未缴费
            userloginService.updateUserPermission(username, 1);
        } else {                                 //都可以
            userloginService.updateUserPermission(username, 2);
        }

        userloginService.deleteTeacherPerssion(username);

        userloginService.setTeacherPerssion(username, gradelist);

        String urlname = URLEncoder.encode(username, "utf-8");
        //重定向
        return "redirect:/admin/setPermission?username=" + urlname;

    }


    //修改用户的密码
    @RequestMapping(value = "/editPassword", method = {RequestMethod.POST})
    public String editPassword(String newPassword, String editname, String currentPage) throws Exception {

        userloginService.updataPassword(editname, newPassword);
        //重定向
        return "redirect:/admin/showUsers?page=" + currentPage;
    }

    // 用户信息显示
    @RequestMapping("/showUsers")
    public String showUser(Model model, Integer page, String teacherName) throws Exception {
        List<UserloginCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(userloginService.getCountUsers());
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findByPaging(page);
        }


        List<Grade> gradelist = gradeService.findAllGrade();
        model.addAttribute("gradeList", gradelist);

        List<Role> rolelist = roleService.findAll();
        model.addAttribute("roleList", rolelist);

        model.addAttribute("usersList", list);

        model.addAttribute("pagingVO", pagingVO);

        Date date = userloginService.getDateRecord();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String lastDate = datetype.format(date);

        model.addAttribute("lastDate", lastDate);

        return "admin/showUsers";
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<添加学生档案操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    // 添加用户信息表一（Get？？？）
    @RequestMapping(value = "/addTableOne", method = {RequestMethod.GET})
    public String showAddTable(Model model) throws Exception {
        List<Major> majorList = majorService.findAllMajor();
        List<School> schoolList = schoolService.findAllSchool();
        List<Grade> list = gradeService.findAllGrade();
        model.addAttribute("schoolList", schoolList);
        model.addAttribute("gradeList", list);
        model.addAttribute("majorList", majorList);
        //stuService.save(stu);
        //重定向
        return "admin/addTableOne";
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
        if (stu.getSchooltext() != "") {
            stu.setSchooltext(datestring + "  " + username + ":  " + stu.getSchooltext());
        }
        if (stu.getFamilytext() != "")
            stu.setFamilytext(datestring + "  " + username + ":  " + stu.getFamilytext());
        if (stu.getImprovetext() != "")
            stu.setImprovetext(datestring + "  " + username + ":  " + stu.getImprovetext());
        if (stu.getSupporttext() != "")
            stu.setSupporttext(datestring + "  " + username + ":  " + stu.getSupporttext());
        if (stu.getEducationtext() != "")
            stu.setEducationtext(datestring + "  " + username + ":  " + stu.getEducationtext());
        if (stu.getStudytext() != "")
            stu.setStudytext(datestring + "  " + username + ":  " + stu.getStudytext());

        stu.setRecordperson(username);
        stuService.save(stu);
        Integer stuID = stu.getStuid();
        signService.addStuSign(stuID);

        String encodeID = Base64.getEncoder().encodeToString(stuID.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        String url = "redirect:/admin/editTableOne?encodeID=" + encodeID;
        return url;
    }

    //修改用户信息界面表一
    @RequestMapping(value = "/editMessage", method = {RequestMethod.GET})
    public String editMessageUI(Integer stuid, Model model) throws Exception {

        String stuBirth = null;
        String motherBirth = null;
        String fatherBirth = null;
        String masterBirth = null;
        String checkDate = null;
        StuCustom stu = stuService.findById(stuid);
        String br = "\n";
        List<Grade> list = gradeService.findAllGrade();
        List<Major> majorList = majorService.findAllMajor();
        List<School> schoolList = schoolService.findAllSchool();

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        if (stu.getStubirth() != null) {
            stuBirth = datetype.format(stu.getStubirth());
        }
        if (stu.getMotherbirth() != null) {
            motherBirth = datetype.format(stu.getMotherbirth());
        }
        if (stu.getFatherbirth() != null) {
            fatherBirth = datetype.format(stu.getFatherbirth());
        }
        if (stu.getMasterbirth() != null) {
            masterBirth = datetype.format(stu.getMasterbirth());
        }
        if (stu.getCheckdate() != null) {
            checkDate = datetype.format(stu.getCheckdate());
        }
        model.addAttribute("majorList", majorList);
        model.addAttribute("schoolList", schoolList);
        model.addAttribute("stumessage", stu);
        model.addAttribute("stuBirth", stuBirth);
        model.addAttribute("motherBirth", motherBirth);
        model.addAttribute("fatherBirth", fatherBirth);
        model.addAttribute("masterBirth", masterBirth);
        model.addAttribute("checkDate", checkDate);
        model.addAttribute("gradeList", list);
        model.addAttribute("br", br);
        return "admin/editMessage";
    }

    //修改用户信息界面表二
    @RequestMapping(value = "/editMessageTwo", method = {RequestMethod.GET})
    public String editMessageTwoUI(Integer stuid, Model model) throws Exception {
        StuCustom stu = stuService.findById(stuid);
        List<Exam> examlist = examService.findExamByStuID(stuid);
        model.addAttribute("stumessage", stu);
        model.addAttribute("examlist", examlist);
        return "admin/editMessageTwo";
    }

    //修改用户信息界面表三
    @RequestMapping(value = "/editMessageThree", method = {RequestMethod.GET})
    public String editMessageThreeUI(Integer stuid, Model model) throws Exception {

        StuCustom stu = stuService.findById(stuid);

        List<LessonCustom> lessonlist = lessonService.findByStuID(stuid);
        model.addAttribute("stumessage", stu);
        model.addAttribute("lesList", lessonlist);
        return "admin/editMessageThree";
    }

    //修改用户信息界面表一
    @RequestMapping(value = "/editMessage", method = {RequestMethod.POST})
    public String editMessage(Stu stu) throws Exception {

        stuService.update(stu);
        Integer stuID = stu.getStuid();
        String url = "redirect:/admin/editMessage?stuid=" + stuID;
        return url;
    }


    //修改exam
    @RequestMapping(value = "/editExam", method = {RequestMethod.GET})
    public String editExamUI(Integer examid, Model model) throws Exception {
        Exam exam = examService.findExamByExamID(examid);
        String examDate = null;
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        if (exam.getExamdata() != null) {
            examDate = datetype.format(exam.getExamdata());
        }
        model.addAttribute("exam", exam);
        model.addAttribute("examDate", examDate);
        return "admin/editExam";
    }

    @RequestMapping(value = "/editExam", method = {RequestMethod.POST})
    public String editExam(Exam exam, Model model) throws Exception {

        examService.editExam(exam);
        Integer examid = exam.getExamid();

        //重定向
        Exam orexam = examService.findExamByExamID(examid);
        String url = "redirect:/admin/editMessageTwo?stuid=" + orexam.getStuid();
        return url;
    }

    //删除exam
    @RequestMapping(value = "/removeExam", method = {RequestMethod.GET})
    private String removeExam(Integer examid, Integer stuid) throws Exception {

        examService.removeByExamID(examid);
        return "redirect:editMessageTwo?stuid=" + stuid;
    }

    //修改lesson
    @RequestMapping(value = "/editLesson", method = {RequestMethod.GET})
    public String editLessonUI(Integer lessonid, Model model) throws Exception {
        LessonCustom lessonCustom = lessonService.findByLessonID(lessonid);
        List<ClassType> typelist = classTypeService.findAllClassType();
        List<com.system.po.Subject> subjectlist = subjectService.findAllSubject();
        String startdate = null;
        String enddate = null;
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        if (lessonCustom.getLessonstart() != null) {
            startdate = datetype.format(lessonCustom.getLessonstart());
        }
        if (lessonCustom.getLessonend() != null) {
            enddate = datetype.format(lessonCustom.getLessonend());
        }

        model.addAttribute("startdate", startdate);
        model.addAttribute("enddate", enddate);
        model.addAttribute("typelist", typelist);
        model.addAttribute("subjectlist", subjectlist);
        model.addAttribute("lessonCustom", lessonCustom);

        return "admin/editLesson";
    }

    @RequestMapping(value = "/editLesson", method = {RequestMethod.POST})
    public String editLesson(Lesson lesson, Model model) throws Exception {

        lessonService.changeLesson(lesson);
        LessonCustom lessonCustom = lessonService.findByLessonID(lesson.getLessonid());
        Integer stuID = lessonCustom.getStuid();
        String url = "redirect:/admin/editMessageThree?stuid=" + stuID;
        return url;
    }

    //删除lesson
    @RequestMapping(value = "/removeLesson", method = {RequestMethod.GET})
    private String removeLesson(Integer lessonid, Integer stuid) throws Exception {

        lessonService.removeByLessonID(lessonid);
        return "redirect:editMessageThree?stuid=" + stuid;
    }

    //  添加信息页面显示
    @RequestMapping(value = "/editTableOne", method = {RequestMethod.GET})
    public String showTableOneUI(String encodeID, Model model) throws Exception {

        //对stuid进行Base64解密，获取真实的id
        String unDecodeStr = new String(Base64.getDecoder().decode(encodeID), StandardCharsets.UTF_8);
        Integer stuid = null;
        if (unDecodeStr != null) {
            stuid = Integer.valueOf(unDecodeStr);
        }

        StuCustom stu = stuService.findById(stuid);
        Sign sign = signService.findSignByStuID(stuid);
        List<School> schoolList = schoolService.findAllSchool();
        List<Major> majorList = majorService.findAllMajor();
        String br = "\n";
        List<Images> imagesList = imageService.findImageByStuID(stuid);
        Integer imagesNum = imagesList.size(); //所有图片的数量
        Integer unsignImageNum = imageService.getCountUnsignImage(stuid);//未签字图片的数量
        model.addAttribute("unsignImageNum",unsignImageNum);
        model.addAttribute("imagesNum",imagesNum);
        model.addAttribute("imagesList",imagesList);
        model.addAttribute("schoolList", schoolList);
        model.addAttribute("majorList", majorList);
        model.addAttribute("stumessage", stu);
        model.addAttribute("signmessage", sign);
        model.addAttribute("br", br);
        return "admin/editTableOne";
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<表一添加部分操作开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/


    // 添加用户信息表一（POST）
    @RequestMapping(value = "/addName", method = {RequestMethod.POST})
    public String addName(Integer stuid, String stuName) throws Exception {

        stuService.addStuNameByID(stuid, stuName);
        signService.changeStuNameSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addSchool", method = {RequestMethod.POST})
    public String addSchool(Integer stuid, String school) throws Exception {

        stuService.addSchoolByID(stuid, school);
        signService.changeSchoolSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addMajor", method = {RequestMethod.POST})
    public String addMajor(Integer stuid, String major) throws Exception {

        stuService.addMajorByID(stuid, major);
        signService.changeMajorSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addMother", method = {RequestMethod.POST})
    public String addMother(Integer stuid, String motherName) throws Exception {

        stuService.addMotherNameByID(stuid, motherName);
        signService.changeMoNameSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }


    @RequestMapping(value = "/addMoCompany", method = {RequestMethod.POST})
    public String addMoCompany(Integer stuid, String motherCompany) throws Exception {

        stuService.addMoCompanyByID(stuid, motherCompany);
        signService.changeMoCompanySign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addMoJob", method = {RequestMethod.POST})
    public String addMoJob(Integer stuid, String motherJob) throws Exception {

        stuService.addMoJobByID(stuid, motherJob);
        signService.changeMoJobSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addFather", method = {RequestMethod.POST})
    public String addFather(Integer stuid, String fatherName) throws Exception {

        stuService.addFaNameByID(stuid, fatherName);
        signService.changeFaNameSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }


    @RequestMapping(value = "/addFaCompany", method = {RequestMethod.POST})
    public String addFaCompany(Integer stuid, String fathercompany) throws Exception {

        stuService.addFaCompanyByID(stuid, fathercompany);
        signService.changeFaCompanySign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addFaJob", method = {RequestMethod.POST})
    public String addFaJob(Integer stuid, String fatherJob) throws Exception {

        stuService.addFaJobByID(stuid, fatherJob);
        signService.changeFaJobSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addBirth", method = {RequestMethod.POST})
    public String addBirth(Integer stuid, Date birth, Model model) throws Exception {

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = stuService.getBirthByID(stuid);
        String birthstring = "";
        if (birthday != null) {
            birthstring = datetype.format(birthday);

        }
        stuService.addStuBirthByID(stuid, birth, birthstring);
        signService.changeBirthSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addMoBirth", method = {RequestMethod.POST})
    public String addMoBirth(Integer stuid, Date motherbirth, Model model) throws Exception {

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = stuService.getMoBirthByID(stuid);
        String birthstring = "";
        if (birthday != null) {
            birthstring = datetype.format(birthday);

        }
        stuService.addMotherBirthByID(stuid, motherbirth, birthstring);
        signService.changeMoBirthSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addFaBirth", method = {RequestMethod.POST})
    public String addFaBirth(Integer stuid, Date fatherbirth, Model model) throws Exception {

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = stuService.getFaBirthByID(stuid);
        String birthstring = "";
        if (birthday != null) {
            birthstring = datetype.format(birthday);
        }
        stuService.addFatherBirthByID(stuid, fatherbirth, birthstring);
        signService.changeFaBirthSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addStuTel", method = {RequestMethod.POST})
    public String addStuTel(Integer stuid, String stuTel) throws Exception {

        stuService.addStuTelByID(stuid, stuTel);
        signService.changeStuTelSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addMotherTel", method = {RequestMethod.POST})
    public String addMotherTel(Integer stuid, String motherTel) throws Exception {

        stuService.addMoTelByID(stuid, motherTel);
        signService.changeMotherTelSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addFatherTel", method = {RequestMethod.POST})
    public String addFatherTel(Integer stuid, String fatherTel) throws Exception {

        stuService.addFaTelByID(stuid, fatherTel);
        signService.changeFatherTelSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addFatherGegree", method = {RequestMethod.POST})
    public String addFatherGegree(Integer stuid, String fatherDegree) throws Exception {

        stuService.addFatherDegreeByID(stuid, fatherDegree);
        signService.changeFaDegreeSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addMotherGegree", method = {RequestMethod.POST})
    public String addMotherGegree(Integer stuid, String motherDegree) throws Exception {

        stuService.addMotherDegreeByID(stuid, motherDegree);
        signService.changeMoDegreeSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }

    @RequestMapping(value = "/addAddress", method = {RequestMethod.POST})
    public String addAddress(Integer stuid, String addAddress) throws Exception {

        stuService.addAddressByID(stuid, addAddress);
        signService.changeAddressSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addMaster", method = {RequestMethod.POST})
    public String addMaster(Integer stuid, String addMaster) throws Exception {

        stuService.addMasterByID(stuid, addMaster);
        signService.changeMasterSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addMasterTel", method = {RequestMethod.POST})
    public String addMasterTel(Integer stuid, String addmastertel) throws Exception {

        stuService.addMasterTelByID(stuid, addmastertel);
        signService.changeMasterTelSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    //添加学生性别
    @RequestMapping(value = "/addStuSex", method = {RequestMethod.POST})
    public String addStuSex(Integer stuid, String addstusex) throws Exception {

        stuService.addStuSexByID(stuid, addstusex);
        signService.changeStuSexSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }

    //添加班主任地址
    @RequestMapping(value = "/addMasterAddress", method = {RequestMethod.POST})
    public String addMasterAddress(Integer stuid, String addmasteraddress) throws Exception {

        stuService.addMasterAddressByID(stuid, addmasteraddress);
        signService.changeMasterAddressSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }

    //添加班主任学历
    @RequestMapping(value = "/addMasterDegree", method = {RequestMethod.POST})
    public String addMasterDegree(Integer stuid, String addmasterdegree) throws Exception {

        stuService.addMasterDegreeByID(stuid, addmasterdegree);
        signService.changeMasterDegreeSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }

    //添加班主任性别
    @RequestMapping(value = "/addMasterSex", method = {RequestMethod.POST})
    public String addMasterSex(Integer stuid, String addmastersex) throws Exception {

        stuService.addMasterSexByID(stuid, addmastersex);
        signService.changeMasterSexSign(stuid);

        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }

    //添加班主任生日
    @RequestMapping(value = "/addMasterBirth", method = {RequestMethod.POST})
    public String addMasterBirth(Integer stuid, Date addmasterbirth) throws Exception {

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = stuService.getMasterBirthByID(stuid);
        String birthstring = "";
        if (birthday != null) {
            birthstring = datetype.format(birthday);
        }
        stuService.addMasterBirthByID(stuid, addmasterbirth, birthstring);
        signService.changeMasterBirthSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }

    //添加检验日期信息
    @RequestMapping(value = "/addCheckDate", method = {RequestMethod.POST})
    public String addCheckDate(Integer stuid, Date addcheckdate) throws Exception {

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        Date checkdate = stuService.getCheckDateByID(stuid);
        String datestring = "";
        if (checkdate != null) {
            datestring = datetype.format(checkdate);
        }
        System.out.println("dateString" + datestring);
        stuService.addCheckDateByID(stuid, addcheckdate, datestring);
        signService.changeCheckDate(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }


    @RequestMapping(value = "/addSchoolText", method = {RequestMethod.POST})
    public String addSchoolText(Integer stuid, String addschooltext) throws Exception {

        Date date = new Date();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(date);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        addschooltext = datestring + "  " + username + ":  " + addschooltext;

        stuService.addSchoolTextByID(stuid, addschooltext);
        signService.changeSchoolTextSign(stuid);
        //  stuService.updataStuHisByID(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addFamilyText", method = {RequestMethod.POST})
    public String addFamilyText(Integer stuid, String addfamilytext) throws Exception {

        Date date = new Date();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(date);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        addfamilytext = datestring + "  " + username + ":  " + addfamilytext;


        stuService.addFamilyTextByID(stuid, addfamilytext);
        signService.changeFamilyTextSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addStudyText", method = {RequestMethod.POST})
    public String addStudyText(Integer stuid, String addstudytext) throws Exception {

        Date date = new Date();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(date);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        addstudytext = datestring + "  " + username + ":  " + addstudytext;

        stuService.addStudyTextByID(stuid, addstudytext);
        signService.changeStudyTextSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addEducationText", method = {RequestMethod.POST})
    public String addEducationText(Integer stuid, String addedutext) throws Exception {

        Date date = new Date();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(date);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        addedutext = datestring + "  " + username + ":  " + addedutext;

        stuService.addEducationTextByID(stuid, addedutext);
        signService.changeEducationTextSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addSupportText", method = {RequestMethod.POST})
    public String addSupportText(Integer stuid, String addsupporttext) throws Exception {

        Date date = new Date();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(date);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        addsupporttext = datestring + "  " + username + ":  " + addsupporttext;

        stuService.addSupportTextByID(stuid, addsupporttext);
        signService.changeSupportTextSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    @RequestMapping(value = "/addImproveText", method = {RequestMethod.POST})
    public String addImproveText(Integer stuid, String addimprovetext) throws Exception {

        Date date = new Date();
        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = datetype.format(date);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        addimprovetext = datestring + "  " + username + ":  " + addimprovetext;

        stuService.addImproveTextByID(stuid, addimprovetext);
        signService.changeImproveTextSign(stuid);
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;

    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<添加部分信息操作结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<表二操作开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    //  添加信息页面显示
    @RequestMapping(value = "/editTableTwo", method = {RequestMethod.GET})
    public String showTableTwoUI(String encodeID, Model model) throws Exception {

        //对stuid进行Base64解密，获取真实的id
        String unDecodeStr = new String(Base64.getDecoder().decode(encodeID), StandardCharsets.UTF_8);

        Integer stuid = null;
        if (unDecodeStr != null) {
            stuid = Integer.valueOf(unDecodeStr);
        }

        StuCustom stu = stuService.findById(stuid);
        List<Exam> examlist = examService.findExamByStuID(stuid);
        model.addAttribute("stumessage", stu);
        model.addAttribute("examlist", examlist);
        return "admin/editTableTwo";
    }

    @RequestMapping(value = "/addExam", method = {RequestMethod.GET})
    public String addExamUI(Integer stuid, Model model) throws Exception {

        model.addAttribute("StuID", stuid);
        return "admin/addExam";
    }

    // 添加学生信息操作
    @RequestMapping(value = "/addExam", method = {RequestMethod.POST})
    public String addExam(Integer stuid, Exam exam, Model model) throws Exception {


        exam.setStuid(stuid);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        exam.setUpdateperson(username);
        examService.save(exam);
        signService.SetChangeSign(stuid); //receiveSign设置为0
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableTwo?encodeID=" + encodeID;
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<表二操作结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/


    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<表三操作开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    //  添加信息页面显示
    @RequestMapping(value = "/editTableThree", method = {RequestMethod.GET})
    public String showTableThreeUI(String encodeID, Model model) throws Exception {

        //对stuid进行Base64解密，获取真实的id
        String unDecodeStr = new String(Base64.getDecoder().decode(encodeID), StandardCharsets.UTF_8);
        Integer stuid = null;
        if (unDecodeStr != null) {
            stuid = Integer.valueOf(unDecodeStr);
        }
        StuCustom stu = stuService.findById(stuid);
        List<LessonCustom> lessonlist = lessonService.findByStuID(stuid);
        model.addAttribute("stumessage", stu);
        model.addAttribute("lesList", lessonlist);
        return "admin/editTableThree";
    }

    //  添加学生信息页面显示

    @RequestMapping(value = "/addLesson", method = {RequestMethod.GET})
    public String addLessonUI(Integer stuid, Model model) throws Exception {

        List<ClassType> typelist = classTypeService.findAllClassType();
        List<com.system.po.Subject> subjectlist = subjectService.findAllSubject();
        model.addAttribute("typelist", typelist);
        model.addAttribute("subjectlist", subjectlist);
        model.addAttribute("StuID", stuid);

        return "admin/addLesson";
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
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableThree?encodeID=" + encodeID;
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
        return "redirect:/admin/editTableThree?encodeID=" + encodeID;
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<表三操作结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<删除学生信息操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //删除学生信息（搜索姓名）
    @RequestMapping(value = "/removeStuName", method = {RequestMethod.GET})
    private String removeStuName(Integer stuid, String currentPage) throws Exception {

        //删除表1
        stuService.removeStuByID(stuid);
        //删除表2
        lessonService.removeLessonByID(stuid);
        //删除表3
        examService.removeExamByID(stuid);
        //删除标记sign
        signService.removeSignByID(stuid);

        return "redirect:/admin/searchName?page=" + currentPage;
    }

    @RequestMapping(value = "/removeStuGrade", method = {RequestMethod.GET})
    private String removeStuGrade(Integer stuid, String currentPage, Integer gradeid) throws Exception {

        //删除表1
        stuService.removeStuByID(stuid);
        //删除表2
        lessonService.removeLessonByID(stuid);
        //删除表3
        examService.removeExamByID(stuid);
        //删除标记sign
        signService.removeSignByID(stuid);
        return "redirect:/admin/searchGrade?gradeid=" + gradeid + "&page=" + currentPage;
    }

    @RequestMapping(value = "/removeStuDate", method = {RequestMethod.GET})
    private String removeStuDate(Integer stuid, String currentPage, Date startdate, Date enddate) throws Exception {

        //删除表1
        stuService.removeStuByID(stuid);
        //删除表2
        lessonService.removeLessonByID(stuid);
        //删除表3
        examService.removeExamByID(stuid);
        //删除标记sign
        signService.removeSignByID(stuid);

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        String datestart = datetype.format(startdate);
        String dateend = datetype.format(enddate);
        return "redirect:/admin/searchDate?page=" + currentPage + "&datestart=" + datestart + "&dateend=" + dateend;
    }

    //专业查询中的删除
    @RequestMapping(value = "/removeStuMajor", method = {RequestMethod.GET})
    private String removeStuMajor(Integer stuid, String currentPage, String majorName) throws Exception {
        //删除表1
        stuService.removeStuByID(stuid);
        //删除表2
        lessonService.removeLessonByID(stuid);
        //删除表3
        examService.removeExamByID(stuid);
        //删除标记sign
        signService.removeSignByID(stuid);
        String encodeMajor = URLEncoder.encode(majorName, "utf-8");
        return "redirect:/admin/searchMajor?encodeMajor=" + encodeMajor + "&page=" + currentPage;
    }

    //学校查询中的删除
    @RequestMapping(value = "/removeStuSchool", method = {RequestMethod.GET})
    private String removeStuSchool(Integer stuid, String currentPage, String schoolName) throws Exception {
        //删除表1
        stuService.removeStuByID(stuid);
        //删除表2
        lessonService.removeLessonByID(stuid);
        //删除表3
        examService.removeExamByID(stuid);
        //删除标记sign
        signService.removeSignByID(stuid);
        String encodeSchool = URLEncoder.encode(schoolName, "utf-8");
        return "redirect:/admin/searchSchool?encodeSchool=" + encodeSchool + "&page=" + currentPage;
    }

    //相同姓名中的删除
    @RequestMapping(value = "/removeStuSame", method = {RequestMethod.GET})
    private String removeStuSame(Integer stuid, String currentPage) throws Exception {
        //删除表1
        stuService.removeStuByID(stuid);
        //删除表2
        lessonService.removeLessonByID(stuid);
        //删除表3
        examService.removeExamByID(stuid);
        //删除标记sign
        signService.removeSignByID(stuid);

        return "redirect:/admin/searchSame?page=" + currentPage;
    }


    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<搜索操作开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    // 搜索姓名操作
    @RequestMapping("/searchName")
    public String searchNameUI(Model model, Integer page) throws Exception {
        List<StuCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(stuService.getCountStudent());
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = stuService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = stuService.findByPaging(page);
        }
        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/searchName";
    }

    // 搜索姓名操作
    @RequestMapping(value = "/selectName", method = {RequestMethod.POST})
    public String searchName(String StudentName, Model model) throws Exception {
        List<StuCustom> list = stuService.findByName(StudentName);
        PagingVO pagingVO = new PagingVO();
        pagingVO.setTotalCount(0);
        model.addAttribute("pagingVO", pagingVO);
        model.addAttribute("stuList", list);
        return "admin/searchName";
    }


    // 搜索制表人操作

    @RequestMapping(value = "/searchLister", method = {RequestMethod.GET})
    public String searchListerUI(Integer page, String name, String datestart, String dateend, Model model) throws Exception {


        if (name != null && datestart != null && dateend != null) {
            String username = URLDecoder.decode(name, "utf-8");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startdate = sdf.parse(datestart);
            Date enddate = sdf.parse(dateend);
            List<StuCustom> list = null;
            //页码对象
            PagingVO pagingVO = new PagingVO();
            //设置总页数
            pagingVO.setTotalCount(stuService.getCountLister(username, startdate, enddate));
            if (page == null || page == 0) {
                pagingVO.setCurentPageNo(1);
                pagingVO.setToPageNo(1);
                list = stuService.findLister(1, username, startdate, enddate);
            } else {
                pagingVO.setToPageNo(page);
                list = stuService.findLister(page, username, startdate, enddate);
            }
            model.addAttribute("starttime", datestart);
            model.addAttribute("endtime", dateend);
            model.addAttribute("name", username);
            model.addAttribute("stuList", list);
            model.addAttribute("pagingVO", pagingVO);

        }
        return "admin/searchLister";
    }

    // 搜索制表人操作
    @RequestMapping(value = "/searchLister", method = {RequestMethod.POST})
    public String searchLister(String name, Date startdate, Date enddate, Model model) throws Exception {


        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        if (startdate == null || enddate == null) {
            model.addAttribute("message", "请输入完整日期");
            return "error";
        }
        String datestart = datetype.format(startdate);
        String dateend = datetype.format(enddate);

        String urlname = URLEncoder.encode(name, "utf-8");
        return "redirect:/admin/searchLister?name=" + urlname + "&datestart=" + datestart + "&dateend=" + dateend;

    }


    // 搜索更新人操作
    @RequestMapping("/searchUpdater")
    public String searchUpdaterUI(Integer page, String name, String datestart, String dateend, Model model) throws Exception {
        if (name != null && datestart != null && dateend != null) {
            String username = URLDecoder.decode(name, "utf-8");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startdate = sdf.parse(datestart);
            Date enddate = sdf.parse(dateend);
            List<StuCustom> list = null;
            //页码对象
            PagingVO pagingVO = new PagingVO();
            //设置总页数
            pagingVO.setTotalCount(stuService.getCountUpdater(username, startdate, enddate));
            if (page == null || page == 0) {
                pagingVO.setCurentPageNo(1);
                pagingVO.setToPageNo(1);
                list = stuService.findUpdater(1, username, startdate, enddate);
            } else {
                pagingVO.setToPageNo(page);
                list = stuService.findUpdater(page, username, startdate, enddate);
            }
            model.addAttribute("starttime", datestart);
            model.addAttribute("endtime", dateend);
            model.addAttribute("name", username);
            model.addAttribute("stuList", list);
            model.addAttribute("pagingVO", pagingVO);

        }

        return "admin/searchUpdater";
    }

    // 搜索更新人操作
    @RequestMapping(value = "/searchUpdater", method = {RequestMethod.POST})
    public String searchUpdater(String name, Date startdate, Date enddate, Model model) throws Exception {

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        if (startdate == null || enddate == null) {
            model.addAttribute("message", "请输入完整日期");
            return "error";
        }
        String datestart = datetype.format(startdate);
        String dateend = datetype.format(enddate);
        String urlname = URLEncoder.encode(name, "utf-8");
        return "redirect:/admin/searchUpdater?name=" + urlname + "&datestart=" + datestart + "&dateend=" + dateend;

    }


    // 搜索年级操作
    @RequestMapping(value = "/searchGrade", method = {RequestMethod.GET})
    public String addGradeUI(Integer gradeid, Model model, Integer page) throws Exception {

        List<Grade> gradelist = gradeService.findAllGrade();
        model.addAttribute("gradeList", gradelist);
        Integer gradeIndex;
        //List<StuCustom> stuCustomList = stuService.findStuByGrade(gradeid);
        if (gradeid != null) {
            gradeIndex = gradeid;
            List<StuCustom> list = null;
            PagingVO pagingVO = new PagingVO();

            pagingVO.setTotalCount(stuService.getCountByGrade(gradeid));
            if (page == null || page == 0) {
                pagingVO.setCurentPageNo(1);
                pagingVO.setToPageNo(1);
                list = stuService.findStuByGrade(1, gradeid);
            } else {
                pagingVO.setToPageNo(page);
                list = stuService.findStuByGrade(page, gradeid);
            }

            List<StuCustom> allStuList = stuService.findAllStuByGrade(gradeid);
            model.addAttribute("allStuList", allStuList);
            model.addAttribute("gradeIndex", gradeIndex);
            model.addAttribute("stuList", list);
            model.addAttribute("pagingVO", pagingVO);
        }
        return "admin/searchGrade";
    }

    // 搜索年级操作
    @RequestMapping(value = "/searchGrade", method = {RequestMethod.POST})
    public String searchGradePost(Integer gradeid, Model model) throws Exception {

        return "redirect:/admin/searchGrade?gradeid=" + gradeid;
    }

    // 搜索时间操作
    @RequestMapping(value = "/searchDate", method = {RequestMethod.GET})
    public String searchDateUI(String datestart, String dateend, Model model, Integer page) throws Exception {

        if (dateend != null && dateend != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startdate = sdf.parse(datestart);
            Date enddate = sdf.parse(dateend);
            List<StuCustom> list = null;
            PagingVO pagingVO = new PagingVO();
            pagingVO.setTotalCount(stuService.getCountByDate(startdate, enddate));
            if (page == null || page == 0) {
                pagingVO.setCurentPageNo(1);
                pagingVO.setToPageNo(1);
                list = stuService.findStuByDate(1, startdate, enddate);
            } else {
                pagingVO.setToPageNo(page);
                list = stuService.findStuByDate(page, startdate, enddate);
            }
            List<StuCustom> allStuList = stuService.findAllStuByDate(startdate, enddate);
            model.addAttribute("allStuList", allStuList);
            model.addAttribute("starttime", datestart);
            model.addAttribute("endtime", dateend);
            model.addAttribute("stuList", list);
            model.addAttribute("pagingVO", pagingVO);
        }

        return "admin/searchDate";
    }

    // 搜索时间操作
    @RequestMapping(value = "/searchDate", method = {RequestMethod.POST})
    public String searchDate(Date startdate, Date enddate, String test, Model model) throws Exception {

        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        if (startdate == null || enddate == null) {
            model.addAttribute("message", "请输入完整日期");
            return "error";
        }
        String datestart = datetype.format(startdate);
        String dateend = datetype.format(enddate);
        return "redirect:/admin/searchDate?datestart=" + datestart + "&dateend=" + dateend;
    }

    // 搜索优质学员
    @RequestMapping(value = "/searchOutstand", method = {RequestMethod.GET})
    public String searchOutstandUI(Model model, Integer page) throws Exception {

        List<StuCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(stuService.getCountByOutstand());
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = stuService.findOutStandStu(1);
        } else {
            pagingVO.setToPageNo(page);
            list = stuService.findOutStandStu(page);
        }
        //全部的优质学员
        List<StuCustom> allStuList = stuService.findAllOutStandStu();
        model.addAttribute("allStuList", allStuList);
        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/searchOutstand";
    }

    // 搜索电话
    @RequestMapping(value = "/searchTel", method = {RequestMethod.GET})
    public String searchTelUI(Model model, String telphone) throws Exception {

        List<Stu> stuList = stuService.findStuByStuTel(telphone);
        List<Stu> motherList = stuService.findStuByMoTel(telphone);
        List<Stu> fatherList = stuService.findStuByFaTel(telphone);
        model.addAttribute("allStuList", stuList);
        model.addAttribute("motherList", motherList);
        model.addAttribute("fatherList", fatherList);
        model.addAttribute("tel", telphone);
        return "admin/searchTel";
    }

    @RequestMapping(value = "/searchTel", method = {RequestMethod.POST})
    public String searchTel(String telphone) throws Exception {

        return "redirect:/admin/searchTel?telphone=" + telphone;
    }

    //专业查询
    @RequestMapping(value = "/searchMajor", method = {RequestMethod.GET})
    public String searchMajorUI(Model model, String encodeMajor, Integer page) throws Exception {

        List<Major> majorList = majorService.findAllMajor();
        model.addAttribute("majorList", majorList);
        String majorTemp = null;
        if (encodeMajor != null) {
            List<StuCustom> stuCustomList = null;
            majorTemp = encodeMajor;
            PagingVO pagingVO = new PagingVO();
            String majorName = URLDecoder.decode(encodeMajor, "utf-8");
            //设置总页数
            pagingVO.setTotalCount(stuService.getCountByMajor(majorName));
            if (page == null || page == 0) {
                pagingVO.setCurentPageNo(1);
                pagingVO.setToPageNo(1);
                stuCustomList = stuService.findStuByMajor(1, majorName);
            } else {
                pagingVO.setToPageNo(page);
                stuCustomList = stuService.findStuByMajor(page, majorName);
            }
            model.addAttribute("majorTemp", majorTemp);
            model.addAttribute("stuList", stuCustomList);
            model.addAttribute("pagingVO", pagingVO);
        }
        return "admin/searchMajor";
    }

    //专业查询
    @RequestMapping(value = "/searchMajor", method = {RequestMethod.POST})
    public String searchMajor(String majorName) throws Exception {
        String encodeMajor = URLEncoder.encode(majorName, "utf-8");
        return "redirect:/admin/searchMajor?encodeMajor=" + encodeMajor;
    }

    //学校查询
    @RequestMapping(value = "/searchSchool", method = {RequestMethod.GET})
    public String searchSchoolUI(Model model, String encodeSchool, Integer page) throws Exception {
        List<School> schoolList = schoolService.findAllSchool();
        model.addAttribute("schoolList", schoolList);
        String schoolTemp = null;
        if (encodeSchool != null) {
            List<StuCustom> stuCustomList = null;
            schoolTemp = encodeSchool;
            PagingVO pagingVO = new PagingVO();
            String schoolName = URLDecoder.decode(encodeSchool, "utf-8");
            //设置总页数
            pagingVO.setTotalCount(stuService.getCountBySchool(schoolName));
            if (page == null || page == 0) {
                pagingVO.setCurentPageNo(1);
                pagingVO.setToPageNo(1);
                stuCustomList = stuService.findStuBySchool(1, schoolName);
            } else {
                pagingVO.setToPageNo(page);
                stuCustomList = stuService.findStuBySchool(page, schoolName);
            }
            //stuCustomList = stuService.findAllStuBySchool(schoolName);
            model.addAttribute("schoolTemp", schoolTemp);
            model.addAttribute("stuList", stuCustomList);
            model.addAttribute("pagingVO", pagingVO);
        }
        return "admin/searchSchool";
    }

    //学校查询
    @RequestMapping(value = "/searchSchool", method = {RequestMethod.POST})
    public String searchSchool(String schoolName) throws Exception {

        String encodeSchool = URLEncoder.encode(schoolName, "utf-8");
        return "redirect:/admin/searchSchool?encodeSchool=" + encodeSchool;
    }

    //相同姓名查询
    @RequestMapping(value = "/searchSame", method = {RequestMethod.GET})
    public String searchSameUI(Model model, Integer page) throws Exception {
        List<StuCustom> stuCustomList = null;
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(stuService.getCountBySameStu());
        System.out.println(stuService.getCountBySameStu());
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            stuCustomList = stuService.findStuBySameName(1);
        } else {
            pagingVO.setToPageNo(page);
            stuCustomList = stuService.findStuBySameName(page);
        }
        model.addAttribute("stuList", stuCustomList);
        model.addAttribute("pagingVO", pagingVO);
        return "admin/searchSame";
    }

    //检验查询（起始时间，结束时间 专业）
    @RequestMapping(value = "/searchCheck", method = {RequestMethod.GET})
    public String searchCheckUI(Integer page, String majorName, String datestart, String dateend, Model model) throws Exception {

        List<Major> majorList = majorService.findAllMajor();
        model.addAttribute("majorList", majorList);
        if (majorName != null && datestart != null && dateend != null) {
            String name = URLDecoder.decode(majorName, "utf-8");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startdate = sdf.parse(datestart);
            Date enddate = sdf.parse(dateend);
            List<StuCustom> list = null;
            //页码对象
            PagingVO pagingVO = new PagingVO();
            //设置总页数
            pagingVO.setTotalCount(stuService.getCountCheck(name, startdate, enddate));
            if (page == null || page == 0) {
                pagingVO.setCurentPageNo(1);
                pagingVO.setToPageNo(1);
                list = stuService.findCheck(1, name, startdate, enddate);
            } else {
                pagingVO.setToPageNo(page);
                list = stuService.findCheck(page, name, startdate, enddate);
            }
            model.addAttribute("starttime", datestart);
            model.addAttribute("endtime", dateend);
            model.addAttribute("majorTemp", majorName);
            model.addAttribute("stuList", list);
            model.addAttribute("pagingVO", pagingVO);
        }
        return "admin/searchCheck";
    }

    // 搜索制表人操作
    @RequestMapping(value = "/searchCheck", method = {RequestMethod.POST})
    public String searchCheck(String majorName, Date startdate, Date enddate, Model model) throws Exception {


        SimpleDateFormat datetype = new SimpleDateFormat("yyyy-MM-dd");
        if (startdate == null || enddate == null) {
            model.addAttribute("message", "请输入完整日期");
            return "error";
        }
        String datestart = datetype.format(startdate);
        String dateend = datetype.format(enddate);

        String urlname = URLEncoder.encode(majorName, "utf-8");
        return "redirect:/admin/searchCheck?majorName=" + urlname + "&datestart=" + datestart + "&dateend=" + dateend;

    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<搜索操作结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<提醒功能开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //生日提醒
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
        return "admin/remindBirth";
    }

    //生日提醒删除
    @RequestMapping(value = "/removeBirthday", method = {RequestMethod.GET})
    private String removeBirthday(Integer id, Integer currentPage) throws Exception {

        remindService.removeBirthday(id);

        return "redirect:/admin/remindBirth?page=" + currentPage;
    }

    //缴费提醒
    @RequestMapping(value = "/remindPay", method = {RequestMethod.GET})
    public String remindPayUI(Model model, Integer page) throws Exception {

        List<LessonCustom> lessonCustoms = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
         pagingVO.setTotalCount(remindService.getCountRemindPay());
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            lessonCustoms = remindService.findAllRemindPay(1);
        } else {
            pagingVO.setToPageNo(page);
            lessonCustoms = remindService.findAllRemindPay(page);
        }
        List<StuCustom> stuCustomList = remindService.findAllRemindList();
        model.addAttribute("stuCustomList", stuCustomList);
        model.addAttribute("allStuList", lessonCustoms);
        model.addAttribute("pagingVO", pagingVO);
        return "admin/remindPay";
    }

    // 缴费提醒删除
    @RequestMapping(value = "/removeRemindPay", method = {RequestMethod.GET})
    private String removeRemindPay(Integer id, Integer currentPage) throws Exception {
        System.out.println(id);
        remindService.removeRemindPay(id);

        return "redirect:/admin/remindPay?page=" + currentPage;
    }

    //接收提醒(校长已签字)
    @RequestMapping(value = "/remindReceive", method = {RequestMethod.GET})
    public String remindReceive(Model model, Integer page) throws Exception {

        List<StuCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(signService.getCountAdminUpdate());

        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = stuService.adminUpdateRemind(1);
        } else {
            pagingVO.setToPageNo(page);
            list = stuService.adminUpdateRemind(page);
        }

        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);
        return "admin/remindReceive";
    }

    //接收提醒(校长未签字)
    @RequestMapping(value = "/remindReceiveUnsign", method = {RequestMethod.GET})
    public String remindReceiveUnsign(Model model, Integer page) throws Exception {

        List<StuCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(signService.getCountAdminUpdateUnsign());

        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = stuService.adminUpdateRemindUnsign(1);
        } else {
            pagingVO.setToPageNo(page);
            list = stuService.adminUpdateRemindUnsign(page);
        }

        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);
        return "admin/remindReceiveUnsign";
    }

    //新录入信息提醒 （已经签字）
    @RequestMapping(value = "/remindNewStu", method = {RequestMethod.GET})
    public String remindNewStu(Model model, Integer page) throws Exception {

        List<StuCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(signService.getCountAdminReceive());

        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = stuService.adminReceiveRemind(1);
        } else {
            pagingVO.setToPageNo(page);
            list = stuService.adminReceiveRemind(page);
        }

        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);
        return "admin/remindNewStu";
    }

    //新录入信息提醒（未签字）
    @RequestMapping(value = "/remindNewStuUnsign", method = {RequestMethod.GET})
    public String remindNewStuUnsign(Model model, Integer page) throws Exception {

        List<StuCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(signService.getCountAdminReceiveUnsign());

        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = stuService.adminReceiveRemindUnsign(1);
        } else {
            pagingVO.setToPageNo(page);
            list = stuService.adminReceiveRemindUnsign(page);
        }

        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);
        return "admin/remindNewStuUnsign";
    }


    //新录入信息处理
    @RequestMapping(value = "/signConfirm", method = {RequestMethod.GET})
    public String signConfirm(Integer stuid, String currentPage, Model model) throws Exception {

        Sign sign = signService.findSignByStuID(stuid);
        if (sign.getMastersignid() == 0) {
            model.addAttribute("message", "校长未签字，不可删除");
            return "error";
        }
        signService.adminSignReceive(stuid);
        //   examService.changeExamSign(stuid);
        //   lessonService.changeLessonSign(stuid);
        return "redirect:/admin/remindNewStu?page=" + currentPage;
    }

    //更新的信息处理
    @RequestMapping(value = "/updateConfirm", method = {RequestMethod.GET})
    public String updateConfirm(Integer stuid, String currentPage, Model model) throws Exception {

        Sign sign = signService.findSignByStuID(stuid);
        if (sign.getMastersignid() == 0) {
            model.addAttribute("message", "校长未签字，不可删除");
            return "error";
        }

        signService.adminSignUpdate(stuid);
        //  examService.changeExamSign(stuid);
        //  lessonService.changeLessonSign(stuid);
        return "redirect:/admin/remindReceive?page=" + currentPage;
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<提醒功能结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<缴费情况开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //查看缴费的学生 表三的上课开始时间<=查询当天日期<=表三上课结束时间
    @RequestMapping(value = "/paidStudent", method = {RequestMethod.GET})
    public String paidStudentUI(Model model, Integer page, Integer gradeid, Integer subjectid, Integer typeid) throws Exception {

        List<Grade> gradelist = gradeService.findAllGrade();
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
        List<LessonCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(stuService.countPayStuBySelect(gradeid, subjectid, typeid));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = stuService.findStuByPayStuAndSelect(1, gradeid, subjectid, typeid);
        } else {
            pagingVO.setToPageNo(page);
            list = stuService.findStuByPayStuAndSelect(page, gradeid, subjectid, typeid);
        }
        List<LessonCustom> allStuList = stuService.findAllStuByPayStuAndSelect(gradeid, subjectid, typeid);  //用于保存为excle 表格
        model.addAttribute("allStuList", allStuList);
        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);
        model.addAttribute("gradeIndex", gradeid);
        model.addAttribute("subjectIndex", subjectid);
        model.addAttribute("typeIndex", typeid);

        return "admin/paidStudent";
    }

    @RequestMapping(value = "/paidStudent", method = {RequestMethod.POST})
    public String paidStudent(Integer gradeid, Integer subjectid, Integer typeid) throws Exception {

        return "redirect:/admin/paidStudent?gradeid=" + gradeid + "&subjectid=" + subjectid + "&typeid=" + typeid;
    }

    //查看预缴费学员 查询当天日期<表三的上课时间且小于表三的上课结束时间
    @RequestMapping(value = "/prePayStu", method = {RequestMethod.GET})
    public String prePayStuUI(Model model, Integer page, Integer gradeid, Integer subjectid, Integer typeid) throws Exception {

        List<Grade> gradelist = gradeService.findAllGrade();
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
//        Integer gradeIndex;  //????????
//        gradeIndex = gradeid;  //????????
        List<LessonCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(stuService.countPrePayStuBySelect(gradeid, subjectid, typeid));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = stuService.findStuByPrePayStuAndSelect(1, gradeid, subjectid, typeid);
        } else {
            pagingVO.setToPageNo(page);
            list = stuService.findStuByPrePayStuAndSelect(page, gradeid, subjectid, typeid);
        }
        List<LessonCustom> allStuList = stuService.findAllStuByPrePayStuAndSelect(gradeid, subjectid, typeid);  //用于保存为excle 表格
        model.addAttribute("allStuList", allStuList);
        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);
        model.addAttribute("gradeIndex", gradeid);
        model.addAttribute("subjectIndex", subjectid);
        model.addAttribute("typeIndex", typeid);

        return "admin/prePayStu";
    }

    @RequestMapping(value = "/prePayStu", method = {RequestMethod.POST})
    public String prePayStu(Integer gradeid, Integer subjectid, Integer typeid) throws Exception {

        return "redirect:/admin/prePayStu?gradeid=" + gradeid + "&subjectid=" + subjectid + "&typeid=" + typeid;
    }

    //查看未交费的学生
    @RequestMapping(value = "/paidNotStudent", method = {RequestMethod.GET})
    public String paidNotStudentUI(Model model, Integer gradeid, Integer teleType, Integer page) throws Exception {

        if (teleType == null) teleType = 0;
        List<Grade> gradelist = gradeService.findAllGrade();
        Grade grade = new Grade();
        grade.setGradeid(-1);
        grade.setGradename("全部");
        gradelist.add(0, grade);
        List<StuCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(stuService.getCountByUnPayStu(gradeid, teleType));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = stuService.findStuByUnPayStu(1, gradeid, teleType);
        } else {
            pagingVO.setToPageNo(page);
            list = stuService.findStuByUnPayStu(page, gradeid, teleType);
        }
        List<StuCustom> allStuList = stuService.findAllStuByUnPayStu(gradeid, teleType);
        model.addAttribute("gradelist", gradelist);
        model.addAttribute("gradeIndex", gradeid);
        model.addAttribute("teleType", teleType);
        model.addAttribute("allStuList", allStuList);
        model.addAttribute("stuList", list);
        model.addAttribute("pagingVO", pagingVO);
        return "admin/paidNotStudent";
    }

    // 添加记事本目录（表格）
    @RequestMapping(value = "/paidNotStudent", method = {RequestMethod.POST})
    public String paidNotStudent(Integer gradeid, Integer teleType) throws Exception {

        return "redirect:/admin/paidNotStudent?gradeid=" + gradeid + "&teleType=" + teleType;
    }



    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<缴费情况结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/


    //显示记事本目录（表格）
    @RequestMapping(value = "/showNoteDic", method = {RequestMethod.GET})
    public String showNoteDicUI(Model model, Integer page) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<NoteDic> list = null;
        PagingVO pagingVO = new PagingVO();
        //pagingVO.setStringtemp(username);
        pagingVO.setTotalCount(userloginService.getCountNoteDic(username, 0));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findNoteDic(1, username, 0);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findNoteDic(page, username, 0);
        }
        model.addAttribute("noteDicList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showNoteDic";
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

        NoteDic noteDic = new NoteDic();
        noteDic.setDicname(dicName);
        noteDic.setUsername(username);
        noteDic.setDictype(0);
        userloginService.saveNoteDic(noteDic);

        return "redirect:/admin/showNoteDic?page=" + currentPage;
    }

    //删除记事本目录
    // 删除学生
    @RequestMapping(value = "/removeNoteDic", method = {RequestMethod.GET})
    private String removeNoteDic(Integer dicid, String currentPage) throws Exception {
      /*  if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            return "admin/showStudent";
        }*/
        userloginService.removeNoteDic(dicid);  //删除记事本目录
        userloginService.removeNoteByDicID(dicid);  //删除某个目录对应的内容
        return "redirect:/admin/showNoteDic?page=" + currentPage;
    }

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
            list = userloginService.findTextByName(1, username, 0);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findTextByName(page, username, 0);
        }
        model.addAttribute("textList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showTextDic";
    }

    // 添加记事本（文本）目录
    @RequestMapping(value = "/showTextDic", method = {RequestMethod.POST})
    public String showTextDic(String textName, String currentPage, Model model) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        TextDic textDic = new TextDic();
        textDic.setTitle(textName);
        textDic.setUsername(username);
        textDic.setTexttype(0);
        userloginService.saveText(textDic);
        return "redirect:/admin/showTextDic?page=" + currentPage;
    }


    @RequestMapping(value = "/removeText", method = {RequestMethod.GET})
    private String removeText(Integer textid, String currentPage) throws Exception {

        userloginService.removeTextByID(textid);
        return "redirect:/admin/showTextDic?page=" + currentPage;

    }

    // 记事本操作
    @RequestMapping(value = "/editNoteText", method = {RequestMethod.GET})
    public String editNoteTextUI(Integer textid, String currentPage, Model model) throws Exception {

        TextDic textDic = userloginService.findTextDicByID(textid);
        if (textDic == null) {
            model.addAttribute("message", "该记事本不存在");
            return "error";
        }
        //没有权限访问
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (!textDic.getUsername().equals(username)) {
            model.addAttribute("message", "没有权限访问");
            return "error";
        }
        model.addAttribute("textDic", textDic.getContent());
        model.addAttribute("textid", textid);
        model.addAttribute("texttitle", textDic.getTitle());
        model.addAttribute("currentPage", currentPage);
        return "admin/editNoteText";
    }


    // 记事本操作
    @RequestMapping(value = "/editNoteText", method = {RequestMethod.POST})
    public String editNoteText(TextDic textDic, String currentPage) throws Exception {
        userloginService.updeTextDicByID(textDic);
        //  System.out.print(currentPage);
        return "redirect:/admin/showTextDic?page=" + currentPage;
    }


    @RequestMapping(value = "/editSeNoteText", method = {RequestMethod.GET})
    public String editSeNoteTextUI(Integer textid, String currentPage, Model model) throws Exception {

        TextDic textDic = userloginService.findTextDicByID(textid);
        if (textDic == null) {
            model.addAttribute("message", "该记事本不存在");
            return "error";
        }
        //没有权限访问
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (!textDic.getUsername().equals(username)) {
            model.addAttribute("message", "没有权限访问");
            return "error";
        }
        model.addAttribute("textDic", textDic.getContent());
        model.addAttribute("textid", textid);
        model.addAttribute("texttitle", textDic.getTitle());
        model.addAttribute("currentPage", currentPage);
        return "admin/editSeNoteText";
    }


    // 记事本操作
    @RequestMapping(value = "/editSeNoteText", method = {RequestMethod.POST})
    public String editSeNoteText(TextDic textDic, String currentPage) throws Exception {
        userloginService.updeTextDicByID(textDic);
        //  System.out.print(currentPage);
        return "redirect:/admin/showSeTextDic?page=" + currentPage;
    }

    // 记事表操作
    @RequestMapping(value = "/editNoteTable", method = {RequestMethod.GET})
    public String editNoteTableUI(Model model, Integer page, Integer dicid) throws Exception {
        NoteDic noteDic = userloginService.findNoteDicByID(dicid);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (!noteDic.getUsername().equals(username)) {
            model.addAttribute("message", "没有权限访问");
            return "error";
        }

        List<NoteTable> list = null;
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(userloginService.getCountNoteTable(dicid));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findNoteTableByDicID(1, dicid);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findNoteTableByDicID(page, dicid);
        }

        List<NoteTable> noteTableList = userloginService.findAllNoteTableByDicID(dicid);
        model.addAttribute("dicid", dicid);
        model.addAttribute("noteTableList", list);
        model.addAttribute("allNoteTableList", noteTableList);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/editNoteTable";
    }

    // 记事表操作
    @RequestMapping(value = "/editNoteTable", method = {RequestMethod.POST})
    public String editNoteTable(String notetext) throws Exception {

        // Subject subject = SecurityUtils.getSubject();
        //String username = (String) subject.getPrincipal();
        // userloginService.updateNote(username, notetext);

        return "redirect:/admin/editNoteTable";
    }

    // 记事表操作
    @RequestMapping(value = "/editSeNoteTable", method = {RequestMethod.GET})
    public String editSeNoteTableUI(Model model, Integer page, Integer dicid) throws Exception {
        NoteDic noteDic = userloginService.findNoteDicByID(dicid);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (!noteDic.getUsername().equals(username)) {
            model.addAttribute("message", "没有权限访问");
            return "error";
        }

        List<NoteTable> list = null;
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(userloginService.getCountNoteTable(dicid));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findNoteTableByDicID(1, dicid);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findNoteTableByDicID(page, dicid);
        }

        List<NoteTable> noteTableList = userloginService.findAllNoteTableByDicID(dicid);
        model.addAttribute("dicid", dicid);
        model.addAttribute("noteTableList", list);
        model.addAttribute("allNoteTableList", noteTableList);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/editSeNoteTable";
    }

    // 记事表操作
    @RequestMapping(value = "/editSeNoteTable", method = {RequestMethod.POST})
    public String editSeNoteTable(String notetext) throws Exception {

        // Subject subject = SecurityUtils.getSubject();
        //String username = (String) subject.getPrincipal();
        // userloginService.updateNote(username, notetext);

        return "redirect:/admin/editSeNoteTable";
    }


    // 添加记事表操作
    @RequestMapping(value = "/addNoteTable", method = {RequestMethod.GET})
    public String addNoteTableUI(Model model, Integer dicid, Integer currentpage) throws Exception {


        List<Grade> gradelist = gradeService.findAllGrade();
        model.addAttribute("gradeList", gradelist);
        model.addAttribute("dicid", dicid);
        model.addAttribute("currentPage", currentpage);

        return "admin/addNoteTable";
    }

    // 添加记事表操作
    @RequestMapping(value = "/addNoteTable", method = {RequestMethod.POST})
    public String addNoteTable(NoteTable noteTable, Integer dicid, Integer currentpage) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        noteTable.setUsername(username);
        noteTable.setDicid(dicid);

        userloginService.saveNoteTable(noteTable);

        return "redirect:/admin/editNoteTable?dicid=" + dicid + "&page=" + currentpage;
    }

    // 添加记事表操作
    @RequestMapping(value = "/addSeNoteTable", method = {RequestMethod.GET})
    public String addSeNoteTableUI(Model model, Integer dicid, Integer currentpage) throws Exception {


        List<Grade> gradelist = gradeService.findAllGrade();
        model.addAttribute("gradeList", gradelist);
        model.addAttribute("dicid", dicid);
        model.addAttribute("currentPage", currentpage);

        return "admin/addSeNoteTable";
    }

    // 添加记事表操作
    @RequestMapping(value = "/addSeNoteTable", method = {RequestMethod.POST})
    public String addSeNoteTable(NoteTable noteTable, Integer dicid, Integer currentpage) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        noteTable.setUsername(username);
        noteTable.setDicid(dicid);

        userloginService.saveNoteTable(noteTable);

        return "redirect:/admin/editSeNoteTable?dicid=" + dicid + "&page=" + currentpage;
    }

    //删除记事本中的一条记录
    @RequestMapping(value = "/removeNoteTable", method = {RequestMethod.GET})
    private String removeNoteTable(Integer noteID, Integer dicID, String currentPage) throws Exception {
      /*  if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            return "admin/showStudent";
        }*/
        userloginService.removeNoteTable(noteID);
        return "redirect:/admin/editNoteTable?dicid=" + dicID + "&page=" + currentPage;

    }

    //删除记事本中的一条记录
    @RequestMapping(value = "/removeSeNoteTable", method = {RequestMethod.GET})
    private String removeSeNoteTable(Integer noteID, Integer dicID, String currentPage) throws Exception {
      /*  if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            return "admin/showStudent";
        }*/
        userloginService.removeNoteTable(noteID);
        return "redirect:/admin/editSeNoteTable?dicid=" + dicID + "&page=" + currentPage;

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
        return "admin/modifyNoteTable";
    }

    @RequestMapping(value = "/modifyNoteTable", method = {RequestMethod.POST})
    private String modifyNoteTable(NoteTable noteTable, Integer dicid, Integer page) throws Exception {

        userloginService.updateNoteTableByID(noteTable);
        return "redirect:/admin/editNoteTable?dicid=" + dicid + "&page=" + page;
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
        return "admin/modifySeNoteTable";
    }

    @RequestMapping(value = "/modifySeNoteTable", method = {RequestMethod.POST})
    private String modifySeNoteTable(NoteTable noteTable, Integer dicid, Integer page) throws Exception {

        userloginService.updateNoteTableByID(noteTable);
        return "redirect:/admin/editSeNoteTable?dicid=" + dicid + "&page=" + page;
    }

    //查看其他用户的记事本

    //显示记事本目录
    @RequestMapping(value = "/showUserNoteDic", method = {RequestMethod.GET})
    public String showUserNoteDic(Model model, Integer page, Integer page1, String username) throws Exception {

        PagingVO pagingVO = new PagingVO();
        List<TextDic> listtext = null;
        pagingVO.setTotalCount(userloginService.getCountText(username, 0));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            listtext = userloginService.findTextByName(1, username, 0);
        } else {
            pagingVO.setToPageNo(page);
            listtext = userloginService.findTextByName(page, username, 0);
        }
        model.addAttribute("textList", listtext);
        model.addAttribute("pagingVO", pagingVO);

        List<NoteDic> listnote = null;
        PagingVO pagingVO1 = new PagingVO();

        pagingVO1.setTotalCount(userloginService.getCountNoteDic(username, 0));
        if (page1 == null || page1 == 0) {
            pagingVO1.setToPageNo(1);
            listnote = userloginService.findNoteDic(1, username, 0);
        } else {
            pagingVO1.setToPageNo(page1);
            listnote = userloginService.findNoteDic(page1, username, 0);
        }
        model.addAttribute("noteDicList", listnote);
        model.addAttribute("pagingVO1", pagingVO1);

        model.addAttribute("username", username);
        return "admin/showUserNoteDic";
    }

    @RequestMapping(value = "/showUserSeNoteDic", method = {RequestMethod.GET})
    public String showUserSeNoteDic(Model model, Integer page, Integer page1, String username) throws Exception {

        PagingVO pagingVO = new PagingVO();
        List<TextDic> listtext = null;
        pagingVO.setTotalCount(userloginService.getCountText(username, 1));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            listtext = userloginService.findTextByName(1, username, 1);
        } else {
            pagingVO.setToPageNo(page);
            listtext = userloginService.findTextByName(page, username, 1);
        }
        model.addAttribute("textList", listtext);
        model.addAttribute("pagingVO", pagingVO);

        List<NoteDic> listnote = null;
        PagingVO pagingVO1 = new PagingVO();

        pagingVO1.setTotalCount(userloginService.getCountNoteDic(username, 1));
        if (page1 == null || page1 == 0) {
            pagingVO1.setToPageNo(1);
            listnote = userloginService.findNoteDic(1, username, 1);
        } else {
            pagingVO1.setToPageNo(page1);
            listnote = userloginService.findNoteDic(page1, username, 1);
        }
        model.addAttribute("noteDicList", listnote);
        model.addAttribute("pagingVO1", pagingVO1);

        model.addAttribute("username", username);
        return "admin/showUserNoteDic";
    }

    // 查看用户的文本
    @RequestMapping(value = "/showUserNoteText", method = {RequestMethod.GET})
    public String showUserNoteText(Model model, Integer textid) throws Exception {

        TextDic textDic = userloginService.findTextDicByID(textid);
        if (textDic == null) {
            model.addAttribute("message", "该记事本不存在");
            return "error";
        }
        model.addAttribute("textDic", textDic.getContent());
        model.addAttribute("texttitle", textDic.getTitle());

        return "admin/showUserNoteText";
    }

    // 记事表操作
    @RequestMapping(value = "/showUserNoteTable", method = {RequestMethod.GET})
    public String showUserNoteTable(Model model, Integer page, Integer dicid) throws Exception {

        List<NoteTable> list = null;
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(userloginService.getCountNoteTable(dicid));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findNoteTableByDicID(1, dicid);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findNoteTableByDicID(page, dicid);
        }

        List<NoteTable> noteTableList = userloginService.findAllNoteTableByDicID(dicid);
        model.addAttribute("dicid", dicid);
        model.addAttribute("noteTableList", list);
        model.addAttribute("allNoteTableList", noteTableList);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showUserNoteTable";
    }





    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<其他操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    // 普通用户账号密码重置
    @RequestMapping("/userPasswordRest")
    public String userPasswordRestUI() throws Exception {
        return "admin/userPasswordRest";
    }

    // 普通用户账号密码重置处理
    @RequestMapping(value = "/userPasswordRest", method = {RequestMethod.POST})
    public String userPasswordRest(Userlogin userlogin) throws Exception {

        Userlogin u = userloginService.findByName(userlogin.getUsername());

        if (u != null) {
            if (u.getRole() == 0) {
                throw new CustomException("该账户为管理员账户，没法修改");
            }
            u.setPassword(userlogin.getPassword());
            userloginService.updateByName(userlogin.getUsername(), u);
        } else {
            throw new CustomException("没找到该用户");
        }

        return "admin/userPasswordRest";
    }

    // 本账户密码重置
    @RequestMapping("/passwordRest")
    public String passwordRestUI() throws Exception {
        return "admin/passwordRest";
    }

    //判断用户名是否存在
    @ResponseBody
    @RequestMapping(value = "/isExist", method = {RequestMethod.POST, RequestMethod.GET})
    public String isExist(String realname) throws Exception {

        String result = null;
        List<StuCustom> stuCustomList = stuService.findByName(realname);
        if (stuCustomList.isEmpty() == true) {
            result = "0";
        } else {
            result = "1";
        }
        return result;
    }

    //显示第二个记事本（文本）目录
    @RequestMapping(value = "/showSeTextDic", method = {RequestMethod.GET})
    public String showSeTextDicUI(Model model, Integer page) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<TextDic> list = null;
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(userloginService.getCountText(username, 1));

        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findTextByName(1, username, 1);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findTextByName(page, username, 1);
        }
        model.addAttribute("textList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showSeTextDic";
    }

    // 添加记事本（文本）目录
    @RequestMapping(value = "/showSeTextDic", method = {RequestMethod.POST})
    public String showSeTextDic(String textName, String currentPage, Model model) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        TextDic textDic = new TextDic();
        textDic.setTitle(textName);
        textDic.setUsername(username);
        textDic.setTexttype(1);
        userloginService.saveText(textDic);
        return "redirect:/admin/showSeTextDic?page=" + currentPage;
    }

    @RequestMapping(value = "/removeSeText", method = {RequestMethod.GET})
    private String removeSeText(Integer textid, String currentPage) throws Exception {

        userloginService.removeTextByID(textid);
        return "redirect:/admin/showSeTextDic?page=" + currentPage;

    }

    //显示记事本目录（表格）
    @RequestMapping(value = "/showSeNoteDic", method = {RequestMethod.GET})
    public String showSeNoteDicUI(Model model, Integer page) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<NoteDic> list = null;
        PagingVO pagingVO = new PagingVO();
        //pagingVO.setStringtemp(username);
        pagingVO.setTotalCount(userloginService.getCountNoteDic(username, 1));
        if (page == null || page == 0) {
            pagingVO.setCurentPageNo(1);
            pagingVO.setToPageNo(1);
            list = userloginService.findNoteDic(1, username, 1);
        } else {
            pagingVO.setToPageNo(page);
            list = userloginService.findNoteDic(page, username, 1);
        }
        model.addAttribute("noteDicList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showSeNoteDic";
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

        NoteDic noteDic = new NoteDic();
        noteDic.setDicname(dicName);
        noteDic.setUsername(username);
        noteDic.setDictype(1);
        userloginService.saveNoteDic(noteDic);

        return "redirect:/admin/showSeNoteDic?page=" + currentPage;
    }

    //删除记事本目录
    // 删除学生
    @RequestMapping(value = "/removeSeNoteDic", method = {RequestMethod.GET})
    private String removeSeNoteDic(Integer dicid, String currentPage) throws Exception {
      /*  if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            return "admin/showStudent";
        }*/
        userloginService.removeNoteDic(dicid);  //删除记事本目录
        userloginService.removeNoteByDicID(dicid);  //删除某个目录对应的内容
        return "redirect:/admin/showSeNoteDic?page=" + currentPage;
    }

    //科目管理
    @RequestMapping(value = "/manageSubject", method = {RequestMethod.GET})
    public String managerSubjectUI(Model model) throws Exception {
        List<com.system.po.Subject> subjectList = subjectService.findAllSubject();
        model.addAttribute("subjectList", subjectList);
        return "admin/manageSubject";
    }

    @RequestMapping(value = "/manageSubject", method = {RequestMethod.POST})
    public String managerSubject(String subjectName) throws Exception {

        subjectService.saveSubject(subjectName);
        return "redirect:/admin/manageSubject";
    }

    @RequestMapping(value = "/removeSubject", method = {RequestMethod.GET})
    private String removeSubject(Integer subjectid) throws Exception {

        subjectService.deleteSubject(subjectid);
        return "redirect:/admin/manageSubject";
    }

    //科目类型管理
    @RequestMapping(value = "/manageClassType", method = {RequestMethod.GET})
    public String manageClassTypeUI(Model model) throws Exception {
        List<ClassType> classTypeList = classTypeService.findAllClassType();
        model.addAttribute("classTypeList", classTypeList);
        return "admin/manageClassType";
    }

    @RequestMapping(value = "/manageClassType", method = {RequestMethod.POST})
    public String manageClassType(String typename) throws Exception {
        classTypeService.saveClassType(typename);
        return "redirect:/admin/manageClassType";
    }

    @RequestMapping(value = "/removeClassType", method = {RequestMethod.GET})
    private String removeClassType(Integer typeid) throws Exception {
        classTypeService.deleteClassType(typeid);
        return "redirect:/admin/manageClassType";
    }

    //专业管理
    //科目管理
    @RequestMapping(value = "/manageMajor", method = {RequestMethod.GET})
    public String manageMajorUI(Model model) throws Exception {
        List<Major> majorList = majorService.findAllMajor();
        model.addAttribute("majorList", majorList);
        return "admin/manageMajor";
    }

    @RequestMapping(value = "/manageMajor", method = {RequestMethod.POST})
    public String manageMajor(String majorname) throws Exception {
        majorService.saveMajor(majorname);
        return "redirect:/admin/manageMajor";
    }

    @RequestMapping(value = "/removeMajor", method = {RequestMethod.GET})
    private String removeMajor(Integer majorid) throws Exception {
        majorService.deleteMajor(majorid);
        return "redirect:/admin/manageMajor";
    }

    //学校类型管理
    @RequestMapping(value = "/manageSchol", method = {RequestMethod.GET})
    public String manageSchoolUI(Model model) throws Exception {
        List<School> schoolList = schoolService.findAllSchool();
        model.addAttribute("schoolList", schoolList);
        return "admin/manageSchol";
    }

    @RequestMapping(value = "/manageSchol", method = {RequestMethod.POST})
    public String manageSchool(String schoolname) throws Exception {
        schoolService.saveSchool(schoolname);
        return "redirect:/admin/manageSchol";
    }

    @RequestMapping(value = "/removeSchool", method = {RequestMethod.GET})
    private String removeSchool(Integer schoolid) throws Exception {
        schoolService.deleteSchool(schoolid);
        return "redirect:/admin/manageSchol";
    }

    //校区管理
    @RequestMapping(value = "/manageCampus", method = {RequestMethod.GET})
    public String manageCampusUI(Model model) throws Exception {
        List<Campus> campusList = campusService.findAllCampus();
        model.addAttribute("campusList", campusList);
        return "admin/manageCampus";
    }

    @RequestMapping(value = "/manageCampus", method = {RequestMethod.POST})
    public String manageCampus(String campusname) throws Exception {
        campusService.saveCampus(campusname);
        return "redirect:/admin/manageCampus";
    }

    @RequestMapping(value = "/removeCampus", method = {RequestMethod.GET})
    private String removeCampus(Integer campusid) throws Exception {
        campusService.deleteCampus(campusid);
        return "redirect:/admin/manageCampus";
    }


    @RequestMapping(value = "/uploadExcel", method = {RequestMethod.GET})
    public String uploadExcel(Model model) throws Exception {

        List<ExcelLog> excelLogSuccess = excelLogService.findUploadSuccess();
        List<ExcelLog> excelLogFail = excelLogService.findUploadFail();
        List<ExcelLog> excelLogConfirm = excelLogService.findUploadConfirm();
        model.addAttribute("excelLogSuccess", excelLogSuccess);
        model.addAttribute("excelLogFail", excelLogFail);
        model.addAttribute("excelLogConfirm", excelLogConfirm);
        return "/admin/uploadExcel";
    }

    public static int index;
    public static int currentindex;

    //上传文件  新建学生档案
    //判断用户名是否存在
    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = {RequestMethod.POST})
    public String uploadExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        excelLogService.deleteExcel();
        String flag = "02";//表示没有保存到数据库
        int iRowNum = 0;// 工作表中的行数量
        int iCellNum = 0;// 每行中的列数量
        DecimalFormat df = new DecimalFormat("0");

        Integer completeNum = 0;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());

            HSSFWorkbook wb = new HSSFWorkbook(fs);// xls 获取工作簿对象

            HSSFSheet sheet = wb.getSheetAt(0);// 获取工作簿中第一个sheet表

            iRowNum = sheet.getPhysicalNumberOfRows();// 获取sheet表中总行数

            System.out.println("表格的总行数" + iRowNum);

            /** 开始读取行数据 */

            int startRowNum = 4;// excel  从第5行开始读取
            for (int j = startRowNum; j < iRowNum; j++) {
                HSSFRow rowTmp = sheet.getRow(j);
                if (rowTmp == null) {// 判断是否为空行
                    continue;
                }
                if (j == startRowNum) {
                    iCellNum = rowTmp.getLastCellNum();

                    //System.out.println("表格的总列数"+iCellNum);
                    if (iCellNum != 36) {// 判断列数与模板项数
                        flag = "导入表格列数与所选模板项数不符！请核对后重新上传。";
                        //model.addAttribute("message", "导入表格列数与所选模板项数不符！请核对后重新上传。");
                        return flag;

                    }
                    iCellNum = iCellNum + 1;// 获取第一行中总单元格总数+1
                }

                String aValues[] = new String[iCellNum];// 列数集合

                /** 开始读取每行中每个单元格数据 */

                for (int k = 0; k < iCellNum; k++) {
                    HSSFCell cellTmp = rowTmp.getCell(k);
                    if (cellTmp == null) {// 判断是否为空单元格
                        aValues[k] = new String("");
                        continue;
                    }
                    aValues[k] = parseCell(cellTmp);// 解析单元格中的数据
                    //System.out.println("第"+k+"列"+aValues[k]);

                }

                Stu stu = new Stu();
                Exam exam = new Exam();
                List<StuCustom> stuCustomList = stuService.findByName(aValues[1]);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                ExcelLog excelLog = new ExcelLog();

                //数据库中没有当前的姓名（加入到数据库中）
                if (stuCustomList.isEmpty()) {

                    stu.setStutype("普通学生");
                    stu.setRecordperson("");
                    stu.setRecorddate(date); //录入日期
                    stu.setEducationtext("");
                    stu.setFamilytext("");
                    stu.setImprovetext("");
                    stu.setSchooltext("");
                    stu.setStudytext("");
                    stu.setSupporttext("");
                    stu.setStuname(aValues[1]);
                    stu.setMothername(aValues[2]);
                    stu.setFathername(aValues[3]);
                    stu.setStutel(aValues[4]);
                    stu.setMothertel(aValues[5]);
                    stu.setFathertel(aValues[6]);
                    stu.setMastername(aValues[7]);
                    stu.setMastertel(aValues[8]);
                    if (!aValues[9].equals(""))
                        stu.setStubirth(sdf.parse(aValues[9]));
                    stu.setSchool(aValues[10]);
                    stu.setGradeid(parseGrade(aValues[11]));
                    stu.setMajor(aValues[12]);
                    stu.setAddress(aValues[13]);
                    stuService.save(stu);

                    Integer stuID = stu.getStuid();
                    signService.addStuSign(stuID);


                    //若偶有的成绩d
                    if (!aValues[16].equals("") || !aValues[17].equals("") || !aValues[18].equals("") || !aValues[19].equals("")
                            || !aValues[20].equals("") || !aValues[21].equals("") || !aValues[22].equals("") || !aValues[23].equals("")
                            || !aValues[24].equals("") || !aValues[25].equals("") || !aValues[26].equals("") || !aValues[27].equals("")
                            || !aValues[28].equals("") || !aValues[29].equals("") || !aValues[30].equals("") || !aValues[31].equals("")
                            || !aValues[32].equals("") || !aValues[33].equals("")) {
                        exam.setRecorddate(date);
                        exam.setStuid(stuID);
                        if (aValues[14] != "") exam.setExamdata(sdf.parse(aValues[14]));
                        exam.setUpdateperson(aValues[15]);
                        if (!aValues[16].equals("")) exam.setMath(Integer.parseInt(aValues[16]));   //待完善
                        if (!aValues[17].equals("")) exam.setMathscore(Integer.parseInt(aValues[17]));
                        if (!aValues[18].equals("")) exam.setChinese(Integer.parseInt(aValues[18]));
                        if (!aValues[19].equals("")) exam.setChinesescore(Integer.parseInt(aValues[19]));
                        if (!aValues[20].equals("")) exam.setEnglish(Integer.parseInt(aValues[20]));
                        if (!aValues[21].equals("")) exam.setEnglishscore(Integer.parseInt(aValues[21]));
                        if (!aValues[22].equals("")) exam.setPhysics(Integer.parseInt(aValues[22]));
                        if (!aValues[23].equals("")) exam.setPhyscore(Integer.parseInt(aValues[23]));
                        if (!aValues[24].equals("")) exam.setChemistry(Integer.parseInt(aValues[24]));
                        if (!aValues[25].equals("")) exam.setChemscore(Integer.parseInt(aValues[25]));
                        if (!aValues[26].equals("")) exam.setPolitics(Integer.parseInt(aValues[26]));
                        if (!aValues[27].equals("")) exam.setPolscore(Integer.parseInt(aValues[27]));
                        if (!aValues[28].equals("")) exam.setHistory(Integer.parseInt(aValues[28]));
                        if (!aValues[29].equals("")) exam.setHisscore(Integer.parseInt(aValues[29]));
                        if (!aValues[30].equals("")) exam.setBiology(Integer.parseInt(aValues[30]));
                        if (!aValues[31].equals("")) exam.setBioscore(Integer.parseInt(aValues[31]));
                        if (!aValues[32].equals("")) exam.setGeography(Integer.parseInt(aValues[32]));
                        if (!aValues[33].equals("")) exam.setGeoscore(Integer.parseInt(aValues[33]));
                        exam.setExamtype(aValues[34]);
                        exam.setExamremark(aValues[35]);
                        examService.save(exam);
                    }

                    //保存成功后，将记录存到excellog
                    excelLog.setExcelid(Integer.parseInt(aValues[0]));
                    excelLog.setStuname(aValues[1]);
                    excelLog.setTypeid(0);
                    excelLogService.saveExcel(excelLog);
                    completeNum++;

                } else { //数据控有相同的名字，存到excellog
                    excelLog.setExcelid(Integer.parseInt(aValues[0]));
                    excelLog.setStuname(aValues[1]);
                    excelLog.setTypeid(1);
                    excelLogService.saveExcel(excelLog);
                    completeNum++;
                }
                currentindex = completeNum;
                index = Integer.parseInt(df.format(Math.floor(completeNum * 100 / (iRowNum - 4))));
                //System.out.println(index);
            }
            flag = "01";

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return completeNum.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return completeNum.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return completeNum.toString();
        }
        return flag;
    }


    //更新学生信息
    @ResponseBody
    @RequestMapping(value = "/updateFile", method = {RequestMethod.POST})
    public String updateFile(@RequestParam("file") MultipartFile file, ModelMap model) {

        excelLogService.deleteExcel();
        DecimalFormat df = new DecimalFormat("0");
        String flag = "02";//表示没有保存到数据库
        int iRowNum = 0;// 工作表中的行数量
        int iCellNum = 0;// 每行中的列数量
        Integer completeNum = 0;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());

            HSSFWorkbook wb = new HSSFWorkbook(fs);// xls 获取工作簿对象

            HSSFSheet sheet = wb.getSheetAt(0);// 获取工作簿中第一个sheet表

            iRowNum = sheet.getPhysicalNumberOfRows();// 获取sheet表中总行数

            //System.out.println("表格的总行数"+iRowNum);

            /** 开始读取行数据 */

            int startRowNum = 4;// excel  从第5行开始读取
            for (int j = startRowNum; j < iRowNum; j++) {
                HSSFRow rowTmp = sheet.getRow(j);
                if (rowTmp == null) {// 判断是否为空行
                    continue;
                }
                if (j == startRowNum) {
                    iCellNum = rowTmp.getLastCellNum();

                    System.out.println("表格的总列数" + iCellNum);
                    if (iCellNum != 36) {// 判断列数与模板项数
                        flag = "导入表格列数与所选模板项数不符！请核对后重新上传。";
                        //model.addAttribute("message", "导入表格列数与所选模板项数不符！请核对后重新上传。");
                        return flag;

                    }
                    iCellNum = iCellNum + 1;// 获取第一行中总单元格总数+1
                }

                String aValues[] = new String[iCellNum];// 列数集合

                /** 开始读取每行中每个单元格数据 */

                for (int k = 0; k < iCellNum; k++) {
                    HSSFCell cellTmp = rowTmp.getCell(k);
                    if (cellTmp == null) {// 判断是否为空单元格
                        aValues[k] = new String("");
                        continue;
                    }
                    aValues[k] = parseCell(cellTmp);// 解析单元格中的数据
                    //System.out.println("第"+k+"列"+aValues[k]);

                }


                Exam exam = new Exam();
                List<StuCustom> stuCustomList = stuService.findByName(aValues[1]);
                StuCustom stuCustom = stuCustomList.get(0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                ExcelLog excelLog = new ExcelLog();

                if (stuCustomList.size() == 1) { //当前名字在数据库中只有一个

                    if (stuCustom.getGradename().equals(aValues[11]) && stuCustom.getSchool().equals(aValues[10])) {

                        if (!aValues[16].equals("") || !aValues[17].equals("") || !aValues[18].equals("") || !aValues[19].equals("")
                                || !aValues[20].equals("") || !aValues[21].equals("") || !aValues[22].equals("") || !aValues[23].equals("")
                                || !aValues[24].equals("") || !aValues[25].equals("") || !aValues[26].equals("") || !aValues[27].equals("")
                                || !aValues[28].equals("") || !aValues[29].equals("") || !aValues[30].equals("") || !aValues[31].equals("")
                                || !aValues[32].equals("") || !aValues[33].equals("")) {
                            exam.setRecorddate(date);
                            exam.setStuid(stuCustom.getStuid());
                            if (aValues[14] != "") exam.setExamdata(sdf.parse(aValues[14]));
                            exam.setUpdateperson(aValues[15]);
                            if (!aValues[16].equals("")) exam.setMath(Integer.parseInt(aValues[16]));   //待完善
                            if (!aValues[17].equals("")) exam.setMathscore(Integer.parseInt(aValues[17]));
                            if (!aValues[18].equals("")) exam.setChinese(Integer.parseInt(aValues[18]));
                            if (!aValues[19].equals("")) exam.setChinesescore(Integer.parseInt(aValues[19]));
                            if (!aValues[20].equals("")) exam.setEnglish(Integer.parseInt(aValues[20]));
                            if (!aValues[21].equals("")) exam.setEnglishscore(Integer.parseInt(aValues[21]));
                            if (!aValues[22].equals("")) exam.setPhysics(Integer.parseInt(aValues[22]));
                            if (!aValues[23].equals("")) exam.setPhyscore(Integer.parseInt(aValues[23]));
                            if (!aValues[24].equals("")) exam.setChemistry(Integer.parseInt(aValues[24]));
                            if (!aValues[25].equals("")) exam.setChemscore(Integer.parseInt(aValues[25]));
                            if (!aValues[26].equals("")) exam.setPolitics(Integer.parseInt(aValues[26]));
                            if (!aValues[27].equals("")) exam.setPolscore(Integer.parseInt(aValues[27]));
                            if (!aValues[28].equals("")) exam.setHistory(Integer.parseInt(aValues[28]));
                            if (!aValues[29].equals("")) exam.setHisscore(Integer.parseInt(aValues[29]));
                            if (!aValues[30].equals("")) exam.setBiology(Integer.parseInt(aValues[30]));
                            if (!aValues[31].equals("")) exam.setBioscore(Integer.parseInt(aValues[31]));
                            if (!aValues[32].equals("")) exam.setGeography(Integer.parseInt(aValues[32]));
                            if (!aValues[33].equals("")) exam.setGeoscore(Integer.parseInt(aValues[33]));
                            exam.setExamtype(aValues[34]);
                            exam.setExamremark(aValues[35]);
                            examService.save(exam);
                        }

                        //保存成功后，将记录存到excellog
                        excelLog.setExcelid(Integer.parseInt(aValues[0]));
                        excelLog.setStuname(aValues[1]);
                        excelLog.setTypeid(0);
                        excelLogService.saveExcel(excelLog);
                        completeNum++;
                    } else {//只有一个名字但是学校年级不能对应
                        excelLog.setExcelid(Integer.parseInt(aValues[0]));
                        excelLog.setStuname(aValues[1]);
                        excelLog.setTypeid(2);
                        excelLogService.saveExcel(excelLog);
                        completeNum++;
                    }

                } else { //数据库有相同的名字，存到excellog
                    excelLog.setExcelid(Integer.parseInt(aValues[0]));
                    excelLog.setStuname(aValues[1]);
                    excelLog.setTypeid(1);
                    excelLogService.saveExcel(excelLog);
                    completeNum++;
                }
                currentindex = completeNum;
                index = Integer.parseInt(df.format(Math.floor(completeNum * 100 / (iRowNum - 4))));
            }
            flag = "01";

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return completeNum.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return completeNum.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return completeNum.toString();
        }
        return flag;
    }

    //利用cookie存储当前的进度
    @ResponseBody
    @RequestMapping(value = "/getProgressValue", method = {RequestMethod.POST})
    public void getProgressValue(@RequestParam(value = "isComplated") String isComplated, HttpServletResponse response) {


        if (isComplated.equals("true")) {
            index = 0;
            currentindex = 0;
        }
        String json = "{\"progressValue\":\"" + index + "\" , \"currentValue\":\"" + currentindex + "\"}";
        try {
            response.getWriter().print(json);  //返回json数据格式
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对单元格进行解析操作
     *
     * @param cell
     * @return
     */
    public String parseCell(HSSFCell cell) {
        String result = new String();
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    DecimalFormat format = new DecimalFormat("######0.00");
                    // 单元格设置成常规
                    result = format.format(value);

                    String a = result.substring(result.length() - 3);

                    if (a.equals(".00")) {// 如果以 .00 结尾 取消不显示
                        result = result.substring(0, result.length() - 3);
                    }

                }
                break;
            case HSSFCell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString().trim();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                result = "";
            default:
                result = "";
                break;
        }
        return result;
    }

    /**
     * 对年级进行解析操作
     *
     * @param grade
     * @return
     */

    public Integer parseGrade(String grade) {
        if (grade.equals("幼小")) {
            return 0;
        } else if (grade.equals("小一")) {
            return 1;
        } else if (grade.equals("小二")) {
            return 2;
        } else if (grade.equals("小三")) {
            return 3;
        } else if (grade.equals("小四")) {
            return 4;
        } else if (grade.equals("小五")) {
            return 5;
        } else if (grade.equals("小六")) {
            return 6;
        } else if (grade.equals("初一")) {
            return 7;
        } else if (grade.equals("初二")) {
            return 8;
        } else if (grade.equals("初三")) {
            return 9;
        } else if (grade.equals("高一")) {
            return 10;
        } else if (grade.equals("高二")) {
            return 11;
        } else if (grade.equals("高三")) {
            return 12;
        } else if (grade.equals("大一")) {
            return 13;
        } else if (grade.equals("大二")) {
            return 14;
        } else if (grade.equals("大三")) {
            return 15;
        } else if (grade.equals("大四")) {
            return 16;
        } else if (grade.equals("工作")) {
            return 17;
        } else
            return 0;
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
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }


    //下载图片
    @RequestMapping(value = "/downloadImage", method = {RequestMethod.GET})
    public String downloadImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletContext().getRealPath("/") + "/uploadImages/";
        String fileName = request.getParameter("filename");
        //获取扩展
        String extName = fileName.substring(fileName.lastIndexOf("."));//.jpg
        String title = request.getParameter("title")+extName;
        Integer stuid = Integer.parseInt(request.getParameter("stuid"));
        File file = new File(path + fileName);
        if (file.exists()) {
            //设置MIME类型
            response.setContentType("application/octet-stream");
            //或者为response.setContentType("application/x-msdownload");
            //设置头信息,设置文件下载时的默认文件名，同时解决中文名乱码问题
            response.addHeader("Content-disposition", "attachment;filename=" + new String(title.getBytes(), "ISO-8859-1"));
            InputStream inputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] bs = new byte[1024];
            while ((inputStream.read(bs) > 0)) {
                outputStream.write(bs);
            }
            outputStream.close();
            inputStream.close();
        }
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }

    //删除图片
    @RequestMapping(value = "/deleteImage", method = {RequestMethod.GET})
    public String deleteImage(HttpServletRequest request) throws IOException {
        String path = request.getServletContext().getRealPath("/") + "/uploadImages/";
        String fileName = request.getParameter("filename");
        Integer stuid = Integer.parseInt(request.getParameter("stuid"));
        File file = new File(path + fileName);
        if (file.exists()) {
            file.delete();
            imageService.removeImageByName(fileName);
        }
        String encodeID = Base64.getEncoder().encodeToString(stuid.toString().getBytes(StandardCharsets.UTF_8));
        //重定向
        return "redirect:/admin/editTableOne?encodeID=" + encodeID;
    }
}



