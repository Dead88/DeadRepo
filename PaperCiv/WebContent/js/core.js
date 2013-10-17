var SCREEN_WIDTH = window.innerWidth, SCREEN_HEIGHT = window.innerHeight;
var container;
var rightHud, leftHud, ressourceStock;
var unitsListDiv, buildingsListDiv;
var projector, camera, scene, renderer;
var clock = new THREE.Clock(), delta, mouse = { x: 0, y: 0 };

var gameProperties;
var gameMap;
var areas = [];

var players = [];
var humanPlayer;
var trees = [];

var moveForward = false;
var moveBackward = false;
var moveLeft = false;
var moveRight = false;

var previousOverTexture;
var displayAreasOver = true;
var canSelectAreas = true;

var entityToCreate;
var entityToCreateTexture;

//FIXME : Now, use actions and define what is a turn

function include (url) {
	var xhr = null;
	if (window.XMLHttpRequest) xhr = new XMLHttpRequest();
	else if (window.ActiveXObject) xhr = new ActiveXObject("Microsoft.XMLHTTP");

	if (xhr == null) throw new Error("XMLHttpRequest non support�");

	xhr.open("GET", url, true);
	xhr.onreadystatechange = function () {
	if (this.readyState == 4 && this.status == 200) {
		setTimeout(this.responseText, 0);
	}};

	xhr.send(null);
}

function init(gameProps, map, players) {	
	gameProperties = gameProps;
	
	gameMap = map;
	areas = gameMap.Areas;
	
	console.warn(players[0]);
	humanPlayer = players[0];

	//DIVs
	constructHUD();
	
	//CAMERA
	projector = new THREE.Projector();
	camera = new THREE.PerspectiveCamera( 70, SCREEN_WIDTH / SCREEN_HEIGHT, 0.1, 500 );
	camera.position.set( 0, 6, 20);
	camera.rotation.x = - Math.PI / 4;
	
    //SCENE
    scene = new THREE.Scene();
    scene.fog = new THREE.Fog( 0xb8f2fe, 0 , 200 );
    
    //RENDERER    
    renderer = new THREE.WebGLRenderer();
    renderer.setSize( SCREEN_WIDTH, SCREEN_HEIGHT );
	renderer.setClearColor( scene.fog.color, 1 );
	container.appendChild( renderer.domElement );
	
	//BUILD ENTITIES
    build();
    
	//CONTROLS
    $(document).keydown(function(event){
		if(event.which == 37) moveLeft = true;
		if(event.which == 38) moveForward = true;
		if(event.which == 39) moveRight = true;
		if(event.which == 40) moveBackward = true;
    });
    $(document).keyup(function(event){  
    	if(event.which == 37) moveLeft = false;
		if(event.which == 38) moveForward = false;
		if(event.which == 39) moveRight = false;
		if(event.which == 40) moveBackward = false;
    });
    $(document).click(function(event){
    	if(event.which == 2){
    		if(camera.rotation.x == (- Math.PI / 2.5))
    			camera.rotation.x = - Math.PI / 4;
    		else camera.rotation.x = - Math.PI / 2.5;
    	}
    });
    
    //EVENTS
	window.addEventListener( "resize", onWindowResize, false );
	window.addEventListener( "mousemove", onMouseMove, false );	
	window.addEventListener( "mousedown", onMouseDown, false );
	window.addEventListener( "mousewheel", onMouseWheel, false );
	window.addEventListener( "DOMMouseScroll", onMouseWheel, false );
	
	$(".hudTransparentOverlay").mouseover(function(){ displayAreasOver = false; canSelectAreas = false; });
	$(".hudTransparentOverlay").mouseleave(function(){ displayAreasOver = true; canSelectAreas = true; });
	$(".rightHud").mouseover(function(){ displayAreasOver = false; canSelectAreas = false; });
	$(".rightHud").mouseleave(function(){ displayAreasOver = true; canSelectAreas = true; });

	//DISABLE RIGHT CLICK CONTEXT MENU
	$(window).bind("contextmenu",function(e){
		e.preventDefault();
	});
}

function animate() {
	render();
	//webkitRequestAnimationFrame(animate);
	requestAnimationFrame(animate);  
}

function render() {
	delta = clock.getDelta();
	
	moveCamera();
	
	if(humanPlayer)
		$(ressourceStock).html("Paper : "+humanPlayer.PaperAmount+"&nbsp;&nbsp;&nbsp;&nbsp;" +
				""+humanPlayer.PlayerRace.FictiveRessource.Name+" : "+humanPlayer.FictiveRessourceAmount);
	
	renderer.clear();
	renderer.render( scene, camera );
}

function moveCamera(){
	if(moveForward) camera.position.z -= 0.4;
	if(moveBackward) camera.position.z += 0.4;
	if(moveLeft) camera.position.x -= 0.4;
	if(moveRight)  camera.position.x += 0.4;
}

function onWindowResize() {
	SCREEN_WIDTH = window.innerWidth;
	SCREEN_HEIGHT = window.innerHeight;
	
	renderer.setSize( SCREEN_WIDTH, SCREEN_HEIGHT );

	camera.aspect = SCREEN_WIDTH / SCREEN_HEIGHT;
	camera.updateProjectionMatrix();
}
function onMouseMove(e) {
	e.preventDefault();
	mouse.x = (e.clientX / SCREEN_WIDTH) * 2 - 1;
	mouse.y = - (e.clientY / SCREEN_HEIGHT) * 2 + 1;
	
	if(displayAreasOver)
		overArea();
}
function onMouseDown(e){
	e.preventDefault();
	mouse.x = (e.clientX / SCREEN_WIDTH) * 2 - 1;
	mouse.y = - (e.clientY / SCREEN_HEIGHT) * 2 + 1;
	
	if(e.which == 1){
		if(canSelectAreas)
			selectArea();
	}
	else if(e.which == 3){
		if(canSelectAreas)
			deselectArea();
	}
}
function onMouseWheel(e) {
	var d = e.wheelDelta;
	
	if(d == 120){
		 camera.position.y -= 0.4; 
	}
	else if(d == -120){
		 camera.position.y += 0.4;
	}
}

function build(){	
	//INIT MAP AND AREAS
	var obj = new THREE.Object3D();

	for(var i=0;i<areas.length;i++){
		var area = areas[i];
		
		var texture = THREE.ImageUtils.loadTexture(area.Texture);
		
		var areaGeometry = new THREE.PlaneGeometry(1,1);
		areaGeometry.applyMatrix( new THREE.Matrix4().makeRotationX( - Math.PI / 2 ) );
		
		areaGeometry.vertices[0].add( area.Vertices[0] );
		areaGeometry.vertices[1].add( area.Vertices[1] );
		areaGeometry.vertices[2].add( area.Vertices[2] );
		areaGeometry.vertices[3].add( area.Vertices[3] );
		
		var areaMaterial = new THREE.MeshBasicMaterial( { color:0xffffff, map:texture } );
		
		var areaMesh = new THREE.Mesh(areaGeometry, areaMaterial);
	
		areaMesh.position.set( area.X, area.Y , area.Z);
		area.Mesh = areaMesh;
		
		obj.add(area.Mesh);
		
		if(area.Doodad){
			if(area.Doodad.MeshType == "Tree"){
				generateTree(area.Doodad);
			}
			else if(area.Doodad.MeshType == "Deposit"){
				generateDeposit(area);
			}
			else if(area.Doodad.MeshType == "Unit"){
				var texture = THREE.ImageUtils.loadTexture(area.Doodad.Texture);
				area.Mesh.material.map = texture;
			}
		}
		if(area.Building){
			var texture = THREE.ImageUtils.loadTexture(area.Building.Texture);
			area.Mesh.material.map = texture;
		}
	}
	
	gameMap.Mesh = obj;
	
	generateHeightOn9(gameMap.HeightsQuantity,gameMap.Height);
	
	//TODO : find a way to build mounts
//	for(var j=0;j<20;j++){
//		var r = Math.floor( getRandomBetween( 0 + gameMap.Length, areas.length - gameMap.Length ) );
//		
//		generateHeightOn9(1, getRandomBetween(0.5, 1.5), r);
//		
//		if(j%2 != 0)
//			generateHeightOn9(1, getRandomBetween(0.5, 1.5), r - gameMap.Length + 1);
//		else generateHeightOn9(1, getRandomBetween(0.5, 1.5), r - gameMap.Length - 1);
//	}
	
	cleanAreasMesh();
	
	scene.add(gameMap.Mesh);
}

function overArea(){
	var vector = new THREE.Vector3(mouse.x, mouse.y , 0.5 );
	
	projector.unprojectVector(vector, camera);
	
	var ray = new THREE.Raycaster(
			camera.position,
			vector.sub(camera.position).normalize()
	);
	
	var ints = ray.intersectObjects(gameMap.Mesh.getDescendants());
	
	if(ints.length > 0){
		if(gameMap.Mesh.getObjectByName("over")!=null){
			var child = gameMap.Mesh.getObjectByName("over");
			
			child.material = new THREE.MeshBasicMaterial( { color:0xffffff, map:previousOverTexture } );
			child.name = "";
		}
	
		if(ints[0].object.name == ""){	
			previousOverTexture = ints[0].object.material.map;
			
			if(entityToCreate){
				ints[0].object.material = new THREE.MeshBasicMaterial( { color:0xffffff, map:entityToCreateTexture} );
				
				var area = getAreaByAreaMeshUuid(ints[0].object.uuid);
				
				if(area){
					if(!canBuildEntityOnSelectedArea("B", entityToCreate, area)){
						ints[0].object.material = new THREE.MeshBasicMaterial( { color:0xff0000, map:entityToCreateTexture } );
					}
				}
			}
			else {
				ints[0].object.material = new THREE.MeshBasicMaterial( { color:0xcccccc, map:previousOverTexture } );
			}
			ints[0].object.name = "over";
		}
	}
}
function selectArea(){	
	var vector = new THREE.Vector3(mouse.x, mouse.y , 0.5 );
	
	projector.unprojectVector(vector, camera);
	
	var ray = new THREE.Raycaster(
			camera.position,
			vector.sub(camera.position).normalize()
	);
	
	var ints = ray.intersectObjects(gameMap.Mesh.getDescendants());
	
	if(ints.length > 0){
		if(gameMap.Mesh.getObjectByName("selected")!=null){
			var child = gameMap.Mesh.getObjectByName("selected");
			
			var previousTexture = child.material.map;
			child.material = new THREE.MeshBasicMaterial( { color:0xffffff, map:previousTexture } );
			child.name = "";
		}
	
		var area = getAreaByAreaMeshUuid(ints[0].object.uuid);
		
		if(entityToCreate && area){
			if(canBuildEntityOnSelectedArea("B",entityToCreate, area)){
				ints[0].object.material = new THREE.MeshBasicMaterial( { color:0x00ff00, map:entityToCreateTexture } );
				ints[0].object.name = "selected";
				
				if(addPlayerBuildingAtCoordinate(entityToCreate.Id, area))
					refreshUnitAndBuildingLists();
				
				deselectArea();	
			}
			else{ 
				alert("Impossible de construire ici !");
			}
		}
		else{
			var previousTexture = ints[0].object.material.map;
			ints[0].object.material = new THREE.MeshBasicMaterial( { color:0x00ff00, map:previousTexture } );
			ints[0].object.name = "selected";
			
			if(area){		
				$("#areaimg").attr("src", area.Mesh.material.map.sourceFile);
				
				if(area.Building){
					var buildingtext = area.Building.Name+"<br />"+area.Building.Life+" PV<br />Armure : "+area.Building.Armor;
					$("#areatext").html(buildingtext);
				}
				else if(area.Doodad){
					if(area.Doodad.MeshType == "Deposit"){
						var doodadtext = "Gisement : "+(area.Doodad.Type ? area.Doodad.Type.Name : "Papier")+"<br />Quantit� : "+area.Doodad.Quantity;
						$("#areatext").html(doodadtext);
					}
					else if(area.Doodad.MeshType == "Tree"){
						$("#areatext").html("For�t");
					}
					else if(area.Doodad.MeshType == "Unit"){
						var doodadtext = area.Doodad.Name+"<br />"+area.Doodad.Life+" PV<br />Puissance : "+area.Doodad.Power+"<br />"
						+"Armure : "+area.Doodad.Armor+"<br />Fr�quence de Tir : "+area.Doodad.FireFrequency+"<br />Port�e : "+area.Doodad.Range+"<br />"+
						"Vitesse : "+area.Doodad.Speed;
						$("#areatext").html(doodadtext);
					}
				}
				else{
					if(area.AreaType.Type.indexOf("ground")!=-1){
						$("#areatext").html("Terre d�serte");
					}
					else $("#areatext").html("Eau");
				}
			}
			
			$(leftHud).show();
		}
	}
	else{ deselectArea(); }
}
function deselectArea(){
	if(gameMap.Mesh.getObjectByName("selected")!=null){
		var child = gameMap.Mesh.getObjectByName("selected");
		
		var previousTexture = child.material.map;
		child.material = new THREE.MeshBasicMaterial( { color:0xffffff, map:previousTexture } );
		child.name = "";
	}
	
	$(".rightHud img").removeClass("selected");
	$(".rightHud img").addClass("notselected");
	entityToCreate = null;
	entityToCreateTexture = null;
	$(leftHud).hide();
}

function getAreaByAreaMeshUuid(uuid){
	for(var i=0;i<areas.length;i++){
		if(areas[i].Mesh.uuid == uuid)
			return areas[i];
	}
	return null;
}

function generateHeightOn9(numberOfHeight, height, predefinedIndex){
	for(var i=0;i<numberOfHeight;i++){
		var randomIndex;
		
		if(predefinedIndex != null){
			randomIndex = predefinedIndex;
		}
		else randomIndex = Math.floor( getRandomBetween( 0, areas.length ) );

		var centerArea = areas[randomIndex];
		var previousArea = areas[randomIndex-1];
		var nextArea  = areas[randomIndex+1];
		
		var previousRightArea = areas[randomIndex-gameMap.Length];
		var previousRearRightArea = areas[randomIndex-1-gameMap.Length];
		var previousTopRightArea = areas[randomIndex+1-gameMap.Length];
		
		var nextLeftArea = areas[randomIndex+gameMap.Length];
		var nextRearLeftArea = areas[randomIndex-1+gameMap.Length];
		var nextTopLeftArea = areas[randomIndex+1+gameMap.Length];
		
		centerArea.Mesh.geometry.vertices[0].y += height;
		centerArea.Mesh.geometry.vertices[1].y += height;
		centerArea.Mesh.geometry.vertices[2].y += height;
		centerArea.Mesh.geometry.vertices[3].y += height;
		
		if(previousArea){
			previousArea.Mesh.geometry.vertices[1].y += height;
			previousArea.Mesh.geometry.vertices[3].y += height;
		}
		if(nextArea){
			nextArea.Mesh.geometry.vertices[0].y += height;
			nextArea.Mesh.geometry.vertices[2].y += height;
		}
		
		if(previousRightArea){
			previousRightArea.Mesh.geometry.vertices[0].y += height;
			previousRightArea.Mesh.geometry.vertices[1].y += height;
		}
		if(previousRearRightArea)
			previousRearRightArea.Mesh.geometry.vertices[1].y += height;
		if(previousTopRightArea)
			previousTopRightArea.Mesh.geometry.vertices[0].y += height;
		
		if(nextLeftArea){
			nextLeftArea.Mesh.geometry.vertices[2].y += height;
			nextLeftArea.Mesh.geometry.vertices[3].y += height;
		}
		if(nextRearLeftArea)
			nextRearLeftArea.Mesh.geometry.vertices[3].y += height;
		if(nextTopLeftArea)
			nextTopLeftArea.Mesh.geometry.vertices[2].y += height;
	}
}

function cleanAreasMesh(){
	for(var i=0;i<areas.length;i++){
		var mesh = areas[i].Mesh;
	
		var yCount = 0;
		
		for(var j=0;j<mesh.geometry.vertices.length;j++){
			yCount += mesh.geometry.vertices[j].y;
			//mesh.geometry.vertices[j].normalize();
		}
		
		mesh.geometry.computeCentroids();
		mesh.geometry.computeVertexNormals();
		mesh.geometry.computeFaceNormals();
		
		var result = yCount / mesh.geometry.vertices.length;
		
		areas[i].Y = result;
		
		if(areas[i].Doodad && areas[i].Doodad.Mesh){
			areas[i].Doodad.Y += result;
			areas[i].Doodad.Mesh.position.y += result;
		}
		if(areas[i].Building && areas[i].Building.Mesh){
			areas[i].Building.Y += result;
			areas[i].Building.Mesh.position.y += result;
		}
	}
}

function generateTree(areaDoodad){		
	var textureTronc = THREE.ImageUtils.loadTexture(areaDoodad.TrunkTexture);
	var textureFeuillage = THREE.ImageUtils.loadTexture(areaDoodad.FoliageTexture); 

	var g, m, mesh1, mesh2;
	var treeObj = new THREE.Object3D();
	
	m = new THREE.MeshBasicMaterial( { color: 0xffffff } );
	m.map = textureTronc;
	g = new THREE.CylinderGeometry( 0.05, 0.05, 0.5);
	mesh1 = new THREE.Mesh( g, m);
	
	m = new THREE.MeshBasicMaterial( { color: 0xffffff } );
	m.map = textureFeuillage;
	g = new THREE.CylinderGeometry( 0, 0.2, 0.5);
	mesh2 = new THREE.Mesh( g, m);
	
	areaDoodad.Y = 0.125;
	
	treeObj.position.set( areaDoodad.X , areaDoodad.Y, areaDoodad.Z );
	mesh2.position.set( mesh1.position.x, mesh1.position.y + 0.25, mesh1.position.z );
	
	treeObj.add( mesh1 );
	treeObj.add( mesh2 );
	trees.push(treeObj);

	areaDoodad.Mesh = treeObj;
	
	scene.add(treeObj);
}

function generateDeposit(area){
	var areaDoodad = area.Doodad;
	var texturePath;
	if(areaDoodad.Type){
		texturePath = areaDoodad.Type.Texture;
	}
	else texturePath = "img/maptexture/paper.jpg";
	
	var textureDeposit = THREE.ImageUtils.loadTexture(texturePath);
	
	area.Mesh.material.map = textureDeposit;
}

function constructHUD(){
	//CONTAINER
	container = document.createElement( 'div' );
	document.body.appendChild( container );
	
	//HUDs
	rightHud = document.createElement('div');
	$(rightHud).addClass("rightHud");
	document.body.appendChild( rightHud );
	
	leftHud = document.createElement('div');
	$(leftHud).addClass("leftHud");
	document.body.appendChild( leftHud );
	
	//TRANSPARENT OVERLAYS
	var rightHudTransparentOverlay = document.createElement('div');
	$(rightHudTransparentOverlay).addClass("hudTransparentOverlay");
	rightHud.appendChild(rightHudTransparentOverlay);
	
	var leftHudTransparentOverlay = document.createElement('div');
	$(leftHudTransparentOverlay).addClass("hudTransparentOverlay");
	leftHud.appendChild(leftHudTransparentOverlay);
	
	//RESSOURCE STOCK
	ressourceStock = document.createElement('div');
	$(ressourceStock).attr("class", "hudElement");
	rightHud.appendChild(ressourceStock);
	
	//UNITS & BUILDINGS LIST
	var unitsList = "";
	var buildingsList = "";
	unitsListDiv = document.createElement('div');
	buildingsListDiv = document.createElement('div');
	$(unitsListDiv).attr("class","hudElement");
	$(buildingsListDiv).attr("class","hudElement");
	
	for(var i=0;i<humanPlayer.PlayerRace.Units.length;i++){
		var unit = humanPlayer.PlayerRace.Units[i];
		
		if(haveRequiredBuildingsFor(unit)){
			unitsList += "<img src=\""+unit.Texture+"\" " +
					"title=\""+unit.Name+" :\n" +
						"Cost "+unit.PaperCost+" - "+unit.FictiveCost+" (Paper - "+humanPlayer.PlayerRace.FictiveRessource.Name+")\" " +
					"style=\"margin-right:4px;\" onclick=\"haveEnoughRessourceFor(this, 'U',"+i+");\" />";
		}
	}
	
	for(var i=0;i<humanPlayer.PlayerRace.Buildings.length;i++){
		var building = humanPlayer.PlayerRace.Buildings[i];
		
		if(haveRequiredBuildingsFor(building)){
			buildingsList += "<img src=\""+building.Texture+"\" " +
				"title=\""+building.Name+" :\n" +
						"Cost "+building.PaperCost+" - "+building.FictiveCost+" (Paper - "+humanPlayer.PlayerRace.FictiveRessource.Name+")\" " +
				"style=\"margin-right:4px;\" onclick=\"haveEnoughRessourceFor(this, 'B',"+i+");\" />";
		}
	}
	
	$(unitsListDiv).html(unitsList);
	$(buildingsListDiv).html(buildingsList);
	rightHud.appendChild(unitsListDiv);
	rightHud.appendChild(buildingsListDiv);

	//MAIN MENU LINK
	var menuLink = document.createElement('a');
	$(menuLink).attr("class","hudElement");
	$(menuLink).attr("href","index.jsp");
	$(menuLink).html("Retour � l'accueil");
	rightHud.appendChild(menuLink);
	
	//SELECTED AREA INFO
	var areainfo = document.createElement('div');
	$(areainfo).attr("id","areainfodiv");
	$(areainfo).attr("class","hudElement");
	leftHud.appendChild(areainfo);
	
	var areaimg = document.createElement('img');
	$(areaimg).attr("id","areaimg");
	$(areaimg).attr("class","hudElement");
	$(areaimg).css("width","64");
	$(areaimg).css("height","64");
	leftHud.appendChild(areaimg);
	
	var areatext = document.createElement('p');
	$(areatext).attr("id","areatext");
	$(areatext).attr("class","hudElement");
	leftHud.appendChild(areatext);
}

function refreshUnitAndBuildingLists(){
	var unitsList = "";
	var buildingsList = "";
	
	for(var i=0;i<humanPlayer.PlayerRace.Units.length;i++){
		var unit = humanPlayer.PlayerRace.Units[i];
		
		if(haveRequiredBuildingsFor(unit)){
			unitsList += "<img src=\""+unit.Texture+"\" " +
					"title=\""+unit.Name+" :\n" +
						"Cost "+unit.PaperCost+" - "+unit.FictiveCost+" (Paper - "+humanPlayer.PlayerRace.FictiveRessource.Name+")\" " +
					"style=\"margin-right:4px;\" onclick=\"haveEnoughRessourceFor(this, 'U',"+i+");\" />";
		}
	}
	
	for(var i=0;i<humanPlayer.PlayerRace.Buildings.length;i++){
		var building = humanPlayer.PlayerRace.Buildings[i];
		
		if(haveRequiredBuildingsFor(building)){
			buildingsList += "<img src=\""+building.Texture+"\" " +
				"title=\""+building.Name+" :\n" +
						"Cost "+building.PaperCost+" - "+building.FictiveCost+" (Paper - "+humanPlayer.PlayerRace.FictiveRessource.Name+")\" " +
				"style=\"margin-right:4px;\" onclick=\"haveEnoughRessourceFor(this, 'B',"+i+");\" />";
		}
	}
	
	$(unitsListDiv).html(unitsList);
	$(buildingsListDiv).html(buildingsList);
}

function getRandomBetween( from, to){
	var r = from + ( Math.random()*(to - from) );
	return r;
}


//TODO: Stop using humanPlayer but work with player ids
function haveRequiredBuildingsFor(entity){
	var list = "";
	
	for(var i=0;i<entity.RequiredBuildingIds.length;i++)
	{
		list += entity.RequiredBuildingIds+";";
	}
	
	var result = $.ajax({
		url: "ajax.do",
		async: false,
		data: { playerId: humanPlayer.Id, 
				method: "haveRequiredBuildings", 
				idsTofind: list }
	}).done(function(msg){
		return msg;
	}).responseText;
	
	if(result == "OK")
		return true;
	else return false;
}

function haveEnoughRessourceFor(img, entityIdentifier, entityArrayId){
	var entity;
	
	if(entityIdentifier == "B")
		entity = humanPlayer.PlayerRace.Buildings[entityArrayId];
	else if(entityIdentifier == "U") 
		entity = humanPlayer.PlayerRace.Units[entityArrayId];
	
	var result = $.ajax({
		url: "ajax.do",
		async: false,
		data: { playerId: humanPlayer.Id, method: "haveEnoughRessource", paperCost: entity.PaperCost, fictiveCost: entity.FictiveCost }
	}).done(function(msg){
		return msg;
	}).responseText;
	
	if(result == "OK"){
		selectEntityToCreate(img, entityIdentifier, entity);
	}
	else alert("Vous n'avez pas assez de ressource pour "+entity.Name);
}

function selectEntityToCreate(img, entityIdentifier, entity){
	
	if(entityIdentifier == "B"){
		$(".rightHud img").removeClass("selected");
		$(img).addClass("selected");
		buildingToCreateHudImage = img;
		entityToCreate = entity;
		entityToCreateTexture = THREE.ImageUtils.loadTexture(entity.Texture);
	}
	else if(entityIdentifier == "U"){
		if(!addPlayerUnitAtCoordinate(entity.Id))
			alert("La sortie du b�timent producteur est obstru�e !");
		deselectArea();
	}
}

function canBuildEntityOnSelectedArea(entityIdentifier, entity, area){
	
	if(entityIdentifier == "B" && entity.Type.Name == "DepositBuilding"){
		if(!area || (area && !area.Doodad) || (area.Doodad && area.Doodad.MeshType != "Deposit"))
			return false;
	}
	else if((entityIdentifier == "B" && entity.Type.Name != "DepositBuilding")
			|| entityIdentifier == "U"){
		if((area && area.Doodad) || (area && area.Building) || !area)
			return false;
	}

	var result = $.ajax({
		url: "ajax.do",
		async: false,
		data: { playerId: humanPlayer.Id, 
				method: "canBuildEntityOnSelectedArea", 
				entityIdentifier: entityIdentifier,
				entityType: entity.Type.Name,
				xzCoord: area.X+";"+area.Z }
	}).done(function(msg){
		return msg;
	}).responseText;
	
	if(result == "OK"){
		return true;
	}
	else return false;
}

function addPlayerBuildingAtCoordinate(id, area){
	var result = $.ajax({
		url: "ajax.do",
		async: false,
		data: { playerId: humanPlayer.Id, 
				method: "addPlayerBuildingAtCoordinate", 
				buildingId : id, 
				X: area.X, 
				Y: area.Y, 
				Z: area.Z}
	}).done(function(msg){
		return msg;
	}).responseText;
	
	if(result != "KO"){
		area.Building = $.parseJSON(result.split(";")[0]);
		humanPlayer = $.parseJSON(result.split(";")[1]);
		
		entityToCreate = null;
		entityToCreateTexture = null;
		return true;
	}
	else return false;
}

function addPlayerUnitAtCoordinate(entityId){
	var result = $.ajax({
		url: "ajax.do",
		async: false,
		data: { playerId: humanPlayer.Id, 
			method: "addPlayerUnitAtCoordinate", 
			unitId : entityId}
	}).done(function(msg){
		return msg;
	}).responseText;
	
	if(result != "KO"){
		var area = gameMap.Areas[result.split(";")[0]];
		area.Doodad = $.parseJSON(result.split(";")[1]);
		area.Mesh.material.map = THREE.ImageUtils.loadTexture(area.Doodad.Texture);
		humanPlayer = $.parseJSON(result.split(";")[2]);
		return true;	
	}
	else return false;	
}
