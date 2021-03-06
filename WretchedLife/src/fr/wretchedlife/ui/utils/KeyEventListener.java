package fr.wretchedlife.ui.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.wretchedlife.core.Game;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.entity.ext.RegionEntrance;
import fr.wretchedlife.factory.SoundFactory;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;
import fr.wretchedlife.ui.panel.GamePanel;

public class KeyEventListener implements KeyListener {

	private GamePanel gamePanel;
	private Player player;
	
	public KeyEventListener(GamePanel gamePanel) {
		
		this.gamePanel = gamePanel;
		this.player = gamePanel.getGame().getPlayer();
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
			Game game = gamePanel.getGame();
			GameMap currentRegion = game.getCurrentRegion();
			Area playerArea = game.getPlayerArea();
			Area destinationArea =  null;
			
			if( e.getKeyChar() == 'q' ) {
				destinationArea = currentRegion.getAreaByCoordinate( playerArea.getX() - player.getTexture().getIconWidth(), playerArea.getY() );
			}
			else if( e.getKeyChar() == 'z' ) {
				destinationArea = currentRegion.getAreaByCoordinate( playerArea.getX() , playerArea.getY() - player.getTexture().getIconHeight() );
			}
			else if( e.getKeyChar() == 'd' ) {
				destinationArea = currentRegion.getAreaByCoordinate( playerArea.getX() + player.getTexture().getIconWidth(), playerArea.getY() );
			}
			else if( e.getKeyChar() == 's' ) {
				destinationArea = currentRegion.getAreaByCoordinate( playerArea.getX() , playerArea.getY() + player.getTexture().getIconHeight() );
			}
			
			if(destinationArea == null) return;
			if(destinationArea.getType() == Area.Type.GROUND_AREA ) {
				
				currentRegion.deselectArea( gamePanel.getGameMenuPanel() );
				
				if(destinationArea.getEntity() != null && destinationArea.getEntity() instanceof RegionEntrance ) {
					
					currentRegion.clearAreaOver();
					
					RegionEntrance regionEntrance = (RegionEntrance) destinationArea.getEntity();
					
					for(int i = 0; i < game.getRegions().size(); i++) {
						GameMap region = game.getRegions().get(i);
						
						if( regionEntrance.getRegionId().equals( region.getId() ) ) {
							
							regionEntrance.use();
							
							for(int j = 0; j < region.getAreas().size(); j++) {
								Area regionArea = region.getAreas().get(j);
								
								if(regionArea.getEntity() != null && regionArea.getEntity() instanceof RegionEntrance) {
									RegionEntrance destinationRegionExit = (RegionEntrance) regionArea.getEntity();
									
									if(destinationRegionExit.getRegionId().equals( game.getCurrentRegion().getId() )) {
										Area playerDestinationArea = region.getNearestAvailableArea( regionArea, game.getPlayer(), false );
										
										if( playerDestinationArea != null ) {
											player.move( game, playerArea, playerDestinationArea );
											game.setCurrentRegion( region );
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
					
					currentRegion.manageEnemies( gamePanel );
					
					if(destinationArea.getEntity() == null){
						player.move( game, playerArea, destinationArea );
						
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
			
			currentRegion.refreshVisibleAreas( gamePanel);
		}
	}
}
