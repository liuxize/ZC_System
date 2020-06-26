<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/5/6
  Time: 8:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>修改信息</title>
    <link rel="shortcut icon" type="image/x-icon" href="/images/logo-dt.png" media="screen">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>

<!-- 顶栏 -->


<div class="container" id="content">
    <div class="row">
        <jsp:include page="menucopy.jsp"></jsp:include>
        <div class="col-md-12 container">
            <h2 class="text-center">${stumessage.stuid}号学生档案</h2>

            <ul class="nav nav-tabs">
                <li><a href="/admin/editMessage?stuid=${stumessage.stuid}">表一</a></li>
                <li><a href="/admin/editMessageTwo?stuid=${stumessage.stuid}">表二</a></li>
                <li  class="active"><a href="/admin/editMessageThree?stuid=${stumessage.stuid}">表三</a></li>
            </ul>


            <!--div class="col-sm-10" style="font-size: 15px; padding: 10pt;">
                hahah
            </div-->

            <table class="table table-bordered text-center table-striped ">


                <tr>
                    <td style="width: 5%;">学生</td>
                    <td style="width: 14%;">${stumessage.stuname}</td>
                    <td style="width: 8%;">所在学校</td>
                    <td colspan="3">${stumessage.school}</td>
                    <td style="width: 10%;">状态</td>
                    <td  colspan="3">${stumessage.gradename}</td>
                </tr>


                <tr>
                    <td colspan="2">上课时间</td>
                    <td>录入老师</td>
                    <td style="width: 12%;">上课老师</td>
                    <td style="width: 8%;">科目</td>
                    <td style="width: 8%;">课程类型</td>
                    <td>每日上课时间</td>
                    <td width="7%">课时</td>
                    <td>备注</td>
                    <td width="5%">操作</td>
                </tr>
                <c:forEach items="${lesList}" var="item">
                    <tr>
                        <td rowspan="3" colspan="2"><fmt:formatDate value="${item.lessonstart}" dateStyle="medium"/>至
                            <fmt:formatDate value="${item.lessonend}" dateStyle="medium"/></td>
                        <td>
                            <c:if test="${item.lessonsign==0}">
                                <font color="red">
                                        ${item.operator}
                                </font>
                            </c:if>
                            <c:if test="${item.lessonsign==1}">
                                ${item.operator}
                            </c:if>
                        </td>
                        <td>${item.lecturer}</td>
                        <td>${item.subjectname}</td>
                        <td>${item.typename}</td>
                        <td>${item.schooltime}</td>
                        <td>${item.lessontime}</td>
                        <td rowspan="3">${item.remark}</td>
                        <td rowspan="3">
                            <button type="button" class="btn btn-primary btn-xs"
                                    onClick="location.href='/admin/editLesson?lessonid=${item.lessonid}'">修改
                            </button>
                            <div style="height: 10px"></div>
                            <button type="button" class="btn btn-danger btn-xs"
                                    onClick="location.href='/admin/removeLesson?lessonid=${item.lessonid}&stuid=${stumessage.stuid}'">删除
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" align="left">上课日期: ${item.schooldate}</td>
                    </tr>
                    <tr>
                        <td colspan="6" align="left">出勤日期: ${item.dutydatehis}</td>
                    </tr>
                </c:forEach>
            </table>


        </div>
    </div>


</div>


</div>
</div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(1)").addClass("active")
</script>
</html>

