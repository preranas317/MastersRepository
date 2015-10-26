<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">

$(function(){
    $.ajax({
        url: "match.json",
        success: function( data ) {
        	$("#t1").empty();
        	for(var i =0;i<data.matchList.sarray.length;++i)
        		 $("#t1").append("<tr><td>"+data.matchList.sarray[i]+"</td></tr>");
        
        },
    });
    update1();
});
function update1()
{
    $.ajax({
        url: "match.deferred.json",
        success: function( data ) {
            $("#t1").empty();
            $("#who").html("username");
            for(var i =0;i<data.sarray.length;++i)
            	 $("#t1").append("<tr><td>"+data.sarray[i]+"</td></tr>");
            
            update1();
        },
    });
}
</script>
</head>
<body>

</body>
</html>