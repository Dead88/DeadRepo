package fr.wretchedlife.ui.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.SinglePlayerGame;
import fr.wretchedlife.entity.ext.Enemy;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.entity.ext.RegionEntrance;
import fr.wretchedlife.factory.SoundFactory;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;
import fr.wretchedlife.ui.Window;

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
		
		this.addMouseListener( new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == 1)
					selectArea( e );
				else if(e.getButton() == 3)
					deselectArea();
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		this.addMouseMotionListener( new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				overArea(e);
			}
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		
		this.addKeyListener( new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				
				if( !player.isMoving() ){
					
					Area playerArea = getSinglePlayerGame().getPlayerArea();
					Area destinationArea =  null;
					
					if( e.getKeyChar() == 'q' ) {
						destinationArea = getAreaByCoordinate( playerArea.getX() - player.getTexture().getIconWidth(), playerArea.getY() );
					}
					else if( e.getKeyChar() == 'z' ) {
						destinationArea = getAreaByCoordinate( playerArea.getX() , playerArea.getY() - player.getTexture().getIconHeight() );
					}
					else if( e.getKeyChar() == 'd' ) {
						destinationArea = getAreaByCoordinate( playerArea.getX() + player.getTexture().getIconWidth(), playerArea.getY() );
					}
					else if( e.getKeyChar() == 's' ) {
						destinationArea = getAreaByCoordinate( playerArea.getX() , playerArea.getY() + player.getTexture().getIconHeight() );
					}
					
					if(destinationArea == null) return;
					if(destinationArea.getType() == Area.Type.GROUND_AREA ) {
						
						deselectArea();
						
						if(destinationArea.getEntity() != null && destinationArea.getEntity() instanceof RegionEntrance ) {
							
							clearAreaOver();
							
							RegionEntrance regionEntrance = (RegionEntrance) destinationArea.getEntity();
							
							for(int i = 0; i < singlePlayerGame.getRegions().size(); i++) {
								GameMap region = singlePlayerGame.getRegions().get(i);
								
								if( regionEntrance.getRegionId().equals( region.getId() ) ) {
									
									regionEntrance.use();
									
									for(int j = 0; j < region.getAreas().size(); j++) {
										Area regionArea = region.getAreas().get(j);
										
										if(regionArea.getEntity() != null && regionArea.getEntity() instanceof RegionEntrance) {
											RegionEntrance destinationRegionExit = (RegionEntrance) regionArea.getEntity();
											
											if(destinationRegionExit.getRegionId().equals( singlePlayerGame.getCurrentRegion().getId() )) {
												player.move( getSinglePlayerGame(), playerArea, region.getAreas().get( j + 1 ) );
												singlePlayerGame.setCurrentRegion( region );
												break;
											}
										}
									}
								
									break;
								}
							}
						}
						else if(destinationArea.getEntity() == null){
							
							manageEnemies();
							
							if(destinationArea.getEntity() == null){
								player.move( getSinglePlayerGame(), playerArea, destinationArea );
								
								if(destinationArea.getItem() != null) {
									if(player.getInventory().size() == player.getInventoryMaxSize()
									|| player.getTransportedWeight() + destinationArea.getItem().getWeight() > player.getTransportableWeight()) {
										JOptionPane.showMessageDialog( window.getCurrentPanel() , "Vous ne pouvez pas ramasser cet objet !");
									}
									else {
										SoundFactory.playSound( SoundFactory.storeItemSoundFilePath );
										
										player.getInventory().add( destinationArea.getItem() );
										player.setTransportedWeight( player.getTransportedWeight() + destinationArea.getItem().getWeight() );
										destinationArea.setItem( null );
									}
								}
							}
						}
					}
					
					refreshVisibleAreas();
				}
			}
		});
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
		
		SoundFactory.playSound( SoundFactory.attackSoundFilePath );
		
		if(e.getLifeRemain() <= 0) {
			player.setExperience( player.getExperience() + e.getExperienceToEarn() );
			enemyArea.setEntity( null );
			singlePlayerGame.getCurrentRegion().getEnemies().remove( e );
		}
		else {
			e.attackPlayer( player );
		}
		
		gameMenuPanel.clearInfoPanel();
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
		
		gameMenuPanel.clearInfoPanel();
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
				e.attackPlayer( player );
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
	
	public void gameOver() {
		JOptionPane.showMessageDialog( window.getCurrentPanel() , "GAME OVER !");
		window.displayMainMenu();
	}
}
