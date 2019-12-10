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
<div style="position: fixed; display:block; z-index:9999">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">管理员<shiro:principal/></a>
            </div>
            <div>

                <ul class="nav navbar-nav">
                    <li id="nav0"><a href="/admin/announcement">公告</a></li>
                    <li id="nav1"><a href="/admin/addTableOne">添加档案</a></li>

                    <li class="dropdown" id="nav2">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            信息查询
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/admin/searchName">姓名查询</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/searchGrade">年级查询</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/searchDate">时间查询</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/searchOutstand">优质学员查询</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/searchTel">电话查询</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/searchLister">制表人查询</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/searchUpdater">录入人查询</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/searchMajor">专业查询</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/searchCheck">检验查询</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/searchSchool">学校查询</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/searchSame">重名查询</a></li>
                        </ul>
                    </li>

                    <li id="nav10"><a href="/admin/remindBirth">生日提醒</a></li>

                    <li id="nav11"><a href="/admin/remindPay">周工作提醒</a></li>


                    <li class="dropdown" id="nav4">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            缴费情况
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/admin/paidStudent?gradeid=-1&subjectid=-1&typeid=-1">已缴费学员</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/prePayStu?gradeid=-1&subjectid=-1&typeid=-1">预缴费学员</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/paidNotStudent?gradeid=-1">未缴费学员</a></li>
                        </ul>
                    </li>

                    <li class="dropdown" id="nav5">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            班级管理
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/admin/showTextDic">文本</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/showNoteDic">表格</a></li>
                        </ul>
                    </li>

                    <li class="dropdown" id="nav20">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            招生信息管理
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/admin/showSeTextDic">文本</a></li>
                            <%--editNoteText--%>
                            <li class="divider"></li>
                            <li><a href="/admin/showSeNoteDic">表格</a></li>
                        </ul>
                    </li>

                    <li class="dropdown" id="nav7">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            接收处理
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/admin/remindNewStu">新录入信息</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/remindReceive">更新信息</a></li>

                            <!--<li><a href="/admin/remindReceive">校长处理</a></li>-->
                        </ul>
                    </li>

                    <li class="dropdown" id="nav21">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            系统管理
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/admin/manageSubject">科目管理</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/manageClassType">课程管理</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/manageMajor">专业管理</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/manageSchol">学校管理</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/manageCampus">校区管理</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/manageAnnounce">公告管理</a></li>
                        </ul>
                    </li>

                    <li id="nav6"><a href="/admin/showUsers">用户信息</a></li>

                    <li id="nav12"><a href="/admin/uploadExcel">上传信息</a></li>
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

<div style="height: 60px"></div>


