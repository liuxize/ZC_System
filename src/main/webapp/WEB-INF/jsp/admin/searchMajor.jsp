<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/3/1
  Time: 下午6:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>专业查询</title>
    <link rel="shortcut icon" type="image/x-icon" href="/images/logo-dt.png" media="screen">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <!-- bootstrap-select -->
    <link rel="stylesheet"  href="/css/bootstrap-select.min.css">
    <script src="/js/bootstrap-select.min.js"></script>

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
                        <h1 class="col-md-5">专业查询</h1>
                        <form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0; float:right;"
                              action="/admin/searchMajor" id="form1" method="post">
                            <div class="form-inline" style="float: right">
                                <div class="form-group" style="width: 150px">
                                    <select class="selectpicker show-tick form-control" data-live-search="true" name="majorName" >
                                        <c:forEach items="${majorList}" var="item">
                                            <c:choose>
                                                <c:when test="${item.majorname==majorTemp}">
                                                    <option value="${item.majorname}"
                                                            selected="selected">${item.majorname}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${item.majorname}">${item.majorname}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-default" type="submit">查找</button>
                                </div>

                            </div>
                        </form>


                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th width="10%">序号</th>
                        <th width="15%">学号</th>
                        <th width="20%">姓名</th>
                        <th width="10%">状态</th>
                        <th width="20%">电话</th>
                        <th width="25%">操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${stuList}" var="item" varStatus="status">
                        <tr>
                            <td>${(pagingVO.curentPageNo-1)*(pagingVO.pageSize)+status.index + 1}</td>
                            <td>${item.stuid}</td>
                            <td>${item.stuname}</td>
                            <td>${item.gradename}</td>
                            <td>${item.stutel}</td>
                            <td>
                                <button type="button" class="btn btn-primary btn-xs"
                                        onClick="ToTableOne(${item.stuid})">查看信息
                                </button>
                                &nbsp; &nbsp; &nbsp;
                                <button type="button" class="btn btn-success btn-xs"
                                        onClick="window.open('/admin/editMessage?stuid=${item.stuid}')">修改信息
                                </button>
                                &nbsp; &nbsp; &nbsp;
                                <button type="button" class="btn btn-danger btn-xs"
                                        onclick="remove('${item.stuid}','${item.major}','${pagingVO.curentPageNo}')">删除信息
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="/admin/searchMajor?page=1&encodeMajor=${majorTemp}">首页</a></li>
                                <c:if test="${pagingVO.curentPageNo <= 1}">
                                    <li><a href="/admin/searchMajor?page=1&encodeMajor=${majorTemp}">&laquo;上一页</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo > 1}">
                                    <li><a href="/admin/searchMajor?page=${pagingVO.upPageNo}&encodeMajor=${majorTemp}">&laquo;上一页</a></li>
                                </c:if>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/searchMajor?page=${pagingVO.curentPageNo+1}&encodeMajor=${majorTemp}">${pagingVO.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/searchMajor?page=${pagingVO.curentPageNo+2}&encodeMajor=${majorTemp}">${pagingVO.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/searchMajor?page=${pagingVO.curentPageNo+3}&encodeMajor=${majorTemp}">${pagingVO.curentPageNo+3}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}&majorname=${majorTemp}">
                                    <li>
                                        <a href="/admin/searchMajor?page=${pagingVO.curentPageNo+4}&encodeMajor=${majorTemp}">${pagingVO.curentPageNo+4}</a>
                                    </li>
                                </c:if>

                                <c:if test="${pagingVO.curentPageNo <pagingVO.totalCount}">
                                    <li><a href="/admin/searchMajor?page=${pagingVO.nextPageNo}&encodeMajor=${majorTemp}">下一页&raquo;</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo >=pagingVO.totalCount}">
                                    <li><a href="/admin/searchMajor?page=${pagingVO.totalCount}&encodeMajor=${majorTemp}">下一页&raquo;</a></li>
                                </c:if>
                                <li><a href="/admin/searchMajor?page=${pagingVO.totalCount}&encodeMajor=${majorTemp}">尾页</a></li>

                                <li><a><input id="toPage" style="height: 18px; width: 50px;border: 0px;outline:none;" type="text" placeholder="共${pagingVO.totalCount}页"/></a></li>
                                <li><a href="javascript:void(0);" onclick="jumpPage()">跳转</a></li>

                            </ul>
                        </nav>
                    </c:if>
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

    function jumpPage(){
        var page = $("#toPage").val();
        if (page==''){return;}
        if(page<=${pagingVO.totalCount}){
            window.location.href="/admin/searchMajor?page=" + page+ "&encodeMajor=" + '${majorTemp}';
        }
    }


    function ToTableOne(str) {
        var a=btoa(str);
        window.open('/admin/editTableOne?encodeID='+a);
        // window.location.href="/admin/editTableOne?encodeID="+a;
    }

    function remove(id,majorName,page) {
        var msg = "您确定要删除吗";
        if (confirm(msg) == true) {
            window.location.href="/admin/removeStuMajor?stuid=" + id + "&majorName="+ majorName+"&currentPage=" +page;
            return true;
        } else {
            return false;
        }
    }

</script>
</html>
