<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/2/22
  Time: 上午10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>添加课程信息</title>
    <link rel="shortcut icon" type="image/x-icon" href="/images/logo-dt.png" media="screen">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <link rel="stylesheet"  href="/css/bootstrap-select.min.css">
    <script src="/js/bootstrap-select.min.js"></script>
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
                        <h1 style="text-align: center;">添加课程信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/master/addLesson" id="editfrom" method="post">
                        <div class="form-group">
                            <label for="inputlessname" class="col-sm-2 control-label">科目</label>
                            <div class="col-sm-2">
                                <select class="selectpicker show-tick form-control" data-live-search="true" name="subjectid" id="inputlessname">
                                    <c:forEach items="${subjectlist}" var="item">
                                        <option value="${item.subjectid}">${item.subjectname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputLessonType" class="col-sm-2 control-label">课程类型</label>
                            <div class="col-sm-2">
                                <select class="selectpicker show-tick form-control" data-live-search="true" name="typeid" id="inputLessonType">
                                    <c:forEach items="${typelist}" var="item">
                                        <option value="${item.typeid}">${item.typename}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputstartdata" class="col-sm-2 control-label">开始日期</label>
                            <div class="col-sm-2">
                                <input type="date"   max="9999-12-31" id="inputstartdata" name="lessonstart"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputEdnData" class="col-sm-2 control-label">结束日期</label>
                            <div class="col-sm-10">
                                <input type="date"  max="9999-12-31"  id="inputEdnData" name="lessonend"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputlecturer" class="col-sm-2 control-label">上课老师</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputlecturer" name="lecturer" placeholder="请输入">
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="inputschooltime" class="col-sm-2 control-label">每日上课时间</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control"  id="inputschooltime" name="schooltime" placeholder="请输入">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputLessonTime" class="col-sm-2 control-label">课时</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="inputLessonTime" name="lessontime" placeholder="请输入">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputschooldate" class="col-sm-2 control-label">上课日期</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control"  id="inputschooldate" name="schooldate" placeholder="请输入">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputLessText" class="col-sm-2 control-label">备注</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputLessText" name="remark" placeholder="请输入">
                                <input type="hidden" name="stuid" value=${StuID}>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputDutyDate" class="col-sm-2 control-label">出勤日期</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputDutyDate" name="dutydate" placeholder="请输入">
                                <input type="hidden" name="stuid" value=${StuID}>
                            </div>
                        </div>
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
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(1)").addClass("active")
</script>
</html>
