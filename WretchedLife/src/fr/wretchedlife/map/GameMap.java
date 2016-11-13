package fr.wretchedlife.map;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.Game;
import fr.wretchedlife.entity.ext.Enemy;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.factory.SoundFactory;
import fr.wretchedlife.ui.panel.GameMenuPanel;
import fr.wretchedlife.ui.panel.GamePanel;

public class GameMap {

	private String id;
	private String name;
	private Type type;
	private FloorType floorType;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private int numberOfAreas;
	private int numberOfLines;
	private ArrayList<Area> areas;
	private String groundTexturePath;
	private String groundOverTexturePath;
	private String groundSelectedTexturePath;
	private boolean isLinkedToAnotherRegion;
	
	Area selectedArea;
	Area overArea;
	
	public static enum Type {
		OUTDOOR,
		BUILDING,
		UNDERGROUND,
		DUNGEON
	}
	
	public static enum FloorType {
		GRASS,
		DIRT,
		SAND,
		SNOW,
		HOUSE,
		CAVE,
		DUNGEON
	}

	public GameMap() { 
		this.id = Constants.getRandomId( Constants.gameMapIdLength );
	}
	public GameMap(String name, Type type, FloorType floorType, ArrayList<Enemy> enemies,
			int numberOfAreas, int numberOfLines, ArrayList<Area> areas
	) {
		this.id = Constants.getRandomId( Constants.gameMapIdLength );
		this.name = name;
		this.type = type;
		this.floorType = floorType;
		this.enemies = enemies;
		this.numberOfAreas = numberOfAreas;
		this.numberOfLines = numberOfLines;
		this.areas = areas;
		this.isLinkedToAnotherRegion = false;
		
		switch( getFloorType() ) {
			case DIRT : {
				this.groundTexturePath = Constants.dirtTexturePath;
				this.groundOverTexturePath = Constants.dirtOverTexturePath;
				this.groundSelectedTexturePath = Constants.dirtSelectedTexturePath;
			} break;
			case GRASS : {
				this.groundTexturePath = Constants.grassTexturePath;
				this.groundOverTexturePath = Constants.grassOverTexturePath;
				this.groundSelectedTexturePath = Constants.grassSelectedTexturePath;			
			} break;
			case SAND : {
				this.groundTexturePath = Constants.sandTexturePath;
				this.groundOverTexturePath = Constants.sandOverTexturePath;
				this.groundSelectedTexturePath = Constants.sandSelectedTexturePath;
			} break;
			case SNOW : {
				this.groundTexturePath = Constants.snowTexturePath;
				this.groundOverTexturePath = Constants.snowOverTexturePath;
				this.groundSelectedTexturePath = Constants.snowSelectedTexturePath;
			} break;
			case HOUSE : {
				this.groundTexturePath = Constants.parquetTexturePath;
				this.groundOverTexturePath = Constants.parquetOverTexturePath;
				this.groundSelectedTexturePath = Constants.parquetSelectedTexturePath;
			} break;
			case CAVE : {
				this.groundTexturePath = Constants.rockTexturePath;
				this.groundOverTexturePath = Constants.rockOverTexturePath;
				this.groundSelectedTexturePath = Constants.rockSelectedTexturePath;
			} break;
			case DUNGEON : {
				this.groundTexturePath = Constants.dungeonTexturePath;
				this.groundOverTexturePath = Constants.dungeonOverTexturePath;
				this.groundSelectedTexturePath = Constants.dungeonSelectedTexturePath;
			}
			default : {} break;
		}
	}
	
	public Area getAreaByCoordinate(int x, int y) {
		for(int i = 0; i < getAreas().size(); i++) {
			Area area = getAreas().get( i );
			if(area.getX() == x 
			&& area.getY() == y) {
				return area;
			}
		}
		return null;
	}
	
	public Area getAreaByMouseCoordinate(int x, int y) {
		for(int i = 0; i < getAreas().size(); i++) {
			Area area = getAreas().get( i );
			if(
				(
					x > area.getX()
					&& x < area.getX() + ( area.getTexture().getIconWidth() )
				) 
				&&
				(
					y > area.getY()
					&& y < area.getY() + ( area.getTexture().getIconHeight() ) 
				) 
			) {
				return area;
			}
		}
		return null;
	}
	
	public Area getNearestAvailableArea(Area centerArea, Player player, boolean noItems) {
		Area topArea = getAreaByCoordinate(centerArea.getX(), centerArea.getY() - player.getTexture().getIconHeight() );
		Area topRightArea = getAreaByCoordinate(centerArea.getX() + player.getTexture().getIconWidth(), centerArea.getY() - player.getTexture().getIconHeight() );
		Area rightArea = getAreaByCoordinate(centerArea.getX() + player.getTexture().getIconWidth(), centerArea.getY() );
		Area bottomRightArea = getAreaByCoordinate(centerArea.getX() + player.getTexture().getIconWidth(), centerArea.getY() + player.getTexture().getIconHeight() );
		Area bottomArea = getAreaByCoordinate(centerArea.getX(), centerArea.getY() + player.getTexture().getIconHeight() );
		Area bottomLeftArea = getAreaByCoordinate(centerArea.getX() - player.getTexture().getIconWidth(), centerArea.getY() + player.getTexture().getIconHeight() );
		Area leftArea = getAreaByCoordinate(centerArea.getX() - player.getTexture().getIconWidth(), centerArea.getY() );
		Area topLeftArea = getAreaByCoordinate(centerArea.getX() - player.getTexture().getIconWidth(), centerArea.getY() - player.getTexture().getIconHeight() );
		
		if( rightArea != null && rightArea.getType() == Area.Type.GROUND_AREA 
		&& rightArea.getEntity() == null 
		&& ( !noItems || ( noItems && rightArea.getItem() == null) ) ) {
			return rightArea; 
		}
		else if( bottomRightArea != null && bottomRightArea.getType() == Area.Type.GROUND_AREA 
		&& bottomRightArea.getEntity() == null 
		&& ( !noItems || ( noItems && bottomRightArea.getItem() == null) ) ) {
			return bottomRightArea; 
		}
		else if( bottomArea != null && bottomArea.getType() == Area.Type.GROUND_AREA 
		&& bottomArea.getEntity() == null 
		&& ( !noItems || ( noItems && bottomArea.getItem() == null) ) ) {
			return bottomArea; 
		}
		else if( bottomLeftArea != null && bottomLeftArea.getType() == Area.Type.GROUND_AREA 
		&& bottomLeftArea.getEntity() == null 
		&& ( !noItems || ( noItems && bottomLeftArea.getItem() == null) ) ) {
			return bottomLeftArea; 
		}
		else if( leftArea != null && leftArea.getType() == Area.Type.GROUND_AREA 
		&& leftArea.getEntity() == null 
		&& ( !noItems || ( noItems && leftArea.getItem() == null) ) ) {
			return leftArea; 
		}
		else if( topLeftArea != null && topLeftArea.getType() == Area.Type.GROUND_AREA 
		&& topLeftArea.getEntity() == null 
		&& ( !noItems || ( noItems && topLeftArea.getItem() == null) ) ) {
			return topLeftArea; 
		}
		else if( topArea != null && topArea.getType() == Area.Type.GROUND_AREA 
		&& topArea.getEntity() == null 
		&& ( !noItems || ( noItems && topArea.getItem() == null) ) ) {
			return topArea; 
		}
		else if( topRightArea != null && topRightArea.getType() == Area.Type.GROUND_AREA 
		&& topRightArea.getEntity() == null 
		&& ( !noItems || ( noItems && topRightArea.getItem() == null) ) ) {
			return topRightArea; 
		}
		
		return null;
	}
	
	public Area getEnemyArea( Enemy e ) {
		for(int i = 0; i < getAreas().size(); i++) {
			Area area = getAreas().get( i );
			if(area.getEntity() != null && area.getEntity() instanceof Enemy
			&& area.getEntity().getId().equals( e.getId() ) ) {
				return area;
			}
		}
		return null;
	}
	
	public void manageEnemies( GamePanel gamePanel ) {
		Game game = gamePanel.getGame();
		Player player = game.getPlayer();
		Area playerArea = game.getPlayerArea();
		if( playerArea == null) return;
		
		for(int i = 0; i < game.getCurrentRegion().getEnemies().size(); i++) {
			Enemy e = game.getCurrentRegion().getEnemies().get(i);
			Area eArea = game.getCurrentRegion().getEnemyArea( e );
			if(eArea == null) continue;
			
			if( eArea.getX() >= playerArea.getX() - player.getTexture().getIconWidth()
			&& eArea.getX() <= playerArea.getX() + player.getTexture().getIconWidth() 
			&& eArea.getY() >= playerArea.getY() - player.getTexture().getIconHeight()
			&& eArea.getY() <= playerArea.getY() + player.getTexture().getIconHeight() ) {
				e.attackPlayer( gamePanel.getGameMenuPanel().getInfoPanel(), player );
			}
			
			if(playerArea.getX() <= ( eArea.getX() + (e.getTexture().getIconWidth() * Constants.enemyVisibilyRange) )
			&& playerArea.getX() >= ( eArea.getX() - (e.getTexture().getIconWidth() * Constants.enemyVisibilyRange) )
			&& playerArea.getY() <= ( eArea.getY() + (e.getTexture().getIconHeight() * Constants.enemyVisibilyRange) )
			&& playerArea.getY() >= ( eArea.getY() - (e.getTexture().getIconHeight() * Constants.enemyVisibilyRange) ) ){
				e.moveToPlayer( gamePanel );
			}
			else e.moveToRandomArea( gamePanel );
		}
	}
	
	public void attackEnemy( Area enemyArea, GamePanel gamePanel ){
		
		Game game = gamePanel.getGame();
		Player player = game.getPlayer();
		Enemy e = (Enemy) enemyArea.getEntity();
		
		int randDamage = Constants.getRandomBetween( player.getItemMinDamage(), player.getItemMaxDamage()) - e.getItemDefense();
		
		if(randDamage > 0) {
			e.setLifeRemain( e.getLifeRemain() - randDamage);
		}
		
		gamePanel.getGameMenuPanel().getInfoPanel().log( "Dealed " + randDamage + " dmg to " + e.getName() );
		SoundFactory.playSound( SoundFactory.attackSoundFilePath );
		
		if(e.getLifeRemain() <= 0) {
			gamePanel.getGameMenuPanel().getInfoPanel().log( "Killed " + e.getName() + ", earned " + e.getExperienceToEarn()+" exp" );
			if( e.getLootContainer() != null ) {
				gamePanel.getGameMenuPanel().getInfoPanel().log( e.getName() + " dropped a chest with " + e.getLootContainer().getInventory().size() + " item(s)" );
			}
			player.setExperience( player.getExperience() + e.getExperienceToEarn() );
			
			if( e.getLootContainer() != null) {
				Area nearestAreaFromEnemy = game.getCurrentRegion().getNearestAvailableArea(enemyArea, player, true);
				nearestAreaFromEnemy.setItem( e.getLootContainer() );
			}

			enemyArea.setEntity( null );
			game.getCurrentRegion().getEnemies().remove( e );
		}
		else {
			e.attackPlayer( gamePanel.getGameMenuPanel().getInfoPanel(), player );
		}
		
		gamePanel.getGameMenuPanel().clearPreviewInfoPanel();
		gamePanel.getGameMenuPanel().displayAreaInfos( gamePanel, enemyArea);
	}
	
	public void selectArea( MouseEvent e, GamePanel gamePanel ) {
		deselectArea( gamePanel.getGameMenuPanel() );
		
		Area area = getAreaByMouseCoordinate(e.getX(), e.getY());
		
		if( area != null) {
			clearAreaOver();
			
			if( area.getType() == Area.Type.GROUND_AREA ) {
				area.setTexture( Constants.getTexture( getGroundSelectedTexturePath() ) );
			}
			else if( area.getType() == Area.Type.SEA_AREA ) {
				area.setTexture( Constants.getTexture( Constants.seaAreaTexturePath ) );
			}
			
			selectedArea = area;
			gamePanel.getGameMenuPanel().displayAreaInfos( gamePanel, selectedArea );
		}
	}
	
	public void overArea( MouseEvent e ) {
		if( overArea == null
		|| ( overArea != null && !overArea.isCoordinateOfArea(e.getX(), e.getY())) ) {
			
			clearAreaOver();
	
			if( selectedArea == null
			|| ( selectedArea != null && !selectedArea.isCoordinateOfArea(e.getX(), e.getY())) ) {
				
				Area area = getAreaByMouseCoordinate(e.getX(), e.getY());
					
				if( area != null ) {
					if( area.getType() == Area.Type.GROUND_AREA ) {
						area.setTexture( Constants.getTexture( getGroundOverTexturePath() ) );
					}
					else if( area.getType() == Area.Type.SEA_AREA ) {
						area.setTexture( Constants.getTexture( Constants.seaAreaTexturePath ) );
					}
					overArea = area;
				}
			}
		}
	}
	
	public void deselectArea( GameMenuPanel gameMenuPanel ) {
		if( selectedArea != null ) {
			if( selectedArea.getType() == Area.Type.GROUND_AREA ) {
				selectedArea.setTexture( Constants.getTexture( getGroundTexturePath() ) );
			}
			else if( selectedArea.getType() == Area.Type.SEA_AREA ) {
				selectedArea.setTexture( Constants.getTexture( Constants.seaAreaTexturePath ) );
			}
			selectedArea = null;
		}
		
		gameMenuPanel.clearPreviewInfoPanel();
	}
	
	public void clearAreaOver() {
		if( overArea != null ) {
			if( overArea.getType() == Area.Type.GROUND_AREA ) {
				overArea.setTexture( Constants.getTexture( getGroundTexturePath() ) );
			}
			else if( overArea.getType() == Area.Type.SEA_AREA ) {
				overArea.setTexture( Constants.getTexture( Constants.seaAreaTexturePath ) );
			}
			overArea = null;
		}
	}
	
	public void refreshVisibleAreas( GamePanel gamePanel ) {
		Game game = gamePanel.getGame();
		Area playerArea = game.getPlayerArea();
		Player player = game.getPlayer();
		
		for(int i = 0; i < game.getCurrentRegion().getAreas().size(); i++) {
			Area area = game.getCurrentRegion().getAreas().get( i );
			
			if(area.getX() <= ( playerArea.getX() + (player.getTexture().getIconWidth() * Constants.playerVisibilyRange) )
			&& area.getX() >= ( playerArea.getX() - (player.getTexture().getIconWidth() * Constants.playerVisibilyRange) )
			&& area.getY() <= ( playerArea.getY() + (player.getTexture().getIconHeight() * Constants.playerVisibilyRange) )
			&& area.getY() >= ( playerArea.getY() - (player.getTexture().getIconHeight() * Constants.playerVisibilyRange) ) 
			) {
				area.setVisible( true );
			}
		}
		
		while( playerArea.getY() + (player.getTexture().getIconHeight() * (Constants.playerVisibilyRange + 1) ) >= gamePanel.getHeight() ){
			for(int i = 0; i < game.getCurrentRegion().getAreas().size(); i++) {
				Area area = game.getCurrentRegion().getAreas().get( i );
				area.setY( area.getY() - area.getTexture().getIconHeight() );
			}
		}
		while( playerArea.getY() - (player.getTexture().getIconHeight() * (Constants.playerVisibilyRange) ) <= 0 ){
			for(int i = 0; i < game.getCurrentRegion().getAreas().size(); i++) {
				Area area = game.getCurrentRegion().getAreas().get( i );
				area.setY( area.getY() + area.getTexture().getIconHeight() );
			}
		}
		
		while( playerArea.getX() + (player.getTexture().getIconWidth() * (Constants.playerVisibilyRange + 1) ) >= gamePanel.getWidth() ){
			for(int i = 0; i < game.getCurrentRegion().getAreas().size(); i++) {
				Area area = game.getCurrentRegion().getAreas().get( i );
				area.setX( area.getX() - area.getTexture().getIconWidth() );
			}
		}
		while( playerArea.getX() - (player.getTexture().getIconWidth() * (Constants.playerVisibilyRange + 1) ) <= 0 ){
			for(int i = 0; i < game.getCurrentRegion().getAreas().size(); i++) {
				Area area = game.getCurrentRegion().getAreas().get( i );
				area.setX( area.getX() + area.getTexture().getIconWidth() );
			}
		}
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public FloorType getFloorType() {
		return floorType;
	}
	public void setFloorType(FloorType floorType) {
		this.floorType = floorType;
	}
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
	public int getNumberOfAreas() {
		return numberOfAreas;
	}
	public void setNumberOfAreas(int numberOfAreas) {
		this.numberOfAreas = numberOfAreas;
	}
	public int getNumberOfLines() {
		return numberOfLines;
	}
	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}
	public ArrayList<Area> getAreas() {
		return areas;
	}
	public void setAreas(ArrayList<Area> areas) {
		this.areas = areas;
	}
	public String getGroundTexturePath() {
		return groundTexturePath;
	}
	public void setGroundTexturePath(String groundTexturePath) {
		this.groundTexturePath = groundTexturePath;
	}
	public String getGroundOverTexturePath() {
		return groundOverTexturePath;
	}
	public void setGroundOverTexturePath(String groundOverTexturePath) {
		this.groundOverTexturePath = groundOverTexturePath;
	}
	public String getGroundSelectedTexturePath() {
		return groundSelectedTexturePath;
	}
	public void setGroundSelectedTexturePath(String groundSelectedTexturePath) {
		this.groundSelectedTexturePath = groundSelectedTexturePath;
	}
	public boolean isLinkedToAnotherRegion() {
		return isLinkedToAnotherRegion;
	}
	public void setLinkedToAnotherRegion(boolean isLinkedToAnotherRegion) {
		this.isLinkedToAnotherRegion = isLinkedToAnotherRegion;
	}
}
