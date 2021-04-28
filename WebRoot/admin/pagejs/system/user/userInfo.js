layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;


  $("#xiugai").click(function(){
	  var uid = $("#uid").val();
	  var name = $("#uname").val();
	  var loginname = $("#loginname").val();
	  var password = $("#password").val();
	 // var identityCode = $("#identityCode").val();
	  var sex = $("input[type='radio']:checked").val();
	  var email = $("#email").val();
	  var phone = $("#phone").val();
	  var role = $("select[name='role1']").val();
	  var work = $("select[name='work']").val();
	  var part=  $("select[name='part']").val();
	  var isStatus = $("input[name='isStatus']:checked").val();
	  
	  var data = {
			  "uid":uid,
			  "uname":name,
     		  "loginname":loginname,
			  "password":password,
			  "sex":sex,
			  "email":email,
			  "phone":phone,
			  "role1":role,
			  "work":work,
			  "part":part,
			  "isStatus":isStatus
	  }
	  if(name.length<3){
		  layer.alert("登录名不能小于3位数")
		  return false;
	  }else if(password.length < 5 || password.length > 19){
		  layer.alert('密码必须6到12位，且不能出现空格');
		  return false;
	  }else if(loginname.length == "" || loginname.length == null){
		  layer.alert('用户名不能为空');
		  return false;
	  }else if(!email.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)){
		  layer.alert("邮箱格式不正确！请重新输入");
		  return false;
	  }else if(phone.length != 11){
		  layer.alert("手机格式不正确! 请重新输入");
		  return false;
	  }/*else if(name !=null  ){
		  var check = checkUname(name);
		  if(check == false){
			  layer.alert("登录账号已存在! 请重新输入")
			  return false;
		  }
	  }*/
	  
	  $.ajax({
	  		url:"/ControlText/SelectUserServlet?action=upUser",
			data:data,
			tyep:"post",
			success:function(data){
				if(data >0){
					layer.msg("用户信息修改成功");
					setTimeout(function(){
						layer.closeAll("iframe");
			            //刷新父页面
			           // parent.location.reload();
			            parent.layui.table.reload("newsList"); 
		        	},1000);
				}else{
					layer.msg("系统异常");
				}
			}
	  })
	  return false;
  })
  
});
