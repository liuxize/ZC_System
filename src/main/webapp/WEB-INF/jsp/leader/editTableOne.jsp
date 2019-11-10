    <%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/2/19
  Time: 下午12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>添加信息</title>
    <link rel="shortcut icon" type="image/x-icon" href="/images/logo-dt.png" media="screen">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <style>
        .btnc {
            float: right;
        }

        .btn1 {
            border: 1px solid #31B0D5;
            display: inline-block;
            padding: 1px 1px;
            margin-right: 5px;
        }

        .table th, .table td {

            vertical-align: middle !important;
        }

        .a ul li a {
            font-size: 18px;
        }

        .inp {
            width: 60%;
            height: 30px;
            border: none;
            padding-left: 10px;
        }

        .inp2 {
            width: 100%;
            height: 30px;
            border: none;
            padding-left: 10px;
        }

        .inp3 {
            width: 60%;
            height: 30px;
            border: none;
            padding-left: 10px;
        }

        .inp4 {
            width: 20%;
            height: 30px;
            border: none;
            padding-left: 10px;
        }

        .inp5 {
            width: 100%;
            border: none;
            padding-left: 10px;
            resize: none;
        }

        td {

        }
    </style>


</head>
<body>

<!-- 顶栏 -->

<div class="container" id="content">
    <div class="row">
        <jsp:include page="menucopy.jsp"></jsp:include>
        <div class="col-md-12 container">
            <h2 class="text-center">${stumessage.stuid}号学生档案</h2>


            <ul class="nav nav-tabs">
                <li class="active"><a href="javascript:void(0);" onclick="ToTableOne(${stumessage.stuid})">表一</a></li>
                <c:if test="${permission==true}">
                    <li><a href="javascript:void(0);" onclick="ToTableTwo(${stumessage.stuid})">表二</a></li>
                    <li><a href="javascript:void(0);" onclick="ToTableThree(${stumessage.stuid})">表三</a></li>
                </c:if>
            </ul>


            <div class="col-sm-10" style="font-size: 18px; padding: 10pt;">
                <span style="float:left;">${stumessage.stutype}</span>
                <span style="float:right;">
                    制表时间：&nbsp;
                    <fmt:formatDate value="${stumessage.recorddate}" dateStyle="medium"/>
                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                    制表人:&nbsp; ${stumessage.recordperson}
                     &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                     负责人签字:&nbsp;
                    <C:if test="${signmessage.leadersignid==0}"><font
                            color="red">${signmessage.leadersign}</font></C:if>
                    <C:if test="${signmessage.leadersignid==1}">${signmessage.leadersign}</C:if>
                     &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                    校长签字:&nbsp;
                    <C:if test="${signmessage.mastersignid==0}"><font
                            color="red">${signmessage.mastersign}</font></C:if>
                    <C:if test="${signmessage.mastersignid==1}"> ${signmessage.mastersign}</C:if>

                </span>
            </div>


            <table class="table table-bordered text-center table-striped ">

                <tr>
                    <td style="vertical-align: middle !important;text-align: center; width: 5%; !important;"
                        rowspan="4">姓名
                    </td>
                    <td style="width: 6%">
                        <c:if test="${signmessage.stunamesign==0}"><font color="red">学生</font></c:if>
                        <c:if test="${signmessage.stunamesign==1}">学生</c:if>
                    </td>
                    <td style="width:13%; border-right-color:transparent;">
                        ${stumessage.stuname}
                    </td>
                    <td style="width: 3%">
<%--                        <!--按键开始--学生历史姓名-->--%>
<%--                        <c:if test="${permission==true}">--%>
<%--                            <p>--%>
<%--                                <button type="button" class="btn btn-default btn-xs"--%>
<%--                                        style="background-color:transparent;border:0" data-toggle="modal"--%>
<%--                                        data-target="#StuNameModal">--%>
<%--                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>--%>
<%--                                </button>--%>
<%--                            </p>--%>
<%--                            <button type="button" class="btn btn-default btn-xs"--%>
<%--                                    style="background-color:transparent;border:0" data-toggle="modal"--%>
<%--                                    data-target="#addStuNameModal">--%>
<%--                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>--%>
<%--                            </button>--%>
<%--                            <!--按键结束-->--%>
<%--                        </c:if>--%>
<%--                        <!-- 模态框（Modal） 显示姓名 -->--%>
<%--                        <div class="modal fade" id="StuNameModal" tabindex="-1" role="dialog"--%>
<%--                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">--%>
<%--                            <div class="modal-dialog"> <!-- modal-lg 放大版-->--%>
<%--                                <div class="modal-content">--%>
<%--                                    <div class="modal-header">--%>
<%--                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">--%>
<%--                                            &times;--%>
<%--                                        </button>--%>
<%--                                        <h4 class="modal-title">姓名记录</h4>--%>
<%--                                    </div>--%>
<%--                                    <div class="modal-body">--%>
<%--                                            <textarea class="inp5" rows="10" name="improvetext"--%>
<%--                                                      readonly="readonly">${stumessage.stunamehis}</textarea>--%>
<%--                                    </div>--%>
<%--                                    <div class="modal-footer">--%>
<%--                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭--%>
<%--                                        </button>--%>
<%--                                    </div>--%>
<%--                                </div><!-- /.modal-content -->--%>
<%--                            </div><!-- /.modal -->--%>
<%--                        </div>--%>

<%--                        <div class="modal fade" id="addStuNameModal" tabindex="-1" role="dialog"--%>
<%--                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">--%>
<%--                            <div class="modal-dialog"> <!-- modal-lg 放大版-->--%>
<%--                                <div class="modal-content">--%>

<%--                                    <form role="form" action="/leader/addName" method="post">--%>
<%--                                        <div class="modal-header">--%>
<%--                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">--%>
<%--                                                &times;--%>
<%--                                            </button>--%>
<%--                                            <h4 class="modal-title">添加姓名信息</h4>--%>
<%--                                        </div>--%>
<%--                                        <div class="modal-body">--%>
<%--                                            <input type="text" class="form-control" name="stuName" required="required"--%>
<%--                                                   placeholder="请输入">--%>
<%--                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>--%>
<%--                                        </div>--%>
<%--                                        <div class="modal-footer">--%>
<%--                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭--%>
<%--                                            </button>--%>
<%--                                            <button class="btn btn-default" type="submit">保存</button>--%>
<%--                                        </div>--%>
<%--                                    </form>--%>
<%--                                </div><!-- /.modal-content -->--%>
<%--                            </div><!-- /.modal -->--%>
<%--                        </div>--%>
<%--                        <!-- 模态框（Modal）添加学校历史 结束-->--%>

                    </td>
                    <td style="width: 5%">
                        <c:if test="${signmessage.schoolsign==0}"><font color="red">所在学校</font></c:if>
                        <c:if test="${signmessage.schoolsign==1}">所在学校</c:if>
                    </td>
                    <td style=" width:20%; border-right-color:transparent;">
                        ${stumessage.school}
                    </td>

                    <td style="width: 3%;">
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#myModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addSchoolModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                            <!--按键结束-->
                        </c:if>

                        <!-- 模态框（Modal）显示学校历史 -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog modal-dialog-centered" role="document"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel">所在学校记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.schoolhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addSchoolModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addSchool" id="editstu" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" id="addSchoolModalLabel">添加学校信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <select class="form-control" name="school">
                                                <c:forEach items="${schoolList}" var="item">
                                                    <option value="${item.schoolname}">${item.schoolname}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">提交</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal）添加学校历史 结束-->

                    </td>
                    <td style="width: 5%;">年级</td>
                    <td colspan="2">
                        ${stumessage.gradename}
                    </td>
                    <td style="width: 5%;">
                        <c:if test="${signmessage.stusexsign==0}"><font color="red">性别</font></c:if>
                        <c:if test="${signmessage.stusexsign==1}">性别</c:if>
                    </td>
                    <td style="border-right-color:transparent;">
                        ${stumessage.stusex}
                    </td>
                    <td style="width: 3%">
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#StuSexModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addStuSexModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                            <!--按键结束-->
                        </c:if>
                    </td>


                    <!-- 模态框（Modal）添加专业 -->
                    <div class="modal fade" id="StuSexModal" tabindex="-1" role="dialog"
                         aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                        <div class="modal-dialog"> <!-- modal-lg 放大版-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" style="text-align: center">历史记录</h4>
                                </div>
                                <div class="modal-body">
                                            <textarea class="inp5" rows="10"
                                                      readonly="readonly">${stumessage.stusexhis}</textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                    </button>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal -->
                    </div>

                    <div class="modal fade" id="addStuSexModal" tabindex="-1" role="dialog"
                         aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                        <div class="modal-dialog"> <!-- modal-lg 放大版-->
                            <div class="modal-content">

                                <form role="form" action="/leader/addStuSex" method="post">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" style="text-align: center">添加性别信息</h4>
                                    </div>
                                    <div class="modal-body">
                                        <input type="text" class="form-control" name="addstusex" required="required"
                                               placeholder="请输入">

                                        <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                        <button class="btn btn-default" type="submit">提交</button>
                                    </div>
                                </form>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal -->
                    </div>
                    <!-- 模态框（Modal)结束-->
                </tr>

                <tr>
                    <td>
                        <c:if test="${signmessage.monamesign==0}"><font color="red">母亲</font></c:if>
                        <c:if test="${signmessage.monamesign==1}">母亲</c:if>
                    </td>
                    <td style=" border-right-color:transparent;">${stumessage.mothername}</td>
                    <td>
                        <!--按键开始--母亲历史姓名-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#MotherModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addMotherModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                            <!--按键结束-->
                        </c:if>

                        <!-- 模态框（Modal）添加母亲姓名 -->
                        <div class="modal fade" id="MotherModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" style="text-align: center">母亲姓名记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.mothernamehis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addMotherModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addMother" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" style="text-align: center">添加母亲姓名</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="motherName"
                                                   required="required"
                                                   placeholder="请输入">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">提交</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal)结束-->
                    </td>
                    <td>
                        <c:if test="${signmessage.mothercompanysign==0}"><font color="red">工作单位</font></c:if>
                        <c:if test="${signmessage.mothercompanysign==1}">工作单位</c:if>
                    </td>
                    <td style="border-right-color:transparent;">
                        ${stumessage.mothercompany}
                    </td>
                    <td>
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#MoCompanyModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addMoCompanyModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框（Modal）显示母亲公司 -->
                        <div class="modal fade" id="MoCompanyModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" id="MoCompanyModalLabel">母亲工作单位记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.mothercompanyhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addMoCompanyModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addMoCompany" id="editMocom" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" id="addMoComLabel">添加母亲工作单位信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" id="addMocompany"
                                                   name="motherCompany"
                                                   placeholder="请输入">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal）添加学校历史 结束-->
                    </td>

                    <td>
                        <c:if test="${signmessage.modegreesign==0}"><font color="red">学历</font></c:if>
                        <c:if test="${signmessage.modegreesign==1}">学历</c:if>
                    </td>
                    <td  style="width: 10%; border-right-color:transparent;">
                        ${stumessage.motherdegree}
                    </td>
                    <td style="width: 3%">
                        <!--按键开始--专业历史记录-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#MoDegreeModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addMoDegreeModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                            <!--按键结束-->
                        </c:if>
                    </td>


                    <!-- 模态框（Modal）添加专业 -->
                    <div class="modal fade" id="MoDegreeModal" tabindex="-1" role="dialog"
                         aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                        <div class="modal-dialog"> <!-- modal-lg 放大版-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" style="text-align: center">母亲学历记录</h4>
                                </div>
                                <div class="modal-body">
                                            <textarea class="inp5" rows="10"
                                                      readonly="readonly">${stumessage.motherdegreehis}</textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                    </button>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal -->
                    </div>

                    <div class="modal fade" id="addMoDegreeModal" tabindex="-1" role="dialog"
                         aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                        <div class="modal-dialog"> <!-- modal-lg 放大版-->
                            <div class="modal-content">

                                <form role="form" action="/leader/addMotherGegree" method="post">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" style="text-align: center">添加母亲学历</h4>
                                    </div>
                                    <div class="modal-body">
                                        <input type="text" class="form-control" name="motherDegree" required="required"
                                               placeholder="请输入">
                                        <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                        <button class="btn btn-default" type="submit">提交</button>
                                    </div>
                                </form>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal -->
                    </div>
                    <!-- 模态框（Modal)结束-->

                    <td>
                        <c:if test="${signmessage.motherjobsign==0}"><font color="red">职务</font></c:if>
                        <c:if test="${signmessage.motherjobsign==1}">职务</c:if>
                    </td>
                    <td style=" border-right-color:transparent;">
                        ${stumessage.motherjob}
                    </td>
                    <td>
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#MoJobModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addMoJobModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->


                        <!-- 模态框（Modal）显示母亲公司 -->
                        <div class="modal fade" id="MoJobModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" id="MoJobModalLabel">母亲职务记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.motherjobhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addMoJobModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addMoJob" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加母亲职务信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="motherJob"
                                                   placeholder="请输入">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal）添加学校历史 结束-->


                    </td>
                </tr>
                <tr>
                    <td>
                        <c:if test="${signmessage.fanamesign==0}"><font color="red">父亲</font></c:if>
                        <c:if test="${signmessage.fanamesign==1}">父亲</c:if>
                    </td>
                    <td style="border-right-color:transparent;">${stumessage.fathername}</td>
                    <td>
                        <!--按键开始--父亲历史姓名-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#FatherModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addFatherModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框（Modal）添加父亲姓名 -->
                        <div class="modal fade" id="FatherModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" style="text-align: center">父亲姓名记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.fathernamehis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addFatherModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addFather" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" style="text-align: center">添加母亲姓名</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="fatherName"
                                                   required="required"
                                                   placeholder="请输入">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">提交</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal)结束-->
                    </td>
                    <td>
                        <c:if test="${signmessage.fathercompanysign==0}"><font color="red">工作单位</font></c:if>
                        <c:if test="${signmessage.fathercompanysign==1}">工作单位</c:if>
                    </td>
                    <td style="border-right-color:transparent;">
                        ${stumessage.fathercompany}
                    </td>
                    <td>
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#FaCompanyModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addFaComModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框（Modal）显示父亲公司 -->
                        <div class="modal fade" id="FaCompanyModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title">父亲工作单位记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.fathercompanyhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addFaComModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addFaCompany" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加父亲工作单位信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="fathercompany"
                                                   placeholder="请输入">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal）添加学校历史 结束-->

                    </td>

                    <td>
                        <c:if test="${signmessage.fadegreesign==0}"><font color="red">学历</font></c:if>
                        <c:if test="${signmessage.fadegreesign==1}">学历</c:if>
                    </td>
                    <td style="border-right-color:transparent;">
                        ${stumessage.fatherdegree}
                    </td>
                    <td>
                        <!--按键开始--专业历史记录-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#FaDegreeModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addFaDegreeModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                            <!--按键结束-->
                        </c:if>
                    </td>


                    <!-- 模态框（Modal）添加父亲学历 -->
                    <div class="modal fade" id="FaDegreeModal" tabindex="-1" role="dialog"
                         aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                        <div class="modal-dialog"> <!-- modal-lg 放大版-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" style="text-align: center">父亲学历记录</h4>
                                </div>
                                <div class="modal-body">
                                            <textarea class="inp5" rows="10"
                                                      readonly="readonly">${stumessage.fatherdegreehis}</textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                    </button>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal -->
                    </div>

                    <div class="modal fade" id="addFaDegreeModal" tabindex="-1" role="dialog"
                         aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                        <div class="modal-dialog"> <!-- modal-lg 放大版-->
                            <div class="modal-content">

                                <form role="form" action="/leader/addFatherGegree" method="post">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" style="text-align: center">添加父亲学历</h4>
                                    </div>
                                    <div class="modal-body">
                                        <input type="text" class="form-control" name="fatherDegree" required="required"
                                               placeholder="请输入">
                                        <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                        <button class="btn btn-default" type="submit">提交</button>
                                    </div>
                                </form>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal -->
                    </div>
                    <!-- 模态框（Modal)结束-->

                    <td>
                        <c:if test="${signmessage.fatherjobsign==0}"><font color="red">职务</font></c:if>
                        <c:if test="${signmessage.fatherjobsign==1}">职务</c:if>
                    </td>
                    <td style="border-right-color:transparent;">

                        ${stumessage.fatherjob}
                    </td>
                    <td>
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#FaJobModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addFaJobModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框（Modal）显示母亲公司 -->
                        <div class="modal fade" id="FaJobModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title">父亲职务记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.fatherjobhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addFaJobModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addFaJob" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加父亲职务信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="fatherJob" required="required"
                                                   placeholder="请输入">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal）添加学校历史 结束-->
                    </td>
                </tr>

                <tr>
                    <%--班主任姓名信息--%>
                    <td>
                        <c:if test="${signmessage.masternamesign==0}"><font color="red">班主任</font></c:if>
                        <c:if test="${signmessage.masternamesign==1}">班主任</c:if>
                    </td>
                    <td style="border-right-color:transparent;">${stumessage.mastername}</td>
                    <td>
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#teacherModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addteacherModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框（Modal）显示母亲公司 -->
                        <div class="modal fade" id="teacherModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title">教师记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.masternamehis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addteacherModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addMaster" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加教师信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="addMaster"
                                                   placeholder="请输入" required="required">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                    </td>

                    <%--班主任家庭住址--%>
                    <td>
                        <c:if test="${signmessage.masteraddresssign==0}"><font color="red">家庭住址</font></c:if>
                        <c:if test="${signmessage.masteraddresssign==1}">家庭住址</c:if>
                    </td>
                    <td style="border-right-color:transparent;">${stumessage.masteraddress}</td>
                    <td>
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#masterAddressModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addMasterAddressModel">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <div class="modal fade" id="masterAddressModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title">家庭住址记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10"
                                                      readonly="readonly">${stumessage.masteraddresshis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addMasterAddressModel" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addMasterAddress" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加家庭住址信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="addmasteraddress"
                                                   placeholder="请输入" required="required">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                    </td>

                    <%--班主任学历信息--%>
                    <td>
                        <c:if test="${signmessage.masterdegreesign==0}"><font color="red">学历</font></c:if>
                        <c:if test="${signmessage.masterdegreesign==1}">学历</c:if>
                    </td>
                    <td style="border-right-color:transparent;">${stumessage.masterdegree}</td>
                    <td>
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#masterDegreeModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addMasterDegreeModel">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <div class="modal fade" id="masterDegreeModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title">班主任学历记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10"
                                                      readonly="readonly">${stumessage.masterdegreehis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <div class="modal fade" id="addMasterDegreeModel" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addMasterDegree" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加班主任学历信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="addmasterdegree"
                                                   placeholder="请输入" required="required">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                    </td>
                    <%--班主任性别信息--%>
                    <td>
                        <c:if test="${signmessage.mastersexsign==0}"><font color="red">性别</font></c:if>
                        <c:if test="${signmessage.mastersexsign==1}">性别</c:if>
                    </td>
                    <td style="border-right-color:transparent;">${stumessage.mastersex}</td>
                    <td>
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#masterSexModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addMasterSexModel">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <div class="modal fade" id="masterSexModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title">历史记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10"
                                                      readonly="readonly">${stumessage.mastersexhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <div class="modal fade" id="addMasterSexModel" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addMasterSex" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加班主任性别信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="addmastersex"
                                                   placeholder="请输入" required="required">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                    </td>

                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${signmessage.addresssign==0}"><font color="red">现住地址</font></c:if>
                        <c:if test="${signmessage.addresssign==1}">现住地址</c:if>
                    </td>
                    <td class="text-left" colspan="10" style="border-right-color:transparent;">
                        ${stumessage.address}
                    </td>
                    <td>
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#addressModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addAddressModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框（Modal）显示住址 -->
                        <div class="modal fade" id="addressModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title">历史信息</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.addresshis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addAddressModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addAddress" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加现住地址信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="addAddress"
                                                   placeholder="请输入">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal）添加住址历史 结束-->

                    </td>

                </tr>
                <%--生日--%>
                <tr>
                    <td>
                        生日
                    </td>


                    <td class="text-left" colspan="2" style="border-right-color:transparent;">
                        <c:if test="${signmessage.birthsign==0}"><font color="red">学生：</font></c:if>
                        <c:if test="${signmessage.birthsign==1}">学生：</c:if>
                        <fmt:formatDate value="${stumessage.stubirth}" dateStyle="medium"/>
                    </td>

                    <td>
                        <!--按键开始--生日历史记录-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#BirthModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addBirthModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框（Modal）添加生日 -->
                        <div class="modal fade" id="BirthModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" style="text-align: center">生日记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.stubirthhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addBirthModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addBirth" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" style="text-align: center">添加生日</h4>
                                        </div>
                                        <div class="modal-body">
                                            <!--input type="text" class="form-control" name="fatherName" required="required"
                                                   placeholder="请输入"-->
                                            <input class="inp2" type="date"  max="9999-12-31" name="birth" required="required"/>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">提交</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal)结束-->
                    </td>


                    <td class="text-left" colspan="2" style="border-right-color:transparent;">
                        <c:if test="${signmessage.motherbirthsign==0}"><font color="red">母亲：</font></c:if>
                        <c:if test="${signmessage.motherbirthsign==1}">母亲：</c:if>
                        <fmt:formatDate value="${stumessage.motherbirth}" dateStyle="medium"/>
                    </td>

                    <td>
                        <!--按键开始--生日历史记录-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#MoBirthModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addMoBirthModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框（Modal）添加生日 -->
                        <div class="modal fade" id="MoBirthModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" style="text-align: center">母亲生日记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.motherbirthhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addMoBirthModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addMoBirth" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" style="text-align: center">添加母亲生日</h4>
                                        </div>
                                        <div class="modal-body">
                                            <!--input type="text" class="form-control" name="fatherName" required="required"
                                                   placeholder="请输入"-->
                                            <input class="inp2" type="date"  max="9999-12-31" name="motherbirth" required="required"/>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">提交</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal)结束-->
                    </td>
                    <td class="text-left" colspan="3" style="border-right-color:transparent;">
                        <c:if test="${signmessage.fatherbirthsign==0}"><font color="red">父亲： </font></c:if>
                        <c:if test="${signmessage.fatherbirthsign==1}">父亲： </c:if>
                        <fmt:formatDate value="${stumessage.fatherbirth}" dateStyle="medium"/>
                    </td>

                    <td>
                        <!--按键开始--生日历史记录-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#FaBirthModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addFaBirthModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框（Modal）添加生日 -->
                        <div class="modal fade" id="FaBirthModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" style="text-align: center">父亲生日记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.fatherbirthhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addFaBirthModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addFaBirth" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" style="text-align: center">添加父亲生日</h4>
                                        </div>
                                        <div class="modal-body">
                                            <!--input type="text" class="form-control" name="fatherName" required="required"
                                                   placeholder="请输入"-->
                                            <input class="inp2" type="date"  max="9999-12-31" name="fatherbirth" required="required"/>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">提交</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal)结束-->
                    </td>

                    <%--添加班主任生日信息--%>
                    <td class="text-left" style="border-right-color:transparent;">
                        <c:if test="${signmessage.masterbirthsign==0}"><font color="red">班主任： </font></c:if>
                        <c:if test="${signmessage.masterbirthsign==1}">班主任： </c:if>
                        <fmt:formatDate value="${stumessage.masterbirth}" dateStyle="medium"/>
                    </td>

                    <td>
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#MasterBirthModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addMasterBirthModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框（Modal）添加生日 -->
                        <div class="modal fade" id="MasterBirthModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" style="text-align: center">班主任生日记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10"
                                                      readonly="readonly">${stumessage.masterbirthhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addMasterBirthModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addMasterBirth" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" style="text-align: center">添加班主任生日</h4>
                                        </div>
                                        <div class="modal-body">
                                            <!--input type="text" class="form-control" name="fatherName" required="required"
                                                   placeholder="请输入"-->
                                            <input class="inp2" type="date"  max="9999-12-31" name="addmasterbirth" required="required"/>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">提交</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal)结束-->
                    </td>
                </tr>
                <%--电话--%>
                <tr>
                    <td>电话</td>
                    <td class="text-left" colspan="2" style="border-right-color:transparent;">
                        <c:if test="${signmessage.stutelsign==0}"><font color="red">学生： </font></c:if>
                        <c:if test="${signmessage.stutelsign==1}">学生： </c:if>
                        ${stumessage.stutel}
                    </td>

                    <td>
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#StuTelModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addStuTelModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!-- 模态框（Modal）显示学生电话 -->
                        <div class="modal fade" id="StuTelModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" style="text-align: center">学生电话记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.stutelhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addStuTelModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addStuTel" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" style="text-align: center">添加学生电话</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="stuTel" required="required"
                                                   placeholder="请输入">

                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">提交</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal)结束-->

                    </td>

                    <td class="text-left" colspan="2" style="border-right-color:transparent;">
                        <c:if test="${signmessage.mothertelsign==0}"><font color="red">母亲： </font></c:if>
                        <c:if test="${signmessage.mothertelsign==1}">母亲： </c:if>
                        ${stumessage.mothertel}
                    </td>

                    <td>
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#MotherTelModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addMotherTelModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!-- 模态框（Modal）显示母亲电话 -->
                        <div class="modal fade" id="MotherTelModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" style="text-align: center">母亲电话记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.mothertelhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addMotherTelModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addMotherTel" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" style="text-align: center">添加母亲电话</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="motherTel" required="required"
                                                   placeholder="请输入">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">提交</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal)结束-->

                    </td>

                    <td class="text-left" colspan="3" style="border-right-color:transparent;">
                        <c:if test="${signmessage.fathertelsign==0}"><font color="red">父亲： </font></c:if>
                        <c:if test="${signmessage.fathertelsign==1}">父亲： </c:if>
                        ${stumessage.fathertel}
                    </td>

                    <td>
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#FatherTelModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addFatherTelModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!-- 模态框（Modal）显示父亲电话 -->
                        <div class="modal fade" id="FatherTelModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" style="text-align: center">父亲电话记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.fathertelhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addFatherTelModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addFatherTel" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" style="text-align: center">添加父亲电话</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="fatherTel" required="required"
                                                   placeholder="请输入">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">提交</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal)结束-->

                    </td>

                    <%--班主任电话信息--%>
                    <td class="text-left" style="border-right-color:transparent;">
                        <c:if test="${signmessage.mastertelsign==0}"><font color="red">班主任： </font></c:if>
                        <c:if test="${signmessage.mastertelsign==1}">班主任： </c:if>
                        ${stumessage.mastertel}
                    </td>

                    <td>
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#MasterTelModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addMasterTelModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框（Modal）显示母亲公司 -->
                        <div class="modal fade" id="MasterTelModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title">班主任电话记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10" name="improvetext"
                                                      readonly="readonly">${stumessage.mastertelhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addMasterTelModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addMasterTel" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加班主任
                                                电话信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" class="form-control" name="addmastertel"
                                                   placeholder="请输入" required="required">
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal）添加学校历史 结束-->

                    </td>

                </tr>
                <%--专业，检验日期--%>
                <tr>
                    <td>
                        <c:if test="${signmessage.majorsign==0}"><font color="red">专业</font></c:if>
                        <c:if test="${signmessage.majorsign==1}">专业</c:if>
                    </td>
                    <td colspan="2" style="border-right-color:transparent;">
                        ${stumessage.major}
                    </td>
                    <td>
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#majorModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addMajorModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框（Modal）显示母亲公司 -->
                        <div class="modal fade" id="majorModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title">专业记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10"
                                                      readonly="readonly">${stumessage.majorhis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addMajorModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addMajor" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加专业信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <select class="form-control" name="major">
                                                <c:forEach items="${majorList}" var="item">
                                                    <option value="${item.majorname}">${item.majorname}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框（Modal）添加学校历史 结束-->

                    </td>

                    <%--检验日期添加--%>
                    <td>
                        <c:if test="${signmessage.checkdatesign==0}"><font color="red">检验日期</font></c:if>
                        <c:if test="${signmessage.checkdatesign==1}">检验日期</c:if>
                    </td>
                    <td class="text-left"
                        style="border-right-color:transparent;">
                        <fmt:formatDate value="${stumessage.checkdate}" dateStyle="medium"/>

                    </td>
                    <td>
                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <p>
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;border:0" data-toggle="modal"
                                        data-target="#CheckDateModal">
                                    <span class="glyphicon glyphicon-list-alt" style="color: #2e6da4"></span>
                                </button>
                            </p>
                            <button type="button" class="btn btn-default btn-xs"
                                    style="background-color:transparent;border:0" data-toggle="modal"
                                    data-target="#addCheckDateModal">
                                <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                            </button>
                        </c:if>
                        <!--按键结束-->

                        <div class="modal fade" id="CheckDateModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title">检验日期记录</h4>
                                    </div>
                                    <div class="modal-body">
                                            <textarea class="inp5" rows="10"
                                                      readonly="readonly">${stumessage.checkdatehis}</textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                        <div class="modal fade" id="addCheckDateModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addCheckDate" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加检验日期信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input class="inp2" type="date"  max="9999-12-31" name="addcheckdate" required="required"/>

                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                    </td>

                    <td colspan="2"  style="border-right-color:transparent;">
                        照片和视频存储等:
                    </td>
                    <td colspan="2" align="center" style="border-right-color:transparent;">
                        <c:if test="${unsignImageNum==0&&permission==true}">
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addFigure"
                                    style="background-color:transparent;border:0">添加</button>
                        </c:if>
                        <c:if test="${unsignImageNum>0&&permission==true}">
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addFigure"
                                    style="background-color:transparent;border:0;color: red;">添加&nbsp;(${unsignImageNum})</button>
                        </c:if>

                        <div class="modal fade" id="addFigure" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <form action="/leader/uploadImage" method="post" enctype="multipart/form-data">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加图片</h4>
                                        </div>
                                        <div class="modal-body">

                                            <input type="text" class="form-control" placeholder="图片名称" required="required"
                                                   name="imageTitle">
                                            <div style="height: 10px"></div>
                                            <input type="file" class="form-control" id="updateFile" name="image" required="required"
                                                   accept="image/*"/>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                    </td>
                    <td colspan="2" align="center">
                        <c:if test="${permission==true}">
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#figureList"
                                    style="background-color:transparent;border:0">查阅&nbsp;(${imagesNum})
                            </button>
                        </c:if>
                        <div class="modal fade" id="figureList" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title">查阅图片</h4>
                                    </div>
                                    <div class="modal-body">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th style="width: 10%">编号</th>
                                                <th style="width: 80%">图片名称</th>
                                                <th style="width: 10%;">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${imagesList}" var="item" varStatus="status">
                                            <tr>
                                                <td>${status.index + 1}</td>
                                                <td>
                                                    <c:if test="${item.imagesign==0}"><font color="red">${item.title}</font></c:if>
                                                    <c:if test="${item.imagesign==1}">${item.title}</c:if>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-primary btn-xs"
                                                            onClick=" window.open('/uploadImages/${item.path}')">查看
                                                    </button>
                                                </td>
                                            </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                    </td>
                </tr>
                <tr>
                    <td style="font-size: 20px; " colspan="13">三教两管一提</td>
                </tr>
                <tr>
                    <td colspan="3">
                        <C:if test="${signmessage.schooltextsign==0}"><font color="red">基础情况</font></C:if>
                        <C:if test="${signmessage.schooltextsign==1}">基础情况</C:if>
                    </td>
                    <td class="text-left" colspan="10">

                        <div class="form-group">
                            <!--label for="name"></label>-->
                            <textarea class="inp5" rows="20" name="schooltext"
                                      readonly="readonly">${stumessage.schooltexthis}${br}<C:if test="${signmessage.schooltextsign==0}">更新的内容:${br}${stumessage.schooltext}</C:if>
                            </textarea>

                        </div>

                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <div class="btnc">
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;" data-toggle="modal"
                                        data-target="#addSchoolText" style="color: #2e6da4">添加
                                    <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                                </button>
                            </div>
                        </c:if>
                        <!--按键结束-->
                        <!-- 模态框-->
                        <div class="modal fade" id="addSchoolText" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addSchoolText" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加在校情况信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <textarea class="form-control" rows="6" name="addschooltext"
                                                      required="required"
                                                      placeholder="请输入" style="resize: none;"></textarea>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框-->
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <C:if test="${signmessage.familytextsign==0}"><font color="red">家庭状况</font></C:if>
                        <C:if test="${signmessage.familytextsign==1}">家庭状况</C:if>
                    </td>
                    <td class="text-left" colspan="10">
                        <div class="form-group">
                            <!--label for="name"></label>-->
                            <textarea class="inp5" rows="20" name="familytext"
                                      readonly="readonly">${stumessage.familytexthis}${br}<C:if test="${signmessage.familytextsign==0}">更新的内容:${br}${stumessage.familytext}</C:if>
                            </textarea>
                        </div>

                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <div class="btnc">
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;" data-toggle="modal"
                                        data-target="#addFamilyText" style="color: #2e6da4">添加
                                    <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                                </button>
                            </div>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框-->
                        <div class="modal fade" id="addFamilyText" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addFamilyText" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加家庭状况信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <textarea class="form-control" rows="6" name="addfamilytext"
                                                      required="required"
                                                      placeholder="请输入" style="resize: none;"></textarea>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框-->

                    </td>
                </tr>

                <tr>
                    <td colspan="3">
                        <C:if test="${signmessage.studytextsign==0}"><font color="red">众成学习情况</font></C:if>
                        <C:if test="${signmessage.studytextsign==1}">众成学习情况</C:if>
                    </td>
                    <td class="text-left" colspan="10">
                        <div class="form-group">
                            <!--label for="name"></label>-->
                            <textarea class="inp5" rows="20" name="studytext"
                                      readonly="readonly">${stumessage.studytexthis}${br}<C:if test="${signmessage.studytextsign==0}">更新的内容:${br}${stumessage.studytext}</C:if>
                            </textarea>
                        </div>

                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <div class="btnc">
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;" data-toggle="modal"
                                        data-target="#addStudyText" style="color: #2e6da4">添加
                                    <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                                </button>
                            </div>
                        </c:if>
                        <!--按键结束-->

                        <!-- 模态框-->
                        <div class="modal fade" id="addStudyText" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addStudyText" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加学习情况信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <textarea class="form-control" rows="6" name="addstudytext"
                                                      required="required"
                                                      placeholder="请输入" style="resize: none;"></textarea>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框-->

                    </td>
                </tr>

                <tr>
                    <td colspan="3">
                        <C:if test="${signmessage.educationtextsign==0}"><font
                                color="red">众成结合以上三个方面，<br>针对学生给予的教育方案</font></C:if>
                        <C:if test="${signmessage.educationtextsign==1}">众成结合以上三个方面，<br>针对学生给予的教育方案</C:if>
                    </td>
                    <td class="text-left" colspan="10">
                        <div class="form-group">
                            <!--label for="name"></label>-->
                            <textarea class="inp5" rows="20" name="educationtext"
                                      readonly="readonly">${stumessage.educationtexthis}${br}<C:if test="${signmessage.educationtextsign==0}">更新的内容:${br}${stumessage.educationtext}</C:if>
                            </textarea>
                        </div>

                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <div class="btnc">
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;" data-toggle="modal"
                                        data-target="#addEduText" style="color: #2e6da4">添加
                                    <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                                </button>
                            </div>
                        </c:if>
                        <!--按键结束-->
                        <!-- 模态框-->
                        <div class="modal fade" id="addEduText" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addEducationText" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加学习情况信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <textarea class="form-control" rows="6" name="addedutext"
                                                      required="required"
                                                      placeholder="请输入" style="resize: none;"></textarea>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框-->
                    </td>
                </tr>

                <tr>
                    <td colspan="3">
                        <C:if test="${signmessage.supporttextsign==0}"><font color="red">家长配合情况</font></C:if>
                        <C:if test="${signmessage.supporttextsign==1}">家长配合情况</C:if>
                    </td>
                    <td class="text-left" colspan="10">
                        <div class="form-group">
                            <!--label for="name"></label>-->
                            <textarea class="inp5" rows="20" name="supporttext"
                                      readonly="readonly">${stumessage.supporttexthis}${br}<C:if test="${signmessage.supporttextsign==0}">更新的内容:${br}${stumessage.supporttext}</C:if>
                            </textarea>
                        </div>

                        <!--按键开始-->
                        <c:if test="${permission==true}">
                            <div class="btnc">
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;" data-toggle="modal"
                                        data-target="#addSupportText" style="color: #2e6da4">添加
                                    <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                                </button>
                            </div>
                        </c:if>
                        <!--按键结束-->
                        <!-- 模态框-->
                        <div class="modal fade" id="addSupportText" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addSupportText" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加家长配合情况信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <textarea class="form-control" rows="6" name="addsupporttext"
                                                      required="required"
                                                      placeholder="请输入" style="resize: none;"></textarea>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框-->
                    </td>
                </tr>

                <tr>
                    <td colspan="3">
                        <C:if test="${signmessage.improvetextsign==0}"><font color="red">学生提高程度</font></C:if>
                        <C:if test="${signmessage.improvetextsign==1}">学生提高程度</C:if>
                    </td>
                    <td class="text-left" colspan="10">
                        <div class="form-group">
                            <!--label for="name"></label>-->
                            <textarea class="inp5" rows="20" name="improvetext"
                                      readonly="readonly">${stumessage.improvetexthis}${br}<C:if test="${signmessage.improvetextsign==0}">更新的内容:${br}${stumessage.improvetext}</C:if>
                            </textarea>
                        </div>

                        <c:if test="${permission==true}">
                            <!--按键开始-->
                            <div class="btnc">
                                <button type="button" class="btn btn-default btn-xs"
                                        style="background-color:transparent;" data-toggle="modal"
                                        data-target="#addImproveText" style="color: #2e6da4">添加
                                    <span class="glyphicon glyphicon-plus" style="color: #2e6da4"></span>
                                </button>
                            </div>
                        </c:if>
                        <!--按键结束-->
                        <!-- 模态框-->
                        <div class="modal fade" id="addImproveText" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">

                                    <form role="form" action="/leader/addImproveText" method="post">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title">添加学习提高情况信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <textarea class="form-control" rows="6" name="addimprovetext"
                                                      required="required"
                                                      placeholder="请输入" style="resize: none;"></textarea>
                                            <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框-->
                    </td>
                </tr>

            </table>

        </div>
    </div>
</div>
</div>
</div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(1)").addClass("active");

    function ToTableOne(str) {
        var a = btoa(str);
        window.location.href = "/leader/editTableOne?encodeID=" + a;
    }

    function ToTableTwo(str) {
        var a = btoa(str);
        window.location.href = "/leader/editTableTwo?encodeID=" + a;
    }

    function ToTableThree(str) {
        var a = btoa(str);
        window.location.href = "/leader/editTableThree?encodeID=" + a;
    }
</script>
</html>
