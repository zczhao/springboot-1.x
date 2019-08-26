package zzc.springboot.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Value("${person.last-name}")
	private String name;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello " + name;
	}
}
