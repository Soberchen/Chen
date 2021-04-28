<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
	pageContext.setAttribute("ctx", basePath);
%>


<script type="text/html" id="toolbarDemo">
<button class="layui-btn" lay-event="btn-expand">全部展开</button>
	        	<button class="layui-btn layui-btn-normal" lay-event="btn-fold">全部折叠</button>&nbsp;
				<c:forEach var="i" items="${list1}">
					${i.button}
				</c:forEach>
			</script>

<table id="menuList" lay-filter="menuList"></table>
<%-- <script src="${ctx}admin/lib/layui-v2.5.5/layui.js" charset="utf-8"></script> --%>
<%-- <script src="${ctx}admin/js/lay-config.js?v=1.0.4" charset="utf-8"></script> --%>

<!-- 	  	<script src="user/menuList.js" charset="utf-8"></script> -->
<script src="admin/pagejs/system/product/productList.js" charset="utf-8"></script>