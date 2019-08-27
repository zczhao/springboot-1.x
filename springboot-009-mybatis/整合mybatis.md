# 整合MyBatis

## 1、引入mybatis-starter

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
</dependency>
```

步骤：

1. 配置数据源相关配置(参考springboot-008-druid)

2. 给数据库建表

   src/main/resources/sql/department.sql

   ```sql
   CREATE TABLE IF NOT EXISTS department (
       id int not null primary key AUTO_INCREMENT,
       dept_name varchar(255)
   );
   ```

   src/main/resources/employee.sql

   ```sql
   CREATE TABLE IF NOT EXISTS employee (
       id int not null primary key AUTO_INCREMENT,
       last_name varchar(255),
       email varchar(255),
       gender int,
       dept_id int
   );
   ```

   application.yml

   ```yaml
   spring:
     datasource:    
       schema:
         - classpath:sql/department.sql
         - classpath:sql/employee.sql
   ```

3. 创建JavaBean

## 2、注解模式

```java
package zzc.springboot.mapper;

import org.apache.ibatis.annotations.*;
import zzc.springboot.bean.Department;

/**
 * @Mapper 指定这是一个操作数据库的mapper
 */
@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id=#{id}")
    Department getDepartmentById(Integer id);

    @Delete("DELETE FROM department WHERE id=#{id}")
    int deleteDepartmentById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id") // 插入并返回主键
    @Insert("INSERT INTO department(dept_name) VALUES(#{deptName})")
    int insertDepartment(Department department);

    @Update("UPDATE department dept_name=#{deptName} WHERE id=#{id}")
    int updateDepartment(Department department);

}

```

数据库里字段为：xxx_xxx 和 JavaBean里的字段对就不上

解决办法：开启驼峰命令，自定义MyBatis的配置规则，给容器中添加一个ConfigurationCustomizer

```java
package zzc.springboot.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }

}
```

如果不想在mapper接口上加@Mapper注解，解决办法：在启动类或MybatisConfig类上加入@MapperScan(value = "mapper接口所在的包名")

```java
package zzc.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 使用@MapperScan批量扫描所有的Mapper接口
@MapperScan(value = "zzc.springboot.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

## 3、配置文件模式

application.yml

```yaml
mybatis:
  config-location: classpath:mybatis/mybatis-config.xml # 指定全局配置文件的位置
  mapper-locations: classpath:mybatis/mapper/*.xml # 指定sql映射文件的位置
```

src/main/resources/mybatis/mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
</configuration>
```

src/main/resources/mybatis/mapper/EmployeeMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="zzc.springboot.mapper.EmployeeMapper">
    <!--
    Employee getEmployeeById(Integer id);

    int insertEmployeeById(Employee employee);
    -->
    <select id="getEmployeeById" resultType="zzc.springboot.bean.Employee">
        SELECT * FROM employee WHERE id = #{id}
    </select>

    <insert id="insertEmployeeById">
        INSERT INTO employee(last_name,email,gender,dept_id) VALUES(#{latName},#{email},#{gender},#{deptId});
    </insert>
</mapper>
```

EmployeeMapper.java

```java
package zzc.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import zzc.springboot.bean.Employee;

@Mapper
public interface EmployeeMapper {

    Employee getEmployeeById(Integer id);

    int insertEmployeeById(Employee employee);
}

```

Employee.java

```java
package zzc.springboot.bean;

public class Employee {

    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer deptId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}

```



## 4、测试