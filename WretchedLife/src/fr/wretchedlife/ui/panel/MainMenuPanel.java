package fr.wretchedlife.ui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.wretchedlife.Constants;
import fr.wretchedlife.ui.Window;

public class MainMenuPanel extends JPanel {
	
	private static final long serialVersionUID = -2754785116380521108L;
	private static final String NEW_GAME_BUTTON_LABEL = "Nouvelle Partie";
	private static final String NEW_MULTIPLAYER_GAME_BUTTON_LABEL = "Nouvelle Partie Multijoueur";
	private static final String JOIN_MULTIPLAYER_GAME_BUTTON_LABEL = "Rejoindre Partie Multijoueur";
	private static final String EXIT_BUTTON_LABEL = "Quitter";

	private final Window window;
	private Image backgroundImage;
	
	public MainMenuPanel( final Window _window ) {
		this.window = _window;
		this.setLayout( new BorderLayout() );
		this.backgroundImage = Constants.getTexture( ".//img//menuBkg.jpg" ).getImage();
		
		buildPanel();
	}
	
	public void buildPanel() {
		JButton newGameButton = new JButton( NEW_GAME_BUTTON_LABEL );
		newGameButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					window.startNewgame();
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		JButton newMultiGameButton = new JButton( NEW_MULTIPLAYER_GAME_BUTTON_LABEL );
		newMultiGameButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					window.startNewMultiplayerGame();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		newMultiGameButton.setVisible(true);
		
		JButton joinMultiGameButton = new JButton( JOIN_MULTIPLAYER_GAME_BUTTON_LABEL );
		joinMultiGameButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String serverAddr = JOptionPane.showInputDialog("Adresse de l'hôte :");
					
					if(serverAddr != null)
						window.joinMultiplayerGame( serverAddr );
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		joinMultiGameButton.setVisible(true);
		
		JButton exitButton = new JButton( EXIT_BUTTON_LABEL );
		exitButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.exit(0);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setOpaque( false );
		buttonsPanel.setPreferredSize( new Dimension(500,500));
		buttonsPanel.add( newGameButton );
		buttonsPanel.add( newMultiGameButton );
		buttonsPanel.add( joinMultiGameButton );
		buttonsPanel.add( exitButton );
		
		this.add( buttonsPanel, BorderLayout.SOUTH);
	}
	
	public void paintComponent(Graphics g) {	
		
		g.drawImage( backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
