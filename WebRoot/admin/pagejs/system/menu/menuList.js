layui.extend({
	treeTable: '{/}admin/js/lay-module/treetable-lay/treeTable'   // {/}的意思即代表采用自有路径，即不跟随 base 路径
}).use(['form','layer','laydate','table','laytpl','treeTable'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    var treeTable=layui.treeTable;
    	var dtree = layui.dtree, layer = layui.layer, $ = layui.jquery;
        // 渲染表格
		var insTb = treeTable.render({
            elem: '#menuList',
            url: 'SelectMenuServlet?action=SelectMenu',
            toolbar: '#toolbarDemo',
            height: 'full-115',
            tree: {
                iconIndex: 2,           // 折叠图标显示在第几列
                isPidData: true,        // 是否是id、pid形式数据
                idName: 'id',  // id字段名称
                pidName: 'mfatherid'     // pid字段名称
            },
            cols: [[
            	{field: 'id', title: '权限编号',width:10},
                {type: 'radio'},
                {field: 'mname', title: '权限名称'},
                {field: 'mfatherid', title: '权限标识id'},
                {field: 'type', width: 200, align: 'center', title: '类型', templet: function (d) {
                        if (d.type == 3) {
                            return '<span class="layui-badge layui-bg-orange">按钮</span>';
                        }
                        if (d.mfatherid ==0) {
                            return '<span class="layui-badge layui-bg-blue">目录</span>';
                        } else{
                            return '<span class="layui-badge layui-bg-green">菜单</span>';
                        }
                }}
            ]]
        });
		
		//监听工具栏
		treeTable.on('toolbar(menuList)', function(obj){
			var data=insTb.checkStatus()[0];
		    switch(obj.event){
		      case 'btn-expand':	//全部展开
		    	  insTb.expandAll('#menuList');
		      break;
		      
		      case 'btn-fold':	//全部折叠
		    	  insTb.foldAll('#menuList');
		      break;
		      case 'hairMenu':	//分配权限
					if(data.length == 0 || data.length > 1){
						layer.msg("请选择一行数据进行操作")
						return ;
					}else{
						hairMenu(userid);
					}
		      break;
		      case 'addMenu':	//新增权限
		    	  layer.open({
		        		title : "添加权限",
		        		type : 2,
		        		content : "admin/page/system/menu/menuAdd.jsp",
		        		area:['800px','700px'],
		    	  })
		      break;
		      
		      case 'upMenu':	//修改权限
		    	// alert(JSON.stringify(data));
		    	  //alert(data);
		    	  upUser(data.id);
		      break;
			        
		      case 'delMenu':	//删除权限
		    	  layer.confirm('确定删除此权限吗?', {icon: 3, title:'提示'}, function(index){
						delMenu();
						layer.close(index);
		            });
		      break;
		    };
		 });
		
	   //---------删除权限-------
		function delMenu(){
	    	var menuid = '';
	    	JSON.stringify(insTb.checkStatus().map(function (d) {
	    		menuid = d.id;
	        }));
	    	if(menuid == null || menuid == ""){
	    		layer.msg("请选中一个节点进行删除");
	    	}else{
	    		$.ajax({
	    			url:"SelectMenuServlet?action=delMenu",
	    			data:{"id":menuid},
	    			type:"post",
	    			dataType:"json",
	    			success:function(data){
	    				//var info = eval("("+data+")");	    				
	    				if(data== 1){
	    					layer.msg("删除成功");
	    					insTb.reload();
	    				}else{
	    					layer.msg("删除失败");
	    				}
	    			}
	    		})
	    	}
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
					  url: "/Queen/SelectMenuServlet?action=SelectAllMenu",
					  dataStyle: "layuiStyle",  //使用layui风格的数据格式
					  dataFormat: "list",  //配置data的风格为list
					  response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
					  checkbar:true,
					  line: true,  // 显示树线
					  done: function(res, $ul, first){
						  $.ajax({
							  url:"/Queen/SelectMenuServlet?action=menuByUseridType",
							  type:"post",
							  data:{"userid":userid},
							  success:function(res){
								  var cs = eval('(' + res + ')');
								  $.each(cs,function(index,row){
									dtree.chooseDataInit("dataTree3",[row.id]); // 初始化选中
								  })
							  }
						  })
	  		    	  }
	    			});
	    		},
	    		btn:['分配权限'],
	    		yes: function(index,layero){
	    			console.log("11");	
	    			var params = dtree.getCheckbarNodesParam("dataTree3");
	    			var infos = JSON.stringify(params);
	    			var cs = eval('(' + infos + ')');
	    			alert(JSON.stringify(cs));
	    			var menuidList = new Array();	//所有选中值的权限id
	    			alert(menuidList.length);
	    			$.each(cs,function(index,row){
						menuidList[index] = row.nodeId;
	    			})
	    			$.ajax({
	    				url:"/Queen/SelectMenuServlet?action=menuByUserid1",
	    				data:{"array":menuidList,"userid":userid},
	    				type:"post",
	    				traditional:true,
	    				success:function(data){
	    					var demo = eval('(' + data + ')');
	    					if(demo==1){
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
       /*---------修改权限------*/
	    function upUser(id){
	    	alert(id);
	    	layui.layer.open({
	    		title : "修改用户信息",
	    		type : 2,
	    		content : "admin/page/system/menu/menuInfo.jsp",
	    		area:['400px','540px'],
	    		success:function(layero, index){
	    			$.ajax({
	    				url:"/ControlText/SelectMenuServlet?action=selectMenuUp",
	    				type:"post",
	    				data:{"id":id},
	    				success:function(data){
	    					var info = eval('(' + data + ')')[0];
	    					//alert(info);
	          				var body = layui.layer.getChildFrame('body', index);
	          				body.find("#mid").val(info.id);
	    					body.find("#mname").val(info.mname);
	    					body.find("#mfunction").val(info.mfunction);
	    					body.find("#type2").val(info.type);
	    					body.find("#father").val(info.father);
	    				
	    			//		------下拉框赋值--------
	    					$.ajax({
	  						  url:"/ControlText/SelectMenuServlet?action=SelectMenuFather&type="+info.type,
	  						  type:"post",
	  						  async:false,
	  						  success:function(data){
	  							  var info = eval("("+data+")");
//	  							  var row = info.data;
	  							  var role = body.find("#father");
	  							  var html = '';
	  							  $.each(info,function(index,item){
	  								  html += '<option value="'+item.id+'">'+item.mname+'</option>';
	  							  })
	  							  role.html(html);
	  							  form.render("select");
	  							
	  						  }
	  					  })
	    					//  ------下拉框赋值--------
	    					//赋值后选中
	    			/*		$.ajax({
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
});