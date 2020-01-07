$(document).ready(function(){
	$(".popover-top").popover({trigger: 'hover click','placement': 'top'});
	loadsensor();

	$("#mapimg").attr({src: 'resources/mapfile/'+$("#maplist").val(), style: "max-width: 100%; height: auto; position: absolute;"});
	function loadsensor(){
	$.ajax({
		url: "getposition",
		method: "POST",
		contentType: 'application/json',
		datatype: "JSON",
		data: JSON.stringify({
			"floor" : $("#maplist").val()
		}),
		success: function(dataa) {
			  values = dataa.list ; //java에서 정의한 ArrayList명을 적어준다.
              $.each(values, function( index, result ) {
		    		var img = document.createElement('img'); // 이미지 객체 생성
		            img.src = 'images/detector.gif'; // 이미지 경로 설정 (랜덤)
		            img.style.width='25px';
		            img.style.height='25px';
		            img.style.position='absolute';
		            img.style.left=result.x+'px';
		            img.style.top=result.y+'px';
		            var idtmp;
		            if(result.sub_num!=0){
		            	idtmp=result.s_uuid+"A"+result.sub_num;
		            }else{
		            	idtmp=result.s_uuid;
		            }
		            img.setAttribute("id",idtmp);
		            img.setAttribute("data-trigger","hover");
		            if(result.sub_num!=0){
		            	img.setAttribute("data-content",result.s_uuid+"#"+result.sub_num);;
		            }else{
		            	img.setAttribute("data-content",result.s_uuid);
		            }
		            img.setAttribute("data-original-title","감지기 ID");
		            img.setAttribute("data-placement","bottom");
		            document.getElementById("mapdiv").appendChild(img); // board DIV 에 이미지 동적 추가
		            $("#"+idtmp).animate({scrollTop : 300}, 100);
		            $("#"+idtmp).draggable({ containment: "#mapimg", scroll: false });
		            $("#"+idtmp).popover();
		            $("#"+idtmp).mouseup(function dataupload(){
		            	$.ajax({
		        			url: "setposition",
		        			method: "POST",
		        			contentType: 'application/json',
		        			datatype: "JSON",
		        			data: JSON.stringify({
		        				"s_uuid" : idtmp,
		        				"x" : $( "#"+idtmp ).position().left,
		        				"y" : $( "#"+idtmp ).position().top,
		        				"floor" : $("#maplist").val()
		        			}),
		        			success: function(dataa) {
		        				alert('완료');
		        			},
		        			error: function (error) {
		        			    alert('error ' + eval(error));
		        			}
		        		})
		            });
		    	});
		
		}
	})
	}
    $("#gamzilist").on("change", function(){
        make($(this).val());
    });
    $("#maplist").on("change", function(){
        console.log($(this).val());
        $("#box_personal").empty();
        $("#box_personal").html('<div class="row rere" id="mapdiv"><img src="" id="mapimg"></div>');
        $("#mapimg").attr({src: 'resources/mapfile/'+$(this).val(), style: "max-width: 100%; height: auto;"});
        loadsensor();
    });
    $("#gamziadd").click(function() {
    	make($("#gamzilist").val());
	})
	
    function make(data){
    	var cnt=1;
    	if($("#" + data).length == 0) {
    		var img = document.createElement('img'); // 이미지 객체 생성
            img.src = 'images/detector.gif'; // 이미지 경로 설정 (랜덤)
            img.style.width='35px';
            img.style.height='35px';
            img.setAttribute("id",data);
            document.getElementById("mapdiv").appendChild(img); // board DIV 에 이미지 동적 추가
            $("#"+data).draggable({ containment: "#mapimg", scroll: false });
            /*$("#"+data).mouseup(dataupload(data));*/
            $("#"+data).mouseup(function dataupload(){
            	alert("left: " + $( "#"+data ).position().left + ", top: " + $( "#"+data ).position().top);
            	$.ajax({
        			url: "setposition",
        			method: "POST",
        			contentType: 'application/json',
        			datatype: "JSON",
        			data: JSON.stringify({
        				"s_uuid" : data,
        				"x" : $( "#"+data ).position().left,
        				"y" : $( "#"+data ).position().top,
        				"floor" : $("#maplist").val()
        			}),
        			success: function(dataa) {
        				alert(dataa);
        			}
        		})
            });
    	}else{
    		while($("#"+data+"A"+cnt).length != 0){
    			cnt+=1;
    		}
    		var img = document.createElement('img'); // 이미지 객체 생성
            img.src = 'images/detector.png'; // 이미지 경로 설정 (랜덤)
            img.style.width='25px';
            img.style.height='25px';
            img.setAttribute("id",data+"A"+cnt);
            document.getElementById("mapdiv").appendChild(img); // board DIV 에 이미지 동적 추가
            $("#"+data+"A"+cnt).draggable({ containment: "#mapimg", scroll: false });
            $("#"+data+"A"+cnt).mouseup(function dataupload(){
            	alert("left: " + $("#"+data+"A"+cnt).position().left + ", top: " + $("#"+data+"A"+cnt).position().top);
            	$.ajax({
        			url: "setposition",
        			method: "POST",
        			contentType: 'application/json',
        			datatype: "JSON",
        			data: JSON.stringify({
        				"s_uuid" : data+"A"+cnt,
        				"x" : $("#"+data+"A"+cnt).position().left,
        				"y" : $("#"+data+"A"+cnt).position().top,
        				"floor"  : $("#maplist").val()
        			}),
        			success: function(dataa) {
        				alert(dataa);
        			}
        		})
            });
    	}
    }
});