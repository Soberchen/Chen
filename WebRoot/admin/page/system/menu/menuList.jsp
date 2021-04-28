<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>权限管理</title>
    <link rel="stylesheet" href="<%=basePath %>admin/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath %>admin/css/public.css" media="all">
    <style>
        .layui-btn:not(.layui-btn-lg ):not(.layui-btn-sm):not(.layui-btn-xs) {
            height: 34px;
            line-height: 34px;
            padding: 0 8px;
        }
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <blockquote class="layui-elem-quote">
           	 权限管理<br>
        </blockquote>
        <div>
	        <script type="text/html" id="menutoolbarDemo">
				<button class="layui-btn" lay-event="btn-expand">全部展开</button>
	        	<button class="layui-btn layui-btn-normal" lay-event="btn-fold">全部折叠</button>&nbsp;
				<c:forEach var="i" items="${requestScope.menuListBtn }">
	        		${i.mbtn }&nbsp;
	        	</c:forEach>
			</script>
        </div>
        
        <table id="demoTreeTb"></table>
        
    </div>
</div>

<script src="<%=basePath %>admin/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="<%=basePath %>admin/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath %>admin/pagejs/system/menu/menuList.js"></script>
</body>
</html>