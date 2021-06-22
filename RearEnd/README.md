<<<<<<< HEAD
# crm-web

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
=======
# 版本更新记录

# 项目结构

```text
api-template
├── api  -- web模块
    ├── java
        ├── config      --   配置类
            ├── MyInterceptor -- 自定义注解拦截器
            ├── CorsConfig -- 跨域问题
            ├── SwaggerConfig -- Api
            ├── WebConfigurer -- 配置拦截器
        ├── controller
            ├── FeedbackController -- 反馈接口
            ├── OrderController -- 订单接口
            ├── PlanController -- 计划接口
            ├── ProductController -- 产品接口
            ├── UserController -- 用户接口
        ├── dto -- 数据传输
        ├── bean -- 数据导入
        ├── mapper -- 数据库导入
            ├── FeedbackMapper -- 反馈数据库
            ├── OrderMapper -- 订单数据库
            ├── PlanMapper -- 计划数据库
            ├── ProductMapper -- 产品数据库
            ├── UserMapper -- 用户数据库
        ├── service -- 服务层
            ├── Impl -- 服务实现层
                ├── FeedbackService -- 反馈服务实现层
                ├── OrderService -- 订单服务实现层
                ├── PlanService -- 订单服务实现层
                ├── ProductServiceImpl -- 产品服务实现层
                ├── UserService -- 用户服务实现层            
            ├── FeedbackService -- 反馈服务层
            ├── OrderService -- 订单服务层
            ├── PlanService -- 订单服务层
            ├── ProductService -- 产品服务层
            ├── UserService -- 用户服务层
        ├── vo
            ├── ReturnVO -- 统一返回接口
    ├── resources
        ├── mapper 
            ├── FeedbackMapper -- 反馈数据库连接
            ├── OrderMapper -- 订单数据库连接
            ├── PlanMapper -- 计划数据库连接
            ├── ProductMapper -- 产品数据库连接
            ├── UserMapper -- 用户数据库连接
        ├── application.yml         -- 基础配置文件
```

# 参数修改

### 修改项目配置

- 打开api模块的application.yml文件，配置相关的数据库连接参数、端口号、日志的文件夹，
  如果要添加公共的配置，应该在application.yml文件添加，不要重复写。

### 修改拦截器

本模板带有自定义登录拦截，对所有的请求进行拦截

## 基本用法

- maven坐标

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.2.0</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.75</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>

    <!-- SpringBoot整合springfox-swagger3 -->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-boot-starter</artifactId>
        <version>3.0.0</version>
    </dependency>
    <dependency>
        <groupId>org.assertj</groupId>
        <artifactId>assertj-core</artifactId>
    </dependency>

</dependencies>
```

- pom.xml配置

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.4.2</version>
            <configuration>
                <skipTests>true</skipTests>
            </configuration>
        </plugin>
    </plugins>
</build>
```

## 数据库底层代码从抽取到Mapper层

- 自定义mapper及其实现类，详见com.mapper包下代码

- 使用
  在service层直接调用即可(这样service层就显得更加清晰了)
  - Service 
    代码在com.service包下
    
  - ServiceImpl
    代码在com.service.Impl包下

# 运行

直接跑

# 打包

mvn -package



- jpa相关操作自行百度
- sql在api/resources/sql中# practical_training_CRM
>>>>>>> 90b89b1 (提交)
