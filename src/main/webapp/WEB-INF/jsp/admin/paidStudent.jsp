<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/3/2
  Time: 下午7:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>已缴费学员</title>
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
                    <div class="row ">

                        <h1 class="col-md-3 ">已缴费学员</h1>
                        <p>
                            <button class="btn btn-warning" style="margin: 30px 10px 10px 10px; float:right;"
                                    onClick="saveAsExcelFile()">
                                打印信息
                                <sapn class="glyphicon glyphicon-align-justify"/>
                            </button>
                        </p>



                        <form class=" bs-example bs-example-form " role="form"
                              style="margin: 20px 0 10px 10px; float:right;"
                              action="/admin/paidStudent" id="form1" method="post">

                            <div class="form-inline" style="float: right">

                                校区
                                <div class="form-group" style="width: 220px">

                                    <select class="selectpicker show-tick form-control" data-live-search="true" name="typeid" >
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
                                <div class="form-group">

                                    <select class="selectpicker show-tick form-control" data-live-search="true"
                                            name="gradeid" >
                                        <c:forEach items="${gradeList}" var="item">
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

                                科目
                                <div class="form-group" style="width: 100px">

                                    <select class="selectpicker show-tick form-control" data-live-search="true"
                                            name="subjectid">
                                        <c:forEach items="${subjectList}" var="item">
                                            <c:choose>
                                                <c:when test="${item.subjectid==subjectIndex}">
                                                    <option value="${item.subjectid}"
                                                            selected="selected">${item.subjectname}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${item.subjectid}">${item.subjectname}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>

                                类型
                                <div class="form-group" style="width: 100px">

                                    <select class="selectpicker show-tick form-control" data-live-search="true" name="typeid" >
                                        <c:forEach items="${classTypeList}" var="item">
                                            <c:choose>
                                                <c:when test="${item.typeid==typeIndex}">
                                                    <option value="${item.typeid}"
                                                            selected="selected">${item.typename}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${item.typeid}">${item.typename}</option>
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
                        <th style="width:5%">序号</th>
                        <th style="width: 7%">姓名</th>
                        <th style="width: 15%">学校</th>
                        <th style="width: 7%">状态</th>
                        <th style="width: 8%">科目</th>
                        <th style="width: 8%">类型</th>
                        <th style="width: 10%">时间</th>
                        <th>内容</th>
                        <th style="width: 8%">操作</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${stuList}" var="item" varStatus="status">
                        <tr>
                            <td>${(pagingVO.curentPageNo-1)*(pagingVO.pageSize)+status.index + 1}</td>
                            <td>${item.stuname}</td>
                            <td>${item.school}</td>
                            <td>${item.gradename}</td>
                            <td>${item.subjectname}</td>
                            <td>${item.typename}</td>
                            <td><fmt:formatDate value="${item.lessonend}" dateStyle="medium"/></td>
                            <td>${item.remark}</td>
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
                                <li><a href="/admin/paidStudent?page=1&gradeid=${gradeIndex}&subjectid=${subjectIndex}&typeid=${typeIndex}">首页</a>
                                <c:if test="${pagingVO.curentPageNo <= 1}">
                                    <li><a href="/admin/paidStudent?page=1&gradeid=${gradeIndex}&subjectid=${subjectIndex}&typeid=${typeIndex}">&laquo;上一页</a>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo > 1}">
                                    <li><a href="/admin/paidStudent?page=${pagingVO.upPageNo}&gradeid=${gradeIndex}&subjectid=${subjectIndex}&typeid=${typeIndex}">&laquo;上一页</a>
                                </c:if>

                                </li>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/paidStudent?page=${pagingVO.curentPageNo+1}&gradeid=${gradeIndex}&subjectid=${subjectIndex}&typeid=${typeIndex}">${pagingVO.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/paidStudent?page=${pagingVO.curentPageNo+2}&gradeid=${gradeIndex}&subjectid=${subjectIndex}&typeid=${typeIndex}">${pagingVO.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/paidStudent?page=${pagingVO.curentPageNo+3}&gradeid=${gradeIndex}&subjectid=${subjectIndex}&typeid=${typeIndex}">${pagingVO.curentPageNo+3}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/paidStudent?page=${pagingVO.curentPageNo+4}&gradeid=${gradeIndex}&subjectid=${subjectIndex}&typeid=${typeIndex}">${pagingVO.curentPageNo+4}</a>
                                    </li>
                                </c:if>

                                <c:if test="${pagingVO.curentPageNo <pagingVO.totalCount}">
                                    <li><a href="/admin/paidStudent?page=${pagingVO.nextPageNo}&gradeid=${gradeIndex}&subjectid=${subjectIndex}&typeid=${typeIndex}">下一页&raquo;</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo >=pagingVO.totalCount}">
                                    <li><a href="/admin/paidStudent?page=${pagingVO.totalCount}&gradeid=${gradeIndex}&subjectid=${subjectIndex}&typeid=${typeIndex}">下一页&raquo;</a></li>
                                </c:if>
                                <li><a href="/admin/paidStudent?page=${pagingVO.totalCount}&gradeid=${gradeIndex}&subjectid=${subjectIndex}&typeid=${typeIndex}">尾页&raquo;</a></li>
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
            window.location.href="/admin/paidStudent?page="+ page+ "&gradeid=" + ${gradeIndex} + "&subjectid=" + ${subjectIndex} + "&typeid=" + ${typeIndex};
        }
    }

    function s2ab(s) {
        const buf = new ArrayBuffer(s.length);
        const view = new Uint8Array(buf);
        for (var i = 0; i !== s.length; ++i) {
            view[i] = s.charCodeAt(i) & 0xFF;
        };
        return buf;
    }
    function saveAsExcelFile() {

        var data = new Array();   //先声明一维
        for(var k=0;k<=${allStuList.size()};k++){        //一维长度为i,i为变量，可以根据实际情况改变
            data[k]=new Array();    //声明二维，每一个一维数组里面的一个元素都是一个数组

        }
        data[0] =  ["序号", "姓名", "学校", "状态", "科目", "类型", "时间", "内容"];

        <c:forEach items="${allStuList}" var="item" varStatus="status" >
        data[${status.index+1}]=[ ${status.index+1},'${item.stuname}','${item.school}','${item.gradename}',
            '${item.subjectname}','${item.typename}','<fmt:formatDate value="${item.lessonend}" dateStyle="medium"/>','${item.remark}'];
        </c:forEach>

        var wopts = { bookType:'xlsx', type:'binary' };
        var fileName = "download.xlsx";
        const ws = XLSX.utils.aoa_to_sheet(data);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
        const wbout = XLSX.write(wb, wopts);
        saveAs(new Blob([s2ab(wbout)]), fileName); // 保存为文件
    }
    function ToTableOne(str) {
        var a=btoa(str);
        window.open('/admin/editTableOne?encodeID='+a);
    }

</script>
</html>
