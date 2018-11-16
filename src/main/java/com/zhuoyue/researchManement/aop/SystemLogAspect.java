package com.zhuoyue.researchManement.aop;

import com.zhuoyue.researchManement.annotation.SystemControllerLog;
import com.zhuoyue.researchManement.exception.BadRequestException;
import com.zhuoyue.researchManement.util.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class SystemLogAspect
{
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
	
	// Controller层切点
	@Pointcut("@annotation(com.zhuoyue.researchManement.annotation.SystemControllerLog)")
	public void controllerAspect()
	{
		System.out.println("controllerAspect");
	}
	
	/**
	 * 异常通知 用于拦截service层记录异常日志
	 *
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e)
	{
		catchLog(joinPoint, e);
	}
	
	private void catchLog(JoinPoint joinPoint, Throwable e)
	{
		if (filterException(e)) return;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		try
		{
			String ip = IpUtils.getIpAddr(request);
			String params = getRequestParams(joinPoint);
			
			System.out.println("----------");
			System.out.println("请求方法: " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
			System.out.println("方法描述: " + getControllerMethodDescription(joinPoint));
			System.out.println("请求人: " + null);
			System.out.println("请求IP: " + ip);
			System.out.println("请求参数: " + params);
			System.out.println("异常代码: " + e.getClass().getName());
			System.out.println("异常信息: " + e.getMessage());
			System.out.println("异常详细: " + stackException(e));
			System.out.println("----------");
		} catch (Exception ex)
		{
			ex.printStackTrace();
			// 记录本地异常日志
			logger.error("异常记录异常: " + e.getMessage());
		}
	}

	/**
	 * 过滤不处理的exception
	 * @param e
	 * @return
	 */
	private boolean filterException(Throwable e) {
		if (e instanceof BadRequestException) return true;
		return false;
	}
	
	/**
	 * 获取注解中对方的描述信息 用于Controller层注解
	 *
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	private String getControllerMethodDescription(JoinPoint joinPoint) throws Exception
	{
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods)
		{
			if (method.getName().equals(methodName))
			{
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length)
				{
					description = method.getAnnotation(SystemControllerLog.class).value();
					break;
				}
			}
		}
		return description;
	}
	
	private String getRequestParams(JoinPoint joinPoint)
	{
		StringBuilder params = new StringBuilder();
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0)
		{
			for (Object arg : joinPoint.getArgs())
			{
				params.append(arg).append(";");
			}
		}
		return params.toString();
	}
	
	private String stackException(Throwable e)
	{
		StackTraceElement[] stackTraceElementArray = e.getStackTrace();
		StringBuffer sb = new StringBuffer();
		for (StackTraceElement stackTraceElement : stackTraceElementArray)
		{
			sb.append(stackTraceElement.toString()).append("\n");
		}
		return sb.toString();
	}
}
