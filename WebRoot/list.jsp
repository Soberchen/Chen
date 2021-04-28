<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
	pageContext.setAttribute("ctx", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>Layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<script type="text/javascript" src="scripts/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="admin/layui/css/layui.css" media="all">
<link rel="stylesheet"
	href="${ctx}/admin/js/lay-module/layui_ext/dtree/dtree.css">
<link rel="stylesheet"
	href="${ctx}/admin/js/lay-module/layui_ext/dtree/font/dtreefont.css">
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">后台管理</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<!-- </dl></li> -->
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">雅儿 </a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="index.jsp">GG</a></li>
			</ul>
		</div>

		<!-- 左侧导航栏内容 -->
		<div class="layui-side layui-bg-block">
			<div class="layui-side-scroll">
				<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="test"
					style="margin-right: 10px;">
					<c:forEach var="prod" items="${list}">
						<li class="layui-nav-item layui-nav-itemed"><c:if
								test="${prod.mfatherid==0}">
								<a href="javascript:;">${prod.mname }</a>
								<dl class="layui-nav-child">
									<c:forEach var="i" items="${list}">
										<c:if test="${i.mfatherid==prod.id }">
											<dd>
												<a href="javascript:void(0)"
													onclick="judgeCode('${i.id }','${i.url }')">${i.mname }</a>
											</dd>
										</c:if>
									</c:forEach>
								</dl>
							</c:if></li>
					</c:forEach>
				</ul>
			</div>
		</div>


		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div>
				<div class="layuimini-container">
					<div class="layuimini-main">
						<blockquote class="layui-elem-quote quoteBox">
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" name="uname" id="likename"
										class="layui-input" placeholder="请输入查找的登录名" />
								</div>
							</div>
							<div class="layui-inline">
								<button type="button" class="layui-btn" lay-filter="doSubmit"
									id="doSubmit">
									<i class="layui-icon layui-icon-search layui-icon-normal"></i>搜索
								</button>
							</div>
							<input type="text" id="login" value="${id }" style="display:none" />
						</blockquote>
					</div>
				</div>

			</div>
			<div id="cont">
				<!-- <div id="table1"></div> -->
			</div>
		</div>

		<script src="admin/layui/layui.js" charset="utf-8"></script>
		<!--  注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的  -->
		<script>
		
			layui.use('element', function() {
				var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
		
			});
		</script>

		<script type="text/javascript">
			contextPath = "${ctx}";
			var table = layui.table;
		</script>
		<!-- 分配权限 -->
		<div style="height: 400px;overflow: auto;display: none;" id="dtree1">
			<ul id="dataTree3" class="dtree" data-id="0"></ul>
		</div>

		<!-- 分配角色 -->
		<div style="height: 400px;overflow: auto;display: none;" id="hairRole">
			<select name="Roleid" id="RoleName" lay-filter="RoleName">

			</select>
		</div>

		<script src="user/show.js" charset="utf-8"></script>
		<script src="user/userList.js" charset="utf-8"></script>
</body>

</html>
