layui.extend({
    dtree: '{/}admin/js/lay-module/layui_ext/dtree/dtree'   // {/}的意思即代表采用自有路径，即不跟随 base 路径
}).use(['form','layer','laydate','table','laytpl','dtree'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    	var dtree = layui.dtree, layer = layui.layer, $ = layui.jquery;
    

    /*------------- 加载用户数据 --------------------------------*/
    var tableIns = table.render({
        elem: '#menuList',
        url : 'SelectProductServlet?action=selectProduct',
        toolbar: '#toolbarDemo',
        page : false,//开启分页
        height: 'full-145',
       
        limit : 100,
        limits : [5,10,15,20],
        cols : [[
          	{fixed:"left",type: "checkbox", width:50},
            {field: 'id', title: '编号',  align:'center',hide:true},
            {field: 'name', title: '商品名称', minWidth:100, align:"center",sort:true},
            {field: 'price', title: '商品价格', minWidth:100, align:"center",sort:true}
            
        	
        ]]
    });
    /*------------- 加载用户数据 --end------------------------------*/

    /*-------- 搜索用户 ----------------------------*/
    $("#doSubmit").click(function(){
    	var like = $("#likename").val()
    	
    	 tableIns.reload({
	      url:"http://localhost:8080/ControlText/SelectProductServlet?action=selectProduct&uname="+like,
	      page: {
	        curr: 1 //重新从第 1 页开始
	      }
	    });
    })
    
     //工具栏事件
	  table.on('toolbar(newsList)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id);
	    var data = checkStatus.data;
	    
	    var userid = '';
	    for(i=0;i<data.length;i++){
	    	userid = data[i].id;
	    }
	    switch(obj.event){
	      case 'hairMenu':	//分配权限
				if(data.length == 0 || data.length > 1){
					layer.msg("请选择一行数据进行操作")
					return ;
				}else{
					hairMenu(userid);
				}
	      break;
	      
	      case 'addUser':	//新增用户
	    	  addUser();
	      break;
	      
	      case 'hairRole':	//分配角色
	    	  if(data.length == 0 || data.length > 1){
					layer.msg("请选择一行数据进行操作")
					return ;
				}else{
					//HairRole(userid);
				}
	      break;
	      
	      case 'upUser':	//修改用户信息
	    	  
	    	  if(data.length == 0 || data.length > 1){
					layer.msg("请选择一行数据进行操作")
					return ;
				}else{

					upUser(userid);
				}
	      break;
		        
	      case 'delUser':	//删除用户
	    	  if(data.length == 0 || data.length > 1){
					layer.msg("请选择一行数据进行操作")
					return ;
				}else{
					layer.confirm('确定删除用户吗', {icon: 3, title:'提示'}, function(index){
						var loginName = $("#loginName").val();
						
						if(userid == loginName){
							layer.msg("你正在登录当前账号,无法删除")
						}else{
							delUser(userid);
							layer.close(index);
						}
		            });
				}
	      break;
	    };
	  });
    
    //修改用户
    function upUser(id){
    	layui.layer.open({
    		title : "修改用户信息",
    		type : 2,
    		content : "admin/page/system/user/userInfo.jsp",
    		area:['400px','540px'],
    		success:function(layero, index){
    			$.ajax({
    				url:"/ControlText/SelectUserServlet?action=selectUserOne",
    				type:"post",
    				data:{"uid":id},
    				success:function(data){
    					var info = eval('(' + data + ')');
    					//alert(JSON.stringify(info));
    					info=info[0];
          				var body = layui.layer.getChildFrame('body', index);
          			
          				var uid=info.id;
          				
          				body.find("#uid").val(uid);
    					body.find("#uname").val(info.userName);
    					body.find("#password").val(info.password);
    					body.find("#loginname").val(info.loginName);
    					body.find("#email").val(info.email);
    					body.find("#phone").val(info.mobile);
    					
    					body.find("#isStatus").val(info.isStatus);
    					//性别(单选)		
    					var sex2 = info.sex;
    					if(sex2 == 0){
    						body.find("#sex1").prop("checked",true);
    					}else{
    						body.find("#sex2").prop("checked",true);
    					}
    					//--------------------------------------------------
    					//状态(单选)
    					var isStatus = info.isStatus;
    					if(isStatus ==0){
    						body.find("#isStatus0").prop("checked",true);
    					}else if(isStatus ==1){
    						body.find("#isStatus1").prop("checked",true);
    					}
    					/*------下拉框赋值--------*/
    					$.ajax({
    						  url:"/ControlText/SelectUserServlet?action=selectRoleUser",
    						  type:"post",
    						  success:function(data){
    							  var info = eval("("+data+")");
    							 // var row = info.data;
    							  var role = body.find("#role1");
    						      var html = '';
    						     
    						      
    							  $.each(info,function(index,item){
    								  html += '<option value="'+item.id+'">'+item.rname+'</option>';
    							  })
    							  role.html(html);
    							//获取新窗口对象
		                        var iframeWindow = layero.find('iframe')[0].contentWindow;
		                        //重新渲染
		                        iframeWindow.layui.form.render();
    							  
    						  }
    					  })
    					$.ajax({
    						  url:"/ControlText/SelectUserServlet?action=selectWorkUser",
    						  type:"post",
    						  success:function(data){
    							  var info = eval("("+data+")");
    							 // var row = info.data;
    							  var role = body.find("#work");
    						      var html = '';    						         						      
    							  $.each(info,function(index,item){
    								  html += '<option value="'+item.id+'">'+item.name+'</option>';
    							  })
    							  role.html(html);
    							//获取新窗口对象
		                        var iframeWindow = layero.find('iframe')[0].contentWindow;
		                        //重新渲染
		                        iframeWindow.layui.form.render();
    							  
    						  }
    					  })
    					  $.ajax({
    						  url:"/ControlText/SelectUserServlet?action=selectPartUser",
    						  type:"post",
    						  success:function(data){
    							  var info = eval("("+data+")");
    							 // var row = info.data;
    							  var role = body.find("#part");
    						      var html = '';
    						     
    						      
    							  $.each(info,function(index,item){
    								  html += '<option value="'+item.id+'">'+item.name+'</option>';
    							  })
    							  role.html(html);
    							//获取新窗口对象
		                        var iframeWindow = layero.find('iframe')[0].contentWindow;
		                        //重新渲染
		                        iframeWindow.layui.form.render();
    							  
    						  }
    					  })
    					//选中下拉框
    					$.ajax({
    						url:"/ControlText/SelectUserServlet?action=selectUserUp",//查询某个角色
    						type:"post",
    						data:{"id":id},
    						success:function(data){
    							var info1= eval("("+data+")");    		
    	    					var role='dd[lay-value='+info1+']';
    	    					body.find("#role1").siblings("div.layui-form-select").find('dl').find(role).click();
    							
    						}
    					})
    					$.ajax({
    						url:"/ControlText/SelectUserServlet?action=selectUserUpPart",//查询某个部门
    						type:"post",
    						data:{"id":id},
    						success:function(data){
    							var info1= eval("("+data+")");
   							   var part='dd[lay-value='+info1+']';	    
    	    					body.find("#part").siblings("div.layui-form-select").find('dl').find(part).click();
    	    					
    						}
    					})
    					$.ajax({
    						url:"/ControlText/SelectUserServlet?action=selectUserUpWork",//查询某个职称
    						type:"post",
    						data:{"id":id},
    						success:function(data){
    							var info1= eval("("+data+")");
    							
    							var work='dd[lay-value='+info1+']';

   	    					   body.find("#work").siblings("div.layui-form-select").find('dl').find(work).click();
    						}
    					})
                        //获取新窗口对象
                        var iframeWindow = layero.find('iframe')[0].contentWindow;
                        //重新渲染
                        iframeWindow.layui.form.render();
    				}
    			})
    		}
    	})
    }
   //删除用户
    function delUser(id){
    	$.ajax({
    		url:"SelectUserServlet?action=delUser",
    		data:{"id":id},
    		type:"post",
    		success:function(data){
    			if(data == 1){
    				layer.msg("删除成功")
    				tableIns.reload("#newsList");
    			}
    		}
    	})
    }

    //新增用户
    function addUser(){
    	layui.layer.open({
    		title : "添加用户",
    		type : 2,
    		content : "admin/page/system/user/userAdd.jsp",
    		area:['400px','490px'],
    	})
    }
    
     

})