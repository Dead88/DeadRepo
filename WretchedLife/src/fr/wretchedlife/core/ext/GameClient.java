package fr.wretchedlife.core.ext;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

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

public class GameClient extends Listener {
	
	private Client client;
	private Game game;
	
	public GameClient( String serverAddr ) throws Exception {
		game = new Game( false );
		game.setRegions( new ArrayList<GameMap>());
		
		System.out.println("Initalizing client...");
		
		client = new Client();
		
		registerClasses();
		
		client.start();
		
		System.out.println("Client started, connecting...");
		
		client.connect( 5000, serverAddr, Constants.multiplayerPort, Constants.multiplayerPort );
		
		client.addListener( this );
	}
	
	public void registerClasses() {
		client.getKryo().register( GameMap.class );
		client.getKryo().register( GameMap.Type.class );
		client.getKryo().register( GameMap.FloorType.class );
		
		client.getKryo().register( Area.class );
		client.getKryo().register( Area.Type.class );
		
		client.getKryo().register( Entity.class );
		client.getKryo().register( Enemy.class );
		client.getKryo().register( Player.class );
		
		client.getKryo().register( Item.class );
		client.getKryo().register( ItemProperty.class );
		client.getKryo().register( ItemProperty.Code.class );
		client.getKryo().register( ArmorItem.class );
		client.getKryo().register( ArmorItem.Type.class );
		client.getKryo().register( WeaponItem.class );
		client.getKryo().register( ConsumableItem.class );
		client.getKryo().register( ContainerItem.class );
		
		client.getKryo().register( RegionEntrance.class );
		
		client.getKryo().register( EntityFactory.class );
		client.getKryo().register( MapFactory.class );
		client.getKryo().register( ItemFactory.class );
		client.getKryo().register( SoundFactory.class );
		
		client.getKryo().register( EntityGenerator.class );
		client.getKryo().register( ItemGenerator.class );
		client.getKryo().register( NameGenerator.class );
		
		client.getKryo().register( ImageIcon.class );
		client.getKryo().register( ArrayList.class );
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
	}
	
	@Override
	public void received(Connection c, Object o) {
		System.out.println("Receiving data from server");
		if( o instanceof GameMap ) {
			GameMap currentServerRegion = (GameMap) o;
			game.getRegions().add( currentServerRegion ); 
			game.setCurrentRegion( currentServerRegion );
		}
	}
}
