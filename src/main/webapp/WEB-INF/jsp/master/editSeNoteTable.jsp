<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/3/5
  Time: 下午8:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                        <h1 class="col-md-5">记事表格</h1>
                        <button class="btn btn-success col-md-1" style="margin-top: 20px; float:right"
                                onClick="location.href='/master/addSeNoteTable?dicid=${dicid}&currentpage=${pagingVO.curentPageNo}'">
                            添加信息
                            <sapn class="glyphicon glyphicon-plus"/>
                        </button>
                        <%--<button class="btn btn-warning col-md-1" style="margin-top: 20px; float:right"--%>
                                <%--onClick="saveAsExcelFile()">--%>
                            <%--打印信息--%>
                            <%--<sapn class="glyphicon glyphicon-align-justify"/>--%>
                        <%--</button>--%>
                    </div>
                </div>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width:4%">序号</th>
                        <th style="width: 8%">姓名</th>
                        <th style="width: 10%;">学校</th>
                        <th style="width:5%">年级</th>
                        <th style="width: 10%">课程</th>

                        <th>备注</th>
                        <th style="width: 10%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${noteTableList}" var="item" varStatus="status">
                        <tr>
                            <td>${(pagingVO.curentPageNo-1)*(pagingVO.pageSize)+status.index + 1}</td>
                            <td>${item.stuname}</td>
                            <td>${item.stuschool}</td>
                            <td>${item.stugrade}</td>
                            <td>${item.stucourse}</td>

                            <td>${item.remarktext}</td>
                            <td>
                                <button type="button" class="btn btn-danger  btn-xs"
                                        onclick="remove('${item.noteid}','${item.dicid}','${pagingVO.curentPageNo}')">删除
                                </button>

                                <button type="button" class="btn btn-info  btn-xs"
                                        onclick="location.href='/master/modifySeNoteTable?noteID=${item.noteid}&dicid=${item.dicid}&page=${pagingVO.curentPageNo}'">修改
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
                                <li><a href="/master/editSeNoteTable?dicid=${dicid}&page=1">首页</a></li>
                                <c:if test="${pagingVO.curentPageNo <= 1}">
                                    <li><a href="/master/editSeNoteTable?dicid=${dicid}&page=1">&laquo;上一页</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo > 1}">
                                    <li><a href="/master/editSeNoteTable?dicid=${dicid}&page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
                                </c:if>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/master/editSeNoteTable?dicid=${dicid}&page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/master/editSeNoteTable?dicid=${dicid}&page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/master/editSeNoteTable?dicid=${dicid}&page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/master/editSeNoteTable?dicid=${dicid}&page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a>
                                    </li>
                                </c:if>

                                <c:if test="${pagingVO.curentPageNo <pagingVO.totalCount}">
                                    <li><a href="/master/editSeNoteTable?dicid=${dicid}&page=${pagingVO.nextPageNo}">下一页&raquo;</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo >=pagingVO.totalCount}">
                                    <li><a href="/master/editSeNoteTable?dicid=${dicid}&page=${pagingVO.totalCount}">下一页&raquo;</a></li>
                                </c:if>
                                <li><a href="/master/editSeNoteTable?dicid=${dicid}&page=${pagingVO.totalCount}">尾页</a></li>
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
    $("#nav20").addClass("active");

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
            window.location.href="/master/editSeNoteTable?dicid="+ ${dicid}+ "&page=" + page;
        }
    }

    <%--function s2ab(s) {--%>
        <%--const buf = new ArrayBuffer(s.length);--%>
        <%--const view = new Uint8Array(buf);--%>
        <%--for (var i = 0; i !== s.length; ++i) {--%>
            <%--view[i] = s.charCodeAt(i) & 0xFF;--%>
        <%--};--%>
        <%--return buf;--%>
    <%--}--%>
    <%--function saveAsExcelFile() {--%>

        <%--var data = new Array();   //先声明一维--%>
        <%--for(var k=0;k<=${noteTableList.size()};k++){        //一维长度为i,i为变量，可以根据实际情况改变--%>
            <%--data[k]=new Array();    //声明二维，每一个一维数组里面的一个元素都是一个数组--%>

        <%--}--%>
        <%--data[0] =  ["序号", "姓名", "学校", "年级", "课程", "备注"];--%>

        <%--<c:forEach items="${allNoteTableList}" var="item" varStatus="status" >--%>
        <%--data[${status.index+1}]=[ ${status.index+1},'${item.stuname}','${item.stuschool}','${item.stugrade}','${item.stucourse}', '${item.remarktext}'];--%>
        <%--</c:forEach>--%>

        <%--var wopts = { bookType:'xlsx', type:'binary' };--%>
        <%--var fileName = "download.xlsx";--%>
        <%--const ws = XLSX.utils.aoa_to_sheet(data);--%>
        <%--const wb = XLSX.utils.book_new();--%>
        <%--XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');--%>
        <%--const wbout = XLSX.write(wb, wopts);--%>
        <%--saveAs(new Blob([s2ab(wbout)]), fileName); // 保存为文件--%>
    <%--}--%>
    function remove(noteID,dicID,page) {
        var msg = "您确定要删除吗";
        if (confirm(msg) == true) {
            window.location.href="/master/removeSeNoteTable?noteID=" + noteID + "&dicID="+ dicID + "&currentPage=" +page;
            return true;
        } else {
            return false;
        }
    }
</script>
</html>




