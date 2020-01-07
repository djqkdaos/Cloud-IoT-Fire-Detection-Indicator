$(document).ready(function(){
	
	servertest();
	getchart();
	servertestcpu();
	
	var stompClient = null;

	connect();
	function setConnected(connected) {
	    $("#connect").prop("disabled", connected);
	    $("#disconnect").prop("disabled", !connected);
	    if (connected) {
	        $("#conversation").show();
	    }
	    else {
	        $("#conversation").hide();
	    }
	    $("#greetings").html("");
	}

	function connect() {
	    var socket = new SockJS('/websockethandler');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	    	
	    	
	        setConnected(true);
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/topic/greetings', function (greeting) {
	            var contact = JSON.parse(greeting.body);
	            var audio = new Audio('images/alarmsound.mp3');
	        	audio.play();
	            var date = new Date(contact.firedate*1000);
	            var hours = date.getHours();
	            var minutes = "0" + date.getMinutes();
	            var seconds = "0" + date.getSeconds();
	            
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
	    setConnected(false);
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
	
	
	
	
$(function(){
	timer = setInterval( function () {
		servertestcpu();
		servertest();
		getchart();
	},10000);
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
			alert('getDtection');
			  values = dataa.list ; // java에서 정의한 ArrayList명을 적어준다.
              $.each(values, function( index, result ) {
		    		var img = document.createElement('img'); // 이미지 객체 생성
		    		if(result.s_uuid == s_uuid){
		    			img.src = 'images/detectorred.png'; // 이미지 경로 설정 빨강
		    		}else{
		    			img.src = 'images/detector.png'; // 이미지 경로 설정 검정
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
		            document.getElementById("mapdiv").appendChild(img); // board
																		// DIV 에
																		// 이미지
																		// 동적 추가
		            
		    	});
		
		}
	})
	}
	
	
	function servertest(){
		$.ajax({
			url: "sinfo/metrics/jvm.memory.used",
			method: "GET",
			contentType: 'application/json',
			datatype: "JSON",
			success: function(dataa) {
				$("#memo").html(Math.floor((((dataa.measurements[0].value)/1024)/1024))+'MB');
				$('#easypiechart-teal').data('easyPieChart').update(dataa.measurements[0].value/1024/1024/5/1.15);
			}
		})
	}
	function servertestcpu(){
		$.ajax({
			url: "sinfo/metrics/system.cpu.usage",
			method: "GET",
			contentType: 'application/json',
			datatype: "JSON",
			success: function(dataa) {
				$("#cpus").html(Math.floor(dataa.measurements[0].value*100)+'%');
				$('#easypiechart-orange').data('easyPieChart').update(dataa.measurements[0].value*100);
			}
		})
	}
	function getchart(){
		$.ajax({
			url: "monitordata",
			method: "GET",
			contentType: 'application/json',
			datatype: "JSON",
			success: function(dataa) {
				$('#easypiechart-red1').data('easyPieChart').update((dataa.no1*2));
				$('#easypiechart-red2').data('easyPieChart').update((dataa.no2*2));
				$('#easypiechart-red3').data('easyPieChart').update((dataa.no3*2));
				$("#n1").html(dataa.no1+'v');
				$("#n2").html(dataa.no2+'v');
				$("#n3").html(dataa.no3+'v');
				$("#n4").html(dataa.status);
				var lineChartData = {
						labels : dataa.labels,
						datasets : [
							{
								label: "No.1",
								fillColor : "rgba(220,220,220,0.2)",
								strokeColor : "rgba(220,220,220,1)",
								pointColor : "rgba(220,220,220,1)",
								pointStrokeColor : "#fff",
								pointHighlightFill : "#fff",
								pointHighlightStroke : "rgba(220,220,220,1)",
								data : dataa.data1
							},
							{
								label: "No.2",
								fillColor : "rgba(48, 164, 255, 0.2)",
								strokeColor : "rgba(48, 164, 255, 1)",
								pointColor : "rgba(48, 164, 255, 1)",
								pointStrokeColor : "#fff",
								pointHighlightFill : "#fff",
								pointHighlightStroke : "rgba(48, 164, 255, 1)",
								data : dataa.data2
							},
							{
								label: "No.3",
								fillColor : "rgba(60, 60, 60, 0.2)",
								strokeColor : "rgba(60, 60, 60, 1)",
								pointColor : "rgba(60, 60, 60, 1)",
								pointStrokeColor : "#fff",
								pointHighlightFill : "#fff",
								pointHighlightStroke : "rgba(60,60,60, 1)",
								data : dataa.data3
							}
						]

					}
				var chart1 = document.getElementById("line-chart").getContext("2d");
				window.myLine = new Chart(chart1).Line(lineChartData, {
					responsive: true
				});
			}
		})
	}
	
		
	
	
	
		
/*
 * function loadsensor(data,s_uuid){ $.ajax({ url: "getposition", method:
 * "POST", contentType: 'application/json', datatype: "JSON", data:
 * JSON.stringify({ "floor" : data }), success: function(dataa) {
 * 
 * values = dataa.list ; //java에서 정의한 ArrayList명을 적어준다. $.each(values, function(
 * index, result ) { var img = document.createElement('img'); // 이미지 객체 생성
 * if(result.s_uuid == s_uuid){ img.src = 'images/detectorred.png'; // 이미지 경로 설정
 * 빨강 }else{ img.src = 'images/detector.png'; // 이미지 경로 설정 검정 }
 * img.style.width='25px'; img.style.height='25px';
 * img.style.position='absolute'; img.style.left=result.x+'px';
 * img.style.top=result.y+'px'; var idtmp; if(result.sub_num!=0){
 * idtmp=result.s_uuid+"A"+result.sub_num; }else{ idtmp=result.s_uuid; }
 * img.setAttribute("id",idtmp); img.setAttribute("data-trigger","hover");
 * if(result.sub_num!=0){
 * img.setAttribute("data-content",result.s_uuid+"#"+result.sub_num);; }else{
 * img.setAttribute("data-content",result.s_uuid); }
 * img.setAttribute("data-original-title","감지기 ID");
 * img.setAttribute("data-placement","bottom");
 * document.getElementById("mapdiv").appendChild(img); // board DIV 에 이미지 동적 추가
 * 
 * });
 *  } }) }
 */
	
   
});
var map = L.map('map').setView([37.495183,127.122357], 15);

L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

var pulsingIcon = L.icon.pulse({iconSize:[30,30],color:'red'});
L.marker([37.495183,127.122357],{icon: pulsingIcon,title: 'This is pulsing icon'}).addTo(map);