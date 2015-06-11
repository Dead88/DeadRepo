package fr.wretchedlife.ui.panel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import fr.wretchedlife.Constants;

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = -5684147110040142375L;
	private Image backgroundImage;
	
	public InfoPanel() {
		this.backgroundImage = Constants.getTexture( ".//img//inventoryBkg.jpg" ).getImage();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage( backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
