
function judgeCode(id,url) {
		var item={
		   "id" : id,
		   "url":url
		}
		$("#cont").load(contextPath+"/"+url,item);
	}