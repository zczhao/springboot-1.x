# 整合SpringData JPA

简介：

Spring Data 项目的目的是为了简化构建基于Spring框架应用的数据访问技术，包括非关系数据库，Map-Reduce框架，云数据服务等等，另外也包含对关系数据库的访问支持。

## 1、SpringData特点

SpringData为我们提供使用统一的API来对数据访问层进行操作，这主要是Spring Data Commons项目来实现的，Spring Data Commons让我们在使用关系型可非关系型数据访问技术时都基于Spring提供的统一标准，标准包含了CRUD(创建、获取 、更新、删除)、查询、排序和分页的相碰操作。

## 2、统一的Repository接口

Repository<T,ID extends Serializable>：统一接口

RevisionRepository<T，ID extends  Serializable, N extends Number & Comparable<N>>：基于乐观锁机制

CrudRepository<T, ID extends Serializable>：基于CRUD操作

PagingAndSortingRepository<T，ID extends Serializable>：基于CRUD及分页

## 3、提供数据访问模板类xxxTemplate

如：MongoTemplate、RedisTemplate等

## 4、JPA与SpringData

### 1、JpaRepository基本功能

编写接口继承JpaRepository既有crud及分页等基本功能

### 2、定义符合规范的方法命名

在接口中只需要声明符合规范的方法，即拥有对应的功能

| Keyword | Sample                     | JPQL snippet                           |
| ------- | -------------------------- | -------------------------------------- |
| And     | findByLastnameAndFirstname | where x.lastname=?1 and x.firstname=?2 |
| Or      | findByLastnameOrFirstname  | where x.lastname=?1 or x.firstname=?2  |
| Between | findByStartDateBetween     | where x.startData between ?1 and ?2    |

### 3、@Query自定义查询，定制查询SQL

### 4、Specifications查询(Spring Data JPA支持JPA2.0的Criteria查询)

## 整合SpringData JPA

### 1、引入spring-boot-starter-data-jpa

```xml
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### 2、配置文件打印SQL语句

```yaml
spring:
  datasource:
    # 数据源基本配置
    username: root
    password: 123456
    url: jdbc:mysql://192.168.156.60:3306/jpa?useSSL=false
    driver-class-name: com.mysql.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update # 更新或创建数据表结构
    show-sql: true # 控制台显示SQL
```

### 3、创建Entity标准JPA注解

编写一个实体类(bean)和数据表进行映射，并且配置好映射关系

```java
package zzc.springboot.entity;

import javax.persistence.*;

/**
 * 使用JPA注解配置映射关系
 */
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column // 省略默认列名就是属性名
    private String email;
}
```

### 4、创建Repository接口继承JpaRepository

编写一个Dao接口来操作实体类对应的数据表(Repository)

```java
package zzc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zzc.springboot.entity.User;

/**
 * 继承JpaRepository来完成对数据库的操作
 */
public interface UserRepository extends JpaRepository<User,Integer> {

}

```

### 5、测试方法

```java
package zzc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zzc.springboot.entity.User;
import zzc.springboot.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userRepository.findOne(id);
    }

    @GetMapping("/user")
    public User insertUser(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }

}
```

