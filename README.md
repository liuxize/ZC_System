# ZC_System

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

## 3. 待完善
前端界面（固定大小）