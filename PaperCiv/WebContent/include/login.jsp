<form action="login.do" id="formLogin" method="post">
 	Veuillez vous identifier :
 	<br /><br />
 	Username : <input type="text" id="user" />
 	<br /><br />
 	Password : <input type="password" id="pass" />
 	<br /><br />
 	<input type="button" value="Connexion" onclick="submitLogin($('#user').val());" />
 	<br /><br />
 	<i>Astuce : Essayez "test" et "test"</i>
</form>