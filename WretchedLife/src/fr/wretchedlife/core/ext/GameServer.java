package fr.wretchedlife.core.ext;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.esotericsoftware.kryo.Kryo;
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
	}
	
	public void registerClasses() {
		Kryo kryo = server.getKryo();
		kryo.register( ImageIcon.class );
		kryo.register( ArrayList.class );
		
		kryo.register( GameMap.class );
		kryo.register( GameMap.Type.class );
		kryo.register( GameMap.FloorType.class );
		kryo.register( Area.class );
		kryo.register( Area.Type.class );
		
		kryo.register( Entity.class );
		kryo.register( Enemy.class );
		kryo.register( Player.class );
		kryo.register( RegionEntrance.class );
		
		kryo.register( EntityGenerator.class );
		kryo.register( ItemGenerator.class );
		kryo.register( NameGenerator.class );
		
		kryo.register( EntityFactory.class );
		kryo.register( MapFactory.class );
		kryo.register( ItemFactory.class );
		kryo.register( SoundFactory.class );
		
		kryo.register( Item.class );
		kryo.register( ItemProperty.class );
		kryo.register( ItemProperty.Code.class );
		kryo.register( ArmorItem.class );
		kryo.register( ArmorItem.Type.class );
		kryo.register( WeaponItem.class );
		kryo.register( ConsumableItem.class );
		kryo.register( ContainerItem.class );
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
