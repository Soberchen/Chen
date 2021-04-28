 layui.extend({    dtree: '{/}admin/js/lay-module/layui_ext/dtree/dtree'   // {/}的意思即代表采用自有路径，即不跟随 base 路径
}).config({
	base:'/ControlText/admin/js/'
}).extend({
tableMerge:'tableMerge'}).use(['form','layer','laydate','table','laytpl','dtree','tableMerge'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        tableMerge=layui.tableMerge;
    	var dtree = layui.dtree, layer = layui.layer, $ = layui.jquery;
    	
   

    /*------------- 加载用户数据 --------------------------------*/
    var tableIns = table.render({
        elem: '#roleList',
        url : 'SelectRoleServlet?action=selectRole',
        toolbar: '#toolbarDemo',
        page : false,
        height: 'full-145',
        limit : 10,
        limits : [5,10,15,20],
        cols : [[
        	{fixed:"left",type: "checkbox", width:50,},
            {field: 'id', title: '编号',  align:'center',hide:true},
            {field: 'rname', title: '角色',merge:true, minWidth:100, align:"center"},
            {field: 'userName', title: '姓名', minWidth:100, align:"center",sort:true},
            {field: 'loginName', title: '登录名',  align:'center',sort:true},
            {field: 'sex', title: '用户性别',sort:true, align:'center',templet:function(d){
                return d.sex == "1" ? "男" : "女";
            }},
           
            {field: 'email', title: '邮箱', minWidth:100, align:"center",sort:true},
            {field: 'mobile', title: '邮箱', minWidth:100, align:"center",sort:true},
            {field: 'type', title: '状态', align:'center',sort:true,templet:function(d){
                return d.type == "0" ? "<span class='layui-badge layui-bg-green'>正常</span>" : "<span class='layui-badge layui-bg-red'>冻结</span>";
            }}
        ]],done:function(){
        	tableMerge.render(this)
        }
    });
    /*------------- 加载用户数据 --end------------------------------*/

    /*-------- 搜索用户 ----------------------------*/
    $("#doSubmit").click(function(){
    	var like = $("#likename").val()
    	 tableIns.reload({
	      url:"http://localhost:8080/ControlText/SelectRoleServlet?action=selectRole&uname="+like,
	      page: {
	        curr: 1 //重新从第 1 页开始
	      }
	    });
    })
    
     //工具栏事件
	  table.on('toolbar(roleList)', function(obj){
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
    function upUser(userid){
    	layui.layer.open({
    		title : "修改用户信息",
    		type : 2,
    		content : "admin/page/system/role/roleInfo.jsp",
    		area:['400px','540px'],
    		success:function(layero, index){
    			$.ajax({
    				url:"/ControlText/SelectRoleServlet?action=selectRoleUp",
    				type:"post",
    				data:{"id":userid},
    				success:function(data){
    					var info = eval('(' + data + ')');
    					info=info[0];
    					var body = layui.layer.getChildFrame('body', index);
    					var uid=info.id;
          				
          				body.find("#roleid").val(uid);
    					body.find("#rname").val(info.rname);
    					
    					//性别(单选)
    					/*var sex2 = info.data.sex;
    					if(sex2 == 1){
    						body.find("#sex1").prop("checked",true);
    					}else{
    						body.find("#sex2").prop("checked",true);
    					}*/
    					//状态(单选)
    					/*var isStatus = info.data.isStatus;
    					if(isStatus == 0){
    						body.find("#isStatus0").prop("checked",true);
    					}else{
    						body.find("#isStatus1").prop("checked",true);
    					}*/
    			//		------下拉框赋值--------
    	/*				$.ajax({
    						  url:"/MenuTest/RoleServlet?action=allRole",
    						  type:"post",
    						  success:function(data){
    							  var info = eval("("+data+")");
    							  var row = info.data;
    							  var role = body.find("#role1");
    							  $.ajax({
    								  url:"/MenuTest/UserServlet?action=queryUserIsRole",	//查询用户是否有角色有返回1,没有返回0
    								  data:{"userid":userid},
    								  type:"post",
    								  success:function(data){
    									  if(data == 0){
    										  var html = '<option value="0">无角色</option>';
    									  }else{
    										  var html = '';
    									  }
    	    							  $.each(row,function(index,item){
    	    								  html += '<option value="'+item.id+'">'+item.rname+'</option>';
    	    							  })
    	    							  role.html(html);
    	    							//获取新窗口对象
    			                        var iframeWindow = layero.find('iframe')[0].contentWindow;
    			                        //重新渲染
    			                        iframeWindow.layui.form.render();
    								  }
    							  })
    							  
    						  }
    					  })*/
    					//  ------下拉框赋值--------
    					//赋值后选中
    				/*	$.ajax({
    						url:"/MenuTest/RoleServlet?action=allRoleUserid",
    						type:"post",
    						data:{"userid":userid},
    						success:function(data){
    							var info2 = eval("("+data+")")
    							if(info2 == 0){
    								var select = 'dd[lay-value="0"]';
        							body.find("#role1").siblings("div.layui-form-select").find('dl').find(select).click();	//菜单样式
    							}else{
    								var select = 'dd[lay-value='+info2.data.roleid+']';
        							body.find("#role1").siblings("div.layui-form-select").find('dl').find(select).click();	//菜单样式
    							}
    						}
    					})*/
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
    		url:"SelectRoleServlet?action=delRole",
    		data:{"id":id},
    		type:"post",
    		success:function(data){
    			if(data == 1){
    				layer.msg("删除成功")
    				tableIns.reload("#roleList");
    			}
    		}
    	})
    }
    
    
    //分配权限
    function hairMenu(userid){
    	layui.layer.open({
    		title : "分配权限",
    		type : 1,
    		content : $('#dtree1'),
    		area:['300px','500px'],
    		success:function(){
    		    //给dtree树加载数据
    			dtree.render({
				  elem: "#dataTree3",
				  url: "/ControlText/SelectRoleServlet?action=allRole",
				  dataStyle: "layuiStyle",  //使用layui风格的数据格式
				  dataFormat: "list",  //配置data的风格为list
				  response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
				  checkbar:true,
				  line: true,  // 显示树线
				  done: function(res, $ul, first){
					  $.ajax({
						  url:"/ControlText/SelectRoleServlet?action=menuByUserid1",
						  type:"post",
						  data:{"id":userid},
						  success:function(res){
							  var cs = eval('(' + res + ')');
							  $.each(cs,function(index,row){
								  console.log(row.id)
								dtree.chooseDataInit("dataTree3",[row.id]); // 初始化选中
							  })
						  }
					  })
  		    	  }
    			});
    		},
    		btn:['分配权限'],
    		yes: function(index, layero){
    			var params = dtree.getCheckbarNodesParam("dataTree3");
    			var infos = JSON.stringify(params);
    			var cs = eval('(' + infos + ')');
    			var menuidList = new Array();	//所有选中值的权限id
    			$.each(cs,function(index,row){
					menuidList[index] = row.nodeId;
    			})
    			$.ajax({
    				url:"/ControlText/SelectRoleServlet?action=menuByUserListId",
    				data:{"array":menuidList,"id":userid},
    				type:"post",
    				traditional:true,
    				success:function(data){
    					console.log(data)
    					var demo = eval('(' + data + ')');
    					if(demo.status == 1){
    						layer.msg(demo.message);
    						layer.close(index)	//关闭
    					}else{
    						layer.msg("分配失败");
    					}
    				}
    			})
    		}
    	})
    }

/*    //分配角色
    function HairRole(userid){
    	layer.open({
    		type:1,
    		title:"分配角色",
    		area:['200px','100px'],
    		content:$('#hairRole'),
    		success:function(){
    			//查询全部角色
    	    	$.ajax({
    	    		url:"/MenuTest/RoleServlet?action=hairRole",
    	    		type:"post",
    	    		dataType:"json",
    	    		success:function(data){
    	    			
    	    		}
    	    	})
    		}
    	})
    }
    */
    //新增用户
    function addUser(){
    	layui.layer.open({
    		title : "添加用户",
    		type : 2,
    		content : "admin/page/system/role/roleAdd.jsp",
    		area:['400px','490px'],
    	})
    }
    
     

})
