package fr.wretchedlife.ui.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.wretchedlife.ui.panel.GamePanel;

public class MouseEventListener implements MouseListener {

	private GamePanel gamePanel;
	
	public MouseEventListener(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1)
			gamePanel.selectArea( e );
		else if(e.getButton() == 3)
			gamePanel.deselectArea();
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
}
