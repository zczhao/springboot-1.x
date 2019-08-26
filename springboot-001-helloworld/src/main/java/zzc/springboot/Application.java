package zzc.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication
 *      来标注一个主程序类，说明这是一个spring boot应用
 *      Spring Boot应用标注在某个类上说明这个类在SpringBoot的主配置类，SpringBoot就应该运行这个类的main方法来启动SpringBoot应用
 *      是一个组合注解类:
 *          @SpringBootConfiguration：Spring Boot的配置类
 *              标注在某个类上，表示这是一个Spring Boot的配置类
 *              @Configuration：配置类上标注这个注解
 *                  配置类 ---- 配置文件：配置类也是容器中的一个组件@Component
 *          @EnableAutoConfiguration：开启自动配置功能
 *              以前需要配置的东西，SpringBoot自动配置，@EnableAutoConfiguration：告诉springBoot开启自动配置功能，这样自动配置才能生效
 *              @AutoConfigurationPackage：自动配置包
 *                  @Import(AutoConfigurationPackages.Registrar.class)：
 *                      Spring的底层注解@Import，给容器中导入一个组件，导入的组件由AutoConfigurationPackages.Registrar.class
 *                  将主配置类(@SpringBootApplication标注的类)的所在包及下面所有子包里面的所有组件扫描到spring容器
 *              @Import(EnableAutoConfigurationImportSelector.class)：
 *                  给容器中导入组件
 *                  EnableAutoConfigurationImportSelector.class：导入哪些组件的选择器
 *                      将所有需要导入的组件以全类名的方式返回，这些就会被添加到容器中，
 *                      会给容器中导入非常多的自动配置类(XxxAutoConfiguration)，就是给容器中导入这个场景需要的所有组件并配置好这些组件;
 *                      有了自动配置类，就免去了手动编写配置注入功能组件等的工作
 *                          SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class,ClassLoader);
 *                              Spring Boot在启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作。以前我们需要自己配置的东西，自动配置类都帮我们做了
 *                              J2EE的整体整合解决方案和自动配置都在spring-boot-autoconfigure-1.5.17.RELEASE.jar;
 *
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // springboot应用启动
        SpringApplication.run(Application.class, args);
    }
}
