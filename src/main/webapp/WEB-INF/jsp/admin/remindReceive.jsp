<%--
  Created by IntelliJ IDEA.
  User: liuxize
  Date: 2018/3/29
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>学生信息更新</title>
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
                        <h1 class="col-md-5">信息更新处理</h1>
                    </div>
                </div>
                <ul class="nav nav-tabs">
                    <li class="active"><a href="">已签字</a></li>
                    <li><a href="/admin/remindReceiveUnsign?page=1">未签字</a></li>
                </ul>

                <div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th style="width:5%">序号</th>
                            <th style="width: 15%">姓名</th>
                            <th style="width: 27%">学校</th>
                            <th style="width: 13%">年级</th>
                            <th style="width: 25%">电话</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${stuList}" var="item" varStatus="status">
                            <tr>
                                <td>${(pagingVO.curentPageNo-1)*(pagingVO.pageSize)+status.index + 1}</td>
                                <td>${item.stuname}</td>
                                <td>${item.school}</td>
                                <td>${item.gradename}</td>
                                <td>${item.stutel}</td>
                                <td>
                                    <button type="button" class="btn btn-primary btn-xs"
                                            onClick="ToTableOne(${item.stuid})">查看信息
                                    </button>
                                    &nbsp; &nbsp; &nbsp;
                                    <button type="button" class="btn btn-success btn-xs"
                                            onclick="updateConfirm('${item.stuid}','${pagingVO.curentPageNo}')"
                                    >删除信息
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="/admin/remindReceive?page=1">首页</a></li>
                                <c:if test="${pagingVO.curentPageNo <= 1}">
                                    <li><a href="/admin/remindReceive?page=1">&laquo;上一页</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo > 1}">
                                    <li><a href="/admin/remindReceive?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
                                </c:if>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/remindReceive?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/remindReceive?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/remindReceive?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/remindReceive?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo <pagingVO.totalCount}">
                                    <li><a href="/admin/remindReceive?page=${pagingVO.nextPageNo}">下一页&raquo;</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo >=pagingVO.totalCount}">
                                    <li><a href="/admin/remindReceive?page=${pagingVO.totalCount}">下一页&raquo;</a></li>
                                </c:if>
                                <li><a href="/admin/remindReceive?page=${pagingVO.totalCount}">尾页</a></li>

                                <li><a><input id="toPage" style="height: 18px; width: 50px;border: 0px;outline:none;"
                                              type="text" placeholder="共${pagingVO.totalCount}页"/></a></li>
                                <li><a href="javascript:void(0);" onclick="jumpPage()">跳转</a></li>
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
    $("#nav7").addClass("active");

    $("#sub").click(function () {
        $("#form1").submit();
    });

    <c:if test="${pagingVO != null}">
    if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
        $(".pagination li:nth-last-child(3)").addClass("disabled");
        $(".pagination li:nth-last-child(4)").addClass('disabled'); // Disables visually
    }
    ;

    if (${pagingVO.curentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled");
        $(".pagination li:nth-child(2)").addClass("disabled");
    }
    ;
    </c:if>

    function jumpPage() {
        var page = $("#toPage").val();
        if (page == '') {
            return;
        }
        if (page <=${pagingVO.totalCount}) {
            window.location.href = "/admin/remindReceive?page=" + page;
        }
    }

    function ToTableOne(str) {
        var a = btoa(str);
        window.open('/admin/editTableOne?encodeID=' + a);
    }


    function updateConfirm(stuid, page) {
        var msg = "您确定要删除吗";
        if (confirm(msg) == true) {
            window.location.href = "/admin/updateConfirm?stuid=" + stuid + "&currentPage=" + page;

            return true;
        } else {
            return false;
        }
    }


</script>
</html>
