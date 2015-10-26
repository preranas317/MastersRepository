<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Join Page</title>
<script type="text/javascript" src="../js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
 $(function(){
    $.ajax({
        url: "join.json",
        success: function( data ) {
        	$("#p2").empty();
        	$("#list1").empty();
        	$("#p2").append("List of Players willing to JOIN a Game.");
        	for(var i =0;i<data.join.length;++i)
        		$("#list1").append("<li>"+data.join[i]+"</li>");
        
        },
    });
    update();
});
function update()
{
    $.ajax({
        url: "join.deferred.json",
        success: function( data ) {
        	$("#p2").empty();
            $("#list1").empty();
            $("#p2").append("List of Players willing to JOIN a Game.");
            for(var i =0;i<data.length;++i)
                $("#list1").append("<li>"+data[i]+"</li>");
            
            update();
        },
    });
}

$(function() {
	$
	.ajax({
		url : "matchPlayer.json",
		success : function(data) {
			
			$("#msg").empty();
		//	$("#t1").empty();
			var m;
			if  (!(data.matchPlayerList.length)) {
				$("#msg").append("waiting for someone to host...");
			} else {
				for (var i = 0; i < data.matchPlayerList.length; i++) {
					//$("#p2").append("<br>" + data.matchPlayerList[i]);
					m = data.matchPlayerList[i].split(",");
					 if (m[1] === '${p.username}') {
						$("#msg").append(m[0]+ " has hosted a game.Waiting for his move");
						window.location.replace("j2.html?k="+i+"&i=99&j=99&a=99");
						
					}
				}
			}
			update3();
		},
	});
});
function update3() {
	$
			.ajax({
				url : "matchPlayer.deferred.json",
				success : function(data) {
					$("#p2").empty();
					$("#msg").empty();
				//	$("#t1").empty();
					var m;
					if  (!(data.length)) {
						$("#msg").append("waiting for someone to host...");
					} else {
						for (var i = 0; i < data.length; i++) {
							//$("#p2").append("<br>" + data.matchPlayerList[i]);
							m = datat[i].split(",");
							 if (m[1] === '${player.username}') {
								$("#msg").append(m[0]+ " has hosted a game.Waiting for his move");
								window.location.replace("j2.html?k="+i+"&i=99&j=99&a=99");
								
							}
						}
					}
					update3();
				},
			});
	
}



</script>




</head>
<body>
<font size="6" face="Comic Sans MS">Welcome <font color="blue">${player.username}</font> </font>
 <ul id="list1">

</ul>
 <!-- <table id="t1" border="1" cellspacing="5" cellpadding="6">
</table> --> 
<p id="p2"></p>
<p id="msg"></p>
<table border="1">

</table>
</body>
</html>