<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/2/21
  Time: 下午9:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
</head>
<body>

<!-- 顶栏 -->


<div class="container" id="content">
    <div class="row">
        <jsp:include page="menucopy.jsp"></jsp:include>
        <div class="col-md-12 container">

            <h2 class="text-center">${stumessage.stuid}号学生档案</h2>


            <ul class="nav nav-tabs">
                <li><a href="javascript:void(0);" onclick="ToTableOne(${stumessage.stuid})">表一</a></li>
                <li><a href="javascript:void(0);" onclick="ToTableTwo(${stumessage.stuid})">表二</a></li>
                <li class="active"><a href="javascript:void(0);" onclick="ToTableThree(${stumessage.stuid})">表三</a></li>
            </ul>


            <!--div class="col-sm-10" style="font-size: 15px; padding: 10pt;">
                hahah
            </div-->
            <button class="btn btn-success col-md-2" style="float:right; margin: 20px 10px 10px 10px;"
                    onClick="location.href='/teacher/addLesson?stuid=${stumessage.stuid}'">
                添加课程信息
                <sapn class="glyphicon glyphicon-plus"/>
            </button>


            <table class="table table-bordered text-center table-striped ">


                <tr>
                    <td style="width: 5%;">学生</td>
                    <td style="width: 14%;">${stumessage.stuname}</td>
                    <td style="width: 8%;">所在学校</td>
                    <td colspan="3">${stumessage.school}</td>
                    <td>状态</td>
                    <td  colspan="2" >${stumessage.gradename}</td>
                </tr>


                <tr>
                    <td colspan="2">上课时间</td>
                    <td>录入老师</td>
                    <td style="width: 12%;">上课老师</td>
                    <td style="width: 8%;">科目</td>
                    <td style="width: 8%;">课程类型</td>
                    <td style="width: 12%;">每日上课时间</td>
                    <td width="5%">课时</td>
                    <td colspan="2">备注</td>
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
                        <td rowspan="3" colspan="2">${item.remark}</td>
                    </tr>
                    <tr>
                        <td colspan="6" align="left">上课日期: ${item.schooldate}</td>
                    </tr>
                    <tr>
                        <td colspan="5" align="left">
                            出勤日期:${item.dutydatehis}
                            <c:if test="${item.dutydate !=''}"><font color="red">${item.dutydate}</font></c:if>
                        </td>
                        <td>
                            <button type="button" class="btn btn-default btn-xs" data-toggle="modal"
                                    data-target="#modal${item.lessonid}">增加
                            </button>
                            <div class="modal fade" id="modal${item.lessonid}" tabindex="-1" role="dialog"
                                 aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                                <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                    <div class="modal-content">

                                        <form role="form" action="/teacher/addDutyDate" id="editstu" method="post">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                    &times;
                                                </button>
                                                <h4 class="modal-title">添加签到</h4>
                                            </div>
                                            <div class="modal-body">
                                                <input type="text" class="form-control" name="dutydate" required="required"
                                                       placeholder="请输入">
                                                <input type="hidden" name="lessonid" value=${item.lessonid}>
                                                <input type="hidden" name="stuid" value=${item.stuid}>

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
                        </td>
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

    function ToTableOne(str) {
        var a=btoa(str);
        window.location.href="/teacher/editTableOne?encodeID="+a;
    }

    function ToTableTwo(str) {
        var a=btoa(str);
        window.location.href="/teacher/editTableTwo?encodeID="+a;
    }

    function ToTableThree(str) {
        var a=btoa(str);
        window.location.href="/teacher/editTableThree?encodeID="+a;
    }

</script>
</html>
