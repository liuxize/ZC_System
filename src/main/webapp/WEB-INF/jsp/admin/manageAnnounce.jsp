<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 25/03/2018
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>公告管理</title>
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
                        <h1 class="col-md-5">公告管理</h1>

                        <button type="button" class="btn btn-success col-md-2 " data-toggle="modal"
                                id="addbutton"  style="margin-top: 20px; float:right"
                                data-target="#addNoteDic">新建公告
                            <span class="glyphicon glyphicon-plus"></span>
                        </button>


                        <div class="modal fade" id="addNoteDic" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                            <div class="modal-dialog modal-lg"> <!-- modal-lg 放大版-->
                                <div class="modal-content">
                                    <form class="form-horizontal" role="form" action="/admin/addAnnounce" method="post">
                                        <div class="modal-header">

                                            <div class="form-group">
                                                <label class="col-sm-1 control-label">标题</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="anntitle"
                                                            required="required">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-1 control-label">校区</label>
                                                <div class="col-sm-10">
                                                    <select class="selectpicker show-tick form-control" data-live-search="true" name="campusid">
                                                        <c:forEach items="${campusList}" var="item">
                                                            <option value="${item.campusid}">${item.campusname}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="modal-body">
                                            <textarea style="width: 100%; border: none; font-size: 20px" rows="25" name="anncon"  placeholder="内容"></textarea>
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

                    </div>
                </div>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th >序号</th>
                        <th >名称</th>
                        <th>内容摘要</th>
                        <th >校区</th>
                        <th >操作</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${announceList}" var="item" varStatus="status">
                        <%--                        --%>
                        <C:if test="${item.isread==0}">
                            <tr bgcolor="#e3ffdc;">
                                <th style="width:5%">${status.index + 1}</th>
                                <th style="width:20%">${item.anntitle}</th>

                                <th style="width:35%">
                                    <div style="width: 400px; white-space:nowrap;text-overflow:ellipsis;overflow:hidden;"> ${item.anncon}</div>
                                </th>
                                <th style="width:10%">${item.campusname}</th>
                                <th style="width:30%">

                                    <button type="button" class="btn btn-primary  btn-xs" id="editbutton1"
                                            data-toggle="modal" onclick="editAnnounce('${item.annid}', '${item.anntitle}', '${item.campusid}')" data-target="#EditAnnounce">
                                        编辑内容
                                    </button>
                                    &nbsp;&nbsp;
                                    <button type="button" class="btn btn-danger  btn-xs"
                                            onclick="deleteAnnounce('${item.annid}')">
                                        删除公告
                                    </button>
                                    &nbsp; &nbsp;
                                    <button type="button" class="btn btn-info  btn-xs"
                                            onclick="openAnnounce('${item.annid}')">
                                        显示公告
                                    </button>
                                    &nbsp; &nbsp;
                                    <button type="button" class="btn btn-default  btn-xs disabled">
                                        隐藏公告
                                    </button>

                                </th>
                            </tr>
                        </C:if>
                        <C:if test="${item.isread==1}">
                            <tr>
                                <th style="width:5%">${status.index + 1}</th>
                                <th style="width:20%">${item.anntitle}</th>

                                <th style="width:35%">
                                    <div style="width: 400px; white-space:nowrap;text-overflow:ellipsis;overflow:hidden;"> ${item.anncon}</div>
                                </th>
                                <th style="width:10%">${item.campusname}</th>
                                <th style="width:30%">


                                    <button type="button" class="btn btn-primary  btn-xs" id="editbutton2"
                                            data-toggle="modal" onclick="editAnnounce('${item.annid}', '${item.anntitle}', '${item.campusid}')" data-target="#EditAnnounce">
                                        编辑内容
                                    </button>
                                    &nbsp;&nbsp;
                                    <button type="button" class="btn btn-danger  btn-xs"
                                            onclick="deleteAnnounce('${item.annid}')">
                                        删除公告
                                    </button>
                                    &nbsp; &nbsp;
                                    <button type="button" class="btn btn-info  btn-xs disabled">
                                        显示公告
                                    </button>
                                    &nbsp; &nbsp;
                                    <button type="button" class="btn btn-default  btn-xs"
                                            onclick="closeAnnounce('${item.annid}')">
                                        隐藏公告
                                    </button>

                                </th>
                            </tr>
                        </C:if>
                    </c:forEach>


                    </tbody>
                </table>

            </div>
            <div class="modal fade" id="EditAnnounce" tabindex="-1" role="dialog"
                 aria-labelledby="myModalLabel11" aria-hidden="true" style="z-index:9999">
                <div class="modal-dialog modal-lg"> <!-- modal-lg 放大版-->
                    <div class="modal-content">
                        <form class="form-horizontal" role="form" action="/admin/editAnnounce" method="post">
                            <div class="modal-header">
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">标题</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="anntitle" id="annTitle" required="required" value=" " >
                                        <input type="hidden" id="annID"  style="height: 5px"; name="annid" value=" ">
                                    </div>
                                </div>



                                <div class="form-group">
                                    <label class="col-sm-1 control-label">校区</label>
                                    <div class="col-sm-10">
                                        <select class="selectpicker show-tick form-control" data-live-search="true" name="campusid" id="campusID" value=" ">
                                            <c:forEach items="${campusList}" var="item">
                                                <option value="${item.campusid}">${item.campusname}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>


                            </div>
                            <div class="modal-body">
                                <textarea style="width: 100%; font-size: 18px; border: none; white-space:pre-line" rows="25" name="anncon" id="annCon" value=" "></textarea>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button class="btn btn-default" type="submit">保存</button>
                            </div>
                        </form>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
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

    $("#nav21").addClass("active");

    function deleteAnnounce(annid) {
        var msg = "您确定要删除吗";
        if (confirm(msg) == true) {
            window.location.href="/admin/deleteAnnounce?annid="+ annid;
            return true;
        } else {
            return false;
        }
    }

    function openAnnounce(annid) {
        window.location.href="/admin/openAnnounce?annid="+ annid;
    }

    function closeAnnounce(annid) {
        window.location.href="/admin/closeAnnounce?annid="+ annid;
    }

    $("#EditAnnounce").modal("hide");
    function editAnnounce(annid, anntitle, campusid) {
        $("#annID").val(annid);
        $("#annTitle").val(anntitle);
        $('#campusID').selectpicker('val',campusid);


        $.ajax({
            type: "POST",   //http请求方式
            url: "/admin/findAnnounce",//发送给服务器的url
            data: "announceid="+ annid, //发送给服务器的参数
            dataType: "text",  //告诉JQUERY返回的数据格式(注意此处数据格式一定要与提交的controller返回的数据格式一致,不然不会调用回调函数complete)
            //scriptCharset: 'utf-8',
            complete: function (msg) {
                var anncon = msg.responseText;
                // alert( );
                $("#annCon").val(anncon);
                //document.getElementById("context").innerHTML = result;
            }
        });


    }


</script>
</html>

