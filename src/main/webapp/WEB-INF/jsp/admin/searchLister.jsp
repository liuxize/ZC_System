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
    <title>制表人查询</title>
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
                        <h1 class="col-md-5">制表人查询</h1>
                        <form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0; float:right;"
                              action="/admin/searchLister" id="form1" method="post">
                            <div class="form-inline" style="float: right" >

                                <input class="form-control"  type="date"  max="9999-12-31" name="startdate" value="${starttime}"/>至
                                <input class="form-control" type="date"  max="9999-12-31" name="enddate" value="${endtime}"/>
                            </div>
                            <div class="form-inline" style="margin: 10px 0 10px 0; float: right" >
                                <b>姓名：</b> &nbsp;&nbsp;
                                <input type="text" class="form-control" value="${name}" name="name">
                                <button class="btn btn-default " type="submit">查找</button>
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
                                <%--&nbsp; &nbsp; &nbsp;--%>
                                <%--<button type="button" class="btn btn-danger btn-xs"--%>
                                        <%--onClick="location.href='/admin/removeStuName?stuid=${item.stuid}&currentPage=${pagingVO.curentPageNo}'">删除信息--%>
                                <%--</button>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="/admin/searchLister?page=1&datestart=${starttime}&dateend=${endtime}&name=${name}">首页</a></li>
                                <c:if test="${pagingVO.curentPageNo <= 1}">
                                    <li><a href="/admin/searchLister?page=1&datestart=${starttime}&dateend=${endtime}&name=${name}">&laquo;上一页</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo > 1}">
                                    <li><a href="/admin/searchLister?page=${pagingVO.upPageNo}&datestart=${starttime}&dateend=${endtime}&name=${name}">&laquo;上一页</a></li>
                                </c:if>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/searchLister?page=${pagingVO.curentPageNo+1}&datestart=${starttime}&dateend=${endtime}&name=${name}">${pagingVO.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/searchLister?page=${pagingVO.curentPageNo+2}&datestart=${starttime}&dateend=${endtime}&name=${name}">${pagingVO.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/searchLister?page=${pagingVO.curentPageNo+3}&datestart=${starttime}&dateend=${endtime}&name=${name}">${pagingVO.curentPageNo+3}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/searchLister?page=${pagingVO.curentPageNo+4}&datestart=${starttime}&dateend=${endtime}&name=${name}">${pagingVO.curentPageNo+4}</a>
                                    </li>
                                </c:if>

                                <c:if test="${pagingVO.curentPageNo <pagingVO.totalCount}">
                                    <li><a href="/admin/searchLister?page=${pagingVO.nextPageNo}&datestart=${starttime}&dateend=${endtime}&name=${name}">下一页&raquo;</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo >=pagingVO.totalCount}">
                                    <li><a href="/admin/searchLister?page=${pagingVO.totalCount}&datestart=${starttime}&dateend=${endtime}&name=${name}">下一页&raquo;</a></li>
                                </c:if>
                                <li><a href="/admin/searchLister?page=${pagingVO.totalCount}&datestart=${starttime}&dateend=${endtime}&name=${name}">尾页</a></li>

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

    function ToTableOne(str) {
        var a=btoa(str);
        window.open('/admin/editTableOne?encodeID='+a);
    }

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
            window.location.href="/admin/searchLister?page="+ page + "&datestart=" +'${starttime}' + "&dateend=" + '${endtime}' +"&name=" +'${name}';
        }
    }

</script>
</html>
