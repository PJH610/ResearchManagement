package com.zhuoyue.researchManement.service.impl;


import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.dao.UserDao;
import com.zhuoyue.researchManement.enums.RoleTypeEnum;
import com.zhuoyue.researchManement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDAO;

    @Override
    public int insert(User student) {
        return userDAO.insert(student);
    }

    @Override
    public List<User> list(RoleTypeEnum... roleType) {
        return userDAO.list(roleType);
    }

    @Override
    public User selectById(Long id) {
        return userDAO.selectById(id);
    }

    @Override
    public User selectByUname(String uname) {
        return userDAO.selectByUname(uname);
    }

    @Override
    public int updateById(User user) {
        return userDAO.updateById(user);
    }

    @Override
    public int deleteById(Long... id) {
        if (id == null || id.length == 0) return 0;
        return userDAO.deleteById(id);
    }
}