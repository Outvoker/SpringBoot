# SpringBoot

<!-- TOC -->

- [SpringBoot](#springboot)
    - [Spring SpringBoot](#spring-springboot)
        - [Spring能做什么](#spring%E8%83%BD%E5%81%9A%E4%BB%80%E4%B9%88)
        - [为什么用SpringBoot](#%E4%B8%BA%E4%BB%80%E4%B9%88%E7%94%A8springboot)
        - [时代背景](#%E6%97%B6%E4%BB%A3%E8%83%8C%E6%99%AF)
    - [SpringBoot2入门](#springboot2%E5%85%A5%E9%97%A8)
        - [Maven配置](#maven%E9%85%8D%E7%BD%AE)
        - [HelloWorld](#helloworld)
        - [自动配置](#%E8%87%AA%E5%8A%A8%E9%85%8D%E7%BD%AE)
        - [开发效率工具](#%E5%BC%80%E5%8F%91%E6%95%88%E7%8E%87%E5%B7%A5%E5%85%B7)
    - [配置文件](#%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6)
        - [示例](#%E7%A4%BA%E4%BE%8B)
        - [配置提示](#%E9%85%8D%E7%BD%AE%E6%8F%90%E7%A4%BA)
    - [Web开发](#web%E5%BC%80%E5%8F%91)
        - [SpringMVC自动配置概览](#springmvc%E8%87%AA%E5%8A%A8%E9%85%8D%E7%BD%AE%E6%A6%82%E8%A7%88)
        - [简单功能分析](#%E7%AE%80%E5%8D%95%E5%8A%9F%E8%83%BD%E5%88%86%E6%9E%90)
        - [请求参数处理](#%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0%E5%A4%84%E7%90%86)

<!-- /TOC -->

文档：<https://www.yuque.com/atguigu/springboot/>

源代码：<https://gitee.com/leifengyang/springboot2>

## Spring SpringBoot

### Spring能做什么

![20210102212916](http://ruiimg.hifool.cn/img20210102212916.png)

- 微服务开发
- 响应式编程
- 分布式云开发
- Web开发
- 无服务开发（函数式服务）
- 事件驱动
- 批处理

Spring生态：<https://spring.io/projects/spring-boot>

SpringBoot是一个高层框架，底层是Spring Framework

响应式编程：

![20210102213520](http://ruiimg.hifool.cn/img20210102213520.png)

### 为什么用SpringBoot

能快速创建出生产级别的Spring应用

#### SpringBoot优点

- 创建独立Spring应用
- 内嵌web服务器
- 自动starter依赖，简化构建配置
- 自动配置Spring以及第三方功能
- 提供生产级别的监控、健康检查以及外部化配置
- 无代码生成、无需编写XML

### 时代背景

#### 微服务

- 微服务是一种架构风格
- 一个应用拆分为一组小型服务
- 每个服务运行在自己的进程内，可以独立部署升级
- 服务之间使用轻量级HTTP交互
- 服务围绕业务功能拆分
- 可以由全自动部署机制独立部署
- 去中心化，服务自治。服务可以使用不同语言、不同存储技术

#### 分布式

分布式的困难

- 远程调用
- 服务发现
- 负载均衡
- 服务容错
- 配置管理
- 服务监控
- 链路追踪
- 日志管理
- 任务调度

解决办法：SpringBoot + SpringCloud

## SpringBoot2入门

### Maven配置

`maven\lib\maven3\conf`

```xml
    <mirrors>
        <mirror>
            <id>nexus-aliyun</id>
            <mirrorOf>central</mirrorOf>
            <name>Nexus aliyun</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public</url>
        </mirror>
    </mirrors>
 
    <profiles>
            <profile>
                <id>jdk-1.8</id>
                <activation>
                    <activeByDefault>true</activeByDefault>
                    <jdk>1.8</jdk>
                </activation>
                <properties>
                    <maven.compiler.source>1.8</maven.compiler.source>
                    <maven.compiler.target>1.8</maven.compiler.target>
                    <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
                </properties>
            </profile>
    </profiles>
```

### HelloWorld

#### POM

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.4.RELEASE</version>
    </parent>


    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```

#### 创建主程序

```java
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
```

#### 编写业务

```java
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String handle01(){
        return "Hello, Spring Boot 2!";
    }
}
```

#### 测试

直接运行Main方法

#### 简化配置

application.properties

#### 简化部署

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

### 自动配置

#### 默认的包结构

- 主程序所在包及其所有子包的组件都会默认被扫描进来
- 想要改变扫描路径：
  - `@SpringBootApplication(scanBasePackages="com.atguigu")`
  - `@ComponentScan` 指定扫描路径

#### 容器功能

##### Configuration

`@Configuration`

告诉SpringBoot这时一个配置类

1. 配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
2. 配置类本身也是组件
3. proxyBeanMethods：代理bean的方法
   1. 外部无论对配置类中的这个组件注册方法调用多少次，获取的都是之前注册容器中的单实例对象
   2. Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
   3. Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
   4. 组件依赖必须使用Full模式默认。其他默认是否Lite模式

### 开发效率工具

#### Lombok

```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
```

- @NoArgsConstructor: 无参构造方法
- @AllArgsConstructor: 全参构造方法
- @Data: getter(), setter()
- @ToString: toString()
- @EqualsAndHashCode: equals(), hashCode()
- @Slf4j: 日志

#### dev-tools

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>
```

热更新 ctrl + F9

#### Spring Initializr（项目初始化向导）

引入相应场景依赖，配置文件，创建好主程序类，

## 配置文件

application.yml

### 示例

```java
@Data
public class Person {
	
	private String userName;
	private Boolean boss;
	private Date birth;
	private Integer age;
	private Pet pet;
	private String[] interests;
	private List<String> animal;
	private Map<String, Object> score;
	private Set<Double> salarys;
	private Map<String, List<Pet>> allPets;
}

@Data
public class Pet {
	private String name;
	private Double weight;
}
```

```yml
# yaml表示以上对象
person:
  userName: zhangsan
  boss: false
  birth: 2019/12/12 20:12:33
  age: 18
  pet: 
    name: tomcat
    weight: 23.4
  interests: [篮球,游泳]
  animal: 
    - jerry
    - mario
  score:
    english: 
      first: 30
      second: 40
      third: 50
    math: [131,140,148]
    chinese: {first: 128,second: 136}
  salarys: [3999,4999.98,5999.99]
  allPets:
    sick:
      - {name: tom}
      - {name: jerry,weight: 47}
    health: [{name: mario,weight: 47}]
```

### 配置提示

在yml配置的时候提示我们自己定义的配置

build下表示在打包的时候不要打包这个提示器。

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>

 <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-configuration-processor</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

## Web开发

![20210103170410](http://ruiimg.hifool.cn/img20210103170410.png)

### 1. SpringMVC自动配置概览

Spring Boot provides auto-configuration for Spring MVC that **works well with most applications.(大多场景我们都无需自定义配置)**
The auto-configuration adds the following features on top of Spring’s defaults:
- Inclusion of ContentNegotiatingViewResolver and BeanNameViewResolver beans.
  - 内容协商视图解析器和BeanName视图解析器
- Support for serving static resources, including support for WebJars (covered later in this document)).
  - 静态资源（包括webjars）
- Automatic registration of Converter, GenericConverter, and Formatter beans.
  - 自动注册 Converter，GenericConverter，Formatter 
- Support for HttpMessageConverters (covered later in this document).
  - 支持 HttpMessageConverters （后来我们配合内容协商理解原理）
- Automatic registration of MessageCodesResolver (covered later in this document).
  - 自动注册 MessageCodesResolver （国际化用）
- Static index.html support.
  - 静态index.html 页支持
- Custom Favicon support (covered later in this document).
  - 自定义 Favicon  
- Automatic use of a ConfigurableWebBindingInitializer bean (covered later in this document).
  - 自动使用 ConfigurableWebBindingInitializer ，（DataBinder负责将请求数据绑定到JavaBean上）

### 2. 简单功能分析

#### 静态资源访问

只要静态资源放在类路径下：

- /static
- /public
- /resources
- /META-INF/resources

访问：当前项目根路径/ + 静态资源名

原理：静态映射/**
请求进来，先去找Controller看能不能处理。不能处理的所有请求又都交给静态资源处理。

为了拦截器方便（例如登录拦截器，只有登陆之后才能访问一些动态请求），可以放行指定前缀的静态资源访问

#### 静态资源访问前缀

默认无前缀。

改变默认的静态资源路径

```yml
spring:
  mvc:
    static-path-pattern: /res/**

  resources:
    static-locations: [classpath:/haha/]
```

当前项目 + static-path-pattern + 静态资源名 = 静态资源文件夹下找

#### 禁用静态资源访问

```yml
spring:
  resources:
    add-mappings: false   禁用所有静态资源规则
```

### 3. 请求参数处理

#### 请求映射

- @xxxMapping；
- Rest风格支持（使用HTTP请求方式动词来表示对资源的操作）
  - 以前：/getUser   获取用户     /deleteUser 删除用户    /editUser  修改用户       /saveUser 保存用户
  - 现在： /user    GET-获取用户    DELETE-删除用户     PUT-修改用户      POST-保存用户
    - PUT和POST默认不支持
  - 开启方法： 核心Filter；HiddenHttpMethodFilter
    - 用法： 表单method=post，隐藏域 _method=put

        ```html
            <form action="/user" method="post">
                <input name="_method" type="hidden" value="DELETE">
                <input value="REST-DELETE 提交" type="submit">
            </form>
            <form action="/user" method="post">
                <input name="_method" type="hidden" value="PUT">
                <input value="REST-PUT 提交" type="submit">
            </form>
        ```

    - SpringBoot中手动开启

        ```yml
        spring:
            mvc:
                hiddenmethod:
                filter:
                    enabled: true
        ```

Rest原理（表单提交要使用REST的时候）
- 表单提交会带上_method=PUT
- 请求过来被HiddenHttpMethodFilter拦截
  - 请求是否正常，并且是POST
    - 获取到_method的值。
    - 兼容以下请求；PUT.DELETE.PATCH
    - 原生request（post），包装模式requesWrapper重写了getMethod方法，返回的是传入的值。
    - 过滤器链放行的时候用wrapper。以后的方法调用getMethod是调用requesWrapper的。

#### 普通参数与基本注解

##### 注解

`@PathVariable、@RequestHeader、@ModelAttribute、@RequestParam、@MatrixVariable、@CookieValue、@RequestBody`

```java
@RestController
public class ParameterTestController {
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,   //获取路径参数
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv, //获取全部参数
                                      @RequestHeader("User-Agent") String userAgent,    //获取请求头中的信息
                                      @RequestHeader Map<String, String> header,    //获取请求头全部信息
                                      @RequestParam("age") Integer age, //获取请求参数(?age=)
                                      @RequestParam("interests") List<String> interests,    //获取请求参数（多个值）
                                      @RequestParam Map<String, String> params, //获取全部请求参数
                                      @CookieValue("io") String io, //获取cookie
                                      @CookieValue("io") Cookie cookie//获取cookie对象
                                      ){
        Map<String, Object> map = new HashMap<>();
//        map.put("id", id);
//        map.put("name", name);
//        map.put("pv", pv);
//        map.put("userAgent", userAgent);
//        map.put("header", header);
        map.put("age", age);
        map.put("interests", interests);
        map.put("params", params);
        map.put("ga", io);
        System.out.println(cookie);
        map.put("cookie",cookie);
        return map;
    }

    @PostMapping("/save")
    public Map PostMethod(@RequestBody String content){
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }

    //1. 语法 /cars/sell;low=34;brand=byd,audi,yd
    //2. SpringBoot默认禁用矩阵变量
    //3. 矩阵变量必须有url路径变量才能被解析
    @GetMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path){
        Map<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path",path);
        return map;
    }

    // /boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age",pathVar = "empId") Integer empAge){
        Map<String, Object> map = new HashMap<>();
        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        return map;
    }
}
```

/cars/{path}?xxx=xxx&aaa=ccc 查询字符串。@RequestParam;

1. 矩阵变量语法 /cars/path;low=34;brand=byd,audi,yd;
2. SpringBoot默认禁用了矩阵变量的功能
    - 手动开启：原理：对于路径处理使用urlPathHelper进行解析
        - removeSemicolonContent（移除分号内容）支持矩阵变量
3. 矩阵变量必须有url路径变量才能被解析

- 面试题：页面开发，cookie被禁用了，session里面的内容怎么使用；

    session.set(a,b)--->jsessionid--->cookie--->每次发请求携带。 如果cookie被禁，jsessionid找不到，session对象也找不到了
    url重写： /abc;jsessionid=xxx 把cookie的值使用矩阵变量的方式传递

/boss/1/2 找boss 1号 下面的员工2号

/boss/1;age=20/2;age=20 找1号 20岁 下面员工 2号 20岁

矩阵变量要和路径一起看

