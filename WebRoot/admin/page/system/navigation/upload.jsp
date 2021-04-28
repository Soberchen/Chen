<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加导航</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath %>admin/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath %>admin/css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
        #div1,#div2{
                display: inline-block;
        }
    </style>
</head>
<body>
	<blockquote class="layui-elem-quote quoteBox">
	       公而告之:
    		仅支持首页背景图展示
	 </blockquote>	
	 <blockquote class="layui-elem-quote quoteBox">
		 <div class="layui-upload">
		  <button type="button" class="layui-btn" id="test1">上传图片</button>
		  <div class="layui-upload-list">
		  <span>当前图片</span>
		    <img class="layui-upload-img" id="demo1" style="width: 200px;height: 200px;">
		    <p id="demoText"></p>
		  </div>
		</div>   
	 </blockquote>
<script src="<%=basePath %>admin/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="<%=basePath %>admin/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    layui.use(['form','upload'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$;
        	upload = layui.upload;
       	 $('#demo1').attr('src', "http://localhost:8723/Backend/images/background.png"); //图片链接（base64）
       	//普通图片上传
       	  var uploadInst = upload.render({
       	    elem: '#test1'
       	    ,url: '/Backend/UploadeServlet?action=addFile' //改成您自己的上传接口
       	    ,before: function(obj){
       	      //预读本地文件示例，不支持ie8
       	      obj.preview(function(index, file, result){
       	        $('#demo1').attr('src', result); //图片链接（base64）
       	      });
       	    }
       	    ,done: function(res){
       	      //如果上传失败
       	      if(res.code > 0){
       	        return layer.msg('上传失败');
       	      }
       	      //上传成功
       	    }
       	    ,error: function(){
       	      //演示失败状态，并实现重传
       	      var demoText = $('#demoText');
       	      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
       	      demoText.find('.demo-reload').on('click', function(){
       	        uploadInst.upload();
       	      });
       	    }
       	  });
        
    });
</script>
</body>
</html>