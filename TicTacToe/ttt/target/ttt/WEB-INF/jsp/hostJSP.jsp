<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Host Page</title>
<script type="text/javascript" src="../js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	 $(function() {
		$.ajax({
			url : "host.json",
			success : function(data) {
				$("#p1").empty();
				$("#s1").empty();
				$("#s1").append("Hosting players");
				$("#list").empty();
				for (var i = 0; i < data.hosts.length; ++i)
					$("#list").append("<li>" + data.hosts[i] + "</li>");

			},
		});
		update();
	});
	function update() {
		$.ajax({
			url : "host.deferred.json",
			success : function(data) {
				$("#p1").empty();
				$("#s1").empty();
				$("#s1").append("Hosting players");
				$("#list").empty();
				for (var i = 0; i < data.length; ++i)
					$("#list").append("<li>" + data[i] + "</li>");

				update();
			},
		});
	}
 
	$(function() {
		$
		.ajax({
			url : "matchPlayer.json",
			success : function(data) {
				$("#p2").empty();
				$("#msg").empty();
				$("#t1").empty();
				$("#m1").empty();
				var m;
			
				if (!(data.matchPlayerList.length)) {
					$("#msg").append("waiting for smeone to join...");
				} else {
					for (var i = 0; i < data.matchPlayerList.length; i++) {
						
						m = data.matchPlayerList[i].split(",");
						if (m[0] === '${player.username}') {
							$("#msg").append(m[1]+ " has joined a game. Waiting for ur move ");
							$("#msg").append("i:"+i);
							//window.location.replace("tic2.html?k="+i+"&i=99&j=99&a=99");
							window.location.replace("h2.html?k="+i+"&i=99&j=99&a=99");
								
							}
						
							
						}
					}
				
				
			},
		});
		update3();
	});

 function update3() {
		$
				.ajax({
					url : "matchPlayer.deferred.json",
					success : function(data) {
						$("#p2").empty();
						$("#msg").empty();
						$("#t1").empty();
						$("#m1").empty();
						var m;
					
						if ((data.length === 0)) {
							$("#msg").append("waiting for smeone to join...");
						} else {
							for (var i = 0; i < data.length; i++) {
								
								m = data[i].split(",");
								if (m[0] === '${p.username}') {
									$("#msg").append(m[1]+ " has joined a game. Waiting for ur move ");
									$("#msg").append("i:"+i);
									window.location.replace("h2.html?k="+i+"&i=99&j=99&a=99");
							//	window.location.replace("tic2.html?k="+i+"&i=99&j=99&a=99");
										//updateCheck();
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
	<p id="s1"></p>
	<ul id="list"></ul>
	<p id="p2"></p>
	<p id="msg"></p>
	<p id="m1"></p>
	
	

</body>
</html>