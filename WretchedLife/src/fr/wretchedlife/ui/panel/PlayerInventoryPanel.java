package fr.wretchedlife.ui.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.wretchedlife.Constants;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.obj.Item;

public class PlayerInventoryPanel extends JPanel {

	private static final long serialVersionUID = -1753937450636198706L;
	private GameMenuPanel gameMenuPanel;
	private Player player;
	
	private Image backgroundImage;
	
	public PlayerInventoryPanel( GameMenuPanel _gameMenuPanel, Player _player) {
		this.gameMenuPanel = _gameMenuPanel;
		this.player = _player;
		this.backgroundImage = Constants.getTexture( ".//img//inventoryBkg.jpg" ).getImage();
		
		for(int i=0; i < player.getInventoryMaxSize(); i++) {
			final JButton buttonItem = new JButton();
			buttonItem.setBackground( Constants.itemButtonBkgColor );
			buttonItem.setBorder( BorderFactory.createEtchedBorder(1) );
			buttonItem.setPreferredSize( new Dimension( 40, 40 ));
			buttonItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(buttonItem.getIcon() != null) {
						
						for(int i = 0; i < player.getInventory().size(); i++){
							Item playerItem = player.getInventory().get(i);
							
							if( playerItem.getId().equals( buttonItem.getName() ) ){
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayInventoryItemInfos( playerItem );
							}
						}
					}
				}
			});
			this.add( buttonItem );
		}
	}
	
	public void refresh() {
		this.removeAll();
		for(int i=0; i < player.getInventoryMaxSize(); i++) {
			final JButton buttonItem = new JButton();
			buttonItem.setBackground( Constants.itemButtonBkgColor );
			buttonItem.setBorder( BorderFactory.createEtchedBorder(1) );
			buttonItem.setPreferredSize( new Dimension( 32, 32 ));
			buttonItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(buttonItem.getIcon() != null) {
						
						for(int i = 0; i < player.getInventory().size(); i++){
							Item playerItem = player.getInventory().get(i);
							
							if( playerItem.getId().equals( buttonItem.getName() ) ){
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayInventoryItemInfos( playerItem );
							}
						}
					}
				}
			});
			this.add( buttonItem );
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		g.drawImage( backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
		
		for(int i=0; i < this.getComponentCount(); i++) {
			JButton inventoryButton = this.getComponent(i) instanceof JButton ? (JButton) this.getComponent(i) : null;
			
			if(inventoryButton==null || inventoryButton.getWidth() <= 0 || inventoryButton.getHeight() <= 0) continue;
			
			if( i < player.getInventory().size() ) {
				inventoryButton.setIcon( 
					new ImageIcon( 
						player.getInventory().get(i).getTexture().getImage().getScaledInstance( 
							inventoryButton.getWidth(), inventoryButton.getHeight(),  java.awt.Image.SCALE_SMOOTH
						)  
					) 
				);
				inventoryButton.setName( player.getInventory().get(i).getId() );
				inventoryButton.setEnabled(true);
			}
			else {
				inventoryButton.setIcon( null );
				inventoryButton.setName( "" );
				inventoryButton.setEnabled(false);
			}
		}
	}
}
