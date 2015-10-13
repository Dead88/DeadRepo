package fr.wretchedlife.core.utils;

import java.util.Calendar;

import javax.swing.JPanel;

import fr.wretchedlife.Constants;
import fr.wretchedlife.ui.Window;
import fr.wretchedlife.ui.panel.GameMenuPanel;
import fr.wretchedlife.ui.panel.GamePanel;
import fr.wretchedlife.ui.panel.PlayerInventoryPanel;

public class GameEventListener {
	
	private Calendar timer;
	
	public GameEventListener() {
		this.timer = Calendar.getInstance();
	}
	
	public void listen( Window w ) throws Exception {
		
		if( w.getCurrentPanel() != null && w.getCurrentPanel() instanceof GamePanel ) {
			GamePanel gamePanel = (GamePanel) w.getCurrentPanel();
			
			if( !gamePanel.hasFocus() ) gamePanel.grabFocus();

			Calendar now = Calendar.getInstance();
	
			if( ( now.getTimeInMillis() - timer.getTimeInMillis() ) >= Constants.enemyManagmentDelay ) {
				timer = Calendar.getInstance();
				gamePanel.manageEnemies();
			}
			
			if( w.getCurrentMenuPanel() != null) {
				GameMenuPanel gameMenuPanel = (GameMenuPanel)  w.getCurrentMenuPanel();
				JPanel leftHudPanel = (JPanel) gameMenuPanel.getComponent(0);
				PlayerInventoryPanel playerInventoryPanel = (PlayerInventoryPanel) leftHudPanel.getComponent(1);
				
				if( gamePanel.getGame() != null && gamePanel.getGame().getPlayer() != null ) {
					if( gamePanel.getGame().getPlayer().getLifeRemain() <= 0 ) {
						gamePanel.gameOver();
					}
					else if( gamePanel.getGame().getPlayer().getExperience() >= gamePanel.getGame().getPlayer().getExperienceToReach() ) {
						gamePanel.getGame().getPlayer().levelUp();
						playerInventoryPanel.refresh();
					}
				}
			}
		}
	}
}
