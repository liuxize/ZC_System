<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/2/11
  Time: 下午8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <title>添加信息</title>
    <link rel="shortcut icon" type="image/x-icon" href="/images/logo-dt.png" media="screen">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">

    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <style>
        .btnc {
            float: right;
        }

        .btn1 {
            border: 1px solid #31B0D5;
            display: inline-block;
            padding: 1px 5px;
            margin-right: 10px;
        }

        .table th, .table td {

            vertical-align: middle !important;
        }

        .a ul li a {
            font-size: 18px;
        }

        .inp {
            width: 60%;
            height: 30px;
            border: none;
            padding-left: 10px;
        }

        .inp2 {
            width: 100%;
            height: 30px;
            border: none;
            padding-left: 10px;
        }

        .inp3 {
            width: 60%;
            height: 30px;
            border: none;
            padding-left: 10px;
        }

        .inp4 {
            width: 20%;
            height: 30px;
            border: none;
            padding-left: 10px;
        }

        .inp5 {
            width: 100%;
            border: none;
            padding-left: 10px;
            resize: none;
        }

        td {

        }
    </style>
</head>
<body>

<div class="container" id="content">
    <div class="row">
        <jsp:include page="menucopy.jsp"></jsp:include>
        <div class="col-md-15 container" >
            <h2 class="text-center">众成·学生档案管理</h2>
            <ul class="nav nav-tabs">
                <li class="active"><a href="#">表一</a></li>
                <li class="disabled"><a href="#">表二</a></li>
                <li class="disabled"><a href="#">表三</a></li>
            </ul>
            <form role="form" action="/leader/addTableOne" id="editstu" method="post">

                <div class="col-sm-10" style="font-size: 15px; padding: 10pt">

                    <label class="checkbox-inline">
                        <input type="radio" name="stutype" value="普通学生" checked>普通学生
                    </label>
                    <label class="checkbox-inline">
                        <input type="radio" name="stutype" value="优质学生">优质学生
                    </label>
                </div>


                <!--<div class="col-md-offset-5 col-md-2">
                    2018年1月8日(自动生成)

                </div>-->
                <table class="table table-bordered text-center table-striped ">

                    <tr>
                        <td style="vertical-align: middle !important;text-align: center; width: 50px !important;"
                            rowspan="4">姓名
                        </td>
                        <td style="width: 70px;">学生</td>
                        <td style="width: 100px;">
                            <input class="inp2" type="text" name="stuname"  required="required" placeholder="请输入" onBlur="checkIsExist();"
                                   id="stuname"/>

                            <%--<input type="text" onBlur="checkIsExist();" name="custrealname" id="custrealname"  value="请输入领导用户名/手机号" --%>
                            <%--onFocus="if(value==defaultValue){value='';this.style.color='#000'}" --%>
                            <%--onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color:#999999" >--%>
                        </td>
                        <td style="width: 50px;">所在学校</td>
                        <td colspan="3" style="width: 200px;">
                            <select class="form-control" name="school">
                                <c:forEach items="${schoolList}" var="item">
                                    <option value="${item.schoolname}">${item.schoolname}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="width: 50px">年级</td>
                        <td>
                            <select class="form-control" name="gradeid">
                                <c:forEach items="${gradeList}" var="item">
                                    <option value="${item.gradeid}">${item.gradename}</option>
                                </c:forEach>
                            </select>

                        </td>
                        <td>性别</td>
                        <td>
                            <input class="inp2" type="text" name="stusex" placeholder="请输入"/>
                        </td>
                    </tr>

                    <tr>
                        <td>母亲</td>
                        <td style="width: 100px;"><input class="inp2" type="text" name="mothername" placeholder="请输入"/>
                        </td>
                        <td style="width: 50px;">工作单位</td>
                        <td class="text-left" colspan="3">
                            <input class="inp2" type="text" name="mothercompany" placeholder="请输入"/>
                        </td>
                        <td>学历</td>
                        <td>
                            <input class="inp2" type="text" name="motherdegree" placeholder="请输入"/>
                        </td>
                        <td>职务</td>
                        <td>
                            <input class="inp2" type="text" name="motherjob" placeholder="请输入"/>
                        </td>

                    </tr>
                    <tr>
                        <td>父亲</td>
                        <td style="width: 100px;"><input class="inp2" type="text" name="fathername" placeholder="请输入"/>
                        </td>
                        <td style="width: 50px;">工作单位</td>
                        <td class="text-left" colspan="3">
                            <input class="inp2" type="text" name="fathercompany" placeholder="请输入"/>
                        </td>
                        <td>学历</td>
                        <td>
                            <input class="inp2" type="text" name="fatherdegree" placeholder="请输入"/>
                        </td>
                        <td>职务</td>
                        <td>
                            <input class="inp2" type="text" name="fatherjob" placeholder="请输入"/>
                        </td>
                    </tr>

                    <tr>
                        <td style="width: 70px">班主任</td>
                        <td style="width: 100px;"><input class="inp2" type="text" name="mastername" placeholder="请输入"/>
                        </td>
                        <td style="width: 50px;">家庭住址</td>
                        <td class="text-left" colspan="3">
                            <input class="inp2" type="text" name="masteraddress" placeholder="请输入"/>
                        </td>
                        <td>学历</td>
                        <td>
                            <input class="inp2" type="text" name="masterdegree" placeholder="请输入"/>
                        </td>
                        <td>性别</td>
                        <td>
                            <input class="inp2" type="text" name="mastersex" placeholder="请输入"/>
                        </td>




                        <%--<td>教师</td>--%>
                        <%--<td class="text-left" colspan="2">--%>
                        <%--<input class="inp2" type="text" name="mastername" placeholder="请输入"/>--%>
                        <%--</td>--%>
                        <%--<td>电话</td>--%>
                        <%--<td colspan="7">--%>
                        <%--<input class="inp2" type="text" name="mastertel" placeholder="请输入"/>--%>
                        <%--</td>--%>
                    </tr>
                    <tr>
                        <td colspan="2">现住地址</td>
                        <td class="text-left" colspan="9">
                            <input class="inp2" type="text" name="address" placeholder="请输入"/>
                        </td>
                    </tr>
                    <tr>
                        <td>生日</td>
                        <td class="text-left" colspan="10">
                            学生：
                            <input class="inp4"  type="date"  max="9999-12-31" style="width: 180px" name="stubirth"/>
                            <%--<input class="inp4" type="text" name="stutel" placeholder="请输入"/>--%>
                            &nbsp; &nbsp;&nbsp;
                            母亲：
                            <input class="inp4" type="date"  max="9999-12-31" style="width: 180px" name="motherbirth"/>
                            &nbsp; &nbsp;&nbsp;
                            父亲：
                            <input class="inp4" type="date"  max="9999-12-31" style="width: 180px" name="fatherbirth"/>
                            &nbsp; &nbsp;&nbsp;
                            班主任：
                            <input class="inp4" type="date"  max="9999-12-31" style="width: 180px" name="masterbirth"/>
                        </td>
                    </tr>

                    <tr>
                        <td>电话</td>
                        <td class="text-left" colspan="10">
                            学生：
                            <input class="inp4" type="text" style="width: 175px" name="stutel" placeholder="请输入"/>
                            &nbsp; &nbsp; &nbsp;
                            母亲：
                            <input class="inp4" type="text" style="width: 175px" name="mothertel" placeholder="请输入"/>
                            &nbsp; &nbsp; &nbsp;
                            父亲：
                            <input class="inp4" type="text" style="width: 175px" name="fathertel" placeholder="请输入"/>
                            &nbsp; &nbsp; &nbsp;
                            班主任：
                            <input class="inp4" type="text" style="width: 175px" name="mastertel" placeholder="请输入"/>
                        </td>
                    </tr>

                    <tr>
                        <td>专业</td>
                        <td colspan="6">
                            <select class="form-control" name="major">
                                <c:forEach items="${majorList}" var="item">
                                    <option value="${item.majorname}">${item.majorname}</option>
                                </c:forEach>
                            </select>
                            <%--<input class="inp2" type="text" name="major" placeholder="请输入"/>--%>
                        </td>

                        <td>检验日期</td>
                        <td colspan="2">
                            <input class="inp4" type="date"  max="9999-12-31" style="width: 300px" name="checkdate"/>
                        </td>
                        <td>
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addFigure"
                                    style="background-color:transparent;border:0">添加照片</button>
                            <div class="modal fade" id="addFigure" tabindex="-1" role="dialog"
                                 aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
                                <div class="modal-dialog"> <!-- modal-lg 放大版-->
                                    <div class="modal-content">
                                        <form action="/leader/uploadImage" method="post" enctype="multipart/form-data">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                    &times;
                                                </button>
                                                <h4 class="modal-title">添加图片</h4>
                                            </div>
                                            <div class="modal-body">

                                                <input type="text" class="form-control" placeholder="图片名称" required="required"
                                                       name="imageTitle">
                                                <div style="height: 10px"></div>
                                                <input type="file" class="form-control" id="updateFile" name="image" required="required"
                                                       accept="image/*"/>
                                                <input type="hidden" name="stuid" value=${stumessage.stuid}>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                                </button>
                                                <button class="btn btn-default" type="submit">保存</button>
                                            </div>
                                        </form>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal -->
                            </div>
                        </td>


                    </tr>
                    <tr>
                        <td style="font-size: 20px; " colspan="11">三教两管一提</td>
                    </tr>
                    <tr>
                        <td colspan="3">基础情况</td>
                        <td class="text-left" colspan="8">
                            <div class="form-group">
                                <!--label for="name"></label>-->
                                <textarea class="inp5" rows="20" name="schooltext"></textarea>
                            </div>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">家庭状况</td>
                        <td class="text-left" colspan="8">
                            <div class="form-group">
                                <!--label for="name"></label>-->
                                <textarea class="inp5" rows="20" name="familytext"></textarea>
                            </div>

                        </td>
                    </tr>

                    <tr>
                        <td colspan="3">众成学习情况</td>
                        <td class="text-left" colspan="8">
                            <div class="form-group">
                                <!--label for="name"></label>-->
                                <textarea class="inp5" rows="20" name="studytext"></textarea>
                            </div>

                        </td>
                    </tr>

                    <tr>
                        <td colspan="3">众成结合以上三个方面，<br>针对学生给予的教育方案</td>
                        <td class="text-left" colspan="8">
                            <div class="form-group">
                                <!--label for="name"></label>-->
                                <textarea class="inp5" rows="20" name="educationtext"></textarea>
                            </div>

                        </td>
                    </tr>

                    <tr>
                        <td colspan="3">家长配合情况</td>
                        <td class="text-left" colspan="8">
                            <div class="form-group">
                                <!--label for="name"></label>-->
                                <textarea class="inp5" rows="20" name="supporttext"></textarea>
                            </div>

                        </td>
                    </tr>

                    <tr>
                        <td colspan="3">学生提高程度</td>
                        <td class="text-left" colspan="8">
                            <div class="form-group">
                                <!--label for="name"></label>-->
                                <textarea class="inp5" rows="20" name="improvetext"></textarea>
                            </div>

                        </td>
                    </tr>

                </table>
                <div class="form-group" style="text-align: center">
                    <button class="btn btn-default" type="submit">提交</button>
                    <button class="btn btn-default" type="reset">重置</button>
                </div>
            </form>

        </div>
    </div>


</div>


</div>
</div>
</div>
</body>
<script type="text/javascript">
    $("#nav1").addClass("active");

    function checkIsExist() {
        var realname = $.trim($("#stuname").val());
        if(realname != ""){
            $.ajax({
                type:"POST",   //http请求方式
                url:"/leader/isExist",//发送给服务器的url
                data:"realname="+realname, //发送给服务器的参数
                dataType:"text",  //告诉JQUERY返回的数据格式(注意此处数据格式一定要与提交的controller返回的数据格式一致,不然不会调用回调函数complete)
                complete : function(msg) {
                    //console.log(msg.responseText);
                    var result = msg.responseText;
                    var date = "该学生名字已经存在，是否继续添加？";
                    if (result == "1") {

                        if (confirm(date) == true) {
                            return true;
                        } else {
                            document.getElementById("stuname").value=""
                            return false;
                        }

                    }
                }
            });
        }

    }



</script>
</html>
