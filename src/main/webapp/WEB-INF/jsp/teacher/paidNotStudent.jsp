<%--
  Created by IntelliJ IDEA.
  User: liuxize
  Date: 2018/3/24
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>未缴费学员</title>
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
                        <h1 class="col-md-3">未缴费学员</h1>

                        <form class="bs-example bs-example-form" role="form"
                              style="margin: 20px 0 10px 10px; float:right;"
                              action="/teacher/paidNotStudent" id="form1" method="post">
                            <div class="form-inline" style="float: right">

                                校区
                                <div class="form-group" style="width: 220px">

                                    <select class="selectpicker show-tick form-control" data-live-search="true" name="campusid" >
                                        <c:forEach items="${campusList}" var="item">
                                            <c:choose>
                                                <c:when test="${item.campusid==campusIndex}">
                                                    <option value="${item.campusid}"
                                                            selected="selected">${item.campusname}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${item.campusid}">${item.campusname}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>

                                状态
                                <div class="form-group" style="width: 150px">
                                    <select class="selectpicker show-tick form-control" data-live-search="true" name="gradeid" >
                                        <c:forEach items="${gradelist}" var="item">
                                            <c:choose>
                                                <c:when test="${item.gradeid==gradeIndex}">
                                                    <option value="${item.gradeid}"
                                                            selected="selected">${item.gradename}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${item.gradeid}">${item.gradename}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>

                                电话
                                <div class="form-group" style="width: 150px">
                                    <select class="selectpicker show-tick form-control" data-live-search="true" name="teleType"  id="teletype">
                                        <C:if test="${teleType==0}">
                                            <option id="allTel"  value="0" selected="selected">全部</option>
                                            <option id="Tel" value ="1">有电话</option>
                                            <option id="noTel" value="2">无电话</option>
                                        </C:if>
                                        <C:if test="${teleType==1}">
                                            <option id="allTel"  value="0" >全部</option>
                                            <option id="Tel" value ="1" selected="selected">有电话</option>
                                            <option id="noTel" value="2">无电话</option>
                                        </C:if>
                                        <C:if test="${teleType==2}">
                                            <option id="allTel"  value="0" >全部</option>
                                            <option id="Tel" value ="1" >有电话</option>
                                            <option id="noTel" value="2" selected="selected">无电话</option>
                                        </C:if>
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
                        <th style="width:4%">序号</th>
                        <th style="width: 8%">姓名</th>
                        <th>学校</th>
                        <th  style="width: 6%">状态</th>
                        <th colspan="3">电话</th>
                        <th width="8%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${stuList}" var="item" varStatus="status">
                        <tr>
                            <td>${(pagingVO.curentPageNo-1)*(pagingVO.pageSize)+status.index + 1}</td>
                            <td>${item.stuname}</td>
                            <td>${item.school}</td>
                            <td>${item.gradename}</td>
                            <td style="border-right-color:transparent; width: 20%">学生：${item.stutel}</td>
                            <td style="border-right-color:transparent; width: 20%">父亲：${item.fathertel}</td>
                            <td style="width: 20%">母亲：${item.mothertel}</td>
                            <td>
                                <button type="button" class="btn btn-primary btn-xs"
                                        onClick="ToTableOne(${item.stuid})">查看信息
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
                                <li><a href="/teacher/paidNotStudent?page=1&gradeid=${gradeIndex}&teleType=${teleType}&campusid=${campusIndex}">首页</a></li>
                                <c:if test="${pagingVO.curentPageNo <= 1}">
                                    <li><a href="/teacher/paidNotStudent?page=1&gradeid=${gradeIndex}&teleType=${teleType}&campusid=${campusIndex}">&laquo;上一页</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo > 1}">
                                    <li><a href="/teacher/paidNotStudent?page=${pagingVO.upPageNo}&gradeid=${gradeIndex}&teleType=${teleType}&campusid=${campusIndex}">&laquo;上一页</a></li>
                                </c:if>

                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/teacher/paidNotStudent?page=${pagingVO.curentPageNo+1}&gradeid=${gradeIndex}&teleType=${teleType}&campusid=${campusIndex}">${pagingVO.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/teacher/paidNotStudent?page=${pagingVO.curentPageNo+2}&gradeid=${gradeIndex}&teleType=${teleType}&campusid=${campusIndex}">${pagingVO.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/teacher/paidNotStudent?page=${pagingVO.curentPageNo+3}&gradeid=${gradeIndex}&teleType=${teleType}&campusid=${campusIndex}">${pagingVO.curentPageNo+3}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/teacher/paidNotStudent?page=${pagingVO.curentPageNo+4}&gradeid=${gradeIndex}&teleType=${teleType}&campusid=${campusIndex}">${pagingVO.curentPageNo+4}</a>
                                    </li>
                                </c:if>

                                <c:if test="${pagingVO.curentPageNo <pagingVO.totalCount}">
                                    <li><a href="/teacher/paidNotStudent?page=${pagingVO.nextPageNo}&gradeid=${gradeIndex}&teleType=${teleType}&campusid=${campusIndex}">下一页&raquo;</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo >=pagingVO.totalCount}">
                                    <li><a href="/teacher/paidNotStudent?page=${pagingVO.totalCount}&gradeid=${gradeIndex}&teleType=${teleType}&campusid=${campusIndex}">下一页&raquo;</a></li>
                                </c:if>
                                <li><a href="/teacher/paidNotStudent?page=${pagingVO.totalCount}&gradeid=${gradeIndex}&teleType=${teleType}&campusid=${campusIndex}">尾页</a></li>

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
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script src="https://cdn.bootcss.com/FileSaver.js/2014-11-29/FileSaver.min.js"></script>
<script src="https://cdn.bootcss.com/xlsx/0.11.3/xlsx.full.min.js"></script>
<script type="text/javascript">
    $("#nav4").addClass("active");

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
    };

    if (${pagingVO.curentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled");
        $(".pagination li:nth-child(2)").addClass("disabled");
    };
    </c:if>

    function jumpPage(){
        var page = $("#toPage").val();
        if (page==''){return;}
        if(page<=${pagingVO.totalCount}){
            window.location.href= "/teacher/paidNotStudent?page=" + page+ "&gradeid=" + ${gradeIndex}+ "&teleType=" + ${teleType} + "&campusid=" + ${campusIndex};
        }
    }



    function ToTableOne(str) {
        var a=btoa(str);
        window.open('/teacher/editTableOne?encodeID='+a);
    }
</script>

</html>

