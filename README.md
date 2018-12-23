# ZC_System (辅导班管理系统)

## 1. 运行环境搭建

(1) 开发环境：idea；  Java版本：jdk8； 服务器：tomcat 8； 数据库：mysql

(2) 数据库在sql文件夹，数据库配置在 \ZC_System\src\main\resources\mysql.properties

(3) tomcat部署方法：将编译好的war文件放到webapps文件夹，修改conf文件夹的server.xml
```
<Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">
			 <!--加入路径信息-->
		    <Context path="/ZC_System" docBase="ZC_System" debug="0" privileged="true"/>
```
## 2. 功能介绍

1. 用户登录：使用shiro框架登录，限制同一用户在线只能是一个；用户角色：管理员，校长，教师，负责人；

2. 查询功能：可以根据姓名，年级，时间，优质学员，电话，制表人，录入人，专业，检验，学校，重名进行查询；

3. 上传信息：导入excle表格到数据库，并通过进度条显示后台进度

(2)  
## 3. 待完善

1. 上传excle表格失败时返回信息

2. 前端界面（固定大小）