<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/2/26
  Time: 下午6:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>记事本</title>
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

<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">记事本: ${texttitle}</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/admin/editNoteText" id="editfrom" method="post">



                        <div class="form-group">
                            <input class="hidden" name="textid" value="${textid}">
                            <textarea style="width: 100%; resize: none; border: none; font-size:18px;" rows="25"
                                      name="content">${textDic}</textarea>
                            <input class="hidden" name="currentPage" value="${currentPage}">
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">保存</button>
                            <button class="btn btn-default" type="reset">重置</button>
                        </div>
                    </form>
                </div>

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
    $("#nav5").addClass("active");
</script>
</html>