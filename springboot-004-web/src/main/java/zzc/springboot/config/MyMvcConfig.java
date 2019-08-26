package zzc.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import zzc.springboot.component.LoginHandlerInterceptor;
import zzc.springboot.component.MyLocaleResolver;

/**
 * 使用WebMvcConfigurerAdapter可以扩展SpringMVC的功能
 */
//@EnableWebMvc
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {



	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//super.addViewControllers(registry);
		// 浏览器发送 /zzc 请求来到success页面
		registry.addViewController("/zzc").setViewName("success");
	}

	// 所有的WebMvcConfigurerAdapter组件都会一起起作用
	@Bean // 将组件注册到容器中
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/").setViewName("login");
				registry.addViewController("/index.html").setViewName("login");
				registry.addViewController("/main.html").setViewName("main");
			}

			// 注册拦截器
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				// 静态资源 *.css *.js
				// SpringBoot已经做好静态资源映射
				registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/main.html").excludePathPatterns("/", "index.html", "/user/login");
				super.addInterceptors(registry);
			}
		};
		return adapter;
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}
}
