package fr.wretchedlife.ui.panel;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.wretchedlife.Constants;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.ui.Window;

public class PlayerInfoPanel extends JPanel {
	
	private static final long serialVersionUID = 7673009830989360583L;
	private final Window window;
	private Player player;
	
	private Image backgroundImage;
	
	public PlayerInfoPanel( final Window _window, Player _player ) {
		this.window = _window;
		this.player = _player;
		this.backgroundImage = Constants.getTexture( ".//img//inventoryBkg.jpg" ).getImage();
		this.setLayout( new GridLayout(2,4) );
		this.add( new JLabel() );
		this.add( new JLabel() );
		this.add( new JLabel() );
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1; 
		
		JPanel skillPointsButtonPanel = new JPanel(); 
		skillPointsButtonPanel.setLayout( new GridBagLayout() )	;
		skillPointsButtonPanel.setOpaque(false);
		
		ImageIcon skillPointsButtonIcon = Constants.getTexture( ".//img//plusbtn.png" );
		JButton strenghPointAddButton = new JButton( skillPointsButtonIcon );
		strenghPointAddButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(player.getSkillPointsLeft() > 0 ) {
					player.setStrengh( player.getStrengh() + 1 );
					player.setSkillPointsLeft( player.getSkillPointsLeft() - 1 );
				}
			}
		});
		skillPointsButtonPanel.add( strenghPointAddButton, gbc );
		
		JButton agilityPointAddButton = new JButton( skillPointsButtonIcon );
		agilityPointAddButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(player.getSkillPointsLeft() > 0 ) {
					player.setAgility( player.getAgility() + 1 );
					player.setSkillPointsLeft( player.getSkillPointsLeft() - 1 );
				}
			}
		});
		skillPointsButtonPanel.add( agilityPointAddButton, gbc );
		
		JButton knowledgePointAddButton = new JButton( skillPointsButtonIcon );
		knowledgePointAddButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(player.getSkillPointsLeft() > 0 ) {
					player.setKnowledge( player.getKnowledge() + 1 );
					player.setSkillPointsLeft( player.getSkillPointsLeft() - 1 );
				}
			}
		});
		skillPointsButtonPanel.add( knowledgePointAddButton, gbc );
		
		this.add( skillPointsButtonPanel );
		
		this.add( new JLabel() );
		this.add( new JLabel() );
		
		JPanel gameOptionPanel = new JPanel();
		gameOptionPanel.setOpaque(false);
		JButton lvlUpButton = new JButton("XP");
		lvlUpButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				player.setExperience( player.getExperience() + 10 );
			}
		});
		JButton mainMenuButton = new JButton("Quitter");
		mainMenuButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous vraimment quitter la partie ?","Attention", 0);
				if(dialogResult == JOptionPane.YES_OPTION){
					window.displayMainMenu();
				}
			}
		});
		gameOptionPanel.add( lvlUpButton );
		gameOptionPanel.add( mainMenuButton );
		this.add( gameOptionPanel );
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		g.drawImage( backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
		
		((JLabel) this.getComponent(0)).setForeground(Constants.goldColor);
		((JLabel) this.getComponent(1)).setForeground(Constants.goldColor);
		((JLabel) this.getComponent(2)).setForeground(Constants.goldColor);
		((JLabel) this.getComponent(4)).setForeground(Constants.goldColor);
		((JLabel) this.getComponent(5)).setForeground(Constants.goldColor);
		
		((JLabel) this.getComponent(0)).setText("<html>Niveau " + player.getLevel()+"<br />"+"Exp " + player.getExperience()+" / "+ player.getExperienceToReach()+"</html>" ) ;
		((JLabel) this.getComponent(1)).setText("<html>"
				+ "Vie " + player.getLifeRemain()+" / "+ player.getLife()+"<br />"
				+ "Faim "+ player.getHungerPercent()+" %<br />"
				+ "Soif "+ player.getThirstPercent()+" %</html>");
		((JLabel) this.getComponent(2)).setText("<html>"
				+ "Force " + player.getStrengh() + "<br />"
				+ "Agilité " + player.getAgility() + "<br />"
				+ "Savoir "+ player.getKnowledge()+"</html>" );
		
		JPanel skillPointsButtonPanel = (JPanel) this.getComponent(3);
		if( player.getSkillPointsLeft() > 0 ) 
			skillPointsButtonPanel.setVisible(true);
		else skillPointsButtonPanel.setVisible(false);
		
		((JLabel) this.getComponent(4)).setText("<html>Inventaire " + player.getInventory().size()+" / "+ player.getInventoryMaxSize()+"<br />Poids " + player.getTransportedWeight()+" / "+ player.getTransportableWeight()+"</html>" );
		((JLabel) this.getComponent(5)).setText("<html>"
				+ "Dégâts " + player.getItemMinDamage() +" à "+player.getItemMaxDamage()+"<br />"
				+ "Armure " + player.getItemDefense() +"</html>");
	}
}
