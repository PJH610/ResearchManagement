package com.zhuoyue.researchManement.shiro;

import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.common.UserManager;
import com.zhuoyue.researchManement.enums.RoleTypeEnum;
import com.zhuoyue.researchManement.service.UserService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 为当前前登录的Subjsct授予角色和权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前登录的用户名
        String username = (String) super.getAvailablePrincipal(principals);
        // 从数据库中获取当前登录用户的详细信息
        User user = userService.selectByUname(username.trim());
        if (user == null) return null;

        RoleTypeEnum roleTypeEnum = user.getRoleType();
        List<String> roleList = new ArrayList<String>();
        if (roleTypeEnum != null) roleList.add(roleTypeEnum.getDesc());

        // 为当前用户设置角色和权限
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        simpleAuthorInfo.addRoles(roleList);
        return simpleAuthorInfo;
    }

    /**
     * 验证当前登录的Subject
     * 经测试：本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取基于用户名和密码的令牌
        // 实际上这个authcToken是从AdminController里面currentUser.login(token)传过来的
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        System.err.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));

        User user = userService.selectByUname(upToken.getUsername());
        if (user != null) {
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUname());//这里的参数要给个唯一的;
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUname(), user.getPassword(), credentialsSalt, String.valueOf(user.getId()));

            this.setSession(UserManager.CURRENT_USER, user);
            return authcInfo;
        }

        return null;
    }

    /**
     * 将一些数据放到ShiroSession中，以便于其它地方使用
     * 比如Controller，使用时直接用HttpSession.getAttribute(key)就可以取到
     * @param key
     * @param value
     */
    private void setSession(Object key, Object value) {
        Subject cureentUser = SecurityUtils.getSubject();
        if (cureentUser != null) {
            Session session = cureentUser.getSession();
            System.out.println("Session默认超时时间为[" + ((org.apache.shiro.session.Session) session).getTimeout() + "]毫秒");
            if (session != null) {
                ((org.apache.shiro.session.Session) session).setAttribute(key, value);
            }
        }
    }
}
