## 麻将社区跟做

## 资料
[spring文档](https://spring.io/guides)  
[spring wen文档](https://spring.io/guides/gs/serving-web-content/)  
[spring data](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
[spring mvc](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-config-interceptors)

## 网站压力测试
**在docker容器中，此项目在1000总量，100并发情况下的压力测试：**


![avatar](https://github.com/codersdady/comunity/blob/master/img/docker_community_100.png)


**在docker容器中，此项目在1000总量，单线程下的压力测试：**

![avatar](https://github.com/codersdady/comunity/blob/master/img/docker_community_one.png)


**在本机win10中，此项目在1000总量，100并发情况下的压力测试：**

![avatar](https://github.com/codersdady/comunity/blob/master/img/win_community_100.png)


**在本机win10中，此项目在1000总量，单线程下的压力测试：**

![avatar](https://github.com/codersdady/comunity/blob/master/img/win_community_one.png)


## 工具  
flyway
git  
lombok(@Data省略set，get，toString等)  
[visual Paradigm](https://www.visual-paradigm.com)  
[mybatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)  
[mybatis generator](http://mybatis.org/generator/)
devtools(spring “热”部署工具)

## 脚本  
```sql
create table USER
(
    ID           INT auto_increment,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);
```
```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```

