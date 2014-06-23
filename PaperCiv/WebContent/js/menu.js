var SCREEN_WIDTH = window.innerWidth, SCREEN_HEIGHT = window.innerHeight;
var container;
var projector, camera, scene, renderer;
var clock = new THREE.Clock(), delta, mouse = { x: 0, y: 0 };

var titleMesh;
var titleDirection = "R";

var user;
var connectedToChat = false;
var chatWS;

function init() {	
	//CAMERA
	projector = new THREE.Projector();
	camera = new THREE.PerspectiveCamera( 70, SCREEN_WIDTH / SCREEN_HEIGHT, 0.1, 500 );
	camera.position.set( 0, 2.25, 20);
	
    //SCENE
    scene = new THREE.Scene();
    scene.fog = new THREE.Fog( 0xC4F4F5, 0 , 200 );
    
    //CONTAINER
	container = document.createElement( 'div' );
    $(container).attr("class","fond");
	document.body.appendChild( container );
    
    //RENDERER    
    renderer = new THREE.WebGLRenderer();
    renderer.setSize( SCREEN_WIDTH, SCREEN_HEIGHT );
	renderer.setClearColor( scene.fog.color, 1 );
	container.appendChild( renderer.domElement );
	
	build();
	
	window.addEventListener( "resize", onWindowResize, false );
}

function animate() {
	render();
	requestAnimationFrame(animate);  
}

function render() {
	delta = clock.getDelta();
	
	if(titleDirection == "R" && titleMesh.position.x < 5){
		titleMesh.position.x += 0.025;
	}	
	else if(titleDirection == "R" && titleMesh.position.x >= 5)
		titleDirection = "L";
	
	if(titleDirection == "L" && titleMesh.position.x > -10){
		titleMesh.position.x -= 0.025;
	}
	else if(titleDirection == "L" && titleMesh.position.x <= -10)
		titleDirection = "R";
	
	titleMesh.lookAt(new THREE.Vector3( camera.position.x - 5, camera.position.y, camera.position.z) );
	camera.lookAt(new THREE.Vector3( titleMesh.position.x + 5, titleMesh.position.y, titleMesh.position.z) );
	
	renderer.clear();
	renderer.render( scene, camera );
}

function onWindowResize() {
	SCREEN_WIDTH = window.innerWidth;
	SCREEN_HEIGHT = window.innerHeight;
	
	renderer.setSize( SCREEN_WIDTH, SCREEN_HEIGHT );

	camera.aspect = SCREEN_WIDTH / SCREEN_HEIGHT;
	camera.updateProjectionMatrix();
}

function build(){
	var groundGeo, groundTexture, groundMaterial, groundMesh;
	var titleGeo, titleTexture, titleMaterial;
	
	//GROUND
	var groundGeo = new THREE.PlaneGeometry(1000,1000);
	groundGeo.applyMatrix( new THREE.Matrix4().makeRotationX( - Math.PI / 2 ) );
	
	var groundTexture = THREE.ImageUtils.loadTexture("img/biggrass.jpg");
	groundTexture.anisotropy = 16;
	groundTexture.wrapS = groundTexture.wrapT = THREE.RepeatWrapping;
	groundTexture.repeat.set( 100, 100 );
	
	var groundMaterial = new THREE.MeshBasicMaterial( { color: 0xffffff, map: groundTexture } );
	
	var groundMesh = new THREE.Mesh(groundGeo, groundMaterial);
	
	scene.add(groundMesh);
	
	//TITLE
	titleGeo = new THREE.TextGeometry( "PaperCiv", 
	{
		font:"optimer", size: 2, height: 1
	});
	// font: helvetiker, gentilis, droid sans, droid serif, optimer
	// weight: normal, bold
	
	titleMaterial = new THREE.MeshBasicMaterial( { color:0xB50000 } );		
	
	titleMesh = new THREE.Mesh(titleGeo, titleMaterial);
	titleMesh.position.set(-5, 4, 4);
	
	scene.add(titleMesh);
	
	//TEST PARTICLE
	var radiusRange = 50;
	var particleTexture = THREE.ImageUtils.loadTexture( 'img/spark.png' );
	
    var spriteMaterial = new THREE.SpriteMaterial( { map: particleTexture, useScreenCoordinates: false, color: 0xFFFF96 } );
	
	var sprite = new THREE.Sprite( spriteMaterial );
	//sprite.scale.set( 32, 32, 1.0 ); // imageWidth, imageHeight
	sprite.scale.set( 1.5, 1.5, 1.0 );
	sprite.position.set( 0, 5, 15);
	
	// sprite.opacity = 0.80; // translucent particles
	sprite.material.blending = THREE.AdditiveBlending; // "glowing" particles
	
	scene.add(sprite);
}

function toggleChatConnection(){
	if(!connectedToChat){
		chatWS = new WebSocket("ws://nicolas-pc:8080/PaperCiv/chat/"+user.UserName);
		chatWS.onopen = function(evt) { onChatOpen(evt); };
		chatWS.onclose = function(evt) { onChatClose(evt); }; 
		chatWS.onmessage = function(evt) { onChatMessage(evt); }; 
		chatWS.onerror = function(evt) { onChatError(evt); };
	}
	else if(connectedToChat) {
		chatWS.close();
	}
}

function submitChatMessage(){
	if($("#chatinput").val() && $("#chatinput").val() != ""){
		chatWS.send($("#chatinput").val());
		$("#chatinput").val("");
	}
}


function onChatOpen(evt) { 
	$("#chatoutput").html("");
	$("#chatbox").slideDown(500);
	$("#chattogglebutton").val("Deconnexion");
	connectedToChat = true;
	
	//FIXME : this line is important, it allow receiving messages without sending one
	chatWS.send("");
}  
function onChatClose(evt) {
	$("#chatoutput").html("");
	$("#chatbox").slideUp(500);
	$("#chattogglebutton").val("Connexion");
	connectedToChat = false;	    
}  
function onChatMessage(evt) 
{ 
	writeChatOutput(evt.data);
}  
function onChatError(evt) { 
	writeChatOutput(evt.data);
} 

function writeChatOutput(msg) {
	$("#chatoutput").append("&#10;"+msg);
}

function checkSession(_user){
	user = _user;

	$("#login").hide();
	$("#menu").hide();
	$("#skirmish").hide();
	$("#multiplayer").hide();
	
	if(user){
		$("#chatwrapper").show(800);
		$("#menu").slideDown(800);
	}
	else if(!user)
		$("#login").slideDown(800);
}

function changeMenu(previousMenu, newMenu){
	$("#"+previousMenu).slideUp(400, function(){
	  	$("#"+newMenu).slideDown(400);
	});  
}

function submitLogin(godmode){
	var username = $("#user").val();
	var pass = $("#pass").val();
	
	if(!username && !godmode){
		alert("Veuillez saisir votre Username !");
		$("#user").focus();
		return;
	}
	if(!pass && !godmode){
	  	alert("Veuillez saisir votre Password !");
		$("#pass").focus();
		return;
	}

	if(godmode){
		document.location.href="login.do?godmode=1";
	}
	
	$.ajax({
		url: "login.do?user="+username+"&pass="+pass
	}).complete(function(xhr, status) {
		if(xhr.responseText.indexOf("{")!=-1){
			user = $.parseJSON(xhr.responseText);
			$("#message").html("");
			changeMenu("login","menu");
			$("#chatwrapper").show(500);
		}
		else $("#message").html(xhr.responseText);
	});
}

function submitSkirmish(){
	$("#formSkirmish").submit();
}