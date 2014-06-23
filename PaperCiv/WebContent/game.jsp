<%@page import="java.util.Properties"%>
<%@page import="fr.paperciv.factories.XmlFactory"%>
<%@page import="fr.paperciv.objs.Player"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.paperciv.objs.map.Area"%>
<%@page import="fr.paperciv.common.PaperSession"%>
<%@page import="fr.paperciv.objs.map.GameMap"%>
<%
	Properties gameProperties = PaperSession.getGameProperties(request);
	GameMap gameMap = PaperSession.getGameMapSession(request);	
	ArrayList<Player> players = PaperSession.getGamePlayersSession(request);
%>
<html>
	<head>
		<title>PaperCiv - Skirmish</title>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript" src="http://mrdoob.github.com/three.js/build/three.js"></script>
		<script type="text/javascript" src="js/PersonnalControl.js"></script>
		<script type="text/javascript" src="js/simplex-noise.js"></script>
		<script type="text/javascript" src="js/textureanim.js"></script>
		<script type="text/javascript" src="js/core.js"></script>
		<link href="css/menu.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">		
			$(document).ready(function (){
				var gameMap = $.parseJSON('<%=XmlFactory.getJSONStringFromObject(gameMap)%>');
				console.warn(gameMap);
					
 				for(var i=0;i<gameMap.Areas.length;i++)
				{
					var area = gameMap.Areas[i];
			
					var vertices = [];
					vertices.push( new THREE.Vector3(area.Vertices[0].X, area.Vertices[0].Y, area.Vertices[0].Z) );					
					vertices.push( new THREE.Vector3(area.Vertices[1].X, area.Vertices[1].Y, area.Vertices[1].Z) );
					vertices.push( new THREE.Vector3(area.Vertices[2].X, area.Vertices[2].Y, area.Vertices[2].Z) );
					vertices.push( new THREE.Vector3(area.Vertices[3].X, area.Vertices[3].Y, area.Vertices[3].Z) );
					
					area.Vertices = vertices;
				}
					 			
				var players = [];
				
				<%for(int i=0;i<players.size();i++)
				{
				%>
					var player = $.parseJSON('<%=XmlFactory.getJSONStringFromObject(players.get(i))%>');
					players.push(player);
				<%
				}%>
				
				var gameProperties = $.parseJSON('<%=XmlFactory.getJSONStringFromObject(gameProperties)%>');
				
				init(gameProperties, gameMap, players);	
	    		animate();
			});
		</script>
	</head>
	<body>
	</body>
</html>