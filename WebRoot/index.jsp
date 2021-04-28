<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<title>管理权限</title>
	</head>
	<body>
		<style type="text/css">
body {
	background: url("scripts/34822368eaef5b4b74198fad52d67d0b.jpg");
}

* {
	padding: 0;
	marging: 0;
}

.main {
	padding-left: 25px;
	padding-right: 25px;
	padding-top: 15px;
	width: 350px;
	height: 350px;
	background: white;
	/*让表单垂直居中在界面*/
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -175px;
	margin-left: -175px;
}

.title {
	height: 40px;
	line-height: 40px;
	padding-left: 140px;
}

.title span {
	font-size: 18px;
}

.title-msg {
	height: 64px;
	line-height: 64px;
}

.title-msg span {
	font-size: 12px;
	color: gray;
}

.input-content input {
	width: 340px;
	height: 40px;
	border: 1px solid #dad9d6;
	padding-left: 10px;
}

.enter-btn {
	margin-top: 20px; width : 350px;
	height: 40px;
	color: white;
	background: #0bc5de;
	border: 0px;
	width: 350px;
}

.foor {
	font-size: 12px;
	color: gray;
	margin-top: 20px;
}

.left {
	float: left;
}

.right {
	float: right;
}

a {
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="main">
		<div class="title">
			<span>登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</span>
		</div>

		<div class="title-msg">
			<span>请输入用户名和密码</span>
		</div>

		<form class="login-form" method="post" action="<%=basePath%>LoginServlet" id="cc">
			<!-- 输入框 -->
			<div class="input-content" >
				<div>
					<input type="text" placeholder="用户名" name="name" id="username" required>
				</div>
				<div style="margin-top: 16px">
					<input type="password" placeholder="密码" id="password" name="password" required>
				</div>
			</div>

			<!-- 登录按钮 -->
			<div style="text-align: center">
			 <button type="submit" class="enter-btn" onclick="ifCont('${name}','${pwd}')" >登录</button>
			</div>

			<div class="foor">
				<div class="left">
					<span><a href="lookpwd.jsp">忘记密码？</a></span>
				</div>
				<div class="right">
					<span><a href="register.html">还没有账号，立即注册</a></span>
				</div>
			</div>
		</form>
	</div>
	 <script type="text/javascript" src="scripts/jquery-1.12.4.js"></script>
 <script src="user/Yan.js" charset="utf-8"></script> 
    <script type="text/javascript">
      
  </script>
</body>
</html>


