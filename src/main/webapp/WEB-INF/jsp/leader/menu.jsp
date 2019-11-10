<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

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
                <a class="navbar-brand" href="#">负责人<shiro:principal/></a>
            </div>
            <div>

                <ul class="nav navbar-nav">
                    <li id="nav1"><a href="/leader/addTableOne">添加档案</a></li>
                    <li id="nav2"><a href="/leader/searchName">查询信息</a></li>


                    <li id="nav10"><a href="/leader/remindBirth">生日提醒</a></li>

                    <li id="nav11"><a href="/leader/remindPay">周工作提醒</a></li>

                    <li class="dropdown" id="nav4">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            缴费情况
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/leader/paidStudent?gradeid=-1&subjectid=-1&typeid=-1">已缴费学员</a></li>
                            <li class="divider"></li>
                            <li><a href="/leader/prePayStu?gradeid=-1&subjectid=-1&typeid=-1">预缴费学员</a></li>
                        </ul>
                    </li>

                    <li class="dropdown" id="nav5">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            班级管理
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/leader/showTextDic">文本</a></li>
                            <%--editNoteText--%>
                            <li class="divider"></li>
                            <li><a href="/leader/showNoteDic">表格</a></li>
                        </ul>
                    </li>

                    <li class="dropdown" id="nav20">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            招生信息管理
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/leader/showSeTextDic">文本</a></li>
                            <%--editNoteText--%>
                            <li class="divider"></li>
                            <li><a href="/leader/showSeNoteDic">表格</a></li>
                        </ul>
                    </li>

                    <li class="dropdown" id="nav7">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            接收处理
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/leader/remindNewStu">新录入信息</a></li>
                            <li class="divider"></li>
                            <li><a href="/leader/remindReceive">更新信息</a></li>
                        </ul>
                    </li>


                </ul>
            </div>
            <form action="#" class="navbar-form navbar-right" rol="search">
                <button class="btn btn-default dropdown-toggle" type="button" style="margin-right: 20px; ">
                    <%--登录用户名--%>
                    <a role="menuitem" tabindex="-1" href="/logout">
                        <span class="glyphicon glyphicon-off pull-right"></span>
                        注销
                    </a>
                </button>
            </form>
        </div>
    </nav>
</div>

<div style="height: 100px"></div>

