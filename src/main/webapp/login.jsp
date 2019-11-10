<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>登陆</title>
	<link rel="shortcut icon" type="image/x-icon" href="/images/logo-dt.png" media="screen">

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>login</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/app.css">
</head>


<body style="background: #ffffff" onkeydown="keyLogin();">
<div id="Layer1" style="position:absolute; width:100%; height:100%; z-index:-1">
	<img src="images/b1.jpg" height="100%" width="100%"/></div>
<div class="login-wrapper container-fluid">
	<!-- 头部 -->
	<header>
		<%--<h2 class="text-center" style="font-size: 60px;color:black; font-family: microsoft yahei"></h2>--%>
		<div class="text-center">
			<img src="/images/logoo.png">
			<%--https://www.qt86.com/random.php 72 华文新魏 大小 75 --%>
			<%--图片宽度 815 图片高度 140 字体大小 75 旋转角度 0 左侧间距 10 上侧间距 110--%>

		</div>
	</header>
	<!-- 内容 -->
	<div class="container">
		<!-- 表单 -->
		<div class="login-form">
			<div class="login-form-title">
				<span>登录</span>
			</div>
			<form action="/login" method="post">
				<div class="form-group">
					<input type="text" class="form-control login-input" placeholder="用户名" required name="username" value="管理员">
				</div>
				<div class="form-group">
					<input type="password" class="form-control login-input" placeholder="密码" required name="password" value="123">
				</div>
				<button id="submit" type="submit" class="btn login-btn">登录</button>

			</form>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
    function keyLogin(){
        if (event.keyCode==13)  //回车键的键值为13
            document.getElementById("submit").click(); //调用登录按钮的登录事件
    }

    document.getElementById("submit").click(); //自动登录
</script>
</html>