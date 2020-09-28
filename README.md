- 项目预览地址: https://jiqunar.com
- Swagger接口文档地址: https://api.jiqunar.com/swagger-ui.html
- 前端Git地址: https://github.com/IMJacky/Light.Web
- 后端Git地址: https://github.com/IMJacky/light-admin

简介
----

1、前端：Ant Design Vue Pro

2、后端：Spring Boot，Redis，MySql，MyBatis Plus，Druid，Swagger，拦截器，过滤器，全局异常处理，日志，代码生成，多数据源

3、对象以及数据库表：用户，角色，资源（菜单/按钮），用户所属角色（可一对多），角色所拥有的资源以及基础信息包括部门，岗位

4、前期的前后端交互，针对后端所配置的用户权限动态来生成系统菜单，以及用户信息，登录

5、纯Docker容器部署到腾讯云（1C 2G）上，包括数据库，Nginx，缓存等等，使用Jenkins进行CI/CD；可惜的是前端没弄自动化，因为服务器资源不够用，动不动就TM挂掉了（垃圾Java）


总览
----

Fork 自 [Ant Design Pro](https://pro.ant.design/) 

登录页
![登录页](https://raw.githubusercontent.com/IMJacky/picturestore/master/login.PNG)

工作台
![工作台](https://raw.githubusercontent.com/IMJacky/picturestore/master/home.PNG)

菜单
![菜单](https://raw.githubusercontent.com/IMJacky/picturestore/master/menu.PNG)

用户
![用户](https://raw.githubusercontent.com/IMJacky/picturestore/master/user.PNG)

分配资源
![分配资源](https://raw.githubusercontent.com/IMJacky/picturestore/master/rolemenu.PNG)

运行
----

- 安装依赖
```
yarn install
```

- 开发模式运行
```
yarn run serve
```

- 编译项目
```
yarn run build
```

- Lints and fixes files
```
yarn run lint
```
