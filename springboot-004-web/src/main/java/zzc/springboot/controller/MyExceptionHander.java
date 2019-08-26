package zzc.springboot.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zzc.springboot.exception.UserNotExistException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHander {

	// 浏览器客户端返回的都是json
	/*
	@ExceptionHandler(UserNotExistException.class)
	public Map<String, Object> handleException(Exception e) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "user.notexist");
		map.put("message", e.getMessage());
		return map;
	}
	*/

	@ExceptionHandler(UserNotExistException.class)
	public String handleException(Exception e, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 传入自己的错误状态码 4xx 5xx
		//   Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
		request.setAttribute("javax.servlet.error.status_code", 500);
		map.put("code", "user.notexist");
		//map.put("message", e.getMessage());
		map.put("message", "用户出错啦");
		request.setAttribute("ext", map);
		// 转发的/error
		return "forward:/error";
	}
}
