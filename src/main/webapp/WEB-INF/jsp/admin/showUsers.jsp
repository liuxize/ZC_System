<%@ page import="java.util.List" %>

<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/2/12
  Time: 下午3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
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
<!-- 顶栏 -->


<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 class="col-md-5">登陆名单管理</h1>


                        <div class="btnc">
                            <button type="button" class="btn btn-success col-md-2"
                                    style=" margin-top: 20px; float:right" data-toggle="modal"
                                    data-target="#addEduText">添加用户
                                <span class="glyphicon glyphicon-plus"></span>
                            </button>
                        </div>
                        <!--按键结束-->
                        <!-- 模态框-->
                        <div class="modal fade" id="addEduText" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                <div class="modal-content">


                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 align="center" class="modal-title">添加用户信息</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" action="/admin/addUsers" id="editfrom"
                                              method="post">
                                            <div class="form-group">
                                                <label for="inputPassword3" class="col-sm-2 control-label">姓名</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputPassword3"
                                                           name="username" required="required" placeholder="请输入姓名">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputPassword4"
                                                           name="password" required="required" placeholder="请输入密码">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label for="inputbirth" class="col-sm-2 control-label">生日</label>
                                                <div class="col-sm-9">
                                                    <input type="Date" class="form-control" required="required" max="9999-12-31" id="inputbirth"
                                                           name="userbirth">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label for="inputPassword3" class="col-sm-2 control-label" name="role">职位</label>
                                                <div class="col-sm-9">
                                                    <select class="form-control" name="role" id="role">
<%--                                                            onChange="show()">--%>
                                                        <c:forEach items="${roleList}" var="item">
                                                            <option value="${item.roleid}">${item.permissions}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>


<%--                                            <div class="form-group" id="div" style="display: none"--%>
<%--                                                 onMouseout="hidden();">--%>
<%--                                                <label for="inputPassword3" class="col-sm-2 control-label">权限</label>--%>
<%--                                                <div class="col-sm-9">--%>
<%--                                                    <fieldset>--%>
<%--                                                        <div>--%>
<%--                                                            <input type="checkbox" style="font-size: 26px" name="grade0"--%>
<%--                                                                   value="${gradeList[0].gradeid}">${gradeList[0].gradename}--%>
<%--                                                        </div>--%>

<%--                                                        <div>--%>
<%--                                                            <input type="checkbox" style="font-size: 26px" name="grade1"--%>
<%--                                                                   value="${gradeList[1].gradeid}">${gradeList[1].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                            <input type="checkbox" style="font-size: 26px" name="grade2"--%>
<%--                                                                   value="${gradeList[2].gradeid}">${gradeList[2].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                            <input type="checkbox" style="font-size: 26px" name="grade3"--%>
<%--                                                                   value="${gradeList[3].gradeid}">${gradeList[3].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                            <input type="checkbox" style="font-size: 26px" name="grade4"--%>
<%--                                                                   value="${gradeList[4].gradeid}">${gradeList[4].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                            <input type="checkbox" style="font-size: 26px" name="grade5"--%>
<%--                                                                   value="${gradeList[5].gradeid}">${gradeList[5].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                            <input type="checkbox" style="font-size: 26px" name="grade6"--%>
<%--                                                                   value="${gradeList[6].gradeid}">${gradeList[6].gradename}--%>
<%--                                                        </div>--%>

<%--                                                        <div>--%>
<%--                                                            <input type="checkbox" style="font-size: 26px" name="grade7"--%>
<%--                                                                   value="${gradeList[7].gradeid}">${gradeList[7].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                            <input type="checkbox" style="font-size: 26px" name="grade8"--%>
<%--                                                                   value="${gradeList[8].gradeid}">${gradeList[8].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                            <input type="checkbox" style="font-size: 26px" name="grade9"--%>
<%--                                                                   value="${gradeList[9].gradeid}">${gradeList[9].gradename}--%>
<%--                                                        </div>--%>

<%--                                                        <div>--%>
<%--                                                            <input type="checkbox" style="font-size: 26px"--%>
<%--                                                                   name="grade10"--%>
<%--                                                                   value="${gradeList[10].gradeid}">${gradeList[10].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                            <input type="checkbox" style="font-size: 26px"--%>
<%--                                                                   name="grade11"--%>
<%--                                                                   value="${gradeList[11].gradeid}">${gradeList[11].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                            <input type="checkbox" style="font-size: 26px"--%>
<%--                                                                   name="grade12"--%>
<%--                                                                   value="${gradeList[12].gradeid}">${gradeList[12].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                        </div>--%>
<%--                                                        <div>--%>
<%--                                                            <input type="checkbox" style="font-size: 26px"--%>
<%--                                                                   name="grade13"--%>
<%--                                                                   value="${gradeList[13].gradeid}">${gradeList[13].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                            <input type="checkbox" style="font-size: 26px"--%>
<%--                                                                   name="grade14"--%>
<%--                                                                   value="${gradeList[14].gradeid}">${gradeList[14].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                            <input type="checkbox" style="font-size: 26px"--%>
<%--                                                                   name="grade15"--%>
<%--                                                                   value="${gradeList[15].gradeid}">${gradeList[15].gradename}--%>
<%--                                                            &nbsp; &nbsp;--%>
<%--                                                            <input type="checkbox" style="font-size: 26px"--%>
<%--                                                                   name="grade16"--%>
<%--                                                                   value="${gradeList[16].gradeid}">${gradeList[16].gradename}--%>
<%--                                                        </div>--%>
<%--                                                        <div><input type="checkbox" style="font-size: 26px"--%>
<%--                                                                    name="grade17"--%>
<%--                                                                    value="${gradeList[17].gradeid}">${gradeList[17].gradename}--%>
<%--                                                        </div>--%>

<%--                                                        <div>--%>
<%--                                                            <input type="checkbox" style="font-size: 26px"--%>
<%--                                                                   name="pay"--%>
<%--                                                                   value="-2">已缴费--%>
<%--                                                            <input type="checkbox" style="font-size: 26px"--%>
<%--                                                                    name="unpay"--%>
<%--                                                                    value="-1">未缴费--%>

<%--                                                        </div>--%>

<%--                                                    </fieldset>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>


                                            <div class="form-group" style="text-align: center">
                                                <button class="btn btn-default" type="submit">提交</button>
                                                <button class="btn btn-default" type="reset" onclick="hide()">重置
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                            </div><!-- /.modal -->
                        </div>
                        <!-- 模态框-->
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 10%">编号</th>
                        <th style="width: 10%">账户名</th>
                        <th style="width: 15%;">密码</th>
                        <th style="width: 15%;">生日</th>
                        <th style="width: 10%;">职称</th>
                        <th style="width: 40%">操作</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${usersList}" var="item"  varStatus="status">
                        <tr>
                            <td>${(pagingVO.curentPageNo-1)*(pagingVO.pageSize)+status.index + 1}</td>
                            <td>${item.username}</td>
                            <td>${item.password}</td>
                            <td><fmt:formatDate value="${item.userbirth}" dateStyle="medium"/></td>
                            <td>
                                <c:if test="${item.role==0}">
                                    管理员
                                </c:if>
                                <c:if test="${item.role==1}">
                                    校长
                                </c:if>
                                <c:if test="${item.role==2}">
                                    教师
                                </c:if>
                                <c:if test="${item.role==3}">
                                    负责人
                                </c:if>
                            </td>
                            <td>
                                <input type="hidden" id="teacherName" name="teacherName"
                                       value="${item.username}">
                                <input type="hidden" id="page" name="page"
                                       value="${pagingVO.curentPageNo}">

                                <button type="button" class="btn btn-default btn-xs btn-warning" data-toggle="modal"
                                        id="editbutton"
                                        onclick="Values('${item.username}')"
                                        data-target="#EditPassword">修改密码
                                </button>
                                &nbsp; &nbsp;

                                <c:if test="${item.role==0}">
                                    <button type="button"  class="btn btn-default btn-info btn-xs "
                                            onclick="confirmd()">更新年级
                                    </button>
                                    &nbsp; &nbsp;
                                </c:if>

                                <c:if test="${item.role!=0}">
                                    <button type="button"  class="btn btn-default btn-info btn-xs "
                                            onClick="  window.open('/admin/showUserNoteDic?username=${item.username}')">班级管理
                                    </button>
                                    &nbsp; &nbsp;
                                    <button type="button"  class="btn btn-default btn-warning btn-xs "
                                            onClick="  window.open('/admin/showUserSeNoteDic?username=${item.username}')">招生管理
                                    </button>
                                    &nbsp; &nbsp;
                                </c:if>

                                <c:if test="${item.role==1 || item.role==2 || item.role==3}">
                                    <button type="button"  class="btn btn-default btn-xs btn-info"
                                            onClick=" window.open('/admin/setPermission?username=${item.username}')">修改权限
                                    </button>
                                    &nbsp; &nbsp;
                                </c:if>


                                <c:if test="${item.role!=0}">
                                    <button type="button" class="btn btn-danger  btn-xs"
                                            onclick="deleteUser('${item.username}','${pagingVO.curentPageNo}')">
                                        删除用户
                                    </button>
                                </c:if>
                                <!--弹出框-->
                            </td>
                        </tr>
                    </c:forEach>
                    <!-- 修改用户密码-->
                    <div class="modal fade" id="EditPassword" tabindex="-1" role="dialog"
                         aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                        <div class="modal-dialog"> <!-- modal-lg 放大版-->
                            <div class="modal-content">


                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 align="center" class="modal-title">请输入新密码</h4>
                                    </div>
                                <form role="form" action="/admin/editPassword" method="post">
                                    <div class="modal-body">

                                        <input type="text" class="form-control" name="newPassword" required="required"
                                               placeholder="请输入">
                                        <input type="hidden" id="editname" name="editname" value=" ">
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

                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="/admin/showUsers?page=1">首页</a></li>
                                <c:if test="${pagingVO.curentPageNo <= 1}">
                                    <li><a href="/admin/showUsers?page=1">&laquo;上一页</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo > 1}">
                                    <li><a href="/admin/showUsers?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
                                </c:if>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showUsers?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showUsers?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showUsers?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showUsers?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo <pagingVO.totalCount}">
                                    <li><a href="/admin/showUsers?page=${pagingVO.nextPageNo}">下一页&raquo;</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo >=pagingVO.totalCount}">
                                    <li><a href="/admin/showUsers?page=${pagingVO.totalCount}">下一页&raquo;</a></li>
                                </c:if>
                                <li><a href="/admin/showUsers?page=${pagingVO.totalCount}">尾页</a></li>

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
    $("#nav6").addClass("active");

    function show() {  //编辑权限
        if ($("#role").val() ==${0}) {
            document.getElementById("div").style.display = "none";
        }
        else{
            document.getElementById("div").style.display = "";
        }
    }

    function hide() {
        document.getElementById("div").style.display = "none";
    }



    $("#EditPassword").modal("hide"); 
    function Values(ID) { 
        $("#editname").val(ID); 
        $("#currentPage").val("${pagingVO.curentPageNo}"); 
    }

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
            window.location.href="/admin/showUsers?page="+ page;
        }
    }

    function confirmd() {
        var msg = "上次更新日期为"+'${lastDate}'+"您确定要更新年级吗";
        if (confirm(msg) == true) {
            window.location.href='/admin/updateStuGrade?currentPage=${pagingVO.curentPageNo}';
            alert("更新成功");
            return true;
        } else {
            return false;
        }
    }



    function deleteUser(username,page) {
        var msg = "您确定要删除吗";
        if (confirm(msg) == true) {
            window.location.href="/admin/removeUsers?username=" + username + "&currentPage=" +page;
            return true;
        } else {
            return false;
        }
    }

    $("#sub").click(function () {
        $("#form1").submit();
    });
</script>
</html>