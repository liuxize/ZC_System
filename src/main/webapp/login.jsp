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


<body style="background: #ffffff">
<div id="Layer1" style="position:absolute; width:100%; height:100%; z-index:-1">
	<img src="images/b1.jpg" height="100%" width="100%"/></div>
<div class="login-wrapper container-fluid">
	<!-- 头部 -->
	<header>
		<%--<h2 class="text-center" style="font-size: 60px;color:black; font-family: microsoft yahei">众成教育学生档案管理</h2>--%>
		<div class="text-center">
			<img src="/images/logo.png">
			<%--https://www.qt86.com/random.php 72  华文新魏--%>
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
					<input type="text" class="form-control login-input" placeholder="用户名" required name="username">
				</div>
				<div class="form-group">
					<input type="password" class="form-control login-input" placeholder="密码" required name="password">
				</div>
				<button type="submit" class="btn login-btn">登录</button>

			</form>
		</div>
	</div>
</div>
</body>

</html>