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
    </style>
</head>
<body>
<form class="layui-form layui-form-pane" action="">
    <div class="layui-form-item">
        <label class="layui-form-label required">网站名称</label>
        <div class="layui-input-block">
            <input type="text" name="sitename"  placeholder="请输入网站名" value="${sessionScope.config.sitename }" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">标题栏后缀</label>
        <div class="layui-input-block">
            <input type="text" name="title"  placeholder="请输入标题栏后缀" value="${sessionScope.config.title }" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">关键字</label>
        <div class="layui-input-block">
            <input type="text" name="keywords"   placeholder="请输入关键字" value="${sessionScope.config.keywords }" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">网站描述</label>
        <div class="layui-input-block">
            <input type="text" name="description"  placeholder="请输入网站描述" value="${sessionScope.config.description }" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">导航介绍语</label>
        <div class="layui-input-block">
            <input type="text" name="modal"  placeholder="请输入导航介绍语" value="${sessionScope.config.modal }" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">站长QQ</label>
        <div class="layui-input-block">
            <input type="text" name="kfqq"  placeholder="请输入站长QQ" value="${sessionScope.config.kfqq }" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">本站URL</label>
        <div class="layui-input-block">
            <input type="text" name="url"  placeholder="请输入本站URL" value="${sessionScope.config.url }" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">备案号</label>
        <div class="layui-input-block">
            <input type="text" name="icp"  placeholder="请输入备案号" value="${sessionScope.config.icp }" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">腾讯云智服</label>
        <div class="layui-input-block">
            <input type="text" name="yzf"  placeholder="请输入腾讯云智服" value="${sessionScope.config.yzf }" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">播放器key</label>
        <div class="layui-input-block">
            <input type="text" name="music"  placeholder="请输入播放器key" value="${sessionScope.config.music }" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认修改</button>
        </div>
    </div>
</form>
<script src="<%=basePath %>admin/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="<%=basePath %>admin/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    layui.use(['form','upload'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$;
        	upload = layui.upload;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
        	var info = JSON.stringify(data.field);
        	var row = eval("("+info+")");
        	$.ajax({
        		url:"/Backend/SiteSettings?action=updateNav",
        		type:"post",
        		data:row,
        		success:function(data2){
        			if(data2 == 1){
        				layer.alert('修改成功')
       	                // 关闭弹出层
       	                layer.close(index);
       	                form.render(); //更新全部
        			}
        		}
        	})
            return false;
        });
        
    });
</script>
</body>
</html>