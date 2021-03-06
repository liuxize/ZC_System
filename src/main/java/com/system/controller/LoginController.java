package com.system.controller;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.net.URL;

import com.system.po.Loginlog;
import com.system.po.Userlogin;
import com.system.realm.LoginRealm;


import com.system.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.StringTokenizer;

/**
 * Created by Jacey on 2017/6/30.
 */
@Controller
public class LoginController {
    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Autowired
    DefaultWebSessionManager sessionManager;

    //登录跳转
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String loginUI() throws Exception {
        return "../../login";
    }

    //登录表单处理
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login(Userlogin userlogin,HttpServletRequest request) throws Exception {

        String nowUseKey = getNowUseKey(request,userlogin);
        //Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(userlogin.getUsername(),
                userlogin.getPassword(),nowUseKey);
        Subject subject = SecurityUtils.getSubject();
        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
        subject.login(token);

//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        if (ip.equals("0:0:0:0:0:0:0:1")) {
//            ip = "本地";
//        }
        Loginlog loginlog = new Loginlog();
        loginlog.setUsername((String) subject.getPrincipal());
        Date day=new Date();
        Timestamp timeStamp = new Timestamp(day.getTime());
        //System.out.println(timeStamp);

        try {
            // 获取ip归属地的json信息
            StringBuilder ipJson = new StringBuilder("");
            String  url = "http://pv.sohu.com/cityjson?ie=utf-8";

            Scanner scanner = new Scanner(new URL(url).openConnection().getInputStream(),
                    "utf-8");
            while (scanner.hasNext()) {
                ipJson.append(scanner.next());
            }
            ipJson.delete(0,16).deleteCharAt(ipJson.length()-1);
            String[] temp = ipJson.toString().split(",");
            String cip = temp[0].split(":")[1].replace("\"","");
            String cname = temp[2].split(":")[1].replace("}","").replace("\"","");;

            loginlog.setCip(cip);
            loginlog.setCname(cname);
        } catch (Exception e) {
            loginlog.setCip("IP查询接口错误");
            loginlog.setCname("区域查询接口错误");
            e.printStackTrace();
        }
        //System.out.println(loginlog.getCip());
        //System.out.println(loginlog.getCname());
        userloginService.saveLoginLog(loginlog);




        if (subject.hasRole("admin")) {
            putSession(userlogin.getUsername(),nowUseKey);
            return "redirect:/admin/announcement";
        } else if (subject.hasRole("master")) {
            putSession(userlogin.getUsername(),nowUseKey);
            return "redirect:/master/announcement";
        } else if (subject.hasRole("teacher")) {
            putSession(userlogin.getUsername(),nowUseKey);
            return "redirect:/teacher/announcement";
        } else if(subject.hasRole("leader")) {
            return  "redirect:/leader/announcement";
        }

        return "/login";
    }

    private void putSession(String username,String nowUseKey){
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        for(Session session:sessions){
            String sessionName = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
            if(username.equals(sessionName)) {
                LoginRealm.stringSessionMap.put(nowUseKey,session);
            }
        }
    }

    private String getNowUseKey(HttpServletRequest request,Userlogin userlogin){
        String remoteAddr = request.getRemoteAddr();
        String  browserDetails  =   request.getHeader("User-Agent");
        StringTokenizer st = new StringTokenizer(browserDetails,";");
        st.nextToken();
        //得到用户的浏览器名
        String userbrowser = st.nextToken();
        return remoteAddr + userbrowser+userlogin.getUsername();
    }

}
