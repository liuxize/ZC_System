<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/2/11
  Time: 下午7:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>公告</title>
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

    <style type="text/css">
        /* Custom Styles */
        ul.nav-tabs{
            width: 200px;
            margin-top: 20px;
            border-radius: 4px;
            border: 1px solid #ddd;
            box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
        }
        ul.nav-tabs li{
            margin: 0;
            border-top: 1px solid #ddd;
        }
        ul.nav-tabs li:first-child{
            border-top: none;
        }
        ul.nav-tabs li a{
            margin: 0;
            padding: 8px 16px;
            border-radius: 0;
        }
        /*ul.nav-tabs li.active a, ul.nav-tabs li.active a:hover{*/
        /*    color: #fff;*/
        /*    background: #0088cc;*/
        /*    border: 1px solid #0088cc;*/
        /*}*/
        ul.nav-tabs li:first-child a{
            border-radius: 4px 4px 0 0;
        }
        ul.nav-tabs li:last-child a{
            border-radius: 0 0 4px 4px;
        }
        ul.nav-tabs.affix{
            top: 30px; /* Set the top position of pinned element */
        }

    </style>
</head>
<body>

<div class="container" id="content">
    <div class="row">
        <jsp:include page="menucopy.jsp"></jsp:include>
        <div class="col-md-12" >
            <div class="panel panel-default" >
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">公告栏</h1>
                    </div>
                </div>
                <div class="panel-body" style="height: 80%">
                    <div class="row">
                        <div class="col-xs-3" id="myScrollspy">
                            <ul class="nav nav-tabs nav-stacked" id="myNav">
                                <c:forEach items="${announceList}" var="item">
                                    <li p="${item.annid}" q="${item.anntitle}"><a href="javascript:;">${item.anntitle}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="col-xs-9">
                            <h2 style="text-align: center;" id = annTitle>${firstTitle}</h2>
                            <div id = "context" style="font-size: 18px; white-space:pre-line">${firstCon}</div>
                        </div>
                    </div>

                </div>


            </div>
        </div>
    </div>


</div>


</body>
<script  type="text/javascript">
    $(document).ready(function () {
        $("#myNav").affix({
            offset: {
                top: 125
            }
        });
    });


    $("#myNav li").click(function () {

        $(this).siblings('li').removeClass('active');
        $(this).addClass('active');
        var announceid = $(this).attr("p");
        document.getElementById("annTitle").innerHTML = $(this).attr("q");
        $.ajax({
            type: "POST",   //http请求方式
            url: "/admin/findAnnounce",//发送给服务器的url
            data: "announceid="+ announceid, //发送给服务器的参数
            dataType: "text",  //告诉JQUERY返回的数据格式(注意此处数据格式一定要与提交的controller返回的数据格式一致,不然不会调用回调函数complete)
            //scriptCharset: 'utf-8',
            complete: function (msg) {
                var result = msg.responseText;
               // alert( );
                document.getElementById("context").innerHTML = result;
            }
        });

    });




    //$("ul#myNav").on("click","li",function(){

    // $(function() {
    //     $('#myNav li').click(function () {
    //         // alert($(this).attr("p"));
    //         $(this).siblings('li').removeClass('active');
    //         //
    //         //$('.active').removeClass('active');
    //         $(this).addClass('active');
    //
    //
    //     });
    // });
</script>
</html>
