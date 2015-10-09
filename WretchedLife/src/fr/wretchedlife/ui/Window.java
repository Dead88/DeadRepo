package fr.wretchedlife.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.wretchedlife.core.Game;
import fr.wretchedlife.core.ext.GameClient;
import fr.wretchedlife.core.ext.GameServer;
import fr.wretchedlife.ui.panel.GameMenuPanel;
import fr.wretchedlife.ui.panel.GamePanel;
import fr.wretchedlife.ui.panel.MainMenuPanel;
import fr.wretchedlife.ui.utils.WindowEventListener;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel currentPanel;
	private JPanel currentMenuPanel;
	
	public JPanel getCurrentPanel() {return currentPanel;}
	public void setCurrentPanel(JPanel currentPanel) {this.currentPanel = currentPanel;}
	public JPanel getCurrentMenuPanel() {return currentMenuPanel;}
	public void setCurrentMenuPanel(JPanel currentMenuPanel) {this.currentMenuPanel = currentMenuPanel;}

	public Window() {
		this.setTitle("Wretched Life");
		this.setSize( Toolkit.getDefaultToolkit().getScreenSize() );
		//this.setSize( new Dimension( 1366, 768));
		this.setResizable(true);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setIconImage(premierMechant.getTexture());
		this.setLayout( new BorderLayout() );
		this.addWindowListener( new WindowEventListener( this ) );
		
		//SoundFactory.playAmbientSound();
	}

	public void displayMainMenu() {
		if(currentMenuPanel != null) {
			if(currentPanel instanceof GamePanel ) {
				GamePanel gp = (GamePanel) currentPanel;
				try {
					if(gp.getGame() instanceof GameServer) ((GameServer) gp.getGame()).getHost().close();
					else if(gp.getGame() instanceof GameClient) ((GameClient) gp.getGame()).getClientSocket().close();
					gp.setGame(null);
				}
				catch(Exception e) {}
			}
			this.remove( currentMenuPanel );
			currentMenuPanel = null;
		}
		if(currentPanel != null) {
			this.remove( currentPanel );
			currentPanel = null;
		}
		
		MainMenuPanel mainMenuPanel = new MainMenuPanel( this );
		this.add( mainMenuPanel, BorderLayout.CENTER );
		this.currentPanel = mainMenuPanel;
	}
	
	public void startNewgame() throws Exception {
		if(currentPanel != null) {
			if(currentPanel instanceof GamePanel ) {
				GamePanel gp = (GamePanel) currentPanel;
				if(gp.getGame() instanceof GameServer) ((GameServer) gp.getGame()).getHost().close();
				else if(gp.getGame() instanceof GameClient) ((GameClient) gp.getGame()).getClientSocket().close();
				gp.setGame(null);
			}
			this.remove( currentPanel );
			currentPanel = null;
		}
		if(currentMenuPanel != null) {
			this.remove( currentMenuPanel );
			currentMenuPanel = null;
		}

		Game game = new Game( true );
		
		GameMenuPanel gameMenuPanel = new GameMenuPanel( this, game );
		GamePanel gamePanel = new GamePanel( this, game, gameMenuPanel );
		
		this.add( gameMenuPanel , BorderLayout.SOUTH );
		this.add( gamePanel, BorderLayout.CENTER );
		
		this.currentMenuPanel = gameMenuPanel;
		this.currentPanel = gamePanel;
	}
	
	public void goToNewGameMenu() {
		
	}
	
	public void startNewMultiplayerGame() throws Exception {
		if(currentPanel != null) {
			if(currentPanel instanceof GamePanel ) {
				GamePanel gp = (GamePanel) currentPanel;
				if(gp.getGame() instanceof GameServer) ((GameServer) gp.getGame()).getHost().close();
				else if(gp.getGame() instanceof GameClient) ((GameClient) gp.getGame()).getClientSocket().close();
				gp.setGame(null);
			}
			this.remove( currentPanel );
			currentPanel = null;
		}
		if(currentMenuPanel != null) {
			this.remove( currentMenuPanel );
			currentMenuPanel = null;
		}
		
		GameServer gameServer = new GameServer();
		Thread serverThread = new Thread( gameServer );
		serverThread.start();
	    
		GameMenuPanel gameMenuPanel = new GameMenuPanel( this, gameServer );
		GamePanel gamePanel = new GamePanel( this, gameServer, gameMenuPanel );
		
		this.add( gameMenuPanel , BorderLayout.SOUTH );
		this.add( gamePanel, BorderLayout.CENTER );
		
		this.currentMenuPanel = gameMenuPanel;
		this.currentPanel = gamePanel;
	}
	
	public void joinMultiplayerGame( String serverAddr ) throws Exception {
		if(currentPanel != null) {
			if(currentPanel instanceof GamePanel ) {
				GamePanel gp = (GamePanel) currentPanel;
				if(gp.getGame() instanceof GameServer) ((GameServer) gp.getGame()).getHost().close();
				else if(gp.getGame() instanceof GameClient) ((GameClient) gp.getGame()).getClientSocket().close();
				gp.setGame(null);
			}
			this.remove( currentPanel );
			currentPanel = null;
		}
		if(currentMenuPanel != null) {
			this.remove( currentMenuPanel );
			currentMenuPanel = null;
		}
		
		GameClient gameClient = null;
		
		try {
			gameClient = new GameClient( serverAddr );
			
			Thread clientThread = new Thread( gameClient );
			clientThread.start();
			
			while( gameClient.getClientSocket().isConnected() ) {
			
			}
			
			GameMenuPanel gameMenuPanel = new GameMenuPanel( this, gameClient );
			GamePanel gamePanel = new GamePanel( this, gameClient, gameMenuPanel );	
			
			this.add( gameMenuPanel , BorderLayout.SOUTH );
			this.add( gamePanel, BorderLayout.CENTER );
			
			this.currentMenuPanel = gameMenuPanel;
			this.currentPanel = gamePanel;
		}
		catch(Exception e) {
			e.printStackTrace();
			displayMainMenu();
			JOptionPane.showMessageDialog( null , "Connexion impossible : "+e.getMessage());
		}
	}
	
	public void render() {
		this.getContentPane().validate();	
		this.repaint();
	}
}
