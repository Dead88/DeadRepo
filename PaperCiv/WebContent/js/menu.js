function changeMenu(previousMenu, newMenu){
	$("#"+previousMenu).slideUp(500, function(){
	  	$("#"+newMenu).slideDown(500);
	});  
}

function submitLogin(){
	var user = $("#user").val();
	var pass = $("#pass").val();
	
	if(!user){
		alert("Veuillez saisir votre Username !");
		$("#user").focus();
		return;
	}
	if(!pass){
	  	alert("Veuillez saisir votre Password !");
		$("#pass").focus();
		return;
	}
	
	$.ajax({
		url: "login.do?user="+user+"&pass="+pass,
	}).complete(function(xhr, status) {
		if(xhr.responseText == "OK"){
			$("#message").html("");
			changeMenu("login","menu");
		}
		else $("#message").html(xhr.responseText);
	});
}

function newSkirmish(){
	changeMenu("menu","skirmish");
}

function submitSkirmish(){
//	var playerRace = $("#playerRace").val();
//	var enemyRace = $("#enemyRace").val();
//	var map = $("#map").val();
	
	$("#formSkirmish").submit();
}