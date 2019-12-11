<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 25/03/2018
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>公告管理</title>
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
                        <h1 class="col-md-5">公告管理</h1>

                        <button type="button" class="btn btn-success col-md-2 " data-toggle="modal"
                                id="editbutton"  style="margin-top: 20px; float:right"
                                data-target="#addNoteDic">新建公告
                            <span class="glyphicon glyphicon-plus"></span>
                        </button>

                        <!-- 修改用户密码-->
                        <div class="modal fade" id="addNoteDic" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 align="center" class="modal-title">请输入课程类型名称</h4>
                                    </div>
                                    <form role="form" action="/admin/manageAnnounce" method="post">
                                        <div class="modal-body">

                                            <input type="text" class="form-control" name="typename"
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
                        <th >序号</th>
                        <th >名称</th>
                        <th>内容摘要</th>
                        <th >操作</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${announceList}" var="item" varStatus="status">
                        <%--                        --%>
                        <C:if test="${item.isread==0}">
                            <tr bgcolor="#e3ffdc;">
                                <th style="width:5%">${status.index + 1}</th>
                                <th style="width:20%">${item.anntitle}</th>

                                <th style="width:45%">
                                    <div style="width:450px; white-space:nowrap;text-overflow:ellipsis;overflow:hidden;"> ${item.anncon}</div>
                                </th>
                                <th style="width:30%">

                                    <button type="button" class="btn btn-primary  btn-xs"
                                            onclick="deleteClassType('${item.annid}')">
                                        编辑内容
                                    </button>
                                    &nbsp;&nbsp;
                                    <button type="button" class="btn btn-danger  btn-xs"
                                            onclick="deleteClassType('${item.annid}')">
                                        删除公告
                                    </button>
                                    &nbsp; &nbsp;
                                    <button type="button" class="btn btn-info  btn-xs"
                                            onclick="deleteClassType('${item.annid}')">
                                        显示公告
                                    </button>
                                    &nbsp; &nbsp;
                                    <button type="button" class="btn btn-default  btn-xs disabled"
                                            onclick="deleteClassType('${item.annid}')">
                                        隐藏公告
                                    </button>

                                </th>
                            </tr>
                        </C:if>
                        <C:if test="${item.isread==1}">
                            <tr>
                                <th style="width:5%">${status.index + 1}</th>
                                <th style="width:20%">${item.anntitle}</th>

                                <th style="width:45%">
                                    <div style="width:450px; white-space:nowrap;text-overflow:ellipsis;overflow:hidden;"> ${item.anncon}</div>
                                </th>
                                <th style="width:30%">

                                    <button type="button" class="btn btn-primary  btn-xs"
                                            onclick="deleteClassType('${item.annid}')">
                                        编辑内容
                                    </button>
                                    &nbsp;&nbsp;
                                    <button type="button" class="btn btn-danger  btn-xs"
                                            onclick="deleteClassType('${item.annid}')">
                                        删除公告
                                    </button>
                                    &nbsp; &nbsp;
                                    <button type="button" class="btn btn-info  btn-xs disabled"
                                            onclick="deleteClassType('${item.annid}')">
                                        显示公告
                                    </button>
                                    &nbsp; &nbsp;
                                    <button type="button" class="btn btn-default  btn-xs"
                                            onclick="deleteClassType('${item.annid}')">
                                        隐藏公告
                                    </button>

                                </th>
                            </tr>
                        </C:if>
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

    function deleteClassType(typeid) {
        var msg = "您确定要删除吗";
        if (confirm(msg) == true) {
            window.location.href="/admin/removeClassType?typeid="+ typeid;
            return true;
        } else {
            return false;
        }
    }

</script>
</html>

