   var  flag=false;
function ifCont(uname,pass){
	var name=$("#username").val();
	var pwd=$("#password").val();

	if(name==""||pwd==""){
		alert("用户名或密码不能为空！")
		flag=false;
	}
	flag=true;
}
$("#cc").submit(function(){
	return flag;
});