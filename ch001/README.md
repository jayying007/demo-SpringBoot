### 项目地址



### 流程

先执行demo.sql语句,再做下面操作  
首先注册一个账号，用Postman模拟发送请求

![image-20210321092822180](https://jane-blog.oss-cn-guangzhou.aliyuncs.com/img/image-20210321092822180.png)

可以看到PasswordEncoder起作用了，数据库内部没有保存实际的密码

![image-20210321092856876](https://jane-blog.oss-cn-guangzhou.aliyuncs.com/img/image-20210321092856876.png)

这个时候我们还没有登录，Session里面没有存放用户信息，所以如果访问其他接口，是会被禁止的。

![image-20210321091056393](https://jane-blog.oss-cn-guangzhou.aliyuncs.com/img/image-20210321091056393.png)

我们先登录

![image-20210321093035298](https://jane-blog.oss-cn-guangzhou.aliyuncs.com/img/image-20210321093035298.png)

这个时候再去调用permission/test3的接口

![image-20210321093056400](https://jane-blog.oss-cn-guangzhou.aliyuncs.com/img/image-20210321093056400.png)

这个用户目前没有角色，所以我们访问permission/test1和permission/test2都是没权限的

![image-20210321093725796](https://jane-blog.oss-cn-guangzhou.aliyuncs.com/img/image-20210321093725796.png)

我们在后台给用户绑定一个student的角色

```mysql
insert into user_role value (null, 2, 1);
```

然后这次发现可以请求permission/test1接口了（注意这里需要先刷新后台用户的Permission列表，我这里是重调了login方法，主要是其中的`userDetailsService.loadUserByUsername(username);`）

![image-20210321094053796](https://jane-blog.oss-cn-guangzhou.aliyuncs.com/img/image-20210321094053796.png)

当我们将用户的角色修改为teacher时，发现也可以访问了

![image-20210321094424154](https://jane-blog.oss-cn-guangzhou.aliyuncs.com/img/image-20210321094424154.png)