<%@page import="fr.paperciv.objs.map.GameMap"%>
<%@page import="fr.paperciv.objs.races.Race"%>
<%@page import="fr.paperciv.common.PaperSession"%>
<%@page import="fr.paperciv.objs.Mapping"%>
<%@page import="fr.paperciv.common.Constants"%>
<%@page import="fr.paperciv.factories.XmlFactory"%>
<%
Mapping mapping = PaperSession.getMappingSession(request);
%>
<script type="text/javascript">
$(document).ready(function() {
	$("#enemyRaceId").html($("#playerRaceId").html());
});
</script>
<form action="skirmish.do" id="formSkirmish" method="post">
 	<b>Configuration de la partie</b>
	<br /><br />
	Race : 
	<select id="playerRaceId" name="playerRaceId">
	<%for(int i=0;i<mapping.getRaces().size();i++)
	{
		Race race = mapping.getRaces().get(i);
	%>
		<option value="<%=race.getId()%>"><%=race.getName()%></option>
	<%
	}
	%>
	</select>
	<br /><br />
	Ennemi : 
	<select id="enemyRaceId" name="enemyRaceId">
	</select>
	<br /><br />
	Carte : 
	<select id="gameMapId" name="gameMapId">
	<%for(int i=0;i<mapping.getGameMaps().size();i++)
	{
		GameMap gameMap = mapping.getGameMaps().get(i);
	%>
		<option value="<%=gameMap.getId()%>"><%=gameMap.getName()%></option>
	<%
	}
	%>
	</select>
	<br /><br />
	<br /><br />
 	<input type="button" value="Retour" onclick="changeMenu('skirmish','menu');" />
 	<input type="button" value="Lancer" onclick="submitSkirmish();" />
</form>
