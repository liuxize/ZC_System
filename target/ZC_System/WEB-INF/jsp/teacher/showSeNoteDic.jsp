<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 25/03/2018
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>记事本目录</title>
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
                        <h1 class="col-md-5">记事表格目录</h1>

                        <button type="button" class="btn btn-success col-md-2 " data-toggle="modal"
                                id="editbutton" onclick="SetPage()" style="margin-top: 20px; float:right"
                                data-target="#addNoteDic">新建记事表格
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
                                        <h4 align="center" class="modal-title">请输入表格标题</h4>
                                    </div>
                                    <form role="form" action="/teacher/showSeNoteDic" method="post">
                                        <div class="modal-body">

                                            <input type="text" class="form-control" name="dicName"
                                                   placeholder="请输入" required="required">
                                            <input type="hidden" id="currentPage" name="currentPage" value=" ">
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
                    <c:forEach items="${noteDicList}" var="item" varStatus="status">
                        <tr>
                            <th>${(pagingVO.curentPageNo-1)*(pagingVO.pageSize)+status.index + 1}</th>
                            <th>${item.dicname}</th>
                            <th>
                                <button type="button" class="btn btn-primary btn-xs"
                                        onClick="window.open('/teacher/editSeNoteTable?dicid=${item.dicid}')">查看表格
                                </button>

                                    <%--<form role="form" action="/teacher/removeNoteDic" method="post">--%>
                                    <%--<input type="hidden"  name="dicid" value="${item.dicid}">--%>
                                    <%--<input type="hidden"  name="currentPage" value="${pagingVO.curentPageNo}">--%>
                                    <%--<button type="button" class="btn btn-danger  btn-xs">删除表格--%>
                                    <%--</button>--%>
                                    <%--</form>--%>

                                <button type="button" class="btn btn-danger  btn-xs"
                                        onclick="remove('${item.dicid}','${pagingVO.curentPageNo}')">删除表格
                                </button>


                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="/teacher/showSeNoteDic?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/teacher/showSeNoteDic?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/teacher/showSeNoteDic?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/teacher/showSeNoteDic?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/teacher/showSeNoteDic?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a>
                                    </li>
                                </c:if>
                                <li><a href="/teacher/showSeNoteDic?page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
                            </ul>
                        </nav>
                    </c:if>
                </div>

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

    function confirmd() {
        var msg = "您真的确定要删除吗？！";
        if (confirm(msg) == true) {
            return true;
        } else {
            return false;
        }
    };

    $("#addNoteDic").modal("hide");
    function SetPage() {
        $("#currentPage").val("${pagingVO.curentPageNo}");
    }

    $("#sub").click(function () {
        $("#form1").submit();
    });

    <c:if test="${pagingVO != null}">
    if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
        $(".pagination li:last-child").addClass("disabled")
    }
    ;

    if (${pagingVO.curentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    }
    ;
    </c:if>

    function remove(dicid,page) {
        var msg = "您确定要删除吗";
        if (confirm(msg) == true) {
            window.location.href="/teacher/removeSeNoteDic?dicid=" + dicid + "&currentPage=" +page;
            return true;
        } else {
            return false;
        }
    }
</script>
</html>

