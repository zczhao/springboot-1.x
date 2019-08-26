package zzc.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import zzc.springboot.bean.Person;

/**
 * SpringBoot单元测试
 * <p>
 * 可以在测试期间很方便的类似编码一样进行自动注入等功能
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private Person person;

	@Autowired
	ApplicationContext ioc;

	@Test
	public void testHelloService() {
		boolean b = ioc.containsBean("helloService");
		System.out.println(b);
	}

	@Test
	public void testConfiguration() {
		System.out.println(person);
	}

}
