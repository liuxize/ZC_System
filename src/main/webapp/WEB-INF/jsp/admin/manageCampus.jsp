<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/11/10
  Time: 4:34 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>校区管理</title>
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
                        <h1 class="col-md-5">校区管理</h1>

                        <button type="button" class="btn btn-success col-md-2 " data-toggle="modal"
                                id="editbutton"  style="margin-top: 20px; float:right"
                                data-target="#addNoteDic">新建校区名称
                            <span class="glyphicon glyphicon-plus"></span>
                        </button>

                        <div class="modal fade" id="addNoteDic" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 align="center" class="modal-title">请输入校区名称</h4>
                                    </div>
                                    <form role="form" action="/admin/manageCampus" method="post">
                                        <div class="modal-body">

                                            <input type="text" class="form-control" name="campusname"
                                                   placeholder="请输入" required="required">
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                                关闭
                                            </button>
                                            <button class="btn btn-default" type="submit">保存</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                    </div>
                </div>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width:10%">序号</th>
                        <th style="width:45%">名称</th>
                        <th style="width:45%">操作</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${campusList}" var="item" varStatus="status">
                        <tr>
                            <th>${status.index + 1}</th>
                            <th>${item.campusname}</th>
                            <th>


                                <button type="button" class="btn btn-danger  btn-xs"
                                        onclick="deleteCampus('${item.campusid}')">删除校区名称
                                </button>


                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

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
    $("#nav21").addClass("active");

    function deleteCampus(campusid) {
        var msg = "您确定要删除吗";
        if (confirm(msg) == true) {
            window.location.href="/admin/removeCampus?campusid="+ campusid;
            return true;
        } else {
            return false;
        }
    }
</script>

</html>
