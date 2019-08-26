# SpringBoot与数据访问

## 1、jdbc

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

## 2、测试连接

Application.java

```java
package zzc.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

```

application.yml

```yml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://192.168.156.60:3306/jdbc?useSSL=false
    driver-class-name: com.mysql.jdbc.Driver
```

ApplicationTest.java

```java
package zzc.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testJDBCConnection() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}

```

效果：
​	默认是使用org.apache.tomcat.jdbc.pool.DataSource作为数据源；
​	数据源的相关配置都在DataSourceProperties里面；

数据库自动配置原理：

org.springframework.boot.autoconfigure.jdbc：

1、参考DataSourceConfiguration，根据配置创建数据源，默认是使用Tomcat连接池，可以使用spring.datasource.type指定自定义的数据源类型

2、SpringBoot默认可以支持

```
org.apache.tomcat.jdbc.pool.DataSource
com.zaxxer.hikari.HikariDataSource
org.apache.commons.dbcp.BasicDataSource
org.apache.commons.dbcp2.BasicDataSource
```

3、自定义数据源类型

```java
@Configuration
@ConditionalOnMissingBean({DataSource.class})
@ConditionalOnProperty(
    name = {"spring.datasource.type"}
)
static class Generic {
    Generic() {
    }

    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        // 使用DataSourceBuilder创建数据源，利用反射创建相应type的数据源，并且绑定相关属性
        return properties.initializeDataSourceBuilder().build();
    }
}
```

4、DataSourceInitializer：ApplicationListener

作用：

1. runSchemaScripts(); 运行建表sql语句
2. runDataScripts(); 运行插入数据的sql语句

默认只需要将文件文件命名为：`schema-*.sql`、`data-*.sql`

默认规则：schema.sql、schema-all.sql

启动时创建表：

​	使用默认文件，创建文件名为 schema-all.sql文件：

```sql
CREATE TABLE IF NOT EXISTS department (
    id int not null primary key AUTO_INCREMENT,
    dept_name varchar(255)
);
```

​	使用自定义文件，创建文件名为department.sql：

```sql
CREATE TABLE IF NOT EXISTS department (
    id int not null primary key AUTO_INCREMENT,
    dept_name varchar(255)
);
```

application.yml

```yaml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://192.168.156.60:3306/jdbc?useSSL=false
    driver-class-name: com.mysql.jdbc.Driver
    schema:
      - classpath:department.sql #指定自定义文件位置
```

5、操作数据库

自动配置了JdbcTemplate操作数据库

```java
package zzc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/query")
    public Map<String,Object> map() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from department");
        return list.get(0);
    }
}

```

