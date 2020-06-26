<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/3/6
  Time: 下午9:07
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
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">添加用户信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/master/addSeNoteTable" id="editfrom" method="post">
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputPassword3" name="stuname" placeholder="请输入" required ="required">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">学校</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputPassword4" name="stuschool" placeholder="请输入">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label" name="role">状态</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="stugrade" style="width: 150px">
                                    <c:forEach items="${gradeList}" var="item">
                                        <option value="${item.gradename}">${item.gradename}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label" name="role">课程</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control"  name="stucourse" placeholder="请输入">
                            </div>
                        </div>



                        <div class="form-group">
                            <label for="inputPassworde" class="col-sm-2 control-label">备注</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputPassworde" name="remarktext" placeholder="请输入">
                                <input type="hidden"  name="dicid" value="${dicid}">
                                <input type="hidden"  name="currentpage" value="${currentPage}">
                            </div>
                        </div>

                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
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
    $("#nav20").addClass("active");
</script>
</html>
