package com.zhuoyue.researchManement.service;


import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.enums.RoleTypeEnum;

import java.util.List;

public interface UserService {

	int insert(User user);

	List<User> list(RoleTypeEnum... roleType);

	User selectById(Long id);

	User selectByUname(String uname);

	int updateById(User user);

	int deleteById(Long... id);
}