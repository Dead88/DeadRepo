package fr.wretchedlife.ui.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.wretchedlife.core.Game;
import fr.wretchedlife.entity.ext.Enemy;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.ui.Window;
import fr.wretchedlife.ui.utils.KeyEventListener;
import fr.wretchedlife.ui.utils.MouseEventListener;
import fr.wretchedlife.ui.utils.MouseMotionEventListener;

public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = -3464337157282868986L;
	
	private Window window;
	
	private GameMenuPanel gameMenuPanel;
	private Game game;
	
	public Game getGame() {return game;}
	public void setGame(Game game) {this.game = game;}
	
	public GameMenuPanel getGameMenuPanel() {return gameMenuPanel;}
	public void setGameMenuPanel(GameMenuPanel gameMenuPanel) {this.gameMenuPanel = gameMenuPanel;}
	
	public GamePanel( final Window _window, Game _game, GameMenuPanel _gameMenuPanel ) throws Exception {
		this.window = _window;
		this.gameMenuPanel = _gameMenuPanel;
		this.game = _game;
		
		this.setBackground( Color.CYAN );
		this.setFocusable( true );
		this.setVisible( true );
		this.setOpaque(true);

		game.getCurrentRegion().refreshVisibleAreas( this );
		
		this.addMouseListener( new MouseEventListener( this ) );
		this.addMouseMotionListener( new MouseMotionEventListener( this ) );
		this.addKeyListener( new KeyEventListener( this ) );
	}
	
	public void paintComponent(Graphics g) {	
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 
			this.getWidth(),
			this.getHeight());
		
		for(int i = 0; i < game.getCurrentRegion().getAreas().size(); i++) {
			Area area = game.getCurrentRegion().getAreas().get( i );
			
			if( area.isVisible()) {
				g.drawImage( area.getTexture().getImage(), 
					area.getX(), 
					area.getY(), 
					area.getTexture().getIconWidth(),
					area.getTexture().getIconHeight(),
					null
				);
			}
			else if( !area.isVisible()) {
				g.setColor( Color.BLACK );
				g.fillRect(area.getX(), 
					area.getY(), 
					area.getTexture().getIconWidth(),
					area.getTexture().getIconHeight()
				);
			}
			
			if( area.getItem() != null && area.isVisible() ) {
				g.drawImage( area.getItem().getTexture().getImage(), 
					area.getX(), 
					area.getY(), 
					area.getItem().getTexture().getIconWidth(),
					area.getItem().getTexture().getIconHeight(),
					null
				);
			}
			
			if( area.getEntity() != null && area.isVisible() ){
				g.drawImage( area.getEntity().getTexture().getImage(), 
					area.getX(), 
					area.getY(), 
					area.getEntity().getTexture().getIconWidth(),
					area.getEntity().getTexture().getIconHeight(),
					null
				);
				
				if( area.getEntity() instanceof Player ) {
					Player p = (Player) area.getEntity();
					
					g.setColor(Color.GREEN);
					((Graphics2D) g).setStroke(new BasicStroke(2));
					g.drawLine(
						area.getX(), 
						area.getY(),
						area.getX() + ( (int) ( p.getLifeRemain() * 64 ) / p.getLife() ), 
						area.getY()
					);
					
					g.setColor(Color.MAGENTA);
					((Graphics2D) g).setStroke(new BasicStroke(2));
					g.drawLine(
						area.getX(), 
						area.getY() + 3,
						area.getX() + ( (int) ( p.getHungerPercent() * 64 ) / 100 ), 
						area.getY() + 3
					);
					
					g.setColor(Color.CYAN);
					((Graphics2D) g).setStroke(new BasicStroke(2));
					g.drawLine(
						area.getX(), 
						area.getY() + 5,
						area.getX() + ( (int) ( p.getThirstPercent() * 64 ) / 100 ), 
						area.getY() + 5
					);
				}
				else if( area.getEntity() instanceof Enemy ) {
					Enemy e = (Enemy) area.getEntity();
					
					g.setColor(Color.GREEN);
					((Graphics2D) g).setStroke(new BasicStroke(2));
					g.drawLine(
						area.getX(), 
						area.getY(),
						area.getX() + ( (int) ( e.getLifeRemain() * 64 ) / e.getLife() ), 
						area.getY()
					);
				}
			}	
		}
	}
	
	public void gameOver() {
		JOptionPane.showMessageDialog( window.getCurrentPanel() , "GAME OVER !");
		window.displayMainMenu();
	}
}
