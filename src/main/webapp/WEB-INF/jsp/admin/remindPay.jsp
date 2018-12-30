<%--
  Created by IntelliJ IDEA.
  User: liuxize
  Date: 2018/3/24
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>缴费提醒</title>
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
                        <h1 class="col-md-5">缴费提醒</h1>
                        <button class="btn btn-warning col-md-2" style="margin-top: 20px; float:right"
                                onClick="saveAsExcelFile()">
                            打印
                            <sapn class="glyphicon glyphicon-align-justify"/>
                        </button>
                    </div>
                </div>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width:5%">序号</th>
                        <th>姓名</th>
                        <th>学校</th>
                        <th>年级</th>
                        <th colspan="3">电话</th>
                        <th style="width: 15%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${allStuList}" var="item" varStatus="status">
                        <tr>
                            <td>${(pagingVO.curentPageNo-1)*(pagingVO.pageSize)+status.index + 1}</td>
                            <td>${item.stuname}</td>
                            <td>${item.school}</td>
                            <td>${item.gradename}</td>
                            <td style="border-right-color:transparent; width: 17%">学生：${item.stutel}</td>
                            <td style="border-right-color:transparent; width: 17%">父亲：${item.fathertel}</td>
                            <td style="width: 17%">母亲：${item.mothertel}</td>
                            <td>
                                <button type="button" class="btn btn-primary btn-xs"
                                        onClick="ToTableOne(${item.stuid})">查看信息
                                </button>

                                <button type="button" class="btn btn-danger  btn-xs"
                                        onclick="remove('${item.stuid}','${pagingVO.curentPageNo}')">删除
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
                                <li><a href="/admin/remindPay?page=1">首页</a></li>
                                <c:if test="${pagingVO.curentPageNo <= 1}">
                                    <li><a href="/admin/remindPay?page=1">&laquo;上一页</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo > 1}">
                                    <li><a href="/admin/remindPay?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
                                </c:if>

                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/remindPay?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/remindPay?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/remindPay?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/remindPay?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo <pagingVO.totalCount}">
                                    <li><a href="/admin/remindPay?page=${pagingVO.nextPageNo}">下一页&raquo;</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo >=pagingVO.totalCount}">
                                    <li><a href="/admin/remindPay?page=${pagingVO.totalCount}">下一页&raquo;</a></li>
                                </c:if>
                                <li><a href="/admin/remindPay?page=${pagingVO.totalCount}">尾页</a></li>

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
    $("#nav11").addClass("active");

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
            window.location.href="/admin/remindPay?page=" + page;
        }
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
        $(".pagination li:last-child").addClass("disabled")
    }
    ;

    if (${pagingVO.curentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    }
    ;
    </c:if>

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
        for(var k=0;k<=${stuCustomList.size()};k++){        //一维长度为i,i为变量，可以根据实际情况改变
            data[k]=new Array();    //声明二维，每一个一维数组里面的一个元素都是一个数组

        }
        data[0] =  ["序号", "姓名", "学校", "年级", "电话", "备注"];

        <c:forEach items="${stuCustomList}" var="item" varStatus="status" >
        data[${status.index+1}]=[ ${status.index+1},'${item.stuname}','${item.school}','${item.gradename}',
            "学生:"+'${item.stutel}'+"  父亲:"+'${item.fathertel}'+"  母亲:"+'${item.mothertel}'];
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

    function remove(id,page) {
        var msg = "您确定要删除吗";
        if (confirm(msg) == true) {
            window.location.href="/admin/removeRemindPay?id=" + id + "&currentPage=" +page;

            return true;
        } else {
            return false;
        }
    }
</script>
</html>




