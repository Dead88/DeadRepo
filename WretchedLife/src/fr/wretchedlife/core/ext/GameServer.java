package fr.wretchedlife.core.ext;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.Game;
import fr.wretchedlife.entity.Entity;
import fr.wretchedlife.entity.ext.Enemy;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.entity.ext.RegionEntrance;
import fr.wretchedlife.factory.EntityFactory;
import fr.wretchedlife.factory.ItemFactory;
import fr.wretchedlife.factory.MapFactory;
import fr.wretchedlife.factory.SoundFactory;
import fr.wretchedlife.generator.EntityGenerator;
import fr.wretchedlife.generator.ItemGenerator;
import fr.wretchedlife.generator.NameGenerator;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;
import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.ItemProperty;
import fr.wretchedlife.obj.ext.ArmorItem;
import fr.wretchedlife.obj.ext.ConsumableItem;
import fr.wretchedlife.obj.ext.ContainerItem;
import fr.wretchedlife.obj.ext.WeaponItem;

public class GameServer extends Listener {

	private Server server;
	private Game game;
	
	public GameServer() {
		try {
			server = new Server();
			
			registerClasses();
					
			server.bind( Constants.multiplayerPort, Constants.multiplayerPort );
			server.start();
			
			server.addListener( this );
			
			System.out.println("Server started...");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		game = new Game( true );
		
		System.out.println("Game ready, server too...");
	}
	
	public void registerClasses() {
		server.getKryo().register( GameMap.class );
		server.getKryo().register( GameMap.Type.class );
		server.getKryo().register( GameMap.FloorType.class );
		
		server.getKryo().register( Area.class );
		server.getKryo().register( Area.Type.class );
		
		server.getKryo().register( Entity.class );
		server.getKryo().register( Enemy.class );
		server.getKryo().register( Player.class );
		
		server.getKryo().register( Item.class );
		server.getKryo().register( ItemProperty.class );
		server.getKryo().register( ItemProperty.Code.class );
		server.getKryo().register( ArmorItem.class );
		server.getKryo().register( ArmorItem.Type.class );
		server.getKryo().register( WeaponItem.class );
		server.getKryo().register( ConsumableItem.class );
		server.getKryo().register( ContainerItem.class );
		
		server.getKryo().register( RegionEntrance.class );
		
		server.getKryo().register( EntityFactory.class );
		server.getKryo().register( MapFactory.class );
		server.getKryo().register( ItemFactory.class );
		server.getKryo().register( SoundFactory.class );
		
		server.getKryo().register( EntityGenerator.class );
		server.getKryo().register( ItemGenerator.class );
		server.getKryo().register( NameGenerator.class );
		
		server.getKryo().register( ImageIcon.class );
		server.getKryo().register( ArrayList.class );
	}
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void connected(Connection c) {
		System.out.println("Client connected...");
		GameMap currentServerRegion = game.getCurrentRegion();
		c.sendTCP( currentServerRegion );
	}
	
	@Override
	public void received(Connection c, Object o) {
	}
	
	@Override
	public void disconnected(Connection arg0) {
	}
}
