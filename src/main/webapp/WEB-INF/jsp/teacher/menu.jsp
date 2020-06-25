<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--
<style>


    .navbar-inverse .navbar-nav > li > a {
        color: white;
        font-size: large;
    }
</style>
-->
<div style="position: fixed; display:block; z-index:9998">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">教师<shiro:principal/></a>
            </div>
            <div>

                <ul class="nav navbar-nav">
                    <li id="nav0"><a href="/teacher/announcement">公告</a></li>
                    <li id="nav1"><a href="/teacher/addTableOne">添加档案</a></li>
                    <li id="nav2"><a href="/teacher/searchName">查询信息</a></li>

                    <li id="nav10"><a href="/teacher/remindBirth">生日提醒</a></li>

                    <li id="nav11"><a href="/teacher/remindPay">周工作提醒</a></li>
                    <li class="dropdown" id="nav4">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            学员信息分类
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/teacher/paidStudent?gradeid=-1&subjectid=-1&typeid=-1">已缴费学员</a></li>
                            <li class="divider"></li>
                            <li><a href="/teacher/prePayStu?gradeid=-1&subjectid=-1&typeid=-1">预缴费学员</a></li>
                        </ul>
                    </li>
                    <li class="dropdown" id="nav5">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            班级管理
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/teacher/showTextDic">文本</a></li>
                            <%--editNoteText--%>
                            <li class="divider"></li>
                            <li><a href="/teacher/showNoteDic">表格</a></li>
                        </ul>
                    </li>

                    <li class="dropdown" id="nav20">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            招生信息管理
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/teacher/showSeTextDic">文本</a></li>
                            <%--editNoteText--%>
                            <li class="divider"></li>
                            <li><a href="/teacher/showSeNoteDic">表格</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <form action="#" class="navbar-form navbar-right" rol="search">
                <button class="btn btn-default dropdown-toggle" type="button" style="margin-right: 20px; ">
                    <%--登录用户名--%>
                    <a role="menuitem" tabindex="-1" href="/logout">
<%--                        <span class="glyphicon glyphicon-off pull-right"></span>--%>
                        注销
                    </a>
                </button>
            </form>
        </div>
    </nav>
</div>

<div style="height: 100px"></div>
<!--
<div class="container">
<div class="row">
<div class="span6 secondmenu">
<ul class="nav nav-pills" id="nav1">
<li><a href="/teacher/addTableOne">添加档案<span class="badge pull-right"></span></a></li>
<li><a href="/admin/showUsers">登陆管理<span class="badge pull-right"></span></a></li>

<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">信息查询<b class="caret"></b></a>
<ul class="dropdown-menu">
<li><a href="/admin/searchName">搜素姓名</a></li>
<li class="divider"></li>
<li><a href="/admin/searchGrade">搜索年级</a></li>
<li class="divider"></li>
<li><a href="/admin/searchDate">搜索时间</a></li>
</ul>
</li>

<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">提醒功能<b class="caret"></b></a>
<ul class="dropdown-menu">
<li><a href="/admin/remindBirth">生日提醒</a></li>
<li class="divider"></li>
<li><a href="#">缴费提醒</a></li>
<li class="divider"></li>
<li><a href="#">接收提醒</a></li>
</ul>
</li>

<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">缴费情况<b class="caret"></b></a>
<ul class="dropdown-menu">
<li><a href="#">已缴费学员</a></li>
<li class="divider"></li>
<li><a href="#">未缴费学员</a></li>
</ul>
</li>

<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">记事本<b class="caret"></b></a>
<ul class="dropdown-menu">
<li><a href="/admin/editNoteText">文本</a></li>
<li class="divider"></li>
<li><a href="/admin/editNoteTable">表格</a></li>
</ul>
</li>

<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">账户管理<b class="caret"></b></a>
<ul class="dropdown-menu">
<li><a href="/admin/showUsers">用户信息</a></li>
<li class="divider"></li>
<li><a href="#">表格</a></li>
</ul>
</li>

<li><a href="/admin/showStudent">学生管理<span class="badge pull-right"></span></a></li>
<li><a href="/admin/showTeacher">教师管理<span class="badge pull-right"></span></a></li>
<li><a href="/admin/userPasswordRest">账号密码重置<sapn class="glyphicon glyphicon-repeat pull-right" /></a></li>
<li><a href="/admin/passwordRest">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
<li><a href="/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
<li class="disabled"><a href="##">Responsive</a></li>

</ul>
</div>
</div>
</div>

-->

