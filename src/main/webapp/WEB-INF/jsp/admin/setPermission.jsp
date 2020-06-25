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
<%--                    <form class="form-horizontal" role="form" action="/admin/setPermission" id="EditPermision" method="post" style="text-align: center;font-size: 25px">--%>
                    <form  role="form" onsubmit="return false" action="#" id="EditPermision"  style="text-align: center;font-size: 25px">
                        <input type="hidden" id="username" name="username" value="${username}">
                        <h3 style="text-align: center;"> 校区权限 </h3>
                        <div>
                            <c:forEach items="${campusList}" var="item">
                                <input type="checkbox" style="zoom:180%;" name="campus"
                                       value="${item.campusid+500}">${item.campusname}
                                <C:if test="${(item.campusid) % 4 == 0}">
                                    <br>
                                </C:if>
                            </c:forEach>
                        </div>

                        <h3 style="text-align: center;"> 年级权限 </h3>

                        <div>
                            <c:forEach items="${gradeList}" var="item">
                                <input type="checkbox" style="zoom:180%;" name="grade"
                                       value="${item.gradeid}">${item.gradename}
                                <C:if test="${(item.gradeid+1) % 4 == 0}">
                                    <br>
                                </C:if>
                            </c:forEach>
                        </div>

                        <h3 style="text-align: center;"> 缴费权限 </h3>

                        <div>
                            <C:if test="${num==0}">
                                <input type="checkbox" style="zoom:180%;" checked="checked"
                                       name="pay"
                                       value="-2">已缴费
                                &nbsp; &nbsp;
                                <input type="checkbox" style="zoom:180%;"
                                       name="unpay"
                                       value="-2">未缴费
                            </C:if>

                            <C:if test="${num==1}">
                                <input type="checkbox" style="zoom:180%;" name="pay" value="-2">已缴费
                                &nbsp; &nbsp;
                                <input type="checkbox" style="zoom:180%;"  name="unpay" value="-2" checked="checked">未缴费
                            </C:if>

                            <C:if test="${num==2}">
                                <input type="checkbox" style="zoom:180%;" name="pay" value="-2" checked="checked">已缴费
                                &nbsp; &nbsp;
                                <input type="checkbox" style="zoom:180%;"  name="unpay" value="-2" checked="checked">未缴费
                            </C:if>

                        </div>





                        <div style="height: 20px"></div>
                        <div class="form-group" style="text-align: center">
<%--                            <button class="btn btn-default" type="submit">提交</button>--%>
                            <button class="btn btn-default" onclick="setPermission();">提交</button>
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

    function setPermission() {
        var id = document.getElementsByName('grade');
        var value = new Array();
        value.push('100000'); //防止传入空数组
        for(var i = 0; i < id.length; i++){
            if(id[i].checked)
                value.push(id[i].value);
        }

        var campusid = document.getElementsByName('campus');
        var campuslst = new Array();
        campuslst.push('100000'); //防止传入空数组
        for(var j = 0; j < campusid.length; j++){
            if(campusid[j].checked)
                campuslst.push(campusid[j].value);
        }
        //alert(campuslst)

        var pay_val = '0';
        var unpay_val = '0';
        var pays = document.getElementsByName('pay');
        if(pays[0].checked)
            pay_val = $("input[name='pay']").val()
            //alert($("input[name='pay']").val());

        var unpays = document.getElementsByName('unpay');
        if(unpays[0].checked)
            unpay_val = $("input[name='unpay']").val()
            //alert($("input[name='unpay']").val());

        var username = document.getElementById("username").value;
        var data1 = JSON.stringify({'permission': value.toString(),"pay_val":pay_val,"unpay_val":unpay_val, "username":username, "campuslst": campuslst.toString()});

        //alert(data1)



        $.ajax({
            type : "POST",
            url : "/admin/setPermission1",
            dataType : 'json',
                //;charset=UTF-8",
            //contentType:'application/json; charset=utf-8',
            data : JSON.stringify(data1),
            contentType: "application/json;charset=utf-8",
            success: function(result){
            }

        })



        // $.ajax({
        //     type: "POST",
        //     url: "/admin/setPermission",
        //     data: JSON.stringify(data),
        //     contentType: "application/json",  //发送到后台的数据格式
        //     data_type: "json",  // 接收后端返回的数据格式
        //     success: function (data) {
        //         // if (resp.error == "OK") {
        //         //     console.log('分享成功')
        //         // } else {
        //         //     console.log('分享失败')
        //         // }
        //     }
        // })
    };


    $(document).ready(function () {
        var gradeAuth = ${permisionList};
        for (var i = 0; i < gradeAuth.length; i++) {
            $("input[value='" + gradeAuth[i] + "']").attr("checked", "checked");
        }

        var campusAuth = ${campusAuthlist};
        for (var i = 0; i < campusAuth.length; i++) {
            var campusAuthid = campusAuth[i]+500
            $("input[value='" + campusAuthid + "']").attr("checked", "checked");
        }



    })
</script>
</html>
