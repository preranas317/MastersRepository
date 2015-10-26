<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
/* $(function() {
	$.ajax({
		url : "board.json",
		cache:false,
		success : function(data) {
			var m ='${k}';
			
			$("#t1").empty();
			$("#t2").empty();
			$("#t3").empty();
			$("#t4").empty();
			$("#t5").empty();
			$("#t6").empty();
			$("#t7").empty();
			$("#t8").empty();
			$("#t9").empty();
			$("#m1").append("json....");
			
			
			$("#t1").append("<a href='tic2.html?k="+m+"&i=0&j=0'>"+data.boardList[m].sarray[0][0]);
			$("#t2").append("<a href='tic2.html?k="+m+"&i=0&j=1'>"+data.boardList[m].sarray[0][1]);		

			$("#t3").append("<a href='tic2.html?k="+m+"&i=0&j=2'>"+data.boardList[m].sarray[0][2]);
			$("#t4").append("<a href='tic2.html?k="+m+"&i=1&j=0'>"+data.boardList[m].sarray[1][0]);
			$("#t5").append("<a href='tic2.html?k="+m+"&i=1&j=1'>"+data.boardList[m].sarray[1][1]);
			$("#t6").append("<a href='tic2.html?k="+m+"&i=1&j=2'>"+data.boardList[m].sarray[1][2]);
			$("#t7").append("<a href='tic2.html?k="+m+"&i=2&j=0'>"+data.boardList[m].sarray[2][0]);
			$("#t8").append("<a href='tic2.html?k="+m+"&i=2&j=1'>"+data.boardList[m].sarray[2][1]);
			$("#t9").append("<a href='tic2.html?k="+m+"&i=2&j=2'>"+data.boardList[m].sarray[2][2]);
		
						



		},
	});
	update();
});




function update() {
	$("#m1").append("ala...");
	$.ajax({
		
		url : "board.deferred.json",
		cache:false,
		success : function(data) {
			$("#m1").empty();
		
			
		var m ='${k}';
			$("#t1").empty();
			$("#t2").empty();
			$("#t3").empty();
			$("#t4").empty();
			$("#t5").empty();
			$("#t6").empty();
			$("#t7").empty();
			$("#t8").empty();
			$("#t9").empty();
			
			
			$("#t1").append("<a href='tic2.html?k="+m+"&i=0&j=0'>"+data[m].sarray[0][0]);
			$("#t2").append("<a href='tic2.html?k="+m+"&i=0&j=1'>"+data[m].sarray[0][1]);		

			$("#t3").append("<a href='tic2.html?k="+m+"&i=0&j=2'>"+data[m].sarray[0][2]);
			$("#t4").append("<a href='tic2.html?k="+m+"&i=1&j=0'>"+data[m].sarray[1][0]);
			$("#t5").append("<a href='tic2.html?k="+m+"&i=1&j=1'>"+data[m].sarray[1][1]);
			$("#t6").append("<a href='tic2.html?k="+m+"&i=1&j=2'>"+data[m].sarray[1][2]);
			$("#t7").append("<a href='tic2.html?k="+m+"&i=2&j=0'>"+data[m].sarray[2][0]);
			$("#t8").append("<a href='tic2.html?k="+m+"&i=2&j=1'>"+data[m].sarray[2][1]);
			$("#t9").append("<a href='tic2.html?k="+m+"&i=2&j=2'>"+data[m].sarray[2][2]);		
			
			update1();
		},
		
	});
	
}
  */

  $(function() {
		$.ajax({
			url : "checkPlayer.json",
			cache:false,
			success : function(data) {
				
				var m ='${k}';
				$("#t1").empty();
				$("#t2").empty();
				$("#t3").empty();
				$("#t4").empty();
				$("#t5").empty();
				$("#t6").empty();
				$("#t7").empty();
				$("#t8").empty();
				$("#t9").empty();
				$("#m1").append("json....");
				
				var j= data.checkList[m].names.split(",");
				
				
				if(j[0] ==='${player.username}'){
					$("#m1").append(j[0]);
					$("#m1").append("<br>"+data.checkList[m].flag);
						if(data.checkList[m].flag === true){
							$("#m1").append("<br> in flag wala if...");
							$("#t1").append("<a href='tic2.html?k="+m+"&i=0&j=0'>"+data.checkList[m].board.sarray[0][0]);
							$("#t2").append("<a href='tic2.html?k="+m+"&i=0&j=1'>"+data.checkList[m].board.sarray[0][1]);		
	
							$("#t3").append("<a href='tic2.html?k="+m+"&i=0&j=2'>"+data.checkList[m].board.sarray[0][2]);
							$("#t4").append("<a href='tic2.html?k="+m+"&i=1&j=0'>"+data.checkList[m].board.sarray[1][0]);
							$("#t5").append("<a href='tic2.html?k="+m+"&i=1&j=1'>"+data.checkList[m].board.sarray[1][1]);
							$("#t6").append("<a href='tic2.html?k="+m+"&i=1&j=2'>"+data.checkList[m].board.sarray[1][2]);
							$("#t7").append("<a href='tic2.html?k="+m+"&i=2&j=0'>"+data.checkList[m].board.sarray[2][0]);
							$("#t8").append("<a href='tic2.html?k="+m+"&i=2&j=1'>"+data.checkList[m].board.sarray[2][1]);
							$("#t9").append("<a href='tic2.html?k="+m+"&i=2&j=2'>"+data.checkList[m].board.sarray[2][2]);	
						}
						else{
							$("#t1").append(data.checkList[m].board.sarray[0][0]);
							$("#t2").append(data.checkList[m].board.sarray[0][1]);
							$("#t3").append(data.checkList[m].board.sarray[0][2]);
							$("#t4").append(data.checkList[m].board.sarray[1][0]);
							$("#t5").append(data.checkList[m].board.sarray[1][1]);
							$("#t6").append(data.checkList[m].board.sarray[1][2]);
							$("#t7").append(data.checkList[m].board.sarray[2][0]);
							$("#t8").append(data.checkList[m].board.sarray[2][1]);
							$("#t9").append(data.checkList[m].board.sarray[2][2]);	
							}
			
				}
				else{
					$("#m1").append(j[1]);
					$("#m1").append("<br>"+data.checkList[m].flag);
					if(data.checkList[m].flag === false){
						$("#m1").append("<br> inelse flag wala if...");
						$("#t1").append("<a href='tic2.html?k="+m+"&i=0&j=0'>"+data.checkList[m].board.sarray[0][0]);
						$("#t2").append("<a href='tic2.html?k="+m+"&i=0&j=1'>"+data.checkList[m].board.sarray[0][1]);		

						$("#t3").append("<a href='tic2.html?k="+m+"&i=0&j=2'>"+data.checkList[m].board.sarray[0][2]);
						$("#t4").append("<a href='tic2.html?k="+m+"&i=1&j=0'>"+data.checkList[m].board.sarray[1][0]);
						$("#t5").append("<a href='tic2.html?k="+m+"&i=1&j=1'>"+data.checkList[m].board.sarray[1][1]);
						$("#t6").append("<a href='tic2.html?k="+m+"&i=1&j=2'>"+data.checkList[m].board.sarray[1][2]);
						$("#t7").append("<a href='tic2.html?k="+m+"&i=2&j=0'>"+data.checkList[m].board.sarray[2][0]);
						$("#t8").append("<a href='tic2.html?k="+m+"&i=2&j=1'>"+data.checkList[m].board.sarray[2][1]);
						$("#t9").append("<a href='tic2.html?k="+m+"&i=2&j=2'>"+data.checkList[m].board.sarray[2][2]);	
								}
					
					
					else{
							$("#t1").append(data.checkList[m].board.sarray[0][0]);
							$("#t2").append(data.checkList[m].board.sarray[0][1]);
							$("#t3").append(data.checkList[m].board.sarray[0][2]);
							$("#t4").append(data.checkList[m].board.sarray[1][0]);
							$("#t5").append(data.checkList[m].board.sarray[1][1]);
							$("#t6").append(data.checkList[m].board.sarray[1][2]);
							$("#t7").append(data.checkList[m].board.sarray[2][0]);
							$("#t8").append(data.checkList[m].board.sarray[2][1]);
							$("#t9").append(data.checkList[m].board.sarray[2][2]);	
					}
				}



			},
		});
		update();
	});




	function update() {
		$("#m1").append("deferred...");
		var m ='${k}';
		$.ajax({
			
			url : "checkPlayer.deferred.json",
			cache:false,
			success : function(data) {
				$("#m1").empty();
			
				$("#m1").appned("in deferred..");
			
				$("#t1").empty();
				$("#t2").empty();
				$("#t3").empty();
				$("#t4").empty();
				$("#t5").empty();
				$("#t6").empty();
				$("#t7").empty();
				$("#t8").empty();
				$("#t9").empty();
			var j= data[m].names.split(",");
				
				$("#m1").append("<br> player name :"+'${player.username}');
				if(j[0] ==='${player.username}'){
					$("#m1").append(j[0]);
					$("#m1").append("<br>"+data[m].flag);
					if(data[m].flag === true){
						
						$("#t1").append("<a href='tic2.html?k="+m+"&i=0&j=0'>"+data[m].board.sarray[0][0]);
						$("#t2").append("<a href='tic2.html?k="+m+"&i=0&j=1'>"+data[m].board.sarray[0][1]);		

						$("#t3").append("<a href='tic2.html?k="+m+"&i=0&j=2'>"+data[m].board.sarray[0][2]);
						$("#t4").append("<a href='tic2.html?k="+m+"&i=1&j=0'>"+data[m].board.sarray[1][0]);
						$("#t5").append("<a href='tic2.html?k="+m+"&i=1&j=1'>"+data[m].board.sarray[1][1]);
						$("#t6").append("<a href='tic2.html?k="+m+"&i=1&j=2'>"+data[m].board.sarray[1][2]);
						$("#t7").append("<a href='tic2.html?k="+m+"&i=2&j=0'>"+data[m].board.sarray[2][0]);
						$("#t8").append("<a href='tic2.html?k="+m+"&i=2&j=1'>"+data[m].board.sarray[2][1]);
						$("#t9").append("<a href='tic2.html?k="+m+"&i=2&j=2'>"+data[m].board.sarray[2][2]);	
								}
								else{
									$("#t1").append(data[m].board.sarray[0][0]);
									$("#t2").append(data[m].board.sarray[0][1]);
									$("#t3").append(data[m].board.sarray[0][2]);
									$("#t4").append(data[m].board.sarray[1][0]);
									$("#t5").append(data[m].board.sarray[1][1]);
									$("#t6").append(data[m].board.sarray[1][2]);
									$("#t7").append(data[m].board.sarray[2][0]);
									$("#t8").append(data[m].board.sarray[2][1]);
									$("#t9").append(data[m].board.sarray[2][2]);	
									}
						
			
				}
				else{
					
					$("#m1").append(j[1]);
					$("#m1").append("<br>"+data[m].flag);
					if(data[m].flag === false){
						
						$("#t1").append("<a href='tic2.html?k="+m+"&i=0&j=0'>"+data[m].sarray[0][0]);
						$("#t2").append("<a href='tic2.html?k="+m+"&i=0&j=1'>"+data[m].sarray[0][1]);		

						$("#t3").append("<a href='tic2.html?k="+m+"&i=0&j=2'>"+data[m].sarray[0][2]);
						$("#t4").append("<a href='tic2.html?k="+m+"&i=1&j=0'>"+data[m].sarray[1][0]);
						$("#t5").append("<a href='tic2.html?k="+m+"&i=1&j=1'>"+data[m].sarray[1][1]);
						$("#t6").append("<a href='tic2.html?k="+m+"&i=1&j=2'>"+data[m].sarray[1][2]);
						$("#t7").append("<a href='tic2.html?k="+m+"&i=2&j=0'>"+data[m].sarray[2][0]);
						$("#t8").append("<a href='tic2.html?k="+m+"&i=2&j=1'>"+data[m].sarray[2][1]);
						$("#t9").append("<a href='tic2.html?k="+m+"&i=2&j=2'>"+data[m].sarray[2][2]);	
								}
								else{
									$("#t1").append(data[m].board.sarray[0][0]);
									$("#t2").append(data[m].board.sarray[0][1]);
									$("#t3").append(data[m].board.sarray[0][2]);
									$("#t4").append(data[m].board.sarray[1][0]);
									$("#t5").append(data[m].board.sarray[1][1]);
									$("#t6").append(data[m].board.sarray[1][2]);
									$("#t7").append(data[m].board.sarray[2][0]);
									$("#t8").append(data[m].board.sarray[2][1]);
									$("#t9").append(data[m].board.sarray[2][2]);	
									}
					
				}

				update();
			},
			
		});
		
	}

</script>

</head>
<body>

	<p id="m1"></p>	
		
		
	<table border="1">

		<tr>
			<td id="t1"></td>
			<td id="t2"></td>
			<td id="t3"></td>
		</tr>

		<tr>
			<td id="t4"></td>
			<td id="t5"></td>
			<td id="t6"></td>
		</tr>
		<tr>
			<td id="t7"></td>
			<td id="t8"></td>
			<td id="t9"></td>
		</tr>

	</table>
	<p id="link1"></p>
</body>
</html>
		
