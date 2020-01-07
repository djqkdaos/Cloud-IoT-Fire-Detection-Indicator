$(window).on('load', function() {
	loadsensor('4efc76a86d428af53ec0c3478a3c5cd0b93a33fbc13f0b6490e5c40e36ffa098/2.png','1900065c-d2f3-4bb6-bc82-9b12d48111d8')
	function loadsensor(data,s_uuid){
		$.ajax({
			url: "getposition",
			method: "POST",
			contentType: 'application/json',
			datatype: "JSON",
			data: JSON.stringify({
				"floor" : data
			}),
			success: function(dataa) {
				  values = dataa.list ; //java에서 정의한 ArrayList명을 적어준다.
	              $.each(values, function( index, result ) {
			    		var img = document.createElement('img'); // 이미지 객체 생성
			    		img.src = 'images/detector.gif'; // 이미지 경로 설정 검정
			            img.style.width='25px';
			            img.style.height='25px';
			            img.style.position='absolute';
			            img.style.left=(result.x+40)+'px';
			            img.style.top=(result.y+30)+'px';
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
			            
			    	});
			
			}
		})
		}
})