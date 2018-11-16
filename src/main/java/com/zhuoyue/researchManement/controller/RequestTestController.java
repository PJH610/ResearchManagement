package com.zhuoyue.researchManement.controller;

/**
 * Created by 12413 on 2018/5/6.
 */

import com.zhuoyue.researchManement.annotation.SystemControllerLog;
import com.zhuoyue.researchManement.exception.BadRequestException;
import com.zhuoyue.researchManement.service.TestService;
import com.zhuoyue.researchManement.util.RSAHelper;
import com.zhuoyue.researchManement.util.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/requestTest")
public class RequestTestController extends BaseApiController
{
	@Autowired
	private TestService testService;

	private static RequestTestController requestTestController = new RequestTestController();

	@GetMapping()
	@SystemControllerLog("测试接口")
	public Map<String, Object> testString(Long id, String name, String des, HttpSession session)
	{
		int a = 1 / 0;
		if (true) throw new BadRequestException("出错测试");
		testService.selectTest();
		if (name == null || name.trim().length() == 0) throw new BadRequestException("名称不能为空");
		return onRespWithId("插入成功", 12L);
	}

	@GetMapping("/controllerTest")
	@SystemControllerLog("controller接口测试")
	public Map<String, Object> controllerTest() {
		return requestTestController.testString(1L, "名称", "描述", null);
	}

	@GetMapping("/login")
	public String login(Boolean logout, HttpSession session) {
		if (logout != null && logout) {
			session.removeAttribute("t_login");
			return "logout";
		} else {
			session.setAttribute("t_login", "true");
			return "login";
		}

	}

	@GetMapping("/login/get")
	public String loginGet(HttpSession session, HttpServletRequest request) {
		System.out.println("wordUri: " + request.getServletContext().getRealPath("/WEB-INF/data/"));
		if ("true".equals(session.getAttribute("t_login"))) return "true";
		return "false";
	}



	@PostMapping("/pubkey")
	public Map<String, Object> pubkey(HttpSession session) throws Exception {
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

	@PostMapping("/decrypt")
	public Map<String, Object> decrypt(@RequestParam String param, HttpSession session) {
		Map<String, Object> keypair = (Map<String, Object>) session.getAttribute(RSAUtils.KEYPAIR);
		session.removeAttribute(RSAUtils.KEYPAIR);

		return onSuccessRep(RSAUtils.decrypt((RSAPrivateKey) keypair.get(RSAUtils.PRIVATE_KEY), param));
	}


}
