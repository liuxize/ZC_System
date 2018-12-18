<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2018/2/23
  Time: 上午9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>添加考试信息</title>
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
                        <h1 style="text-align: center;">添加课程信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/master/addExam" id="editfrom" method="post">


                        <div class="form-group">
                            <label for="inputlexamtime" class="col-sm-2 control-label">考试时间</label>
                            <div class="col-xs-3">
                                <input type="date" class="form-control"  max="9999-12-31" id="inputlexamtime" name="examdata"
                                       placeholder="请输入">
                            </div>

                            <label for="inputexamtype" class="col-sm-2 control-label">考试类型</label>
                            <div class="col-xs-3">
                                <input type="text" class="form-control"  max="9999-12-31" id="inputexamtype" name="examtype"
                                       placeholder="请输入">
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="inputmath" class="col-sm-2 control-label">数学成绩</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputmath" name="math"
                                       placeholder="请输入">
                            </div>

                            <label for="inputmathscore" class="col-sm-2 control-label">数学满分</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputmathscore" name="mathscore"
                                       placeholder="请输入">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputchinese" class="col-sm-2 control-label">语文成绩</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputchinese" name="chinese"
                                       placeholder="请输入">
                            </div>

                            <label for="inputchscore" class="col-sm-2 control-label">语文满分</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputchscore" name="chinesescore"
                                       placeholder="请输入">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputeng" class="col-sm-2 control-label">英语成绩</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputeng" name="english"
                                       placeholder="请输入">
                            </div>

                            <label for="inputengscore" class="col-sm-2 control-label">英语满分</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputengscore" name="englishscore"
                                       placeholder="请输入">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputgeo" class="col-sm-2 control-label">物理成绩</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputgeo" name="physics"
                                       placeholder="请输入">
                            </div>

                            <label for="inputgeoscore" class="col-sm-2 control-label">物理满分</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputgeoscore" name="physcore"
                                       placeholder="请输入">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputchem" class="col-sm-2 control-label">化学成绩</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputchem" name="chemistry"
                                       placeholder="请输入">
                            </div>

                            <label for="inputchemscore" class="col-sm-2 control-label">化学满分</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputchemscore" name="chemscore"
                                       placeholder="请输入">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputpol" class="col-sm-2 control-label">政治成绩</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputpol" name="politics"
                                       placeholder="请输入">
                            </div>

                            <label for="inputpolscore" class="col-sm-2 control-label">政治满分</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputpolscore" name="polscore"
                                       placeholder="请输入">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputhis" class="col-sm-2 control-label">历史成绩</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputhis" name="history"
                                       placeholder="请输入">
                            </div>

                            <label for="inputhiscore" class="col-sm-2 control-label">历史满分</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputhiscore" name="hisscore"
                                       placeholder="请输入">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputbio" class="col-sm-2 control-label">生物成绩</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputbio" name="biology"
                                       placeholder="请输入">
                            </div>

                            <label for="inputbioscore" class="col-sm-2 control-label">生物满分</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputbioscore" name="bioscore"
                                       placeholder="请输入">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputgio" class="col-sm-2 control-label">地理成绩</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputgio" name="geography"
                                       placeholder="请输入">
                            </div>

                            <label for="inputgioscore" class="col-sm-2 control-label">地理满分</label>
                            <div class="col-xs-3">
                                <input type="number" class="form-control" id="inputgioscore" name="geoscore"
                                       placeholder="请输入">
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="inputExamText" class="col-sm-2 control-label">备注</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputExamText" name="examremark"
                                       placeholder="请输入">
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

