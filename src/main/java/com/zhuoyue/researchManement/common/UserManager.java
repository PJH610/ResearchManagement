package com.zhuoyue.researchManement.common;

import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.exception.CurrentUserException;

import javax.servlet.http.HttpServletRequest;

public class UserManager {

    public static final String CURRENT_USER = "currentUser";

    public static User getUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        if (user == null) throw new CurrentUserException("用户信息异常");
        return user;
    }

    public static void setUser(User user, HttpServletRequest request) {
        request.getSession().setAttribute(CURRENT_USER, user);
    }

}
