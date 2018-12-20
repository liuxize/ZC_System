<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/4/7
  Time: 11:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>上传文件</title>
    <link rel="shortcut icon" type="image/x-icon" href="/images/logo-dt.png" media="screen">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/fileinput.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/fileinput.min.js"></script>
    <script src="/js/zh.min.js"></script>


</head>
<body >
<!-- 顶栏 -->

<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">上传文件</h1>
                    </div>
                </div>
                <div class="panel-body" >


                        <form class="form-inline"  role="form" style="height: 100px ; margin: 50px 0 0 20%;" >

                            <div class="form-group " style=" font-size: 18px">
                                导入学生信息 &nbsp; &nbsp;
                            </div>

                            <div class="form-group " style=" margin: 0 3% 0 0; ">
                                <input  id="excelFile"  name="excelFile" type="file" class="file" accept="application/vnd.ms-excel">
                            </div>

                            <div class="form-group" style=" margin: 0 3% 0 0; ">
                            <button class="btn btn-default" onclick="addTempMess();">导入</button>
                            </div>

                            <div class="form-group">
                                <button class="btn btn-default" onClick="saveAsExcelFile1()">下载导入日志</button>
                            </div>
                            <%--<div class="form-group">--%>
                                <%--<input id="showText" style="border:0"  readonly="readonly" value="">--%>
                            <%--</div>--%>
                        </form>


                        <form class="form-inline"  role="form" style="height: 100px ; margin: 0 0 0 20%;">

                            <div class="form-group " style=" font-size: 18px">
                                更新学生信息 &nbsp; &nbsp;
                            </div>

                            <div class="form-group " style=" margin: 0 3% 0 0; ">
                                <input  id="updateFile"  name="updateFile" type="file" class="file" accept="application/vnd.ms-excel">
                            </div>

                            <div class="form-group" style=" margin: 0 3% 0 0; ">
                                <button class="btn btn-default" onclick="addUpdateMess();">导入</button>
                            </div>

                            <div class="form-group">
                                <button class="btn btn-default" onClick="saveAsExcelFile();">下载更新日志</button>
                            </div>
                            <%--<div class="form-group">--%>
                            <%--<input id="showText" style="border:0"  readonly="readonly" value="">--%>
                            <%--</div>--%>

                        </form>

                    <%--<div class="form-group">--%>
                        <%--<button class="btn btn-default" onClick="getproess();">cookie</button>--%>
                    <%--</div>--%>

                    <div class="progress progress-striped active">
                        <div id="progressbar" class="progress-bar progress-bar-success" role="progressbar"
                              aria-valuemin="0" aria-valuemax="100">
                        </div>
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
    $("#nav li:nth-child(12)").addClass("active")
    var timerId;
    $(function(){
        //每隔0.5秒自动调用方法，实现进度条的实时更新
        setInterval(function(){
            getproess();
        },500)
        //timerId=window.setInterval(getForm,500);
    });


    $("#excelFile").fileinput({
        language: 'zh', //设置语言
        allowedFileExtensions: ['xls'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload: false, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : false, //是否显示预览
        showCaption: true,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        //initialPreviewAsData: true
    });


    function addUpdateMess() {

        var name = $("#updateFile").val();
        if (name == "" || name.length == 0) {
            alert("请选择上传文件!")
            return false;
        }
        var fileA = name.split("//");
        var fileB = fileA[fileA.length - 1].toLowerCase().split(".");
        var fileC = fileB[fileB.length - 1];
        if (fileC != "xls") {
            alert("请选择office03版本excel文件!")
            return false;
        }
        var formData = new FormData();
        formData.append("file",$("#updateFile")[0].files[0]);
        formData.append("name",name);

        if (confirm("确定进行数据上传吗？确定开始后，请勿进行其他操作并等待上传完毕。")) {

            $.ajax({
                type: 'POST',   //http请求方式
                url: '/admin/updateFile',//发送给服务器的url
                async: true,
                data: formData, //发送给服务器的参数
                // 告诉jQuery不要去处理发送的数据
                processData: false,
                // 告诉jQuery不要去设置Content-Type请求头
                contentType: false,
                beforeSend: function () {
                    console.log("正在进行，请稍候");
                    // 禁用按钮防止重复提交
                    //$("#submit").attr({ disabled: "disabled" });
                },
                success: function (responseStr) {
                    if (responseStr == "01") {
                        alert("导入成功");
                    } else {

                        alert("导入失败，已经导入"+responseStr+"条信息，请检查表格格式");
                    }
                },
                complete: function () {
                    $("#submit").removeAttr("disabled");
                }
            });
        }
    }
    $("#updateFile").fileinput({
        language: 'zh', //设置语言
        allowedFileExtensions: ['xls'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload: false, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : false, //是否显示预览
        showCaption: true,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        //initialPreviewAsData: true
    });

    function s2ab(s) {
        var buf = new ArrayBuffer(s.length);
        var view = new Uint8Array(buf);
        for (var i = 0; i !== s.length; ++i) {
            view[i] = s.charCodeAt(i) & 0xFF;
        };
        return buf;
    }
    function saveAsExcelFile1() {

        var data = new Array();   //先声明一维
        for(var k=0;k<=  ${excelLogSuccess.size()};k++){        //一维长度为i,i为变量，可以根据实际情况改变
            data[k]=new Array();    //声明二维，每一个一维数组里面的一个元素都是一个数组

        }
        data[0] =  ["序号", "核对号", "姓名"];
        <c:forEach items="${excelLogSuccess}" var="item" varStatus="status" >
        data[${status.index+1}]=[ ${status.index+1},'${item.excelid}','${item.stuname}'];
        </c:forEach>


        var dateFail=new Array();
        for(var k=0;k<=  ${excelLogFail.size()};k++){        //一维长度为i,i为变量，可以根据实际情况改变
            dateFail[k]=new Array();    //声明二维，每一个一维数组里面的一个元素都是一个数组

        }
        dateFail[0] = ["序号", "核对号", "姓名"];
        <c:forEach items="${excelLogFail}" var="item" varStatus="status" >
        dateFail[${status.index+1}]=[ ${status.index+1},'${item.excelid}','${item.stuname}'];
        </c:forEach>

        var wopts = { bookType:'xlsx', type:'binary' };
        var fileName = "导入日志.xlsx";
        var ws = XLSX.utils.aoa_to_sheet(data);
        var ws1 = XLSX.utils.aoa_to_sheet(dateFail);

        var wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, '导入成功')
        XLSX.utils.book_append_sheet(wb, ws1, '重名');
        var wbout = XLSX.write(wb, wopts);
        saveAs(new Blob([s2ab(wbout)]), fileName); // 保存为文件
    }

    function saveAsExcelFile() {

        var data = new Array();   //先声明一维
        for(var k=0;k<=  ${excelLogSuccess.size()};k++){        //一维长度为i,i为变量，可以根据实际情况改变
            data[k]=new Array();    //声明二维，每一个一维数组里面的一个元素都是一个数组

        }
        data[0] =  ["序号", "核对号", "姓名"];
        <c:forEach items="${excelLogSuccess}" var="item" varStatus="status" >
        data[${status.index+1}]=[ ${status.index+1},'${item.excelid}','${item.stuname}'];
        </c:forEach>


        var dateFail=new Array();
        for(var k=0;k<=  ${excelLogFail.size()};k++){        //一维长度为i,i为变量，可以根据实际情况改变
            dateFail[k]=new Array();    //声明二维，每一个一维数组里面的一个元素都是一个数组

        }
        dateFail[0] = ["序号", "核对号", "姓名"];
        <c:forEach items="${excelLogFail}" var="item" varStatus="status" >
        dateFail[${status.index+1}]=[ ${status.index+1},'${item.excelid}','${item.stuname}'];
        </c:forEach>

        var dateConfirm=new Array();
        for(var k=0;k<=  ${excelLogConfirm.size()};k++){        //一维长度为i,i为变量，可以根据实际情况改变
            dateConfirm[k]=new Array();    //声明二维，每一个一维数组里面的一个元素都是一个数组

        }
        dateConfirm[0] = ["序号", "核对号", "姓名"];
        <c:forEach items="${excelLogConfirm}" var="item" varStatus="status" >
        dateConfirm[${status.index+1}]=[ ${status.index+1},'${item.excelid}','${item.stuname}'];
        </c:forEach>

        var wopts = { bookType:'xlsx', type:'binary' };
        var fileName = "更新日志.xlsx";
        var ws = XLSX.utils.aoa_to_sheet(data);
        var ws1 = XLSX.utils.aoa_to_sheet(dateFail);
        var ws2 = XLSX.utils.aoa_to_sheet(dateConfirm);

        var wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, '导入成功')
        XLSX.utils.book_append_sheet(wb, ws1, '重名');
        XLSX.utils.book_append_sheet(wb, ws2, '信息不匹配');

        var wbout = XLSX.write(wb, wopts);
        saveAs(new Blob([s2ab(wbout)]), fileName); // 保存为文件
    }

    var isComplated = "false"
    function getproess() {
        //获取进度信息
       // console.log("kaishi");
        $.ajax({
            type:"post",//请求方式
            url:"/admin/getProgressValue",//发送请求地址
         //   timeout:10,//超时时间：30秒????
            data:{'isComplated':isComplated},
            dataType:"json",//设置返回数据的格式
            //请求成功后的回调函数 data为json格式
            success:function(data) {
                //console.log(data.progressValue);
                $("#progressbar").css("width", data.progressValue + "%").text(data.progressValue + "%");
               // console.log( "aaa"+$("#progressbar")[0].style.width)
                if(data.progressValue==100){
                    alert("上传成功");
                    isComplated = "true"

                }else{
                    isComplated = "false"
                }

            },
            //请求出错的处理
            error:function(){
            }
        });
    }

    function addTempMess() {
        var name = $("#excelFile").val();
        if (name == "" || name.length == 0) {
            alert("请选择上传文件!")
            return false;
        }
        var fileA = name.split("//");
        var fileB = fileA[fileA.length - 1].toLowerCase().split(".");
        var fileC = fileB[fileB.length - 1];
        if (fileC != "xls") {
            alert("请选择office03版本excel文件!")
            return false;
        }
        var formData = new FormData();
        formData.append("file",$("#excelFile")[0].files[0]);
        formData.append("name",name);

        if (confirm("确定进行数据上传吗？确定开始后，请勿进行其他操作并等待上传完毕。")) {
            $.ajax({
                type: 'POST',   //http请求方式
                url: '/admin/uploadFile',//发送给服务器的url
                async: true,
                data: formData, //发送给服务器的参数
<<<<<<< HEAD
=======
                dataType:"json",
>>>>>>> parent of 26e019a... delete
                // 告诉jQuery不要去处理发送的数据
                processData: false,
                // 告诉jQuery不要去设置Content-Type请求头
                contentType: false,
                beforeSend: function () {
                    console.log("正在进行，请稍候");
                    // 禁用按钮防止重复提交
                    //$("#submit").attr({ disabled: "disabled" });
                },
                success: function (responseStr) {
                    if (responseStr == "01") {
                        alert("导入成功");
                    } else {

                        alert("导入失败，已经导入"+responseStr+"条信息，请检查表格格式");
                    }
                },
                complete: function () {
                    $("#submit").removeAttr("disabled");
                }
            });
        }
    }

</script>
</html>
