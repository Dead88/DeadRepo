package fr.wretchedlife.ui.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import fr.wretchedlife.ui.panel.GamePanel;

public class MouseMotionEventListener implements MouseMotionListener {

private GamePanel gamePanel;
	
	public MouseMotionEventListener(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		gamePanel.overArea(e);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
}
