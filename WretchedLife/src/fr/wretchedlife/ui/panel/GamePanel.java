package fr.wretchedlife.ui.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.SinglePlayerGame;
import fr.wretchedlife.entity.ext.Enemy;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.factory.SoundFactory;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;
import fr.wretchedlife.ui.Window;
import fr.wretchedlife.ui.utils.KeyEventListener;
import fr.wretchedlife.ui.utils.MouseEventListener;
import fr.wretchedlife.ui.utils.MouseMotionEventListener;

public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = -3464337157282868986L;
	
	private Window window;
	
	private Area selectedArea;
	private Area overArea;
	
	private GameMenuPanel gameMenuPanel;
	private SinglePlayerGame singlePlayerGame;
	private Player player;
	
	public SinglePlayerGame getSinglePlayerGame() {return singlePlayerGame;}
	public void setSinglePlayerGame(SinglePlayerGame singlePlayerGame) {this.singlePlayerGame = singlePlayerGame;}
	
	public GameMenuPanel getGameMenuPanel() {return gameMenuPanel;}
	public void setGameMenuPanel(GameMenuPanel gameMenuPanel) {this.gameMenuPanel = gameMenuPanel;}
	
	public GamePanel( final Window _window, SinglePlayerGame _singlePlayerGame, GameMenuPanel _gameMenuPanel ) throws Exception {
		this.window = _window;
		this.gameMenuPanel = _gameMenuPanel;
		this.singlePlayerGame = _singlePlayerGame;
		
		this.player = singlePlayerGame.getPlayer();
		
		this.setBackground( Color.CYAN );
		this.setFocusable( true );
		this.setVisible( true );
		this.setOpaque(true);

		refreshVisibleAreas();
		
		this.addMouseListener( new MouseEventListener( this ) );
		this.addMouseMotionListener( new MouseMotionEventListener( this ) );
		this.addKeyListener( new KeyEventListener( singlePlayerGame, this ) );
	}
	
	public void paintComponent(Graphics g) {	
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 
			this.getWidth(),
			this.getHeight());
		
		for(int i = 0; i < singlePlayerGame.getCurrentRegion().getAreas().size(); i++) {
			Area area = singlePlayerGame.getCurrentRegion().getAreas().get( i );
			
			if( area.isVisible()) {
				g.drawImage( area.getTexture().getImage(), 
					area.getX(), 
					area.getY(), 
					area.getTexture().getIconWidth(),
					area.getTexture().getIconHeight(),
					null
				);
			}
			else if( !area.isVisible()) {
				g.setColor( Color.BLACK );
				g.fillRect(area.getX(), 
					area.getY(), 
					area.getTexture().getIconWidth(),
					area.getTexture().getIconHeight()
				);
			}
			
			if( area.getItem() != null && area.isVisible() ) {
				g.drawImage( area.getItem().getTexture().getImage(), 
					area.getX(), 
					area.getY(), 
					area.getItem().getTexture().getIconWidth(),
					area.getItem().getTexture().getIconHeight(),
					null
				);
			}
			
			if( area.getEntity() != null && area.isVisible() ){
				g.drawImage( area.getEntity().getTexture().getImage(), 
					area.getX(), 
					area.getY(), 
					area.getEntity().getTexture().getIconWidth(),
					area.getEntity().getTexture().getIconHeight(),
					null
				);
			}	
		}
	}
	
	public void attackEnemy( Area enemyArea ){
		
		Enemy e = (Enemy) enemyArea.getEntity();
		
		int randDamage = Constants.getRandomBetween( player.getItemMinDamage(), player.getItemMaxDamage()) - e.getItemDefense();
		
		if(randDamage > 0)
			e.setLifeRemain( e.getLifeRemain() - randDamage);
		
		gameMenuPanel.getInfoPanel().log( "Dealed " + randDamage + " dmg to " + e.getName() );
		SoundFactory.playSound( SoundFactory.attackSoundFilePath );
		
		if(e.getLifeRemain() <= 0) {
			gameMenuPanel.getInfoPanel().log( "Killed " + e.getName() + ", earned " + e.getExperienceToEarn()+" exp" );
			if( e.getLootContainer() != null ) {
				gameMenuPanel.getInfoPanel().log( e.getName() + " dropped a chest with " + e.getLootContainer().getInventory().size() + " item(s)" );
			}
			player.setExperience( player.getExperience() + e.getExperienceToEarn() );
			
			if( e.getLootContainer() != null) {
				Area nearestAreaFromEnemy = getNearestAvailableArea( singlePlayerGame.getCurrentRegion(), enemyArea, true );
				nearestAreaFromEnemy.setItem( e.getLootContainer() );
			}

			enemyArea.setEntity( null );
			singlePlayerGame.getCurrentRegion().getEnemies().remove( e );
		}
		else {
			e.attackPlayer( gameMenuPanel.getInfoPanel(), player );
		}
		
		gameMenuPanel.clearPreviewInfoPanel();
		gameMenuPanel.displayAreaInfos( this, enemyArea);
	}
	
	public void selectArea( MouseEvent e ) {
		
		deselectArea();
		
		for(int i = 0; i < singlePlayerGame.getCurrentRegion().getAreas().size(); i++ ){
			Area area = singlePlayerGame.getCurrentRegion().getAreas().get(i);
			
			if( isCoordinateOfArea(e.getX(), e.getY(), area) ) {
				
				clearAreaOver();
				
				if( area.getType() == Area.Type.GROUND_AREA )
					area.setTexture( Constants.getTexture( singlePlayerGame.getCurrentRegion().getGroundSelectedTexturePath() ) );
				else if( area.getType() == Area.Type.SEA_AREA )
					area.setTexture( Constants.getTexture( Constants.seaAreaTexturePath ) );
				selectedArea = area;
				
				gameMenuPanel.displayAreaInfos( this, selectedArea );
				
				break;
			}
		}
	}
	
	public void overArea( MouseEvent e ) {
		
		if( overArea == null
		|| ( overArea != null && !isCoordinateOfArea(e.getX(), e.getY(), overArea)) ) {
			
			clearAreaOver();
	
			if( selectedArea == null
			|| ( selectedArea != null && !isCoordinateOfArea(e.getX(), e.getY(), selectedArea)) ) {
				
				for( int i = 0; i < singlePlayerGame.getCurrentRegion().getAreas().size(); i++ ){
					Area area = singlePlayerGame.getCurrentRegion().getAreas().get(i);
					
					if( isCoordinateOfArea(e.getX(), e.getY(), area) ) {
						
						if( area.getType() == Area.Type.GROUND_AREA )
							area.setTexture( Constants.getTexture( singlePlayerGame.getCurrentRegion().getGroundOverTexturePath() ) );
						else if( area.getType() == Area.Type.SEA_AREA )
							area.setTexture( Constants.getTexture( Constants.seaAreaTexturePath ) );
						overArea = area;
						break;
					}
				}
			}
		}
	}
	
	public void deselectArea() {
		if( selectedArea != null ) {
			if( selectedArea.getType() == Area.Type.GROUND_AREA )
				selectedArea.setTexture( Constants.getTexture( singlePlayerGame.getCurrentRegion().getGroundTexturePath() ) );
			else if( selectedArea.getType() == Area.Type.SEA_AREA )
				selectedArea.setTexture( Constants.getTexture( Constants.seaAreaTexturePath ) );
			selectedArea = null;
		}
		
		gameMenuPanel.clearPreviewInfoPanel();
	}
	
	public void clearAreaOver() {
		
		if( overArea != null ) {
			if( overArea.getType() == Area.Type.GROUND_AREA )
				overArea.setTexture( Constants.getTexture( singlePlayerGame.getCurrentRegion().getGroundTexturePath() ) );
			else if( overArea.getType() == Area.Type.SEA_AREA )
				overArea.setTexture( Constants.getTexture( Constants.seaAreaTexturePath ) );
			overArea = null;
		}
	}
	
	public void refreshVisibleAreas() {
		Area playerArea = getSinglePlayerGame().getPlayerArea();
		
		for(int i = 0; i < singlePlayerGame.getCurrentRegion().getAreas().size(); i++) {
			Area area = singlePlayerGame.getCurrentRegion().getAreas().get( i );
			
			if(area.getX() <= ( playerArea.getX() + (player.getTexture().getIconWidth() * Constants.playerVisibilyRange) )
			&& area.getX() >= ( playerArea.getX() - (player.getTexture().getIconWidth() * Constants.playerVisibilyRange) )
			&& area.getY() <= ( playerArea.getY() + (player.getTexture().getIconHeight() * Constants.playerVisibilyRange) )
			&& area.getY() >= ( playerArea.getY() - (player.getTexture().getIconHeight() * Constants.playerVisibilyRange) ) 
			) {
				area.setVisible( true );
			}
		}
		
		while( playerArea.getY() + (player.getTexture().getIconHeight() * (Constants.playerVisibilyRange + 1) ) >= this.getHeight() ){
			for(int i = 0; i < singlePlayerGame.getCurrentRegion().getAreas().size(); i++) {
				Area area = singlePlayerGame.getCurrentRegion().getAreas().get( i );
				area.setY( area.getY() - area.getTexture().getIconHeight() );
			}
		}
		while( playerArea.getY() - (player.getTexture().getIconHeight() * (Constants.playerVisibilyRange) ) <= 0 ){
			for(int i = 0; i < singlePlayerGame.getCurrentRegion().getAreas().size(); i++) {
				Area area = singlePlayerGame.getCurrentRegion().getAreas().get( i );
				area.setY( area.getY() + area.getTexture().getIconHeight() );
			}
		}
		
		while( playerArea.getX() + (player.getTexture().getIconWidth() * (Constants.playerVisibilyRange + 1) ) >= this.getWidth() ){
			for(int i = 0; i < singlePlayerGame.getCurrentRegion().getAreas().size(); i++) {
				Area area = singlePlayerGame.getCurrentRegion().getAreas().get( i );
				area.setX( area.getX() - area.getTexture().getIconWidth() );
			}
		}
		while( playerArea.getX() - (player.getTexture().getIconWidth() * (Constants.playerVisibilyRange + 1) ) <= 0 ){
			for(int i = 0; i < singlePlayerGame.getCurrentRegion().getAreas().size(); i++) {
				Area area = singlePlayerGame.getCurrentRegion().getAreas().get( i );
				area.setX( area.getX() + area.getTexture().getIconWidth() );
			}
		}
	}
	
	public void manageEnemies() {
		
		Area playerArea = getSinglePlayerGame().getPlayerArea();
		if( playerArea == null) return;
		
		for(int i = 0; i < singlePlayerGame.getCurrentRegion().getEnemies().size(); i++) {
			Enemy e = singlePlayerGame.getCurrentRegion().getEnemies().get(i);
			Area eArea = getEnemyArea( e.getId() );
			if(eArea == null) continue;
			
			if( eArea.getX() >= playerArea.getX() - player.getTexture().getIconWidth()
			&& eArea.getX() <= playerArea.getX() + player.getTexture().getIconWidth() 
			&& eArea.getY() >= playerArea.getY() - player.getTexture().getIconHeight()
			&& eArea.getY() <= playerArea.getY() + player.getTexture().getIconHeight() ) {
				e.attackPlayer( gameMenuPanel.getInfoPanel(), player );
			}
			
			if(playerArea.getX() <= ( eArea.getX() + (e.getTexture().getIconWidth() * Constants.enemyVisibilyRange) )
			&& playerArea.getX() >= ( eArea.getX() - (e.getTexture().getIconWidth() * Constants.enemyVisibilyRange) )
			&& playerArea.getY() <= ( eArea.getY() + (e.getTexture().getIconHeight() * Constants.enemyVisibilyRange) )
			&& playerArea.getY() >= ( eArea.getY() - (e.getTexture().getIconHeight() * Constants.enemyVisibilyRange) ) ){
				e.moveToPlayer( this );
			}
			else e.moveToRandomArea( this );
		}
	}
	
	public static boolean isCoordinateOfArea(int x, int y, Area area) {
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
			return true;
		}
		
		return false;
	}
	
//	public Area getPlayerArea() {
//		for(int i = 0; i < singlePlayerGame.getCurrentRegion().getAreas().size(); i++) {
//			Area area = singlePlayerGame.getCurrentRegion().getAreas().get( i );
//			if(area.getEntity() != null && area.getEntity() instanceof Player) {
//				return area;
//			}
//		}
//		return null;
//	}
	
	public Area getEnemyArea( String enemyId ) {
		for(int i = 0; i < singlePlayerGame.getCurrentRegion().getAreas().size(); i++) {
			Area area = singlePlayerGame.getCurrentRegion().getAreas().get( i );
			if(area.getEntity() != null && area.getEntity() instanceof Enemy
			&& area.getEntity().getId().equals( enemyId ) ) {
				return area;
			}
		}
		return null;
	}
	
	public Area getAreaByCoordinate(int x, int y) {
		for(int i = 0; i < singlePlayerGame.getCurrentRegion().getAreas().size(); i++) {
			Area area = singlePlayerGame.getCurrentRegion().getAreas().get( i );
			if(area.getX() == x 
			&& area.getY() == y) {
				return area;
			}
		}
		return null;
	}
	
	public Area getRegionAreaByCoordinate( GameMap region, int x, int y) {
		for(int i = 0; i < region.getAreas().size(); i++) {
			Area area = region.getAreas().get( i );
			if(area.getX() == x 
			&& area.getY() == y) {
				return area;
			}
		}
		return null;
	}
	
	public Area getNearestAvailableArea( GameMap region, Area centerArea, boolean noItems) {
		Area topArea = getRegionAreaByCoordinate(region, centerArea.getX(), centerArea.getY() - player.getTexture().getIconHeight() );
		Area topRightArea = getRegionAreaByCoordinate(region, centerArea.getX() + player.getTexture().getIconWidth(), centerArea.getY() - player.getTexture().getIconHeight() );
		Area rightArea = getRegionAreaByCoordinate(region, centerArea.getX() + player.getTexture().getIconWidth(), centerArea.getY() );
		Area bottomRightArea = getRegionAreaByCoordinate(region, centerArea.getX() + player.getTexture().getIconWidth(), centerArea.getY() + player.getTexture().getIconHeight() );
		Area bottomArea = getRegionAreaByCoordinate(region, centerArea.getX(), centerArea.getY() + player.getTexture().getIconHeight() );
		Area bottomLeftArea = getRegionAreaByCoordinate(region, centerArea.getX() - player.getTexture().getIconWidth(), centerArea.getY() + player.getTexture().getIconHeight() );
		Area leftArea = getRegionAreaByCoordinate(region, centerArea.getX() - player.getTexture().getIconWidth(), centerArea.getY() );
		Area topLeftArea = getRegionAreaByCoordinate(region, centerArea.getX() - player.getTexture().getIconWidth(), centerArea.getY() - player.getTexture().getIconHeight() );
		
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
	
	public void gameOver() {
		JOptionPane.showMessageDialog( window.getCurrentPanel() , "GAME OVER !");
		window.displayMainMenu();
	}
}
