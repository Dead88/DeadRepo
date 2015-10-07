package fr.wretchedlife.ui.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.wretchedlife.core.SinglePlayerGame;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.entity.ext.RegionEntrance;
import fr.wretchedlife.factory.SoundFactory;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;
import fr.wretchedlife.ui.panel.GamePanel;

public class KeyEventListener implements KeyListener {

	private SinglePlayerGame singlePlayerGame;
	private GamePanel gamePanel;
	private Player player;
	
	public KeyEventListener(SinglePlayerGame singlePlayerGame, GamePanel gamePanel) {
		this.singlePlayerGame = singlePlayerGame;
		this.gamePanel = gamePanel;
		this.player = singlePlayerGame.getPlayer();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
				
		if( !player.isMoving() ){
			
			Area playerArea = singlePlayerGame.getPlayerArea();
			Area destinationArea =  null;
			
			if( e.getKeyChar() == 'q' ) {
				destinationArea = gamePanel.getAreaByCoordinate( playerArea.getX() - player.getTexture().getIconWidth(), playerArea.getY() );
			}
			else if( e.getKeyChar() == 'z' ) {
				destinationArea = gamePanel.getAreaByCoordinate( playerArea.getX() , playerArea.getY() - player.getTexture().getIconHeight() );
			}
			else if( e.getKeyChar() == 'd' ) {
				destinationArea = gamePanel.getAreaByCoordinate( playerArea.getX() + player.getTexture().getIconWidth(), playerArea.getY() );
			}
			else if( e.getKeyChar() == 's' ) {
				destinationArea = gamePanel.getAreaByCoordinate( playerArea.getX() , playerArea.getY() + player.getTexture().getIconHeight() );
			}
			
			if(destinationArea == null) return;
			if(destinationArea.getType() == Area.Type.GROUND_AREA ) {
				
				gamePanel.deselectArea();
				
				if(destinationArea.getEntity() != null && destinationArea.getEntity() instanceof RegionEntrance ) {
					
					gamePanel.clearAreaOver();
					
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
										Area playerDestinationArea = gamePanel.getNearestAvailableArea( region, regionArea, false );
										
										if( playerDestinationArea != null ) {
											player.move( singlePlayerGame, playerArea, playerDestinationArea );
											singlePlayerGame.setCurrentRegion( region );
											break;
										}
									}
								}
							}
						
							break;
						}
					}
				}
				else if(destinationArea.getEntity() == null){
					
					gamePanel.manageEnemies();
					
					if(destinationArea.getEntity() == null){
						player.move( singlePlayerGame, playerArea, destinationArea );
						
						if(destinationArea.getItem() != null) {
							if(player.getInventory().size() == player.getInventoryMaxSize()
							|| player.getTransportedWeight() + destinationArea.getItem().getWeight() > player.getTransportableWeight()) {
								SoundFactory.playSound( SoundFactory.impossibleFilePath );
							}
							else {
								SoundFactory.playSound( SoundFactory.storeItemSoundFilePath );
								gamePanel.getGameMenuPanel().getInfoPanel().log( "Picked up " + destinationArea.getItem().getName() );
								
								player.getInventory().add( destinationArea.getItem() );
								player.setTransportedWeight( player.getTransportedWeight() + destinationArea.getItem().getWeight() );
								destinationArea.setItem( null );
							}
						}
					}
				}
			}
			
			gamePanel.refreshVisibleAreas();
		}
	}
}
