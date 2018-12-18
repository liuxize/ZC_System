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
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">${username}的文本目录</h1>

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
                    <c:forEach items="${textList}" var="item" varStatus="status">
                        <tr>
                            <th>${(pagingVO.curentPageNo-1)*(pagingVO.pageSize)+status.index + 1}</th>
                            <th>${item.title}</th>
                            <th>
                                <button type="button" class="btn btn-primary btn-xs"
                                        onClick="window.open('/admin/showUserNoteText?textid=${item.textid}')">查看文本

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
                                <li><a href="/admin/showUserNoteDic?username=${username}&page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showUserNoteDic?username=${username}&page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showUserNoteDic?username=${username}&page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showUserNoteDic?username=${username}&page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showUserNoteDic?username=${username}&page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a>
                                    </li>
                                </c:if>
                                <li><a href="/admin/showUserNoteDic?username=${username}&page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
                            </ul>
                        </nav>
                    </c:if>
                </div>

            </div>

        </div>


        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">${username}的表格目录</h1>

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
                            <th>${(pagingVO1.curentPageNo-1)*(pagingVO1.pageSize)+status.index + 1}</th>
                            <th>${item.dicname}</th>
                            <th>
                                <button type="button" class="btn btn-primary btn-xs"
                                        onClick="window.open('/admin/showUserNoteTable?dicid=${item.dicid}')">查看表格

                                </button>

                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO1 != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="/admin/showUserNoteDic?username=${username}&page1=${pagingVO1.upPageNo}">&laquo;上一页</a></li>
                                <li class="active"><a href="">${pagingVO1.curentPageNo}</a></li>
                                <c:if test="${pagingVO1.curentPageNo+1 <= pagingVO1.totalCount}">
                                    <li>
                                        <a href="/admin/showUserNoteDic?username=${username}&page1=${pagingVO1.curentPageNo+1}">${pagingVO1.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO1.curentPageNo+2 <= pagingVO1.totalCount}">
                                    <li>
                                        <a href="/admin/showUserNoteDic?username=${username}&page1=${pagingVO1.curentPageNo+2}">${pagingVO1.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO1.curentPageNo+3 <= pagingVO1.totalCount}">
                                    <li>
                                        <a href="/admin/showUserNoteDic?username=${username}&page1=${pagingVO1.curentPageNo+3}">${pagingVO1.curentPageNo+3}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO1.curentPageNo+4 <= pagingVO1.totalCount}">
                                    <li>
                                        <a href="/admin/showUserNoteDic?username=${username}&page1=${pagingVO1.curentPageNo+4}">${pagingVO1.curentPageNo+4}</a>
                                    </li>
                                </c:if>
                                <li><a href="/admin/showUserNoteDic?username=${username}&page1=${pagingVO1.totalCount}">最后一页&raquo;</a></li>
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
    $("#nav6").addClass("active");

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
</script>
</html>

