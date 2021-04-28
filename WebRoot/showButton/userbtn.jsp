<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page contentType="text/html; charset=GB2312"language="java" errorPage=""%> --%>
<%-- 	<jsp:include page="list.jsp"/> --%>
	
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path ;
	pageContext.setAttribute("ctx", basePath);
%>    		
   <script type="text/html" id="toolbarDemo">
				<c:forEach var="i" items="${list1}">
					${i.button}
				</c:forEach>
			</script>
		
			
		<table id="newsList" lay-filter="newsList"></table>  
	  	<script src="user/userList.js" charset="utf-8"></script>