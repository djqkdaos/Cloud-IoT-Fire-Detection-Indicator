function deletemap(data) {
	var execute = confirm("정말 삭제하시겠습니까?");
	if(execute)
		location.href="/deleteMap.do?hash="+data;
}

function updatename(hash,idx,old_name) {
	var execute = confirm("수정하시겠습니까?");
	if(execute){
		location.href="/updatename.do?hash="+hash+"&val="+$("#new_name"+idx).val()+"&old_name="+old_name;
		 
	}
}
