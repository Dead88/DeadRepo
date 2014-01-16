<form action="login.do" id="formLogin" method="post">
 	Veuillez vous identifier :
 	<br /><br />
 	Username : <input type="text" id="user" />
 	<br /><br />
 	Password : <input type="password" id="pass" />
 	<br /><br />
 	<input type="button" value="Connexion" onclick="submitLogin(false);" />
 	<br /><br />
 	<i>Astuce : Essayez "test" et "test"</i><br />
 	<input type="button" value="Accès Direct" onclick="submitLogin(true);" />
</form>