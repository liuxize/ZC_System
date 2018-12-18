package com.system.realm;

import com.system.po.Role;
import com.system.po.Userlogin;
import com.system.service.RoleService;
import com.system.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;


@Component
public class LoginRealm extends AuthorizingRealm{

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    @Autowired
    DefaultWebSessionManager sessionManager;


    public static Map<String,Session> stringSessionMap = new HashMap<String, Session>();


    /**
     * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     *     当调用权限验证时，就会调用此方法
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String) getAvailablePrincipal(principalCollection);

        Role role = null;

        try {
            Userlogin userlogin = userloginService.findByName(username);
            //获取角色对象
            role = roleService.findByid(userlogin.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> r = new HashSet<String>();
        if (role != null) {
            r.add(role.getRolename());
            info.setRoles(r);
        }

        return info;
    }

    /**
     * 在这个方法中，进行身份验证
     *         login时调用
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken realToken = (UsernamePasswordToken) token;
        //用户名
        String username = (String) realToken.getPrincipal();

        //密码
        String password = new String((char[])realToken.getCredentials());

        Userlogin userlogin = null;
        try {
            userlogin = userloginService.findByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userlogin == null) {
            //没有该用户名
            throw new UnknownAccountException();
        } else if (!password.equals(userlogin.getPassword())) {
            //密码错误
            throw new IncorrectCredentialsException();
        }

        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        for(Session session:sessions){
            //清除该用户以前登录时保存的session
            String sessionName = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
            if(username.equals(sessionName)) {
                if(!stringSessionMap.containsKey(realToken.getHost())){
                    sessionManager.getSessionDAO().delete(session);
                    for(String key : stringSessionMap.keySet()){
                        if(stringSessionMap.get(key) == session){
                            stringSessionMap.remove(key);
                            break;
                        }
                    }
                }
            }
        }
        //身份验证通过,返回一个身份信息
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(username,password,getName());

        return aInfo;
    }
}
