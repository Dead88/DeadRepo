<%

%>
<script type="text/javascript">
$(document).ready(function() {
	$("#multiGameHost").hide();
	$("#multiGameSearch").hide();
});
</script>
<div id="multiHostChoice">
	<b>Choisir une méthode de connexion</b>
	<br /><br />
	<input type="button" value="Créer" onclick="newMultiGame();" />
	<input type="button" value="Rejoindre" onclick="changeMenu('multiHostChoice','multiGameSearch');" />
	<br /><br />
	<input type="button" value="Retour" onclick="changeMenu('multiplayer','menu');" />
</div>
<div id="multiGameHost">
</div>
<div id="multiGameSearch">
	tableau des parties multi... :(
	<br /><br />
	<input type="button" value="Retour" onclick="changeMenu('multiGameSearch','multiHostChoice');" />
</div>