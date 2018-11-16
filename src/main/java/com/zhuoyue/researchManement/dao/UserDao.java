package com.zhuoyue.researchManement.dao;




import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.enums.RoleTypeEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

	int insert(User student);

	List<User> list(@Param("roleType") RoleTypeEnum... roleType);

	User selectById(Long id);

	User selectByUname(String uname);

	int updateById(User student); // 通过id进行修改

	int deleteById(Long... id);
}

