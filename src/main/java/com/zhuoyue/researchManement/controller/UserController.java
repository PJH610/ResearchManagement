package com.zhuoyue.researchManement.controller;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.zhuoyue.researchManement.annotation.SystemControllerLog;
import com.zhuoyue.researchManement.bean.MyPageInfo;
import com.zhuoyue.researchManement.bean.Password;
import com.zhuoyue.researchManement.bean.Unit;
import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.common.UserManager;
import com.zhuoyue.researchManement.enums.Gender;
import com.zhuoyue.researchManement.enums.RoleType;
import com.zhuoyue.researchManement.enums.RoleTypeEnum;
import com.zhuoyue.researchManement.exception.BadRequestException;
import com.zhuoyue.researchManement.service.UserService;
import com.zhuoyue.researchManement.util.Md5Utils;
import com.zhuoyue.researchManement.util.RSAUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhuoyue.researchManement.enums.RoleTypeEnum.SUBJECT_HOST;


@RestController
@RequestMapping("/api/user")
public class
UserController extends BaseApiController {

	@Autowired
	UserService userService;

	// 获取加密密码所需的公钥
	@PostMapping("/pubkey")
	public Map<String, Object> pubkey(HttpSession session) {
		Map<String, Object> keypair = RSAUtils.getKey();
		session.setAttribute(RSAUtils.KEYPAIR, keypair);

		RSAPublicKey publicKey = (RSAPublicKey) keypair.get(RSAUtils.PUBLIC_KEY);

		String modulus = publicKey.getModulus().toString(16);
		String exponent = publicKey.getPublicExponent().toString(16);

		Map<String, Object> param = new HashMap<>();
		param.put("modulus", modulus);
		param.put("exponent", exponent);

		return onDataResp(param);
	}

	// 登录接口
	@PostMapping("/login")
	public Map<String, Object> login(@RequestParam String uname, @RequestParam String password, HttpSession session) {
		if (uname == null || uname.trim().length() == 0) return onBadResp ("用户名不能为空");
		if (password == null || password.trim().length() == 0) return onBadResp ("密码不能为空");

		Password pwd = null;
		try {
			Map<String, Object> keypair = (Map<String, Object>) session.getAttribute(RSAUtils.KEYPAIR);
			session.removeAttribute(RSAUtils.KEYPAIR);

			if (keypair != null) pwd = new Gson().fromJson(RSAUtils.decrypt((RSAPrivateKey) keypair.get(RSAUtils.PRIVATE_KEY), password), Password.class);
		} catch (Exception e) {
		}
		if (pwd == null) return onBadResp("密码错误");

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(uname.trim(), pwd.getPassword());

		try {
			// 登录，即身份验证
			subject.login(token);

			return onSuccessRep("登录成功");
		} catch (UnknownAccountException e) {
			return onBadResp("用户名或密码错误");
		} catch (IncorrectCredentialsException e) {
			return onBadResp("用户名或密码错误");
		} catch (LockedAccountException e) {
			return onBadResp("登录失败，该用户已被冻结");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return onBadResp("验证失败");
		}

	}

	// 注销登录接口
	@RequestMapping("/logout")
	public Map<String, Object> logout() {
		return onSuccessRep("已退出登录");
	}

	// 注册接口
	@RequestMapping("/register")
	public Map<String, Object> register(@RequestParam String uname, @RequestParam String realname, @RequestParam String password,
										@RequestParam Gender gender, @RequestParam(defaultValue = "") String ethnic,
										@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date birthday,
										Long unit_id, @RequestParam(defaultValue = "") String position ,
										@RequestParam(defaultValue = "") String professional_title,
										@RequestParam(defaultValue = "") String email, @RequestParam(defaultValue = "") String phone,
										@RequestParam(defaultValue = "") String telephone, @RequestParam(defaultValue = "") String fax,
										@RequestParam(defaultValue = "") String education, @RequestParam(defaultValue = "") String profession,
										@RequestParam(defaultValue = "") String degree, @RequestParam(defaultValue = "") String postcode,
										@RequestParam(defaultValue = "") String address) {
		if (uname == null || uname.trim().length() == 0) return onBadResp ("账号不能为空");
		if (realname == null || realname.trim().length() == 0) return onBadResp ("姓名不能为空");
		if (password == null || password.trim().length() == 0) return onBadResp ("密码不能为空");
		if (gender == null) return onBadResp ("性别不能为空");
		if (unit_id == null) return onBadResp ("单位不能为空");
		if (birthday == null) return onBadResp ("出生日期不能为空");

		User user = new User();
		user.setUname(uname.trim());
		user.setRealname(realname.trim());
		user.setPassword(Md5Utils.encrypt(uname.trim(), password.trim()));
		user.setGender(gender);
		user.setEthnic(ethnic.trim());
		user.setUnit(new Unit(unit_id, null, null));
		user.setBirthday(new Date());
		user.setPosition(position.trim());
		user.setProfessionalTitle(professional_title.trim());
		user.setEmail(email.trim());
		user.setPhone(phone.trim());
		user.setTelephone(telephone.trim());
		user.setFax(fax.trim());
		user.setEducation(education.trim());
		user.setProfession(profession.trim());
		user.setLastlogin(new Date());
		user.setDegree(degree.trim());
		user.setPostcode(postcode.trim());
		user.setAddress(address.trim());
		user.setRoleType(SUBJECT_HOST);

		if (userService.selectByUname(user.getUname()) != null) return onBadResp(user.getUname() + " 已经存在，不能重复注册");

		userService.insert(user);
		return onRespWithId("注册成功", user.getId());
	}

	// 分页显示，查询用户
	@GetMapping("/list")
	@SystemControllerLog("用户列表")
	public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size){
		PageHelper.startPage(page_num, page_size);
		return onDataResp(new MyPageInfo <User> (userService.list()));
	}

	@GetMapping("/list/expert")
	@SystemControllerLog("专家列表")
	@RequiresRoles(value = RoleType.CITY_RESEARCH, logical = Logical.OR)
	public Map<String, Object> listExpert(){
		return onDataResp(userService.list(RoleTypeEnum.EXPERT));
	}

	@GetMapping("/show/{id}")
	public Map<String,Object> show(@PathVariable Long id) {
		return onDataResp(userService.selectById(id));
	}

	@GetMapping("/current")
	public Map<String,Object> show(HttpServletRequest request) {
		return onDataResp(userService.selectById(UserManager.getUser(request).getId()));
	}

	//修改资料接口
	@PostMapping("/update")
	public Map <String, Object> update(String realname, Gender gender, String ethnic, Long unit_id, @DateTimeFormat(pattern="yyyy-MM-dd") Date birthday,
									   String email, String position, String professional_title, String phone, String telephone, String fax,
									   String education, String degree, String profession, String postcode, String address
			, HttpServletRequest request) {

		if (realname != null && realname.trim().length() == 0) return onBadResp ("姓名不能为空");

		User currentUser = UserManager.getUser(request);

		User user = new User();
		user.setId(currentUser.getId());
		if (realname != null) user.setRealname(realname.trim());
		if (gender != null) user.setGender(gender);
		if (ethnic != null) user.setEthnic(ethnic.trim());
		if (birthday != null) user.setBirthday(birthday);
		if (unit_id != null) user.setUnit(new Unit(unit_id, null, null));
		if (position != null) user.setPosition(position.trim());
		if (professional_title != null) user.setProfessionalTitle(professional_title.trim());
		if (phone != null) user.setPhone(phone.trim());
		if (email != null) user.setEmail(email.trim());
		if (telephone != null) user.setTelephone(telephone.trim());
		if (fax != null) user.setFax(fax.trim());
		if (education != null) user.setEducation(education.trim());
		if (degree != null) user.setDegree(degree.trim());
		if (profession != null) user.setProfession(profession.trim());
		if (postcode != null) user.setPostcode(postcode.trim());
		if (address != null) user.setAddress(address.trim());

		userService.updateById(user);

		UserManager.setUser(userService.selectById(currentUser.getId()), request);
		return onSuccessRep("修改成功");
	}

	// 修改密码接口
	@PostMapping("/update/pwd")
	public Map <String, Object>updatePwd (@RequestParam String password, @RequestParam String new_password, HttpServletRequest request) {
		if (password == null || password.trim().length() == 0) return onBadResp ("原密码长度必须大于零");
		if (new_password == null || new_password.trim().length() == 0) return onBadResp ("新密码长度必须大于零");

		User user = userService.selectById(UserManager.getUser(request).getId());
		if (user == null || !user.getPassword().equals(Md5Utils.encrypt(user.getUname(), password.trim()))) return onBadResp("密码修改失败");

		User updateUser = new User();
		updateUser.setId(user.getId());
		updateUser.setPassword(Md5Utils.encrypt(user.getUname(), new_password.trim()));
		userService.updateById (updateUser);

		return onSuccessRep(" 密码修改成功");
	}


	// delect 接口
	@PostMapping("/delete")
	public Map<String,Object> delte(@RequestParam Long[] id) {
		userService.deleteById(id);
		return onSuccessRep("删除成功");
	}
}


