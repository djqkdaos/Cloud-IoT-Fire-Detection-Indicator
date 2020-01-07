$(document).ready(function(){
	$("#mapimg").attr({src: 'resources/mapfile/'+$("#maplist").val(), style: "max-width: 100%; height: auto;"});
	
    $("#maplist").on("change", function(){
        console.log($(this).val());
        $("#box_personal").empty();
        $("#box_personal").html('<div class="row rere" id="mapdiv"><img src="" id="mapimg"></div>');
        $("#mapimg").attr({src: 'resources/mapfile/'+$(this).val(), style: "max-width: 100%; height: auto;"});
    });
});