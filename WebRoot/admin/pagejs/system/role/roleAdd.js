layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  //***********验证角色名是否存在***************

/*  $.ajax({
	  url:"/Queen/SelectRoleServlet?action=selectRoleOne",
	  type:"post",
	  success:function(data){
		  var info = eval("("+data+")");
//		  var row = info.data;
		  var role = $("#role1");
		  var html = '';
		  $.each(info,function(index,item){
			  html += '<option value="'+item.id+'">'+item.rname+'</option>';
		  })
		  role.html(html);
		  form.render("select");
	  }
  })


function checkUname(uname){
  var is = false;
  $.ajax({
	  url:"/Queen/SelectUserServlet?action=IfUser",
	  data:{"uname":uname},
	  async:false,
	  type:"post",
	  success:function(data){
		  if(data == 0){
			  is = true;
		  }else{
			  is = false;
		  }
		  
	  }
  })
  return is;
}*/
  /*****************提交按钮事件***********************/
  $("#tijiao").click(function(){
	  var rname = $("#rname").val();
	  if(rname.length == "" ){
		  layer.msg("角色名不能为空")
		  return false;
	  }/*else if(rname.length != null){
		  var check = checkUname(rname);
		  if(check == false){
			  layer.msg("角色名已存在! 请重新输入")
			  return false;
		  }
	  }*/
		  $.ajax({
			url:"/ControlText/SelectRoleServlet?action=addRole",
			data:{"rname":rname},
			tyep:"post",
			success:function(data){
				if(data == 1){
					layer.msg("添加成功")
					setTimeout(function(){
						//layer.closeAll("iframe");
						parent.layer.closeAll("iframe");
			            //刷新父页面
//			            parent.location.reload();
			            parent.layui.table.reload("roleList");
		        	},1000);
				}else{
					layer.msg("系统异常");
				}
			}
		})
	  return false;
  })
  
});
