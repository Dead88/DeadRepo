package fr.wretchedlife.core.utils;

import javax.swing.JPanel;

import fr.wretchedlife.ui.Window;
import fr.wretchedlife.ui.panel.GameMenuPanel;
import fr.wretchedlife.ui.panel.GamePanel;
import fr.wretchedlife.ui.panel.PlayerInventoryPanel;

public class GameEventListener {
	
	public GameEventListener() {}
	
	public void listen( Window w ) throws Exception {
		
		if( w.getCurrentPanel() != null && w.getCurrentPanel() instanceof GamePanel ) {
			GamePanel gamePanel = (GamePanel) w.getCurrentPanel();
			
			if( !gamePanel.hasFocus() ) gamePanel.grabFocus();
			
			if( w.getCurrentMenuPanel() != null) {
				GameMenuPanel gameMenuPanel = (GameMenuPanel)  w.getCurrentMenuPanel();
				JPanel leftHudPanel = (JPanel) gameMenuPanel.getComponent(0);
				PlayerInventoryPanel playerInventoryPanel = (PlayerInventoryPanel) leftHudPanel.getComponent(1);
				
				if( gamePanel.getSinglePlayerGame() != null && gamePanel.getSinglePlayerGame().getPlayer() != null ) {
					if( gamePanel.getSinglePlayerGame().getPlayer().getLifeRemain() <= 0 ) {
						gamePanel.gameOver();
					}
					else if( gamePanel.getSinglePlayerGame().getPlayer().getExperience() >= gamePanel.getSinglePlayerGame().getPlayer().getExperienceToReach() ) {
						gamePanel.getSinglePlayerGame().getPlayer().levelUp();
						playerInventoryPanel.refresh();
					}
				}
			}
		}
	}
}
