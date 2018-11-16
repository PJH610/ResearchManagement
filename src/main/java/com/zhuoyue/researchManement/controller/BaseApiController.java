package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.MyPageInfo;
import com.zhuoyue.researchManement.exception.BadRequestException;
import com.zhuoyue.researchManement.exception.CurrentUserException;
import com.zhuoyue.researchManement.exception.FileSaveException;
import org.apache.shiro.authz.UnauthorizedException;
import org.omg.CORBA.Current;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseApiController
{

	// 运行时异常
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public Map<String, Object> runtimeExceptionHandler(RuntimeException ex)
	{
		ex.printStackTrace();
		return onResponse(500, "运行时异常");
	}
	
	// 空指针异常
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public Map<String, Object> nullPointExceptionHandler(NullPointerException ex)
	{
		ex.printStackTrace();
		return onResponse(500, "空指针异常");
	}
	
	// 类型转换异常
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ClassCastException.class)
	public Map<String, Object> classCastExceptionHandler(ClassCastException ex)
	{
		ex.printStackTrace();
		return onResponse(500, "空指针异常");
	}
	
	// IO异常
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IOException.class)
	public ResponseEntity<Object> iOException(IOException ex)
	{
		ex.printStackTrace();
		return new ResponseEntity<Object>(onResponse(500, "IO异常"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 未知方法异常
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NoSuchMethodException.class)
	public Map<String, Object> noSuchMethodExceptionHandler(NoSuchMethodException ex)
	{
		ex.printStackTrace();
		return onResponse(500, "未知方法异常");
	}
	
	// 数组越界异常
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public Map<String, Object> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex)
	{
		ex.printStackTrace();
		return onResponse(500, "数组越界异常");
	}
	
	// 400错误
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, Object> requestNotReadble(HttpMessageNotReadableException ex)
	{
		return onResponse(400, ex.getMessage());
	}
	
	// 400错误
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(TypeMismatchException.class)
	public Map<String, Object> requestTypeMismatch(TypeMismatchException ex)
	{
		return onResponse(400, ex.getMessage());
	}
	
	// 400错误
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Map<String, Object> requestMissingServletRequest(MissingServletRequestParameterException ex)
	{
		return onResponse(400, ex.getMessage());
	}
	
	// 405错误
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Map<String, Object> request405()
	{
		return onResponse(405, "");
	}
	
	// 406错误
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public Map<String, Object> request406()
	{
		return onResponse(406, "");
	}
	
	// 500错误
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({HttpMediaTypeNotSupportedException.class, HttpMessageNotWritableException.class})
	public Map<String, Object> server500(Exception ex)
	{
		ex.printStackTrace();
		return onResponse(500, ex.getMessage());
	}
	
	/**
	 * 全局处理Exception
	 * 错误的情况返回500
	 *
	 * @param ex
	 * @param req
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Map<String, Object> otherExceptionHandler(Exception ex, WebRequest req)
	{
		ex.printStackTrace();
		return onResponse(500, ex.getMessage());
	}
	
	// bad请求
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public Map<String, Object> badRequestHandler(BadRequestException ex)
	{
		return onResponse(ex.getCode(), ex.getMessage());
	}

	// 文件储存失败
	@ExceptionHandler(FileSaveException.class)
	public Map<String, Object> fileSaveExceptionHandler(FileSaveException ex)
	{
		ex.printStackTrace();
		return onResponse(400, "上传文件失败");
	}

	// shiro角色权限异常
	@ExceptionHandler(UnauthorizedException.class)
	public Map<String, Object> unauthorizedExceptionHandler(UnauthorizedException ex) {
		return onResponse(400, "您没有足够的权限执行该操作!");
	}

	// 用户登录信息session异常
	@ExceptionHandler(CurrentUserException.class)
	public Map<String, Object> currentUserExceptionHandler(CurrentUserException ex) {
		return onResponse(400, "用户信息异常");
	}

	protected Map<String, Object> onResponse(int code, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		if (msg != null) map.put("msg", msg);
		return map;
	}

	protected Map<String, Object> onSuccessRep(String msg)
	{
		return onResponse(200, msg);
	}

	protected Map<String, Object> onBadResp(String msg)
	{
		return onResponse(400, msg);
	}

	protected Map<String, Object> onRespWithId(String msg, Long id) {
		Map<String, Object> map = onSuccessRep(msg);
		if (id != null) map.put("id", id);
		return map;
	}

	protected Map<String, Object> onDataResp(int code, String msg, Object data) {
		Map<String, Object> map = onResponse(code, msg);
		map.put("data", data);
		return map;
	}

	protected Map<String, Object> onDataResp(Object data) {
		return onDataResp(200, null, data);
	}
}
