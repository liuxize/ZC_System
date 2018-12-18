<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/5/16
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>电话查询</title>
    <link rel="shortcut icon" type="image/x-icon" href="/images/logo-dt.png" media="screen">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

</head>
<body>

<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 class="col-md-5">电话查询</h1>
                        <form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0; float:right;"
                              action="/admin/searchTel" id="form1" method="post">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="请输入电话" name="telphone" value="${tel}">
                                <span class="input-group-addon btn" id="sub">搜索</span>
                            </div>
                        </form>


                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 25%">姓名</th>
                        <th style="width: 25%">身份</th>
                        <th style="width: 25%">电话</th>
                        <th style="width: 25%">操作</th>

                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${allStuList}" var="item" varStatus="status">
                        <tr>
                            <td>${item.stuname}</td>
                            <td>学生</td>
                            <td>${tel}</td>
                            <td>
                                <button type="button" class="btn btn-primary btn-xs"
                                        onClick="ToTableOne(${item.stuid})">查看信息
                                </button>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:forEach items="${motherList}" var="item" varStatus="status">
                        <tr>
                            <td>${item.mothername}</td>
                            <td>${item.stuname}的母亲</td>
                            <td>${tel}</td>
                            <td>
                                <button type="button" class="btn btn-primary btn-xs"
                                        onClick="ToTableOne(${item.stuid})">查看信息
                                </button>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:forEach items="${fatherList}" var="item" varStatus="status">
                        <tr>
                            <td>${item.fathername}</td>
                            <td>${item.stuname}的父亲</td>
                            <td>${tel}</td>
                            <td>
                                <button type="button" class="btn btn-primary btn-xs"
                                        onClick="ToTableOne(${item.stuid})">查看信息
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav2").addClass("active");

    function confirmd() {
        var msg = "您真的确定要删除吗？！";
        if (confirm(msg) == true) {
            return true;
        } else {
            return false;
        }
    };

    $("#sub").click(function () {
        $("#form1").submit();
    });

    function ToTableOne(str) {
        var a=btoa(str);
        window.open('/admin/editTableOne?encodeID='+a);
    }
</script>
</html>
