package fr.wretchedlife.core;

import java.util.ArrayList;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.utils.XmlTools;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.factory.MapFactory;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;

public class Game {
	
	private Player player;
	private Area playerArea;
	private ArrayList<GameMap> regions;
	private GameMap currentRegion;
	
	public Game( boolean generateRegions ) {
		this.player = new Player();
		
		if(generateRegions) {
			this.regions = MapFactory.generateRegions( player );
			this.currentRegion = regions.get(0);
			
			Area randomArea = currentRegion.getAreas().get( Constants.getRandomBetween( 0 ,  currentRegion.getAreas().size() - 1 ) );
			randomArea.setEntity( player );
			
			this.playerArea = randomArea;
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Area getPlayerArea() {
		return playerArea;
	}
	public void setPlayerArea(Area playerArea) {
		this.playerArea = playerArea;
	}
	public ArrayList<GameMap> getRegions() {
		return regions;
	}
	public void setRegions(ArrayList<GameMap> regions) {
		this.regions = regions;
	}
	public GameMap getCurrentRegion() {
		return currentRegion;
	}
	public void setCurrentRegion(GameMap currentRegion) {
		this.currentRegion = currentRegion;
	}
}

