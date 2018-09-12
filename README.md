## Registration-login-interface2

### Version 1.2

#### 1. 添加功能以及修护 Bug

* 单个用户只能够登录一次，数据库记录用户状态
* 添加注解式事务配置
* 上一个版本的用户登出是无效的，看似登出了，但是访问被拦截页面却可以访问（实际未登出）

![](https://upload-images.jianshu.io/upload_images/3426615-b0de88b528c5df81.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 2. 想法

* 数据库新增一列 `status`，在登录之后设置为 1，代表已登录，登出时设置为 0，不能再次登录状态为 1 的用户
* 在 spring-mybatis.xml 中配置注解式事务，虽然在 service 层中没有使用（会抛出异常的操作，都不会在解决之前就操作数据库，所以感觉没有必要加上）：

#### 3. 实现

##### 映射器相关

首先在 user 表中新增一列 `status`：

```
alter table user add column (status varchar(5))
```

在 User 类中新增对应属性（忽略），在 UserMapper.xml 中新增：

```
    <update id="setStatus" parameterType="po.User">
        UPDATE user SET status = #{status}
        WHERE username = #{username}
    </update>
```

在这里还有一点，要修改一下添加用户的语句，新增的用户设置状态为未登录：

```
    <insert id="addUser" parameterType="po.User">
        INSERT INTO user(username,password,phone,email, status)
        VALUES (#{username}, #{password}, #{phone}, #{email}, "0");
    </insert>
```

然后在映射器接口中新增一行：

```
    void setStatus(User user);
```

##### service 层

为节省篇幅，UserService 和 UserServiceImpl 中的代码一起写：

根据用户名来查找用户，在 controller 中 logout() 方法中使用：

```
    //UserService
    User findUserByName(String username);

    //UserServiceImpl
    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }
```

修改登录和登出的方法，：

```
    @Override
    //根据用户输入名字去数据库查找有没有这个用户，如果没有，就会抛出异常
    //登入成功就设为 1
    public void login(User user) throws UserException{
        User db_user = userMapper.findUserByName(user.getUsername());
        exceptionService.loginException(user, db_user);
        if (db_user.getStatus().equals("1")){
            throw new UserException("该用户已在其他地点登录");
        } else {
            user.setStatus("1");
            userMapper.setStatus(user);
        }
    }

    @Override
    //登出就设为 0
    public String logout(User user) {
        user.setStatus("0");
        userMapper.setStatus(user);
        return user.getUsername();
    }
```

##### controller

更改登录信息检测顺序，原先是先检测用户名和密码，再检测验证码，但是在这个版本中，如果还是这么做就会有一个 Bug：

以用户 “12345” 为例，在 UserServiceImpl 中的 login() 方法中，我们只检测用户名和密码是否正确，如果没有错，就将用户的状态设置为 1，但是如果在这之后，验证码输入不正确，就会导致无法登入，但此时 “12345” 用户状态以及设为 1，却还没登入

所以需要修改两次检测的顺序：

```
    userService.verifyCode(request.getParameter("verifyCode"), verifyCode.getText());
    userService.login(user);
```

在上一个版本中有一个 Bug，上文也提及，看似登出，但是访问被拦截页面却可以访问（实际未登出），这是因为 session 的销毁操作其实没有进行，将 session 中的属性和一个输入参数 User 比较，但是这个 User 是空的，所以没有执行 session 的销毁操作，只是执行了后一步的跳转页面，上面写到的根据用户名查找用户就是用在这一步，将查找到的用户的状态设置为 0，然后销毁 session

```
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        userService.logout(userService.findUserByName(
                (String)request.getSession().getAttribute("username")));
        request.getSession().invalidate();
        return new ModelAndView("user/login").addObject("message", "已登出");
    }
```

##### 事务

刚好最近学到了事务，就顺手配置一下，但是在 service 中还没有用到

配置注解式事务，用 Spring 提供的事务管理器：

```
    <tx:annotation-driven/>
    <bean id="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
```

在 service 层中，如果要声明一个事务，只要在方法前加上一个注解 `@Transactional`

默认情况下，只有在抛出 RuntimeException 才可以导致事务回滚，如果要改变，抛出自定义异常时也能够使事务回滚，就要加一个语句：

`@Transactional(rollbackFor=UserException.class)`

##### 总结

虽然没有用到事务，但是接下来会另外做一个 Demo，其中会涉及到事务，所以这里就不刻意去修改某些方法了，接下来的一个版本可能就是添加 Shiro 来进行权限管理
