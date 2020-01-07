$(window).on('load', function() {
	
	var stompClient = null;

	connect();

	function connect() {
	    var socket = new SockJS('/websockethandler');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	    	
	    	
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/topic/greetings', function (greeting) {
	            var contact = JSON.parse(greeting.body);
	            var audio = new Audio('images/alarmsound.mp3');
	        	audio.play();
	            var date = new Date(contact.firedate*1000);
	            var hours = date.getHours();
	            var minutes = "0" + date.getMinutes();
	            var seconds = "0" + date.getSeconds();
	            var formattedTime = hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
	            $('#noticelist tbody').prepend('<tr><td id="table">new</td>'+
									'<td id="table"><strong>'+formattedTime+'</strong></td>'+
									'<td id="table"><strong>'+contact.s_uuid+'</strong></td>'+
									'<td id="table"><strong>'+contact.address+'</strong></td>'+
									'<td id="table"><strong>'+contact.status+'</strong></td></tr>"');
/*	            $("#mapimg").attr({src: 'resources/mapfile/'+contact.floor+'', style: '"max-width: 100%; height: auto; position: absolute;"'});*/
	            $("#mapimg").attr({src: 'images/sample.png', style: '"max-width: 100%; height: auto; position: absolute;"'});
	            loadsensor(contact.floor, contact.s_uuid);
	        	$("#titleinfo").html('주소 : '+contact.address+', 도면 명 : '+contact.new_name);
	            $.notify({
	                icon: 'images/bell.png',
	                title: '<strong>화재감지</strong><br>',
	                message: '새로운 화재가 감지되었습니다.',
	                animate: {
	            		enter: 'animated bounceIn',
	            		exit: 'animated bounceOut'
	            	}
	            },{
	            	type: 'minimalist',
	           		delay: 5000,
	            		icon_type: 'image',
	            		template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
	            			'<img data-notify="icon" class="img-circle pull-left">' +
	            			'<span data-notify="title">{1}</span>' +
	            			'<span data-notify="message">{2}</span>' +
	            		'</div>'
	            });
	        });
	       


	    });
	}

	function disconnect() {
	    if (stompClient !== null) {
	        stompClient.disconnect();
	    }
	    console.log("Disconnected");
	}

	function sendName() {
	    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
	}

	function showGreeting(message) {
	    $("#greetings").append("<tr><td>" + message + "</td></tr>");
	}

	$(function () {
	    $("form").on('submit', function (e) {
	        e.preventDefault();
	    });
	    $( "#connect" ).click(function() { connect(); });
	    $( "#disconnect" ).click(function() { disconnect(); });
	    $( "#send" ).click(function() { sendName(); });
	});
	
	
	
	

	
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
		    		if(result.s_uuid == s_uuid){
		    			img.src = 'images/detectorred.gif'; // 이미지 경로 설정 빨강
		    		}else{
		    			img.src = 'images/detector.gif'; // 이미지 경로 설정 검정
		    		}
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
		            
		    	});
		
		}
	})
	}
	
   
});