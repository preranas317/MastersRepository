<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${p.username} playing</title>
<script type="text/javascript" src="../js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">


  $(function() {
		$.ajax({
			url : "../user/checkPlayer.json",
			cache:false,
			success : function(data) {
				$("#m1").empty();
				$("#link1").empty();
				var m ='${k}';
				
				
				$("#row1").empty();
				$("#row2").empty();
				$("#row3").empty();
				
				
				if((data.checkList[m].flag) || (data.checkList[m].msg!=null)){
					for(var j =0;j<3;j++){
						if(data.checkList[m].board.sarray[0][j] === 'X'){
							$("#row1").append("<td><font color='blue' size='5'>"+data.checkList[m].board.sarray[0][j]+"</font></td>");}
						else if(data.checkList[m].board.sarray[0][j] === 'O'){
							$("#row1").append("<td><font color='red' size='5'>"+data.checkList[m].board.sarray[0][j]+"</font></td>");}
						else{								
							$("#row1").append("<td><font color='black' size='5'>"+data.checkList[m].board.sarray[0][j]+"</font></td>");}
					}
					for(var j =0;j<3;j++){
						if(data.checkList[m].board.sarray[1][j] === 'X'){
							$("#row2").append("<td><font color='blue' size='5'>"+data.checkList[m].board.sarray[1][j]+"</font></td>");}
						else if(data.checkList[m].board.sarray[1][j] === 'O'){
							$("#row2").append("<td><font color='red' size='5'>"+data.checkList[m].board.sarray[1][j]+"</font></td>");}
						else{								
							$("#row2").append("<td><font color='black' size='5'>"+data.checkList[m].board.sarray[1][j]+"</font></td>");}
					}
					for(var j =0;j<3;j++){
						if(data.checkList[m].board.sarray[2][j] === 'X'){
							$("#row3").append("<td><font color='blue' size='5'>"+data.checkList[m].board.sarray[2][j]+"</font></td>");}
						else if(data.checkList[m].board.sarray[2][j] === 'O'){
							$("#row3").append("<td><font color='red' size='5'>"+data.checkList[m].board.sarray[2][j]+"</font></td>");}
						else{								
							$("#row3").append("<td><font color='black' size='5'>"+data.checkList[m].board.sarray[2][j]+"</font></td>");}
					}
					if(data.checkList[m].msg!=null){
						$("#m1").append("<font face='AR BERKLEY' color='#800080 size='7>"+data.checkList[m].msg+"</font>");
						$("#link1").append("<a href='home.html'>Home</a>");
					}
						
			
				}
				else{
					for(var j =0;j<3;j++){
						if(data.checkList[m].board.sarray[0][j] === 'X'){
							$("#row1").append("<td><font color='blue' size='5'>"+data.checkList[m].board.sarray[0][j]+"</font></td>");}
						else if(data.checkList[m].board.sarray[0][j] === 'O'){
							$("#row1").append("<td><font color='red' size='5'>"+data.checkList[m].board.sarray[0][j]+"</font></td>");}
						else{								
							$("#row1").append("<td><font color='blue' size='5'><a href='j2.html?k="+m+"&i=0&j="+j+"'>"+data.checkList[m].board.sarray[0][j]+"</a></font></td>");}
					}
					for(var j =0;j<3;j++){
						if(data.checkList[m].board.sarray[1][j] === 'X'){
						$("#row2").append("<td><font color='blue' size='5'>"+data[m].board.sarray[1][j]+"</font></td>");}
						else if(data.checkList[m].board.sarray[1][j] === 'O'){
							$("#row2").append("<td><font color='red' size='5'>"+data.checkList[m].board.sarray[1][j]+"</font></td>");}
						else{								
							$("#row2").append("<td><font color='blue' size='5'><a href='j2.html?k="+m+"&i=1&j="+j+"'>"+data.checkList[m].board.sarray[1][j]+"</a></font></td>");}
					}
					for(var j =0;j<3;j++){
						if(data.checkList[m].board.sarray[2][j] === 'X'){
						$("#row3").append("<td><font color='blue'>"+data.checkList[m].board.sarray[2][j]+"</font></td>");}
						else if(data.checkList[m].board.sarray[2][j] === 'O'){
							$("#row3").append("<td><font color='red' size='5'>"+data.checkList[m].board.sarray[2][j]+"</font></td>");}
						else{								
							$("#row3").append("<td><font color='blue' size='5'><a href='j2.html?k="+m+"&i=2&j="+j+"'>"+data.checkList[m].board.sarray[2][j]+"</a></font></td>");}
					}
				}
	
				

			},
		});
		update();
	});




	function update() {
	
		$.ajax({
			
			url : "../user/checkPlayer.deferred.json",
			cache:false,
			success : function(data) {
				$("#m1").empty();
				$("#link1").empty();
				
				var m ='${k}';
				$("#row1").empty();
				$("#row2").empty();
				$("#row3").empty();
				
			
				
				if((data[m].flag)  || (data[m].msg != null)){
					for(var j =0;j<3;j++){
						if(data[m].board.sarray[0][j] === 'X'){
							$("#row1").append("<td><font color='blue' size='5'>"+data[m].board.sarray[0][j]+"</font></td>");}
						else if(data[m].board.sarray[0][j] === 'O'){
							$("#row1").append("<td><font color='red' size='5'>"+data[m].board.sarray[0][j]+"</font></td>");}
						else{								
							$("#row1").append("<td><font color='black' size='5'>"+data[m].board.sarray[0][j]+"</font></td>");}
					}
					for(var j =0;j<3;j++){
						if(data[m].board.sarray[1][j] === 'X'){
							$("#row2").append("<td><font color='blue' size='5'>"+data[m].board.sarray[1][j]+"</font></td>");}
						else if(data[m].board.sarray[1][j] === 'O'){
							$("#row2").append("<td><font color='red' size='5'>"+data[m].board.sarray[1][j]+"</font></td>");}
						else{								
							$("#row2").append("<td><font color='black' size='5'>"+data[m].board.sarray[1][j]+"</font></td>");}
					}
					for(var j =0;j<3;j++){
						if(data[m].board.sarray[2][j] === 'X'){
							$("#row3").append("<td><font color='blue' size='5'>"+data[m].board.sarray[2][j]+"</font></td>");}
						else if(data[m].board.sarray[2][j] === 'O'){
							$("#row3").append("<td><font color='red' size='5'>"+data[m].board.sarray[2][j]+"</font></td>");}
						else{								
							$("#row3").append("<td><font color='black' size='5'>"+data[m].board.sarray[2][j]+"</font></td>");}
					}
					
					if(data[m].msg!=null){
						$("#link1").append("<a href='home.html'>Home</a>");
						$("#m1").append("<font face='AR BERKLEY' color='#800080 size='7>"+data[m].msg+"</font>");
					}
					
						
				}
				else{
					for(var j =0;j<3;j++){
						if(data[m].board.sarray[0][j] === 'X'){
							$("#row1").append("<td><font color='blue' size='5'>"+data[m].board.sarray[0][j]+"</font></td>");}
						else if(data[m].board.sarray[0][j] === 'O'){
							$("#row1").append("<td><font color='red' size='5'>"+data[m].board.sarray[0][j]+"</font></td>");}
						else{								
							$("#row1").append("<td><font color='blue' size='5'><a href='j2.html?k="+m+"&i=0&j="+j+"'>"+data[m].board.sarray[0][j]+"</a></font></td>");}
					}
					for(var j =0;j<3;j++){
						if(data[m].board.sarray[1][j] === 'X'){
						$("#row2").append("<td><font color='blue' size='5'>"+data[m].board.sarray[1][j]+"</font></td>");}
						else if(data[m].board.sarray[1][j] === 'O'){
							$("#row2").append("<td><font color='red' size='5'>"+data[m].board.sarray[1][j]+"</font></td>");}
						else{								
							$("#row2").append("<td><font color='blue' size='5'><a href='j2.html?k="+m+"&i=1&j="+j+"'>"+data[m].board.sarray[1][j]+"</a></font></td>");}
					}
					for(var j =0;j<3;j++){
						if(data[m].board.sarray[2][j] === 'X'){
						$("#row3").append("<td><font color='blue' size='5'>"+data[m].board.sarray[2][j]+"</font></td>");}
						else if(data[m].board.sarray[2][j] === 'O'){
							$("#row3").append("<td><font color='red' size='5'>"+data[m].board.sarray[2][j]+"</font></td>");}
						else{								
							$("#row3").append("<td><font color='blue' size='5'><a href='j2.html?k="+m+"&i=2&j="+j+"'>"+data[m].board.sarray[2][j]+"</a></font></td>");}
					}
				}
				
			
					
				update();
			},
			
		});
		
	}

</script>

</head>
<body>
<font size="6" face="Comic Sans MS">Welcome <font color="blue">${player.username}</font> </font><br><br><br><br>
<table border="1" cellspacing="8" cellpadding="8" background="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcT-MdlBNtlQNDli5oU1IvT82476aGoU9VbbJVbXdmpztOkeDlqG" align="center">
	<tr id ="row1"></tr>
	<tr id ="row2"></tr>
	<tr id ="row3"></tr>
	
	
	</table>
	<p id="m1" align="center"></p>	
		
	
	<p id="link1" align="left"></p>
	<p align="left"><a href="<c:url value="/j_spring_security_logout" />" >logout</a></p> 
</body>
</html>
		
