<%--
  Created by IntelliJ IDEA.
  User: liuxize
  Date: 2018/3/30
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>修改信息</title>
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
        <div class="col-md-12 container" >
            <h2 class="text-center">${stumessage.stuid}号学生档案</h2>

            <ul class="nav nav-tabs">
                <li class="active"><a href="/admin/editMessage?stuid=${stumessage.stuid}">表一</a></li>
                <li><a href="/admin/editMessageTwo?stuid=${stumessage.stuid}">表二</a></li>
                <li><a href="/admin/editMessageThree?stuid=${stumessage.stuid}">表三</a></li>
            </ul>
            <form role="form" action="/admin/editMessage" id="editstu" method="post">

                <div class="col-sm-10" style="font-size: 15px; padding: 10pt">

                    <label class="checkbox-inline">
                        <C:if test="${stumessage.stutype=='普通学生'}">
                            <input type="radio" name="stutype" value="普通学生" checked>普通学生
                        </C:if>
                        <C:if test="${stumessage.stutype!='普通学生'}">
                            <input type="radio" name="stutype" value="普通学生">普通学生
                        </C:if>
                    </label>
                    <label class="checkbox-inline">
                        <C:if test="${stumessage.stutype=='优质学生'}">
                            <input type="radio" name="stutype" value="优质学生" checked>优质学生
                        </C:if>
                        <C:if test="${stumessage.stutype!='优质学生'}">
                            <input type="radio" name="stutype" value="优质学生" >优质学生
                        </C:if>

                    </label>
                </div>


                <!--<div class="col-md-offset-5 col-md-2">

                </div>-->
                <table class="table table-bordered text-center table-striped ">

                    <tr>
                        <td style="vertical-align: middle !important;text-align: center; width: 5%; !important;"
                            rowspan="4">姓名
                            <input  type="hidden" name="stuid" value=" ${stumessage.stuid}" />
                        </td>
                        <td style="width: 6%;">学生</td>
                        <td style="width: 13%">
                            <input class="inp2" type="text" name="stuname" value="${stumessage.stuname}" required="required" placeholder="请输入" onBlur="checkIsExist();"
                                   id="stuname"/>
                        </td>
                        <td style="width: 8%">所在学校</td>
                        <td style="width: 22%">
                            <select class="form-control" name="school">
                            <c:forEach items="${schoolList}" var="item">
                                <c:choose>
                                    <c:when test="${item.schoolname == stumessage.school}">
                                        <option value="${item.schoolname}"
                                                selected="selected">${item.schoolname}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${item.schoolname}">${item.schoolname}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            </select>
                        </td>
                        <td style="width: 6%;">年级</td>
                        <td style="width: 15%;">
                            <select class="form-control" name="gradeid">
                                <c:forEach items="${gradeList}" var="item">
                                    <c:choose>
                                        <c:when test="${item.gradeid==stumessage.gradeid}">
                                            <option value="${item.gradeid}"
                                                    selected="selected">${item.gradename}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${item.gradeid}">${item.gradename}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="width: 6%;">性别</td>
                        <td>
                            <input class="inp2" type="text" name="stusex" value="${stumessage.stusex}"/>
                        </td>
                        <%--<td>生日</td>--%>
                        <%--<td>--%>
                            <%--<input class="inp2" type="date" name="stubirth" id="birthDate" value="datestart"/>--%>
                        <%--</td>--%>
                    </tr>

                    <tr>
                        <td>母亲</td>
                        <td><input class="inp2" type="text" name="mothername" value="${stumessage.mothername}"/>
                        </td>
                        <td>工作单位</td>
                        <td class="text-left" >
                            <input class="inp2" type="text" name="mothercompany" value="${stumessage.mothercompany}"/>
                        </td>
                        <td>学历</td>
                        <td>
                            <input class="inp2" type="text" name="motherdegree" value="${stumessage.motherdegree}"/>
                        </td>

                        <td>职务</td>
                        <td>
                            <input class="inp2" type="text" name="motherjob" value="${stumessage.motherjob}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>父亲</td>
                        <td><input class="inp2" type="text" name="fathername" value="${stumessage.fathername}"/>
                        </td>
                        <td>工作单位</td>
                        <td class="text-left" >
                            <input class="inp2" type="text" name="fathercompany" value="${stumessage.fathercompany}"/>
                        </td>
                        <td>学历</td>
                        <td>
                            <input class="inp2" type="text" name="fatherdegree" value="${stumessage.fatherdegree}"/>
                        </td>
                        <td>职务</td>
                        <td>
                            <input class="inp2" type="text" name="fatherjob" value="${stumessage.fatherjob}"/>
                        </td>
                    </tr>

                    <tr>
                        <td>班主任</td>
                        <td><input class="inp2" type="text" name="mastername" value="${stumessage.mastername}"/>
                        </td>
                        <td>家庭住址</td>
                        <td class="text-left" >
                            <input class="inp2" type="text" name="masteraddress" value="${stumessage.masteraddress}"/>
                        </td>
                        <td>学历</td>
                        <td>
                            <input class="inp2" type="text" name="masterdegree" value="${stumessage.masterdegree}"/>
                        </td>
                        <td>性别</td>
                        <td>
                            <input class="inp2" type="text" name="mastersex" value="${stumessage.mastersex}"/>
                        </td>
                    </tr>


                    <tr>
                        <td colspan="2">现住地址</td>
                        <td class="text-left" colspan="9">
                            <input class="inp2" type="text" name="address" value="${stumessage.address}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>生日</td>
                        <td class="text-left" colspan="10">
                            学生：
                            <input class="inp4" type="date" max="9999-12-31"  style="width: 180px" name="stubirth" id="birthDate" />
                            &nbsp; &nbsp; &nbsp;
                            母亲：
                            <input class="inp4" type="date"  max="9999-12-31" style="width: 180px" id="motherBirth" name="motherbirth"/>
                            &nbsp; &nbsp; &nbsp;
                            父亲：
                            <input class="inp4" type="date"  max="9999-12-31" style="width: 180px" id="fatherBirth" name="fatherbirth"/>
                            &nbsp; &nbsp; &nbsp;
                            班主任：
                            <input class="inp4" type="date" max="9999-12-31"  style="width: 180px" id="masterBirth" name="masterbirth"/>
                        </td>
                    </tr>

                    <tr>
                        <td>电话</td>
                        <td class="text-left" colspan="10">
                            学生：
                            <input class="inp4" type="text" name="stutel" style="width: 178px" value="${stumessage.stutel}"/>
                            &nbsp; &nbsp; &nbsp;
                            母亲：
                            <input class="inp4" type="text" name="mothertel" style="width: 180px" value="${stumessage.mothertel}"/>
                            &nbsp; &nbsp; &nbsp;
                            父亲：
                            <input class="inp4" type="text" name="fathertel"  style="width: 180px" value="${stumessage.fathertel}"/>
                            &nbsp; &nbsp; &nbsp;
                            班主任：
                            <input class="inp4" type="text" name="mastertel"  style="width: 178px" value="${stumessage.mastertel}"/>
                        </td>
                    </tr>

                    <tr>
                        <td>专业</td>
                        <td colspan="2">
                            <select class="form-control" name="major">
                                <c:forEach items="${majorList}" var="item">
                                    <c:choose>
                                        <c:when test="${item.majorname == stumessage.major}">
                                            <option value="${item.majorname}"
                                                    selected="selected">${item.majorname}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${item.majorname}">${item.majorname}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td>检验日期</td>
                        <td colspan="7" class="text-left">
                            <input class="inp4" type="date"  max="9999-12-31" style="width: 250px" id="checkDate" name="checkdate"/>
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
                                <textarea class="inp5" rows="20" name="schooltexthis">${stumessage.schooltexthis}</textarea>
                            </div>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">家庭状况</td>
                        <td class="text-left" colspan="8">
                            <div class="form-group">
                                <!--label for="name"></label>-->
                                <textarea class="inp5" rows="20" name="familytexthis">${stumessage.familytexthis}</textarea>
                            </div>

                        </td>
                    </tr>

                    <tr>
                        <td colspan="3">众成学习情况</td>
                        <td class="text-left" colspan="8">
                            <div class="form-group">
                                <!--label for="name"></label>-->
                                <textarea class="inp5" rows="20" name="studytexthis">${stumessage.studytexthis}</textarea>
                            </div>

                        </td>
                    </tr>

                    <tr>
                        <td colspan="3">众成结合以上三个方面，<br>针对学生给予的教育方案</td>
                        <td class="text-left" colspan="8">
                            <div class="form-group">
                                <!--label for="name"></label>-->
                                <textarea class="inp5" rows="20" name="educationtexthis">${stumessage.educationtexthis}</textarea>
                            </div>

                        </td>
                    </tr>

                    <tr>
                        <td colspan="3">家长配合情况</td>
                        <td class="text-left" colspan="8">
                            <div class="form-group">
                                <!--label for="name"></label>-->
                                <textarea class="inp5" rows="20" name="supporttexthis">${stumessage.supporttexthis}</textarea>
                            </div>

                        </td>
                    </tr>

                    <tr>
                        <td colspan="3">学生提高程度</td>
                        <td class="text-left" colspan="8">
                            <div class="form-group">
                                <!--label for="name"></label>-->
                                <textarea class="inp5" rows="20" name="improvetexthis">${stumessage.improvetexthis}</textarea>
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
    $("#nav2").addClass("active")

    $(document).ready(function () {
        var stuBirth = '${stuBirth}' ;
        var motherBirth = '${motherBirth}' ;
        var fatherBirth = '${fatherBirth}' ;
        var masterBirth = '${masterBirth}';
        var checkDate = '${checkDate}';

        $('#birthDate').attr('value',stuBirth);
        $('#motherBirth').attr('value',motherBirth);
        $('#fatherBirth').attr('value',fatherBirth);
        $('#masterBirth').attr('value',masterBirth);
        $('#checkDate').attr('value',checkDate);
    });

    function checkIsExist() {
        var realname = $.trim($("#stuname").val());
        if(realname != ""){
            $.ajax({
                type:"POST",   //http请求方式
                url:"/admin/isExist",//发送给服务器的url
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
