package zzc.springboot.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HelloCommandLineRunner implements CommandLineRunner {

	/**
	 * 普通Java工程/控制台项目，入口函数
	 */
    @Override
    public void run(String... strings) throws Exception {
    	// 在此方法内写实现代码，程序启动时自动调用
        System.out.println("CommandLineRunner.run...");
    }

}
