package zzc.springboot.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyServerConfig {

	// 注册Servlet、Filter和Listener
	/*
	@Bean
	public ServletListenerRegistrationBean myListener(){
		ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
		return registrationBean;
	}


	@Bean
	public FilterRegistrationBean myFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new MyFilter());
		registrationBean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));
		return registrationBean;
	}

	@Bean
	public ServletRegistrationBean myServlet() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new Myservlet(), "/myServlet");
		return registrationBean;
	}
	*/

	/**
	 * 配置嵌入式的容器
	 *
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			// 定制嵌入式的Servlet容器相关规则
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.setPort(8080);
			}
		};
	}


}
