<%@page import="fr.paperciv.factories.XmlFactory"%>
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
    <script type="text/javascript" src="http://mrdoob.github.com/three.js/build/three.js"></script>
    <script type="text/javascript" src="http://mrdoob.github.com/three.js/examples/fonts/optimer_regular.typeface.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
   	<link href="css/menu.css" rel="stylesheet" type="text/css" />
   	<script type="text/javascript">
	   	$(document).ready(function(){
	   		var user = $.parseJSON('<%=XmlFactory.getJSONStringFromObject(user)%>');
	   		
	   		checkSession(user);
	   		
	   		init();	
	   		animate();
	   	});
   	</script>
  </head>
  <body>
	<div id="mainmenuwrapper">
		<br />
		<b style="font-size: 22px;"><u>v0.7 alpha</u></b>
		<br /><br />
		<div id="message" style="color: red;"></div>
 		<br />
		<div id="login">
			<jsp:include flush="true" page="include/login.jsp" />
		</div>
		<div id="menu">
			<jsp:include flush="true" page="include/menu.jsp" />
		</div>
		<div id="skirmish">
			<jsp:include flush="true" page="include/skirmish.jsp" />
		</div>
		<br />
	</div>
	<div id="chatwrapper">
		<br />	
		<b style="font-size: 22px;">Paper Chat</b>
		<br /><br />
		<input type="button" id="chattogglebutton" value="Connexion" onclick="toggleChatConnection();" />
		<br /><br />
		<div id="chatbox">
			<textarea id="chatoutput" cols="40" rows="8" disabled="disabled" style="resize:none;font-weight:bold;"></textarea>
			<br /><br />
			<input type="text" size="30" id="chatinput" /><input type="button" value="Envoyer" onclick="submitChatMessage();" />
			<br /><br />
		</div>
	</div>
  </body>
</html>
