package zzc.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	// 记录器
	Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testLogging() {
		// 日志的级别（由低到高）：trace<debug<info<warn<error 可以调整输出的日志级别，日志就只会在这个级别及高级别的生效
		logger.trace("trace");
		logger.debug("debug");
		// SpringBoot默认日志级别为：info，没有指定级别的就用SpringBoot默认级别，root级别
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}

}
