package fr.wretchedlife.ui.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.wretchedlife.Constants;
import fr.wretchedlife.entity.ext.Player;

public class PlayerEquipementPanel extends JPanel {

	private static final long serialVersionUID = 2336990588863184812L;
	private GameMenuPanel gameMenuPanel;
	private Player player;
	
	private Image backgroundImage;
	private static Dimension equipementButtonDimension = new Dimension( 48, 48 );
	
	public PlayerEquipementPanel( GameMenuPanel _gameMenuPanel, Player _player) {
		this.gameMenuPanel = _gameMenuPanel;
		this.player = _player;
		this.backgroundImage = Constants.getTexture( ".//img//inventoryBkg.jpg" ).getImage();
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new GridLayout(5, 3));
		
		JButton leftShoulderButton = new JButton();
		leftShoulderButton.setBackground( Constants.itemButtonBkgColor );
		leftShoulderButton.setBorder( BorderFactory.createEtchedBorder(1) );
		leftShoulderButton.setName("leftShoulderButton");
		leftShoulderButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( leftShoulderButton );
		
		JButton headArmorButton = new JButton();
		headArmorButton.setBackground( Constants.itemButtonBkgColor );
		headArmorButton.setBorder( BorderFactory.createEtchedBorder(1) );
		headArmorButton.setName("headArmorButton");
		headArmorButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( headArmorButton );
		
		JButton rightShoulderArmorButton = new JButton();
		rightShoulderArmorButton.setBackground( Constants.itemButtonBkgColor );
		rightShoulderArmorButton.setBorder( BorderFactory.createEtchedBorder(1) );
		rightShoulderArmorButton.setName("rightShoulderArmorButton");
		rightShoulderArmorButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( rightShoulderArmorButton );
		
		JButton armArmorButton = new JButton();
		armArmorButton.setBackground( Constants.itemButtonBkgColor );
		armArmorButton.setBorder( BorderFactory.createEtchedBorder(1) );
		armArmorButton.setName("armArmorButton");
		armArmorButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( armArmorButton );
		
		JButton chestArmorButton = new JButton();
		chestArmorButton.setBackground( Constants.itemButtonBkgColor );
		chestArmorButton.setBorder( BorderFactory.createEtchedBorder(1) );
		chestArmorButton.setName("chestArmorButton");
		chestArmorButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( chestArmorButton );
		
		armArmorButton = new JButton();
		armArmorButton.setBackground( Constants.itemButtonBkgColor );
		armArmorButton.setBorder( BorderFactory.createEtchedBorder(1) );
		armArmorButton.setName("armArmorButton");
		armArmorButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( armArmorButton );
		
		JButton handsArmorButton = new JButton();
		handsArmorButton.setBackground( Constants.itemButtonBkgColor );
		handsArmorButton.setBorder( BorderFactory.createEtchedBorder(1) );
		handsArmorButton.setName("handsArmorButton");
		handsArmorButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( handsArmorButton );

		JButton beltArmorButton = new JButton();
		beltArmorButton.setBackground( Constants.itemButtonBkgColor );
		beltArmorButton.setBorder( BorderFactory.createEtchedBorder(1) );
		beltArmorButton.setName("beltArmorButton");
		beltArmorButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( beltArmorButton );
		
		handsArmorButton = new JButton();
		handsArmorButton.setBackground( Constants.itemButtonBkgColor );
		handsArmorButton.setBorder( BorderFactory.createEtchedBorder(1) );
		handsArmorButton.setName("handsArmorButton");
		handsArmorButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( handsArmorButton );
		
		JButton leftHandWeaponItemButton = new JButton();
		leftHandWeaponItemButton.setBackground( Constants.itemButtonBkgColor );
		leftHandWeaponItemButton.setBorder( BorderFactory.createEtchedBorder(1) );
		leftHandWeaponItemButton.setName("leftHandWeaponItemButton");
		leftHandWeaponItemButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( leftHandWeaponItemButton );
		
		JButton legsArmorButton = new JButton();
		legsArmorButton.setBackground( Constants.itemButtonBkgColor );
		legsArmorButton.setBorder( BorderFactory.createEtchedBorder(1) );
		legsArmorButton.setName("legsArmorButton");
		legsArmorButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( legsArmorButton );

		JButton rightHandWeaponItemButton = new JButton();
		rightHandWeaponItemButton.setBackground( Constants.itemButtonBkgColor );
		rightHandWeaponItemButton.setBorder( BorderFactory.createEtchedBorder(1) );
		rightHandWeaponItemButton.setName("rightHandWeaponItemButton");
		rightHandWeaponItemButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( rightHandWeaponItemButton );
		
		buttonPanel.add( new JLabel() );
		
		JButton feetArmorButton = new JButton();
		feetArmorButton.setBackground( Constants.itemButtonBkgColor );
		feetArmorButton.setBorder( BorderFactory.createEtchedBorder(1) );
		feetArmorButton.setName("feetArmorButton");
		feetArmorButton.setPreferredSize( equipementButtonDimension );
		buttonPanel.add( feetArmorButton );
		
		buttonPanel.add( new JLabel() );
		
		this.add( buttonPanel );
	}
	
	@Override
	protected void paintComponent(Graphics g) {

		g.drawImage( backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
		
		JPanel buttonPanel = (JPanel) this.getComponent(0);
		
		for( int i = 0; i < buttonPanel.getComponentCount(); i++ ){
			if( buttonPanel.getComponent(i) instanceof JLabel )
				continue;
			
			JButton equipmentButton = (JButton) buttonPanel.getComponent(i);
			ImageIcon img = null;

			if( equipmentButton.getActionListeners() != null && equipmentButton.getActionListeners().length > 0)
				equipmentButton.removeActionListener( equipmentButton.getActionListeners()[0] );
			
			switch( equipmentButton.getName() ){
				case "leftShoulderButton" : {
					if( player.getLeftShoulderArmor() != null ) {
						img = player.getHeadArmor().getTexture();
						equipmentButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayEquipedItemInfos( player.getLeftShoulderArmor() );
							}
						});
					}
				}break ;
				case "headArmorButton" : {
					if( player.getHeadArmor() != null ) {
						img = player.getHeadArmor().getTexture();
						equipmentButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayEquipedItemInfos( player.getHeadArmor() );
							}
						});
					}
				}break ;
				case "rightShoulderArmorButton" : {
					if( player.getRightShoulderArmor() != null ) {
						img = player.getRightShoulderArmor().getTexture();	
						equipmentButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayEquipedItemInfos( player.getRightShoulderArmor() );
							}
						});
					}
				}break ;
				case "armArmorButton" : {
					if( player.getArmArmor() != null ) {
						img = player.getArmArmor().getTexture();	
						equipmentButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayEquipedItemInfos( player.getArmArmor() );
							}
						});
					}
				}break ;
				case "chestArmorButton" : {
					if( player.getChestArmor() != null ) {
						img = player.getChestArmor().getTexture();
						equipmentButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayEquipedItemInfos( player.getChestArmor() );
							}
						});
					}
				}break ;
				case "handsArmorButton" : {
					if( player.getHandsArmor() != null ) {
						img = player.getHandsArmor().getTexture();	
						equipmentButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayEquipedItemInfos( player.getHandsArmor() );
							}
						});
					}
				}break ;
				case "beltArmorButton" : {
					if( player.getBeltArmor() != null ) {
						img = player.getBeltArmor().getTexture();	
						equipmentButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayEquipedItemInfos( player.getBeltArmor() );
							}
						});
					}
				}break ;
				case "leftHandWeaponItemButton" : {
					if( player.getLeftHandWeaponItem() != null ) {
						img = player.getLeftHandWeaponItem().getTexture();	
						equipmentButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayEquipedItemInfos( player.getLeftHandWeaponItem() );
							}
						});
					}
				}break ;
				case "rightHandWeaponItemButton" : {
					if( player.getRightHandWeaponItem() != null ) {
						img = player.getRightHandWeaponItem().getTexture();	
						equipmentButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayEquipedItemInfos( player.getRightHandWeaponItem() );
							}
						});
					}
				}break ;
				case "legsArmorButton" : {
					if( player.getLegsArmor() != null ) {
						img = player.getLegsArmor().getTexture();	
						equipmentButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayEquipedItemInfos( player.getLegsArmor() );
							}
						});
					}
				}break ;
				case "feetArmorButton" : {
					if( player.getFeetArmor() != null ) {
						img = player.getFeetArmor().getTexture();	
						equipmentButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								gameMenuPanel.clearPreviewInfoPanel();
								gameMenuPanel.displayEquipedItemInfos( player.getFeetArmor() );
							}
						});
					}
				}break ;
			}
			
			if(img != null) {
				ImageIcon scaledImg = new ImageIcon( img.getImage().getScaledInstance( 
						equipmentButton.getWidth(), 
						equipmentButton.getHeight(),  
						java.awt.Image.SCALE_SMOOTH
					)  
				) ;
				equipmentButton.setIcon( scaledImg );
				equipmentButton.setEnabled(true);
			}
			else {
				equipmentButton.setIcon( null );
				equipmentButton.setEnabled(false);
			}
		}
	}
}
