<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/4/7
  Time: 11:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>权限设置</title>
    <link rel="shortcut icon" type="image/x-icon" href="/images/logo-dt.png" media="screen">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 顶栏 -->

<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">${username}的权限</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/admin/setPermission" id="EditPermision" method="post" style="text-align: center;font-size: 25px">
                                    <input type="hidden" name="username" value="${username}">
                                    <div>
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade0"
                                               value="${gradeList[0].gradeid}">${gradeList[0].gradename}
                                        &nbsp; &nbsp;
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade1"
                                               value="${gradeList[1].gradeid}">${gradeList[1].gradename}
                                        &nbsp; &nbsp;
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade2"
                                               value="${gradeList[2].gradeid}">${gradeList[2].gradename}
                                    </div>

                                    <div>
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade3"
                                               value="${gradeList[3].gradeid}">${gradeList[3].gradename}
                                        &nbsp; &nbsp;
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade4"
                                               value="${gradeList[4].gradeid}">${gradeList[4].gradename}
                                        &nbsp; &nbsp;
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade5"
                                               value="${gradeList[5].gradeid}">${gradeList[5].gradename}

                                    </div>

                                    <div>

                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade6"
                                               value="${gradeList[6].gradeid}">${gradeList[6].gradename}
                                        &nbsp; &nbsp;
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade7"
                                               value="${gradeList[7].gradeid}">${gradeList[7].gradename}
                                        &nbsp; &nbsp;
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade8"
                                               value="${gradeList[8].gradeid}">${gradeList[8].gradename}
                                    </div>

                                    <div>

                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade9"
                                               value="${gradeList[9].gradeid}">${gradeList[9].gradename}
                                        &nbsp; &nbsp;
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade10"
                                               value="${gradeList[10].gradeid}">${gradeList[10].gradename}
                                        &nbsp; &nbsp;
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade11"
                                               value="${gradeList[11].gradeid}">${gradeList[11].gradename}

                                    </div>
                                    <div>
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade12"
                                               value="${gradeList[12].gradeid}">${gradeList[12].gradename}
                                        &nbsp; &nbsp;
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade13"
                                               value="${gradeList[13].gradeid}">${gradeList[13].gradename}
                                        &nbsp; &nbsp;
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade14"
                                               value="${gradeList[14].gradeid}">${gradeList[14].gradename}

                                    </div>
                                    <div>
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade15"
                                               value="${gradeList[15].gradeid}">${gradeList[15].gradename}
                                        &nbsp; &nbsp;
                                        <input type="checkbox" style="zoom:180%;"
                                               name="grade16"
                                               value="${gradeList[16].gradeid}">${gradeList[16].gradename}
                                        &nbsp; &nbsp;
                                        <input type="checkbox" style="zoom:180%;"
                                                name="grade17"
                                                value="${gradeList[17].gradeid}">${gradeList[17].gradename}
                                    </div>

                        <div>
                            <C:if test="${num==0}">
                                <input type="checkbox" style="zoom:180%;" checked="checked"
                                       name="pay"
                                       value="-1">已缴费
                                &nbsp; &nbsp;
                                <input type="checkbox" style="zoom:180%;"
                                       name="unpay"
                                       value="-2">未缴费
                            </C:if>

                            <C:if test="${num==1}">
                                <input type="checkbox" style="zoom:180%;" name="pay" value="-1">已缴费
                                &nbsp; &nbsp;
                                <input type="checkbox" style="zoom:180%;"  name="unpay" value="-2" checked="checked">未缴费
                            </C:if>

                            <C:if test="${num==2}">
                                <input type="checkbox" style="zoom:180%;" name="pay" value="-1" checked="checked">已缴费
                                &nbsp; &nbsp;
                                <input type="checkbox" style="zoom:180%;"  name="unpay" value="-2" checked="checked">未缴费
                            </C:if>

                        </div>





                        <div style="height: 20px"></div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                        </div>

                    </form>
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
    $("#nav li:nth-child(1)").addClass("active")

    $(document).ready(function () {
        var r = ${permisionList};
        for (var i = 0; i < r.length; i++) {

            $("input[value='" + r[i] + "']").attr("checked", "checked");
        }



    })
</script>
</html>
