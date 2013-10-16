<%@page import="fr.paperciv.objs.User"%>
<%@page import="fr.paperciv.common.PaperSession"%>
<%
	User user = PaperSession.getUserSession(request);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title>PaperCiv</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
   	<link href="css/menu.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
    	$(document).ready(function(){
    		checkSession();
    	});
    	
    	function checkSession(){
   			$("#login").hide();
   			$("#menu").hide();
   			$("#skirmish").hide();
   			
   			<%if(user==null){%>
   				$("#login").slideDown(500);
   			<%}else{%>
   				$("#menu").slideDown(500);
   			<%}%>
    	}
    </script>
  </head>
  <body>
  <img class="fond" src="img/fondMenu.png" alt="" />
 	<br /><br />
  	<br /><br />
	<div align="center">
		<div style="background-color: lightgray;text-align: center;width: 25%;border: 5px ridge grey; opacity: 0.9;">
			<br /><br />
			<b style="font-size: 22px;"><u>PAPERCIV v0.6 alpha</u></b>
			<br /><br />
			<div id="message" style="color: red;"></div>
	 		<br /><br />
			<div id="login">
				<jsp:include flush="true" page="include/login.jsp" />
			</div>
			<div id="menu">
				<jsp:include flush="true" page="include/menu.jsp" />
			</div>
			<div id="skirmish">
				<jsp:include flush="true" page="include/skirmish.jsp" />
			</div>
			<br /><br />
	 		<br /><br />
		</div>
	</div>
  </body>
</html>
