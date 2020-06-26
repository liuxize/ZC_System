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
                <li class="active"><a href="/admin/editMessageTwo?stuid=${stumessage.stuid}">表二</a></li>
                <li><a href="/admin/editMessageThree?stuid=${stumessage.stuid}">表三</a></li>
            </ul>


            <!--div class="col-sm-10" style="font-size: 15px; padding: 10pt;">
                hahah
            </div-->


            <table class="table table-bordered text-center table-striped ">

                <tr>
                    <td style="width: 100px;">学生</td>
                    <td colspan="2">${stumessage.stuname}</td>
                    <td colspan="2">所在学校</td>
                    <td colspan="6">${stumessage.school}</td>
                    <td style="width: 50px;">状态</td>
                    <td  colspan="2">${stumessage.gradename}</td>
                </tr>

                <tr>
                    <td colspan="14">成绩</td>
                </tr>

                <tr>
                    <td >时间</td>
                    <td style="width: 80px;">录入人</td>
                    <td style="width: 100px;">考试类型</td>
                    <td style="width: 50px;">数学</td>
                    <td style="width: 50px;">语文</td>
                    <td style="width: 50px;">英语</td>
                    <td style="width: 50px;">物理</td>
                    <td style="width: 50px;">化学</td>
                    <td style="width: 50px;">政治</td>
                    <td style="width: 50px;">历史</td>
                    <td style="width: 50px;">生物</td>
                    <td style="width: 50px;">地理</td>
                    <td >备注</td>
                    <td width="5%">操作</td>
                </tr>

                <c:forEach items="${examlist}" var="item">
                    <tr>
                        <td rowspan="2">
                            <fmt:formatDate value="${item.examdata}" dateStyle="medium"/>

                        </td>
                        <td rowspan="2">
                            <c:if test="${item.examsign==0}">
                                <font color="red">
                                        ${item.updateperson}
                                </font>
                            </c:if>
                            <c:if test="${item.examsign==1}">
                                ${item.updateperson}
                            </c:if>
                        </td>
                        <td rowspan="2">${item.examtype}</td>
                        <td>${item.math}</td>
                        <td>${item.chinese}</td>
                        <td>${item.english}</td>
                        <td>${item.physics}</td>
                        <td>${item.chemistry}</td>
                        <td>${item.politics}</td>
                        <td>${item.history}</td>
                        <td>${item.biology}</td>
                        <td>${item.geography}</td>
                        <td  rowspan="2">${item.examremark}</td>
                        <td  rowspan="2">
                            <button type="button" class="btn btn-primary btn-xs"
                                    onClick="location.href='/admin/editExam?examid=${item.examid}'">修改
                            </button>
                            <div style="height: 10px"></div>
                            <button type="button" class="btn btn-danger btn-xs"
                                    onClick="location.href='/admin/removeExam?examid=${item.examid}&stuid=${stumessage.stuid}'">删除
                            </button>

                        </td>

                    </tr>
                    <tr>
                        <td>${item.mathscore}</td>
                        <td>${item.chinesescore}</td>
                        <td>${item.englishscore}</td>
                        <td>${item.physcore}</td>
                        <td>${item.chemscore}</td>
                        <td>${item.polscore}</td>
                        <td>${item.hisscore}</td>
                        <td>${item.bioscore}</td>
                        <td>${item.geoscore}</td>


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

