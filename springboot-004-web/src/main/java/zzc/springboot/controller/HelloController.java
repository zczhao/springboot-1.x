package zzc.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zzc.springboot.entity.User;
import zzc.springboot.exception.UserNotExistException;

import java.util.Map;

@Controller
public class HelloController {

	/*
	@RequestMapping("/","/index.html")
	public String index(){
		return "index";
	}*/

	@ResponseBody
	@RequestMapping("/hello")
	public String hello(@RequestParam("user") String user){
		if("aaa".equals(user)) {
			throw new UserNotExistException();
		}
		return "Hello World";
	}


	// 查询一些数据在页面展示
	@RequestMapping("/success")
	public String success(Map<String,Object> map){
		// classpath:/templates/success.html
		map.put("hello", "你好12312");
		return "success";
	}

	/**
	 * http://localhost:8080/crud/user/1.xml
	 * http://localhost:8080/crud/user/1.json
	 */
	@ResponseBody
	@RequestMapping(value = "/user/{id}", method= RequestMethod.GET)
	public User getUserPage(@PathVariable("id") String id, Model model) {
		User user = new User("zczhao","123456","zczhao@vip.com",22);
		model.addAttribute(user);
		return user;
	}
}
